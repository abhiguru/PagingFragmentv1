package `in`.tutorial.pagingfragmentv1.data.remote.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class DispatchListResponse(
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("next")
    val next: Boolean,
    @SerializedName("pages_count")
    val pagesCount: Int,
    @SerializedName("results")
    val dispatchItems: List<DispatchItem>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totals")
    val totals: Totals,
    @SerializedName("type")
    val type: String
) {
    @Keep
    data class DispatchItem(
        @SerializedName("dispCustomer")
        val dispCustomer: DispCustomer,
        @SerializedName("dispCustomerId")
        val dispCustomerId: String,
        @SerializedName("dispCustomerName")
        val dispCustomerName: String,
        @SerializedName("dispDate")
        val dispDate: String,
        @SerializedName("dispId")
        val dispId: String,
        @SerializedName("dispNo")
        val dispNo: String,
        @SerializedName("dispQty")
        val dispQty: Int,
        @SerializedName("grCustomer")
        val grCustomer: GrCustomer,
        @SerializedName("grCustomerId")
        val grCustomerId: String,
        @SerializedName("grCustomerName")
        val grCustomerName: String,
        @SerializedName("grDate")
        val grDate: String,
        @SerializedName("grNo")
        val grNo: String,
        @SerializedName("grQty")
        val grQty: Int,
        @SerializedName("id")
        val id: String,
        @SerializedName("item")
        val item: Item,
        @SerializedName("itemId")
        val itemId: String,
        @SerializedName("itemName")
        val itemName: String,
        @SerializedName("packageMark")
        val packageMark: String
    ) {
        @Keep
        data class DispCustomer(
            @SerializedName("1275a7fc-2831-11ed-ba24-525400024271")
            val a7fc283111edBa24525400024271: String?,
            @SerializedName("1275b249-2831-11ed-ba24-525400024271")
            val b249283111edBa24525400024271: String?,
            @SerializedName("1275b2eb-2831-11ed-ba24-525400024271")
            val b2eb283111edBa24525400024271: String?,
            @SerializedName("1275b54e-2831-11ed-ba24-525400024271")
            val b54e283111edBa24525400024271: String?,
            @SerializedName("1275b7b1-2831-11ed-ba24-525400024271")
            val b7b1283111edBa24525400024271: String?,
            @SerializedName("1275b86f-2831-11ed-ba24-525400024271")
            val b86f283111edBa24525400024271: String?,
            @SerializedName("1275b98f-2831-11ed-ba24-525400024271")
            val b98f283111edBa24525400024271: String?,
            @SerializedName("1275bdeb-2831-11ed-ba24-525400024271")
            val bdeb283111edBa24525400024271: String?,
            @SerializedName("1275bed5-2831-11ed-ba24-525400024271")
            val bed5283111edBa24525400024271: String?,
            @SerializedName("1275c1e5-2831-11ed-ba24-525400024271")
            val c1e5283111edBa24525400024271: String?
        )

        @Keep
        data class GrCustomer(
            @SerializedName("1275a7fc-2831-11ed-ba24-525400024271")
            val a7fc283111edBa24525400024271: String?,
            @SerializedName("1275b249-2831-11ed-ba24-525400024271")
            val b249283111edBa24525400024271: String?,
            @SerializedName("1275b2eb-2831-11ed-ba24-525400024271")
            val b2eb283111edBa24525400024271: String?,
            @SerializedName("1275b54e-2831-11ed-ba24-525400024271")
            val b54e283111edBa24525400024271: String?,
            @SerializedName("1275b7b1-2831-11ed-ba24-525400024271")
            val b7b1283111edBa24525400024271: String?,
            @SerializedName("1275b98f-2831-11ed-ba24-525400024271")
            val b98f283111edBa24525400024271: String?,
            @SerializedName("1275bdeb-2831-11ed-ba24-525400024271")
            val bdeb283111edBa24525400024271: String?,
            @SerializedName("1275bed5-2831-11ed-ba24-525400024271")
            val bed5283111edBa24525400024271: String?,
            @SerializedName("1275c1e5-2831-11ed-ba24-525400024271")
            val c1e5283111edBa24525400024271: String?,
            @SerializedName("12781695-2831-11ed-ba24-525400024271")
            val edBa24525400024271: String?
        )

        @Keep
        data class Item(
            @SerializedName("6da2df11-2903-11ed-ba24-525400024271")
            val da2df11290311edBa24525400024271: String?,
            @SerializedName("6da2e384-2903-11ed-ba24-525400024271")
            val da2e384290311edBa24525400024271: String?,
            @SerializedName("6da2e45c-2903-11ed-ba24-525400024271")
            val da2e45c290311edBa24525400024271: String?,
            @SerializedName("6da2e496-2903-11ed-ba24-525400024271")
            val da2e496290311edBa24525400024271: String?,
            @SerializedName("6da2e4c6-2903-11ed-ba24-525400024271")
            val da2e4c6290311edBa24525400024271: String?,
            @SerializedName("6da2e566-2903-11ed-ba24-525400024271")
            val da2e566290311edBa24525400024271: String?,
            @SerializedName("6da2e6ca-2903-11ed-ba24-525400024271")
            val da2e6ca290311edBa24525400024271: String?,
            @SerializedName("6da2e808-2903-11ed-ba24-525400024271")
            val da2e808290311edBa24525400024271: String?,
            @SerializedName("6da2e871-2903-11ed-ba24-525400024271")
            val da2e871290311edBa24525400024271: String?,
            @SerializedName("6da2e979-2903-11ed-ba24-525400024271")
            val da2e979290311edBa24525400024271: String?
        )
    }

    @Keep
    data class Totals(
        @SerializedName("count")
        val count: Int,
        @SerializedName("dispQty")
        val dispQty: Int,
        @SerializedName("stock")
        val stock: Int
    )
}