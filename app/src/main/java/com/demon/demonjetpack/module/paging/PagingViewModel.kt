package com.demon.demonjetpack.module.paging

import com.demon.demonjetpack.base.ext.getDataOrThrow
import com.demon.demonjetpack.base.http.HttpViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author DeMon
 * Created on 2020/9/28.
 * E-mail 757454343@qq.com
 * Desc:
 */
@HiltViewModel
class PagingViewModel @Inject constructor() : HttpViewModel() {

    fun getAuthorList(author: String) =
        toPage {
            repository.articleAuthorList(author, it).getDataOrThrow()
        }

}