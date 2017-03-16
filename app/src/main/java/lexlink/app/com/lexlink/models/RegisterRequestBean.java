package lexlink.app.com.lexlink.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by harmis on 1/3/17.
 */

public class RegisterRequestBean {


    @SerializedName("Address")
    @Expose
    public String address;
    @SerializedName("Salutation")
    @Expose
    public String salutation;
    @SerializedName("FirstName")
    @Expose
    public String firstName;
    @SerializedName("MiddleName")
    @Expose
    public String middleName;
    @SerializedName("LastName")
    @Expose
    public String lastName;
    @SerializedName("Email")
    @Expose
    public String email;
    @SerializedName("City")
    @Expose
    public String city;
    @SerializedName("PostalCode")
    @Expose
    public String postalCode;
    @SerializedName("PassWord")
    @Expose
    public String passWord;
    @SerializedName("DeviceToken")
    @Expose
    public String deviceToken;
    @SerializedName("DeviceAccess")
    @Expose
    public String deviceAccess;
    @SerializedName("Category")
    @Expose
    public String Category;

    /**
     * No args constructor for use in serialization
     */
    public RegisterRequestBean() {
    }

    /**
     * @param middleName
     * @param lastName
     * @param postalCode
     * @param email
     * @param address
     * @param deviceToken
     * @param firstName
     * @param salutation
     * @param passWord
     * @param deviceAccess
     * @param city
     *
     */


    public RegisterRequestBean(String address, String salutation, String firstName, String middleName, String lastName, String email, String city, String postalCode, String passWord, String deviceToken, String deviceAccess) {
        super();
        this.address = address;
        this.salutation = salutation;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.postalCode = postalCode;
        this.passWord = passWord;
        this.deviceToken = deviceToken;
        this.deviceAccess = deviceAccess;
    }

    /**
     * @param middleName
     * @param lastName
     * @param postalCode
     * @param email
     * @param address
     * @param deviceToken
     * @param firstName
     * @param salutation
     * @param passWord
     * @param deviceAccess
     * @param city
     * @param category
     */

    public RegisterRequestBean(String address, String salutation, String firstName, String middleName, String lastName, String email, String city, String postalCode, String passWord, String deviceToken, String deviceAccess, String category) {
        super();
        this.address = address;
        this.salutation = salutation;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.postalCode = postalCode;
        this.passWord = passWord;
        this.deviceToken = deviceToken;
        this.deviceAccess = deviceAccess;
        this.Category = category;

    }
}