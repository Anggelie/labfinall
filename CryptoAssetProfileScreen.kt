package com.uvg.labfinal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uvg.labfinal.architecture.CryptoAssetProfileScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoAssetProfileScreen(
    state: CryptoAssetProfileScreenState,
    onBack: () -> Unit,
    cryptoAssetId: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "CryptoAsset Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors()
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
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
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                state.cryptoAsset != null -> {
                    val asset = state.cryptoAsset
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = asset.name,
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Text(
                            text = "Symbol: ${asset.symbol}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Price: ${asset.priceUsd} USD",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "24h Change: ${asset.changePercent24Hr}%",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = if (asset.changePercent24Hr.toFloatOrNull() ?: 0f < 0)
                                    MaterialTheme.colorScheme.error
                                else
                                    MaterialTheme.colorScheme.primary
                            )
                        )
                        Text(
                            text = "Market Cap: ${asset.marketCapUsd} USD",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Supply: ${asset.supply} units",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Max Supply: ${asset.maxSupply ?: "N/A"} units",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}
