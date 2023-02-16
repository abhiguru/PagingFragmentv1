package `in`.tutorial.pagingfragmentv1.data.remote.repository.flow

import `in`.tutorial.pagingfragmentv1.data.remote.model.AuthToken
import `in`.tutorial.pagingfragmentv1.data.remote.model.AuthTokenResponse
import `in`.tutorial.pagingfragmentv1.data.remote.response.GoodReceivedDetails
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface GRDetailsFlowRepository {
    fun getGRDetails(id:UUID): Flow<GoodReceivedDetails>
    fun postAuth(url:String, body: AuthToken):Flow<AuthTokenResponse>
}