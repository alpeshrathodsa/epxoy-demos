package com.sa.demo.exoxycarouseldemo.base.utils

import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView

fun NestedScrollView.setInfiniteScroll(func: NestedScrollView.() -> Unit, recyclerView: RecyclerView) {
    this.let {
        this.setOnScrollChangeListener(object : InfiniteNestedScrollListener(recyclerView) {
            override fun fetchNext() {
                func.invoke(this@setInfiniteScroll)
            }
        })
    }
}
