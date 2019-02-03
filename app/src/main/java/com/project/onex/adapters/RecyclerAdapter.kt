package com.project.onex.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup


import com.project.onex.R
import com.project.onex.holder.MatchHolder
import com.project.onex.models.Response

class RecyclerAdapter(private val info: List<Response>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        val rootView = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item, viewGroup, false)
        return MatchHolder(rootView)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        val holder = viewHolder as MatchHolder
        holder.bind(info[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return info.size
    }
}