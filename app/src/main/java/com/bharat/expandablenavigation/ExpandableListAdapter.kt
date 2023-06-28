package com.bharat.expandablenavigation

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView

class ExpandableListAdapter(
    private val mContext: Context,
    private val mListDataHeader: List<ExpandedMenuModel>,
    private val mListDataChild: HashMap<ExpandedMenuModel, List<String>>,
    private val expandableListView: ExpandableListView
) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        val i = mListDataHeader.size
        Log.d("GROUPCOUNT", i.toString())
        return mListDataHeader.size
    }

    override fun getChildrenCount(p0: Int): Int {
        var childCount = 0
        if (p0 < 2) {
            childCount = mListDataChild[mListDataHeader[p0]]?.size!!
        }
        return childCount
    }

    override fun getGroup(p0: Int): Any {
        return this.mListDataHeader[p0]
    }

    override fun getChild(p0: Int, p1: Int): Any {
        Log.d("CHILD", mListDataChild[this.mListDataHeader[p0]]?.get(p1).toString())
        return this.mListDataChild[this.mListDataHeader[p0]]?.get(p1)!!
    }

    override fun getGroupId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        return p1.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        p1: Boolean,
        convertView: View?,
        p3: ViewGroup?
    ): View {
        val headerTitle = getGroup(groupPosition) as ExpandedMenuModel
        val view = convertView ?: LayoutInflater.from(mContext).inflate(R.layout.listheader, null)

        val lblListHeader = view?.findViewById<TextView>(R.id.submenu)
        lblListHeader?.setTypeface(null, Typeface.BOLD)
        lblListHeader?.text = headerTitle.iconName
        view?.findViewById<ImageView>(R.id.iconimage)?.setImageResource(headerTitle.iconImage)
        return view!!
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        p2: Boolean,
        convertView: View?,
        p4: ViewGroup?
    ): View {
        val childText = getChild(groupPosition, childPosition) as String
        val view = convertView ?: LayoutInflater.from(mContext).inflate(R.layout.list_submenu, null)

        view?.findViewById<TextView>(R.id.submenu)?.text = childText
        return view!!
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }
}