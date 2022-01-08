# Siesma Employee Application

## Build

To build the App run below

```
 ./gradlew clean build
```
![alt text](https://github.com/AishwaryaChavan12/SeismaEmployeeApplication/blob/main/demo/1.png)

## Run Application
To run the App use below command

```
 java -jar build/libs/employee-0.0.1-SNAPSHOT.jar  
```
![alt text](https://github.com/AishwaryaChavan12/SeismaEmployeeApplication/blob/main/demo/2.png)

## API's
### POST /employee/payslip/generate
#### Sample Request
```javascript
[
  {
  	"firstName":"David",
   	"lastName":"Rudd",
   	"annualSalary":60050,
  	"paymentMonth":2,
  	"superRate":0.09
  },
  {
   	"firstName":"Ryan",
   	"lastName":"Chen",
   	"annualSalary":120000,
   	"paymentMonth":2,
   	"superRate":0.1
    
  }
]

```
#### Sample Response
```javascript
[
    {
        "employee": {
            "firstName": "David",
            "lastName": "Rudd",
            "annualSalary": 60050,
            "paymentMonth": 2,
            "superRate": 0.09
        },
        "fromDate": "1 February",
        "toDate": "28 February",
        "grossIncome": 5004,
        "incomeTax": 922,
        "superannuation": 450,
        "netIncome": 4082
    },
    {
        "employee": {
            "firstName": "Ryan",
            "lastName": "Chen",
            "annualSalary": 120000,
            "paymentMonth": 2,
            "superRate": 0.1
        },
        "fromDate": "1 February",
        "toDate": "28 February",
        "grossIncome": 10000,
        "incomeTax": 2669,
        "superannuation": 1000,
        "netIncome": 7331
    }
]
```
![alt text](https://github.com/AishwaryaChavan12/SeismaEmployeeApplication/blob/main/demo/3.png)

### POST /employee/payslip/generate/csv
#### Sample Request
CSV File input
```javascript
Monica,Tan,60050,0.09,3
Brend,Tulu,120000,0.1,3
```

#### Sample Response
CSV File Output
```javascript
Name,Pay Period,Gross Income,Income Tax,Net Income,Super
Monica Tan,1 March - 31 March,5004,922,4082,450
Brend Tulu,1 March - 31 March,10000,2669,7331,1000
```
![alt text](https://github.com/AishwaryaChavan12/SeismaEmployeeApplication/blob/main/demo/4.png)

