package com.bharat.expandablenavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var toolbar: MaterialToolbar
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        drawerLayout = findViewById(R.id.my_drawer_layout)
        toolbar = findViewById(R.id.topAppBar)

        toolbar.setNavigationOnClickListener {
            drawerLayout.open()
        }


        findViewById<NavigationView>(R.id.navigationView).setNavigationItemSelectedListener {

            when(it.itemId) {
                R.id.nav_logout -> viewModel.setToolBarText("hello")
            }

            it.isChecked = true
            drawerLayout.close()
            true
        }

        observeData()
    }

    private fun observeData() {
        viewModel.toolbarText.observe(this) {
            toolbar.title = it
        }
    }

}