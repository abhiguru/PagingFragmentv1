package `in`.tutorial.pagingfragmentv1.data.remote.repository.flow

import `in`.tutorial.pagingfragmentv1.data.remote.NetworkService
import `in`.tutorial.pagingfragmentv1.data.remote.model.AuthToken
import `in`.tutorial.pagingfragmentv1.data.remote.response.AuthTokenResponse
import `in`.tutorial.pagingfragmentv1.data.remote.response.DispatchDetailsResponse
import `in`.tutorial.pagingfragmentv1.data.remote.response.GoodReceivedDetailsResponse
import `in`.tutorial.pagingfragmentv1.data.remote.response.InvoiceDetailsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

class FlowRepositoryImpl(private val networkService: NetworkService):FlowRepository {
    override fun getGRDetails(authToken: String, id: UUID): Flow<GoodReceivedDetailsResponse> = flow{
        emit(networkService.getGRDetails(authToken, id))
    }

    override fun getDispatchDetails(authToken: String, id: UUID): Flow<DispatchDetailsResponse> = flow{
        emit(networkService.getDispatchDetails(authToken, id))
    }

    override fun getInvoiceDetails(authToken: String, id: UUID): Flow<InvoiceDetailsResponse> = flow{
        emit(networkService.getInvoiceDetails(authToken, id))
    }

    override fun postAuth(url: String, body: AuthToken): Flow<AuthTokenResponse> = flow {
        emit(networkService.postAuthToken(url, body))
    }

}