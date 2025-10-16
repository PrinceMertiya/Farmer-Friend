package com.example.farmingfriend

data class CropResponse(
    val cropName: String,
    val isGood: Boolean,
    val remedies: List<String>
)

