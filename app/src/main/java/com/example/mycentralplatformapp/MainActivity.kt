package com.example.mycentralplatformapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val openTriviaAppButton: Button = findViewById(R.id.openAppBtn)

        openTriviaAppButton.setOnClickListener {
            Toast.makeText(this, "Opening Android Trivia App", Toast.LENGTH_SHORT).show()

            // 1. Specify the package name of the app you want to open
            val packageName = "com.example.android.trackmysleepqualityrecyclerview" // Change this to the actual package name

            // 2. Get the launch intent for that package
            val intent = packageManager.getLaunchIntentForPackage(packageName)

            if (intent != null) {
                // 3. Start the app
                startActivity(intent)
            } else {
                // 4. Handle the case where the app is not installed
                Toast.makeText(this, "App not installed!", Toast.LENGTH_LONG).show()
            }
        }
    }
}