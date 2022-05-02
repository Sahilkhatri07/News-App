package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout : DrawerLayout
    lateinit var coordinateLayout: CoordinatorLayout
    lateinit var frame: FrameLayout
    lateinit var toolbar : Toolbar
    lateinit var navigationView : NavigationView

    var previousMenuItem : MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        coordinateLayout = findViewById(R.id.coordinateLayout)
        frame = findViewById(R.id.frame)
        toolbar = findViewById(R.id.toolbar)
        navigationView = findViewById(R.id.navigationView)

        setUpToolbar()
        topHeadline()

        val actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity,drawerLayout,R.string.app_open,R.string.app_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {

            if(previousMenuItem != null) {
                previousMenuItem?.isChecked = false
            }
            it.isChecked = true
            it.isCheckable =true
            previousMenuItem = it

            when(it.itemId) {
                R.id.TopHeadline ->{
                    topHeadline()
                    drawerLayout.closeDrawers()
                }
                R.id.Entertainment ->{
                    supportFragmentManager.beginTransaction().replace(R.id.frame,EntertainmentFragment()).commit()
                    supportActionBar?.title = "Entertainment"
                    drawerLayout.closeDrawers()
                }
                R.id.Technology ->{
                    supportFragmentManager.beginTransaction().replace(R.id.frame,TechnologyFragment()).commit()
                    supportActionBar?.title = "Technology"
                    drawerLayout.closeDrawers()
                }
                R.id.Sport ->{
                    supportFragmentManager.beginTransaction().replace(R.id.frame,SportFragment()).commit()
                    supportActionBar?.title = "Sports"
                    drawerLayout.closeDrawers()
                }

            }

            return@setNavigationItemSelectedListener true
        }

    }

   fun setUpToolbar() {
       setSupportActionBar(toolbar)
       supportActionBar?.title = "NEWS"
       supportActionBar?.setHomeButtonEnabled(true)
       supportActionBar?.setDisplayHomeAsUpEnabled(true)

   }

    fun topHeadline() {
        supportFragmentManager.beginTransaction().replace(R.id.frame,TopHeadlineFragment()).commit()
        supportActionBar?.title = "Top Headline"
        navigationView.setCheckedItem(R.id.TopHeadline)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        if(id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {

        val frag = supportFragmentManager.findFragmentById(R.id.frame)

        when(frag) {
            !is TopHeadlineFragment -> topHeadline()

            else -> super.onBackPressed()
        }
    }

}