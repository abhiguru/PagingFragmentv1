package `in`.tutorial.pagingfragmentv1.data.remote

import `in`.tutorial.pagingfragmentv1.data.remote.response.GoodsReceivedResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NetworkService {
    @Headers(
        Endpoint.HEADER_ACCEPT,
        Endpoint.HEADER_AUTH
    )
    @GET(Endpoint.GET_ALL_GR)
    suspend fun getGRListFlowByGR(
        @Query("page") pageNumber: Int,
        @Query("dateFrom") dateFrom: String,
        @Query("dateTo") dateTo: String,
        @Query("grNo") grNo: String?
    ): GoodsReceivedResponse

    @Headers(
        Endpoint.HEADER_ACCEPT,
        Endpoint.HEADER_AUTH
    )
    @GET(Endpoint.GET_ALL_GR)
    suspend fun getGRListFlowAll(
        @Query("page") pageNumber: Int,
        @Query("dateFrom") dateFrom: String,
        @Query("dateTo") dateTo: String,
    ): GoodsReceivedResponse
}