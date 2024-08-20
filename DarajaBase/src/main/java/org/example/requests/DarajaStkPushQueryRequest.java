package org.example.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DarajaStkPushQueryRequest {
    @SerializedName("BusinessShortCode")
    @JsonProperty("BusinessShortCode")
    private String businessShortCode;

    @SerializedName("Password")
    @JsonProperty("Password")
    private String password;

    @SerializedName("Timestamp")
    @JsonProperty("Timestamp")
    private String timestamp;

    @SerializedName("CheckoutRequestID")
    @JsonProperty("CheckoutRequestID")
    private String checkoutRequestId;
}
