package `in`.tutorial.pagingfragmentv1.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.tutorial.pagingfragmentv1.MyApplication
import `in`.tutorial.pagingfragmentv1.R
import `in`.tutorial.pagingfragmentv1.databinding.ActivityDispatchDetailsBinding
import `in`.tutorial.pagingfragmentv1.view.adapter.DispatchDetailsItemsListAdapter
import `in`.tutorial.pagingfragmentv1.view.adapter.GRDetailsItemsListAdapter
import `in`.tutorial.pagingfragmentv1.viewModel.DispatchDetailsViewModel
import `in`.tutorial.pagingfragmentv1.viewModel.GRDetailsViewModel

class DispatchDetails : AppCompatActivity() {
    var binding:ActivityDispatchDetailsBinding? = null
    private lateinit var viewModel: DispatchDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDispatchDetailsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        viewModel = DispatchDetailsViewModel(this.application as MyApplication)
        val args : DispatchDetailsArgs by navArgs()
        args.dispId.let { viewModel.getDispatchDetails(it) }
        viewModel.dispatchDetails.observe(this) {
            binding?.etDispNo?.setText(it.dispNo)
            binding?.etDispDate?.setText(it.dispDate)
            binding?.etRegistration?.setText(it.registrationNo)
            binding?.etCustomer?.setText(it.customerName)
            binding?.etSupervisor?.setText(it.supervisorName)
            val dispatchAdapter = DispatchDetailsItemsListAdapter(it.dispatchedItems,
                this@DispatchDetails)
            binding?.rvDispDetailsListItems?.apply {
                layoutManager = LinearLayoutManager(this@DispatchDetails)
                adapter = dispatchAdapter
            }
        }
        setupActionBar()
    }
    private fun setupActionBar(){
        setSupportActionBar(binding!!.toolbarDispatchDetails)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.let {
            it.title = resources.getString(R.string.title_dispatch_details)
        }
        binding?.toolbarDispatchDetails!!.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}