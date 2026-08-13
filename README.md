 Fitness App Backend

A **RESTful backend application for a Fitness & Activity Management Platform**, built with **Java and Spring Boot**.

The application provides secure APIs for user authentication, fitness activity management, and personalized recommendations. It uses **PostgreSQL** for persistent data storage and **JWT-based authentication** to secure APIs.

> 🚧 **Current Status:** Backend/API development is the focus of this repository. A frontend client can be connected to these APIs in the future.

---

## ✨ Features

### 🔐 Authentication & Security

* User registration
* User login
* JWT-based authentication
* Spring Security integration
* Protected REST APIs
* Password encryption
* Role-based security structure

### 👤 User Management

* Create and manage users
* User authentication
* User-specific data handling
* Secure access to user resources

### 🏃 Activity Management

Users can manage their fitness activities through REST APIs.

* Create fitness activities
* Retrieve activities
* Associate activities with users
* Store activity-related information
* Track activity data

### 🤖 Recommendations

The backend includes a recommendation system designed to provide fitness-related suggestions.

Recommendations can contain:

* Improvements
* Suggestions
* Safety recommendations

The recommendation system is designed to work with user and activity information.

### 🗄️ Database

The application uses **PostgreSQL** as its primary relational database.

Database interaction is handled through:

* Spring Data JPA
* Hibernate
* Entity relationships
* Repository pattern

### 🐳 Docker

The backend can be containerized using Docker, making it easier to run the application consistently across different environments.

---

# 🛠️ Tech Stack

| Technology         | Purpose                        |
| ------------------ | ------------------------------ |
| ☕ Java             | Backend programming language   |
| 🌱 Spring Boot     | Backend framework              |
| 🔐 Spring Security | Authentication & authorization |
| 🎫 JWT             | Token-based authentication     |
| 🗄️ PostgreSQL     | Relational database            |
| 🔗 JPA             | Persistence API                |
| ⚙️ Hibernate       | ORM                            |
| 📡 REST API        | Client-server communication    |
| 🧪 Postman         | API testing                    |
| 🐳 Docker          | Containerization               |
| 🔧 Maven           | Dependency management & build  |
| 🌐 Neon            | Cloud PostgreSQL option        |

---

# 🏗️ Architecture

The application follows a layered backend architecture:

```text
                    Client
                      │
                      ▼
              ┌───────────────┐
              │   REST API    │
              │  Controllers  │
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │    Service    │
              │     Layer     │
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │  Repository   │
              │     Layer     │
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │ JPA / Hibernate│
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │  PostgreSQL   │
              └───────────────┘
```

Authentication is handled through Spring Security and JWT.

```text
Client
  │
  │ Login
  ▼
Authentication API
  │
  ▼
JWT Token
  │
  │ Authorization: Bearer <token>
  ▼
JWT Authentication Filter
  │
  ▼
Spring Security
  │
  ▼
Protected API
```

---

# 📂 Project Structure

A simplified structure of the backend:

```text
fitness/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── .../
│   │   │       ├── controller/
│   │   │       ├── service/
│   │   │       ├── repository/
│   │   │       ├── entity/
│   │   │       ├── dto/
│   │   │       ├── security/
│   │   │       ├── exception/
│   │   │       └── config/
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       └── ...
│   │
│   └── test/
│
├── Dockerfile
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

> The exact package structure may differ depending on the current version of the project.

---

# 🔐 Authentication

The application uses **JWT (JSON Web Token)** authentication.

### Authentication Flow

```text
1. User registers
        ↓
2. User logs in
        ↓
3. Server validates credentials
        ↓
4. Server generates JWT
        ↓
5. Client stores JWT
        ↓
6. Client sends JWT with API requests
        ↓
7. JWT Filter validates token
        ↓
8. Request reaches protected endpoint
```

For protected endpoints, send the token using:

```http
Authorization: Bearer <your-jwt-token>
```

---

# 🔑 Environment Variables

Sensitive configuration should **not be hardcoded** in the source code.

Example environment configuration:

```text
DB_URL=your-postgresql-url
DB_USERNAME=your-database-username
DB_PASSWORD=your-database-password

JWT_SECRET=your-jwt-secret
```

For local development, configure these values through your environment or application configuration.

### ⚠️ Important

Never commit:

```text
.env
database passwords
JWT secrets
API keys
private credentials
```

to GitHub.

---

# 🗄️ Database

The application uses PostgreSQL.

The database layer is implemented using:

```text
Spring Data JPA
       ↓
Hibernate
       ↓
PostgreSQL
```

Typical entity relationships include user-specific fitness/activity data.

For example:

```text
User
 │
 ├── Activities
 │
 └── Recommendations
```

---

# 🔌 REST API

The backend exposes REST APIs that can be consumed by applications such as:

* Web applications
* Mobile applications
* Desktop applications
* Postman
* Other backend services

## Authentication APIs

Example:

```http
POST /api/auth/register
```

```http
POST /api/auth/login
```

A successful login returns authentication information that can be used to access protected endpoints.

---

## 🏃 Activity APIs

Example:

```http
POST /api/activities
```

Create a fitness activity for a user.

Example request:

```json
{
  "userId": "USER_ID",
  "activityType": "RUNNING",
  "duration": 30,
  "caloriesBurned": 250
}
```

Example:

```http
GET /api/activities/{id}
```

Retrieve a specific activity.

> **Note:** Update the endpoint names and request fields above if your current controller mappings differ. The README should always match the actual API routes in the repository.

---

# 🤖 Recommendation API

The backend supports fitness recommendations containing information such as:

```json
{
  "userId": "USER_ID",
  "activityId": "ACTIVITY_ID",
  "improvements": [
    "Improve running consistency",
    "Increase workout duration gradually"
  ],
  "suggestions": [
    "Maintain proper hydration",
    "Include recovery days"
  ],
  "safety": [
    "Warm up before intense exercise",
    "Avoid sudden increases in workout intensity"
  ]
}
```

---

# 🧪 Testing APIs with Postman

The APIs can be tested using **Postman**.

### Basic workflow

```text
1. Start the Spring Boot application
2. Open Postman
3. Register a user
4. Login
5. Copy the JWT token
6. Add the token to Authorization
7. Select Bearer Token
8. Test protected APIs
```

Example authorization:

```text
Authorization
Type: Bearer Token

Token:
<your-jwt-token>
```

---

# 🚀 Running the Project Locally

## 1️⃣ Clone the repository

```bash
git clone https://github.com/mishraaryan6387/fitness-springboot.git
```

Move into the project:

```bash
cd fitness-springboot
```

---

## 2️⃣ Configure PostgreSQL

Create a PostgreSQL database and configure your database credentials.

Example:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

---

## 3️⃣ Configure JWT

Set your JWT secret through an environment variable:

```text
JWT_SECRET=your-secret-key
```

---

## 4️⃣ Build the project

Using Maven Wrapper:

### Windows

```powershell
.\mvnw.cmd clean install
```

### Linux / macOS

```bash
./mvnw clean install
```

---

## 5️⃣ Run the application

### Windows

```powershell
.\mvnw.cmd spring-boot:run
```

### Linux / macOS

```bash
./mvnw spring-boot:run
```

The backend will normally start at:

```text
http://localhost:8080
```

---

# 🐳 Running with Docker

Build the Docker image:

```bash
docker build -t fitness-backend .
```

Run the container:

```bash
docker run -p 8080:8080 fitness-backend
```

The API will then be accessible through:

```text
http://localhost:8080
```

If environment variables are required:

```bash
docker run \
  -p 8080:8080 \
  -e DB_URL="your-database-url" \
  -e DB_USERNAME="your-username" \
  -e DB_PASSWORD="your-password" \
  -e JWT_SECRET="your-jwt-secret" \
  fitness-backend
```

---

# ☁️ Database Deployment

The backend can work with a cloud PostgreSQL database such as **Neon**.

The basic architecture becomes:

```text
                    Internet
                       │
                       ▼
              ┌─────────────────┐
              │ Fitness Backend │
              │  Spring Boot    │
              └────────┬────────┘
                       │
                       │ JDBC
                       ▼
              ┌─────────────────┐
              │ Cloud PostgreSQL│
              │      Neon       │
              └─────────────────┘
```

This allows the backend to use a remote PostgreSQL database instead of a locally installed database.

---

# 🔭 Future Improvements

Planned improvements may include:

* [ ] Deploy the backend to a cloud platform
* [ ] Connect a React frontend
* [ ] Improve recommendation/AI functionality
* [ ] Add API documentation with Swagger/OpenAPI
* [ ] Add automated tests
* [ ] Add CI/CD with GitHub Actions
* [ ] Improve exception handling
* [ ] Add request validation
* [ ] Add API rate limiting
* [ ] Add monitoring and logging
* [ ] Improve Docker deployment
* [ ] Add more fitness analytics

---

# 📚 What I Learned

Building this project helped me gain practical experience with:

* Building REST APIs using Spring Boot
* Designing backend architecture
* Spring Security
* JWT authentication
* JPA and Hibernate
* PostgreSQL database design
* Entity relationships
* API testing with Postman
* Environment variable management
* Docker containerization
* Git and GitHub
* Debugging backend applications
* Connecting Spring Boot with cloud databases

---

# 👨‍💻 Author

### Aryan Mishra

**B.Tech Computer Science Engineering**
Ajay Kumar Garg Engineering College

Interested in:

```text
Full-Stack Development
Backend Engineering
Spring Boot
React
UI/UX
AI Applications
System Design
```

---

## ⭐ Support

If you find this project useful or interesting, consider giving the repository a ⭐ on GitHub.

---

<div align="center">

### 🏋️ Built with Java & Spring Boot

**Fitness App Backend**

</div>
