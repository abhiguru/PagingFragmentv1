package `in`.tutorial.pagingfragmentv1.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingSource
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.tutorial.pagingfragmentv1.MyApplication
import `in`.tutorial.pagingfragmentv1.R
import `in`.tutorial.pagingfragmentv1.databinding.ActivityGrdetailsBinding
import `in`.tutorial.pagingfragmentv1.databinding.GrDetailsItemsListBinding
import `in`.tutorial.pagingfragmentv1.view.adapter.GRDetailsItemsListAdapter
import kotlinx.coroutines.launch
import java.util.*

class GRDetails : AppCompatActivity() {
    var bindings: ActivityGrdetailsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityGrdetailsBinding.inflate(layoutInflater)
        setContentView(bindings!!.root)
        val grId = intent.getStringExtra("grId")
        val networkService = (this.application as MyApplication).networkService
        lifecycleScope.launch {
            networkService.getGRDetails(UUID.fromString(grId) )
                .run {
                    bindings?.etRegistration?.setText(this.grDetails.registrationNo)
                    bindings?.etSender?.setText(this.grDetails.senderName)
                    bindings?.etDate?.setText(this.grDetails.date)
                    bindings?.etSupervisor?.setText(this.grDetails.supervisorName)
                    bindings?.etCustomer?.setText(this.grDetails.customerName)
                    bindings?.etGrno?.setText(this.grDetails.grNo)
                    val grAdapter = GRDetailsItemsListAdapter(this.grDetails.trl,
                        this@GRDetails)
                    bindings?.rvGrDetailsItemsList?.apply {
                        layoutManager = LinearLayoutManager(this@GRDetails)
                        adapter = grAdapter
                    }
                }
        }

    }
}