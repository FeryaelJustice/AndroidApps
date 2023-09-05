package com.feryaeldev.androidapps.apps.nameapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.feryaeldev.androidapps.R

class NameResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name_result)
        val tvResult = findViewById<TextView>(R.id.tvNameResult)
        val name: String = intent.extras?.getString("EXTRA_NAME") ?: ""
        tvResult.text = "Hola $name"
    }
}