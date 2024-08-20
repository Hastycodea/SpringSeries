package org.example.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DarajaStkPushResponse {
    @JsonProperty("MerchantRequestID")
    @SerializedName("MerchantRequestID")
    private String merchantRequestID;

    @JsonProperty("CheckoutRequestID")
    @SerializedName("CheckoutRequestID")
    private String checkoutRequestID;

    @JsonProperty("ResponseCode")
    @SerializedName("ResponseCode")
    private String responseCode;

    @JsonProperty("ResponseDescription")
    @SerializedName("ResponseDescription")
    private String responseDescription;

    @JsonProperty("CustomerMessage")
    @SerializedName("CustomerMessage")
    private String customerMessage;
}
