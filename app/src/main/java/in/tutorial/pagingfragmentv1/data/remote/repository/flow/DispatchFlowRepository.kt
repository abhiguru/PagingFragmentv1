package `in`.tutorial.pagingfragmentv1.data.remote.repository.flow

import androidx.paging.PagingData
import `in`.tutorial.pagingfragmentv1.data.remote.response.DispatchListResponse
import kotlinx.coroutines.flow.Flow

interface DispatchFlowRepository {
    fun getDispatchListPaging(): Flow<PagingData<DispatchListResponse.DispatchItem>>
}