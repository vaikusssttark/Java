import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpenExchangeRates {
    private static OpenExchangeRates mInstance;
    private static final String URL_BASE = "https://openexchangerates.org/api/";
    private Retrofit mRetrofit;

    private OpenExchangeRates() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static OpenExchangeRates getInstance() {
        if (mInstance == null) {
            mInstance = new OpenExchangeRates();
        }
        return mInstance;
    }

    public OpenExchangeRatesAPI getOpenExchangeRatesAPI() {
        return mRetrofit.create(OpenExchangeRatesAPI.class);
    }
}
