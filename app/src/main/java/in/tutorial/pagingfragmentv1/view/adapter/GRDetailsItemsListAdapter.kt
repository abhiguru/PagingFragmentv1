package `in`.tutorial.pagingfragmentv1.view.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import `in`.tutorial.pagingfragmentv1.R
import `in`.tutorial.pagingfragmentv1.data.remote.response.GoodReceivedDetails
import `in`.tutorial.pagingfragmentv1.databinding.GrDetailsItemsListBinding
import `in`.tutorial.pagingfragmentv1.view.activity.GRDetails

class GRDetailsItemsListAdapter(
    val grDetailsListItems: List<GoodReceivedDetails.GRDetails.GRDetailsItem>,
    private val grDetailsActivity: GRDetails
):RecyclerView.Adapter<GRDetailsItemsListAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: GrDetailsItemsListBinding):
        RecyclerView.ViewHolder(binding.root){
        fun onBind(item:GoodReceivedDetails.GRDetails.GRDetailsItem){
            binding?.tvItemName?.text =
                grDetailsActivity.resources.getString(R.string.gr_dtl_item_name, item.itemName)
            binding?.tvQty?.text =
                grDetailsActivity.resources.getString(R.string.gr_dtl_qty, item.qty.toString())
            binding?.tvItemId?.text = item.itemId
            binding?.tvRack?.text =
                grDetailsActivity.resources.getString(R.string.gr_dtl_rack, item.rack)
            binding?.tvPackageMark?.text =
                grDetailsActivity.resources.getString(R.string.gr_dtl_package_mark, item.packageMark)
            binding?.tvStock?.text =
                grDetailsActivity.resources.getString(R.string.gr_dtl_in_stock, item.stock.toString())
            binding?.tvWeight?.text =
                grDetailsActivity.resources.getString(R.string.gr_dtl_weight, item.weight.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            GrDetailsItemsListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = grDetailsListItems.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model = grDetailsListItems[position]
        holder.onBind(model)
    }
}