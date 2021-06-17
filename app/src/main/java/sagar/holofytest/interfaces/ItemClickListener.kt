package sagar.holofytest.interfaces

import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import sagar.holofytest.model.VideoModel

interface ItemClickListener {

    fun onItemClick(pos: Int, item: VideoModel, playerView: PlayerView, cvPlayerView: CardView, tvTitle: TextView)

}