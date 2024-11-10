package com.example.ksl

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : ComponentActivity() {


    private lateinit var infoImageViewContainer: ConstraintLayout
    private lateinit var bottomIcon: ImageView
    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var gestureDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainpage)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = getColor(R.color.secondthemecolor) // Change to your color resource
        }

        // Initialize GestureDetector and set it to the root view
        val rootView = findViewById<View>(R.id.nestedScrollView) // Ensure root_view ID is set in XML
        gestureDetector = GestureDetector(this, GestureListener())
        rootView.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }


        bottomNavigationView = findViewById(R.id.bottom_navigation)

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
                    // Table menu item clicked
                    navigateToActivity(Table::class.java)
                    true
                }
                else -> false
            }
        }

        val matches = listOf(
            Match("Team A", "Team B", "1:30 p.m", "S.A need 11 runs to win on 7 balls"),
            Match("Team C", "Team D", "2:30 p.m", "S.A need 15 runs to win on 5 balls"),
            Match("Team C", "Team D", "2:30 p.m", "S.A need 15 runs to win on 5 balls"),
            Match("Team C", "Team D", "2:30 p.m", "S.A need 15 runs to win on 5 balls"),
            Match("Team C", "Team D", "2:30 p.m", "S.A need 15 runs to win on 5 balls"),
            Match("Team C", "Team D", "2:30 p.m", "S.A need 15 runs to win on 5 balls"),
            Match("Team C", "Team D", "2:30 p.m", "S.A need 15 runs to win on 5 balls"),
            Match("Team C", "Team D", "2:30 p.m", "S.A need 15 runs to win on 5 balls"),
            Match("Team C", "Team D", "2:30 p.m", "S.A need 15 runs to win on 5 balls"),
            Match("Team C", "Team D", "2:30 p.m", "S.A need 15 runs to win on 5 balls")
            // Add more matches here
        )

        val recyclerView = findViewById<RecyclerView>(R.id.matchesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MatchesAdapter(matches)

        infoImageViewContainer = findViewById(R.id.infoImageViewContainer)
        bottomIcon = findViewById(R.id.bottomIcon)

        bottomIcon.setOnClickListener {
            toggleExpansion()
        }
    }

    private fun navigateToActivity(activityClass: Class<*>, itemId: Int) {
        val intent = Intent(this, activityClass)
        intent.putExtra("selectedItemId", itemId)
        startActivity(intent)
    }

    fun setSelectedNavigationItem(itemId: Int) {
        bottomNavigationView.selectedItemId = itemId
    }


    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_buttons, null)

        val positiveButton: Button = dialogView.findViewById(R.id.positiveButton)
        val negativeButton: Button = dialogView.findViewById(R.id.negativeButton)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Exit")
            .setMessage("Are you sure you want to exit?")
            .setView(dialogView)
            .setCancelable(true) // Allow the dialog to be cancellable by touching outside
            .create()

        positiveButton.setOnClickListener {
            finishAffinity()
            dialog.dismiss()
        }

        negativeButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }


    private fun navigateToActivity(destination: Class<*>) {
        val intent = Intent(this, destination)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    private fun toggleExpansion() {
        val intent = Intent(this, Toogleactivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top)
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
                        onSwipeRight()
                    } else {
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
        val intent = Intent(this, Table::class.java) // Adjust as needed
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun onSwipeLeft() {
        val intent = Intent(this, Matches::class.java) // Adjust as needed
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

}
