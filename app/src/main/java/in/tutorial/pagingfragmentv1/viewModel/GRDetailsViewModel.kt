package `in`.tutorial.pagingfragmentv1.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import `in`.tutorial.pagingfragmentv1.MyApplication
import `in`.tutorial.pagingfragmentv1.data.remote.Endpoint
import `in`.tutorial.pagingfragmentv1.data.remote.repository.flow.FlowRepositoryImpl
import `in`.tutorial.pagingfragmentv1.data.remote.response.GoodReceivedDetailsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.util.*

class GRDetailsViewModel(application: MyApplication) :AndroidViewModel(application){

    val grDetails = MutableLiveData<GoodReceivedDetailsResponse.GRDetails>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    lateinit var flowRepositoryImpl:FlowRepositoryImpl

    fun getDetails(grId:String){
        val networkService = (getApplication<Application>() as MyApplication).networkService
        flowRepositoryImpl = FlowRepositoryImpl(networkService)
        viewModelScope.launch {
            flowRepositoryImpl.getGRDetails(Endpoint.AUTH_TOKEN, UUID.fromString(grId))
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    loading.value = false
                    error.value = true
                    e.localizedMessage?.let { Log.e("Get Details", it) }
                }.collect{
                    grDetails.value = it.grDetails
                    loading.value = false
                    error.value = false
                }
        }
    }
}