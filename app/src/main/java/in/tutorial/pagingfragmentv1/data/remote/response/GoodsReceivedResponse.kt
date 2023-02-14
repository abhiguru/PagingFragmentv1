package `in`.tutorial.pagingfragmentv1.data.remote.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class GoodsReceivedResponse(
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("next")
    val next: Boolean,
    @SerializedName("pages_count")
    val pagesCount: Int,
    @SerializedName("results")
    val grItems: List<GRItem>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totals")
    val totals: Totals,
    @SerializedName("type")
    val type: String
) {
    @Keep
    data class GRItem(
        @SerializedName("customerName")
        val customerName: String,
        @SerializedName("date")
        val date: String,
        @SerializedName("grId")
        val grId: String,
        @SerializedName("grNo")
        val grNo: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("itemName")
        val itemName: String,
        @SerializedName("packageMark")
        val packageMark: String,
        @SerializedName("qty")
        val qty: Int,
        @SerializedName("rack")
        val rack: String,
        @SerializedName("stock")
        val stock: Int,
        @SerializedName("weight")
        val weight: Int
    )

    @Keep
    data class Totals(
        @SerializedName("count")
        val count: Int,
        @SerializedName("qty")
        val qty: String,
        @SerializedName("stock")
        val stock: String
    )
}