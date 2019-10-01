import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SomeDomainAPI {
    @GET("company/{companyID}/users")
    Call<User> getUserByName(@Path("companyID") int companyID, @Query("name") String userName);
}
