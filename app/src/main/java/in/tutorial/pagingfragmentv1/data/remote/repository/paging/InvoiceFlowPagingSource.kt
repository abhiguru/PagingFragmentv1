package `in`.tutorial.pagingfragmentv1.data.remote.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import `in`.tutorial.pagingfragmentv1.data.remote.NetworkService
import `in`.tutorial.pagingfragmentv1.data.remote.response.InvoiceListResponse

class InvoiceFlowPagingSource(
    private val authToken: String,
    private val networkService: NetworkService,
    private val dateFrom:String,
    private val dateTo:String,
    private val grNo:String
): PagingSource<Int, InvoiceListResponse.InvoiceItem>() {
    override fun getRefreshKey(state: PagingState<Int, InvoiceListResponse.InvoiceItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, InvoiceListResponse.InvoiceItem> {
        val currentPage = params.key?:1
        try {
            return networkService.getInvoiceListFlowAll(
                authToken, currentPage as Int, dateFrom, dateTo)
            .run{
                val data = this
                return LoadResult.Page(
                    data.invoiceItems,
                    prevKey = if(currentPage == 1) null else currentPage - 1,
                    nextKey = if(currentPage == data.pagesCount) null else currentPage + 1
                )
            }
        }catch (e:Exception){
            return LoadResult.Error(e)
        }
    }
}