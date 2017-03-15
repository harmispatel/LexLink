package lexlink.app.com.lexlink.interfaces;

import com.google.gson.JsonObject;
import com.squareup.okhttp.RequestBody;

import lexlink.app.com.lexlink.beans.LinkUserbean;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


/**
 * Created by Harmis on 15/03/17.
 */

public interface Webservices {


    @POST("userLogin")
    Call<LinkUserbean> signInUser(@Header("Content-Type") String auth_second, @Body JsonObject jsonBody);


    @Multipart
    @POST("user/signup")
    Call<LinkUserbean> updateProfilePhotoProcess(@Part("email") RequestBody email, @Part("profile_pic\"; filename=\"pp.png\" ") RequestBody file);


}
