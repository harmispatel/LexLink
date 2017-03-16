package lexlink.app.com.lexlink.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Harmis on 15/03/17.
 */

public class PostUserData {

    @SerializedName("UserName")
    private String UserName;
    @SerializedName("PassWord")
    private String PassWord;
    @SerializedName("isSocial")
    private String isSocial;







    @SerializedName("LoginType")
    private String LoginType;


    public PostUserData(String UserName, String PassWord, String isSocial, String LoginType) {
        this.UserName = UserName;
        this.PassWord = PassWord;
        this.isSocial = isSocial;
        this.LoginType = LoginType;

    }




    public String getLoginType() {
        return LoginType;
    }

    public void setLoginType(String loginType) {
        LoginType = loginType;
    }

    public String getIsSocial() {
        return isSocial;
    }

    public void setIsSocial(String isSocial) {
        this.isSocial = isSocial;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

}
