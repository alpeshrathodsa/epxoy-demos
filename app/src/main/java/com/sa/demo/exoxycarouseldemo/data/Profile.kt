package com.sa.demo.exoxycarouseldemo.data

data class Profile(
    val id: Int,
    val name: String,
    val image: ArrayList<String>,
    val lastSeen: String,
    val type: ITEM_TYPE = ITEM_TYPE.CAROUSEL_LIST_BUILDER,
    val hasMore: Boolean = false
)

enum class ITEM_TYPE {
    CAROUSEL_LIST_BUILDER,
    CAROUSEL_LIST_MANUAL,
    CAROUSEL_LIST_INDICATOR,
    CAROUSEL_LIST_GROUP_LOAD_MORE,
    CAROUSEL_GRID_GROUP,
    CAROUSEL_GRID_MANUAL,
    CAROUSEL_PAGED_LIST
}
