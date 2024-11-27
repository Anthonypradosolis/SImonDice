package com.example.simondice

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Clase ModelView que se encarga de manejar la lógica de la aplicación.
 */
class ModelView() : ViewModel() {


    // Etiqueta para el log
    private val TAG_LOG = "miDebug"


    //Variable(estadoLiveData) que almacena el estado del juego como observable
    val estadoLiveData: MutableLiveData<Estados> = MutableLiveData(Estados.INICIO)


    //Lista de botones(buttons) con mutableList para agregar y eliminar elementos
    var buttons = mutableStateOf(listOf<ButtonData>())


    //Variable(mensajeC) que almacena el mensaje que se muestra en la pantalla
    var mensajeC = mutableStateOf("")


    //Lista de colores(secuenciaColores) con mutableList para agregar y eliminar elementos
    private val secuenciaColores = mutableListOf<ColorButton>()


    //Variable(indiceActual) que almacena el índice actual de la secuencia de colores
    private var indiceActual = 0

    /**
     * Inicialización de la clase ModelView.
     * Se inicializa el estado del juego y se obtienen los botones.
     */
    init {
        Log.d(TAG_LOG,"Estado: ${estadoLiveData.value}")
        buttons.value = getButtons()
    }








    /**
     * Función que finaliza el juego.
     * Cambia el estado a PERDIDO y muestra un mensaje en la pantalla.
     */
    fun endGame() {
        estadoLiveData.value = Estados.PERDIDO
        mensajeC.value = "Perdiste"
        Datos.ronda.value = 0
        Log.d(TAG_LOG, "Estado: ${estadoLiveData.value}")
    }

    /**
     * Función que retorna una lista de botones.
     */
    fun getButtons(): List<ButtonData> {
        return listOf(
            ButtonData(ColorButton.VERDE),
            ButtonData(ColorButton.ROJO),
            ButtonData(ColorButton.AMARILLO),
            ButtonData(ColorButton.AZUL))
    }
}