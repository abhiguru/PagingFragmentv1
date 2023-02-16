package `in`.tutorial.pagingfragmentv1.data.remote.repository.flow

import `in`.tutorial.pagingfragmentv1.data.remote.NetworkService
import `in`.tutorial.pagingfragmentv1.data.remote.model.AuthToken
import `in`.tutorial.pagingfragmentv1.data.remote.model.AuthTokenResponse
import `in`.tutorial.pagingfragmentv1.data.remote.response.GoodReceivedDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

class GRDetailsFlowRepositoryImpl(private val networkService: NetworkService):GRDetailsFlowRepository {
    override fun getGRDetails(id: UUID): Flow<GoodReceivedDetails> = flow{
        emit(networkService.getGRDetails(id))
    }

    override fun postAuth(url: String, body: AuthToken): Flow<AuthTokenResponse> = flow {
        emit(networkService.postAuthToken(url, body))
    }

}