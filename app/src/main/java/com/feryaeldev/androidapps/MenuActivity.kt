package com.feryaeldev.androidapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.feryaeldev.androidapps.apps.imcapp.IMCApp
import com.feryaeldev.androidapps.apps.nameapp.NameApp

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnGoToNameApp = findViewById<Button>(R.id.btnGoToNameApp)
        val btnGoToIMCApp = findViewById<Button>(R.id.btnGoToIMCApp)

        btnGoToNameApp.setOnClickListener {
            startActivity(Intent(this, NameApp::class.java))
            finish() // To close the activity
        }

        btnGoToIMCApp.setOnClickListener {
            startActivity(Intent(this, IMCApp::class.java))
            finish()
        }
    }
}