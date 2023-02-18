package `in`.tutorial.pagingfragmentv1.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import `in`.tutorial.pagingfragmentv1.data.remote.response.DispatchListResponse
import `in`.tutorial.pagingfragmentv1.databinding.DispatchListItemBinding
import `in`.tutorial.pagingfragmentv1.view.fragment.DispatchPagingListFragment
import `in`.tutorial.pagingfragmentv1.view.fragment.DispatchPagingListFragmentDirections

class DispatchPagingDataAdapter(
    private val fragment: Fragment
): PagingDataAdapter<DispatchListResponse.DispatchItem,
        DispatchPagingDataAdapter.MyViewHolder>(diffUtil) {
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
            holder.itemView.setOnClickListener {
                val dispatchId = holder.binding.tvDispatchId.text
                fragment.findNavController().navigate(
                    DispatchPagingListFragmentDirections
                        .actionDispatchFiltersFragmentToDispatchDetails(dispatchId.toString())
                )
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DispatchListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<DispatchListResponse.DispatchItem>(){
            override fun areItemsTheSame(
                oldItem: DispatchListResponse.DispatchItem,
                newItem: DispatchListResponse.DispatchItem
            ): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(
                oldItem: DispatchListResponse.DispatchItem,
                newItem: DispatchListResponse.DispatchItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
    class MyViewHolder(val binding:DispatchListItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: DispatchListResponse.DispatchItem){
            binding.tvDispatchId.text = data.dispId
            binding.tvPackageMark.text = data.packageMark
            binding.tvGrNo.text = data.grNo
            binding.tvDispatchNo.text = data.dispNo
            binding.tvDispatchCustomerName.text = data.dispCustomerName
            binding.tvDispatchItemName.text = data.itemName
            binding.tvDispatchQty.text = data.dispQty.toString()
        }
    }
}