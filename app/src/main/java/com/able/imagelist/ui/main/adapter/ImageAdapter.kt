package com.able.imagelist.ui.main.adapter

import android.app.Activity
import android.widget.ImageView
import com.able.imagelist.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class ImageAdapter(urls: ArrayList<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_iv, urls) {

    override fun convert(holder: BaseViewHolder, item: String) {
        val iv = holder.getView<ImageView>(R.id.iv)

        Glide.with(context).load(item)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
//            .placeholder(R.mipmap.ic_launcher)
            .transition(
                DrawableTransitionOptions().crossFade()
            )
            .into(iv)
    }

}