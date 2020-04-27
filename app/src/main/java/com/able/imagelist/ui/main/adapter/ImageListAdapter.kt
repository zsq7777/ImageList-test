package com.able.imagelist.ui.main.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.able.imagelist.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


class ImageListAdapter : RecyclerView.Adapter<ImageListAdapter.BaseViewHolder>() {

    private val dataList: ArrayList<String> = ArrayList()

    fun replaceAll(list: ArrayList<String>) {
        dataList.clear()
        if (list != null && list.size > 0) {
            dataList.addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return OneViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_iv, parent, false)
        );

    }

    override fun getItemCount(): Int {
        return dataList.size

    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
    }

    open class BaseViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        open fun setData(data: Any?) {}
    }

    private class OneViewHolder(view: View) : BaseViewHolder(view) {
        private var ivImage: ImageView? = null

        fun OneViewHolder(view: View) {
            ivImage = view.findViewById(R.id.iv) as ImageView
            val width =
                (ivImage!!.context as Activity).windowManager.defaultDisplay.width
            val params = ivImage!!.layoutParams
            //设置图片的相对于屏幕的宽高比
            params.width = width / 3
            params.height = (200 + Math.random() * 400).toInt()
            ivImage!!.layoutParams = params
        }

        override fun setData(data: Any?) {
            if (data != null) {
                val text = data as String
                Glide.with(itemView.context).load(text)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.ic_launcher)
                    .transition(
                        DrawableTransitionOptions().crossFade()
                    )
                    .into(this!!.ivImage!!)
            }
        }
    }
}

