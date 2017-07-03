package pl.krzysztofsikora.retrofit2.data.remote;


public class ApiUtils {

    private ApiUtils() {}

//    public static final String BASE_URL = "http://jsonplaceholder.typicode.com/";
    public static final String BASE_URL = "http://192.168.1.105:3000/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
