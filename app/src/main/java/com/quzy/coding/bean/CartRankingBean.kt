package com.quzy.coding.bean

import com.quzy.coding.bean.model.CartItemType

/**
 * CreateDate:2023-06-30 15:36
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
class CartRankingBean(val rankingData: RankListDataBean) : CartBaseBean(CartItemType.ITEM_VIEW_TYPE_CART_RANKING_ENTRANCE)