package `in`.tutorial.pagingfragmentv1.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.navigation.NavigationView
import `in`.tutorial.pagingfragmentv1.R
import `in`.tutorial.pagingfragmentv1.data.remote.Endpoint
import `in`.tutorial.pagingfragmentv1.databinding.ActivityMainBinding
import `in`.tutorial.pagingfragmentv1.view.fragment.DispatchPagingListFragmentDirections
import `in`.tutorial.pagingfragmentv1.view.fragment.FilterFragmentDirections
import `in`.tutorial.pagingfragmentv1.view.fragment.GRPagingListFragmentDirections
import `in`.tutorial.pagingfragmentv1.view.fragment.InvoicePagingListFragmentDirections
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var navController: NavController
    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding?.navView?.setNavigationItemSelectedListener(this)
        setContentView(binding!!.root)
        navController = Navigation.findNavController(this, R.id.fragment)
        setupActionBar()
    }

    private fun setupActionBar() {
        setSupportActionBar(binding?.appBarMain?.toolbarMainActivity)
        binding?.appBarMain?.toolbarMainActivity?.setNavigationIcon(R.drawable.ic_action_nav_menu)
        binding?.appBarMain?.toolbarMainActivity?.setNavigationOnClickListener {
            toggleDrawer()
        }
    }
    private fun toggleDrawer(){
        if(binding?.drawerLayout?.isDrawerOpen(GravityCompat.START) == true){
            binding?.drawerLayout?.closeDrawer(GravityCompat.START)
        }else{
            binding?.drawerLayout?.openDrawer(GravityCompat.START)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_goods_received->{
                this.navController.navigate(R.id.GRPagingListFragment)
                this.navController.navigate(
                    GRPagingListFragmentDirections.actionPagingListFragmentToFilterFragment()
                )
                var calTo = Calendar.getInstance()
                val myFormat = "yyyy-MM-dd"
                val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
                val calToDate = sdf.format(calTo.time).toString()
                this.navController.navigate(
                    FilterFragmentDirections
                        .actionFilterFragmentToGRPagingListFragment
                            ("","2020-02-01",calToDate)
                )
            }
            R.id.nav_dispatch->{
                this.navController.navigate(R.id.dispatchPagingListFragment)
                this.navController.navigate(
                    DispatchPagingListFragmentDirections.actionDispatchFiltersFragmentToFilterFragment()
                )
                var calTo = Calendar.getInstance()
                val myFormat = "yyyy-MM-dd"
                val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
                val calToDate = sdf.format(calTo.time).toString()
                this.navController.navigate(
                    FilterFragmentDirections
                        .actionFilterFragmentToDispatchPagingListFragment
                            ("","2020-02-01",calToDate)
                )
            }
            R.id.nav_invoice->{
                this.navController.navigate(R.id.invoicePagingListFragment)
                this.navController.navigate(
                    InvoicePagingListFragmentDirections.actionInvoicePagingListFragmentToFilterFragment()
                )
                var calTo = Calendar.getInstance()
                val myFormat = "yyyy-MM-dd"
                val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
                val calToDate = sdf.format(calTo.time).toString()
                this.navController.navigate(
                    FilterFragmentDirections
                        .actionFilterFragmentToInvoicePagingListFragment
                            ("","2020-02-01",calToDate)
                )
            }
            R.id.nav_sign_out->{
                Endpoint.AUTH_TOKEN = ""
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }
}