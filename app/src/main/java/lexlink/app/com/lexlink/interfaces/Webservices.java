package lexlink.app.com.lexlink.interfaces;


import lexlink.app.com.lexlink.models.PostUserData;
import lexlink.app.com.lexlink.models.UserLoggedbean;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


/**
 * Created by Harmis on 15/03/17.
 */

public interface Webservices {

    @POST("userLogin")
    Call<UserLoggedbean> signInUser(@Body PostUserData linkUserbean);


    @Multipart
    @POST("userRegister")
    Call<UserLoggedbean> register(@Part("json_content") PostUserData linkUserbean, @Part MultipartBody.Part image);
}
