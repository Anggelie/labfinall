package com.uvg.labfinal.architecture

import androidx.lifecycle.ViewModel
import com.uvg.labfinal.CryptoAssetListScreenState
import com.uvg.labfinal.navigation.CryptoAsset
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CryptoAssetListViewModel : ViewModel() {
    private val _state = MutableStateFlow(
        CryptoAssetListScreenState<CryptoAsset>(
            isLoading = false,
            cryptoAssets = listOf(
                CryptoAsset("1", "Bitcoin", "25000", "5"),
                CryptoAsset("2", "Ethereum", "1500", "-3")
            )
        )
    )
    val state: StateFlow<CryptoAssetListScreenState<CryptoAsset>> = _state
}