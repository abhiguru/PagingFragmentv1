package `in`.tutorial.pagingfragmentv1.data.remote

import `in`.tutorial.pagingfragmentv1.data.remote.model.AuthToken
import `in`.tutorial.pagingfragmentv1.data.remote.model.AuthTokenResponse
import `in`.tutorial.pagingfragmentv1.data.remote.response.GoodReceivedDetails
import `in`.tutorial.pagingfragmentv1.data.remote.response.GoodsReceivedResponse
import retrofit2.http.*
import java.util.UUID

interface NetworkService {
    @Headers(
        Endpoint.HEADER_ACCEPT
    )
    @GET(Endpoint.GET_ALL_GR)
    suspend fun getGRListFlowByGR(
        @Header("auth") auth:String,
        @Query("page") pageNumber: Int,
        @Query("dateFrom") dateFrom: String,
        @Query("dateTo") dateTo: String,
        @Query("grNo") grNo: String?
    ): GoodsReceivedResponse

    @Headers(
        Endpoint.HEADER_ACCEPT
    )
    @GET(Endpoint.GET_ALL_GR)
    suspend fun getGRListFlowAll(
        @Header("auth") auth:String,
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

    @POST
    suspend fun postAuthToken(@Url url:String, @Body body: AuthToken): AuthTokenResponse
}