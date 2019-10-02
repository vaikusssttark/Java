import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Test {
    public static void main(String[] args) {
        OpenExchangeRates.getInstance()
                .getOpenExchangeRatesAPI()
                .getLatest("123", null, null, false, false)
                .enqueue(new Callback<EndpointLatestResult>() {
                    public void onResponse(Call<EndpointLatestResult> call, Response<EndpointLatestResult> response) {
                        System.out.println(response.code());
                        System.out.println(123);
                    }

                    public void onFailure(Call<EndpointLatestResult> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
        System.out.println(123);
    }
}
