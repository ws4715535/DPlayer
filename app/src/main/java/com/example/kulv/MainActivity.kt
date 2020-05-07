package com.example.kulv

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kulv.ui.home.HomeFragment
import com.example.kulv.ui.personal.PersonalFragment
import com.example.kulv.ui.player.PlayerFragment
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient

class MainActivity : AppCompatActivity() {

    private val home by lazy { HomeFragment() }
    private val player by lazy { PlayerFragment() }
    private val profile by lazy { PersonalFragment() }
    private var currentItemId = R.id.navigation_home
    private var currentFragment: Fragment = home
    lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
        nav_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        supportFragmentManager.beginTransaction().replace(R.id.container, home).commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item->
        if (item.itemId == currentItemId) return@OnNavigationItemSelectedListener false
        currentItemId = item.itemId
        when (item.itemId) {
            R.id.navigation_home -> {
                switchFragment(home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_player -> {
                switchFragment(player)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                switchFragment(profile)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun switchFragment(targetFragment: Fragment) {
        if (!targetFragment.isAdded) {
            println("un add")
            supportFragmentManager.beginTransaction()
                .hide(currentFragment)
                .add(R.id.container, targetFragment)
                .commit()
        } else {
            println("add")
            supportFragmentManager.beginTransaction()
                .hide(currentFragment)
                .show(targetFragment)
                .commit()
        }
        currentFragment = targetFragment
    }
}
