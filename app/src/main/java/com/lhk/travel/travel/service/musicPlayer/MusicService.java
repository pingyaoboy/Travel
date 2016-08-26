package com.lhk.travel.travel.service.musicPlayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.lhk.travel.travel.aidl.IMusicPlayer;

import java.io.IOException;

/**
 * Created by pyboy on 15/11/15.
 */
public class MusicService extends Service{

    private IMusicPlayer.Stub mBinder = new IMusicPlayer.Stub() {
        //==================音乐播放相关=====================

        @Override
        public void start(String songPath){
            mSongPath = songPath;
            if (mPlayer == null)
                mPlayer = new MediaPlayer();
            //重置
            mPlayer.reset();
            //
//        if (mMusicFileList != null && mMusicFileList.size()>0){
            try {
                mPlayer.setDataSource(mSongPath);
                mPlayer.setLooping(true);//循环
                mPlayer.prepare();
                mPlayer.start();
                mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
//                        next();
                    }
                });
                mPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                    @Override
                    public void onSeekComplete(MediaPlayer mp) {

                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
//        }
        }

        @Override
        public void next(String songPath){
//        currentSong = currentSong == mMusicList.size() - 1 ? 0 : currentSong + 1;
            start(mSongPath);
        }

        @Override
        public void pre(String songPath) {
//        currentSong = currentSong == 0 ? mMusicList.size() - 1 : currentSong - 1;
            start(mSongPath);
        }

        @Override
        public void pause() {
            if (mPlayer.isPlaying())
                mPlayer.pause();
            else
                mPlayer.start();
        }

        @Override
        public void stop() {
            if (mPlayer.isPlaying())
                mPlayer.stop();
        }

        @Override
        public int getLen(){
            return mPlayer.getDuration();
        }
    };

//    public class MusicBinder extends Binder implements IMusicService  {
//
//    }
//    private final IBinder mBinder = new MusicBinder();

    //多媒体对象
    private MediaPlayer mPlayer;
    //当前播放歌曲下标
//    private int currentSong;
//    //歌曲路径列表
//    private ArrayList<String> mMusicList;
//    //歌曲列表
//    private ArrayList<File> mMusicFileList;
    //sd卡路径
//    private final File MUSIC_FILE = Environment.getExternalStorageDirectory();
    private String mSongPath;

    /**
     * 初始化变量
     */
    private void initData(){
        mPlayer = new MediaPlayer();
//        mMusicList = new ArrayList<>();
//        mMusicFileList = new ArrayList<>();
//        if (MUSIC_FILE.listFiles(new MusicFilter()).length > 0){
//            for (File file:MUSIC_FILE.listFiles(new MusicFilter())){
//                mMusicList.add(file.getAbsolutePath());
//                mMusicFileList.add(file);
//            }
//        }
    }

    public void ChangeSong(String songPath){
        mSongPath = songPath;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

//    运行中的程序，如果内存紧张，会在 onTrimMemory(int level) 回调方法中接收到以下级别的信号：
//
//    TRIM_MEMORY_RUNNING_MODERATE：系统可用内存较低，正在杀掉 LRU缓存中的进程。你的进程正在运行，没有被杀掉的危险。
//
//    TRIM_MEMORY_RUNNING_LOW：系统可用内存更加紧张，程序虽然暂没有被杀死的危险，但是应该尽量释放一些资源，以提升系统的性能（这也会直接影响你程序的性能）。
//
//    TRIM_MEMORY_RUNNING_CRITICAL：系统内存极度紧张，而LRU缓存中的大部分进程已被杀死，如果仍然无法获得足够的资源的话，接下来会清理掉 LRU 中的所有进程，并且开始杀死一些系统通常会保留的进程，比如后台运行的服务等。
//
//    当程序未在运行，保留在 LRU 缓存中时， onTrimMemory(int level) 中会返回以下级别的信号：
//
//    TRIM_MEMORY_BACKGROUND：系统可用内存低，而你的程序处在 LRU的顶端，因此暂时不会被杀死，但是此时应释放一些程序再次打开时比较容易恢复的 UI 资源。
//
//    TRIM_MEMORY_MODERATE：系统可用内存低，程序处于 LRU的中部位置，如果内存状态得不到缓解，程序会有被杀死的可能。
//
//    TRIM_MEMORY_COMPLETE：系统可用内存低，你的程序处于 LRU尾部，如果系统仍然无法回收足够的内存资源，你的程序将首先被杀死。此时应释放无助于恢复程序状态的所有资源。
//
//    注：该 API 在版本 14 中加入。旧版本的onLowMemory() 方法，大致相当于 onTrimMemory(int level) 中接收到 TRIM_MEMORY_COMPLETE 级别的信号。
//
//    另：尽管系统主要按照 LRU 中顺序来杀进程，不过系统也会考虑程序占用的内存多少，那些占用内存高的进程有更高的可能性会被首先杀死。
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_RUNNING_LOW){
//            mMusicFileList.clear();
        }
    }


}
