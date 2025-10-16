package com.example.farmingfriend

data class Remedy(
    val medName: String? = null,      // medicine name for chemical remedies
    val remedy: String? = null,       // remedy name for ayurvedic
    val instructions: String
)

data class CropDetailResponse(
    val cropName: String,
    val disease: String,
    val chemical: List<Remedy>?,
    val ayurvedic: List<Remedy>?,
    val organic: List<Remedy>?,
    val biological: List<Remedy>?
)
