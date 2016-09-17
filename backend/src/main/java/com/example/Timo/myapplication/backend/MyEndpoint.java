/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.Timo.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.inject.Named;

import retrofit.RestAdapter;
import retrofit.appengine.UrlFetchClient;
import retrofit.http.GET;
import retrofit.http.Query;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.myapplication.Timo.example.com",
    ownerName = "backend.myapplication.Timo.example.com",
    packagePath=""
  )
)
public class MyEndpoint {


    public static final String API_BASE_URL = "https://graph.accountkit.com";

    private static RestAdapter.Builder builder = new RestAdapter.Builder()
            .setEndpoint(API_BASE_URL)
            .setClient(new UrlFetchClient());

    public static <S> S createService(Class<S> serviceClass) {
        RestAdapter adapter = builder.build();
        return adapter.create(serviceClass);
    }


    public  interface GetUserDetailsService{
        @GET("/v1.0/me")
        AccountsKitUser getUserDetails(@Query("access_token") String accessToken);

    }

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "getCustomToken")
    public CustomTokenBean getCustomToken(@Named("access_token") String accessToken) throws FileNotFoundException {


        GetUserDetailsService getUserDetailsService = createService(GetUserDetailsService.class);
        AccountsKitUser retrofitResponse = getUserDetailsService.getUserDetails(accessToken);
        String uid = retrofitResponse.getPhone().getNumber();
        FirebaseOptions options = null;
        options = new FirebaseOptions.Builder()
                .setServiceAccount(new FileInputStream(
                        new File("WEB-INF/firebase.json")))
                .setDatabaseUrl("https://fb-accountkit-auth-demo.firebaseio.com/")
                .build();
        FirebaseApp.initializeApp(options);
        String customToken = FirebaseAuth.getInstance().createCustomToken(uid);

        CustomTokenBean response = new CustomTokenBean();
        response.setData(customToken);

        return response;
    }

}
