// Profile.kt
package com.example.farmingfriend

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.farmingfriend.databinding.FragmentProfileBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class Profile : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var photoFile: File
    private lateinit var compressedFile: File

    private lateinit var classifier: TFLiteClassifier

    private val MODEL_INPUT_SIZE = 224
    private val MODEL_INPUT_CHANNELS = 3
    private val NUM_CLASSES = 3 // Must match the classifier's numClasses and labels count

    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == android.app.Activity.RESULT_OK) {
            binding.ivPhoto.setImageURI(Uri.fromFile(photoFile))

            val bitmap = decodeSampledBitmapFromFile(photoFile.absolutePath, MODEL_INPUT_SIZE, MODEL_INPUT_SIZE)

            // Classify using TFLiteClassifier instance
            val (predictedLabel, confidence) = classifier.classify(bitmap)

            binding.tvPrediction.text = "Prediction: $predictedLabel (Confidence ${(confidence * 100).toInt()}%)"

            compressedFile = File(requireContext().cacheDir, "compressed_${photoFile.name}")
            compressBitmapToFile(bitmap, compressedFile, 75)

            binding.btnLayout.visibility = View.VISIBLE
            binding.btnLayout1.visibility = View.VISIBLE
        } else {
            Toast.makeText(requireContext(), "Camera cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yy EEEE", Locale.getDefault())
        binding.tvDate.text = "Date: ${dateFormat.format(calendar.time)}"

        binding.btnLayout.visibility = View.GONE
        binding.btnLayout1.visibility = View.GONE
        binding.tvPrediction.text = ""

        // Initialize classifier with correct number of classes
        classifier = TFLiteClassifier(requireContext().assets, "model.tflite", NUM_CLASSES)

        setupSpinners()
        setupListeners()
        return binding.root
    }

    private fun setupSpinners() {
        val crops = arrayOf(
            "Select", "Wheat", "Rice", "Corn", "Soybean", "Barley",
            "Cotton", "Sugarcane", "Potato", "Tomato", "Chili", "Onion",
            "Coffee", "Tea", "Banana", "Apple", "Mango"
        )
        val diseases = arrayOf(
            "Select", "Pest Infestation", "Poor Growth",
            "Weather Damage", "Soil Issues", "Fungal Infection"
        )
        binding.spinnerCrop.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, crops).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        binding.spinnerDisease.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, diseases).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
    }

    private fun setupListeners() {
        binding.ivPhoto.setOnClickListener { openCamera() }
        binding.btnChemical.setOnClickListener { uploadImageWithDetails("chemical") }
        binding.btnAyurvedic.setOnClickListener { uploadImageWithDetails("ayurvedic") }
        binding.btnOrganic.setOnClickListener { uploadImageWithDetails("organic") }
        binding.btnBiological.setOnClickListener { uploadImageWithDetails("biological") }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile = File(requireContext().getExternalFilesDir(null), "crop_${System.currentTimeMillis()}.jpg")
        val uri = FileProvider.getUriForFile(requireContext(), "${requireContext().packageName}.provider", photoFile)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        cameraLauncher.launch(intent)
    }

    private fun uploadImageWithDetails(remedyType: String) {
        val crop = binding.spinnerCrop.selectedItem?.toString() ?: ""
        val disease = binding.spinnerDisease.selectedItem?.toString() ?: ""

        if (crop == "Select" || disease == "Select") {
            Toast.makeText(requireContext(), "Please select crop and disease", Toast.LENGTH_SHORT).show()
            return
        }
        if (!::compressedFile.isInitialized || !compressedFile.exists()) {
            Toast.makeText(requireContext(), "Please capture an image first", Toast.LENGTH_SHORT).show()
            return
        }

        val cropPart = crop.lowercase().toRequestBody("text/plain".toMediaType())
        val diseasePart = disease.lowercase().toRequestBody("text/plain".toMediaType())
        val remedyTypePart = remedyType.toRequestBody("text/plain".toMediaType())
        val imagePart = MultipartBody.Part.createFormData(
            "image",
            compressedFile.name,
            compressedFile.asRequestBody("image/*".toMediaType())
        )

        RetrofitClient.instance.analyzeCropWithDetails(cropPart, diseasePart, remedyTypePart, imagePart)
            .enqueue(object : Callback<CropDetailResponse> {
                override fun onResponse(
                    call: Call<CropDetailResponse>,
                    response: Response<CropDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            val remedies = when (remedyType) {
                                "chemical" -> data.chemical
                                "ayurvedic" -> data.ayurvedic
                                "organic" -> data.organic
                                "biological" -> data.biological
                                else -> emptyList()
                            }
                            if (!remedies.isNullOrEmpty()) {
                                displayRemedies(listOf(remedies.random()))
                            } else {
                                binding.tvRemedies.text = "No remedies found for $crop - $disease"
                            }
                        } else {
                            binding.tvRemedies.text = "Empty response from server"
                        }
                    } else {
                        binding.tvRemedies.text = "Error: ${response.code()} - ${response.message()}"
                    }
                }

                override fun onFailure(call: Call<CropDetailResponse>, t: Throwable) {
                    val staticRemedy = StaticRemedies.getRemedies(crop, disease, remedyType)
                    lifecycleScope.launch {
                        delay(1000)
                        displayRemedies(staticRemedy)
                    }
                }
            })
    }

    private fun displayRemedies(remedies: List<Remedy>) {
        val builder = StringBuilder()
        remedies.forEach {
            if (!it.medName.isNullOrEmpty()) builder.append("Medicine: ${it.medName}\n")
            if (!it.remedy.isNullOrEmpty()) builder.append("Remedy: ${it.remedy}\n")
            builder.append("Instructions: ${it.instructions}\n\n")
        }
        binding.tvRemedies.text = builder.toString()
    }

    private fun decodeSampledBitmapFromFile(filePath: String, reqWidth: Int, reqHeight: Int): Bitmap {
        val options = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        BitmapFactory.decodeFile(filePath, options)
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)
        options.inJustDecodeBounds = false
        val bitmap = BitmapFactory.decodeFile(filePath, options)
        return Bitmap.createScaledBitmap(bitmap, reqWidth, reqHeight, true)
    }

    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        val (height, width) = options.run { outHeight to outWidth }
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

    private fun compressBitmapToFile(bitmap: Bitmap, outputFile: File, quality: Int = 80) {
        FileOutputStream(outputFile).use { out ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
