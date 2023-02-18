package `in`.tutorial.pagingfragmentv1.data.remote.repository.flow

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import `in`.tutorial.pagingfragmentv1.data.remote.repository.paging.DispatchFlowPagingSource
import `in`.tutorial.pagingfragmentv1.data.remote.response.DispatchListResponse
import kotlinx.coroutines.flow.Flow

class DispatchFlowRepositoryImpl(
    private val pagingSource: DispatchFlowPagingSource
):DispatchFlowRepository {
    override fun getDispatchListPaging(): Flow<PagingData<DispatchListResponse.DispatchItem>> {
        return Pager(defaultPagingConfig(), pagingSourceFactory = {pagingSource}).flow
    }
    private fun defaultPagingConfig():PagingConfig{
        return PagingConfig(
            pageSize = 30,
            prefetchDistance = 30,
            enablePlaceholders = false,
            initialLoadSize = 60,
            maxSize = 150
        )
    }
}