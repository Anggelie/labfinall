package com.uvg.labfinal.ktor.domain.network

import com.uvg.labfinal.ktor.data.network.dto.CryptoAssetEntryDto
import com.uvg.labfinal.ktor.data.network.dto.CryptoAssetListDto
import com.uvg.labfinal.ktor.domain.network.util.NetworkError
import com.uvg.labfinal.ktor.domain.network.util.Result

interface CryptixApi {
    suspend fun getAllCryptoAssets(): Result<CryptoAssetListDto, NetworkError>
    suspend fun getCryptoAsset(id: String): Result<CryptoAssetEntryDto, NetworkError>
}