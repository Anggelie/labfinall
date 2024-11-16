package com.uvg.labfinal.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.uvg.labfinal.architecture.CryptoAssetListViewModel
import com.uvg.labfinal.architecture.CryptoAssetProfileRoute

fun NavGraphBuilder.cryptoAssetListScreen() {
    composable(route = "crypto_asset_list") {
        // Obtiene el ViewModel utilizando Hilt
        val viewModel: CryptoAssetListViewModel = hiltViewModel()
        viewModel.state.collectAsStateWithLifecycle().value

        cryptoAssetListScreen()
    }
}


fun NavGraphBuilder.cryptoAssetProfileScreen(
    onBack: () -> Unit
) {
    composable(route = "crypto_asset_profile/{id}") { backStackEntry ->
        // Recupera el ID del criptoactivo desde los argumentos
        val id = backStackEntry.arguments?.getString("id") ?: return@composable

        CryptoAssetProfileRoute(
            cryptoAssetId = id,
            onBack = onBack
        )
    }
}
