
package javaapplication1;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Music {

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("rows")
    @Expose
    private Integer rows;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("row_total")
    @Expose
    private Integer rowTotal;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRowTotal() {
        return rowTotal;
    }

    public void setRowTotal(Integer rowTotal) {
        this.rowTotal = rowTotal;
    }

}
