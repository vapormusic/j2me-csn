
package javaapplication1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("music_id")
    @Expose
    private Integer musicId;
    @SerializedName("music_title")
    @Expose
    private String musicTitle;
    @SerializedName("music_artist")
    @Expose
    private String musicArtist;
    @SerializedName("music_bitrate")
    @Expose
    private Integer musicBitrate;
    @SerializedName("music_bitrate_html")
    @Expose
    private String musicBitrateHtml;
    @SerializedName("music_link")
    @Expose
    private String musicLink;
    @SerializedName("cat_id")
    @Expose
    private Integer catId;
    @SerializedName("music_listen")
    @Expose
    private Integer musicListen;
    @SerializedName("music_filename")
    @Expose
    private String musicFilename;
    @SerializedName("music_cover")
    @Expose
    private String musicCover;
    @SerializedName("music_title_url")
    @Expose
    private String musicTitleUrl;
    @SerializedName("music_downloads")
    @Expose
    private Integer musicDownloads;

    public Integer getMusicId() {
        return musicId;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
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

    public Integer getMusicBitrate() {
        return musicBitrate;
    }

    public void setMusicBitrate(Integer musicBitrate) {
        this.musicBitrate = musicBitrate;
    }

    public String getMusicBitrateHtml() {
        return musicBitrateHtml;
    }

    public void setMusicBitrateHtml(String musicBitrateHtml) {
        this.musicBitrateHtml = musicBitrateHtml;
    }

    public String getMusicLink() {
        return musicLink;
    }

    public void setMusicLink(String musicLink) {
        this.musicLink = musicLink;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getMusicListen() {
        return musicListen;
    }

    public void setMusicListen(Integer musicListen) {
        this.musicListen = musicListen;
    }

    public String getMusicFilename() {
        return musicFilename;
    }

    public void setMusicFilename(String musicFilename) {
        this.musicFilename = musicFilename;
    }

    public String getMusicCover() {
        return musicCover;
    }

    public void setMusicCover(String musicCover) {
        this.musicCover = musicCover;
    }

    public String getMusicTitleUrl() {
        return musicTitleUrl;
    }

    public void setMusicTitleUrl(String musicTitleUrl) {
        this.musicTitleUrl = musicTitleUrl;
    }

    public Integer getMusicDownloads() {
        return musicDownloads;
    }

    public void setMusicDownloads(Integer musicDownloads) {
        this.musicDownloads = musicDownloads;
    }

}
