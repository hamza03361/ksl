package com.example.ksl

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Team(
    val logo: Int,       // Drawable resource ID for the team logo
    val teamName: String,
    val played: Int,
    val wins: Int,
    val losses: Int,
    val noResult: Int,
    val points: Int,
    val nrr: Float       // Net Run Rate
)

class PointsTableAdapter(private val teams: List<Team>) :
    RecyclerView.Adapter<PointsTableAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logoImageView: ImageView = itemView.findViewById(R.id.logoImageView)
        val teamNameTextView: TextView = itemView.findViewById(R.id.teamNameTextView)
        val playedTextView: TextView = itemView.findViewById(R.id.playedTextView)
        val winsTextView: TextView = itemView.findViewById(R.id.winsTextView)
        val lossesTextView: TextView = itemView.findViewById(R.id.lossesTextView)
        val noResultTextView: TextView = itemView.findViewById(R.id.noResultTextView)
        val pointsTextView: TextView = itemView.findViewById(R.id.pointsTextView)
        val nrrTextView: TextView = itemView.findViewById(R.id.nrrTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = teams[position]
        holder.logoImageView.setImageResource(team.logo)
        holder.teamNameTextView.text = team.teamName
        holder.playedTextView.text = team.played.toString()
        holder.winsTextView.text = team.wins.toString()
        holder.lossesTextView.text = team.losses.toString()
        holder.noResultTextView.text = team.noResult.toString()
        holder.pointsTextView.text = team.points.toString()
        holder.nrrTextView.text = team.nrr.toString()
    }

    override fun getItemCount() = teams.size
}
