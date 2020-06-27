package com.benmohammad.luvmvvm.model

import android.util.Log
import com.benmohammad.luvmvvm.data.ApiClient
import com.benmohammad.luvmvvm.data.MuseumResponse
import com.benmohammad.luvmvvm.data.OperationCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "CONSOLE"

class MuseumRepository: MuseumDataSource {

    private var call: Call<MuseumResponse>? = null

    override fun retrieveMuseums(callback: OperationCallback<Museum>) {
        call = ApiClient.build()?.museums()
        call?.enqueue(object : Callback<MuseumResponse>{
            override fun onFailure(call: Call<MuseumResponse>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(
                call: Call<MuseumResponse>,
                response: Response<MuseumResponse>
            ) {
                response.body()?.let {
                    if(response.isSuccessful && (it.isSuccess())) {
                        Log.v(TAG, "data ${it.data}")
                        callback.onSuccess(it.data)
                    } else {
                        callback.onError((it.msg))
                    }
                }
            }
        })
    }

    override fun cancel() {
        call?.let {
            it.cancel()
        }
    }
}