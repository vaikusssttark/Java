import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenExchangeRatesAPI {
    @GET("latest.json")
    Call<EndpointLatestResult> getLatest(@Query("app_id") String appID,
                                         @Query("base") String base,
                                         @Query("symbols") String symbols,
                                         @Query("prettyprint") boolean prettyprint,
                                         @Query("show_alternative") boolean showAlternative);

    @GET("historical/{date}.json")
    Call<EndpointHistoricalResult> getHistorical(@Query("app_id") String appID,
                                                 @Query("base") String base,
                                                 @Query("symbols") String symbols,
                                                 @Query("prettyprint") boolean prettyprint,
                                                 @Query("show_alternative") boolean showAlternative);

    @GET("currencies.json")
    Call<String> getCurrencies();
}
