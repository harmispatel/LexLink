package lexlink.app.com.lexlink.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by harmis on 16/3/17.
 */

public class RegisterBase {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("Profile")
    @Expose
    private RegisterResponce profile;


    @SerializedName("Message")
    @Expose
    private String Message;


    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        this.Message = message;
    }


    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public RegisterResponce getProfile() {
        return profile;
    }

    public void setProfile(RegisterResponce profile) {
        this.profile = profile;
    }

}