package lexlink.app.com.lexlink.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by harmis on 16/3/17.
 */

public class LawyerCategory {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("categoryList")
    @Expose
    private List<LawyerCategoryList> categoryList = null;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public List<LawyerCategoryList> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<LawyerCategoryList> categoryList) {
        this.categoryList = categoryList;
    }
}
