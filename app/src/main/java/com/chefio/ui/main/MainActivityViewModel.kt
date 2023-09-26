package com.chefio.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.common.base.BaseViewModel
import com.common.base.SingleLiveEvent
import com.common.data.database.daos.AppDao
import com.common.data.database.entities.UserLocal
import com.common.data.datastore.PreferenceDataStoreHelper
import com.common.data.network.model.UserInfo
import com.common.data.network.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val dao: AppDao,
    private val dataStore: PreferenceDataStoreHelper,
) : BaseViewModel() {

    private val _userInfo = SingleLiveEvent<List<UserInfo>>()
    val userInfo: LiveData<List<UserInfo>> = _userInfo

    private val _userInfoError = MutableLiveData<Throwable>()
    val userInfoError: LiveData<Throwable> = _userInfoError

    var localUser: LiveData<List<UserLocal>>? = null

    init {
        localUser = dao.getMTUser()
    }

    fun callApi() {
        viewModelScope.launch {
            displayLoader()
            processDataEvent(apiRepository.getUsers(), onError = {
                _userInfoError.postValue(it)
            }) {
                _userInfo.postValue(it)
            }
        }
    }

    fun insertUser(userLocal: UserLocal) {
        viewModelScope.launch {
            val insertedUser = dao.insertMTUser(userLocal)
            Timber.e("insertedUser : ${insertedUser.size}")
            dataStore.setIsLogin(false)
            Timber.e("isLogin : ${dataStore.getIsLogin().first()}")
        }
    }

}