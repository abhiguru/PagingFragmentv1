package `in`.tutorial.pagingfragmentv1.data.remote.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class InvoiceListResponse(
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("next")
    val next: Boolean,
    @SerializedName("pages_count")
    val pagesCount: Int,
    @SerializedName("results")
    val invoiceItems: List<InvoiceItem>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totals")
    val totals: Totals,
    @SerializedName("type")
    val type: String
) {
    @Keep
    data class InvoiceItem(
        @SerializedName("charge")
        val charge: Double,
        @SerializedName("customer")
        val customer: Customer,
        @SerializedName("customerName")
        val customerName: String,
        @SerializedName("date")
        val date: String,
        @SerializedName("duration")
        val duration: Double,
        @SerializedName("grNo")
        val grNo: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("invFinYear")
        val invFinYear: Int,
        @SerializedName("invId")
        val invId: String,
        @SerializedName("invNo")
        val invNo: Int,
        @SerializedName("item")
        val item: Item,
        @SerializedName("itemName")
        val itemName: String,
        @SerializedName("packageMark")
        val packageMark: String,
        @SerializedName("qty")
        val qty: Int,
        @SerializedName("taxTotal")
        val taxTotal: Double,
        @SerializedName("total")
        val total: Int
    ) {
        @Keep
        data class Customer(
            @SerializedName("1275a7fc-2831-11ed-ba24-525400024271")
            val a7fc283111edBa24525400024271: String?,
            @SerializedName("1275b249-2831-11ed-ba24-525400024271")
            val b249283111edBa24525400024271: String?,
            @SerializedName("1275b54e-2831-11ed-ba24-525400024271")
            val b54e283111edBa24525400024271: String?,
            @SerializedName("1275b69f-2831-11ed-ba24-525400024271")
            val b69f283111edBa24525400024271: String?,
            @SerializedName("1275c9a9-2831-11ed-ba24-525400024271")
            val c9a9283111edBa24525400024271: String?,
            @SerializedName("6da2df11-2903-11ed-ba24-525400024271")
            val da2df11290311edBa24525400024271: String?,
            @SerializedName("6da2e384-2903-11ed-ba24-525400024271")
            val da2e384290311edBa24525400024271: String?,
            @SerializedName("6da2e45c-2903-11ed-ba24-525400024271")
            val da2e45c290311edBa24525400024271: String?,
            @SerializedName("6da2e66e-2903-11ed-ba24-525400024271")
            val da2e66e290311edBa24525400024271: String?,
            @SerializedName("6da2e808-2903-11ed-ba24-525400024271")
            val da2e808290311edBa24525400024271: String?
        )

        @Keep
        data class Item(
            @SerializedName("1275a7fc-2831-11ed-ba24-525400024271")
            val a7fc283111edBa24525400024271: String?,
            @SerializedName("1275b249-2831-11ed-ba24-525400024271")
            val b249283111edBa24525400024271: String?,
            @SerializedName("1275b54e-2831-11ed-ba24-525400024271")
            val b54e283111edBa24525400024271: String?,
            @SerializedName("1275b69f-2831-11ed-ba24-525400024271")
            val b69f283111edBa24525400024271: String?,
            @SerializedName("1275c9a9-2831-11ed-ba24-525400024271")
            val c9a9283111edBa24525400024271: String?,
            @SerializedName("6da2df11-2903-11ed-ba24-525400024271")
            val da2df11290311edBa24525400024271: String?,
            @SerializedName("6da2e384-2903-11ed-ba24-525400024271")
            val da2e384290311edBa24525400024271: String?,
            @SerializedName("6da2e45c-2903-11ed-ba24-525400024271")
            val da2e45c290311edBa24525400024271: String?,
            @SerializedName("6da2e66e-2903-11ed-ba24-525400024271")
            val da2e66e290311edBa24525400024271: String?,
            @SerializedName("6da2e808-2903-11ed-ba24-525400024271")
            val da2e808290311edBa24525400024271: String?
        )
    }

    @Keep
    data class Totals(
        @SerializedName("total")
        val total: Int
    )
}