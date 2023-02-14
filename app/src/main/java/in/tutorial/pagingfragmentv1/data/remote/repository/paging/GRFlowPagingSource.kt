package `in`.tutorial.pagingfragmentv1.data.remote.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import `in`.tutorial.pagingfragmentv1.data.remote.NetworkService
import `in`.tutorial.pagingfragmentv1.data.remote.model.GRPaging
import `in`.tutorial.pagingfragmentv1.data.remote.model.GoodReceivedResponseMapper
import `in`.tutorial.pagingfragmentv1.data.remote.response.GoodsReceivedResponse

class GRFlowPagingSource(
    private val networkService: NetworkService,
    private val dateFrom:String,
    private val dateTo:String,
    private val grNo:String
    ):PagingSource<Int, GRPaging.GoodsReceived>(), GoodReceivedResponseMapper<GoodsReceivedResponse,GRPaging> {
    override fun getRefreshKey(state: PagingState<Int, GRPaging.GoodsReceived>): Int? {
        return state.anchorPosition
    }

    // load data from api for params
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GRPaging.GoodsReceived> {
        val currentPage = params.key?:1
        try {
            if(grNo.isNotEmpty()){
                return networkService.getGRListFlowByGR(
                    pageNumber = currentPage, dateFrom = dateFrom,
                    dateTo = dateTo, grNo = grNo
                )
                    .run {
                        val data = mapFromResponse(this)
                        return LoadResult.Page(
                            data = data.goodsReceivedList,
                            prevKey = if (currentPage == 1) null else currentPage - 1,
                            nextKey = if (data.endOfPages) null else currentPage + 1
                        )
                    }
            }else {
                return networkService.getGRListFlowAll(
                    pageNumber = currentPage, dateFrom = dateFrom,
                    dateTo = dateTo
                )
                    .run {
                        val data = mapFromResponse(this)
                        return LoadResult.Page(
                            data = data.goodsReceivedList,
                            prevKey = if (currentPage == 1) null else currentPage - 1,
                            nextKey = if (data.endOfPages) null else currentPage + 1
                        )
                    }
            }
        }catch (e:java.lang.Exception){
            return LoadResult.Error(e)
        }
    }

    override fun mapFromResponse(response: GoodsReceivedResponse): GRPaging {
        return with(response){
            GRPaging(
                currentPage = response.currentPage,
                pagesCount = response.pagesCount,
                goodsReceivedList = grItems.map {
                    GRPaging.GoodsReceived(
                        customerName = it.customerName,
                        date = it.date,
                        grId = it.grId,
                        grNo = it.grNo,
                        id = it.id,
                        itemName = it.itemName,
                        packageMark = it.packageMark,
                        qty = it.qty,
                        rack = it.rack,
                        stock = it.stock,
                        weight = it.weight
                    )
                }
            )
        }
    }
}