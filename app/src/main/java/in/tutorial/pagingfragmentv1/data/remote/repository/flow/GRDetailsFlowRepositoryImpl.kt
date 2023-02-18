package `in`.tutorial.pagingfragmentv1.data.remote.repository.flow

import `in`.tutorial.pagingfragmentv1.data.remote.NetworkService
import `in`.tutorial.pagingfragmentv1.data.remote.model.AuthToken
import `in`.tutorial.pagingfragmentv1.data.remote.response.AuthTokenResponse
import `in`.tutorial.pagingfragmentv1.data.remote.response.GoodReceivedDetailsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

class GRDetailsFlowRepositoryImpl(private val networkService: NetworkService):GRDetailsFlowRepository {
    override fun getGRDetails(authToken: String, id: UUID): Flow<GoodReceivedDetailsResponse> = flow{
        emit(networkService.getGRDetails(authToken, id))
    }

    override fun postAuth(url: String, body: AuthToken): Flow<AuthTokenResponse> = flow {
        emit(networkService.postAuthToken(url, body))
    }

}