package `in`.tutorial.pagingfragmentv1.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import androidx.paging.PagingSource
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.tutorial.pagingfragmentv1.MyApplication
import `in`.tutorial.pagingfragmentv1.R
import `in`.tutorial.pagingfragmentv1.databinding.ActivityGrdetailsBinding
import `in`.tutorial.pagingfragmentv1.databinding.GrDetailsItemsListBinding
import `in`.tutorial.pagingfragmentv1.view.adapter.GRDetailsItemsListAdapter
import `in`.tutorial.pagingfragmentv1.viewModel.GRDetailsViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

class GRDetails : AppCompatActivity() {
    var bindings: ActivityGrdetailsBinding? = null
    private lateinit var viewModel: GRDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityGrdetailsBinding.inflate(layoutInflater)
        setContentView(bindings!!.root)
        val args : GRDetailsArgs by navArgs()
        val grId = args.grId
        viewModel = GRDetailsViewModel(this.application as MyApplication)
        grId?.let { viewModel.getDetails(it) }
        viewModel.grDetails.observe(this) {
            bindings?.etRegistration?.setText(it.registrationNo)
            bindings?.etSender?.setText(it.senderName)
            bindings?.etDate?.setText(it.date)
            bindings?.etSupervisor?.setText(it.supervisorName)
            bindings?.etCustomer?.setText(it.customerName)
            bindings?.etGrno?.setText(it.grNo)
            bindings?.etNote?.setText(it.note)
            val grAdapter = GRDetailsItemsListAdapter(it.trl,
                this@GRDetails)
            bindings?.rvGrDetailsItemsList?.apply {
                layoutManager = LinearLayoutManager(this@GRDetails)
                adapter = grAdapter
            }
        }
        setupActionBar()
    }
    private fun setupActionBar(){
        setSupportActionBar(bindings!!.toolbarGrDetails)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.let {
            it.title = resources.getString(R.string.title_gr_details)
        }
        bindings?.toolbarGrDetails!!.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}