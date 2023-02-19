package `in`.tutorial.pagingfragmentv1.data.remote.repository.flow

import androidx.paging.PagingData
import `in`.tutorial.pagingfragmentv1.data.remote.response.InvoiceListResponse
import kotlinx.coroutines.flow.Flow

interface InvoiceFlowRepository {
    fun getInvoiceListPaging(): Flow<PagingData<InvoiceListResponse.InvoiceItem>>
}