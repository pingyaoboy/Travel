package com.lhk.travel.travel.bean;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pyboy on 16/2/6.
 */
public class MusicInfo implements Parcelable {
    private long id;
    private String title;
    private String album;
    private int duration;
    private long size;
    private String artist;
    private String url;
    private String pinyin;

    public MusicInfo(){

    }

    public MusicInfo(long pId, String pTitle){
        id = pId;
        title = pTitle;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(album);
        dest.writeString(artist);
        dest.writeString(url);
        dest.writeInt(duration);
        dest.writeLong(size);
        dest.writeString(pinyin);
    }

    public static final Parcelable.Creator<MusicInfo>
            CREATOR = new Creator<MusicInfo>() {

        @Override
        public MusicInfo[] newArray(int size) {
            return new MusicInfo[size];
        }

        @Override
        public MusicInfo createFromParcel(Parcel source) {
            MusicInfo musicInfo = new MusicInfo();
            musicInfo.setId(source.readLong());
            musicInfo.setTitle(source.readString());
            musicInfo.setAlbum(source.readString());
            musicInfo.setArtist(source.readString());
            musicInfo.setUrl(source.readString());
            musicInfo.setDuration(source.readInt());
            musicInfo.setSize(source.readLong());
            musicInfo.setPinyin(source.readString());
            return musicInfo;
        }
    };

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public String toString() {
        return "MusicInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", album='" + album + '\'' +
                ", duration=" + duration +
                ", size=" + size +
                ", artist='" + artist + '\'' +
                ", url='" + url + '\'' +
                ", pinyin='" + pinyin + '\'' +
                '}';
    }
}
