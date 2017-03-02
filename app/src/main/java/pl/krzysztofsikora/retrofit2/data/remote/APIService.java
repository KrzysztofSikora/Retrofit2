package pl.krzysztofsikora.retrofit2.data.remote;


import okhttp3.MediaType;
import pl.krzysztofsikora.retrofit2.data.model.Contents;
import pl.krzysztofsikora.retrofit2.data.model.Post;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APIService {
//    @POST("api/contents")
//    @FormUrlEncoded
//    Call<Post> savePost(@Field("title") String title,
//                        @Field("body") String body,
//                        @Field("userId") long userId);

    @POST("api/contents")
    Call<Contents> savePost(@Header("Content-Type") String content_type, @Body Contents contents);

}
