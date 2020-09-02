
package javaapplication1;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artist_1 {

    @SerializedName("music_total")
    @Expose
    private Integer musicTotal;
    @SerializedName("music_list")
    @Expose
    private List<MusicList__> musicList = null;

    public Integer getMusicTotal() {
        return musicTotal;
    }

    public void setMusicTotal(Integer musicTotal) {
        this.musicTotal = musicTotal;
    }

    public List<MusicList__> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<MusicList__> musicList) {
        this.musicList = musicList;
    }

}
