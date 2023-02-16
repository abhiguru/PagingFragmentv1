package `in`.tutorial.pagingfragmentv1.data.remote.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AuthTokenResponse(
    @SerializedName("details")
    val authDetails: AuthDetails,
    @SerializedName("status")
    val status: String,
    @SerializedName("type")
    val type: String
) {
    @Keep
    data class AuthDetails(
        @SerializedName("token")
        val token: String
    )
}