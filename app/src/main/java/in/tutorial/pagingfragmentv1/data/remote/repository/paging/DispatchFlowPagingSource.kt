package `in`.tutorial.pagingfragmentv1.data.remote.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import `in`.tutorial.pagingfragmentv1.data.remote.NetworkService
import `in`.tutorial.pagingfragmentv1.data.remote.response.DispatchListResponse

class DispatchFlowPagingSource(
    private val authToken: String,
    private val networkService: NetworkService,
    private val dateFrom:String,
    private val dateTo:String,
    private val grNo:String
):PagingSource<Int, DispatchListResponse.DispatchItem>() {
    override fun getRefreshKey(state: PagingState<Int, DispatchListResponse.DispatchItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DispatchListResponse.DispatchItem> {
        val currentPage = params.key?:1
        try {
            if(grNo.isNotEmpty()){
                return networkService.getDispListFlowAllByGR(
                    auth = authToken,
                    pageNumber = currentPage, dateFrom = dateFrom,
                    dateTo = dateTo, grNo = grNo
                )
                    .run {
                        val data = this
                        return LoadResult.Page(
                            this.dispatchItems,
                            prevKey = if (currentPage == 1) null else currentPage - 1,
                            nextKey = if (data.pagesCount == data.currentPage) null else currentPage + 1
                        )
                    }
            }else{
                networkService.getDispListFlowAll(authToken, currentPage, dateFrom, dateTo)
                    .run {
                        val data = this
                        return LoadResult.Page(
                            this.dispatchItems,
                            prevKey = if (currentPage == 1) null else currentPage - 1,
                            nextKey = if (data.pagesCount == data.currentPage) null else currentPage + 1
                        )
                    }
            }
        }catch (e:java.lang.Exception){
            return LoadResult.Error(e)
        }
    }
}