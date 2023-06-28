package com.bharat.expandablenavigation

import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var toolbar: MaterialToolbar
    lateinit var viewModel: MainViewModel
    var mMenuAdapter: ExpandableListAdapter? = null
    private lateinit var expandableList: ExpandableListView
    private lateinit var listDataHeader: List<ExpandedMenuModel>
    var listDataChild: HashMap<ExpandedMenuModel, List<String>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        drawerLayout = findViewById(R.id.my_drawer_layout)
        toolbar = findViewById(R.id.topAppBar)
        var navigationView = findViewById<NavigationView>(R.id.navigationView)

        toolbar.setNavigationOnClickListener {
            drawerLayout.open()
        }


        navigationView.setNavigationItemSelectedListener {

            when(it.itemId) {
                R.id.nav_logout -> viewModel.setToolBarText("hello")
            }

            it.isChecked = true
            drawerLayout.close()
            true
        }

        observeData()


        expandableList = findViewById(R.id.expandableListView)

        prepareList()
        mMenuAdapter = ExpandableListAdapter(
            this,
            listDataHeader, listDataChild!!, expandableList
        )
        expandableList.setAdapter(mMenuAdapter)

        expandableList.setOnChildClickListener { p0, p1, p2, p3, p4 -> false }
        expandableList.setOnGroupClickListener { p0, p1, p2, p3 -> false }

    }

    private fun prepareList() {
        listDataHeader = ArrayList()
        listDataChild = HashMap()

        val item1 = ExpandedMenuModel()
        item1.iconName = "heading1"
        item1.iconImage = android.R.drawable.ic_delete
        // Adding data header
        // Adding data header
        (listDataHeader as ArrayList<ExpandedMenuModel>).add(item1)

        val item2 = ExpandedMenuModel()
        item2.iconName = ("heading2")
        item2.iconImage = (android.R.drawable.ic_delete)
        (listDataHeader as ArrayList<ExpandedMenuModel>).add(item2)

        val item3 = ExpandedMenuModel()
        item3.iconName = ("heading3")
        item3.iconImage = (android.R.drawable.ic_delete)
        (listDataHeader as ArrayList<ExpandedMenuModel>).add(item3)

        // Adding child data

        // Adding child data
        val heading1: MutableList<String> = ArrayList()
        heading1.add("Submenu of item 1")

        val heading2: MutableList<String> = ArrayList()
        heading2.add("Submenu of item 2")
        heading2.add("Submenu of item 2")
        heading2.add("Submenu of item 2")

        listDataChild!![(listDataHeader as ArrayList<ExpandedMenuModel>)[0]] = heading1 // Header, Child data

        listDataChild!![(listDataHeader as ArrayList<ExpandedMenuModel>)[1]] = heading2
    }

    private fun observeData() {
        viewModel.toolbarText.observe(this) {
            toolbar.title = it
        }
    }

}