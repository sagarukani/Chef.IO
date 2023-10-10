package com.chefio.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.chefio.R
import com.chefio.databinding.ActivityHomeBinding
import com.common.base.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController(R.id.main_fragment)
        setupSmoothBottomMenu()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bottom, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.homeFragment -> {
                showToast("Another Menu Item 1 Selected")
            }

            R.id.SearchFragment -> {
                showToast("Another Menu Item 2 Selected")
            }

            R.id.ChatFragment -> {
                showToast("Another Menu Item 3 Selected")
            }
            R.id.profileFragment -> {
                showToast("Another Menu Item 3 Selected")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //set an active fragment programmatically
    fun setSelectedItem(pos:Int){
        binding.bottomBar.setSelectedItem(pos)
    }
    //set badge indicator
    fun setBadge(pos:Int){
        binding.bottomBar.setBadge(pos)
    }
    //remove badge indicator
    fun removeBadge(pos:Int){
        binding.bottomBar.removeBadge(pos)
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun setupSmoothBottomMenu() {
        val popupMenu = PopupMenu(this, null)
        popupMenu.inflate(R.menu.menu_bottom)
        val menu = popupMenu.menu
        //binding.bottomBar.setupWithNavController(menu, navController)
        binding.bottomBar.setupWithNavController( navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}