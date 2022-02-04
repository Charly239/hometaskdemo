# hometaskdemo

Home Task 
You need to implement a rest service that accepts requests to validate a bank account and returns information if the requested account is valid. The  service doesn't store any data but instead sends validation requests to other account data sources, aggregates this data and returns to the caller. You DO  NOT need to implement data sources, only the service. You can assume those data source exist and you are given their names and api urls. 
Request has one mandatory field - bank account number.  

It may have an optional field "source", which is a list of names of data source used to query information. If this field is empty then all sources defined in the  application's configuration must be used. 
Data sources are defined in the application's configuration and must not be written in the code. Every source accepts requests with one mandatory field -  bank account number. Data sources return messages with only one field "isValid", which is boolean. 
You don't need to implement data sources, only the service that polls data sources. 

Response is an array of objects, each object has two fields: source and isValid. Source is a string and is the name of a data source, isValid is a boolean  value that data source returned.

Requirements: 
1.  Rest api, all messages in json. 
2.  Spring boot app. 
3.  Sufficient tests to demonstrate the app is working correctly. 
4.  Data sources' url are set as properties and must not be stored in code. Demonstrate how the urls can be set for production and non-production  environments.  
5.  The rest api should return response within 2 seconds. It is guaranteed that all external data sources will return data within 1 second. 6.  


The code is uploaded to github. Please share a link to the code with your recruiter. 
You don't need to implement authentication, authorization, swagger documentation; no need to deploy it anywhere, passing tests and running the app  locally more than enough. 
It is expected that this task will take 1-2 hours to implement at maximum. 

Example: 
Application configuration: 
sources: 
 - name: source1 
 url: https://source1.com/v1/api/account/validate 
 - name: source2 
 url: https://source2.com/v2/api/account/validate


Incoming request: 
{  
 "accountNumber": "12345678", 
 "sources": [  
 "source1",  
 "source2"  
 ] 
}



Request to a data source
{ 
 "accountNumber": "12345678" 
}



Response from a data source 
{ 
 "isValid": true 
}



Response from the service
{  
 "result": [ 
 { 
 "source": "source1",  
 "isValid": true 
 }, 
 { 
 "source": "source2",  
 "isValid": false 
 } 
 ] 
}


