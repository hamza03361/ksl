package com.example.ksl

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class highestscore(
    val name: String,
    val dismissal: String,
    val sr: Int,
    val hs: Int,
    val playerImage: Int // Resource ID for the player's image
)

class highestscoreAdapter(private val players: List<highestscore>) : RecyclerView.Adapter<highestscoreAdapter.PlayerViewHolder>() { // Use BettingAdapter instead of Bettingaverage

    // ViewHolder class to hold the views
    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerName: TextView = itemView.findViewById(R.id.playername)
        val dismissal: TextView = itemView.findViewById(R.id.dismissal)
        val sr: TextView = itemView.findViewById(R.id.sr)
        val hs: TextView = itemView.findViewById(R.id.hs)
        val playerPic: ImageView = itemView.findViewById(R.id.playerpic)
    }

    // Inflate the item layout and create the ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_highestscore, parent, false)
        return PlayerViewHolder(view)
    }

    // Bind data to the views
    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]
        holder.playerName.text = player.name
        holder.dismissal.text = player.dismissal
        holder.sr.text = player.sr.toString()
        holder.hs.text = player.hs.toString()
        holder.playerPic.setImageResource(player.playerImage)
    }

    // Return the total number of items
    override fun getItemCount(): Int {
        return players.size
    }
}
