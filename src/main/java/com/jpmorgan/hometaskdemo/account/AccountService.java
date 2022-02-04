package com.jpmorgan.hometaskdemo.account;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class AccountService {

    public AccountService() {
    }

    /**
     * *Note* Some of the logic in both the validateAccountWithoutSource() and the validateAccountWithSource() can
     * be simplified into one method but for simplicity sake I left them as is
     *
     * Method used to validate the bank account number when a validation data source was not given in the API call
     *
     * @param  account  this object has all the data recieved from the api/v1/account/postbody endpoint
     * @param  dataSourceUrls The list of all the defaulted data sources
     * @return Json String    the result of validating the bank account number without a specified data source
     */
    public String validateAccountWithoutSource(Account account, List<String> dataSourceUrls) throws JSONException{
        //Gets the bank account id from the account param
        String accountId = account.getAccountId();

        //JSON's created to store the results of the validation
        JSONObject jsonResult = new JSONObject();
        JSONArray validationArray = new JSONArray();

        //List of data sources passed in as a param
        List <String> dataSources = dataSourceUrls;

        //Counter used to keep track of the number of data sources
        int counter = 1;
        //Loop through the data source url List
        for (String tempSource : dataSources) {
            //creates a new JSON to be entered into the final JSON
            JSONObject item = new JSONObject();
            item.put("source","source" + counter);
            //Call the sourceValidation method to get the boolean value representing
            // whether or not the account is valid for the specific source url
            item.put("isValid",sourceValidation(tempSource,accountId));
            validationArray.put(item);
            ++counter;
        }
        //Add the resulting data from the validation to the final jsonResult to be returned
        jsonResult.put("results", validationArray);
        return jsonResult.toString();
    }


    /**
     * Method used to validate the bank account number when a validation data source was given in the API call
     *
     * @param  account  this object has all the data recieved from the api/v1/account/postbody endpoint
     * @return Json String    the result of validating the bank account number without a specified data source
     */
    public String validateAccountWithSource(Account account) throws JSONException {
        //Gets the bank account id from the account param
        String accountId = account.getAccountId();

        //JSON's created to store the results of the validation
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        //List of data sources passed in as a param
        List <String> sources = account.getSource();

        //Counter used to keep track of the number of data sources
        int sourceCounter = 1;
        //Loop through the data source url List
        for (String tempSource : sources) {
            //creates a new JSON for the validation data that will be added to the final JSON
            JSONObject item = new JSONObject();
            //Call the sourceValidation method to get the boolean value representing
            // whether or not the account is valid for the specific source url
            item.put("source","source" + sourceCounter);
            item.put("isValid", sourceValidation(tempSource,accountId));
            array.put(item);
            ++sourceCounter;
        }

        //Add the resulting data from the validation to the final jsonResult to be returned
        json.put("results", array);
        return json.toString();
    }


    //This method would be used to create the POST request used to validate the account using the data source urls
    /**
     * Method used to validate the bank account number
     *
     * @param  sourceUrl  this String has the url of the data source that will validate he account number
     * @param  accountId  this string is the account number that is going to be validated
     * @return Boolean    the result of validating the bank account number without a specified data source
     *
     * *Note* Since the data sources aren't actually function I created the logic that would handle the
     * real data sources but commented them out. Instead I returned a random boolean to mock the response of the
     * data sources
     */
    public Boolean sourceValidation(String sourceUrl, String accountId ) throws JSONException {

        /*
        RestTemplate template = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        JSONObject account = new JSONObject();
        account.put("accountNumber", accountId);
        HttpEntity<String> request = new HttpEntity<String>(account.toString(), header);
        String url = sourceUrl;
        String response = template.postForObject(url, header, String.class);
        JSONObject json = new JSONObject(response);
        return (Boolean) json.get("isValid");
        */

        Random rand = new Random();
        return rand.nextBoolean();
    }
}
