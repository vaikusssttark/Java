import org.junit.Test;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;

public class SomeDomainTest {

    private static final int USER_ID = 1;
    private static final int SUCCESS_CODE_200 = 200;

    @Test
    public void getUserFromCompanyDoesNotExists_withUserNameDoesNotExists() {
        SomeDomain.getInstance()
                .getSomeDomainAPI()
                .getUserByID(USER_ID)
                .enqueue(new Callback<User>() {
                    public void onResponse(Call<User> call, Response<User> response) {
                        int result = response.code();
                        assertEquals(SUCCESS_CODE_200, result);
                        int userID = response.body().getId();
                        assertEquals(USER_ID, userID);
                    }

                    public void onFailure(Call<User> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }
}
