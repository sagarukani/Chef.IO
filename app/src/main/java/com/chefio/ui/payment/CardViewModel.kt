package com.chefio.ui.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.common.base.BaseViewModel
import com.common.base.SingleLiveEvent
import com.common.data.database.daos.AppDao
import com.common.data.datastore.PreferenceDataStoreHelper
import com.common.data.network.model.AllCardResponse
import com.common.data.network.model.MessageResponse
import com.common.data.network.model.request.AddCardReqModel
import com.common.data.network.model.request.EditCardReqModel
import com.common.data.network.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val dao: AppDao,
    private val dataStore: PreferenceDataStoreHelper,
) : BaseViewModel() {
    private val _cards = SingleLiveEvent<AllCardResponse>()
    val cards: LiveData<AllCardResponse> = _cards
    private val _loginError = MutableLiveData<Throwable>()
    val loginError: LiveData<Throwable> = _loginError

    private val _editCards = SingleLiveEvent<AllCardResponse>()
    val editCards: LiveData<AllCardResponse> = _editCards

    private val _allCards = SingleLiveEvent<AllCardResponse>()
    val allCards: LiveData<AllCardResponse> = _allCards

    private val _deleteCard = SingleLiveEvent<MessageResponse>()
    val deleteCard: LiveData<MessageResponse> = _deleteCard
    fun addCard(addCardReqModel: AddCardReqModel) {
        viewModelScope.launch {
            displayLoader()
            processDataEvent(apiRepository.addCard(addCardReqModel), onError = {
                _loginError.postValue(it)
            }) {
                _cards.postValue(it)
            }
        }
    }

    fun editCard(editCardReqModel: EditCardReqModel) {
        viewModelScope.launch {
            displayLoader()
            processDataEvent(apiRepository.editCard(editCardReqModel), onError = {
                _loginError.postValue(it)
            }) {
                _editCards.postValue(it)
            }
        }
    }

    fun deleteCard(editCardReqModel: EditCardReqModel) {
        viewModelScope.launch {
            displayLoader()
            processDataEvent(apiRepository.deleteCard(editCardReqModel), onError = {
                _loginError.postValue(it)
            }) {
                _deleteCard.postValue(it)
            }
        }
    }

    fun getAllCards() {
        viewModelScope.launch {
            displayLoader()
            processDataEvent(apiRepository.getCards(), onError = {
                _loginError.postValue(it)
            }) {
                _allCards.postValue(it)
            }
        }
    }
}