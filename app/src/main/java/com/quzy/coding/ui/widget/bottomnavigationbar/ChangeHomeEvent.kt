package com.quzy.coding.ui.widget.bottomnavigationbar


open class ChangeHomeEvent(open var scrollhome: Int = 0, open var innerPosition: Int = 0) {
    override fun toString(): String {
        return "ChangeHomeEvent(scrollhome=$scrollhome, innerPosition=$innerPosition)"
    }
}
