package com.demon.easyjetpack.module.paging

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ConcatAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewbinding.ViewBinding
import com.demon.basemvvm.utils.inflateVB
import com.demon.easyjetpack.list.DataViewHolder

/**
 * @author DeMon
 * Created on 2020/9/28.
 * E-mail 757454343@qq.com
 * Desc:
 */
abstract class PagingAdapter<VB : ViewBinding, T : Any> constructor(private val swipe: SwipeRefreshLayout? = null) :
    PagingDataAdapter<T, DataViewHolder>(DiffComparator<T>()) {

    init {
        addLoadStateListener {
            swipe?.isRefreshing = it.refresh is LoadState.Loading
        }
        swipe?.setOnRefreshListener {
            refresh()
        }
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = getItem(position)
        item?.run {
            onBind(holder, position, this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(inflateVB<VB>(parent))
    }


    abstract fun onBind(holder: DataViewHolder, position: Int, data: T)


    fun withFooter(): ConcatAdapter {
        return this.withLoadStateFooter(PagingLoadAdapter(object : RetryListener {
            override fun onRetry() {
                retry()
            }
        }))
    }

}