package com.tradeloop.base.utils

import androidx.recyclerview.widget.RecyclerView
import com.sa.demo.exoxycarouseldemo.base.utils.InfiniteRvScrollListener


fun RecyclerView.setInfiniteScroll(func: RecyclerView.() -> Unit) {
    this.adapter?.let {
        this.addOnScrollListener(object : InfiniteRvScrollListener() {
            override fun fetchNext() {
                func.invoke(this@setInfiniteScroll)
            }
        })
    }
}

