package lexlink.app.com.lexlink.httpmanager;

/**
 * Created by Harmis on 15/03/17.
 */

import com.squareup.okhttp.OkHttpClient;

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
            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setConnectTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS);
            okHttpClient.setWriteTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS);
            okHttpClient.setReadTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                    .build();


            apiService = retrofit.create(Webservices.class);
            return apiService;
        } else {
            return apiService;
        }
    }

}
