package com.feryaeldev.androidapps.apps.imcapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.feryaeldev.androidapps.R
import com.feryaeldev.androidapps.apps.imcapp.IMCApp.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var tvIMCTitle: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvIMCDescription: TextView
    private lateinit var btnRecalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)
        val result = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponents()
        initUI(result)
        initListeners()
    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initUI(result: Double) {
        tvIMC.text = result.toString()
        when (result) {
            in 0.00..18.50 -> {
                tvIMCTitle.text = getString(R.string.underweight)
                tvIMCTitle.setTextColor(getColor(R.color.weight_low))
                tvIMCDescription.text = getString(R.string.underweight_description)
            }

            in 18.50..24.99 -> {
                tvIMCTitle.text = getString(R.string.normal)
                tvIMCTitle.setTextColor(getColor(R.color.weight_normal))
                tvIMCDescription.text = getString(R.string.normal_description)
            }

            in 25.00..29.99 -> {
                tvIMCTitle.text = getString(R.string.overweight)
                tvIMCTitle.setTextColor(getColor(R.color.weight_high))
                tvIMCDescription.text = getString(R.string.overweight_description)
            }

            else -> {
                if (result >= 30.00) {
                    tvIMCTitle.text = getString(R.string.obesity)
                    tvIMCTitle.setTextColor(getColor(R.color.weight_very_high))
                    tvIMCDescription.text = getString(R.string.obesity_description)
                }
            }
        }
    }

    private fun initComponents() {
        tvIMCTitle = findViewById(R.id.tvIMCTitle)
        tvIMC = findViewById(R.id.tvIMC)
        tvIMCDescription = findViewById(R.id.tvIMCDescription)
        btnRecalculate = findViewById(R.id.btnRecalculate)
    }
}