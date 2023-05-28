package com.jason.sctest

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.jason.sctest.R
import com.jason.sctest.RecyclerAdapter.NormalHolder
import android.widget.TextView
import android.widget.Toast
import java.util.ArrayList

class RecyclerAdapter(private val mContext: Context, private val mDatas: List<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false)
        return NormalHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val normalHolder = holder as NormalHolder
        normalHolder.mTV.text = mDatas[position]
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }

    inner class NormalHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTV: TextView

        init {
            mTV = itemView.findViewById(R.id.item_tv)
            mTV.setOnClickListener { Toast.makeText(mContext, mTV.text, Toast.LENGTH_SHORT).show() }
        }
    }
}