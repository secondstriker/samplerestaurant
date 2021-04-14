package com.codewithmohsen.oprestaurantapp.ui.restaurants

import androidx.lifecycle.ViewModel
import com.codewithmohsen.oprestaurantapp.repository.MenuRepository
import javax.inject.Inject

class RestaurantsViewModel @Inject constructor(
    private val repo: MenuRepository): ViewModel() {

    fun loadData() = repo.loadMenuItems()
}
