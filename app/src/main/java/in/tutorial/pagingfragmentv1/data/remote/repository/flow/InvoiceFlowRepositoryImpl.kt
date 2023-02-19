package `in`.tutorial.pagingfragmentv1.data.remote.repository.flow

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import `in`.tutorial.pagingfragmentv1.data.remote.repository.paging.InvoiceFlowPagingSource
import `in`.tutorial.pagingfragmentv1.data.remote.response.InvoiceListResponse
import kotlinx.coroutines.flow.Flow

class InvoiceFlowRepositoryImpl(
    private val pagingSource: InvoiceFlowPagingSource
) :InvoiceFlowRepository{
    override fun getInvoiceListPaging(): Flow<PagingData<InvoiceListResponse.InvoiceItem>> {
        return Pager(config = defaultPagingConfig(), pagingSourceFactory = {pagingSource}).flow
    }
    private fun defaultPagingConfig(): PagingConfig {
        return PagingConfig(
            pageSize = 30,
            prefetchDistance = 10,
            enablePlaceholders = false,
            initialLoadSize = 60,
            maxSize = 90
        )
    }
}