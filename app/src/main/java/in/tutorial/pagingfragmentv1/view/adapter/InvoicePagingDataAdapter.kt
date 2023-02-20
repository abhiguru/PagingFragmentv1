package `in`.tutorial.pagingfragmentv1.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import `in`.tutorial.pagingfragmentv1.R
import `in`.tutorial.pagingfragmentv1.data.remote.response.InvoiceListResponse
import `in`.tutorial.pagingfragmentv1.databinding.InvoiceListItemBinding
import `in`.tutorial.pagingfragmentv1.view.fragment.DispatchPagingListFragmentDirections
import `in`.tutorial.pagingfragmentv1.view.fragment.InvoicePagingListFragmentDirections

class InvoicePagingDataAdapter(
    private val fragment: Fragment
):
    PagingDataAdapter<InvoiceListResponse.InvoiceItem, InvoicePagingDataAdapter.MyViewHolder>(
        diffUtil) {
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if(position % 2==0){
            holder.itemView.setBackgroundColor(fragment.resources.getColor(R.color.white))
        }else{
            holder.itemView.setBackgroundColor(fragment.resources.getColor(R.color.paging_list))
        }
        getItem(position)?.let {
            holder.onBind(it)
            holder.itemView.setOnClickListener {
                val invoiceId = holder.binding.tvInvId.text
                fragment.findNavController().navigate(
                    InvoicePagingListFragmentDirections
                        .actionInvoicePagingListFragmentToInvoiceActivity(invoiceId = invoiceId as String)
                )
            }
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