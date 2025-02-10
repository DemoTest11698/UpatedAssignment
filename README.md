Technologies Used Spring Boot: Framework for creating the backend application. Spring Web: For building RESTful web services. Spring Data JPA: For working with relational databases. JUnit 5: For unit testing. Mockito: For mocking dependencies in tests. MySql Database: For persisting data Setup and Installation To get this application up and running, follow the steps below:

Clone the repository bash Copy Edit git clone (https://github.com/DemoTest11698/UpdatedAssignment.git/) cd videoapp
Install dependencies This project uses Maven to manage dependencies.
Maven Download JDK Download

Database Setup (Optional) configure your application.properties to point to your actual database MySQL

Running the Application Once the dependencies are installed, you can run the application using the following command:

mvn spring-boot:run The application will start running on localhost:8080.

You can now use the application and access its APIs via http://localhost:8080.

Running Unit Tests Unit tests are an essential part of this project and can be run using JUnit 5. The tests for the controllers, services, and other components are written using Mockito to mock dependencies.

Run all tests

mvn test

mvn -Dtest=VideoControllerTest test This will only run the tests from the VideoControllerTest class.

Test Coverage JUnit tests ensure that the functionality of the application is thoroughly tested. You can check the test results in the terminal output or by looking at the target/test-classes folder.

API Endpoints Here are the available API endpoints for this video streaming application:

Publish a Video (POST v1/videos/publish) Request Body: json Copy Edit { "id": "uuid", "title": "Video Title", "synopsis": "Video Synopsis", "director": "Director Name", "cast": "Cast Names", "yearOfRelease": 2023, "genre": "Genre", "runningTime": 120, "status": "ACTIVE", "impressions": 1000, "views": 500, "videoUrl": "http://video.url" } Response: json Copy Edit { "id": "uuid", "title": "Video Title", "synopsis": "Video Synopsis", "director": "Director Name", "cast": "Cast Names", "yearOfRelease": 2023, "genre": "Genre", "runningTime": 120, "status": "ACTIVE", "impressions": 1000, "views": 500, "videoUrl": "http://video.url" }
Delist (Soft delete) a Video (DELETE v1/videos/delist/{id}) Path Parameter: id (Video ID) Response: 200 OK (no content)
Load Video (GET v1/videos/{id}) Path Parameter: id (Video ID) Response: The details of the video in JSON format.
Play Video (GET v1/videos/play/{id}) Path Parameter: id (Video ID) Response: A mock video content.
List All Videos (GET v1/videos) Response: List of all videos.
Search Videos (GET v1/videos/search?query={query}) Query Parameter: query (search query, such as director or genre) Response: List of videos matching the search query.
Get Video Engagement (GET v1/videos/statistics/{id}) Path Parameter: id (Video ID) Response: Video engagement statistics (Impressions and Views).
