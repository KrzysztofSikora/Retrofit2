package pl.krzysztofsikora.retrofit2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pl.krzysztofsikora.retrofit2.data.model.Contents;
import pl.krzysztofsikora.retrofit2.data.model.Coordinates;
import pl.krzysztofsikora.retrofit2.data.model.Post;
import pl.krzysztofsikora.retrofit2.data.remote.APIService;
import pl.krzysztofsikora.retrofit2.data.remote.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



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
                coordinates.setLat(52.2121);
                coordinates.setLng(22.2121);
                contents.setCoordinates(coordinates);
                contents.setMainContent("test");


                if(!TextUtils.isEmpty(title)) {

                    sendPost(contents);
                }
            }
        });
    }
    public void sendPost(Contents post) {


        mAPIService.savePost("application/json", post).enqueue(new Callback<Contents>() {
            @Override
            public void onResponse(Call<Contents> call, Response<Contents> response) {

                if(response.isSuccessful()) {
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

    public void showResponse(String response) {
        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);
    }



}
