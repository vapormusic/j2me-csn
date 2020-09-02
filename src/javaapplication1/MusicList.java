
package javaapplication1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MusicList {

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
    @SerializedName("music_bitrate")
    @Expose
    private String musicBitrate;
    @SerializedName("music_length")
    @Expose
    private String musicLength;
    @SerializedName("music_width")
    @Expose
    private String musicWidth;
    @SerializedName("music_height")
    @Expose
    private String musicHeight;
    @SerializedName("music_thumbs_time")
    @Expose
    private String musicThumbsTime;
    @SerializedName("music_downloads")
    @Expose
    private String musicDownloads;
    @SerializedName("thumbnail_url")
    @Expose
    private String thumbnailUrl;

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

    public String getMusicBitrate() {
        return musicBitrate;
    }

    public void setMusicBitrate(String musicBitrate) {
        this.musicBitrate = musicBitrate;
    }

    public String getMusicLength() {
        return musicLength;
    }

    public void setMusicLength(String musicLength) {
        this.musicLength = musicLength;
    }

    public String getMusicWidth() {
        return musicWidth;
    }

    public void setMusicWidth(String musicWidth) {
        this.musicWidth = musicWidth;
    }

    public String getMusicHeight() {
        return musicHeight;
    }

    public void setMusicHeight(String musicHeight) {
        this.musicHeight = musicHeight;
    }

    public String getMusicThumbsTime() {
        return musicThumbsTime;
    }

    public void setMusicThumbsTime(String musicThumbsTime) {
        this.musicThumbsTime = musicThumbsTime;
    }

    public String getMusicDownloads() {
        return musicDownloads;
    }

    public void setMusicDownloads(String musicDownloads) {
        this.musicDownloads = musicDownloads;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

}
