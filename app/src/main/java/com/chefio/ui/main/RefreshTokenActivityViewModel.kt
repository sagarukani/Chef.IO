package com.chefio.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.common.base.BaseViewModel
import com.common.base.SingleLiveEvent
import com.common.data.network.model.LoginResponse
import com.common.data.network.model.request.LoginRequestModel
import com.common.data.network.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RefreshTokenActivityViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : BaseViewModel() {
    private val _login = SingleLiveEvent<LoginResponse>()
    val login: LiveData<LoginResponse> = _login
    private val _loginError = MutableLiveData<Throwable>()
    val loginError: LiveData<Throwable> = _loginError

    fun login(reqLogin: LoginRequestModel) {
        viewModelScope.launch {
            displayLoader()
            processDataEvent(apiRepository.login(reqLogin), onError = {
                _loginError.postValue(it)
            }) {
                _login.postValue(it)
            }
        }
    }

    fun dummy() {
        viewModelScope.launch {
            displayLoader()
            processDataEvent(apiRepository.dummy(), onError = {
                it.printStackTrace()
            }) {
                Timber.d(it.toString())
            }
        }
    }
}