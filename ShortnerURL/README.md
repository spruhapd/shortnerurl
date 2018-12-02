# Shortener URL


##### Description:

Most of us are familiar with seeing URLs like bit.ly or t.co on our Twitter or Facebook feeds. These are examples of shortened URLs, which are a short alias or pointer to a longer page link. For example, I can send you the shortened URL http://bit.ly/SaaYw5 that will forward you to a very long Google URL with search results on how to iron a shirt.

##### Mandatory Requirements
•	Design and implement an API for short URL creation
•	Implement forwarding of short URLs to the original ones
•	There should be some form of persistent storage
•	The application should be distributed as one or more Docker images

##### Additional Requirements
•	Design and implement an API for gathering different statistics

##### Assessment
Treat this as a real project. It should be readable, maintainable, and extensible where appropriate.
The implementation should preferably be in Java, however any language can be used.
If you will transfer it to another team - it should be clear how to work with it and what is going on.
You should send us a link to a Git repository that we will be able to clone.

##### 1. Architecture
API consist of two parts: configuration and client.

##### 2. Configuration
Using REST call with JSON parameters, call configuration to:
- Build API for Shortener URL
- Register URL
- Redirect to actual URL

###### 3. Register URL
HTTP method | POST
URI | /regshorturl
Request Type | application/json
Request Headers | Set Authorization header, and authorize user
Request Body | JSON object with following parameters:	<ul><li>`url (required, url to be shorten)`</li><li>`redirectType : 301 | 302 (not required, default 302)`</li></ul> 
*Example: {"url":"https://www.google.com/search?source=hp&ei=idIDXNfvEPL0xgPq3IjQDA&q=failure+to+transfer+org.apache.maven.plugins%3Amaven-resources-plugin%3Apom%3A2.6+from+https%3A%2F%2Frepo.maven.apache.org%2Fmaven2+was+cached+in+the+local+repository%2C&oq=&gs_l=psy-ab.1.2.35i39l6.0.0..6714...1.0..0.130.130.0j1......0......gws-wiz.....6.CHyCnHb0PVg","redirectType":"301"}*
Response Type | application/json
Response | Response parameters in case of successful registration are following: <ul><li>`shortUrl`</li></ul> *Example: {"shortUrl":"http://localhost:8080/ShortnerURL-0.0.1-SNAPSHOT/lHeaX1"}*

##### 4.	Redirect
Redirect client to configured address with configured HTTP status.

##### 5. General requirements
-	Use Java programming language
-	rest is used in implementation to handle the request and response
- Application must not require any additional configuration, that is, it must not contain any external dependencies. 
- SQLlite used to persist the url details.
- Create help page.
- Delivered the code using maven project

### Solution

Created a spring project using maven
Added required dependencies in pom.xml
Build the project as maven project
Deploy the application in Tomcat
