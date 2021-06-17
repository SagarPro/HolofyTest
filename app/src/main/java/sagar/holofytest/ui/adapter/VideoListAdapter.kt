package sagar.holofytest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import sagar.holofytest.R
import sagar.holofytest.interfaces.ItemClickListener
import sagar.holofytest.model.VideoModel
import sagar.holofytest.utils.VideoVariables

class VideoListAdapter(private val context: Context, private val itemClickListener: ItemClickListener): RecyclerView.Adapter<VideoListAdapter.VideoListHolder>() {

    val videoList = ArrayList<VideoModel>()

    fun addVideos(videos: List<VideoModel>){
        videoList.clear()
        videoList.addAll(videos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_list_item, parent, false)
        return VideoListHolder(view)
    }

    override fun onBindViewHolder(holder: VideoListHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    inner class VideoListHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val playerView: PlayerView = itemView.findViewById(R.id.playerView)

        fun onBind(position: Int){

            val exoPlayer = SimpleExoPlayer.Builder(context).build()
            playerView.player = exoPlayer

            val mediaItem = MediaItem.fromUri(videoList[position].url!!)
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.play()
            exoPlayer.volume = 0f
            exoPlayer.repeatMode = SimpleExoPlayer.REPEAT_MODE_ONE

            ViewCompat.setTransitionName(playerView, videoList[position].title)

            itemView.setOnClickListener {
                itemClickListener.onItemClick(position, videoList[position], playerView, mediaItem)
            }

        }

    }

}