package com.nearzero.ca

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btVerify.setOnClickListener{ addValues() }
    }

    private fun addValues() {
        var values = edValues.text.toString()

        if(!TextUtils.isEmpty(values) && values.length > 1) {

            var broked = values.split(",", " ", ";", ".")

            var list = ArrayList<Int>()
            for (i in 0 until broked.size) {
                if(intOrString(broked[i]))
                    list.add(broked[i].toInt())
            }

            if (list.size > 0)
                verify(list)
        }else{
            tvResult.text = resources.getString(R.string.enter_the_numbers)
        }
    }


    fun intOrString(str: String): Boolean {
        return try {
            true
        } catch (e: NumberFormatException) {
            false
        }
    }


    fun verify(list:List<Int>){
        var positives = ArrayList<Int>()

        for (i in 0 until list.size) {
            if (list.get(i) > 0)
                positives.add(list.get(i))
        }

        var negatives = ArrayList<Int>()

        for (i in 0 until list.size) {
            if (list.get(i) < 0)
                negatives.add(list.get(i))
        }

        // and lower
        var higherPositive = if(positives.size > 0) Collections.min(positives) as Int else 0
        var lowerNegative = if(negatives.size > 0) Collections.max(negatives) as Int else 0

        var diffHigher = higherPositive - 0
        var diffLower = (lowerNegative * (-1)) - 0

        if(lowerNegative == 0)
            diffLower = diffHigher + 1

        if(diffHigher <= diffLower) {
            tvResult.text = resources.getString(R.string.the_nearest_number_by_zero_is, higherPositive.toString())
        }else {
            tvResult.text = resources.getString(R.string.the_nearest_number_by_zero_is, lowerNegative.toString())
        }
    }
}
