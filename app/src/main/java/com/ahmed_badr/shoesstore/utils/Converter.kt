package com.ahmed_badr.shoesstore.utils

import androidx.databinding.InverseMethod

object Converter {
    @InverseMethod("stringToDouble")
    @JvmStatic
    fun doubleToString(value: Double): String = value.toString()

    @JvmStatic
    fun stringToDouble(value: String): Double = if (value.isNotEmpty()) value.toDouble() else 0.0

}