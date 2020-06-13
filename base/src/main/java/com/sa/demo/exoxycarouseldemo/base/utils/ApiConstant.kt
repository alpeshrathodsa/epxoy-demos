package com.sa.demo.exoxycarouseldemo.base.utils

object ApiConstant {

    /*todo add url in different flavour*/
    const val BASE_URL = Environment.BASE_URL
//    const val BASE_PREFIX = "api/"
    const val BASE_PREFIX = ""

    const val INTERCEPTOR_LOGGING = "LOGGING_INTERCEPTOR"
    const val INTERCEPTOR_OK_HTTP = "OK_HTTP_INTERCEPTOR"
    const val TAG_OK_HTTP = "OkHttp"

    const val INTERNAL_SERVER = "We could not complete your request."
    const val INTERNAL_SERVER_STATUS = "500"

    const val NO_INTERNET_CONNECTION = "No internet connection."
    const val NO_INTERNET_CONNECTION_STATUS = "503"

    const val TIME_OUT_CONNECTION = "Cannot connect to server.\nPlease try again later."
    const val TIME_OUT_CONNECTION_STATUS = "504"

    const val SOMETHING_WRONG_ERROR = "Something went wrong!!\nPlease try again later."
    const val SOMETHING_WRONG_ERROR_STATUS = "505"

    const val OTHER_EXCEPTION = "Something went wrong!!\nPlease try again later."
    const val OTHER_EXCEPTION_STATUS = "505"

    const val IO_EXCEPTION = "We could not complete your request."
    const val IO_EXCEPTION_STATUS = "403"

    const val HEADER_KEY_AUTHORIZATION: String = "authorization"
    const val HEADER_KEY_TOKEN: String = "token"

    const val HEADER_KEY_CONTENT_TYPE: String = "Content-Type"
    const val HEADER_VALUE_CONTENT_TYPE_JSON: String = "application/json"

    const val KEY_ERROR: String = "error"
    const val KEY_MESSAGE: String = "message"
    const val KEY_CONFLICT_ITEM: String = "conflictItem"
    const val KEY_IS_ACTIVE: String = "isActive"
    const val KEY_IS_DELETED: String = "isDeleted"
    const val KEY_IS_APPROVED: String = "approved"
    const val KEY_PRICE_AVAILABLE: String = "priceAvailable"
    const val KEY_VARIANTS: String = "variants"

    const val TOKEN_PREFIX: String = "Bearer "
}