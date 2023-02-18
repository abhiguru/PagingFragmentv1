package `in`.tutorial.pagingfragmentv1.data.remote.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class GoodReceivedDetailsResponse(
    @SerializedName("details")
    val grDetails: GRDetails,
    @SerializedName("status")
    val status: String,
    @SerializedName("type")
    val type: String
) {
    @Keep
    data class GRDetails(
        @SerializedName("customer")
        val customer: Customer,
        @SerializedName("customer_id")
        val customerId: String,
        @SerializedName("customer_name")
        val customerName: String,
        @SerializedName("date")
        val date: String,
        @SerializedName("grImageUrl")
        val grImageUrl: String,
        @SerializedName("grNo")
        val grNo: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("invoiced")
        val invoiced: Boolean,
        @SerializedName("leon")
        val leon: Boolean,
        @SerializedName("note")
        val note: String,
        @SerializedName("outOfStock")
        val outOfStock: Boolean,
        @SerializedName("registrationNo")
        val registrationNo: String,
        @SerializedName("sender")
        val sender: Sender,
        @SerializedName("sender_id")
        val senderId: String,
        @SerializedName("sender_name")
        val senderName: String,
        @SerializedName("supervisor")
        val supervisor: Supervisor,
        @SerializedName("supervisor_id")
        val supervisorId: String,
        @SerializedName("supervisor_name")
        val supervisorName: String,
        @SerializedName("trl")
        val trl: List<GRDetailsItem>
    ) {

        @Keep
        data class GRDetailsItem(
            @SerializedName("gr_id")
            val grId: String,
            @SerializedName("id")
            val id: String,
            @SerializedName("item")
            val item: Item,
            @SerializedName("item_id")
            val itemId: String,
            @SerializedName("item_name")
            val itemName: String,
            @SerializedName("packageMark")
            val packageMark: String,
            @SerializedName("packaging")
            val packaging: String,
            @SerializedName("qty")
            val qty: Int,
            @SerializedName("rack")
            val rack: String,
            @SerializedName("stock")
            val stock: Int,
            @SerializedName("trlImgUrl")
            val trlImgUrl: String,
            @SerializedName("weight")
            val weight: Int
        ) {
            @Keep
            data class Item(
                @SerializedName("6da2e496-2903-11ed-ba24-525400024271")
                val da2e496290311edBa24525400024271: String
            )
        }
        @Keep
        data class Customer(
            @SerializedName("1275b2eb-2831-11ed-ba24-525400024271")
            val b2eb283111edBa24525400024271: String
        )

        @Keep
        data class Sender(
            @SerializedName("1275b2eb-2831-11ed-ba24-525400024271")
            val b2eb283111edBa24525400024271: String
        )

        @Keep
        data class Supervisor(
            @SerializedName("6726b1f0-2a11-11ed-ba24-525400024271")
            val b1f02a1111edBa24525400024271: String
        )
    }
}