package com.quzy.coding.util.bottomnavigationbar

/**
 * CreateDate:2023/6/16 17:29
 * @author: zongyang qu
 * @Package： com.quzy.coding.util.bottomnavigationbar
 * @Description:
 */
interface OnTabSelectedListener {

    /**
     * Called when a tab enters the selected state.

     * @param position The position of the tab that was selected
     */
    fun onTabSelected(position: Int)

    /**
     * Called when a tab exits the selected state.

     * @param position The position of the tab that was unselected
     */
    fun onTabUnselected(position: Int) {
        // TODO 暂时不支持UnSelecter
    }

    /**
     * Called when a tab that is already selected is chosen again by the user. Some applications
     * may use this action to return to the top level of a category.

     * @param position The position of the tab that was reselected.
     */
    fun onTabReselected(position: Int)
}