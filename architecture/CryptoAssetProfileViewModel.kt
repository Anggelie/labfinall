package com.uvg.labfinal.architecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.labfinal.ktor.data.network.KtorCryptixApi
import com.uvg.labfinal.ktor.di.KtorDependencies
import com.uvg.labfinal.ktor.domain.network.CryptixRepository
import com.uvg.labfinal.ktor.domain.network.util.Result
import com.uvg.labfinal.room.data.localdb.CryptixRepositoryImplementation
import com.uvg.labfinal.room.data.localdb.di.Dependencies
import com.uvg.labfinal.util.CryptoAsset
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CryptoAssetProfileScreenState(
    val cryptoAsset: CryptoAsset? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class CryptoAssetProfileViewModel(
    private val cryptixRepository: CryptixRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CryptoAssetProfileScreenState())
    val state = _state.asStateFlow()

    fun getCryptoAsset(id: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            delay(2000)
            when (val result = cryptixRepository.getCryptoAssetById(id)) {
                is Result.Success -> _state.update { it.copy(cryptoAsset = result.data, isLoading = false) }
                is Result.Error -> {
                    _state.update { it.copy(error = result.error.toString(), isLoading = false) }
                }
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val api = KtorCryptixApi(KtorDependencies.provideHttpClient())
                val db = Dependencies.provideDatabase(application)
                CryptoAssetProfileViewModel(
                    cryptixRepository = CryptixRepositoryImplementation(cryptoAssetDao = db.cryptoAssetDao(), cryptixApi = api)
                )
            }
        }
    }
}