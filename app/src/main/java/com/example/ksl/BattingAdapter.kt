package com.example.ksl

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Playerss(
    val name: String,
    val dismissal: String,
    val matches: Int,
    val runs: Int,
    val average: Float,
    val playerImage: Int // Resource ID for the player's image
)

class PlayersAdapter(private val players: List<Playerss>) : RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder>() {

    // ViewHolder class to hold the views
    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerName: TextView = itemView.findViewById(R.id.playername)
        val dismissal: TextView = itemView.findViewById(R.id.dismissal)
        val matches: TextView = itemView.findViewById(R.id.matches)
        val runs: TextView = itemView.findViewById(R.id.runs)
        val average: TextView = itemView.findViewById(R.id.average)
        val playerPic: ImageView = itemView.findViewById(R.id.playerpic)
    }

    // Inflate the item layout and create the ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_betting, parent, false)
        return PlayerViewHolder(view)
    }

    // Bind data to the views
    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]
        holder.playerName.text = player.name
        holder.dismissal.text = player.dismissal
        holder.matches.text = player.matches.toString()
        holder.runs.text = player.runs.toString()
        holder.average.text = player.average.toString()
        holder.playerPic.setImageResource(player.playerImage)
    }

    // Return the total number of items
    override fun getItemCount(): Int {
        return players.size
    }
}



