package `in`.tutorial.pagingfragmentv1.viewModel

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import `in`.tutorial.pagingfragmentv1.MyApplication
import `in`.tutorial.pagingfragmentv1.data.remote.Endpoint
import `in`.tutorial.pagingfragmentv1.data.remote.repository.flow.FlowRepositoryImpl
import `in`.tutorial.pagingfragmentv1.data.remote.response.DispatchDetailsResponse
import `in`.tutorial.pagingfragmentv1.data.remote.response.InvoiceDetailsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.util.*

class InvoiceDetailsViewModel(val application: MyApplication): AndroidViewModel(application) {
    val invoiceDetails = MutableLiveData<InvoiceDetailsResponse.InvoiceDetails>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    lateinit var flowRepositoryImpl: FlowRepositoryImpl

    fun getInvoiceDetails(invoiceId:String){
        val flowRepositoryImpl = FlowRepositoryImpl(application.networkService)
        viewModelScope.launch{
            flowRepositoryImpl.getInvoiceDetails(Endpoint.AUTH_TOKEN, UUID.fromString(invoiceId))
                .flowOn(Dispatchers.IO)
                .catch {e->
                    loading.value = false
                    error.value = true
                    e.localizedMessage?.let { Log.e("Get Invoice Details", it) }
                }
                .collect{
                    invoiceDetails.value = it.invoiceDetails
                    loading.value = false
                    error.value = false
                }
        }
    }

}