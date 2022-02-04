package com.jpmorgan.hometaskdemo.account;
//Library imports to execute application
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/account")
/*
 *This AccountController class is used to to create rest Endpoints and handles higher level logic
 */
public class AccountController {
    //Retrieve data source URLs from application.properties
    @Value("${dataSourceUrls}")
    List<String> dataSourceUrls;
    //Instantiate AccountService to be used in the AccountController
    private final AccountService accountService;

    //AccountController constructor
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    //Designate the condition for the endpoints
    //*Note* configure the PostMapping to only accept and return data in a JSON formatI
    @PostMapping(
            value = "/postbody",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    //The RequestBody is converted into an Account class
    public String getAccountValidation(@RequestBody Account account) throws JSONException {
        //Logic to check if a data source was added to the body of the endpoint call
        if(account.getSource().equals(null)||account.getSource().isEmpty()){
            return accountService.validateAccountWithoutSource(account,dataSourceUrls); /* Data source was not provided */
        }else {
            return accountService.validateAccountWithSource(account);                   /* Data source was provided */
        }

    }
}
