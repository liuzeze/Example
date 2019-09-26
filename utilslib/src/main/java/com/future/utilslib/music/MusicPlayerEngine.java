package com.future.utilslib.music;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.PowerManager;
import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;


/**
 * Created by D22434 on 2018/1/16.
 */

public class MusicPlayerEngine implements MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnPreparedListener {


    private final WeakReference<Context> mService;

    private MediaPlayer mCurrentMediaPlayer = new MediaPlayer();

    //是否已经初始化
    private boolean mIsInitialized = false;
    //是否已经初始化
    private boolean mIsPrepared = false;
    private MusicPlayerListener onMusicPlayerListener;

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

            //本地歌曲无需缓存
            if (path.startsWith("content://") || path.startsWith("/storage")) {
                player.setDataSource(mService.get(), Uri.parse(path));
            } else {
                //不缓存
                player.setDataSource(path);
            }
            player.prepareAsync();
            player.setOnPreparedListener(this);
            player.setOnBufferingUpdateListener(this);
            player.setOnErrorListener(this);
            player.setOnCompletionListener(this);
        } catch (Exception todo) {
            todo.printStackTrace();
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
        Logger.e("Volume", "vol = " + vol);
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
        Logger.e("Music Server Error what: " + what + " extra: " + extra);
        switch (what) {
            case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
            case MediaPlayer.MEDIA_ERROR_UNKNOWN:

                mIsInitialized = false;
                //播放错误，需要重新释放mediaPlayer
                mCurrentMediaPlayer.release();
                mCurrentMediaPlayer = new MediaPlayer();
                mCurrentMediaPlayer.setWakeMode(mService.get(), PowerManager.PARTIAL_WAKE_LOCK);

                //  错误回调
                if (onMusicPlayerListener != null) {
                    onMusicPlayerListener.onError(mp, what, extra);
                }
                return true;
            default:
                break;
        }
        return true;
    }


    @Override
    public void onCompletion(final MediaPlayer mp) {
        Logger.e("onCompletion");
        if (mp == mCurrentMediaPlayer) {
            //  下一首
            if (onMusicPlayerListener != null) {
                onMusicPlayerListener.onCompletionNext(mp);
            }
        } else {
            // 播放完成
            if (onMusicPlayerListener != null) {
                onMusicPlayerListener.onCompletion(mp);
            }
        }
    }


    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        Logger.e("onBufferingUpdate" + percent);

        // PrepareAsync装载进程  加载百分比
        if (onMusicPlayerListener != null) {
            onMusicPlayerListener.onBufferingUpdate(mp, percent);
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        if (!mIsPrepared) {
            mIsPrepared = true;
            // mediaplayer准备完成
            if (onMusicPlayerListener != null) {
                onMusicPlayerListener.onPrepared(mp);
            }
        }
    }

    public void setOnMusicPlayerListener(MusicPlayerListener onMusicPlayerListener) {
        this.onMusicPlayerListener = onMusicPlayerListener;
    }
}
