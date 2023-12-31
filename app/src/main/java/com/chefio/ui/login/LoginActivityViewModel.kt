package com.chefio.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.common.base.BaseViewModel
import com.common.base.SingleLiveEvent
import com.common.data.database.daos.AppDao
import com.common.data.datastore.PreferenceDataStoreHelper
import com.common.data.network.model.LoginResponse
import com.common.data.network.model.request.LoginRequestModel
import com.common.data.network.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginActivityViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val dao: AppDao,
    private val dataStore: PreferenceDataStoreHelper,
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
}