package util;

public class ShortenerURLHelpConstants {
	/**
	 * /help page
	 */
	public static final String HELP_PAGE = "<style>\n" +
			"table,th,td\n" +
			"{\n" +
			"\tborder:1px solid black;\n" +
			"\tborder-collapse:collapse;\n" +
			"}\n" +
			"th,td\n" +
			"{\n" +
			"\tpadding:5px;\n" +
			"}\n" +
			"</style>\n" +
			"<h3>Shortener URL</h3>\n" +
			"<ul>\n" +
			"\t<li>API to create a Short URL</li>\n" +
			"\t<li>Register Short URL</li>\n" +
			"\t<li>Redirect Short URL to actual URL</li>\n" +
			"</ul>\n" +
			"This API is implemented using the REST call with JSON as a input parameters.\n" +
			"<br/>\n" +
			"<br/><b>Installation of API</b>\n" +
			"<ul>\n" +
			"\t<li>This API is implemented without having any external dependencies, so no extra installation is required for this API to use.</li>\n" +
			"\t<li>All the required dependencies are already mentioned in the pom.xml file. Once the api build successfully all the required dependencies get downloaded.</li>\n" +
			"</ul>\n" +
			"<b>Running API</b>\n" +
			"<ol>\n" +
			"\t<li>Start a web server (tested using Tomcat 7.0).</li>\n" +
			"\t<li>Build the API using 'maven clean install'.</li>\n" +
			"\t<li>Deploy the service war file to the web server.</li>\n" +
			"</ol>\n" +
			"<b>Testing of API</b>\n" +
			"<br/>Use a client-side application (e.g. POSTMASTER) to access following functionalities. All request and response types are application/json.\n" +
			"<ol>\n" +
			"<li>\n" +
			"\t<u>Registering URL</u>\n" +
			"\t<br/> Method: <b>POST</b>\n" +
			"\t<br/> URI: <b>/regshorturl</b>\n" +
			"\t<br/>Request Header: <b>Authorization</b>\n" +
			"\t<br/>Request Body: <b>url</b>, <b>redirectType</b>\n" +
			"\t<ul>\n" +
			"\t\t<li>Example: {\"url\":\"https://www.google.com/search?source=hp&ei=idIDXNfvEPL0xgPq3IjQDA&q=failure+to+transfer+org.apache.maven.plugins%3Amaven-resources-plugin%3Apom%3A2.6+from+https%3A%2F%2Frepo.maven.apache.org%2Fmaven2+was+cached+in+the+local+repository%2C&oq=&gs_l=psy-ab.1.2.35i39l6.0.0..6714...1.0..0.130.130.0j1......0......gws-wiz.....6.CHyCnHb0PVg\",\"redirectType\":\"301\"}</li>\n" +
			"\t</ul>\n" +
			"\tResponse Body: <b>shortUrl</b>\n" +
			"\t<ul>\n" +
			"\t\t<li>Example: {\"shortUrl\":\"http://localhost:8080/ShortnerURL-0.0.1-SNAPSHOT/lHeaX1\"}\n" +
			"\t</ul>\n" +
			"\tDescription\n" +
			"\t<ul>\n" +
			"\t\t<li>\"url\" is mandatory parameter. \"redirectType\" is not, it should be 301, but if set as anything else, then it's 302 .\n" +
			"\t\t<br/>\"shortUrl\" is return in response. Response Body is set empty if it fails, and return status as 401.\n" +
			"\t\tResponse Body is also set to empty when \"url\" is empty after trimming. In that case status 415 is set. \n" +
			"\t\tIf \"url\" was already registered it is updated and status is set to 201, else it is created and status is set to 200.</li>\n" +
			"\t</ul>\n" +
			"</li>\n" +
			"<li>\n" +
			"\t<u>Redirecting Short URL</u>\n" +
			"\t<br/>Description\n" +
			"\t<ul>\n" +
			"\t\tEntering shortUrl provided in response in browser this will redirected to the actual URL which its mapped in database and number of calls for that url is incremented by 1.\n" +
			"\t</ul>\n" +
			"</li>\n" +
			"</ol>";
}
