package com.chefio.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.common.base.BaseViewModel
import com.common.base.SingleLiveEvent
import com.common.data.database.daos.AppDao
import com.common.data.datastore.PreferenceDataStoreHelper
import com.common.data.network.model.MessageResponse
import com.common.data.network.model.ScheduleResponse
import com.common.data.network.model.UserProfile
import com.common.data.network.model.request.ChefProfileReqModel
import com.common.data.network.model.request.ScheduleReqModel
import com.common.data.network.model.request.SignupReqModel
import com.common.data.network.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
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

    private val _editProfile = SingleLiveEvent<Any>()
    val editProfile: LiveData<Any> = _editProfile

    private val _userProfile = SingleLiveEvent<UserProfile>()
    val userProfile: LiveData<UserProfile> = _userProfile

    private val _schedule = SingleLiveEvent<ScheduleResponse>()
    val schedule: LiveData<ScheduleResponse> = _schedule

    fun schedule(reqLogin: ScheduleReqModel) {
        viewModelScope.launch {
            displayLoader()
            processDataEvent(apiRepository.scheduleCreate(reqLogin), onError = {
                _loginError.postValue(it)
            }) {
                _schedule.postValue(it)
            }
        }
    }

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

    fun address(multipartBody: MultipartBody) {
        viewModelScope.launch {
            displayLoader()
            processDataEvent(apiRepository.address(multipartBody), onError = {
                _loginError.postValue(it)
            }) {
                _address.postValue(it)
            }
        }
    }

    fun editProfile(reqLogin: ArrayList<ChefProfileReqModel>) {
        viewModelScope.launch {
            displayLoader()
            processDataEvent(apiRepository.editProfile(reqLogin), onError = {
                _editProfile.postValue(it)
            }) {
                _editProfile.postValue(it)
            }
        }
    }

    fun getProfile() {
        viewModelScope.launch {
            displayLoader()
            processDataEvent(apiRepository.getUserProfile(), onError = {
                _loginError.postValue(it)
                it.printStackTrace()
            }) {
                _userProfile.postValue(it)
            }
        }
    }
}