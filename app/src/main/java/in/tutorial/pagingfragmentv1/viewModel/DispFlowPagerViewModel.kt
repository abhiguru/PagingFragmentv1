package `in`.tutorial.pagingfragmentv1.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import `in`.tutorial.pagingfragmentv1.data.remote.repository.flow.DispatchFlowRepositoryImpl
import `in`.tutorial.pagingfragmentv1.data.remote.response.DispatchListResponse
import kotlinx.coroutines.flow.Flow

class DispatchFlowPagerViewModel(
    private val repositoryImpl: DispatchFlowRepositoryImpl
): ViewModel(){
    fun getDispatchListPaging(): Flow<PagingData<DispatchListResponse.DispatchItem>> =
        repositoryImpl.getDispatchListPaging().cachedIn(viewModelScope)
}