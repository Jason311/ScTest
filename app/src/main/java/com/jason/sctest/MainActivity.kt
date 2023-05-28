package com.jason.sctest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    var amount: BigDecimal? = null
    var seconds = 0L

    private val stringBuilder = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAmountEditListener()
        initTimeEditListener()
        initSubmitListener()
    }

    private fun initAmountEditListener() {
        amountEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val str = p0.toString()
                if (str.isEmpty()) {
                    return
                }
                try {
                    val df = DecimalFormat("###,###,###,##0.##")
                    if (str.isNotEmpty()) {
                        amount = str.toBigDecimal().setScale(2, RoundingMode.HALF_UP)
//                            .toPlainString()
                        val amountStr = df.format(amount)
                        amountTv.text = "Amount :$amountStr"
                    }
                } catch (e: NumberFormatException) {
                    Toast.makeText(this@MainActivity, "Input error", Toast.LENGTH_SHORT).show()
                    amountEt.setText("")
                    initAmountTv()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                initAmountTv()
            }
        })
    }

    private fun initTimeEditListener() {
        timeEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val str = p0.toString()
                if (str.isEmpty()) {
                    return
                }
                try {
                    seconds = str.toLong()
                    val day = seconds / 60 / 60 / 24
                    val hour = seconds / 60 / 60
                    val minutes = seconds / 60 % 60
                    val remainingSeconds = seconds % 60
                    if (day > 0) {
                        timeTv.text = "Time: ${day}d${hour}h${minutes}m${remainingSeconds}s"
                    } else if (hour > 0) {
                        timeTv.text = "Time: ${hour}h${minutes}m${remainingSeconds}s"
                    } else if (minutes > 0) {
                        timeTv.text = "Time: ${minutes}m${remainingSeconds}s"
                    } else {
                        timeTv.text = "Time: ${remainingSeconds}s"
                    }
                } catch (e: NumberFormatException) {
                    Toast.makeText(this@MainActivity, "Input error", Toast.LENGTH_SHORT).show()
                    timeEt.setText("")
                    initTimeTv()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                initTimeTv()
            }
        })
    }

    private fun initSubmitListener() {
        submitBtn.setOnClickListener {
//            val result = amount * seconds
            val result: BigDecimal? = amount?.multiply(seconds.toBigDecimal())
            stringBuilder.insert(0, "$result,")

//            SPUtils.setParam(this@MainActivity, "result", stringBuilder.toString())
            val intent = Intent(this@MainActivity, ResultListActivity::class.java)
            intent.putExtra("result", stringBuilder.toString())
            startActivity(intent)
        }
    }


    fun initAmountTv() {
        if (TextUtils.isEmpty(amountEt.text)) {
            amountTv.text = "Amount :"
        }
    }

    fun initTimeTv() {
        if (TextUtils.isEmpty(timeEt.text)) {
            timeTv.text = "Time :"
        }
    }


}