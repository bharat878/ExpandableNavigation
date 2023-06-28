package com.bharat.expandablenavigation.utils

import com.bharat.expandablenavigation.model.ExpandedMenuModel
import java.util.HashMap

object Utils {
    fun getGroupList(selector: Int): List<ExpandedMenuModel> {
      var listDataHeader: List<ExpandedMenuModel> = ArrayList()

        val item1 = ExpandedMenuModel()
        item1.iconName = Constants.ACCOUNT
        item1.iconImage = selector
        (listDataHeader as ArrayList<ExpandedMenuModel>).add(item1)

        val item2 = ExpandedMenuModel()
        item2.iconName = Constants.PAYMENT
        item2.iconImage = selector
        listDataHeader.add(item2)

        val item3 = ExpandedMenuModel()
        item3.iconName = Constants.REFERRAL
        item3.iconImage = selector
        listDataHeader.add(item3)

        val item4 = ExpandedMenuModel()
        item4.iconName = Constants.HELP
        item4.iconImage = selector
        listDataHeader.add(item4)

        val item5 = ExpandedMenuModel()
        item5.iconName = Constants.LOGOUT
        item5.iconImage = selector
        listDataHeader.add(item5)

        return listDataHeader
    }

    fun getChildList(listDataHeader: List<ExpandedMenuModel>): HashMap<ExpandedMenuModel, List<String>>? {
        var listDataChild: HashMap<ExpandedMenuModel, List<String>>? = HashMap()

        val heading1: MutableList<String> = ArrayList()
        heading1.add(Constants.PROFILE)

        val heading2: MutableList<String> = ArrayList()
        heading2.add(Constants.CARD_PAYMENT)
        heading2.add(Constants.UPI_PAYMENT)
        heading2.add(Constants.NET_BANKING)

        listDataChild!![(listDataHeader as ArrayList<ExpandedMenuModel>)[0]] = heading1
        listDataChild[listDataHeader[1]] = heading2

        return listDataChild
    }
}