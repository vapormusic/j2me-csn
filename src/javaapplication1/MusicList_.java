
package javaapplication1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MusicList_ {

    @SerializedName("music_id")
    @Expose
    private String musicId;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("cat_level")
    @Expose
    private String catLevel;
    @SerializedName("cover_id")
    @Expose
    private String coverId;
    @SerializedName("music_title_url")
    @Expose
    private String musicTitleUrl;
    @SerializedName("music_title")
    @Expose
    private String musicTitle;
    @SerializedName("music_artist")
    @Expose
    private String musicArtist;
    @SerializedName("music_downloads")
    @Expose
    private String musicDownloads;
    @SerializedName("music_listen")
    @Expose
    private String musicListen;
    @SerializedName("music_bitrate")
    @Expose
    private String musicBitrate;
    @SerializedName("music_width")
    @Expose
    private String musicWidth;
    @SerializedName("music_height")
    @Expose
    private String musicHeight;
    @SerializedName("music_length")
    @Expose
    private String musicLength;
    @SerializedName("music_downloads_today")
    @Expose
    private String musicDownloadsToday;
    @SerializedName("music_thumbs_time")
    @Expose
    private String musicThumbsTime;
    @SerializedName("music_deleted")
    @Expose
    private String musicDeleted;
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

    public String getCoverId() {
        return coverId;
    }

    public void setCoverId(String coverId) {
        this.coverId = coverId;
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

    public String getMusicDownloads() {
        return musicDownloads;
    }

    public void setMusicDownloads(String musicDownloads) {
        this.musicDownloads = musicDownloads;
    }

    public String getMusicListen() {
        return musicListen;
    }

    public void setMusicListen(String musicListen) {
        this.musicListen = musicListen;
    }

    public String getMusicBitrate() {
        return musicBitrate;
    }

    public void setMusicBitrate(String musicBitrate) {
        this.musicBitrate = musicBitrate;
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

    public String getMusicLength() {
        return musicLength;
    }

    public void setMusicLength(String musicLength) {
        this.musicLength = musicLength;
    }

    public String getMusicDownloadsToday() {
        return musicDownloadsToday;
    }

    public void setMusicDownloadsToday(String musicDownloadsToday) {
        this.musicDownloadsToday = musicDownloadsToday;
    }

    public String getMusicThumbsTime() {
        return musicThumbsTime;
    }

    public void setMusicThumbsTime(String musicThumbsTime) {
        this.musicThumbsTime = musicThumbsTime;
    }

    public String getMusicDeleted() {
        return musicDeleted;
    }

    public void setMusicDeleted(String musicDeleted) {
        this.musicDeleted = musicDeleted;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

}
