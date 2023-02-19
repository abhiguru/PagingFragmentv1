package `in`.tutorial.pagingfragmentv1.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import `in`.tutorial.pagingfragmentv1.data.remote.response.InvoiceListResponse
import `in`.tutorial.pagingfragmentv1.databinding.InvoiceListItemBinding

class InvoicePagingDataAdapter():
    PagingDataAdapter<InvoiceListResponse.InvoiceItem, InvoicePagingDataAdapter.MyViewHolder>(
        diffUtil) {
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            InvoiceListItemBinding.inflate(
                LayoutInflater.from(parent.context),parent, false
            )
        )
    }

    inner class MyViewHolder(val binding: InvoiceListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun onBind(model: InvoiceListResponse.InvoiceItem){
            binding?.tvDate?.text = model.date
            binding?.tvGrNo?.text = model.grNo
            binding?.tvInvId?.text = model.id
            binding?.tvGrQty?.text = model.qty.toString()
            binding?.tvPackageMark?.text = model.packageMark
            binding?.tvInvNo?.text = model.invNo.toString()
            binding?.tvInvTotal?.text = model.total.toString()
        }
    }
    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<InvoiceListResponse.InvoiceItem>(){
            override fun areItemsTheSame(
                oldItem: InvoiceListResponse.InvoiceItem,
                newItem: InvoiceListResponse.InvoiceItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: InvoiceListResponse.InvoiceItem,
                newItem: InvoiceListResponse.InvoiceItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}