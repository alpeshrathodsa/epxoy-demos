package com.sa.demo.exoxycarouseldemo.base.utils

object Constant {
    const val PREFERENCE_NAME = "com.tradeloop.prefs"
    const val MAX_CLICK_INTERVAL: Long = 500//Max time interval to prevent double click

    const val REQUEST_CODE_PERMISSION_DOC_PICKER = 1001
    const val REQUEST_CODE_PERMISSION_BOARD_PICKER = 1002
    const val REQUEST_CODE_DOC_PICKER = 1003
    const val REQUEST_CODE_BOARD_PICKER = 1004
    const val REQUEST_CODE_PERMISSION_DOWNLOAD_INVOICE = 1005

    const val DATE_FORMAT_1: String = "dd/MM/yyyy"
    const val DATE_FORMAT_2: String = "dd-MM-yyyy"

    /*Folder name for sdcard*/
    const val FOLDER_APP_MEDIA = "Tradeloop"

    //binding property
    const val PROPERTY_BIND_SWIPE_REFRESH_LISTENER = "onRefreshListener"
    const val PROPERTY_BIND_IS_REFRESHING = "isRefreshing"

    /*IMAGE EXTENSION*/
    const val FILE_TYPE_IMAGE = "image"
    const val IMAGE_EXT_PNG = "png"
    const val IMAGE_EXT_JPG = "jpg"
    const val IMAGE_EXT_JPEG = "jpeg"

    const val FILE_TYPE_DOC = "doc"
    const val DOC_EXT_PDF = "pdf"

    //mime types
    const val IMAGE_PNG = "image/png"
    const val IMAGE_JPEG = "image/jpeg"
    const val IMAGE_JPG = "image/jpg"
    const val DOC_PDF = "application/pdf"

    const val MEDIA_MIME_TEXT_PLAIN = "text/plain"
    const val MEDIA_MIME_FOLDER = "resource/folder"

    const val BUNDLE_EXTRA_KEY_MOBILE = "bundle.extras.key.mobile"
    const val BUNDLE_EXTRA_KEY_MOBILE_VERIFY = "bundle.extras.key.mobile_verify"
    const val BUNDLE_EXTRA_KEY_MOBILE_VERIFICATION = "bundle.extras.key.mobile_verification"
    const val BUNDLE_EXTRA_KEY_CATEGORY_LIST = "bundle.extras.key.category.list"
    const val BUNDLE_EXTRA_KEY_CATEGORY = "bundle.extras.key.category"
    const val BUNDLE_EXTRA_KEY_SUB_CATEGORY = "bundle.extras.key.sub.category"
    const val BUNDLE_EXTRA_KEY_SUB_CATEGORY_ID = "bundle.extras.key.sub.category.id"
    const val BUNDLE_EXTRA_KEY_SELLER = "bundle.extras.key.seller"
    const val BUNDLE_EXTRA_KEY_PRODUCT = "bundle.extras.key.product"
    const val BUNDLE_EXTRA_KEY_PRODUCT_ID = "bundle.extras.key.product.id"
    const val BUNDLE_EXTRA_KEY_PRODUCT_NAME = "bundle.extras.key.product.name"
    const val BUNDLE_EXTRA_DETAIL_TYPE = "bundle.extras.key.detail.type"
    const val BUNDLE_EXTRA_WALK_THROUGH_POSITION = "bundle.extras.key.walk.through.position"
    const val BUNDLE_EXTRA_IMAGE = "bundle.extras.key.image"
    const val BUNDLE_EXTRA_DOCUMENT = "bundle.extras.key.document"
    const val BUNDLE_EXTRA_SHOP_BOARDS = "bundle.extras.key.shop.board"
    const val BUNDLE_EXTRA_DOCUMENT_TYPE = "bundle.extras.key.document.type"
    const val BUNDLE_EXTRA_SELLER_ID = "bundle.extras.key.seller.id"
    const val BUNDLE_EXTRA_SELLER_NAME = "bundle.extras.key.seller.name"
    const val BUNDLE_EXTRA_ADDRESS = "bundle.extras.key.address"
    const val BUNDLE_EXTRA_ORDER_ID = "bundle.extras.key.order.id"
    const val BUNDLE_EXTRA_ORDER = "bundle.extras.key.order"
    const val BUNDLE_EXTRA_MESSAGE_SUCCESS = "bundle.extras.key.message.success"
    const val BUNDLE_EXTRA_BUTTON_ACTION = "bundle.extras.key.button.action"
    const val BUNDLE_EXTRA_QUERY = "bundle.extras.key.query"
    const val BUNDLE_EXTRA_QUERY_DESCRIPTION = "bundle.extras.key.query.description"
    const val BUNDLE_EXTRA_IS_FROM_SELLER_PROFILE = "bundle.extras.key.from.seller.profile"
    const val BUNDLE_EXTRA_CMS_TYPE = "bundle.extras.key.cms.type"
    const val BUNDLE_EXTRA_RETURN_ORDER_ID = "bundle.extras.key.return.order.id"

    const val SELLER_LIMIT = 10
    const val PRODUCT_LIMIT = 10
    const val DEFAULT_SNACKBAR_DURATION = 4000

    const val PREF_NETWORK_STATE = "PREF_NETWORK_STATE"
    const val PRODUCT_TYPE_COMBO = "Combo"
    const val QUERY_STATUS_TYPE = "resolved"

    const val TYPE_DATE = 0
    const val TYPE_SENDER = 1
    const val TYPE_RECEIVER = 2

    const val API_DATE = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val DEVICE_TYPE = "android"

    const val APP_URL_ANDROID = "https://play.google.com/store/apps/details?id=com.tradeloop.tradeloop"
    const val branchCustomData = "custom_data"
    const val branchAndroidUrl = "\$android_url"
    const val branchId = "content/refer"
    const val deviceId = "deviceId"
    const val KEY_PRODUCT_ID = "productId"
    const val KEY_PRODUCT_NAME = "productName"
}