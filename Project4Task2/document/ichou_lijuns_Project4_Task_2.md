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
```
The application will query related GIF files based on the user's input information and return a set of five related GIF files. 
Upon receiving a GIF file from the server, the application displays the file. 
The returned Gif URL can be copied and pasted into other social media applications.
```

## Distributed Application Requirements
### 1. Implement a native Android application
```
The name of the native Android application project in Android Studio is: **GifBot**
```
#### a. Has at least three different kinds of views in your Layout (TextView, EditText, ImageView, etc.)

The application uses TextView, EditText, Button, and ImageView.  
Refer to "content_main.xml" for details of how they are arranged in the Layout.

Below is a screenshot of the Android layout after starting the application

(Screenshot)



#### b.Requires input from the user

Below is a screenshot of the user searching for a GIFs of a 'dog'.

(Screenshot)


#### c. Makes an HTTP request (using an appropriate HTTP method) to your web service

The application does an HTTP GET request in GetGif.java. The HTTP request is:
(add url...)
as the case of searching a dog GIFs.

The search method makes this request and parses the returned JSON to
find the GIFs URL, fetches the GIFs, and returns the GIFs of the picture.

#### d. Receives and parses an XML or JSON formatted reply from the web service

An example of the JSON reply is:
(add code)

#### e. Displays new information to the user

Below is the screen shot after the five GIFs has been returned.

(Screenshot)


#### f. Is repeatable (I.e. the user can repeatedly reuse the application without restarting it.)

The user can type in another search term and hit Submit.  
Below is an example of searching "cat" after seaching dog GIFs.

(Screenshot)


### 2. Implement a web application, deployed to Heroku
```
The URL of the web service deployed to Heroku is:
(heroku url)
The project directory name is Project4Task2.
```
#### a. Using an HttpServlet to implement a simple (can be a single path) API
In the web app project:
Model: (name).java
View: (name).jsp
Controller: (name).java

#### b. Receives an HTTP request from the native Android application

(ServerServlet Name).java receives the HTTP GET request with the argument "search".  
It passes the search string on to the model.


#### c. Executes business logic appropriate to your application

(Model Name).java makes an HTTP request to:
(API url)
It then parses the JSON response and extracts the parts it needs to respond to the Android application.

#### d. Replies to the Android application with an XML or JSON formatted response.

Response.jsp formats the response to the mobile application in a simple XML format of the design:
(code)

### 3. Handle error conditions - Does not need to be documented.
- Invalid mobile app input
- Invalid server-side input (regardless of mobile app input validation)
- Mobile app network failure, unable to reach server
- Third-party API unavailable
- Third-party API invalid data

### 4. Log useful information - Itemize what information you log and why you chose it.

- Request from the mobile phone
  To get search string **(same as Parameters list below?)**
- Information about the request and reply to the 3rd party API
  To set input and get output for the 3rd party API
- Information about the reply to the mobile phone
  To show result to the users **(same as Parameters list below?)**
- Information can include such parameters as what kind of model of phone has made the request
  To know what mobile devices do frequent user have 
- Timestamps for when requests are received
  Track time for performace evaluation in the future
- Timestamps for when requests sent to the 3rd party API
  Track time for performace evaluation in the future
- Timestamps for when the data sent in the reply back to the phone
  Track time for performace evaluation in the future
- Parameters included in the request specific to your application (**search string**)
  To see what's the most popular search keyword.
- Parameters included in the request specific to your application (**API url**)
  To fetch GIFs view for user
- Parameters included in the request specific to your application (User uage time)
  (TBD. Can we track logout-login time though we don't have account system?)
- Parameters included in the request specific to your application (Copy url from which of five choices, record top1-5 choices)
  (TBD. Does people tend to copy the first one? though it's random picked from the source)
- (TBD)

(Note: You should NOT log data from interactions from the operations dashboard.)

### 5. Store the log information in a database - Give your Atlas connection string with the three shards
The web service can connect, store, and retrieve information from a MongoDB database in the cloud.

(Add code)


### 6. Display operations analytics and full logs on a web-based dashboard - Provide a screen shot.
#### a. A unique URL addresses a web interface dashboard for the web service. 
Url: (dashboard url)

#### b. The dashboard displays at least 3 interesting operations analytics. 
Below is a screenshot of the interesting operations analytics in the dashboard.
(add reasoning)

- The most popular search keyword
(screenshot, List top 20 search keyword)
(screenshot, if we have time, we can generate word cloud.Just a thought. That would be another api....lol, https://github.com/kennycason/kumo)

- Top 5 GIFs Search Result
(screenshot)

- Service Response Time
  (Table lists latest 100 history data)
  Average: ms
  Maximum: ms
  Minimum: ms

- Frequent User Devices
  (screenshot)
  1.iphone 13
  2.Samsung Galaxy S20
  3....

- User Usage Time (TBD)
  (Date, Time)
  Average: ms
  Maximum: ms
  Minimum: ms

#### c. The dashboard displays formatted full logs.

Below is a screenshot of the formatted full logs in the dashboard.
(screenshot)


### 7. Deploy the web service to Heroku

Below is a screenshot of the application deployment on the Heroku.
(screenshot)


