package lexlink.app.com.lexlink.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by harmis on 16/3/17.
 */

public class LawyerCategoryList {
    boolean isItemSelected = false;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("categoryName")
    @Expose
    private String categoryName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isItemSelected() {
        return isItemSelected;
    }

    public void setItemSelected(boolean itemSelected) {
        isItemSelected = itemSelected;
    }

}
