
package javaapplication1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackList {

    @SerializedName("music_id")
    @Expose
    private String musicId;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("cat_level")
    @Expose
    private String catLevel;
    @SerializedName("music_title_url")
    @Expose
    private String musicTitleUrl;
    @SerializedName("music_title")
    @Expose
    private String musicTitle;
    @SerializedName("music_artist")
    @Expose
    private String musicArtist;
    @SerializedName("music_track_id")
    @Expose
    private String musicTrackId;
    @SerializedName("music_shortlyric")
    @Expose
    private String musicShortlyric;
    @SerializedName("music_length")
    @Expose
    private String musicLength;

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatLevel() {
        return catLevel;
    }

    public void setCatLevel(String catLevel) {
        this.catLevel = catLevel;
    }

    public String getMusicTitleUrl() {
        return musicTitleUrl;
    }

    public void setMusicTitleUrl(String musicTitleUrl) {
        this.musicTitleUrl = musicTitleUrl;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public void setMusicTitle(String musicTitle) {
        this.musicTitle = musicTitle;
    }

    public String getMusicArtist() {
        return musicArtist;
    }

    public void setMusicArtist(String musicArtist) {
        this.musicArtist = musicArtist;
    }

    public String getMusicTrackId() {
        return musicTrackId;
    }

    public void setMusicTrackId(String musicTrackId) {
        this.musicTrackId = musicTrackId;
    }

    public String getMusicShortlyric() {
        return musicShortlyric;
    }

    public void setMusicShortlyric(String musicShortlyric) {
        this.musicShortlyric = musicShortlyric;
    }

    public String getMusicLength() {
        return musicLength;
    }

    public void setMusicLength(String musicLength) {
        this.musicLength = musicLength;
    }

}
