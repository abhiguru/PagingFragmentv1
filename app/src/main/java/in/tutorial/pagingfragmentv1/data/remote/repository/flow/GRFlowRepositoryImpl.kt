package `in`.tutorial.pagingfragmentv1.data.remote.repository.flow

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import `in`.tutorial.pagingfragmentv1.data.remote.model.GRPaging
import `in`.tutorial.pagingfragmentv1.data.remote.repository.paging.GRFlowPagingSource
import `in`.tutorial.pagingfragmentv1.data.remote.response.GoodReceivedDetails
import kotlinx.coroutines.flow.Flow

class GRFlowRepositoryImpl(
    private val pagingSource: GRFlowPagingSource
) : GRFlowRepository{
    override fun getGRListPaging(): Flow<PagingData<GRPaging.GoodsReceived>> {
        return Pager(
            defaultPagingConfig(),
            pagingSourceFactory = {pagingSource}
        ).flow
    }

    private fun defaultPagingConfig():PagingConfig{
        return PagingConfig(
            pageSize = 30,
            prefetchDistance = 10,
            enablePlaceholders = false,
            initialLoadSize = 60,
            maxSize = 90
        )
    }
}