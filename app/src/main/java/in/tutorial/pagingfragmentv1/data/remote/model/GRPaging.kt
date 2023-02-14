package `in`.tutorial.pagingfragmentv1.data.remote.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


data class GRPaging(
    val currentPage: Int,
    val pagesCount: Int,
    val goodsReceivedList :List<GoodsReceived>
){
    val endOfPages = pagesCount == currentPage
    @Keep
    data class GoodsReceived(
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
}
