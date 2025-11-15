# Seyi Blog Spring Boot Application

A modern, container-ready Spring Boot blog platform with PostgreSQL support, file upload, and RESTful APIs.

## Features
- Spring Boot 3.x, Java 17
- PostgreSQL database integration
- RESTful and MVC controllers
- File and video upload support
- Environment variable configuration via `.env`
- Docker-ready (see `Dockerfile`)
- Gradle build system

## Getting Started

### Prerequisites
- Java 17+
- Gradle
- PostgreSQL
- (Optional) Docker

### Environment Variables
Create a `.env` file in the project root (see `.env.example`):
```
POSTGRES_DB=your_db
POSTGRES_USER=your_db_user
POSTGRES_PASSWORD=your_db_password
```

### Running Locally
1. Install dependencies and build:
   ```sh
   gradle clean build
   ```
2. Run the application:
   ```sh
   gradle bootRun
   ```
3. The app will be available at [http://localhost:8080](http://localhost:8080)

### Running with Docker
1. Build the Docker image:
   ```sh
   docker build -t seyi-blog-service .
   ```
2. Run the container:
   ```sh
   docker run --env-file .env -p 8080:8080 seyi-blog-service
   ```

### Testing
Run all tests:
```sh
gradle test
```

## Project Structure
- `src/main/java` - Application source code
- `src/main/resources` - Static files, templates, and configuration
- `src/test/java` - Test code
- `build.gradle` - Gradle build file
- `Dockerfile` - Containerization instructions

## Contributing
1. Fork the repo
2. Create your feature branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/YourFeature`)
5. Open a pull request

## License
MIT
