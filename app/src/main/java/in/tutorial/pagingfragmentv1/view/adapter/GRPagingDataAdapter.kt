package `in`.tutorial.pagingfragmentv1.view.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.RecyclerView
import `in`.tutorial.pagingfragmentv1.data.remote.model.GRPaging
import `in`.tutorial.pagingfragmentv1.databinding.GrListItemBinding
import `in`.tutorial.pagingfragmentv1.view.activity.GRDetails

class GRPagingDataAdapter(
    private val fragment:Fragment
):PagingDataAdapter<GRPaging.GoodsReceived, GRPagingDataAdapter.GRViewHolder>(
    diffCallback = diffUtil
) {
    override fun onBindViewHolder(holder: GRViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
        holder.itemView.setOnClickListener {
            Log.e("GRClick", " GRId - ${holder.binding.tvGrId.text}")
            val intent = Intent(fragment.activity, GRDetails::class.java)
            intent.putExtra("grId", holder.binding.tvGrId.text)
            fragment.activity?.startActivity(intent)
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
            binding.tvGrId.text = data.grId
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