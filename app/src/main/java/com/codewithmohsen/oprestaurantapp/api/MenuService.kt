package com.codewithmohsen.oprestaurantapp.api

import com.codewithmohsen.oprestaurantapp.model.Menu
import retrofit2.http.GET

interface MenuService {

    /**
     * get menu of restaurant.
     */
    @GET("task/menu.json")
    suspend fun getMenu(): NetworkResponse<Menu, Any>
}