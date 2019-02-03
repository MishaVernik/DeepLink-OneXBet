package com.project.onex.holder


import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

import com.project.onex.R
import com.project.onex.models.Response


class MatchHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var tvSportInfo: TextView
    lateinit var tvMatchInfo: TextView
    lateinit var tvTimeInfo: TextView

    init {
        initialize(itemView)
    }

    private fun initialize(rootView: View) {
        tvSportInfo = rootView.findViewById(R.id.tvSportInfo)
        tvMatchInfo = rootView.findViewById(R.id.tvMatchInfo)
        tvTimeInfo = rootView.findViewById(R.id.tvTimeInfo)
    }

    fun bind(response: Response) {
        tvSportInfo.text = response.s + " | " + response.a + " | " + response.c
        tvMatchInfo.text = response.matchInfo
        tvTimeInfo.text = response.data
    }

}
