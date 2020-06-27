package com.benmohammad.luvmvvm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.benmohammad.luvmvvm.R
import com.benmohammad.luvmvvm.model.Museum
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_museum.view.*

class MuseumAdapter(private var museums: List<Museum>): RecyclerView.Adapter<MuseumAdapter.MViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_museum, parent, false)
        return MViewHolder(view)
    }

    override fun getItemCount(): Int {
        return museums.size
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.bind(museums[position])
    }

    fun update(data: List<Museum>) {
        museums = data
        notifyDataSetChanged()
    }

    class MViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val textViewName: TextView = itemView.textViewName
        private val imageView: ImageView = itemView.imageView

        fun bind(museum: Museum) {
            textViewName.text = museum.name
            Glide.with(imageView.context).load(museum.photo).into(imageView)
        }

    }}