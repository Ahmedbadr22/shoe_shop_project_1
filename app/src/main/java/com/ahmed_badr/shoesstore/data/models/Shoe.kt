package com.ahmed_badr.shoesstore.data.models

import android.os.Parcelable
import androidx.databinding.BaseObservable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Shoe(
    var name: String = "Enter Shoe Name",
    var size: Double = 0.0,
    var company: String = "Enter Company Name",
    var description: String = "Enter Description",
    val images: List<Int> = mutableListOf()
) : Parcelable
