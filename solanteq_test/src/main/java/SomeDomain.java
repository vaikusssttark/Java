import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SomeDomain {
    private static SomeDomain mInstance;
    private static final String URL_BASE = "http://makingtalk.site/";
    private Retrofit mRetrofit;

    private SomeDomain() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static SomeDomain getInstance() {
        if (mInstance == null) {
            mInstance = new SomeDomain();
        }
        return mInstance;
    }

    public SomeDomainAPI getSomeDomainAPI() {
        return mRetrofit.create(SomeDomainAPI.class);
    }
}
