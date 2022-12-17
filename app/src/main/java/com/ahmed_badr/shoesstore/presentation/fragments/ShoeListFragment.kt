package com.ahmed_badr.shoesstore.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.TEXT_ALIGNMENT_CENTER
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ahmed_badr.shoesstore.R
import com.ahmed_badr.shoesstore.data.models.Shoe
import com.ahmed_badr.shoesstore.databinding.FragmentShoeListBinding
import com.ahmed_badr.shoesstore.presentation.view_model.SharedViewModel

class ShoeListFragment : Fragment() {
    private lateinit var binding: FragmentShoeListBinding

    private val sharedViewModel : SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoeListBinding.inflate(layoutInflater, container, false)
        binding.shoeListFragment = this@ShoeListFragment
        return binding.root
    }

    @SuppressLint("ResourceType", "UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.shoe_list_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.item_logout -> {
                        navigateToLogin()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner)

        sharedViewModel.shoeListItem.observe(viewLifecycleOwner) { shoesList ->
            for (shoe in shoesList) {
                val shoeItem = buildShoeItem(shoe)
                binding.linearLayoutShoes.addView(shoeItem)
            }
        }

    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private fun buildShoeItem(item: Shoe) : LinearLayout {
        val linearLayout = LinearLayout(requireContext())
        val textView = TextView(requireContext())
        val image = ImageView(requireContext())

        linearLayout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        linearLayout.setPadding(16)
        linearLayout.gravity = Gravity.CENTER_VERTICAL

        textView.text = item.name
        textView.layoutParams = LayoutParams(450, LayoutParams.WRAP_CONTENT)
        textView.textAlignment = TEXT_ALIGNMENT_CENTER

        image.layoutParams = LayoutParams(200, 200)
        image.setImageDrawable(requireContext().getDrawable(item.images[0]))

        linearLayout.addView(image)
        linearLayout.addView(textView)

        return linearLayout
    }

    fun navigateToShoeDetail() {
        val navAction = ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment()
        findNavController().navigate(navAction)
    }

    fun navigateToLogin() {
        val navAction = ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment()
        findNavController().navigate(navAction)
    }
}