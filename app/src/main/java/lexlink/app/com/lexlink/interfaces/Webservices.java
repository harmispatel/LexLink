package lexlink.app.com.lexlink.interfaces;


import lexlink.app.com.lexlink.models.LawyerCategory;
import lexlink.app.com.lexlink.models.PostUserData;
import lexlink.app.com.lexlink.models.RegisterBase;
import lexlink.app.com.lexlink.models.RegisterRequestBean;
import lexlink.app.com.lexlink.models.UserLoggedbean;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


/**
 * Created by Harmis on 15/03/17.
 */

public interface Webservices {

    @POST("userLogin")
    Call<UserLoggedbean> signInUser(@Body PostUserData linkUserbean);

    @POST("userLogin")
    Call<UserLoggedbean> signInAdmin(@Body PostUserData linkUserbean);
    @Multipart
    @POST("userRegister")
    Call<RegisterBase> register(@Part("json_content") RegisterRequestBean description, @Part MultipartBody.Part image);

    @Multipart
    @POST("lawyerRegister")
    Call<RegisterBase> lawerregister(@Part("json_content") RegisterRequestBean description, @Part MultipartBody.Part image);

    @GET("getCategoryList")
    Call<LawyerCategory> getLawyerCategory();
}
