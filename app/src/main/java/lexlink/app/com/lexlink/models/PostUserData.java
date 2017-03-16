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

    @SerializedName("Salutation")
    private String Salutation;

    @SerializedName("FirstName")
    private String FirstName;

    @SerializedName("MiddleName")
    private String MiddleName;

    @SerializedName("LastName")
    private String LastName;

    @SerializedName("Email")
    private String Email;

    @SerializedName("City")
    private String City;

    @SerializedName("PostalCode")
    private String PostalCode;
    @SerializedName("DeviceToken")
    private String DeviceToken;


    @SerializedName("DeviceAccess")
    private String DeviceAccess;


    @SerializedName("LoginType")
    private String LoginType;


    public PostUserData(String UserName, String PassWord, String isSocial, String LoginType) {
        this.UserName = UserName;
        this.PassWord = PassWord;
        this.isSocial = isSocial;
        this.LoginType = LoginType;

    }

    public PostUserData(String Salutation, String FirstName, String MiddleName, String LastName, String Email, String City, String PostalCode, String PassWord, String DeviceToken, String DeviceAccess) {
        this.Salutation = Salutation;
        this.FirstName = FirstName;
        this.MiddleName = MiddleName;
        this.LastName = LastName;
        this.Email = Email;
        this.City = City;
        this.PostalCode = PostalCode;
        this.DeviceToken = DeviceToken;
        this.DeviceAccess = DeviceAccess;
        this.PassWord = PassWord;


    }

    public String getDeviceAccess() {
        return DeviceAccess;
    }

    public void setDeviceAccess(String deviceAccess) {
        DeviceAccess = deviceAccess;
    }

    public String getDeviceToken() {
        return DeviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        DeviceToken = deviceToken;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getSalutation() {
        return Salutation;
    }

    public void setSalutation(String salutation) {
        Salutation = salutation;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
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
