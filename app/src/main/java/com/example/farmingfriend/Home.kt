package com.example.farmingfriend

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.farmingfriend.databinding.FragmentHomeBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import android.widget.TextView
class Home : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var photoFile: File

    private val WEATHER_API_KEY = "6454ef65be194779c39f271112175408"

    /** Modern Camera API launcher */
    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            binding.cameraButton.setImageURI(Uri.fromFile(photoFile))
            uploadImage(photoFile)
        } else {
            Toast.makeText(requireContext(), "Camera cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    /** Location Permission Launcher */
    private val locationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) getCurrentLocationWeather()
        else Toast.makeText(requireContext(), "Location permission denied", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Set today's date and day
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yy EEEE", Locale.getDefault())
        val todayDate = dateFormat.format(calendar.time)
        binding.textView5.text = "Date: $todayDate"

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        setupSpinners()
        checkLocationPermission()
        binding.cameraButton.setOnClickListener { openCamera() }

        return binding.root
    }


    /** Setup Spinner logic **/
    private fun setupSpinners() {
        val spinnerState: Spinner = binding.spinnerState
        val spinnerCity: Spinner = binding.spinnerCity
        val spinnerMarket: Spinner = binding.spinnerMarket

        val states = arrayOf("Select State", "Maharashtra", "Gujarat", "Rajasthan")
        val citiesMap = mapOf(
            "Maharashtra" to arrayOf("Mumbai", "Pune", "Nagpur"),
            "Gujarat" to arrayOf("Ahmedabad", "Surat", "Vadodara"),
            "Rajasthan" to arrayOf("Jaipur", "Udaipur", "Jodhpur")
        )
        val marketsMap = mapOf(
            "Mumbai" to arrayOf("Market1", "Market2"),
            "Pune" to arrayOf("Market3", "Market4"),
            "Ahmedabad" to arrayOf("Market5", "Market6")
            // Add other mappings accordingly
        )

        // State adapter
        val stateAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, states)
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerState.adapter = stateAdapter

        spinnerState.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedState = states[position]
                val cities = if (selectedState != "Select State") citiesMap[selectedState] ?: arrayOf("Select City") else arrayOf("Select City")
                val cityAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, cities)
                cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerCity.adapter = cityAdapter

                // Reset market spinner when state changes
                val defaultMarkets = arrayOf("Select Market")
                val marketAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, defaultMarkets)
                marketAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerMarket.adapter = marketAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCity = spinnerCity.selectedItem.toString()
                val markets = if (selectedCity != "Select City") marketsMap[selectedCity] ?: arrayOf("Select Market") else arrayOf("Select Market")
                val marketAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, markets)
                marketAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerMarket.adapter = marketAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    /** Location permission check **/
    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            getCurrentLocationWeather()
        } else {
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    /** Fetch current weather based on location **/
    private fun getCurrentLocationWeather() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                fetchWeatherData(location.latitude, location.longitude)
            } else {
                Toast.makeText(requireContext(), "Unable to fetch location", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener { e ->
            Toast.makeText(requireContext(), "Location error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchWeatherData(lat: Double, lon: Double) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherApiService::class.java)
        service.getCurrentWeather(lat, lon, WEATHER_API_KEY, "metric")
            .enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    if (response.isSuccessful) {
                        response.body()?.let { weather ->
                            binding.tvLocation.text = weather.name
                            binding.tvTemperature.text = "${weather.main.temp} °C"
                            binding.tvCloudValue.text = "${weather.clouds.all}% "
                            binding.tvHumidity.text = "${weather.main.humidity}%"
                            binding.tvTempValue.text = "${weather.main.temp} °C"
                            binding.tvSunValue.text = weather.weather.firstOrNull()?.description ?: "Clear"
                        }
                    } else {
                        Toast.makeText(requireContext(), "Weather API error", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Toast.makeText(requireContext(), "Weather fetch failed: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }

    /** Camera with FileProvider **/
    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile = File(requireContext().getExternalFilesDir(null), "crop.jpg")
        val uri: Uri = FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.provider",
            photoFile
        )
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        cameraLauncher.launch(intent)
    }

    /** Upload image using Retrofit **/
    /** Upload image using Retrofit with compression **/
    private fun uploadImage(file: File) {
        try {
            // Decode the image to Bitmap
            val bitmap = android.graphics.BitmapFactory.decodeFile(file.absolutePath)

            // Create a new compressed file
            val compressedFile = File(requireContext().cacheDir, "compressed_${file.name}")
            val outputStream = java.io.FileOutputStream(compressedFile)

            // Compress the bitmap (JPEG, quality 70%)
            bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 60, outputStream)
            outputStream.flush()
            outputStream.close()

            // Use compressed file instead of original
            val requestBody = compressedFile.asRequestBody("image/*".toMediaType())
            val imagePart = MultipartBody.Part.createFormData("image", compressedFile.name, requestBody)

            RetrofitClient.instance.uploadCropImage(imagePart)
                .enqueue(object : Callback<CropResponse> {
                    override fun onResponse(call: Call<CropResponse>, response: Response<CropResponse>) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                binding.tvCropName.text = "Crop: ${it.cropName}"
                                binding.tvCropStatus.text = if (it.isGood) "Status: Healthy ✅" else "Status: Unhealthy ❌"
                                binding.tvCropRemedies.text = "Remedies: ${it.remedies.joinToString(", ")}"
                                binding.cropResultLayout.visibility = View.VISIBLE
                            }
                        } else {
                            Toast.makeText(requireContext(), "Failed: ${response.message()}", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<CropResponse>, t: Throwable) {
                        Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Compression failed: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
