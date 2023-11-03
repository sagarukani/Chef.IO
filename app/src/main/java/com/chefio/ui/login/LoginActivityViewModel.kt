package com.chefio.ui.login

import com.common.base.BaseViewModel
import com.common.data.database.daos.AppDao
import com.common.data.datastore.PreferenceDataStoreHelper
import com.common.data.network.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginActivityViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val dao: AppDao,
    private val dataStore: PreferenceDataStoreHelper,
) : BaseViewModel() {

}