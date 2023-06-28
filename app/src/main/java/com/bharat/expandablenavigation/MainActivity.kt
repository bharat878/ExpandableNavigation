package com.bharat.expandablenavigation

import android.os.Bundle
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.bharat.expandablenavigation.utils.Utils
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
        listDataHeader = ArrayList()
        listDataChild = HashMap()

        listDataHeader = Utils.getGroupList()
        listDataChild = Utils.getChildList(listDataHeader)
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

    private fun observeData() {
        viewModel.toolbarText.observe(this) {
            toolbar.title = it

            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

}