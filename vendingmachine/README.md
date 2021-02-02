	
	1) Objective : 	

	
		The goal is to design a small software component that handles the operation of a coin change Vending Machine. We want you to think in how a Vending Machine works in real-life, and implement methods that provide the interactions that both a customer and a supplier can perform. The functionality that will need to be provided by your software component is described below:	

	1) Accepts only coins of ONEPOUND, TWOPOUND, FIVEPOUND.
	2) Display the coin inventory status.
	3) Vendor could initialize /reset the system with required coin change
	4) User can  register the coins with vending machine
	5) User can get the change(in terms of coins) by providing the user input 
	
	
	2) Solution :
	
	
	The REST Api developed to achieve the coin operations functionality. The modularize code will help to enhance more functionality  at later stage and roll out to other countries . The api documentation will give the technical detailed  about each api request and response specification.

	
	1. The Vending machine will initialize when system start.
	2. Vendor could know the current state of the machine
	3. Vendor could reset the system  with required change.
	4. User could register the coins and it will update the coin inventory
	5. User could get the change(coins) by providing the user input
	
	For every APi call the request has been validate and return the standard HTTP Codes and response to the user. With this approach the consumers who wants to integrate with the  front end/UI application relay on the HTTP Codes and response. In case of failure the the api returns proper error codes and responses.
	
	We also implemented the version(V1,V2,..) of the software, later we enhance the changes to release new version  
	
	The algorithm to solve the coin change uses a dynamic programming approach to calculate if there is a way to return change with a set of coins, and also returns the minimum amount of coins.

	Every part is implemented focusing on readability,modularize,  being easily modified and tested.
	
	3) Decision making: 
	
	  The main requirement is to build  core functionality of coin operation and not focusing on the security. This can be integrate with spring OAuth2 at later stage.
	  
	 for the sake of  demonstration purpose we were using the In memory  to store the coin inventory, later stage we could integrate with database. 
	  
	  
	  
	  
	4) System minimum requirements
	
	
	
	    java version: jdk 1.8
	    maven build tool : maven 3.3
	  
	5) Build : Go to project folder and run the below command
	     vendingmachine> mvn clean install
	     
	      It will generate the jar (build) file  vendingmachine-0.0.1-SNAPSHOT.jar
	
	
	6) Run Spring Boot app using Maven:
	
	     vendingmachine>mvn spring-boot:run
	     
	   Run Spring Boot app with java -jar command
	     Go to vendingmachine/target/ and execute
	
	    java -jar vendingmachine-0.0.1-SNAPSHOT.jar
	    
	    
	8) Api interface swagger documentation url : Replace {localhost} with actual host name
	     url : http://localhost:8080/VendingMachine-Api-Interface.html
	     
	9) Test the APi's from Swagger page. In case of facing the access with swagger page . check the  "Vending machine api interface.pdf".
	
	10) Run the unit test cases  to test vending machine service.
	
	
	
	
	
	        
	
	
	         
	  
	  
	  
	
		
	
		
	