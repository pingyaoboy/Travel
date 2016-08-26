package com.lhk.travel.travel.service.musicPlayer.service.filter;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by pyboy on 15/11/15.
 */
public class MusicFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String filename) {
        return filename.endsWith(".mp3");//返回mp3文件
    }
}
