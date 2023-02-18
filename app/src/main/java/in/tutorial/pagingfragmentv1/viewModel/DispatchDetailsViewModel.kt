package `in`.tutorial.pagingfragmentv1.viewModel

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import `in`.tutorial.pagingfragmentv1.MyApplication
import `in`.tutorial.pagingfragmentv1.data.remote.Endpoint
import `in`.tutorial.pagingfragmentv1.data.remote.repository.flow.FlowRepositoryImpl
import `in`.tutorial.pagingfragmentv1.data.remote.response.DispatchDetailsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.util.*

class DispatchDetailsViewModel(val application: MyApplication): AndroidViewModel(application) {
    val dispatchDetails = MutableLiveData<DispatchDetailsResponse.DispatchDetails>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    lateinit var flowRepositoryImpl: FlowRepositoryImpl

    fun getDispatchDetails(dispId:String){
        val flowRepositoryImpl = FlowRepositoryImpl(application.networkService)
        viewModelScope.launch{
            flowRepositoryImpl.getDispatchDetails(Endpoint.AUTH_TOKEN, UUID.fromString(dispId))
                .flowOn(Dispatchers.IO)
                .catch {e->
                    loading.value = false
                    error.value = true
                    e.localizedMessage?.let { Log.e("Get Details", it) }
                }
                .collect{
                    dispatchDetails.value = it.details
                    loading.value = false
                    error.value = false
                }
        }
    }
}