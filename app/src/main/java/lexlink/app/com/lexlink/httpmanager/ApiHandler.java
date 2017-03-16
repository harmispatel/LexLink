package lexlink.app.com.lexlink.httpmanager;

/**
 * Created by Harmis on 15/03/17.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import lexlink.app.com.lexlink.interfaces.Webservices;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by admin on 8/31/2016.
 */
public class ApiHandler {
    private static final String BASE_URL = "http://www.yzelimited.com/lexlink/index.php/Webservice/";


    private static final long HTTP_TIMEOUT = TimeUnit.SECONDS.toMillis(60);
    private static Webservices apiService;


    public static Webservices getApiService() {

        if (apiService == null) {


            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))

                    .baseUrl(BASE_URL).build();


            apiService = retrofit.create(Webservices.class);
            return apiService;
        } else {
            return apiService;
        }
    }

}
