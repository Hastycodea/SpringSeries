package org.example;

import com.google.gson.Gson;
import lombok.Value;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.example.enums.DarajaTransactionType;
import org.example.exceptions.InternalServerErrorException;
import org.example.requests.DarajaStkPushQueryRequest;
import org.example.requests.DarajaStkPushRequest;
import org.example.responses.DarajaAuthTokenResponse;
import org.example.responses.DarajaStkPushQueryResponse;
import org.example.responses.DarajaStkPushResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;

public class DarajaService
{
    @Value("${daraja.base-url}")
    private String DARAJA_BASE_URL;

    @Value("${daraja.callback-url}")
    private String DARAJA_CALLBACK_URL;

    @Value("${daraja.pass-key}")
    private String DARAJA_PASS_KEY;

    @Value("${daraja.consumer-key}")
    private String DARAJA_CONSUMER_KEY;

    @Value("${daraja.consumer-secret}")
    private String DARAJA_CONSUMER_SECRET;

    @Value("${daraja.business-short-code}")
    private String DARAJA_BUSINESS_SHORT_CODE;

    @Value("${daraja.validation-url}")
    private String DARAJA_VALIDATION_URL;

    @Value("${daraja.confirmation-url}")
    private String DARAJA_CONFIRMATION_URL;

    @Value("${daraja.validation-default-action}")
    private String DARAJA_VALIDATION_DEFAULT_ACTION;

    private final Logger logger = LoggerFactory.getLogger(DarajaService.class);

    public boolean initiateStkPush(String payingPhoneNumber, Integer amount) throws Exception {
        String timestamp = getDarajaTimestamp(new Date());
        String orderId = generateOrderId(213214321);
        String password = getDarjaPassword(timestamp);

        DarajaStkPushRequest request = new DarajaStkPushRequest(DARAJA_BUSINESS_SHORT_CODE, password, timestamp,
                DarajaTransactionType.CustomerPayBillOnline.name(), String.valueOf(amount),
                payingPhoneNumber, DARAJA_BUSINESS_SHORT_CODE, payingPhoneNumber, DARAJA_CALLBACK_URL, orderId, "Subscription");

        HttpPost httpPost = initDarajaPostRequest("mpesa/stkpush/v1/processrequest", new Gson().toJson(request));

        int statusCode;
        String reasonPhrase;

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpPost)) {

            statusCode = response.getCode();
            reasonPhrase = response.getReasonPhrase();
            logger.info("stk reason: " + reasonPhrase);

            boolean isSuccessful = Utils.isSuccessfulStatusCode(statusCode);
            logger.info(" stkPush isSuccessful: " + isSuccessful);
            if (isSuccessful) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                logger.info("stk push response raw: " + jsonResponse);
                DarajaStkPushResponse r = new Gson().fromJson(jsonResponse, DarajaStkPushResponse.class);
                logger.info("stk push response from class: " + new Gson().toJson(r));


                //stk push success
                //

                return true;
            } else {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String errorResponseBody = EntityUtils.toString(response.getEntity());
                    throw new InternalServerErrorException("stkPush error", "An error occurred while pushing the STK push. Please retry later.", new Exception(errorResponseBody));
                }
            }
        }

        throw new InternalServerErrorException("stkPush error", "An error occurred while processing the STK push. Please retry later.", new Exception("error"));
    }

    public DarajaStkPushQueryResponse queryStkPush(String checkoutRequestId) throws Exception {
        String timestamp = getDarajaTimestamp(new Date());
        String password = getDarjaPassword(timestamp);
        DarajaStkPushQueryRequest request = new DarajaStkPushQueryRequest(DARAJA_BUSINESS_SHORT_CODE, password, timestamp, checkoutRequestId);

        HttpPost httpPost = initDarajaPostRequest("mpesa/stkpushquery/v1/query", new Gson().toJson(request));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpPost)) {

            int statusCode = response.getCode();
            String reasonPhrase = response.getReasonPhrase();
            logger.info("query stk reason: " + reasonPhrase);

            boolean isSuccessful = Utils.isSuccessfulStatusCode(statusCode);
            logger.info(" query stk isSuccessful: " + isSuccessful);
            if (isSuccessful) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                logger.info("query stk response raw: " + jsonResponse);
                return new Gson().fromJson(jsonResponse, DarajaStkPushQueryResponse.class);
            } else {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String errorResponseBody = EntityUtils.toString(response.getEntity());
                    throw new InternalServerErrorException("query stk error", "An error occurred while querying the stk push. Please retry later.", new Exception(errorResponseBody));
                }
            }
        }

        throw new InternalServerErrorException("stkPush error", "An error occurred while querying the stk push. Please retry later.", new Exception("error"));
    }

    private DarajaAuthTokenResponse getDarajaAuthorizationToken() throws Exception {
        String credentialsString = DARAJA_CONSUMER_KEY + ":" + DARAJA_CONSUMER_SECRET;
        String credentialsBase64 = Base64.getEncoder().encodeToString(credentialsString.getBytes(StandardCharsets.UTF_8));

        HttpGet httpGet = new HttpGet(DARAJA_BASE_URL + "oauth/v1/generate?grant_type=client_credentials");
        httpGet.addHeader(HttpHeaders.AUTHORIZATION, "Basic " + credentialsBase64);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpGet)) {

            int statusCode = response.getCode();

            boolean isSuccessful = Utils.isSuccessfulStatusCode(statusCode);
            if (isSuccessful) {
                String responseBody = EntityUtils.toString(response.getEntity());
                return new Gson().fromJson(responseBody, DarajaAuthTokenResponse.class);
            } else {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    logger.error("error getDarajaAuthorizationToken -> " + EntityUtils.toString(entity));
                }
                throw new Exception("An error occurred while getDarajaAuthorizationToken " + statusCode);
            }
        }
    }

    private HttpPost initDarajaPostRequest(String url, String jsonRequest) throws Exception {
        DarajaAuthTokenResponse authTokenResponse = getDarajaAuthorizationToken();

        HttpPost httpPost = new HttpPost(DARAJA_BASE_URL + url);
        httpPost.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + authTokenResponse.getAccessToken());
        httpPost.setHeader(HttpHeaders.ACCEPT, "application/json");
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        httpPost.setEntity(new StringEntity(jsonRequest));

        return httpPost;
    }

    private String getDarajaTimestamp(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
        return sdf.format(date);
    }

    private String generateOrderId(int mPesaPaymentId) {
        return Integer.toHexString(mPesaPaymentId).toUpperCase();
    }

    private String getDarjaPassword(String timestamp) {
        //Shortcode+Passkey+Timestamp
        return Base64.getEncoder().encodeToString((DARAJA_BUSINESS_SHORT_CODE + DARAJA_PASS_KEY + timestamp).getBytes(StandardCharsets.UTF_8));
    }
}
