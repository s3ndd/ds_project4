# Project 4 Task 2 – Distributed Application and Dashboard

## Team
#### Team Member 1
- **Name:** Zoey Chou
- **AndrewID:** ichou
- **Email:**  ichou@andrew.cmu.edu

#### Team Member 2
- **Name:** Sheldon Shi
- **AndrewID:** lijuns
- **Email:**  lijuns@andrew.cmu.edu

## Description
```txt
The application will query related GIF files based on the user's input information and return a set of five related GIF files. 
Upon receiving a GIF file from the server, the application displays the file. 
The returned GIF link will be displayed under each GIF.
```

## Distributed Application Requirements
### 1. Implement a native Android application
The name of the native Android application project in Android Studio is: **Project4Task2AndroidApp** .
The project folder is: **Project4Task2AndroidApp**


#### a. Has at least three different kinds of views in your Layout (TextView, EditText, ImageView, etc.)
The application uses **TextView**, **SearchView**, **ImageView**, and **ListView**.
Refer to **activity_gifbot.xml** and **gif_list_item.xml** for details of how they are arranged in the Layout.

Below is a screenshot of the Android layout after starting the application

![Start_GIFBot.jpg](_resources/Start_GIFBot.jpg)

#### b.Requires input from the user
Below is a screenshot of the user searching for a GIFs of a 'ship it'.

![Screenshot_20221121-103217_GIFBot.jpg](_resources/Screenshot_20221121-103217_GIFBot.jpg)

#### c. Makes an HTTP request (using an appropriate HTTP method) to your web service
The application does an HTTP GET request in **GIFProcessor.java**. The HTTP request is:
**https://ds-project4-gifbot.herokuapp.com/Project4Task2WebService-1.0-SNAPSHOT/api/v1/gif?search=ship%20it** as the case of searching a `ship it` GIFs.
It also sends the following data as the http headers for the statistic use.
```
Manufacture: samsung
Brand: samsung
Model: SM-A226B
AndroidVersion: 12
```

The search method makes this request using **OkHttpClient** and parses the returned JSON with **Jackson** to find the GIFs URL, fetches the GIFs, and returns the GIFs of the picture.

#### d. Receives and parses an XML or JSON formatted reply from the web service
- The response JSON from the web service
```json
{
	"gifs": [
		"https://media.tenor.com/exU0RKqTu00AAAAM/i-see-it-i-ship-it.gif",
		"https://media.tenor.com/1Hoy6ABLpIcAAAAM/binoculars-ship.gif",
		"https://media.tenor.com/jllngY0wdqwAAAAM/i-ship-it.gif",
		"https://media.tenor.com/pAc4dATt5E4AAAAM/i-ship-it-yes.gif",
		"https://media.tenor.com/QG9WKduTbw4AAAAM/ship-it.gif",
		"https://media.tenor.com/y5dXmGmoBCQAAAAM/msftgarage-microsoftgarage.gif",
		"https://media.tenor.com/NtLKdRlH9HgAAAAM/i-ship-it-shipped.gif",
		"https://media.tenor.com/IfifYf4hdE4AAAAM/shipit-ship.gif",
		"https://media.tenor.com/6spYha10OAgAAAAM/shipit-revert.gif",
		"https://media.tenor.com/H5Ml6GdT12cAAAAM/i-ship-it-ship-it.gif"
	],
	"statusCode": 200
}
```


#### e. Displays new information to the user

Below is the screen shot after the GIFs has been returned.

![Screenshot_20221121-103246_GIFBot.jpg](_resources/Screenshot_20221121-103246_GIFBot.jpg)


#### f. Is repeatable (I.e. the user can repeatedly reuse the application without restarting it.)

The user can type in another search term and hit Submit.  
Below is an example of searching **lgtm** after seaching **ship it** GIFs.


![Screenshot_20221121-110403_GIFBot.jpg](_resources/Screenshot_20221121-110403_GIFBot.jpg)


![Screenshot_20221121-110429_GIFBot.jpg](_resources/Screenshot_20221121-110429_GIFBot.jpg)



### 2. Implement a web application, deployed to Heroku
The URL of the web service deployed to Heroku is: **ds-project4-gifbot**
The project directory name is **Project4Task2WebService**.

#### a. Using an HttpServlet to implement a simple (can be a single path) API
There are two parts in the web application project, one is for the endpoint **/api/v1/gif** to search gifs based on the user input, and the other one is for the endpoint **/dashboard** to generate and display the dashboard page.

The endpoint **/api/v1/gif**
Model :
```
model
├── ClientRequestInfo.java
├── ClientResponseInfo.java
├── DeviceInfo.java
├── ErrorResponse.java
├── GIFsResponse.java
├── IResponse.java
├── Latency.java
├── LogEvent.java
├── tenor
│   ├── TenorMediaFormat.java
│   ├── TenorResponse.java
│   ├── TenorResult.java
│   └── TenorTinyGif.java
├── TenorRequestInfo.java
├── TenorResponseInfo.java
```
Controller: 
```
web
└── GIFBotServlet.java
```

Service:
```
service
├── GIFBotService.java
└── LoggingService.java
```

Repository:
```
repository
├── LoggingRepository.java
└── MongoDBClient.java
```
The endpoint **/dashboard**
Model: 
```
model
├── Dashboard.java
├── Latency.java
├── LogEvent.java
├── SystemLog.java
└── TopDeviceInfo.java
```
Controller: 
```
web
├── DashboardServlet.java
```
View:
```
webapp
├── index.jsp
```
Service:
```
service
├── DashboardService.java
└── LoggingService.java
```
Repository:
```
repository
├── LoggingRepository.java
└── MongoDBClient.java
```


#### b. Receives an HTTP request from the native Android application

**GIFBotService.java** receives the HTTP GET request with the argument "search".  
It passes the search string on to the **GIFBotService.java** to search the gifs from the 3rd party api.

#### c. Executes business logic appropriate to your application

**GIFBotService.java** makes an HTTP request to:
**https://tenor.googleapis.com/v2/search**
It then parses the JSON response and extracts the parts it needs to respond to the Android application.

#### d. Replies to the Android application with an XML or JSON formatted response.
**GIFBotService.java** formatted the response based on the resopnse of the 3rd party api. If the the 3rd party api responds successfully, it will return the following JSON resoponse to the mobile app.
```json
{
	"gifs": [
		"https://media.tenor.com/exU0RKqTu00AAAAM/i-see-it-i-ship-it.gif",
		"https://media.tenor.com/1Hoy6ABLpIcAAAAM/binoculars-ship.gif",
		"https://media.tenor.com/jllngY0wdqwAAAAM/i-ship-it.gif",
		"https://media.tenor.com/pAc4dATt5E4AAAAM/i-ship-it-yes.gif",
		"https://media.tenor.com/QG9WKduTbw4AAAAM/ship-it.gif",
		"https://media.tenor.com/y5dXmGmoBCQAAAAM/msftgarage-microsoftgarage.gif",
		"https://media.tenor.com/NtLKdRlH9HgAAAAM/i-ship-it-shipped.gif",
		"https://media.tenor.com/IfifYf4hdE4AAAAM/shipit-ship.gif",
		"https://media.tenor.com/6spYha10OAgAAAAM/shipit-revert.gif",
		"https://media.tenor.com/H5Ml6GdT12cAAAAM/i-ship-it-ship-it.gif"
	],
	"statusCode": 200
}
```
Otherwise, it will retrun an error resoponse as following JSON.
```json
{
	"message": "invalid input parameter",
	"statusCode": 400
}
```

### 3. Handle error conditions - Does not need to be documented.
- Invalid mobile app input
	- If the user input is invalid, the service returns the error message on the screen	
- Invalid server-side input (regardless of mobile app input validation)
	- If the query parameter `search` is invalid(empty or longer than 50 characters), it will return the error response as follows:
```json
{
	"message": "invalid input parameter",
	"statusCode": 400
}
```
- Mobile app network failure, unable to reach server
	- The app will display an error message
- Third-party API unavailable
- Third-party API invalid data
	- Both of the above will be handle on the the exception cause section.
```java
// GIFBotService.java, search method
catch (IOException e) {
            return new ErrorResponse(e.getMessage(), HttpServletResponse.SC_BAD_GATEWAY);
        }
```


### 4. Log useful information - Itemize what information you log and why you chose it.
- Request information from the mobile phone
	- This information can be use to record the details of the request. When there is an issue, the developer can know what the user's input. 
	- The serarch term is also used for the statistic analysis.
- Request device information
	- This can help resolve the issuse with the particular user's device
	- It is also used for the statistic analysis.
- Response to the mobile
	- This information is useful for track the content sent to the mobile
	- It is also used for the statistic analysis.
- Request information to the 3rd party API
	- When the 3rd party API returns an error, this log can help the develop know what it sent to the 3rd party.
- Resopnse from the 3rd party API
	- When there is an issue about the response, it can know the raw response from the 3rd party API.
- The latency of the web service
	- This can help the develop know the performance of the system.
	- It is also used for the statistic analysis.
- The latency of the 3rd party API
	- This can help the develop know the performance of the 3rd party.
	- It is also used for the statistic analysis.
- Date and time for each request and response
	- When there is an issue, it can help to find the correct information based on the time.

### 5. Store the log information in a database 
The web service can connect, store, and retrieve information from a MongoDB database in the cloud.
- MongoDBClient.java handles the connect to the MongoDB and it is a singleton.
```java
package edu.cmu.andrew.project4task2webservice.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBClient {
    // mongodb connecting string
    private final static String CONNECTION_STRING = "mongodb://dsteamproject4:JyHsGiSpAuU44rrL@ac-3jlcg4c-shard-00-01" +
        ".y3xuxkb.mongodb.net:27017,ac-3jlcg4c-shard-00-02.y3xuxkb.mongodb.net:27017,ac-3jlcg4c-shard-00-00" +
        ".y3xuxkb.mongodb.net:27017/myFirstDatabase?w=majority&retryWrites=true&tls=true&authMechanism=SCRAM-SHA-1";

    // create instance of MongoDBClient
    private static MongoDBClient instance = null;

    // create MongoClient object
    private final MongoClient client;

    // constructor of MongoDBClient
    private MongoDBClient() {
        client = MongoClients.create(CONNECTION_STRING);
    }

    // get instance of MongoDBClient
    public static synchronized MongoDBClient getInstance() {
        // check if MongoClient instance is null, then create new one
        if (instance == null) {
            instance = new MongoDBClient();
        }
        return instance;
    }

    // get MongoClient object
    public MongoClient getClient() {
        return client;
    }
}


```

- LoggingRepository.java handles the log insertion and query.
	- In this web server, it use the `upsert` to handle the log. It will insert a new log if the log with same id is not in the database. Otherwise, it will update the record to add more log information.
	- `_id` is an **UUID** as the request id. For each request, the request id is used for the whole processing. So that there is one log record for each request.
```java
// part of the LoggingRepository.java code
/**
 * upsert the logEvent data to the MongoDB
 *
 * @param logEvent
 * @return
 */
public Document upsert(LogEvent logEvent) {
	// define the filer with _id
	Bson filter = Filters.eq("_id", logEvent.getRequestID());
	// set up the find and update option
	FindOneAndUpdateOptions upsertOptions = new FindOneAndUpdateOptions();
	upsertOptions.returnDocument(ReturnDocument.AFTER);
	upsertOptions.upsert(true);
	// do the inert or update
	return getCollection().findOneAndUpdate(filter, toBson(logEvent), upsertOptions);
}
```

### 6. Display operations analytics and full logs on a web-based dashboard
#### a. A unique URL addresses a web interface dashboard for the web service. 
- URL: (both are diected to the same page)
	- **https://ds-project4-gifbot.herokuapp.com/Project4Task2WebService-1.0-SNAPSHOT/**
	- **https://ds-project4-gifbot.herokuapp.com/Project4Task2WebService-1.0-SNAPSHOT/dashboard**

#### b. The dashboard displays at least 3 interesting operations analytics. 
Below is a screenshot of the interesting operations analytics in the dashboard.

![Screenshot from 2022-11-21 11-54-28.png](_resources/Screenshot from 2022-11-21 11-54-28.png)

- Top Five Popular Keywords, Top Five Popular GIFs

![Screenshot from 2022-11-21 11-58-32.png](_resources/Screenshot from 2022-11-21 11-58-32.png)

- Top Device: Top Mobile Manufacture,  Top Mobile Brand, Top Mobile Model, Top Android Version

![Screenshot from 2022-11-21 11-58-43.png](_resources/Screenshot from 2022-11-21 11-58-43.png)

- Latency: Service Latency, Recent Service Latency(1-5) (ms), External API Latency, Recent External API Latency(1-4) (ms)

![Screenshot from 2022-11-21 11-58-50.png](_resources/Screenshot from 2022-11-21 11-58-50.png)

#### c. The dashboard displays formatted full logs.
Below is a screenshot of the formatted full logs in the dashboard.

![Screenshot from 2022-11-21 12-00-14.png](_resources/Screenshot from 2022-11-21 12-00-14.png)


### 7. Deploy the web service to Heroku
Below is a screenshot of the application deployment on the Heroku.
![Screenshot from 2022-11-21 12-01-23.png](_resources/Screenshot from 2022-11-21 12-01-23.png)

In this project, we use the Github to do the code version control and team collabration. For the Heroku deployment, the project uses the Github Actions to do the continuous delivery. For each merge to the main branch, the deployment will be triggered and run automatically. 
- Dockerfile for Heroku
```shell
# Use Tomcat 10, which supports Servlets 5
FROM tomcat:10.1.0-M5-jdk16-openjdk-slim-bullseye

# This provides better support for running the JVM in a container
ENV JAVA_OPTS="-Xmx300m"

# Expose port 8080 when running on localhost
EXPOSE 8080

# Copy in our ROOT.war to the right place in the container
COPY target/Project4Task2WebService-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

# LOCALHOST:  Run catalina in the container
# Comment the next line out if running on Heroku
#CMD ["catalina.sh", "run"]

# HEROKU: Run catalina in a container on Heroku
# Copy tomcat_starter.sh to the container; will executed when deployed
# Comment the next two lines if running on Localhost
COPY heroku/tomcat_starter.sh /home/
CMD chmod +x /home/tomcat_starter.sh; /home/tomcat_starter.sh
```
- Code for Github workflow
```yaml
name: Heroku Deployment
on:
  push:
    branches: [ main ]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - name: Git Checkout
        uses: actions/checkout@v1
      - name: Set up JDK 11
        uses: actions/setup-java@v1
        with:
          java-version: 11
      - name: Build with Maven
        run: mvn -B package --file Project4Task2WebService/pom.xml
      - name: Heroku Container Registry login
        env: 
          HEROKU_API_KEY: ${{ secrets.HEROKU_API_KEY }}
        working-directory: Project4Task2WebService/heroku
        run: heroku container:login
      - name: Build and push
        env:
          HEROKU_API_KEY: ${{ secrets.HEROKU_API_KEY }}
        working-directory: Project4Task2WebService/heroku
        run: heroku container:push -a ${{ secrets.HEROKU_APP_NAME }} web --context-path=.. 
      - name: Release
        env:
          HEROKU_API_KEY: ${{ secrets.HEROKU_API_KEY }}
        working-directory: Project4Task2WebService/heroku
        run: heroku container:release -a ${{ secrets.HEROKU_APP_NAME }} web 
             
```

- Screenshot for Github Actions


![Screenshot from 2022-11-21 12-07-59.png](_resources/Screenshot from 2022-11-21 12-07-59.png)



