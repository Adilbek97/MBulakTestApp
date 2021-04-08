package com.example.mbulaktestapp.repository

import com.example.mbulaktestapp.model.CheckCodeResult
import com.example.mbulaktestapp.model.CheckPhoneResult
import com.example.mbulaktestapp.model.MainResponse
import com.example.mbulaktestapp.model.CountryResult
import com.example.mbulaktestapp.network.MainApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AuthRepository(private val mainApi: MainApi) {
    private val subscription = CompositeDisposable()

    fun getCountries(onSuccess: (MainResponse<List<CountryResult>>) ->Unit, onFailure: (Throwable) ->Unit){
        subscription.add(mainApi.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it)
            },{
                onFailure(it)
            })
        )
    }

    fun checkPhoneResult(phoneNumber:String,onSuccess: (MainResponse<CheckPhoneResult>) ->Unit, onFailure: (Throwable) ->Unit){
        subscription.add(mainApi.checkPhone(phoneNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it)
            },{
                onFailure(it)
            })
        )
    }

    fun checkCode(id:Int, code:Int, onSuccess: (MainResponse<CheckCodeResult>) ->Unit, onFailure: (Throwable) ->Unit){
        subscription.add(mainApi.checkCode(id,code)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it)
            },{
                onFailure(it)
            })
        )
    }

    fun clear(){
        subscription.dispose()
    }
}