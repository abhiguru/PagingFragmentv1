package `in`.tutorial.pagingfragmentv1.data.remote.repository.flow

import `in`.tutorial.pagingfragmentv1.data.remote.model.AuthToken
import `in`.tutorial.pagingfragmentv1.data.remote.response.AuthTokenResponse
import `in`.tutorial.pagingfragmentv1.data.remote.response.DispatchDetailsResponse
import `in`.tutorial.pagingfragmentv1.data.remote.response.GoodReceivedDetailsResponse
import `in`.tutorial.pagingfragmentv1.data.remote.response.InvoiceDetailsResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface FlowRepository {
    fun getGRDetails(authToken: String, id:UUID): Flow<GoodReceivedDetailsResponse>
    fun getDispatchDetails(authToken: String, id:UUID): Flow<DispatchDetailsResponse>
    fun getInvoiceDetails(authToken: String, id:UUID): Flow<InvoiceDetailsResponse>
    fun postAuth(url:String, body: AuthToken):Flow<AuthTokenResponse>
}