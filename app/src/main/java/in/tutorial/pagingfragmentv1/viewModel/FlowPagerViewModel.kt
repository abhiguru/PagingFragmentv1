package `in`.tutorial.pagingfragmentv1.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import `in`.tutorial.pagingfragmentv1.data.remote.model.GRPaging
import `in`.tutorial.pagingfragmentv1.data.remote.repository.flow.GRFlowRepositoryImpl
import kotlinx.coroutines.flow.Flow

class FlowPagerViewModel(
    private val repositoryImpl: GRFlowRepositoryImpl
):ViewModel() {
    fun getGRListPaging():
            Flow<PagingData<GRPaging.GoodsReceived>> =
        repositoryImpl.getGRListPaging().cachedIn(viewModelScope)
}