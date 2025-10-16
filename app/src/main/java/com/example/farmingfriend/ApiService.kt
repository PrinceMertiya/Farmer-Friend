package com.example.farmingfriend

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST("/analyzeCrop")
    fun uploadCropImage(
        @Part image: MultipartBody.Part
    ): Call<CropResponse>

    @Multipart
    @POST("/analyzeWithDetails")
    fun analyzeCropWithDetails(
        @Part("cropName") cropName: RequestBody,
        @Part("disease") disease: RequestBody,
        @Part("remedyType") remedyType: RequestBody,  // add this
        @Part image: MultipartBody.Part
    ): Call<CropDetailResponse>

}
