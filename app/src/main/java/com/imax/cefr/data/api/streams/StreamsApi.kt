package com.imax.cefr.data.api.streams

import com.imax.cefr.data.models.stream.CreateStreamRequestData
import com.imax.cefr.data.models.stream.CreateStreamResponseData
import com.imax.cefr.data.models.stream.all.AllStreamsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query


//


interface StreamsApi {

    @GET("/api/streams")
    suspend fun getAllStreams(@Query("page") page: Int, @Query("limit") limit: Int): Response<AllStreamsResponse>

    @GET("/streams/{id}")
    suspend fun getSpecificStream(@Path("id") streamId: Int)

    @POST("/api/streams")
    suspend fun createStream(@Body createStreamRequestData: CreateStreamRequestData): Response<CreateStreamResponseData>

    @PUT("/streams/{id}")
    suspend fun updateStream(@Path("id") streamId: Int)

    @DELETE("/streams/{id}")
    suspend fun deleteStream()



}
