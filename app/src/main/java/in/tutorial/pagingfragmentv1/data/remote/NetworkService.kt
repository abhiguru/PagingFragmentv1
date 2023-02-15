package `in`.tutorial.pagingfragmentv1.data.remote

import `in`.tutorial.pagingfragmentv1.data.remote.response.GoodReceivedDetails
import `in`.tutorial.pagingfragmentv1.data.remote.response.GoodsReceivedResponse
import `in`.tutorial.pagingfragmentv1.view.activity.GRDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

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

    @Headers(
        Endpoint.HEADER_ACCEPT,
        Endpoint.HEADER_AUTH
    )
    @GET(Endpoint.GET_GR_DETAILS)
    suspend fun getGRDetails(@Path("id") id:UUID): GoodReceivedDetails

}