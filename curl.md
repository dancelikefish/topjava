#Curl commands for this project:
####MEALS:
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
  -----------
  ###USERS:
  
  * get all users
 
 curl -X GET \
   http://localhost:8080/topjava/rest/admin/users \
   -H 'Content-Type: application/json' \
   -H 'cache-control: no-cache' 
   
-----------
  * get user
  
  curl -X GET \
    http://localhost:8080/topjava/rest/admin/users/100000 \
    -H 'Content-Type: application/json' \
    -H 'cache-control: no-cache'
------------
* get user by email

curl -X GET \
  'http://localhost:8080/topjava/rest/admin/users/by?email=user@yandex.ru' \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache'   
  ----------
  * delete user
  
  curl -X DELETE \
    http://localhost:8080/topjava/rest/admin/users/100000 \
    -H 'Content-Type: application/json' \
    -H 'cache-control: no-cache'
   ----------
* update user

curl -X PUT \
  http://localhost:8080/topjava/rest/admin/users/100001 \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{"id":"100001","email":"user@gmail.com","name":"newUser","enabled":true,"caloriesPerDay":2500,"password":"newpassword"}'    
 ------------
 * add new user
 
 curl -X POST \
   http://localhost:8080/topjava/rest/admin/users \
   -H 'Content-Type: application/json' \
   -H 'cache-control: no-cache' \
   -d '{"id":"null","email":"newuser@gmail.com","name":"newUser","enabled":true,"caloriesPerDay":2500,"password":"newpassword"}'

