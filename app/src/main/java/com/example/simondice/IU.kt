package com.example.simondice

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

/**
 * Función que se encarga de mostrar la interfaz de usuario
 * @param viewModel Modelo de la vista
 */
@Composable
fun IU(viewModel: ModelView) {
    val TAG_LOG = "miDebug"
    val estado by viewModel.estadoLiveData.observeAsState(Estados.INICIO)
    val ronda by Datos.ronda.observeAsState(0) // Observa el LiveData de ronda

}
