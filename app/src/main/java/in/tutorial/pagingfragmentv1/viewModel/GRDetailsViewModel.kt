package `in`.tutorial.pagingfragmentv1.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import `in`.tutorial.pagingfragmentv1.MyApplication
import `in`.tutorial.pagingfragmentv1.data.remote.repository.flow.GRDetailsFlowRepository
import `in`.tutorial.pagingfragmentv1.data.remote.repository.flow.GRDetailsFlowRepositoryImpl
import `in`.tutorial.pagingfragmentv1.data.remote.response.GoodReceivedDetails
import io.reactivex.rxjava3.disposables.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.util.*

class GRDetailsViewModel(application: MyApplication) :AndroidViewModel(application){

    val grDetails = MutableLiveData<GoodReceivedDetails.GRDetails>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    lateinit var grDetailsFlowRepositoryImpl:GRDetailsFlowRepositoryImpl

    fun getDetails(grId:String){
        val networkService = (getApplication<Application>() as MyApplication).networkService
        grDetailsFlowRepositoryImpl = GRDetailsFlowRepositoryImpl(networkService)
        viewModelScope.launch {
            grDetailsFlowRepositoryImpl.getGRDetails(UUID.fromString(grId))
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