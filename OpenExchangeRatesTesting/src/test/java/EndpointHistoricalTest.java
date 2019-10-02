import org.junit.Test;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EndpointHistoricalTest {

    private static final String INVALID_DATA = "2017/08/19";
    private static final String CORRECT_DATA = "2017-08-19";

    private static final String INVALID_APP_ID = "123";
    private static final String CORRECT_APP_ID = "correct123";

    private static final String INVALID_BASE = "XYZ";
    private static final String DEFAULT_BASE = "USD";
    private static final String CORRECT_BASE = "RUB";

    private static final String INVALID_SYMBOLS = "qwe:rty:asd:fgh";
    private static final String CORRECT_SYMBOLS = "USD,RUB,EUR,CNY";

    private static final int INVALID_APP_ID_CODE = 401;
    private static final int INVALID_BASE_CODE = 400;
    private static final int NOT_FOUND_CODE = 404;
    private static final int SUCCESS_CODE = 200;

    /**
     * Тестирование параметра app_id с некорректным форматом data
     */

    // тест с некорректным app_id
    @Test
    public void setInvalidAppIdWithInvalidData() {
        OpenExchangeRates.getInstance()
                .getOpenExchangeRatesAPI()
                .getHistorical(INVALID_DATA, INVALID_APP_ID, null, null, false, false)
                .enqueue(new Callback<EndpointHistoricalResult>() {
                    public void onResponse(Call<EndpointHistoricalResult> call, Response<EndpointHistoricalResult> response) {
                        assertEquals(INVALID_APP_ID_CODE, response.code());
                    }

                    public void onFailure(Call<EndpointHistoricalResult> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    // тест с корректным app_id
    @Test
    public void setCorrectAppIdWithInvalidData() {
        OpenExchangeRates.getInstance()
                .getOpenExchangeRatesAPI()
                .getHistorical(INVALID_DATA, CORRECT_APP_ID, null, null, false, false)
                .enqueue(new Callback<EndpointHistoricalResult>() {
                    public void onResponse(Call<EndpointHistoricalResult> call, Response<EndpointHistoricalResult> response) {
                        assertEquals(NOT_FOUND_CODE, response.code());
                    }

                    public void onFailure(Call<EndpointHistoricalResult> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    /**
     * Дальнейшее тестирование предполагает, что data указан корректно,
     * т.к. при указании data некорректно, страница попросту не будет найдена
     *(за исключением случая, когда неверно указан app_id)
     *
     *
     * Тестирование параметра app_id
     */

    // тест с некорректным app_id
    @Test
    public void setInvalidAppId() {
        OpenExchangeRates.getInstance()
                .getOpenExchangeRatesAPI()
                .getHistorical(CORRECT_DATA, INVALID_APP_ID, null, null, false, false)
                .enqueue(new Callback<EndpointHistoricalResult>() {
                    public void onResponse(Call<EndpointHistoricalResult> call, Response<EndpointHistoricalResult> response) {
                        assertEquals(INVALID_APP_ID_CODE, response.code());
                    }

                    public void onFailure(Call<EndpointHistoricalResult> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    // тест с корректным app_id
    @Test
    public void setCorrectAppId() {
        OpenExchangeRates.getInstance()
                .getOpenExchangeRatesAPI()
                .getHistorical(CORRECT_DATA, CORRECT_APP_ID, null, null, false, false)
                .enqueue(new Callback<EndpointHistoricalResult>() {
                    public void onResponse(Call<EndpointHistoricalResult> call, Response<EndpointHistoricalResult> response) {
                        EndpointHistoricalResult res = response.body();
                        assertEquals(SUCCESS_CODE, response.code());
                        assertEquals(DEFAULT_BASE, res.getBase());
                        assertNotNull(res.getRates());
                    }

                    public void onFailure(Call<EndpointHistoricalResult> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    /**
     * Тестирование параметра base с корректным app_ID
     */

    // тест с некорректным base
    @Test
    public void setInvalidBaseWithCorrectAppID() {
        OpenExchangeRates.getInstance()
                .getOpenExchangeRatesAPI()
                .getHistorical(CORRECT_DATA, CORRECT_APP_ID, INVALID_BASE, null, false, false)
                .enqueue(new Callback<EndpointHistoricalResult>() {
                    public void onResponse(Call<EndpointHistoricalResult> call, Response<EndpointHistoricalResult> response) {
                        assertEquals(INVALID_BASE_CODE, response.code());
                    }

                    public void onFailure(Call<EndpointHistoricalResult> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    // тест с корректным base
    @Test
    public void setCorrectBaseWithCorrectAppID() {
        OpenExchangeRates.getInstance()
                .getOpenExchangeRatesAPI()
                .getHistorical(CORRECT_DATA, CORRECT_APP_ID, CORRECT_BASE, null, false, false)
                .enqueue(new Callback<EndpointHistoricalResult>() {
                    public void onResponse(Call<EndpointHistoricalResult> call, Response<EndpointHistoricalResult> response) {
                        EndpointHistoricalResult res = response.body();
                        assertEquals(SUCCESS_CODE, response.code());
                        assertEquals(CORRECT_BASE, res.getBase());
                        assertNotNull(res.getRates());
                    }

                    public void onFailure(Call<EndpointHistoricalResult> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    /**
     * Тестирование параметра base с некорректным app_ID
     */

    // тест с некорректным base
    @Test
    public void setInvalidBaseWithInvalidAppID() {
        OpenExchangeRates.getInstance()
                .getOpenExchangeRatesAPI()
                .getHistorical(CORRECT_DATA, INVALID_APP_ID, INVALID_BASE, null, false, false)
                .enqueue(new Callback<EndpointHistoricalResult>() {
                    public void onResponse(Call<EndpointHistoricalResult> call, Response<EndpointHistoricalResult> response) {
                        assertEquals(INVALID_APP_ID_CODE, response.code());
                    }

                    public void onFailure(Call<EndpointHistoricalResult> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    // тест с корректным base
    @Test
    public void setCorrectBaseWithInvalidAppID() {
        OpenExchangeRates.getInstance()
                .getOpenExchangeRatesAPI()
                .getHistorical(CORRECT_DATA, INVALID_APP_ID, CORRECT_BASE, null, false, false)
                .enqueue(new Callback<EndpointHistoricalResult>() {
                    public void onResponse(Call<EndpointHistoricalResult> call, Response<EndpointHistoricalResult> response) {
                        assertEquals(INVALID_APP_ID_CODE, response.code());
                    }

                    public void onFailure(Call<EndpointHistoricalResult> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    /**
     * Тестирование параметра symbols с корректными app_id и base
     */

    // тест с некорректным symbols(предполагается, что код ошибки будет 400, но может оказаться и 404)
    @Test
    public void setInvalidSymbolsWithCorrectAppIDAndCorrectBase() {
        OpenExchangeRates.getInstance()
                .getOpenExchangeRatesAPI()
                .getHistorical(CORRECT_DATA, CORRECT_APP_ID, CORRECT_BASE, INVALID_SYMBOLS, false, false)
                .enqueue(new Callback<EndpointHistoricalResult>() {
                    public void onResponse(Call<EndpointHistoricalResult> call, Response<EndpointHistoricalResult> response) {
                        //assertEquals(NOT_FOUND_CODE, response.code());
                        assertEquals(INVALID_BASE_CODE, response.code());
                    }

                    public void onFailure(Call<EndpointHistoricalResult> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    // тест с корректным symbols
    @Test
    public void setCorrectSymbolsWithCorrectAppIDAndCorrectBase() {
        OpenExchangeRates.getInstance()
                .getOpenExchangeRatesAPI()
                .getHistorical(CORRECT_DATA, CORRECT_APP_ID, CORRECT_BASE, CORRECT_SYMBOLS, false, false)
                .enqueue(new Callback<EndpointHistoricalResult>() {
                    public void onResponse(Call<EndpointHistoricalResult> call, Response<EndpointHistoricalResult> response) {
                        EndpointHistoricalResult res = response.body();
                        assertEquals(SUCCESS_CODE, response.code());
                        assertEquals(CORRECT_BASE, res.getBase());
                        assertNotNull(res.getRates());
                    }

                    public void onFailure(Call<EndpointHistoricalResult> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    /**
     * Тестирование параметра symbols с некорректными app_id и base
     */

    // тест с некорректным symbols
    @Test
    public void setInvalidSymbolsWithInvalidAppIDAndInvalidBase() {
        OpenExchangeRates.getInstance()
                .getOpenExchangeRatesAPI()
                .getHistorical(CORRECT_DATA, INVALID_APP_ID, INVALID_BASE, INVALID_SYMBOLS, false, false)
                .enqueue(new Callback<EndpointHistoricalResult>() {
                    public void onResponse(Call<EndpointHistoricalResult> call, Response<EndpointHistoricalResult> response) {
                        assertEquals(INVALID_APP_ID_CODE, response.code());
                    }

                    public void onFailure(Call<EndpointHistoricalResult> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    // тест с корректным symbols
    @Test
    public void setCorrectSymbolsWithInvalidAppIDAndInvalidBase() {
        OpenExchangeRates.getInstance()
                .getOpenExchangeRatesAPI()
                .getHistorical(CORRECT_DATA, INVALID_APP_ID, INVALID_BASE, CORRECT_SYMBOLS, false, false)
                .enqueue(new Callback<EndpointHistoricalResult>() {
                    public void onResponse(Call<EndpointHistoricalResult> call, Response<EndpointHistoricalResult> response) {
                        assertEquals(INVALID_APP_ID_CODE, response.code());
                    }

                    public void onFailure(Call<EndpointHistoricalResult> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    /**
     * Тестирование параметра symbols с некорректным app_id и корректным base
     */

    // тест с некорректным symbols
    @Test
    public void setInvalidSymbolsWithInvalidAppIDAndCorrectBase() {
        OpenExchangeRates.getInstance()
                .getOpenExchangeRatesAPI()
                .getHistorical(CORRECT_DATA, INVALID_APP_ID, CORRECT_BASE, INVALID_SYMBOLS, false, false)
                .enqueue(new Callback<EndpointHistoricalResult>() {
                    public void onResponse(Call<EndpointHistoricalResult> call, Response<EndpointHistoricalResult> response) {
                        assertEquals(INVALID_APP_ID_CODE, response.code());
                    }

                    public void onFailure(Call<EndpointHistoricalResult> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    // тест с корректным symbols
    @Test
    public void setCorrectSymbolsWithInvalidAppIDAndCorrectBase() {
        OpenExchangeRates.getInstance()
                .getOpenExchangeRatesAPI()
                .getHistorical(CORRECT_DATA, INVALID_APP_ID, CORRECT_BASE, CORRECT_SYMBOLS, false, false)
                .enqueue(new Callback<EndpointHistoricalResult>() {
                    public void onResponse(Call<EndpointHistoricalResult> call, Response<EndpointHistoricalResult> response) {
                        assertEquals(INVALID_APP_ID_CODE, response.code());
                    }

                    public void onFailure(Call<EndpointHistoricalResult> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    /**
     * Тестирование параметра symbols с некорректным app_id и корректным base
     */

    // тест с некорректным symbols
    @Test
    public void setInvalidSymbolsWithCorrectAppIDAndInvalidBase() {
        OpenExchangeRates.getInstance()
                .getOpenExchangeRatesAPI()
                .getHistorical(CORRECT_DATA, CORRECT_APP_ID, INVALID_BASE, INVALID_SYMBOLS, false, false)
                .enqueue(new Callback<EndpointHistoricalResult>() {
                    public void onResponse(Call<EndpointHistoricalResult> call, Response<EndpointHistoricalResult> response) {
                        assertEquals(INVALID_BASE_CODE, response.code());
                    }

                    public void onFailure(Call<EndpointHistoricalResult> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    // тест с корректным symbols
    @Test
    public void setCorrectSymbolsWithCorrectAppIDAndInvalidBase() {
        OpenExchangeRates.getInstance()
                .getOpenExchangeRatesAPI()
                .getHistorical(CORRECT_DATA, CORRECT_APP_ID, INVALID_BASE, CORRECT_SYMBOLS, false, false)
                .enqueue(new Callback<EndpointHistoricalResult>() {
                    public void onResponse(Call<EndpointHistoricalResult> call, Response<EndpointHistoricalResult> response) {
                        assertEquals(INVALID_BASE_CODE, response.code());
                    }

                    public void onFailure(Call<EndpointHistoricalResult> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    /**
     * Тестирование параметров prettyprint и show_alternative
     * при корректных app_id, base и symbols
     * (только на факт успешного выполнения запроса)
     */

    // тест с prettyprint = true
    @Test
    public void setPrettyprintTrue() {
        OpenExchangeRates.getInstance()
                .getOpenExchangeRatesAPI()
                .getHistorical(CORRECT_DATA, CORRECT_APP_ID, CORRECT_BASE, CORRECT_SYMBOLS, true, false)
                .enqueue(new Callback<EndpointHistoricalResult>() {
                    public void onResponse(Call<EndpointHistoricalResult> call, Response<EndpointHistoricalResult> response) {
                        EndpointHistoricalResult res = response.body();
                        assertEquals(SUCCESS_CODE, response.code());
                        assertEquals(CORRECT_BASE, res.getBase());
                        assertNotNull(res.getRates());
                    }

                    public void onFailure(Call<EndpointHistoricalResult> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    // тест с prettyprint = true
    @Test
    public void setShowAlternativeTrue() {
        OpenExchangeRates.getInstance()
                .getOpenExchangeRatesAPI()
                .getHistorical(CORRECT_DATA, CORRECT_APP_ID, CORRECT_BASE, CORRECT_SYMBOLS, false, true)
                .enqueue(new Callback<EndpointHistoricalResult>() {
                    public void onResponse(Call<EndpointHistoricalResult> call, Response<EndpointHistoricalResult> response) {
                        EndpointHistoricalResult res = response.body();
                        assertEquals(SUCCESS_CODE, response.code());
                        assertEquals(CORRECT_BASE, res.getBase());
                        assertNotNull(res.getRates());
                    }

                    public void onFailure(Call<EndpointHistoricalResult> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    // тест с prettyprint и show_alternative = true
    @Test
    public void setPrettyprintAndShowAlternativeTrue() {
        OpenExchangeRates.getInstance()
                .getOpenExchangeRatesAPI()
                .getHistorical(CORRECT_DATA, CORRECT_APP_ID, CORRECT_BASE, CORRECT_SYMBOLS, true, true)
                .enqueue(new Callback<EndpointHistoricalResult>() {
                    public void onResponse(Call<EndpointHistoricalResult> call, Response<EndpointHistoricalResult> response) {
                        EndpointHistoricalResult res = response.body();
                        assertEquals(SUCCESS_CODE, response.code());
                        assertEquals(CORRECT_BASE, res.getBase());
                        assertNotNull(res.getRates());
                    }

                    public void onFailure(Call<EndpointHistoricalResult> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }
}
