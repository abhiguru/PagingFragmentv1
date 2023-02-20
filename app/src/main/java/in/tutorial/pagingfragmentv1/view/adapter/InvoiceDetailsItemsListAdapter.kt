package `in`.tutorial.pagingfragmentv1.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import `in`.tutorial.pagingfragmentv1.R
import `in`.tutorial.pagingfragmentv1.data.remote.response.InvoiceDetailsResponse
import `in`.tutorial.pagingfragmentv1.databinding.InvoiceDetailsItemsListBinding
import `in`.tutorial.pagingfragmentv1.view.activity.InvoiceDetailsActivity

class InvoiceDetailsItemsListAdapter(
    val items : List<InvoiceDetailsResponse.InvoiceDetails.InvoiceDetailsItems>,
    val invoiceDetailsActivity: InvoiceDetailsActivity
):RecyclerView.Adapter<InvoiceDetailsItemsListAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: InvoiceDetailsItemsListBinding):RecyclerView.ViewHolder(binding.root) {
        fun onBind(model:InvoiceDetailsResponse.InvoiceDetails.InvoiceDetailsItems){
            binding.tvCharge.text =
                invoiceDetailsActivity.resources.getString(R.string.invoice_detail_charge,
                    model.charge.toString())
            binding.tvItemName.text =
                invoiceDetailsActivity.resources.getString(R.string.invoice_detail_itemName,
                    model.itemName)
            binding.tvDispDate.text =
                invoiceDetailsActivity.resources.getString(R.string.invoice_detail_dispDate,
                    model.dispDate)
            binding.tvDuration.text =
                invoiceDetailsActivity.resources.getString(R.string.invoice_detail_duration,
                    model.duration.toString())
            binding.tvDispQty.text =
                invoiceDetailsActivity.resources.getString(R.string.invoice_detail_dispQty,
                    model.dispQty.toString())
            binding.tvDispTrlId.text = model.dispTrlId
            binding.tvGrQty.text =
                invoiceDetailsActivity.resources.getString(R.string.invoice_detail_grQty,
                    model.qty.toString())
            binding.tvWeight.text =
                invoiceDetailsActivity.resources.getString(R.string.invoice_detail_weight,
                    model.weight.toString())
            binding.tvTax.text =
                invoiceDetailsActivity.resources.getString(R.string.invoice_detail_tax,
                    model.tax.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            InvoiceDetailsItemsListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(items[position])
    }
}