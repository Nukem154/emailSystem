# Email System

To start the Spring Server, follow these steps:

1. Clone the repository: `git clone https://github.com/Nukem154/emailSystem.git`

2. Make sure you have Java 17+ installed

3. Set the environment variables `MAIL_USERNAME` and `MAIL_PASSWORD` with the email credentials of your email service provider either in your `application.properties` file or in your system environment variables.

Example:
> spring.mail.username=example@gmail.com<br/>
> spring.mail.password=mysecretpassword

4. Run the Spring Server: `./mvnw spring-boot:run`

5. Open your web browser and go to `http://localhost:8080` to access the application.

That's it! You should now have the Spring Server up and running.
