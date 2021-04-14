package com.codewithmohsen.oprestaurantapp.api

import com.codewithmohsen.oprestaurantapp.model.MenuModel
import retrofit2.http.GET

interface MenuService {

    /**
     * get menu of restaurant.
     */
    @GET("task/menu.json")
    suspend fun getMenu(): NetworkResponse<MenuModel, Any>
}