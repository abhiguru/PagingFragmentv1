package `in`.tutorial.pagingfragmentv1.data.remote.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class DispatchDetailsResponse(
    @SerializedName("details")
    val details: DispatchDetails,
    @SerializedName("status")
    val status: String,
    @SerializedName("type")
    val type: String
) {
    @Keep
    data class DispatchDetails(
        @SerializedName("customer")
        val customer: Customer,
        @SerializedName("customerId")
        val customerId: String,
        @SerializedName("customerName")
        val customerName: String,
        @SerializedName("dispDate")
        val dispDate: String,
        @SerializedName("dispNo")
        val dispNo: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("note")
        val note: String,
        @SerializedName("registrationNo")
        val registrationNo: String,
        @SerializedName("supervisor")
        val supervisor: Supervisor,
        @SerializedName("supervisorId")
        val supervisorId: String,
        @SerializedName("supervisorName")
        val supervisorName: String,
        @SerializedName("trl")
        val dispatchedItems: List<DispatchedItems>
    ) {
        @Keep
        data class Customer(
            @SerializedName("1275b54e-2831-11ed-ba24-525400024271")
            val b54e283111edBa24525400024271: String
        )

        @Keep
        data class Supervisor(
            @SerializedName("6726b1f0-2a11-11ed-ba24-525400024271")
            val b1f02a1111edBa24525400024271: String
        )

        @Keep
        data class DispatchedItems(
            @SerializedName("dispId")
            val dispId: String,
            @SerializedName("dispQty")
            val dispQty: Int,
            @SerializedName("grCustomer")
            val grCustomer: GrCustomer,
            @SerializedName("grCustomerId")
            val grCustomerId: String,
            @SerializedName("grCustomerName")
            val grCustomerName: String,
            @SerializedName("grDispNo")
            val grDispNo: GrDispNo,
            @SerializedName("grDispNoGRId")
            val grDispNoGRId: String,
            @SerializedName("grDispNoGRNo")
            val grDispNoGRNo: String,
            @SerializedName("grId")
            val grId: String,
            @SerializedName("grQty")
            val grQty: GrQty,
            @SerializedName("grQtyGRTrlId")
            val grQtyGRTrlId: String,
            @SerializedName("grQtyGRTrlQty")
            val grQtyGRTrlQty: Int,
            @SerializedName("grTrlId")
            val grTrlId: String,
            @SerializedName("id")
            val id: String,
            @SerializedName("item")
            val item: Item,
            @SerializedName("itemId")
            val itemId: String,
            @SerializedName("itemName")
            val itemName: String,
            @SerializedName("packageMark")
            val packageMark: String,
            @SerializedName("packaging")
            val packaging: String,
            @SerializedName("rack")
            val rack: String,
            @SerializedName("stock")
            val stock: Int,
            @SerializedName("trl_img_url")
            val trlImgUrl: String,
            @SerializedName("weight")
            val weight: Int
        ) {
            @Keep
            data class GrCustomer(
                @SerializedName("1275b54e-2831-11ed-ba24-525400024271")
                val b54e283111edBa24525400024271: String
            )

            @Keep
            data class GrDispNo(
                @SerializedName("46a66e2e-38d2-11ed-9813-2887ba3afcac")
                val a66e2e38d211ed98132887ba3afcac: String?,
                @SerializedName("f2fe7c89-38d4-11ed-9813-2887ba3afcac")
                val f2fe7c8938d411ed98132887ba3afcac: String?
            )

            @Keep
            data class GrQty(
                @SerializedName("009342a5-38d5-11ed-9813-2887ba3afcac")
                val a538d511ed98132887ba3afcac: Int?,
                @SerializedName("4dab0112-38d2-11ed-9813-2887ba3afcac")
                val dab011238d211ed98132887ba3afcac: Int?
            )

            @Keep
            data class Item(
                @SerializedName("6da2e384-2903-11ed-ba24-525400024271")
                val da2e384290311edBa24525400024271: String
            )
        }
    }
}