package com.example.videotrimcompress

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import java.io.File

class VideoUtil(private val context: Context) {
    private var player: ExoPlayer? = null
    private var metadataRetriever: MediaMetadataRetriever? = null

    init {
        player = ExoPlayer.Builder(context).build()
        metadataRetriever = MediaMetadataRetriever()
    }

    fun createFolderIfNotExists(folderPath: String): File {
        val folder = File(folderPath)
        if (!folder.exists()) {
            folder.mkdirs()
        }
        return folder
    }

    fun getVideoDuration(videoUri: Uri): Long {
        return try {
            metadataRetriever?.setDataSource(context, videoUri)
            metadataRetriever?.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toLong() ?: 0L
        } catch (e: Exception) {
            e.printStackTrace()
            0L
        }
    }

    fun getVideoBitrate(videoUri: Uri): Int {
        return try {
            metadataRetriever?.setDataSource(context, videoUri)
            metadataRetriever?.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE)?.toInt() ?: 0
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }

    fun getVideoResolution(videoUri: Uri): Pair<Int, Int> {
        return try {
            metadataRetriever?.setDataSource(context, videoUri)
            val width = metadataRetriever?.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH)?.toInt() ?: 0
            val height = metadataRetriever?.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT)?.toInt() ?: 0
            Pair(width, height)
        } catch (e: Exception) {
            e.printStackTrace()
            Pair(0, 0)
        }
    }

    fun getVideoFrame(videoUri: Uri, timeUs: Long): Bitmap? {
        return try {
            metadataRetriever?.setDataSource(context, videoUri)
            metadataRetriever?.getFrameAtTime(timeUs, MediaMetadataRetriever.OPTION_CLOSEST_SYNC)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun release() {
        try {
            player?.release()
            metadataRetriever?.release()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
} 