package com.example.ksl

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Toogleactivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.toogleactivity)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = getColor(R.color.secondthemecolor) // Change to your color resource
        }

        val batsmanstats = listOf(
            BatsmanStats("K.Sangakara", "b", "J.Hazlewood", "100", "27", "5", "3", "34.89"),
            BatsmanStats("K.Sangakara", "b", "J.Hazlewood", "100", "27", "5", "3", "34.89"),
            BatsmanStats("K.Sangakara", "b", "J.Hazlewood", "100", "27", "5", "3", "34.89"),
            BatsmanStats("K.Sangakara", "b", "J.Hazlewood", "100", "27", "5", "3", "34.89"),
            BatsmanStats("K.Sangakara", "b", "J.Hazlewood", "100", "27", "5", "3", "34.89"),
            BatsmanStats("K.Sangakara", "b", "J.Hazlewood", "100", "27", "5", "3", "34.89"),
            BatsmanStats("K.Sangakara", "b", "J.Hazlewood", "100", "27", "5", "3", "34.89"),
            BatsmanStats("K.Sangakara", "b", "J.Hazlewood", "100", "27", "5", "3", "34.89"),
            BatsmanStats("K.Sangakara", "b", "J.Hazlewood", "100", "27", "5", "3", "34.89"),
            BatsmanStats("K.Sangakara", "b", "J.Hazlewood", "100", "27", "5", "3", "34.89")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.batsmanstatsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = BatsmanStatsAdapter(batsmanstats)

        val backbutton = findViewById<ImageView>(R.id.backImageView)
        backbutton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}