
package javaapplication1;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recent {

    @SerializedName("music_total")
    @Expose
    private Integer musicTotal;
    @SerializedName("music_list")
    @Expose
    private List<MusicList_> musicList = null;

    public Integer getMusicTotal() {
        return musicTotal;
    }

    public void setMusicTotal(Integer musicTotal) {
        this.musicTotal = musicTotal;
    }

    public List<MusicList_> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<MusicList_> musicList) {
        this.musicList = musicList;
    }

}
