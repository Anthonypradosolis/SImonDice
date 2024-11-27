package com.example.simondice

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData

object Datos {
    // Se inicializa la variable numero en 0
    var numero: Int = 0
    // Se inicializa la variable ronda en 0
    var ronda : MutableLiveData<Int> = MutableLiveData(0)
}


/**
 * Enumeración de los colores de los botones
 * @param color Color del botón
 * @param label Etiqueta del botón
 * @param value Valor del botón
 * @constructor Crea un objeto de la clase ColorButton
 */
enum class ColorButton(val color: Color, val label: String, val value: Int) {
    VERDE(Color.Green, "Verde", 1),
    ROJO(Color.Red, "Rojo", 2),
    AMARILLO(Color.Yellow, "Amarillo", 3),
    AZUL(Color.Blue, "Azul", 4)
}


/**
 * Clase ButtonData que se encarga de almacenar los datos de los botones
 * @param colorButton Color del botón
 */
data class ButtonData(val colorButton: ColorButton )

