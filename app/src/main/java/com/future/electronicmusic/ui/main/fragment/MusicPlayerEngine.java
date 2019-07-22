package com.future.electronicmusic.ui.main.fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.PowerManager;

import java.lang.ref.WeakReference;

/**
 * Created by D22434 on 2018/1/16.
 */

public class MusicPlayerEngine implements MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnPreparedListener {

    private String TAG = "MusicPlayerEngine";

    private final WeakReference<Context> mService;

    private MediaPlayer mCurrentMediaPlayer = new MediaPlayer();


    //是否已经初始化
    private boolean mIsInitialized = false;
    //是否已经初始化
    private boolean mIsPrepared = false;

    MusicPlayerEngine(final Context service) {
        mService = new WeakReference<>(service);
        mCurrentMediaPlayer.setWakeMode(mService.get(), PowerManager.PARTIAL_WAKE_LOCK);
    }

    public void setDataSource(final String path) {
        mIsInitialized = setDataSourceImpl(mCurrentMediaPlayer, path);
    }

    private boolean setDataSourceImpl(final MediaPlayer player, final String path) {
        if (path == null) return false;
        try {
            if (player.isPlaying()) player.stop();
            mIsPrepared = false;
            player.reset();
            if (path.startsWith("content://")) {
                player.setDataSource(mService.get(), Uri.parse(path));
            } else {
                player.setDataSource(path);
            }
            player.prepareAsync();
            player.setOnPreparedListener(this);
            player.setOnBufferingUpdateListener(this);
            player.setOnErrorListener(this);
            player.setOnCompletionListener(this);
        } catch (Exception todo) {
            return false;
        }
        return true;
    }



    public boolean isInitialized() {
        return mIsInitialized;
    }

    public boolean isPrepared() {
        return mIsPrepared;
    }

    public void start() {
        mCurrentMediaPlayer.start();
    }


    public void stop() {
        try {
            mCurrentMediaPlayer.reset();
            mIsInitialized = false;
            mIsPrepared = false;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }


    public void release() {
        mCurrentMediaPlayer.release();
    }


    public void pause() {
        mCurrentMediaPlayer.pause();
    }

    public boolean isPlaying() {
        return mCurrentMediaPlayer.isPlaying();
    }


    /**
     * getDuration 只能在prepared之后才能调用，不然会报-38错误
     *
     * @return
     */
    public long duration() {
        if (mIsPrepared) {
            return mCurrentMediaPlayer.getDuration();
        } else return 0;
    }


    public long position() {
        try {
            return mCurrentMediaPlayer.getCurrentPosition();
        } catch (IllegalStateException e) {
            return -1;
        }
    }

    public void seek(final long whereto) {
        mCurrentMediaPlayer.seekTo((int) whereto);
    }


    public void setVolume(final float vol) {
        try {
            mCurrentMediaPlayer.setVolume(vol, vol);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getAudioSessionId() {
        return mCurrentMediaPlayer.getAudioSessionId();
    }

    @Override
    public boolean onError(final MediaPlayer mp, final int what, final int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
            case MediaPlayer.MEDIA_ERROR_UNKNOWN:

                return true;
            default:
                break;
        }
        return true;
    }


    @Override
    public void onCompletion(final MediaPlayer mp) {

    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();

    }


}
