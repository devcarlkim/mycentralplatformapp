package com.example.mycentralplatformapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

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

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val items = getAppList()
        val adapter = GridItemAdapter(items) { item ->
            val packageName = item.appPackageName
            val intent = packageManager.getLaunchIntentForPackage(packageName)

            if (intent != null) {
                startActivity(intent)
            } else {
                Log.d("MainActivity", "Package name not found: $packageName")
            }
        }

        val gridLayoutManager = GridLayoutManager(this, 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) 2 else 1
            }
        }

        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = adapter
    }

    private fun getAppList(): List<ShowcaseItem> {
        return listOf(
            ShowcaseItem(
                title = "Dice Roller",
                subtitle = "A simple dice game app built to test the views and models.",
                appPackageName = "com.example.diceroller",
                color = "green"
            ),
            ShowcaseItem(
                title = "Color My Views",
                subtitle = "A simple color's view app that teaches layouts and colors in android.",
                appPackageName = "com.example.colormyviews",
                color = "green"
            ),
            ShowcaseItem(
                title = "About Me",
                subtitle = "A simple about me app that teaches about textview and constraints",
                appPackageName = "com.example.aboutme",
                color = "green"
            ),
            ShowcaseItem(
                title = "Title Fragments",
                subtitle = "A simple fragments app that teaches about the fragment activities in android.",
                appPackageName = "com.example.android.titlefragments",
                color = "red"
            ),
            ShowcaseItem(
                title = "Hello World",
                subtitle = "A simple hello world app that teaches the lifecycles and views in android",
                appPackageName = "com.example.android.helloworld",
                color = "red"
            ),
            ShowcaseItem(
                title = "Trivia Game",
                subtitle = "A simple Trivia game app that teaches about the navigation and persistence of data in android.",
                appPackageName = "com.example.android.navigation",
                color = "green"
            ),
            ShowcaseItem(
                title = "Dessert Pusher",
                subtitle = "A simple game about making desserts that teaches about creating functions, activity life cycles and callback methods.",
                appPackageName = "com.example.android.dessertpusher",
                color = "red"
            ),
            ShowcaseItem(
                title = "Guess it",
                subtitle = "A simple game that teaches about timers, buttons and EditTexts.",
                appPackageName = "com.example.android.guesstheword",
                color = "green"
            ),
            ShowcaseItem(
                title = "Sleep Tracker",
                subtitle = "A simple sleep tracker app that tests the timer and recyclerview",
                appPackageName = "com.example.android.trackmysleepqualityrecyclerview",
                color = "green"
            ),
            ShowcaseItem(
                title = "Mars Real Estate",
                subtitle = "A simple app that test Internet connection using retrofit.",
                appPackageName = "com.example.android.marsrealestate",
                color = "red"
            ),
            ShowcaseItem(
                title = "Dev Bytes",
                subtitle = "A simple app that test local database using room.",
                appPackageName = "com.example.android.devbytes",
                color = "red"
            ),
            ShowcaseItem(
                title = "GDG Finder",
                subtitle = "A simple GDG Finder that tests internet connectivity using retrofit.",
                appPackageName = "com.example.android.gdgfinder",
                color = "green"
            )
        )
    }
}
