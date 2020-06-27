package com.benmohammad.luvmvvm.model

import com.benmohammad.luvmvvm.data.OperationCallback

interface MuseumDataSource {

    fun retrieveMuseums(callback: OperationCallback<Museum>)
    fun cancel()
}