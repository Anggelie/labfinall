package com.uvg.labfinal

data class CryptoAssetListScreenState<T>(
    val isLoading: Boolean = false,
    val error: String? = null,
    val cryptoAssets: List<T> = emptyList()
)