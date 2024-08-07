package com.imax.cefr.core.base.source

import com.imax.cefr.core.base.result.AnotherError
import com.imax.cefr.core.base.result.ResultModel
import retrofit2.HttpException
import retrofit2.Response

abstract class BaseDataSource {

    protected suspend fun <T> invokeRequest(request: suspend () -> Response<T>): ResultModel<T> {
        return try {
            val response = request()
            if (response.isSuccessful)
                ResultModel.success(response.body())
            else ResultModel.error(
                AnotherError(
                    response.message(),
                    response.code()
                )
            )
        } catch (httpException: HttpException){
            ResultModel.error(httpException)
        } catch (exception: Exception) {
            ResultModel.error(exception)
        }
    }
}

