package com.sa.demo.exoxycarouseldemo.base.base

import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/6/20
 */
abstract class CoroutinePageKeyedDataSource <K, V>(private val coroutineContext: CoroutineContext) :
        PageKeyedDataSource<K, V>() {

    private val job = Job()
    protected val coroutineScope = CoroutineScope(coroutineContext + job)

    abstract suspend fun loadInitial(params: LoadInitialParams<K>): InitialResult<K, V>
    abstract suspend fun loadAfter(params: LoadParams<K>): LoadResult<K, V>
    abstract suspend fun loadBefore(params: LoadParams<K>): LoadResult<K, V>

    final override fun loadInitial(
            params: LoadInitialParams<K>,
            callback: LoadInitialCallback<K, V>
    ) {
        coroutineScope.launch {
            loadInitial(params).run {
                when(this) {
                    is InitialResult.Success -> {
                        if (position != null)
                            callback.onResult(data, position!!, totalCount!!, previousPageKey, nextPageKey)
                        else
                            callback.onResult(data, previousPageKey, nextPageKey)
                    }
                }
            }
        }
    }

    final override fun loadAfter(params: LoadParams<K>, callback: LoadCallback<K, V>) {
        coroutineScope.launch {
            loadAfter(params).run {
                when(this) {
                    is LoadResult.Success ->
                        callback.onResult(data, adjacentPageKey)
                }
            }
        }
    }

    final override fun loadBefore(params: LoadParams<K>, callback: LoadCallback<K, V>) {
        coroutineScope.launch {
            loadBefore(params).run {
                when(this) {
                    is LoadResult.Success ->
                        callback.onResult(data, adjacentPageKey)
                }
            }
        }
    }

    sealed class InitialResult<out K, out V> {
        data class Success<K, V> private constructor(
                val data: MutableList<V>,
                val position: Int?,
                val totalCount: Int?,
                val previousPageKey: K?,
                val nextPageKey: K?
        ) : InitialResult<K, V>() {
            constructor(
                    data: MutableList<V>,
                    position: Int,
                    totalCount: Int,
                    previousPageKey: K?,
                    nextPageKey: K?
            ) : this(data, position as Int?, totalCount as Int?, previousPageKey, nextPageKey)

            constructor(
                    data: MutableList<V>,
                    previousPageKey: K?,
                    nextPageKey: K?
            ) : this(data, null, null, previousPageKey, nextPageKey)
        }
        object None : InitialResult<Nothing, Nothing>()
    }

    sealed class LoadResult<out K, out V> {
        data class Success<K, V>(val data: MutableList<V>, val adjacentPageKey: K?) : LoadResult<K, V>()
        object None : LoadResult<Nothing, Nothing>()
    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }
}