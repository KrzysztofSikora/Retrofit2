package pl.krzysztofsikora.retrofit2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import pl.krzysztofsikora.retrofit2.data.model.Contents;
import pl.krzysztofsikora.retrofit2.data.model.Coordinates;
import pl.krzysztofsikora.retrofit2.data.model.Post;
import pl.krzysztofsikora.retrofit2.data.remote.APIService;
import pl.krzysztofsikora.retrofit2.data.remote.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    private TextView mResponseTv;
    private APIService mAPIService;
    private static final String TAG = "FragmentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText titleEt = (EditText) findViewById(R.id.et_title);
        final EditText bodyEt = (EditText) findViewById(R.id.et_body);
        Button submitBtn = (Button) findViewById(R.id.btn_submit);
        mResponseTv = (TextView) findViewById(R.id.tv_response);

        mAPIService = ApiUtils.getAPIService();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEt.getText().toString().trim();

                Contents contents = new Contents();
                Coordinates coordinates = new Coordinates();
                coordinates.setLat(52.21212);
                coordinates.setLng(22.21212);
                contents.setCoordinates(coordinates);
                contents.setMainContent("test");

                // you must write string into title field for set true value for this clause
                if (!TextUtils.isEmpty(title)) {
                    Log.i(TAG, "post contents" + contents);
                   sendPost(contents);
//                    getPost();
                }
            }
        });
    }

    public void sendPost(Contents post) {


        mAPIService.savePost("application/json", post).enqueue(new Callback<Contents>() {
            @Override
            public void onResponse(Call<Contents> call, Response<Contents> response) {

                if (response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.i(TAG, "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Contents> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    public void getPost() {
        mAPIService.getAnswers().enqueue(new Callback<List<Contents>>() {


            @Override
            public void onResponse(Call<List<Contents>> call, Response<List<Contents>> response) {

                for(int i=0;i<response.body().size(); i++){
                String result = response.body().get(i).getMainContent();
                 result += "\n" + response.body().get(i).getCoordinates().getLat().toString()
                 + "\n" + response.body().get(i).getCoordinates().getLat().toString();


               Log.d("suc", result);
                }
            }

            @Override
            public void onFailure(Call<List<Contents>> call, Throwable t) {
                Log.d("succes", "succesdas");
            }
        });
    }

    public void showResponse(String response) {
        if (mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);
    }


}
