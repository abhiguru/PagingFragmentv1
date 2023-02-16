package `in`.tutorial.pagingfragmentv1.data.remote.repository.flow

import androidx.paging.PagingData
import `in`.tutorial.pagingfragmentv1.data.remote.model.GRPaging
import `in`.tutorial.pagingfragmentv1.data.remote.response.GoodReceivedDetails
import kotlinx.coroutines.flow.Flow

interface GRFlowRepository {
    fun getGRListPaging(): Flow<PagingData<GRPaging.GoodsReceived>>
}