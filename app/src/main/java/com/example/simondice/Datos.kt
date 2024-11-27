package com.example.simondice

import androidx.lifecycle.MutableLiveData

object Datos {
    // Se inicializa la variable numero en 0
    var numero: Int = 0
    // Se inicializa la variable ronda en 0
    var ronda : MutableLiveData<Int> = MutableLiveData(0)
}