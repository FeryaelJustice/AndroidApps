package com.feryaeldev.androidapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.feryaeldev.androidapps.apps.imcapp.IMCApp
import com.feryaeldev.androidapps.apps.nameapp.NameApp
import com.feryaeldev.androidapps.apps.todoapp.TodoApp

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnGoToNameApp = findViewById<Button>(R.id.btnGoToNameApp)
        val btnGoToIMCApp = findViewById<Button>(R.id.btnGoToIMCApp)
        val btnGoToTODOApp = findViewById<Button>(R.id.btnGoToTODOApp)

        btnGoToNameApp.setOnClickListener {
            navigateToApp(NameApp::class.java)
            // finish() // To close the activity
        }

        btnGoToIMCApp.setOnClickListener {
            navigateToApp(IMCApp::class.java)
        }

        btnGoToTODOApp.setOnClickListener {
            navigateToApp(TodoApp::class.java)
        }
    }

    private fun navigateToApp(appName: Class<*>) {
        startActivity(Intent(this, appName))
    }
}