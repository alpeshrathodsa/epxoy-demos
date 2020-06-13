package com.sa.demo.exoxycarouseldemo.data

data class Profile(
    val id: Int,
    val name: String,
    val image: ArrayList<String>,
    val lastSeen: String,
    val type: EnumItemType = EnumItemType.CAROUSEL_LIST_DEFAULT,
    val hasMore: Boolean = false
)

enum class EnumItemType {
    CAROUSEL_LIST_DEFAULT,
    CAROUSEL_LIST_INDICATOR,
    CAROUSEL_LIST_INDICATOR_CUSTOM,
    CAROUSEL_LIST_GROUP,
    CAROUSEL_LIST_LOAD_MORE,
    CAROUSEL_GRID_DEFAULT,
    CAROUSEL_GRID_GROUP,
}
