package com.jason.sctest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_result_list.*


class ResultListActivity : AppCompatActivity() {

    private var list = listOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_list)
        initData()
    }

    private fun initData() {
        if (intent !== null) {
            val result = intent.getStringExtra("result")
            if (result != null && result.isNotEmpty()) {
                list = result.split(",")
                list = list.subList(0, list.size - 1)
            }
        }

//        val listJson: String = SPUtils.getParam(this, "result", "") as String
//        if (listJson.isNotEmpty()) {
//            list = listJson.split(",")
//        }
        initView()
    }

    private fun initView() {
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager

        val adapter = RecyclerAdapter(this, list)
        recyclerView.adapter = adapter
    }


}