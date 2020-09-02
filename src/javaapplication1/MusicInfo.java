
package javaapplication1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MusicInfo {

    @SerializedName("music_id")
    @Expose
    private String musicId;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("cat_level")
    @Expose
    private String catLevel;
    @SerializedName("cat_sublevel")
    @Expose
    private String catSublevel;
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
    @SerializedName("music_composer")
    @Expose
    private String musicComposer;
    @SerializedName("music_album")
    @Expose
    private String musicAlbum;
    @SerializedName("music_production")
    @Expose
    private String musicProduction;
    @SerializedName("music_album_id")
    @Expose
    private String musicAlbumId;
    @SerializedName("music_year")
    @Expose
    private String musicYear;
    @SerializedName("music_listen")
    @Expose
    private String musicListen;
    @SerializedName("music_downloads")
    @Expose
    private String musicDownloads;
    @SerializedName("music_time")
    @Expose
    private String musicTime;
    @SerializedName("music_bitrate")
    @Expose
    private String musicBitrate;
    @SerializedName("music_length")
    @Expose
    private String musicLength;
    @SerializedName("music_32_filesize")
    @Expose
    private String music32Filesize;
    @SerializedName("music_filesize")
    @Expose
    private String musicFilesize;
    @SerializedName("music_320_filesize")
    @Expose
    private String music320Filesize;
    @SerializedName("music_m4a_filesize")
    @Expose
    private String musicM4aFilesize;
    @SerializedName("music_lossless_filesize")
    @Expose
    private String musicLosslessFilesize;
    @SerializedName("music_width")
    @Expose
    private String musicWidth;
    @SerializedName("music_height")
    @Expose
    private String musicHeight;
    @SerializedName("music_username")
    @Expose
    private String musicUsername;
    @SerializedName("music_lyric")
    @Expose
    private String musicLyric;
    @SerializedName("music_img_height")
    @Expose
    private String musicImgHeight;
    @SerializedName("music_img_width")
    @Expose
    private String musicImgWidth;
    @SerializedName("music_img")
    @Expose
    private String musicImg;
    @SerializedName("video_thumbnail")
    @Expose
    private String videoThumbnail;
    @SerializedName("video_preview")
    @Expose
    private String videoPreview;
    @SerializedName("file_url")
    @Expose
    private String fileUrl;
    @SerializedName("file_32_url")
    @Expose
    private String file32Url;
    @SerializedName("file_320_url")
    @Expose
    private String file320Url;
    @SerializedName("file_m4a_url")
    @Expose
    private String fileM4aUrl;
    @SerializedName("file_lossless_url")
    @Expose
    private String fileLosslessUrl;
    @SerializedName("full_url")
    @Expose
    private String fullUrl;
    @SerializedName("music_genre")
    @Expose
    private String musicGenre;

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

    public String getCatSublevel() {
        return catSublevel;
    }

    public void setCatSublevel(String catSublevel) {
        this.catSublevel = catSublevel;
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

    public String getMusicComposer() {
        return musicComposer;
    }

    public void setMusicComposer(String musicComposer) {
        this.musicComposer = musicComposer;
    }

    public String getMusicAlbum() {
        return musicAlbum;
    }

    public void setMusicAlbum(String musicAlbum) {
        this.musicAlbum = musicAlbum;
    }

    public String getMusicProduction() {
        return musicProduction;
    }

    public void setMusicProduction(String musicProduction) {
        this.musicProduction = musicProduction;
    }

    public String getMusicAlbumId() {
        return musicAlbumId;
    }

    public void setMusicAlbumId(String musicAlbumId) {
        this.musicAlbumId = musicAlbumId;
    }

    public String getMusicYear() {
        return musicYear;
    }

    public void setMusicYear(String musicYear) {
        this.musicYear = musicYear;
    }

    public String getMusicListen() {
        return musicListen;
    }

    public void setMusicListen(String musicListen) {
        this.musicListen = musicListen;
    }

    public String getMusicDownloads() {
        return musicDownloads;
    }

    public void setMusicDownloads(String musicDownloads) {
        this.musicDownloads = musicDownloads;
    }

    public String getMusicTime() {
        return musicTime;
    }

    public void setMusicTime(String musicTime) {
        this.musicTime = musicTime;
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

    public String getMusic32Filesize() {
        return music32Filesize;
    }

    public void setMusic32Filesize(String music32Filesize) {
        this.music32Filesize = music32Filesize;
    }

    public String getMusicFilesize() {
        return musicFilesize;
    }

    public void setMusicFilesize(String musicFilesize) {
        this.musicFilesize = musicFilesize;
    }

    public String getMusic320Filesize() {
        return music320Filesize;
    }

    public void setMusic320Filesize(String music320Filesize) {
        this.music320Filesize = music320Filesize;
    }

    public String getMusicM4aFilesize() {
        return musicM4aFilesize;
    }

    public void setMusicM4aFilesize(String musicM4aFilesize) {
        this.musicM4aFilesize = musicM4aFilesize;
    }

    public String getMusicLosslessFilesize() {
        return musicLosslessFilesize;
    }

    public void setMusicLosslessFilesize(String musicLosslessFilesize) {
        this.musicLosslessFilesize = musicLosslessFilesize;
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

    public String getMusicUsername() {
        return musicUsername;
    }

    public void setMusicUsername(String musicUsername) {
        this.musicUsername = musicUsername;
    }

    public String getMusicLyric() {
        return musicLyric;
    }

    public void setMusicLyric(String musicLyric) {
        this.musicLyric = musicLyric;
    }

    public String getMusicImgHeight() {
        return musicImgHeight;
    }

    public void setMusicImgHeight(String musicImgHeight) {
        this.musicImgHeight = musicImgHeight;
    }

    public String getMusicImgWidth() {
        return musicImgWidth;
    }

    public void setMusicImgWidth(String musicImgWidth) {
        this.musicImgWidth = musicImgWidth;
    }

    public String getMusicImg() {
        return musicImg;
    }

    public void setMusicImg(String musicImg) {
        this.musicImg = musicImg;
    }

    public String getVideoThumbnail() {
        return videoThumbnail;
    }

    public void setVideoThumbnail(String videoThumbnail) {
        this.videoThumbnail = videoThumbnail;
    }

    public String getVideoPreview() {
        return videoPreview;
    }

    public void setVideoPreview(String videoPreview) {
        this.videoPreview = videoPreview;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFile32Url() {
        return file32Url;
    }

    public void setFile32Url(String file32Url) {
        this.file32Url = file32Url;
    }

    public String getFile320Url() {
        return file320Url;
    }

    public void setFile320Url(String file320Url) {
        this.file320Url = file320Url;
    }

    public String getFileM4aUrl() {
        return fileM4aUrl;
    }

    public void setFileM4aUrl(String fileM4aUrl) {
        this.fileM4aUrl = fileM4aUrl;
    }

    public String getFileLosslessUrl() {
        return fileLosslessUrl;
    }

    public void setFileLosslessUrl(String fileLosslessUrl) {
        this.fileLosslessUrl = fileLosslessUrl;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getMusicGenre() {
        return musicGenre;
    }

    public void setMusicGenre(String musicGenre) {
        this.musicGenre = musicGenre;
    }

}
