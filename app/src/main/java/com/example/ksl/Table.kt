package com.example.ksl

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class Table : ComponentActivity() {

    private lateinit var pointsTableButton: Button
    private lateinit var squadsButton: Button
    private var isSquadsButtonActive = false

    private lateinit var gestureDetector: GestureDetector
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.table)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor =
                getColor(R.color.secondthemecolor) // Change to your color resource
        }
        // Handle back button click to navigate to MainActivity
        val backbutton = findViewById<ImageView>(R.id.backImageView)
        backbutton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        pointsTableButton = findViewById(R.id.pointsTableButton)
        squadsButton = findViewById(R.id.squadsButton)

        pointsTableButton.setOnClickListener {
            toggleButtonState(pointsTableButton, squadsButton)
        }

        squadsButton.setOnClickListener {
            toggleButtonState(squadsButton, pointsTableButton)
            if (isSquadsButtonActive) {
                // Navigate to SquadsActivity
                val intent = Intent(this, SquadsActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top)
            }
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
        bottomNavigationView.selectedItemId = R.id.navigation_table

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
                    navigateToActivity(Matches::class.java)
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

                    true
                }
                else -> false
            }
        }
        // Create and populate the list of teams
        val teams = listOf(
            Team(
                logo = R.drawable.team1,  // Replace with your drawable resource ID
                teamName = "Team 1",
                played = 10,
                wins = 7,
                losses = 2,
                noResult = 1,
                points = 15,
                nrr = 0.5f
            ),
            Team(
                logo = R.drawable.team2,
                teamName = "Team 2",
                played = 10,
                wins = 6,
                losses = 3,
                noResult = 1,
                points = 13,
                nrr = 0.3f
            )
            // Add more teams as needed
        )

        // Find the RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.pointsTableRecyclerView)

        // Create the adapter with the list of teams
        val adapter = PointsTableAdapter(teams)

        // Set up the RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }



    private fun toggleButtonState(activeButton: Button, inactiveButton: Button) {
        // Set the active button
        activeButton.setBackgroundColor(getColor(R.color.activeButtonColor)) // Customize this color
        activeButton.setTextColor(getColor(R.color.inactiveButtonTextColor)) // Customize this color

        // Reset the inactive button
        inactiveButton.setBackgroundColor(getColor(R.color.inactiveButtonColor)) // Customize this color
        inactiveButton.setTextColor(getColor(R.color.activeButtonTextColor)) // Customize this color

        // Update the toggle state
        isSquadsButtonActive = activeButton.id == R.id.squadsButton
    }

    private fun navigateToActivity(destination: Class<*>) {
        val intent = Intent(this, destination)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@Table, MainActivity::class.java)
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
        val intent = Intent(this, Statistics::class.java) // Adjust as needed
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun onSwipeLeft() {
        // Handle swipe left - Navigate to next activity or section
        val intent = Intent(this, MainActivity::class.java) // Adjust as needed
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}

