package `in`.tutorial.pagingfragmentv1.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.RecyclerView
import `in`.tutorial.pagingfragmentv1.data.remote.model.GRPaging
import `in`.tutorial.pagingfragmentv1.databinding.GrListItemBinding

class GRPagingDataAdapter():PagingDataAdapter<GRPaging.GoodsReceived, GRPagingDataAdapter.GRViewHolder>(
    diffCallback = diffUtil
) {
    override fun onBindViewHolder(holder: GRViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GRViewHolder {
        val binding = GrListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return GRViewHolder(binding)

    }

    class GRViewHolder(val binding:GrListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(data:GRPaging.GoodsReceived){
            binding.tvGrNo.text = data.grNo
            binding.tvQty.text = data.qty.toString()
            binding.tvRack.text = data.rack
            binding.tvCustomerName.text = data.customerName
            binding.tvItemName.text = data.itemName
            binding.tvPackageMark.text = data.packageMark
        }
    }
    companion object{
        val diffUtil =
            object : DiffUtil.ItemCallback<GRPaging.GoodsReceived>() {
                override fun areItemsTheSame(
                    oldItem: GRPaging.GoodsReceived,
                    newItem: GRPaging.GoodsReceived
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: GRPaging.GoodsReceived,
                    newItem: GRPaging.GoodsReceived
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }
}