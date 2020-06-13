package com.sa.demo.exoxycarouseldemo.base.model

import android.content.res.Resources
import com.sa.demo.exoxycarouseldemo.base.R
import com.sa.demo.exoxycarouseldemo.base.model.remote.result.BaseError
import com.sa.demo.exoxycarouseldemo.base.model.remote.result.BaseResult
import com.sa.demo.exoxycarouseldemo.base.model.remote.result.DownloadFileResult
import com.sa.demo.exoxycarouseldemo.base.utils.ApiConstant
import com.sa.demo.exoxycarouseldemo.base.utils.CommonUtils
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import java.io.File
import java.net.ConnectException

/**
 * Created by Alpesh Rathod on 4/12/19.
 */
open class DownloadableRepository constructor(private val resource: Resources) {
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Any? {
        val result: Resource<T> = safeApiResult(call)
        var data: BaseResult? = null
        val fileResult: DownloadFileResult?

        when (result) {
            is Resource.Success -> {
                fileResult = DownloadFileResult(result.data as ResponseBody)
                return fileResult
            }
            is Resource.Error -> data = result.error
        }
        return data
    }

    private suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                if (response.body() != null) {
                    return Resource.Success(response.body()!!)

                } else {
                    val baseError = BaseError()
                    baseError.code = ApiConstant.SOMETHING_WRONG_ERROR_STATUS
                    baseError.message = resource.getString(R.string.error_something_went_wrong)
                    return Resource.Error(baseError)
                }
            } else {
                if (response.errorBody() != null) {

                    if (response.headers()[ApiConstant.HEADER_KEY_CONTENT_TYPE]?.contains(ApiConstant.HEADER_VALUE_CONTENT_TYPE_JSON)!!) { //check if response is in json format

                        //todo add when (response.code()) {}
                        val errorString = response.errorBody()?.string()
                        val baseError = BaseError()
                        if (CommonUtils.isNotNullOrNotEmpty(errorString)) {
                            val jsonResponse = JSONObject(errorString)
                            try {
                                baseError.code = response.code().toString()
                                val error = jsonResponse.get(ApiConstant.KEY_ERROR).toString()
                                val jsonError = JSONObject(error)
                                baseError.message = jsonError.get(ApiConstant.KEY_MESSAGE).toString()
                            } catch (e: Exception) {
                                baseError.code = ApiConstant.SOMETHING_WRONG_ERROR_STATUS
                                baseError.message = resource.getString(R.string.error_something_went_wrong)
                            }
                        } else {
                            baseError.code = ApiConstant.SOMETHING_WRONG_ERROR_STATUS
                            baseError.message = resource.getString(R.string.error_something_went_wrong)
                        }
                        return Resource.Error(baseError)

                    } else { //if response is not in json format than handle it

                        when (response.code()) {
                            403 -> {
                                val baseError = BaseError()
                                baseError.code = ApiConstant.IO_EXCEPTION_STATUS
                                baseError.message = resource.getString(R.string.error_io_exception)
                                return Resource.Error(baseError)
                            }
                            500 -> {
                                val baseError = BaseError()
                                baseError.code = ApiConstant.INTERNAL_SERVER_STATUS
                                baseError.message = resource.getString(R.string.error_internal_server)
                                return Resource.Error(baseError)
                            }
                            else -> {
                                val baseError = BaseError()
                                baseError.code = ApiConstant.SOMETHING_WRONG_ERROR_STATUS
                                baseError.message = resource.getString(R.string.error_something_went_wrong)
                                return Resource.Error(baseError)
                            }
                        }
                    }
                } else {
                    val baseError = BaseError()
                    baseError.code = ApiConstant.SOMETHING_WRONG_ERROR_STATUS
                    baseError.message = resource.getString(R.string.error_something_went_wrong)
                    return Resource.Error(baseError)
                }

            }
        } catch (error: Exception) {
            return when (error) {
                is ConnectException -> {
                    val baseError = BaseError()
                    baseError.code = ApiConstant.TIME_OUT_CONNECTION_STATUS
                    baseError.message = resource.getString(R.string.error_connection_timeout)
                    Resource.Error(baseError)
                }

                else -> {
                    val baseError = BaseError()
                    baseError.code = ApiConstant.SOMETHING_WRONG_ERROR_STATUS
                    baseError.message = resource.getString(R.string.error_something_went_wrong)
                    Resource.Error(baseError)
                }
            }

        }

    }

    fun createRequestBody(file: File, mediaType: String): RequestBody {
        return RequestBody.create(MediaType.parse(mediaType), file)
    }
}