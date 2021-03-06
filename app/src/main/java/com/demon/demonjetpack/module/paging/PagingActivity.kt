package com.demon.demonjetpack.module.paging

import androidx.lifecycle.lifecycleScope

import com.alibaba.android.arouter.facade.annotation.Route
import com.demon.basemvvm.mvvm.MvvmActivity
import com.demon.demonjetpack.base.data.RouterConst
import com.demon.demonjetpack.bean.ArticleBean
import com.demon.demonjetpack.databinding.ActivityPagingBinding
import com.demon.demonjetpack.databinding.ListPagingBinding
import com.demon.demonjetpack.list.DataViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@Route(path = RouterConst.ACT_PAGING)
@AndroidEntryPoint
class PagingActivity : MvvmActivity<ActivityPagingBinding, PagingViewModel>() {

    override fun init() {
        val pagingAdapter = object : PagingAdapter<ListPagingBinding, ArticleBean>(binding.swipe) {
            override fun onBind(holder: DataViewHolder<ListPagingBinding>, position: Int, data: ArticleBean) {
                holder.binding.run {
                    tvText.text = data.title
                }
            }
        }
        binding.rv.adapter = pagingAdapter.withFooter()

        mViewModel.getAuthorList("鸿洋").observe(this@PagingActivity) {
            lifecycleScope.launch {
                pagingAdapter.submitData(it)
            }
        }


    }
}