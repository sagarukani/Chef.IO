package com.chefio.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.common.base.BaseViewModel
import com.common.base.SingleLiveEvent
import com.common.data.database.daos.AppDao
import com.common.data.datastore.PreferenceDataStoreHelper
import com.common.data.network.model.MessageResponse
import com.common.data.network.model.request.AddressReqModel
import com.common.data.network.model.request.EditProfileReqModelItem
import com.common.data.network.model.request.SignupReqModel
import com.common.data.network.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResgisterViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val dao: AppDao,
    private val dataStore: PreferenceDataStoreHelper,
) : BaseViewModel() {
    private val _signup = SingleLiveEvent<MessageResponse>()
    val signup: LiveData<MessageResponse> = _signup
    private val _loginError = MutableLiveData<Throwable>()
    val loginError: LiveData<Throwable> = _loginError

    private val _address = SingleLiveEvent<MessageResponse>()
    val address: LiveData<MessageResponse> = _address

    private val _editProfile = SingleLiveEvent<MessageResponse>()
    val editProfile: LiveData<MessageResponse> = _editProfile
    fun signup(reqLogin: SignupReqModel) {
        viewModelScope.launch {
            displayLoader()
            processDataEvent(apiRepository.signup(reqLogin), onError = {
                _loginError.postValue(it)
            }) {
                _signup.postValue(it)
            }
        }
    }

    fun address(reqLogin: AddressReqModel) {
        viewModelScope.launch {
            displayLoader()
            processDataEvent(apiRepository.address(reqLogin), onError = {
                _loginError.postValue(it)
            }) {
                _address.postValue(it)
            }
        }
    }

    fun editProfile(reqLogin: ArrayList<EditProfileReqModelItem>) {
        viewModelScope.launch {
            displayLoader()
            processDataEvent(apiRepository.editProfile(reqLogin), onError = {
                _loginError.postValue(it)
            }) {
                _editProfile.postValue(it)
            }
        }
    }
}