package `in`.tutorial.pagingfragmentv1.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.navigation.NavigationView
import `in`.tutorial.pagingfragmentv1.R
import `in`.tutorial.pagingfragmentv1.databinding.ActivityMainBinding
import `in`.tutorial.pagingfragmentv1.view.fragment.FilterFragmentDirections
import `in`.tutorial.pagingfragmentv1.view.fragment.PagingListFragmentDirections

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
                this.navController.navigate(R.id.filterFragment)
            }
            R.id.nav_dispatch->{
                this.navController.navigate(R.id.dispatchFiltersFragment)
            }
        }
        return true
    }
}