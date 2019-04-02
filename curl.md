#Curl commands for this project:

* get all meals

curl -X GET \
  http://localhost:8080/topjava/rest/meals/ \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -H 'cache-control: no-cache' \

---------------
* filter meals by date\time for ex. get breakfasts & lunches:

curl -X POST \
  http://localhost:8080/topjava/rest/meals/filter \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -H 'cache-control: no-cache' \
  -d 'startDate=&endDate=&startTime=10:00&endTime=15:00'
-----------
* create new meal:

curl -X POST \
  http://localhost:8080/topjava/rest/meals/create \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{"id":"","dateTime":"2019-08-04T13:32:54","description":"my new meal","calories":"700"}'
-----------
* update an existed meal

curl -X PUT \
  http://localhost:8080/topjava/rest/meals/100003 \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{"id":"100003","dateTime":"2017-08-04T13:32:54","description":"my updated meal","calories":"700"}'
-----------

* get meal by id

curl -X GET \
  http://localhost:8080/topjava/rest/meals/100003 \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache'