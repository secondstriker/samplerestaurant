package com.codewithmohsen.oprestaurantapp.ui.restaurants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.codewithmohsen.oprestaurantapp.AppCoroutineDispatchers
import com.codewithmohsen.oprestaurantapp.R
import com.codewithmohsen.oprestaurantapp.adapter.MenuItemAdapter
import com.codewithmohsen.oprestaurantapp.binding.FragmentDataBindingComponent
import com.codewithmohsen.oprestaurantapp.databinding.FragmentRestaurantsBinding
import com.codewithmohsen.oprestaurantapp.di.Injectable
import com.codewithmohsen.oprestaurantapp.utils.autoCleared
import javax.inject.Inject

class RestaurantsFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var appCoroutineDispatchers: AppCoroutineDispatchers

    var binding by autoCleared<FragmentRestaurantsBinding>()
    private lateinit var adapter: MenuItemAdapter

    private var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    private val viewModel: RestaurantsViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_restaurants, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        adapter = MenuItemAdapter(appCoroutineDispatchers, dataBindingComponent) { item ->
            //onclick implementation

        }

        binding.recyclerViewFoods.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewFoods.adapter = adapter
        initItemList()
        return binding.root
    }

    private fun initItemList() {
        viewModel.loadData().observe(viewLifecycleOwner, Observer {resource ->
            adapter.submitList(resource?.data)
            binding.status = resource?.status
        })
    }
}