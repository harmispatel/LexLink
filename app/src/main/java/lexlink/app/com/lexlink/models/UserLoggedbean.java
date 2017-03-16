package lexlink.app.com.lexlink.models;

/**
 * Created by Harmis on 15/03/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLoggedbean {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("Profile")
    @Expose
    private Profile profile;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

}