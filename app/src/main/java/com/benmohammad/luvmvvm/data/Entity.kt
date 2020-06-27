package com.benmohammad.luvmvvm.data

import com.benmohammad.luvmvvm.model.Museum

data class MuseumResponse(val status: Int? , val msg: String?, val data: List<Museum>?) {
    fun isSuccess(): Boolean = (status==200)
}