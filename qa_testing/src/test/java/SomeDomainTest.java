import org.junit.Test;
import static org.junit.Assert.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SomeDomainTest {

    private static final int INVALID_COMPANY_ID = 777;
    private static final int COMPANY_1_ID = 666;
    private static final int COMPANY_2_ID = 555;
    private static final String INVALID_USER_NAME = "Skywalker";
    private static final String USER_NAME_FROM_COMPANY_1 = "Thor";
    private static final String USER_NAME_FROM_COMPANY_2 = "Aquaman";
    private static final int ERROR_CODE_404 = 404;
    private static final int SUCCESS_CODE_200 = 200;


    /**
     * Тестирование с несуществующими параметрами
     */

    //тест с ID несуществующей компании и несуществующим USER
    @Test
    public void getUserFromCompanyDoesNotExists_withUserNameDoesNotExists() {
        SomeDomain.getInstance()
                .getSomeDomainAPI()
                .getUserByName(INVALID_COMPANY_ID, INVALID_USER_NAME)
                .enqueue(new Callback<User>() {
                    public void onResponse(Call<User> call, Response<User> response) {
                        int result = response.code();
                        assertEquals(ERROR_CODE_404, result);
                    }

                    public void onFailure(Call<User> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    //тест с ID несуществующей компании и существующим USER из компании 1
    @Test
    public void getUserFromCompanyDoesNotExists_withUserNameFromCompany_1() {
        SomeDomain.getInstance()
                .getSomeDomainAPI()
                .getUserByName(INVALID_COMPANY_ID, USER_NAME_FROM_COMPANY_1)
                .enqueue(new Callback<User>() {
                    public void onResponse(Call<User> call, Response<User> response) {
                        int result = response.code();
                        assertEquals(ERROR_CODE_404, result);
                    }

                    public void onFailure(Call<User> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    //тест с ID несуществующей компании и существующим USER из компании 2
    @Test
    public void getUserFromCompanyDoesNotExists_withUserNameFromCompany_2() {
        SomeDomain.getInstance()
                .getSomeDomainAPI()
                .getUserByName(INVALID_COMPANY_ID, USER_NAME_FROM_COMPANY_2)
                .enqueue(new Callback<User>() {
                    public void onResponse(Call<User> call, Response<User> response) {
                        int result = response.code();
                        assertEquals(ERROR_CODE_404, result);
                    }

                    public void onFailure(Call<User> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    //тест с NAME несуществующего пользователя в 1-ой компании
    @Test
    public void getUserDoesNotExistFromCompany_1() {
        SomeDomain.getInstance()
                .getSomeDomainAPI()
                .getUserByName(COMPANY_1_ID, INVALID_USER_NAME)
                .enqueue(new Callback<User>() {
                    public void onResponse(Call<User> call, Response<User> response) {
                        int result = response.code();
                        assertEquals(ERROR_CODE_404, result);
                    }

                    public void onFailure(Call<User> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    //тест с NAME несуществующего пользователя во 2-ой компании
    @Test
    public void getUserDoesNotExistFromCompany_2() {
        SomeDomain.getInstance()
                .getSomeDomainAPI()
                .getUserByName(COMPANY_2_ID, INVALID_USER_NAME)
                .enqueue(new Callback<User>() {
                    public void onResponse(Call<User> call, Response<User> response) {
                        int result = response.code();
                        assertEquals(ERROR_CODE_404, result);
                    }

                    public void onFailure(Call<User> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    /**
     * Тестирование с пользователями разных компаний
     */

    //тест c запросом пользователя компании 1 с именем пользователя из компании 2
    @Test
    public void getUserFromCompany_1_withUserNameFromCompany_2() {
        SomeDomain.getInstance()
                .getSomeDomainAPI()
                .getUserByName(COMPANY_1_ID, USER_NAME_FROM_COMPANY_2)
                .enqueue(new Callback<User>() {
                    public void onResponse(Call<User> call, Response<User> response) {
                        int result = response.code();
                        assertEquals(ERROR_CODE_404, result);
                    }

                    public void onFailure(Call<User> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    //тест c запросом пользователя компании 2 с именем пользователя из компании 1
    @Test
    public void getUserFromCompany_2_withUserNameFromCompany_1() {
        SomeDomain.getInstance()
                .getSomeDomainAPI()
                .getUserByName(COMPANY_1_ID, USER_NAME_FROM_COMPANY_2)
                .enqueue(new Callback<User>() {
                    public void onResponse(Call<User> call, Response<User> response) {
                        int result = response.code();
                        assertEquals(ERROR_CODE_404, result);
                    }

                    public void onFailure(Call<User> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    /**
     * Тестирование с корректными данными (с учетом, что ответ приходит в формате JSON)
     */

    //тест c запросом пользователя компании 1 с именем пользователя из компании 1
    @Test
    public void getUserFromCompany_1_withUserNameFromCompany_1() {
        SomeDomain.getInstance()
                .getSomeDomainAPI()
                .getUserByName(COMPANY_1_ID, USER_NAME_FROM_COMPANY_1)
                .enqueue(new Callback<User>() {
                    public void onResponse(Call<User> call, Response<User> response) {
                        int result = response.code();
                        assertEquals(SUCCESS_CODE_200, result);
                        String resultUserName = response.body().getName();
                        assertEquals(resultUserName, USER_NAME_FROM_COMPANY_1);
                    }

                    public void onFailure(Call<User> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    //тест c запросом пользователя компании 2 с именем пользователя из компании 2
    @Test
    public void getUserFromCompany_2_withUserNameFromCompany_2() {
        SomeDomain.getInstance()
                .getSomeDomainAPI()
                .getUserByName(COMPANY_1_ID, USER_NAME_FROM_COMPANY_1)
                .enqueue(new Callback<User>() {
                    public void onResponse(Call<User> call, Response<User> response) {
                        int result = response.code();
                        assertEquals(SUCCESS_CODE_200, result);
                        String resultUserName = response.body().getName();
                        assertEquals(resultUserName, USER_NAME_FROM_COMPANY_2);
                    }

                    public void onFailure(Call<User> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }
}
