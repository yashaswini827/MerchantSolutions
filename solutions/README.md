Trading Application
The Trading Application is designed to process signals using the Algo library provided by the Algo team.
It receives signals via HTTP and executes the corresponding Algo based on the received signal.

Getting Started:
These instructions will help you set up and run the Trading Application component on your local machine 
for development and testing purposes.

Prerequisites:
To run this project, you will need the following:
Java Development Kit (JDK) 8 or higher
Maven
Spring Boot

Installation and Usage:
1.Clone the project from the GitHub repository: https://github.com/yashaswini827/MerchantSolutions.git
3.Build the project using Maven
4.Run the application

The application will be accessible at http://localhost:8080

Endpoints:
GET /signals/{signal}
Replace {signal} with the specific signal identifier or value you want to use when running the algorithm.

POST /signals/create
sample request:
{
			"signalNumber": 2,
			"actions": [
				{
					"actionType": "reverse"
				},
				{
					"actionType": "setAlgoParam",
					"params": [
						{
							"paramNumber": 1,
							"paramValue": 80
						}
					]
				},
				{
					"actionType": "submitToMarket"
				}
			]
}
		
Above is a sample request to insert the signal data to the DB.

Testing
The project includes comprehensive unit tests using JUnit and Mockito to ensure the requirements are met.

Improvements:
*The project currently uses an H2 in-memory database for testing purposes. 
It should be replaced with a more appropriate database for real-time use.

*The current implementation of the POST /signals/create API is simple and straightforward.
It can be enhanced by separating entities and DTOs into separate files and creating a dedicated service layer.
However, for now, the focus is primarily on improving the GET /signals/{signal} API to streamline codebase and class creation.


Contributor
Yashaswini


		