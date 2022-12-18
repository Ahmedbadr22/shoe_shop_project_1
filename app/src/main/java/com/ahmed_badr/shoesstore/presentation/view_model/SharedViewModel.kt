package com.ahmed_badr.shoesstore.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmed_badr.shoesstore.R
import com.ahmed_badr.shoesstore.data.models.Shoe

class SharedViewModel : ViewModel() {
    private val _shoeListItem : MutableLiveData<MutableList<Shoe>>
    val shoeListItem : LiveData<MutableList<Shoe>>
        get() = _shoeListItem

    val shoe = Shoe(images = listOf(R.drawable.shoe_1))

    init {
        val list = mutableListOf(
            Shoe("High Hop22", 45.0, "Nike", "Very Good Shoes", listOf(R.drawable.shoe_1)),
            Shoe("Wave 55", 42.0, "Adidas", "Very Good Shoes", listOf(R.drawable.shoe_1)),
        )
        _shoeListItem = MutableLiveData(list)
    }

    fun onSave() {
        _shoeListItem.value?.add(shoe)
    }
}