package `in`.tutorial.pagingfragmentv1.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import `in`.tutorial.pagingfragmentv1.data.remote.repository.flow.InvoiceFlowRepositoryImpl
import `in`.tutorial.pagingfragmentv1.data.remote.response.InvoiceListResponse
import kotlinx.coroutines.flow.Flow

class InvoiceFlowPagerViewModel(
    private val repositoryImpl: InvoiceFlowRepositoryImpl
):ViewModel() {
    fun getInvoiceListPaging(): Flow<PagingData<InvoiceListResponse.InvoiceItem>> =
        repositoryImpl.getInvoiceListPaging().cachedIn(viewModelScope)
}