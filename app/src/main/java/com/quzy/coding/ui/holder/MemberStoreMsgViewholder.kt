package com.quzy.coding.ui.holder

import android.graphics.ImageDecoder
import android.graphics.drawable.Animatable
import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.coding.qzy.baselibrary.utils.extend.dpOfInt
import com.quzy.coding.bean.AssetInfo
import com.quzy.coding.bean.OffineStoreInfo
import com.quzy.coding.ui.activity.RecyclerViewWaterfallComplexKotActivity
import kotlinx.android.synthetic.main.view_content_store_msg.view.*

/**
 * CreateDate:2021/10/28 11:25
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description:
 */
class MemberStoreMsgViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var loginStateValue: String? = null
    var fragment: RecyclerViewWaterfallComplexKotActivity? = null

    init {
        (itemView.layoutParams as? StaggeredGridLayoutManager.LayoutParams)?.isFullSpan = true
    }

    fun bindData(
            assetInfo: AssetInfo?,
            fragment: RecyclerViewWaterfallComplexKotActivity?
    ) {
        assetInfo?.apply {
            updateOffineShopInfo(this, shopDiscountsVo)
        }
        this.fragment = fragment

    }

    fun updateOffineShopInfo(assetInfo: AssetInfo, shopDiscountsVo: OffineStoreInfo?) {
        itemView.apply {
            shop_info_layout_container?.visibility = View.GONE
            shopDiscountsVo?.let {
                fragment?.let { it1 -> Glide.with(it1).load(shopDiscountsVo.logoUrl).into(shop_info_image) }
                if (!TextUtils.isEmpty(it.distance)) {
                    if_location_member?.visibility = View.VISIBLE
                    shop_info_shop_distance?.visibility = View.VISIBLE
                    shop_info_shop_distance.text = it.distance
                } else {
                    if_location_member?.visibility = View.GONE
                    shop_info_shop_distance?.visibility = View.GONE
                }
                shop_info_shop_name.text = it.shopName
                shop_info_layout_container?.visibility = View.VISIBLE
            }

            shop_info_layout_container.setOnClickListener {

            }
            shop_info_arrive_discount.setOnClickListener {
            }
        }
    }
}