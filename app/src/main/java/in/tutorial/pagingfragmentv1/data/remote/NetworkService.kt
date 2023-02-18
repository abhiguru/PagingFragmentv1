package `in`.tutorial.pagingfragmentv1.data.remote

import `in`.tutorial.pagingfragmentv1.data.remote.model.AuthToken
import `in`.tutorial.pagingfragmentv1.data.remote.response.*
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

    @Headers(
        Endpoint.HEADER_ACCEPT
    )
    @GET(Endpoint.GET_DISPATCH_DETAILS)
    suspend fun getDispatchDetails(@Header("auth") auth:String, @Path("id") id:UUID): DispatchDetailsResponse

    @POST
    suspend fun postAuthToken(@Url url:String, @Body body: AuthToken): AuthTokenResponse
}