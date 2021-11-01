package com.mousavi.comosecryptocurrencyapp.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mousavi.comosecryptocurrencyapp.common.Resource
import com.mousavi.comosecryptocurrencyapp.domain.model.Coin
import com.mousavi.comosecryptocurrencyapp.domain.usecases.get_coins.GetCoinsUseCase
import com.mousavi.comosecryptocurrencyapp.presentation.util.CoinOrder
import com.mousavi.comosecryptocurrencyapp.presentation.util.UserEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
) : ViewModel() {

    private var _stateList = mutableStateOf<List<Coin>>(emptyList())
    val state: State<List<Coin>> = _stateList

    private var _userEventState = mutableStateOf(UserEvent())
    val userEventState: State<UserEvent> = _userEventState

    private var _loading = MutableSharedFlow<Boolean>()
    val loading: SharedFlow<Boolean> = _loading.asSharedFlow()

    private var _error = MutableSharedFlow<String>()
    val error: SharedFlow<String> = _error.asSharedFlow()

    init {
        getCoins()
    }

    private fun getCoins(coinOrder: CoinOrder = userEventState.value.order) {
        getCoinsUseCase(coinOrder).onEach {
            when (it) {
                is Resource.Error -> {
                    _loading.emit(false)
                    _error.emit(it.error!!)
                }
                is Resource.Loading -> {
                    _loading.emit(true)
                }
                is Resource.Success -> {
                    delay(1000)
                    _loading.emit(false)
                    _stateList.value = it.data!!
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: UserEvent) {
        val old = userEventState.value.isOrderSectionVisible
        val new = event.isOrderSectionVisible
        _userEventState.value = event

        if (old == new) {//user request order
            getCoins(event.order)
        }
    }
}