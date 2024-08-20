package org.example.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DarajaStkPushQueryResponse {
    @JsonProperty("ResponseCode")
    @SerializedName("ResponseCode")
    private String responseCode;//"0" means stk push reached the user

    @JsonProperty("ResponseDescription")
    @SerializedName("ResponseDescription")
    private String responseDescription;

    @JsonProperty("MerchantRequestID")
    @SerializedName("MerchantRequestID")
    private String merchantRequestID;

    @JsonProperty("CheckoutRequestID")
    @SerializedName("CheckoutRequestID")
    private String checkoutRequestID;

    @JsonProperty("ResultCode")
    @SerializedName("ResultCode")
    private String resultCode; //"0" means success payment

    @JsonProperty("ResultDesc")
    @SerializedName("ResultDesc")
    private String resultDesc;
}
