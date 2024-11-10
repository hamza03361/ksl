package com.example.ksl

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

data class MatchesItem(
    val date: String,
    val t20MatchesText: String,
    val teamAName: String,
    val teamAImageResId: Int,
    val teamBName: String,
    val teamBImageResId: Int,
    val startTime: String
)

class MatchAdapter(private val matches: List<MatchesItem>) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView = itemView.findViewById(R.id.date)
        val t20MatchesTextView: TextView = itemView.findViewById(R.id.t20MatchesTextView)
        val teamATextView: TextView = itemView.findViewById(R.id.teamATextView)
        val teamAImageView: ImageView = itemView.findViewById(R.id.teamAImageView)
        val teamBImageView: ImageView = itemView.findViewById(R.id.teamBImageView)
        val teamBTextView: TextView = itemView.findViewById(R.id.teamBTextView)
        val timeTextView: TextView = itemView.findViewById(R.id.timetextView)
        val bellIconImageView: ImageView = itemView.findViewById(R.id.bellicon) // Assuming bell icon is for tomorrowImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.matches_item, parent, false)
        return MatchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val matchItem = matches[position]

        holder.dateTextView.text = matchItem.date
        holder.t20MatchesTextView.text = matchItem.t20MatchesText
        holder.teamATextView.text = matchItem.teamAName
        holder.teamAImageView.setImageResource(matchItem.teamAImageResId)
        holder.teamBImageView.setImageResource(matchItem.teamBImageResId)
        holder.teamBTextView.text = matchItem.teamBName
        holder.timeTextView.text = matchItem.startTime

        // Check if the match is today
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        if (matchItem.date == today) {
            holder.bellIconImageView.setImageResource(R.drawable.filledbell) // Replace with your filled icon resource ID
        } else {
            holder.bellIconImageView.setImageResource(R.drawable.bell) // Replace with your unfilled icon resource ID
        }
    }

    override fun getItemCount(): Int = matches.size
}
