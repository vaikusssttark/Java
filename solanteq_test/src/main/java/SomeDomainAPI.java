import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SomeDomainAPI {
    @GET("get_user_by_id")
    Call<User> getUserByID(@Query("user_id") int userID);
}
