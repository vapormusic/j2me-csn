
package javaapplication1;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CsnDownload {

    @SerializedName("music_info")
    @Expose
    private MusicInfo musicInfo;
    @SerializedName("track_list")
    @Expose
    private List<TrackList> trackList = null;
    @SerializedName("related")
    @Expose
    private Related related;
    @SerializedName("recent")
    @Expose
    private Recent recent;
    @SerializedName("artist")
    @Expose
    private Artist_1 artist;

    public MusicInfo getMusicInfo() {
        return musicInfo;
    }

    public void setMusicInfo(MusicInfo musicInfo) {
        this.musicInfo = musicInfo;
    }

    public List<TrackList> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<TrackList> trackList) {
        this.trackList = trackList;
    }

    public Related getRelated() {
        return related;
    }

    public void setRelated(Related related) {
        this.related = related;
    }

    public Recent getRecent() {
        return recent;
    }

    public void setRecent(Recent recent) {
        this.recent = recent;
    }

    public Artist_1 getArtist() {
        return artist;
    }

    public void setArtist(Artist_1 artist) {
        this.artist = artist;
    }

}
