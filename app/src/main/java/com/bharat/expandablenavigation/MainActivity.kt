package com.bharat.expandablenavigation

import android.os.Bundle
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.MaterialToolbar

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

        initUI()
        observeData()
        prepareList()
        onClick()
        mMenuAdapter = ExpandableListAdapter(
            this,
            listDataHeader, listDataChild!!, expandableList
        )
        expandableList.setAdapter(mMenuAdapter)
    }


    private fun initUI() {
        drawerLayout = findViewById(R.id.my_drawer_layout)
        toolbar = findViewById(R.id.topAppBar)
        expandableList = findViewById(R.id.expandableListView)
    }

    private fun onClick() {
        toolbar.setNavigationOnClickListener {
            drawerLayout.open()
        }

        expandableList.setOnGroupClickListener { expandableListView, view, groupPosition, p3 ->
            val hasChildPositions =
                expandableListView!!.expandableListAdapter.getChildrenCount(groupPosition) > 0

            if (!hasChildPositions) {
                val groupText =
                    expandableListView.expandableListAdapter.getGroup(groupPosition) as ExpandedMenuModel
                viewModel.setToolBarText(groupText.iconName)
            }
            false
        }

        expandableList.setOnChildClickListener { expandableListView, view, p2, p3, p4 ->
            val childText = expandableListView?.expandableListAdapter?.getChild(p2, p3)
            viewModel.setToolBarText(childText.toString())
            false
        }
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

        listDataChild!![(listDataHeader as ArrayList<ExpandedMenuModel>)[0]] =
            heading1 // Header, Child data

        listDataChild!![(listDataHeader as ArrayList<ExpandedMenuModel>)[1]] = heading2
    }

    private fun observeData() {
        viewModel.toolbarText.observe(this) {
            toolbar.title = it

            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

}