package `in`.tutorial.pagingfragmentv1.data.remote.repository.flow

import `in`.tutorial.pagingfragmentv1.data.remote.model.AuthToken
import `in`.tutorial.pagingfragmentv1.data.remote.response.AuthTokenResponse
import `in`.tutorial.pagingfragmentv1.data.remote.response.GoodReceivedDetailsResponse
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface GRDetailsFlowRepository {
    fun getGRDetails(authToken: String, id:UUID): Flow<GoodReceivedDetailsResponse>
    fun postAuth(url:String, body: AuthToken):Flow<AuthTokenResponse>
}