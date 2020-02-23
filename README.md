# get-exchange-rate-service

This service serves the queries[CQRS] for latest and historical exchange rates. 

- The messages generated from [generate-exchange-rate-service](https://github.com/tdhraik/generate-exchange-rate) is consumed in this service and then written to local mongo.

- This service also makes synchronous REST API calls to [generate-exchange-rate-service](https://github.com/tdhraik/generate-exchange-rate) when local mongo doesn't have any
latest or historical exchange rates data.

- Following end points could be used to interact with the system - 


1. **http://localhost:8192/exchange/latest**

    This end point returns the latest exchange rate.

2. **http://localhost:8192/exchange/historic?from=2020-02-10&to=2020-02-18**

    This end point returns the exchange rates from the given range

3. **http://localhost:8192/admin/refresh**

    This end point should be used when latest job configuration is changed in the [configuration artifact](https://github.com/tdhraik/configurations)
    This makes a POST call to generate-exchange-rate-service so that the updated configurations could be read and 
    scheduler is appropriately reset.


### Steps to dynamically configure the check period ( or the cron expression for the job that checks the latest exchange rate )

1. All the applications should be up and running ( :) ) that's most important ;)

2. Update the cron expression in this configuration file https://github.com/tdhraik/configurations/blob/master/services/generate-exchange-rate.yml

3. Hit the refresh end point http://localhost:8192/admin/refresh

4. The jobs should now run with new cron expression, it could be checked through the logs.




