package com.uvg.labfinal.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNav(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "crypto_asset_list",
        modifier = modifier
    ) {
        // Pantalla de la lista de criptoactivos
        cryptoAssetListScreen()
        // Pantalla del perfil de criptoactivo
        cryptoAssetProfileScreen()
    }
}

fun cryptoAssetProfileScreen() {
    TODO("Not yet implemented")
}
