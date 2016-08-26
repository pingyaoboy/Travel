package com.lhk.travel.travel;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.IOException;

/**
 * Created by pyboy on 15/11/1.
 */
public class MusicPlayer extends Activity {
    /** Called when the activity is first created. */
//    private LyricView lyricView;
    private MediaPlayer mediaPlayer;
    private Button button;
    private SeekBar seekBar;
    private String mp3Path;
    private int INTERVAL=45;//歌词每行的间隔
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.music_player_activity);
        mp3Path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/LyricSync/1.mp3";
//        lyricView = (LyricView) findViewById(R.id.mylrc);
        mediaPlayer = new MediaPlayer();
// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ResetMusic(mp3Path);
//        SerchLrc();
//        lyricView.SetTextSize();
        button = (Button) findViewById(R.id.button);
        button.setText("播放");
        seekBar = (SeekBar) findViewById(R.id.seekbarmusic);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
// TODO Auto-generated method stub
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
// TODO Auto-generated method stub
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
// TODO Auto-generated method stub
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
//                    lyricView.setOffsetY(220 - lyricView.SelectIndex(progress)
//                            * (lyricView.getSIZEWORD() + INTERVAL-1));
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    button.setText("播放");
                    mediaPlayer.pause();
                } else {
                    button.setText("暂停");
                    mediaPlayer.start();
//                    lyricView.setOffsetY(220 - lyricView.SelectIndex(mediaPlayer.getCurrentPosition())
//                            * (lyricView.getSIZEWORD() + INTERVAL-1));
                }
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                ResetMusic(mp3Path);
//                lyricView.SetTextSize();
//                lyricView.setOffsetY(200);
                mediaPlayer.start();
            }
        });
        seekBar.setMax(mediaPlayer.getDuration());
        new Thread(new runable()).start();
    }

    /**
     * 歌词
     */
//    public void SerchLrc() {
//        String lrc = mp3Path;
//        lrc = lrc.substring(0, lrc.length() - 4).trim() + ".lrc".trim();
//        LyricView.read(lrc);
//        lyricView.SetTextSize();
//        lyricView.setOffsetY(350);
//    }

    /**
     * 初始化歌曲
     */
    public void ResetMusic(String path) {
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(mp3Path);
            mediaPlayer.prepare();
        } catch (IllegalArgumentException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    class runable implements Runnable {
        @Override
        public void run() {
// TODO Auto-generated method stub
            while (true) {
                try {
                    Thread.sleep(100);
                    if (mediaPlayer.isPlaying()) {
//                        lyricView.setOffsetY(lyricView.getOffsetY() - lyricView.SpeedLrc());
//                        lyricView.SelectIndex(mediaPlayer.getCurrentPosition());
                        seekBar.setProgress(mediaPlayer.getCurrentPosition());
                        mHandler.post(mUpdateResults);
                    }
                } catch (InterruptedException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    Handler mHandler = new Handler();
    Runnable mUpdateResults = new Runnable() {
        public void run() {
//            lyricView.invalidate(); // 更新视图
        }
    };
}
