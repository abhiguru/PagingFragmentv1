package `in`.tutorial.pagingfragmentv1.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import `in`.tutorial.pagingfragmentv1.R
import `in`.tutorial.pagingfragmentv1.data.remote.response.DispatchDetailsResponse
import `in`.tutorial.pagingfragmentv1.databinding.DispatchDetailsItemsListBinding
import `in`.tutorial.pagingfragmentv1.view.activity.DispatchDetails

class DispatchDetailsItemsListAdapter(
    val dispatchDetailsListItems: List<DispatchDetailsResponse.DispatchDetails.DispatchedItems>,
    private val dispatchDetailsActivity: DispatchDetails
): RecyclerView.Adapter<DispatchDetailsItemsListAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: DispatchDetailsItemsListBinding):
        RecyclerView.ViewHolder(binding.root){
        fun onBind(item: DispatchDetailsResponse.DispatchDetails.DispatchedItems){
            binding?.tvGrNo?.text =
                dispatchDetailsActivity.resources.getString(R.string.dispatch_detail_grNo, item.grDispNoGRNo.toString())
            binding?.tvItemName?.text =
                dispatchDetailsActivity.resources.getString(R.string.dispatch_detail_itemName, item.itemName)
            binding?.tvDispQty?.text =
                dispatchDetailsActivity.resources.getString(R.string.dispatch_detail_dispQty, item.dispQty.toString())
            binding?.tvRack?.text =
                dispatchDetailsActivity.resources.getString(R.string.dispatch_detail_rack, item.rack)
            binding?.tvPackageMark?.text =
                dispatchDetailsActivity.resources.getString(R.string.dispatch_detail_packageMark, item.packageMark)
            binding?.tvWeight?.text =
                dispatchDetailsActivity.resources.getString(R.string.dispatch_detail_weight, item.weight.toString())
            binding?.tvGrQty?.text =
                dispatchDetailsActivity.resources.getString(R.string.dispatch_detail_grQty, item.grQtyGRTrlQty.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            DispatchDetailsItemsListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = dispatchDetailsListItems.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model = dispatchDetailsListItems[position]
        holder.onBind(model)
    }
}