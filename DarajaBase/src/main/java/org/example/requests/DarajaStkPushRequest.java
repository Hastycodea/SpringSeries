package org.example.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DarajaStkPushRequest {

    public DarajaStkPushRequest(String businessShortCode, String password, String timestamp, String transactionType,
                                String amount, String partyA, String partyB, String phoneNumber, String callBackURL,
                                String accountReference, String transactionDesc) {
        this.businessShortCode = businessShortCode;
        this.password = password;
        this.timestamp = timestamp;
        this.transactionType = transactionType;
        this.amount = amount;
        this.partyA = partyA;
        this.partyB = partyB;
        this.phoneNumber = phoneNumber;
        this.callBackURL = callBackURL;
        this.accountReference = accountReference;
        this.transactionDesc = transactionDesc;
    }


    @SerializedName("BusinessShortCode")
    @JsonProperty("BusinessShortCode")
    private String businessShortCode;

    @SerializedName("Password")
    @JsonProperty("Password")
    private String password;

    @SerializedName("Timestamp")
    @JsonProperty("Timestamp")
    private String timestamp;

    @SerializedName("TransactionType")
    @JsonProperty("TransactionType")
    private String transactionType;

    @SerializedName("Amount")
    @JsonProperty("Amount")
    private String amount;

    @SerializedName("PartyA")
    @JsonProperty("PartyA")
    private String partyA;

    @SerializedName("PartyB")
    @JsonProperty("PartyB")
    private String partyB;

    @SerializedName("PhoneNumber")
    @JsonProperty("PhoneNumber")
    private String phoneNumber;

    @SerializedName("CallBackURL")
    @JsonProperty("CallBackURL")
    private String callBackURL;

    @SerializedName("AccountReference")
    @JsonProperty("AccountReference")
    private String accountReference;

    @SerializedName("TransactionDesc")
    @JsonProperty("TransactionDesc")
    private String transactionDesc;
}
