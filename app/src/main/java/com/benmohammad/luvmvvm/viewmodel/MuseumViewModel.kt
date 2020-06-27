package com.benmohammad.luvmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.benmohammad.luvmvvm.data.OperationCallback
import com.benmohammad.luvmvvm.model.Museum
import com.benmohammad.luvmvvm.model.MuseumDataSource

class MuseumViewModel(private val repository: MuseumDataSource): ViewModel() {

    private val _museums = MutableLiveData<List<Museum>>().apply { value = emptyList() }
    val museums: LiveData<List<Museum>> = _museums

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    fun loadMuseums(){
        _isViewLoading.postValue(true)
        repository.retrieveMuseums(object : OperationCallback<Museum>{
            override fun onSuccess(data: List<Museum>?) {
                _isViewLoading.postValue(false)

                if(data != null) {
                    if(data.isEmpty()) {
                        _isEmptyList.postValue(true)
                    } else {
                        _museums.value = data
                    }
                }
            }

            override fun onError(error: String?) {
                _isViewLoading.postValue(false)
                _onMessageError.postValue(error)
            }
        })
    }
}