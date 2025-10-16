package com.example.farmingfriend

import android.graphics.Bitmap
import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class TFLiteClassifier(assetManager: AssetManager, modelPath: String, private val numClasses: Int) {
    private val interpreter: Interpreter = Interpreter(loadModelFile(assetManager, modelPath))
    private fun loadModelFile(assetManager: AssetManager, modelPath: String): MappedByteBuffer {
        val fileDescriptor: AssetFileDescriptor = assetManager.openFd(modelPath)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }
    fun classify(bitmap: Bitmap): Pair<String, Float> {
        val imageProcessor = ImageProcessor.Builder()
            .add(ResizeOp(224, 224, ResizeOp.ResizeMethod.BILINEAR))
            .add(NormalizeOp(0f, 255f))
            .build()
        var tensorImage = TensorImage(DataType.FLOAT32)
        tensorImage.load(bitmap)
        tensorImage = imageProcessor.process(tensorImage)
        val outputBuffer = TensorBuffer.createFixedSize(intArrayOf(1, numClasses), DataType.FLOAT32)
        interpreter.run(tensorImage.buffer, outputBuffer.buffer.rewind())
        val confidences = outputBuffer.floatArray
        val maxIndex = confidences.indices.maxByOrNull { confidences[it] } ?: -1
        val confidence = if (maxIndex != -1) confidences[maxIndex] else 0f
        val labels = listOf("Healthy", "Powdery", "Rust") // Update labels as per your model
        return Pair(labels.getOrElse(maxIndex) { "Unknown" }, confidence)
    }
}
