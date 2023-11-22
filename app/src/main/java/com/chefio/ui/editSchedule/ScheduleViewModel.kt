package com.chefio.ui.editSchedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.common.base.BaseViewModel
import com.common.base.SingleLiveEvent
import com.common.data.database.daos.AppDao
import com.common.data.datastore.PreferenceDataStoreHelper
import com.common.data.network.model.LoginResponse
import com.common.data.network.model.MessageResponse
import com.common.data.network.model.request.LoginRequestModel
import com.common.data.network.repository.ApiRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ScheduleViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val dao: AppDao,
    private val dataStore: PreferenceDataStoreHelper,
) : BaseViewModel() {
    private val _schedule = SingleLiveEvent<MessageResponse>()
    val schedule: LiveData<LoginResponse> = _schedule

    private val _loginError = MutableLiveData<Throwable>()
    val loginError: LiveData<Throwable> = _loginError

    fun schedule(reqLogin: LoginRequestModel) {
        viewModelScope.launch {
            displayLoader()
            processDataEvent(apiRepository.scheduleCreate(reqLogin), onError = {
                _loginError.postValue(it)
            }) {
                _schedule.postValue(it)
            }
        }
    }
}