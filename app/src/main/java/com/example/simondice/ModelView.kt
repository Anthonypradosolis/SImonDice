package com.example.simondice

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
     * Función que empieza el juego.
     * LLama al metodo  agregarColorASecuencia() para agregar un color a la secuencia de colores.
     */
    fun empezarJugar() {
        estadoLiveData.value = Estados.GENERANDO
        secuenciaColores.clear()
        agregarColorASecuencia()
    }


    /**
     * Función que agrega un color a la secuencia de colores.
     * Este metodo esta dentro del metodo empezarJugar() y se llama cada vez que se inicia el juego.
     * Tambien llama al metodo mostrarSecuncia dentro de este metodo
     */
    fun agregarColorASecuencia() {
        val randomButtonIndex = (1..4).random()
        val nuevoColor = ColorButton.values().first { it.value == randomButtonIndex }
        secuenciaColores.add(nuevoColor)
        Datos.ronda.value = Datos.ronda.value?.plus(1) // Incrementa la ronda
        mostrarSecuencia()
    }


    /**
     * Función que muestra la secuencia de colores.
     * Muestra un color por pantalla y lo oculta.
     * Luego cambia el estado a ADIVINANDO.
     */
    private fun mostrarSecuencia() {
        viewModelScope.launch {
            for (color in secuenciaColores) {
                mensajeC.value = color.label
                delay(500)
                mensajeC.value = ""
                delay(500)
            }
            delay(500)
            estadoLiveData.value = Estados.ADIVINANDO
            indiceActual = 0
        }
    }


    /**
     * Función que compara el color seleccionado con el color de la secuencia de colores
     * Y cambia el estado del juego.
     * Finamente llama al metodo agregarColorASecuencia() para agregar un color a la secuencia de colores
     * Si el color seleccionado es igual al color de la secuencia de colores se incrementa el indiceActual
     * Si el indiceActual es igual al tamaño de la secuencia de colores se cambia el estado a GENERANDO
     * Si el color seleccionado es diferente al color de la secuencia de colores se llama al metodo endGame()
     */
    fun compararColorSeleccionado(colorSeleccionado: ColorButton): Boolean {
        if (colorSeleccionado == secuenciaColores[indiceActual]) {
            indiceActual++
            if (indiceActual == secuenciaColores.size) {
                estadoLiveData.value = Estados.GENERANDO
                viewModelScope.launch {
                    delay(1500)
                    agregarColorASecuencia()
                }
            }
            return true
        } else {
            endGame()
            return false
        }
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