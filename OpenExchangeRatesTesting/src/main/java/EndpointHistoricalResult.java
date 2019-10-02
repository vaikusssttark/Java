import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class EndpointHistoricalResult {
    @SerializedName("disclaimer")
    @Expose
    private String disclaimer;

    @SerializedName("license")
    @Expose
    private String license;

    @SerializedName("timestamp")
    @Expose
    private long timestamp;

    @SerializedName("base")
    @Expose
    private String base;

    @SerializedName("rates")
    @Expose
    private HashMap<String, Double> rates;

    public String getDisclaimer() {
        return disclaimer;
    }

    public String getLicense() {
        return license;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getBase() {
        return base;
    }

    public HashMap<String, Double> getRates() {
        return rates;
    }
}
