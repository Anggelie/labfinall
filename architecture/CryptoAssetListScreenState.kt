package com.uvg.labfinal.architecture

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uvg.labfinal.CryptoAssetListScreenState
import com.uvg.labfinal.util.CryptoAsset

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoAssetListScreen(
    state: CryptoAssetListScreenState<CryptoAsset>,
    modifier: Modifier = Modifier,
    onCryptoClick: (String) -> Unit = {}
) {
    Column(modifier = modifier.fillMaxSize()) {
        // Barra superior
        TopAppBar(
            title = { Text(text = "CryptoAssets") },
            colors = TopAppBarDefaults.topAppBarColors()
        )

        // Contenido principal basado en el estado
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            state.error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Error: ${state.error}",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            state.cryptoAssets.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No crypto assets available")
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize()
                ) {
                    items(state.cryptoAssets) { cryptoAsset ->
                        CryptoAssetRow(
                            cryptoAsset = cryptoAsset,
                            onClickCrypto = onCryptoClick
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun CryptoAssetRow(
    cryptoAsset: CryptoAsset,
    onClickCrypto: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onClickCrypto(cryptoAsset.id) }
    ) {
        Column {
            Text(
                text = cryptoAsset.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Price: ${cryptoAsset.priceUsd} USD",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Change (24h): ${cryptoAsset.changePercent24Hr}%",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = if ((cryptoAsset.changePercent24Hr.toFloatOrNull() ?: 0f) < 0)
                        MaterialTheme.colorScheme.error
                    else
                        MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}
