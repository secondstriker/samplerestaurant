package com.codewithmohsen.oprestaurantapp.repository

import androidx.lifecycle.LiveData
import com.codewithmohsen.oprestaurantapp.AppCoroutineDispatchers
import com.codewithmohsen.oprestaurantapp.api.MenuService
import com.codewithmohsen.oprestaurantapp.api.NetworkResponse
import com.codewithmohsen.oprestaurantapp.model.MenuItem
import com.codewithmohsen.oprestaurantapp.model.MenuModel
import javax.inject.Inject

class MenuServiceRepository @Inject constructor(
    private val dispatchers: AppCoroutineDispatchers,
    private val service: MenuService
): BaseRepository<List<MenuItem>, MenuModel>(dispatchers) {

    fun loadMenuItems(): LiveData<Resource<List<MenuItem>>> {
        super.fetchNetwork()
        return super.liveDataResult()
    }

    override suspend fun createCall(): NetworkResponse<MenuModel, Any> =
        service.getMenu()

    override fun successRequestToResult(requestType: Resource<MenuModel>): Resource<List<MenuItem>> =
        Resource.success(requestType.data)
}