package com.example.mbulaktestapp.ui.phone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mbulaktestapp.model.CheckCodeResult
import com.example.mbulaktestapp.model.CheckPhoneResult
import com.example.mbulaktestapp.model.CountryResult
import com.example.mbulaktestapp.repository.AuthRepository
import com.example.mbulaktestapp.ui.ResultState

class PhoneNumberViewModel(private val authRepository: AuthRepository): ViewModel() {

    private val loadingState: MutableLiveData<ResultState<List<CountryResult>>> = MutableLiveData()
    private val checkPhoneState: MutableLiveData<ResultState<CheckPhoneResult>> = MutableLiveData()
    private val checkCodeState: MutableLiveData<ResultState<CheckCodeResult>> = MutableLiveData()

    fun getLoadingState():LiveData<ResultState<List<CountryResult>>> = loadingState
    fun checkPhoneState():LiveData<ResultState<CheckPhoneResult>> = checkPhoneState
    fun checkCodeState():LiveData<ResultState<CheckCodeResult>> = checkCodeState

    fun getCountries(){
        authRepository.getCountries({
            if (it.code == 200 && it.error == null) {
                loadingState.value = ResultState.Success(it.result ?: emptyList())
            } else {
                it.error?.let {
                    loadingState.value = ResultState.ErrorMessageFromServer(it.message)
                }
            }
        }){
                loadingState.value = ResultState.Error(it.message ?: "")
        }
    }

    fun checkPhoneResult(phoneNumber:String){
        checkPhoneState.value = ResultState.InProgress
        authRepository.checkPhoneResult(phoneNumber,{
            if (it.code == 200 && it.error == null) {
                checkPhoneState.value = ResultState.Success(it.result ?: CheckPhoneResult() )
            } else {
                it.error?.let {
                    checkPhoneState.value = ResultState.ErrorMessageFromServer(it.message)
                    when(it.code){
                        400 -> {
                            checkPhoneState.value = ResultState.ErrorMessageFromServer("Неверный запрос, неверно заданы параметры")
                        }
                        409 -> {
                            checkCodeState.value = ResultState.ErrorMessageFromServer("Номер уже занят\\зарегистрирован ")
                        }
                        500 -> {
                            checkCodeState.value = ResultState.ErrorMessageFromServer("Ошибка со стороны сервера")
                        }
                    }
                }
            }
        }){
            checkPhoneState.value = ResultState.Error(it.message ?: "")
        }
    }
    fun checkCodeResult(id: Int, code:Int){
        checkCodeState.value = ResultState.InProgress
        authRepository.checkCode(id, code ,{
            if (it.code == 200 && it.error == null) {
                checkCodeState.value = ResultState.Success(it.result ?: CheckCodeResult() )
            } else {
                it.error?.let {
                    when(it.code){
                        400 -> {
                            checkCodeState.value = ResultState.ErrorMessageFromServer("Неверный запрос, код введен не верно")
                        }
                        500 -> {
                            checkCodeState.value = ResultState.ErrorMessageFromServer("Ошибка со стороны сервера")
                        }
                    }

                }
            }
        }){
            checkCodeState.value = ResultState.Error(it.message ?: "")
        }
    }

    override fun onCleared() {
        super.onCleared()
        authRepository.clear()
    }
}