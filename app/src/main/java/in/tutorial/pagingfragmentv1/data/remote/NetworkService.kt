package `in`.tutorial.pagingfragmentv1.data.remote

import `in`.tutorial.pagingfragmentv1.data.remote.model.AuthToken
import `in`.tutorial.pagingfragmentv1.data.remote.response.AuthTokenResponse
import `in`.tutorial.pagingfragmentv1.data.remote.response.DispatchListResponse
import `in`.tutorial.pagingfragmentv1.data.remote.response.GoodReceivedDetailsResponse
import `in`.tutorial.pagingfragmentv1.data.remote.response.GoodsReceivedListResponse
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
    ): GoodsReceivedListResponse

    @Headers(
        Endpoint.HEADER_ACCEPT
    )
    @GET(Endpoint.GET_ALL_GR)
    suspend fun getGRListFlowAll(
        @Header("auth") auth:String,
        @Query("page") pageNumber: Int,
        @Query("dateFrom") dateFrom: String,
        @Query("dateTo") dateTo: String,
    ): GoodsReceivedListResponse

    @Headers(
        Endpoint.HEADER_ACCEPT
    )
    @GET(Endpoint.GET_ALL_DISPATCH)
    suspend fun getDispListFlowAll(
        @Header("auth") auth:String,
        @Query("page") pageNumber: Int,
        @Query("dateFrom") dateFrom: String,
        @Query("dateTo") dateTo: String,
    ): DispatchListResponse

    @Headers(
        Endpoint.HEADER_ACCEPT
    )
    @GET(Endpoint.GET_ALL_DISPATCH)
    suspend fun getDispListFlowAllByGR(
        @Header("auth") auth:String,
        @Query("page") pageNumber: Int,
        @Query("dateFrom") dateFrom: String,
        @Query("dateTo") dateTo: String,
        @Query("grNo") grNo: String?
    ): DispatchListResponse

    @Headers(
        Endpoint.HEADER_ACCEPT
    )
    @GET(Endpoint.GET_GR_DETAILS)
    suspend fun getGRDetails(@Header("auth") auth:String, @Path("id") id:UUID): GoodReceivedDetailsResponse

    @POST
    suspend fun postAuthToken(@Url url:String, @Body body: AuthToken): AuthTokenResponse
}