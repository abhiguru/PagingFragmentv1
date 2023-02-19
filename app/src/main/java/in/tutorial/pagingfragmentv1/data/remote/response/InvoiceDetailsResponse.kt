package `in`.tutorial.pagingfragmentv1.data.remote.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class InvoiceDetailsResponse(
    @SerializedName("details")
    val invoiceDetails: InvoiceDetails,
    @SerializedName("status")
    val status: String,
    @SerializedName("type")
    val type: String
) {
    @Keep
    data class InvoiceDetails(
        @SerializedName("customer")
        val customer: Customer,
        @SerializedName("customerId")
        val customerId: String,
        @SerializedName("customerName")
        val customerName: String,
        @SerializedName("grId")
        val grId: String,
        @SerializedName("grNo")
        val grNo: GrNo,
        @SerializedName("grNoVal")
        val grNoVal: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("invDate")
        val invDate: String,
        @SerializedName("invFinYear")
        val invFinYear: Int,
        @SerializedName("invName")
        val invName: String,
        @SerializedName("invNo")
        val invNo: Int,
        @SerializedName("labour")
        val labour: Int,
        @SerializedName("oneTimeCharge")
        val oneTimeCharge: Int,
        @SerializedName("taxAmount")
        val taxAmount: Double,
        @SerializedName("total")
        val total: Int,
        @SerializedName("trl")
        val invoiceDetailsItems: List<InvoiceDetailsItems>
    ) {
        @Keep
        data class Customer(
            @SerializedName("1275a7fc-2831-11ed-ba24-525400024271")
            val a7fc283111edBa24525400024271: String
        )

        @Keep
        data class GrNo(
            @SerializedName("a1304871-398c-11ed-9813-2887ba3afcac")
            val a1304871398c11ed98132887ba3afcac: String
        )

        @Keep
        data class InvoiceDetailsItems(
            @SerializedName("charge")
            val charge: Double,
            @SerializedName("dispDate")
            val dispDate: String,
            @SerializedName("dispQty")
            val dispQty: Int,
            @SerializedName("dispTrlId")
            val dispTrlId: String,
            @SerializedName("duration")
            val duration: Double,
            @SerializedName("id")
            val id: String,
            @SerializedName("invoiceId")
            val invoiceId: String,
            @SerializedName("item")
            val item: Item,
            @SerializedName("itemId")
            val itemId: String,
            @SerializedName("itemName")
            val itemName: String,
            @SerializedName("noOfDays")
            val noOfDays: Int,
            @SerializedName("qty")
            val qty: Int,
            @SerializedName("tax")
            val tax: Int,
            @SerializedName("weight")
            val weight: Int
        ) {
            @Keep
            data class Item(
                @SerializedName("6da2df11-2903-11ed-ba24-525400024271")
                val da2df11290311edBa24525400024271: String
            )
        }
    }
}