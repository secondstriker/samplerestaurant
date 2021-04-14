package com.codewithmohsen.oprestaurantapp.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codewithmohsen.oprestaurantapp.AppCoroutineDispatchers
import com.codewithmohsen.oprestaurantapp.api.NetworkResponse
import com.codewithmohsen.oprestaurantapp.utils.exhaustive
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


abstract class BaseRepository<ResultType, RequestType: Any>
constructor(private val dispatchers: AppCoroutineDispatchers) {

    private val result = MutableLiveData<Resource<ResultType>>()

     fun fetchNetwork() {
        CoroutineScope(dispatchers.main).launch {

            result.value = (Resource.loading(null))

            val apiResponse = createCall()
            when (apiResponse) {
                is NetworkResponse.Success -> {

                    result.value = successRequestToResult(
                        Resource.success(processResponse(apiResponse))
                    )
                }
                is NetworkResponse.ApiError -> {
                    withContext(dispatchers.main) {
                        result.value = (Resource.error("Api error, please try again.", null))
                    }
                }
                is NetworkResponse.NetworkError -> {
                    withContext(dispatchers.main) {
                        onFetchFailed()
                        result.value = (Resource.error(apiResponse.error.message, null))
                    }
                }
                is NetworkResponse.UnknownError ->{
                    withContext(dispatchers.main) {
                        onFetchFailed()
                        result.value = Resource.error(apiResponse.error?.message, null)
                    }
                }
            }.exhaustive
        }

    }
    protected open fun onFetchFailed() {}

    protected fun liveDataResult() = result as LiveData<Resource<ResultType>>

    @WorkerThread
    protected open suspend fun processResponse(response: NetworkResponse.Success<RequestType>) = response.body

    @MainThread
    protected abstract suspend fun createCall(): NetworkResponse<RequestType, Any>

    @MainThread
    protected abstract fun successRequestToResult(requestType: Resource<RequestType>): Resource<ResultType>
}
