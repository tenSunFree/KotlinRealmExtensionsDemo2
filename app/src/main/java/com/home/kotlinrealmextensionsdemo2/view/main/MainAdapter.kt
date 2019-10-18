package com.home.kotlinrealmextensionsdemo2.view.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.NetworkImageView
import com.home.kotlinrealmextensionsdemo2.R
import com.home.kotlinrealmextensionsdemo2.model.bean.Photo
import com.home.kotlinrealmextensionsdemo2.model.network.volley.VolleySingleton

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var photoList: List<Photo>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_main_recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.idTextView.text = photoList!![position].id.toString()
        holder.titleTextView.text = photoList!![position].title
        holder.thumbnailCustomImageView.setDefaultImageResId(R.drawable.icon_replace)
        holder.thumbnailCustomImageView.setErrorImageResId(R.drawable.icon_error)
        holder.thumbnailCustomImageView.setImageUrl(
            photoList!![position].thumbnailUrl, VolleySingleton.imageLoader
        )
        holder.rootConstraintLayout.post {
            val layoutParams = holder.itemView.layoutParams
            layoutParams.width = holder.rootConstraintLayout.width
            layoutParams.height = holder.rootConstraintLayout.width
            holder.rootConstraintLayout.layoutParams = layoutParams
        }
    }

    override fun getItemCount(): Int {
        return photoList!!.size
    }

    fun setPostList(photoList: List<Photo>) {
        this.photoList = photoList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTextView: TextView = itemView.findViewById(R.id.text_view_id)
        val titleTextView: TextView = itemView.findViewById(R.id.text_view_title)
        val thumbnailCustomImageView: NetworkImageView =
            itemView.findViewById(R.id.custom_image_view_thumbnail)
        val rootConstraintLayout: ConstraintLayout =
            itemView.findViewById(R.id.constraint_layout_root)
    }
}