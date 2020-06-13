package com.sa.demo.exoxycarouseldemo.base.model

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import com.sa.demo.exoxycarouseldemo.base.R
import com.sa.demo.exoxycarouseldemo.base.model.remote.result.BaseError
import com.sa.demo.exoxycarouseldemo.base.model.remote.result.ReqAddCart
import com.sa.demo.exoxycarouseldemo.base.model.remote.result.ResCheckPrice
import com.sa.demo.exoxycarouseldemo.base.utils.ApiConstant
import com.sa.demo.exoxycarouseldemo.base.utils.CommonUtils
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response
import java.io.File
import java.net.ConnectException


open class BaseRepository constructor(private val resource: Resources) {

    suspend fun <T : Any> safeApiCall(
        call: suspend () -> Response<T>,
        resultLiveData: MutableLiveData<Resource<T>>? = null
    ): Any? {
        val result: Resource<T> = safeApiResult(call)
        var data: Any? = null

        when (result) {
            is Resource.Success -> data = result.data
            is Resource.Error -> {
                if (resultLiveData == null) data = result.error
                else resultLiveData.postValue(Resource.Error(result.error))
            }
        }
        return data
    }

    private suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                return if (response.body() != null) {
                    Resource.Success(response.body()!!)
                } else {
                    val baseError = BaseError()
                    baseError.code = ApiConstant.SOMETHING_WRONG_ERROR_STATUS
                    baseError.message = resource.getString(R.string.error_something_went_wrong)
                    Resource.Error(baseError)
                }
            } else {
                if (response.errorBody() != null) {

                    if (response.headers()[ApiConstant.HEADER_KEY_CONTENT_TYPE]?.contains(
                            ApiConstant.HEADER_VALUE_CONTENT_TYPE_JSON
                        )!!
                    ) { //check if response is in json format

                        //todo add when (response.code()) {}
                        val errorString = response.errorBody()?.string()
                        val baseError = BaseError()
                        if (CommonUtils.isNotNullOrNotEmpty(errorString)) {
                            val jsonResponse = JSONObject(errorString)
                            try {
                                baseError.code = response.code().toString()
                                val error = jsonResponse.get(ApiConstant.KEY_ERROR).toString()
                                if (error.contains(ApiConstant.KEY_MESSAGE)) {
                                    val jsonError = JSONObject(error)
                                    baseError.message =
                                        jsonError.get(ApiConstant.KEY_MESSAGE).toString()
                                } else {
                                    baseError.message = error
                                }
                                if (errorString?.contains(ApiConstant.KEY_CONFLICT_ITEM) == true)
                                    baseError.conflictItem =
                                        jsonResponse.get(ApiConstant.KEY_CONFLICT_ITEM) as Boolean
                                if (errorString?.contains(ApiConstant.KEY_IS_DELETED) == true)
                                    baseError.isDeleted =
                                        jsonResponse.get(ApiConstant.KEY_IS_DELETED) as Boolean
                                if (errorString?.contains(ApiConstant.KEY_IS_ACTIVE) == true)
                                    baseError.isActive =
                                        jsonResponse.get(ApiConstant.KEY_IS_ACTIVE) as Boolean
                                if (errorString?.contains(ApiConstant.KEY_IS_APPROVED) == true)
                                    baseError.isApproved =
                                        jsonResponse.get(ApiConstant.KEY_IS_APPROVED) as Boolean
                                if (errorString?.contains(ApiConstant.KEY_PRICE_AVAILABLE) == true)
                                    baseError.priceAvailable =
                                        jsonResponse.get(ApiConstant.KEY_PRICE_AVAILABLE) as Boolean
                                if (errorString?.contains(ApiConstant.KEY_VARIANTS) == true) {
                                    val data =
                                        ArrayList<ResCheckPrice>()
                                    val jArray =
                                        jsonResponse.get(ApiConstant.KEY_VARIANTS) as JSONArray
                                    for (i in 0 until jArray.length()) {
                                        val obj = jArray.getJSONObject(i)
                                        val variant = JSONObject(obj.getString("variant"))
                                        data.add(
                                            ResCheckPrice(
                                                priceAvailable = (obj.getBoolean("priceAvailable")),
                                                variant = ReqAddCart(
                                                    primaryPvId = if (variant.has("primaryPvId")) {
                                                        if (!variant.get("primaryPvId").toString().contains("null"))
                                                            variant.getInt(
                                                                "primaryPvId"
                                                            ) else null
                                                    } else null,
                                                    secondaryPvId = if (variant.has("secondaryPvId")) {
                                                        if (!variant.get("secondaryPvId").toString().contains("null"))
                                                            variant.getInt(
                                                                "secondaryPvId"
                                                            ) else null
                                                    } else null,
                                                    productId = variant.getInt("productId"),
                                                    quantity = variant.getInt("quantity")
                                                )
                                            )
                                        )
                                    }
                                    baseError.variants = data

                                }
                            } catch (e: Exception) {
                                baseError.code = ApiConstant.SOMETHING_WRONG_ERROR_STATUS
                                baseError.message =
                                    resource.getString(R.string.error_something_went_wrong)
                            }
                        } else {
                            baseError.code = ApiConstant.SOMETHING_WRONG_ERROR_STATUS
                            baseError.message =
                                resource.getString(R.string.error_something_went_wrong)
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
                                baseError.message =
                                    resource.getString(R.string.error_internal_server)
                                return Resource.Error(baseError)
                            }
                            else -> {
                                val baseError = BaseError()
                                baseError.code = ApiConstant.SOMETHING_WRONG_ERROR_STATUS
                                baseError.message =
                                    resource.getString(R.string.error_something_went_wrong)
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