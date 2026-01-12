# Dockerizing Spring Boot MySQL Application

## 1. Pull and Run MySLQ Image in Docker Container

### **Step 1: Pull MySQL Docker Image**

```bash
docker pull mysql
```

- Downloads the official MySQL image from Docker Hub.

### **Step 2: Create Docker Network**

```bash
docker network create springboot-mysql-net
```

- Creates a Docker network so the **Spring Boot container can communicate with the MySQL container**.
- Both containers must be in the **same network**.

### **Step 3: Verify Network Creation**

```bash
docker network ls
```

- Lists all Docker networks.

### **Step 4: Run MySQL Container**

```bash
docker run --name mysqldb \
--network springboot-mysql-net \
-e MYSQL_ROOT_PASSWORD=root \
-e MYSQL_DATABASE=employeedb \
-d mysql
```

- **--name mysqldb** → Names the container
- **--network springboot-mysql-net** → Connects container to the same network
- **-e MYSQL_ROOT_PASSWORD=root** → Sets MySQL root password
- **-e MYSQL_DATABASE=employeedb** → Creates database on startup
- **-d** → Runs container in detached mode

Check running containers:

```bash
docker ps
```

View MySQL logs:

```bash
docker logs -f 8cb32c14cae9
```

### **Step 5: Access MySQL Inside the Container**

```bash
docker exec -it 8cb3 bash
```

- Opens an interactive shell inside the MySQL container.

Login to MySQL:

```bash
mysql -u root -p
```

Verify database:

```sql
show databases;
show table;
```

### **Interview One-Liner**

> We use a Docker network so Spring Boot and MySQL containers can communicate using container names instead of IP addresses.
> 

## 2. Create a Dockerfile to Build Docker Image

### Step 1: Create a Dockerfile

Create a file named **`Dockerfile`** in the root directory of your project.

### Step 2: Define the Dockerfile content

```jsx
FROM eclipse-temurin:17

LABEL  maintainer="chrisannlee97@gmail.com"

WORKDIR /app

#copy jar file to docker container
COPY target/springboot-restful-webservices-0.0.1-SNAPSHOT.jar /app/springboot-restful-webservices.jar

ENTRYPOINT ["java", "-jar", "springboot-restful-webservices.jar"]
```

## 3. Implement Profile and Build Docker Image

### **Step 1: Create Docker Profile Properties File**

Create a properties file in the **resources** folder of the Spring Boot project.

**File name:**

`application-docker.properties`

**Content:**

```
spring.application.name=springboot-restful-webservices
spring.datasource.url=jdbc:mysql://mysqldb:3306/employeedb
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
```

- Copy the content from `application.properties`.
- Update `spring.datasource.url` and `password` based on the MySQL container configuration.
- Use the MySQL container name (`mysqldb`) as the hostname.

### **Step 2: Activate Docker Profile**

Add the following property to `application.properties`:

```
spring.profiles.active=docker
```

- This ensures `application-docker.properties` is used when running the application in Docker.

### **Step 3: Build Docker Image**

Run the following command in the project root directory (where the `Dockerfile` is located):

```bash
docker build -t springboot-restful-webservices:0.1.RELEASE .
```

- Builds the Docker image with the specified tag.

## 4. Run Spring Boot App Docker Image in a Container and Testing

```bash
docker run --network springboot-mysql-net \
--name springboot-mysql-container \
-p 8080:8080 \
springboot-restful-webservices
```

- **--network springboot-mysql-net** → Connects the Spring Boot container to the same network as MySQL
- **--name springboot-mysql-container** → Names the Spring Boot container
- **-p 8080:8080** → Maps container port 8080 to host port 8080
- **springboot-restful-webservices** → Docker image to run
