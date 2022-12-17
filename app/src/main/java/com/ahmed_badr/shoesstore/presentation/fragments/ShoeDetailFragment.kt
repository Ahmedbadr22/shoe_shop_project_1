package com.ahmed_badr.shoesstore.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ahmed_badr.shoesstore.R
import com.ahmed_badr.shoesstore.data.models.Shoe
import com.ahmed_badr.shoesstore.databinding.FragmentShoeDetailBinding
import com.ahmed_badr.shoesstore.presentation.view_model.SharedViewModel

class ShoeDetailFragment : Fragment() {
    private lateinit var binding: FragmentShoeDetailBinding
    private val sharedViewModel : SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoeDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel = sharedViewModel
            shoeDetailFragment = this@ShoeDetailFragment
        }
    }

    fun saveShoe() {
        binding.apply {
            val name : String = editTextShoeItemName.text.toString()
            val size : String = editTextShoeItemSize.text.toString()
            val company : String = editTextShoeItemCompany.text.toString()
            val description : String = editTextShoeItemDescription.text.toString()

            val shoe = Shoe(name, size.toDouble(), company, description, listOf(R.drawable.shoe_1))
            sharedViewModel.setShoe(shoe)
        }
    }

    fun cancel() {
        Log.d("Neew", "Cancel")
        val navAction = ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment()
        findNavController().navigate(navAction)
    }

}