package com.sa.demo.exoxycarouseldemo.base.utils

import androidx.recyclerview.widget.RecyclerView

abstract class InfiniteRvScrollListener : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val linearLayoutManager: androidx.recyclerview.widget.LinearLayoutManager =
                recyclerView.layoutManager as androidx.recyclerview.widget.LinearLayoutManager
        if (linearLayoutManager.findLastCompletelyVisibleItemPosition() >= linearLayoutManager.itemCount - 2) {
            fetchNext()
        }
    }

    abstract fun fetchNext()
}