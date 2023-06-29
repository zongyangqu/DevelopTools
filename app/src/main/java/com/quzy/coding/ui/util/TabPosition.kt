package com.quzy.coding.ui.util

/**
 * 会员码页面tabIndex
 * tabIndex之所以从100开始,而不从0开始，是因为BottomNavigationBar对index=0的tab选中效果有特殊的处理
 * 而这里不需要特殊处理
 */
class TabPosition {
    companion object {
        const val TAB_STORE_CENTER = 100
        const val TAB_WELFARE_CENTER = 101
    }
}
