package pl.krzysztofsikora.retrofit2.data.remote;


import java.util.List;

import okhttp3.MediaType;
import pl.krzysztofsikora.retrofit2.data.model.Contents;
import pl.krzysztofsikora.retrofit2.data.model.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {
//    @POST("api/contents")
//    @FormUrlEncoded
//    Call<Post> savePost(@Field("title") String title,
//                        @Field("body") String body,
//                        @Field("userId") long userId);

    @POST("api/contents")
    Call<Contents> savePost(@Header("Content-Type") String content_type, @Body Contents contents);

    @GET("api/contents")
    Call<List<Contents>> getAnswers();

}
