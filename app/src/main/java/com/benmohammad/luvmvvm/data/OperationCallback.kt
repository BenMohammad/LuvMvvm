package com.benmohammad.luvmvvm.data

interface OperationCallback<T> {
    fun onSuccess(data: List<T>?)
    fun onError(error: String?)
}