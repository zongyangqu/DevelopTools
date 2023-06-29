package com.quzy.coding.ui.widget.bottomnavigationbar

object BottomScrollManager {

    var listener: BottomScrollListener? = null
    var map = HashMap<String, BottomScrollListener>()

    fun notifyHomeBottom(key: String, bean: ChangeHomeEvent) {
        map[key]?.onChange(bean)
    }

    const val MAIN_HOME_KEY = "MAIN_HOME_KEY"
    const val SUB_MAIN_HOME_KEY = "SUB_MAIN_HOME_KEY"
    const val MAIN_ACTIVITY = "MAIN_ACTIVITY"
}