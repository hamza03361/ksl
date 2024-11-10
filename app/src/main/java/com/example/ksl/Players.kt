package com.example.ksl

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

data class Squad(
    val batters: List<String>,
    val bowlers: List<String>,
    val wicketkeepers: List<String>,
    val allrounders: List<String>,
    val extras: List<String>
)
class Players : ComponentActivity() {


    private lateinit var gestureDetector: GestureDetector


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.squadplayers)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor =
                getColor(R.color.secondthemecolor) // Change to your color resource
        }
        val backbutton = findViewById<ImageView>(R.id.backImageView)
        backbutton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        // Initialize the BottomNavigationView and set the selected item to "Matches"
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Set the selected item to the "Matches" menu item
        bottomNavigationView.selectedItemId = R.id.navigation_table

        val rootView = findViewById<View>(R.id.root_view) // Ensure root_view ID is set in XML
        gestureDetector = GestureDetector(this, GestureListener())
        rootView.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }
        val teamName = intent.getStringExtra("TEAM_NAME")

        // Use the teamName to determine which squad to display
        displaySquad(teamName)

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
    }

    private fun navigateToActivity(destination: Class<*>) {
        val intent = Intent(this, destination)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@Players, MainActivity::class.java)
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
    private fun displaySquad(teamName: String?) {
        val squad = when (teamName) {
            "Team1" -> Squad(
                batters = listOf("Batter1", "Batter2", "Batter3", "Batter4"),
                bowlers = listOf("Bowler1", "Bowler2", "Bowler3", "Bowler4"),
                wicketkeepers = listOf("Wicketkeeper1", "Wicketkeeper2"),
                allrounders = listOf("AllRounder1", "AllRounder2", "AllRounder3"),
                extras = listOf("Extra1", "Extra2", "Extra3", "Extra4")
            )
            // Add more teams as needed
            else -> null
        }

        squad?.let {
            val battersAdapter = PlayerAdapter(it.batters.map { batter -> Player(batter, "Batter", R.drawable.team2) })
            val bowlersAdapter = PlayerAdapter(it.bowlers.map { bowler -> Player(bowler, "Bowler", R.drawable.team1) })
            val wicketkeepersAdapter = PlayerAdapter(it.wicketkeepers.map { wk -> Player(wk, "Wicketkeeper", R.drawable.team1) })
            val allroundersAdapter = PlayerAdapter(it.allrounders.map { ar -> Player(ar, "All-Rounder", R.drawable.team2) })
            val extrasAdapter = PlayerAdapter(it.extras.map { extra -> Player(extra, "Extra", R.drawable.team2) })

            findViewById<RecyclerView>(R.id.battersRecyclerView).apply {
                layoutManager = LinearLayoutManager(this@Players)
                adapter = battersAdapter
            }

            findViewById<RecyclerView>(R.id.bowlersRecyclerView).apply {
                layoutManager = LinearLayoutManager(this@Players)
                adapter = bowlersAdapter
            }

            findViewById<RecyclerView>(R.id.wicketkeepersRecyclerView).apply {
                layoutManager = LinearLayoutManager(this@Players)
                adapter = wicketkeepersAdapter
            }

            findViewById<RecyclerView>(R.id.allroundersRecyclerView).apply {
                layoutManager = LinearLayoutManager(this@Players)
                adapter = allroundersAdapter
            }

            findViewById<RecyclerView>(R.id.extrasRecyclerView).apply {
                layoutManager = LinearLayoutManager(this@Players)
                adapter = extrasAdapter
            }
        }
    }


}