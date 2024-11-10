package com.example.ksl

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Player(
    val name: String,
    val role: String,
    val imageResId: Int // This would be the drawable resource ID for the player's image
)

class PlayerAdapter(private val players: List<Player>) :
    RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerImageView: ImageView = itemView.findViewById(R.id.playerImageView)
        val playerNameTextView: TextView = itemView.findViewById(R.id.playerNameTextView)
        val playerRoleTextView: TextView = itemView.findViewById(R.id.playerRoleTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_player, parent, false)
        return PlayerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]
        holder.playerNameTextView.text = player.name
        holder.playerRoleTextView.text = player.role
        holder.playerImageView.setImageResource(player.imageResId) // Set the player's image
    }

    override fun getItemCount(): Int {
        return players.size
    }
}
