package sagar.holofytest.utils

import android.annotation.SuppressLint
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerView

object VideoVariables {

    //original playerView's player
    @SuppressLint("StaticFieldLeak")
    var currentPlayer: Player? = null

    //original player
    @SuppressLint("StaticFieldLeak")
    var currentPlayerView: PlayerView? = null

}