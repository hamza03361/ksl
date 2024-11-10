package com.example.ksl

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class Matches : ComponentActivity() {

    private lateinit var gestureDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.matches)

        // Set the status bar color if the API level is 21 (Lollipop) or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor =
                getColor(R.color.secondthemecolor) // Change to your color resource
        }

        val matches = listOf(
            MatchesItem(
                date = "2024-08-13",
                t20MatchesText = "T20 16 of 48",
                teamAName = "Team A",
                teamAImageResId = R.drawable.team2, // Replace with your actual resource ID
                teamBName = "Team B",
                teamBImageResId = R.drawable.team3, // Replace with your actual resource ID
                startTime = "Starts at 1:30 p.m"
              //  bellResId = R.drawable.bell
            ),
            MatchesItem(
                date = "2024-07-13",
                t20MatchesText = "T20 16 of 48",
                teamAName = "Team A",
                teamAImageResId = R.drawable.team2, // Replace with your actual resource ID
                teamBName = "Team B",
                teamBImageResId = R.drawable.team3, // Replace with your actual resource ID
                startTime = "Starts at 1:30 p.m"
                //  bellResId = R.drawable.bell
            ),
            MatchesItem(
                date = "2024-08-13",
                t20MatchesText = "T20 16 of 48",
                teamAName = "Team A",
                teamAImageResId = R.drawable.team2, // Replace with your actual resource ID
                teamBName = "Team B",
                teamBImageResId = R.drawable.team3, // Replace with your actual resource ID
                startTime = "Starts at 1:30 p.m"
                //  bellResId = R.drawable.bell
            ),
            MatchesItem(
                date = "2024-08-13",
                t20MatchesText = "T20 16 of 48",
                teamAName = "Team A",
                teamAImageResId = R.drawable.team2, // Replace with your actual resource ID
                teamBName = "Team B",
                teamBImageResId = R.drawable.team3, // Replace with your actual resource ID
                startTime = "Starts at 1:30 p.m"
                //  bellResId = R.drawable.bell
            ),
            MatchesItem(
                date = "2024-08-13",
                t20MatchesText = "T20 16 of 48",
                teamAName = "Team A",
                teamAImageResId = R.drawable.team2, // Replace with your actual resource ID
                teamBName = "Team B",
                teamBImageResId = R.drawable.team3, // Replace with your actual resource ID
                startTime = "Starts at 1:30 p.m"
                //  bellResId = R.drawable.bell
            ),
        )

        val recyclerView: RecyclerView = findViewById(R.id.matchesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MatchAdapter(matches)

        // Handle back button click to navigate to MainActivity
        val backbutton = findViewById<ImageView>(R.id.backImageView)
        backbutton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val rootView = findViewById<View>(R.id.root_view) // Ensure root_view ID is set in XML
        gestureDetector = GestureDetector(this, GestureListener())
        rootView.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }

        // Initialize the BottomNavigationView and set the selected item to "Matches"
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Set the selected item to the "Matches" menu item
        bottomNavigationView.selectedItemId = R.id.navigation_matches

        // Handle navigation item selection
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    // Home menu item clicked
                    navigateToActivity(MainActivity::class.java)
                    true
                }
                R.id.navigation_matches -> {
                    // Matches menu item clicked, stay on the same activity
                    // No need to start Matches activity again
                    true
                }
                R.id.navigation_live -> {
                    // Live menu item clicked
                    navigateToActivity(Live::class.java)
                    true
                }
                R.id.navigation_statistics -> {
                    // Statistics menu item clicked
                    navigateToActivity(Statistics::class.java)
                    true
                }
                R.id.navigation_table -> {
                    // Table menu item clicked
                    navigateToActivity(Table::class.java)
                    true
                }
                else -> false
            }
        }


    }

    private fun navigateToActivity(destination: Class<*>) {
        val intent = Intent(this, destination)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@Matches, MainActivity::class.java)
        startActivity(intent)
    }

    inner class GestureListener : GestureDetector.SimpleOnGestureListener() {

        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val diffX = e2?.x?.minus(e1?.x ?: 0f) ?: 0f
            val diffY = e2?.y?.minus(e1?.y ?: 0f) ?: 0f
            return if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        // Swipe Right
                        onSwipeRight()
                    } else {
                        // Swipe Left
                        onSwipeLeft()
                    }
                    true
                } else {
                    false
                }
            } else {
                false
            }
        }
    }


    private fun onSwipeRight() {
        // Handle swipe right - Navigate to previous activity or section
        val intent = Intent(this, MainActivity::class.java) // Adjust as needed
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun onSwipeLeft() {
        // Handle swipe left - Navigate to next activity or section
        val intent = Intent(this, Live::class.java) // Adjust as needed
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}