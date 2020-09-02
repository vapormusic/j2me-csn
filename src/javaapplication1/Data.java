
package javaapplication1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("q")
    @Expose
    private String q;
    @SerializedName("music")
    @Expose
    private Music music;
    @SerializedName("top_music")
    @Expose
    private TopMusic topMusic;
    @SerializedName("music_playback")
    @Expose
    private MusicPlayback musicPlayback;
    @SerializedName("video")
    @Expose
    private Video video;
    @SerializedName("artist")
    @Expose
    private Artist artist;
    @SerializedName("album")
    @Expose
    private Album album;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public TopMusic getTopMusic() {
        return topMusic;
    }

    public void setTopMusic(TopMusic topMusic) {
        this.topMusic = topMusic;
    }

    public MusicPlayback getMusicPlayback() {
        return musicPlayback;
    }

    public void setMusicPlayback(MusicPlayback musicPlayback) {
        this.musicPlayback = musicPlayback;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

}
