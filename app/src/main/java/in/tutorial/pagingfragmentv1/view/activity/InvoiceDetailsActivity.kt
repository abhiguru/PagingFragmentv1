package `in`.tutorial.pagingfragmentv1.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.tutorial.pagingfragmentv1.MyApplication
import `in`.tutorial.pagingfragmentv1.R
import `in`.tutorial.pagingfragmentv1.databinding.ActivityInvoiceDetailsBinding
import `in`.tutorial.pagingfragmentv1.view.adapter.InvoiceDetailsItemsListAdapter
import `in`.tutorial.pagingfragmentv1.viewModel.InvoiceDetailsViewModel

class InvoiceDetailsActivity : AppCompatActivity() {
    var binding: ActivityInvoiceDetailsBinding? = null
    private lateinit var viewModel: InvoiceDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInvoiceDetailsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        viewModel = InvoiceDetailsViewModel(this.application as MyApplication)
        val args : InvoiceDetailsActivityArgs by navArgs()
        args.invoiceId.let { viewModel.getInvoiceDetails(it) }
        viewModel.invoiceDetails.observe(this) {
            binding?.etInvoiceNo?.setText(it.invNo.toString())
            binding?.etCustomer?.setText(it.customerName)
            binding?.etInvoiceDate?.setText(it.invDate)
            binding?.etGrno?.setText(it.grNoVal)
            binding?.etLabour?.setText(it.labour.toString())
            binding?.etTaxAmount?.setText(it.taxAmount.toString())
            binding?.etTotal?.setText(it.total.toString())
            val dispatchAdapter = InvoiceDetailsItemsListAdapter(it.invoiceDetailsItems,
                this@InvoiceDetailsActivity)
            binding?.rvInvoiceDetailsListItems?.apply {
                layoutManager = LinearLayoutManager(this@InvoiceDetailsActivity)
                adapter = dispatchAdapter
            }
        }
        setupActionBar()
    }
    private fun setupActionBar(){
        setSupportActionBar(binding!!.toolbarInvoiceDetails)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.let {
            it.title = resources.getString(R.string.title_invoice_details)
        }
        binding?.toolbarInvoiceDetails!!.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}