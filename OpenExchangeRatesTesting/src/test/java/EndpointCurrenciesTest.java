import org.junit.Test;
import static org.junit.Assert.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;

public class EndpointCurrenciesTest {

    private static final String CURRENCY_ABBR = "AED";
    private static final String CURRENCY_NAME = "United Arab Emirates Dirham";


    /**
     * Тестирование на ненулевое значение и совпадение пары ключ-значение
     */

    @Test
    public void getCurrencies() {
        OpenExchangeRates.getInstance()
                .getOpenExchangeRatesAPI()
                .getCurrencies()
                .enqueue(new Callback<HashMap<String, String>>() {
                    public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                        HashMap<String, String> map = response.body();
                        assertNotNull(response.body());
                        assertEquals(map.get(CURRENCY_ABBR), CURRENCY_NAME);
                    }

                    public void onFailure(Call<HashMap<String, String>> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }
}
