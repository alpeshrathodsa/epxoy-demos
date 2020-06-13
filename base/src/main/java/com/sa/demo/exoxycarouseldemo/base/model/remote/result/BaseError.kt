package com.sa.demo.exoxycarouseldemo.base.model.remote.result

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

class BaseError : BaseResult() {
    @SerializedName("code")
    var code: String? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("conflictItem")
    var conflictItem: Boolean? = null

    @SerializedName("isDeleted")
    var isDeleted: Boolean? = null

    @SerializedName("isActive")
    var isActive: Boolean? = null

    @SerializedName("approved")
    var isApproved: Boolean? = null

    @SerializedName("priceAvailable")
    var priceAvailable: Boolean? = true

    @SerializedName("variants")
    var variants: List<ResCheckPrice>? = null
}

@Keep
data class ResCheckPrice(

    @field:SerializedName("variant")
    val variant: ReqAddCart? = null,

    @field:SerializedName("priceAvailable")
    val priceAvailable: Boolean? = null
)

@Keep
data class ReqAddCart(

    @field:SerializedName("quantity")
    val quantity: Int? = null,

    @field:SerializedName("productId")
    val productId: Int? = null,

    @field:SerializedName("secondaryPvId")
    val secondaryPvId: Int? = null,

    @field:SerializedName("primaryPvId")
    val primaryPvId: Int? = null
)