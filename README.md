# Employee Management — Module 4

Spring Boot + Spring Data JPA application with a database-backed `employee` table and `department` table.

## Database relation

- `Department` **1** ↔ **N** `Employee`
- `Employee.department` is `@ManyToOne`
- `Department.employees` is `@OneToMany(mappedBy = "department")`
- SQL foreign key: `employees.department_id -> departments.id`

## Run with MySQL

1. Create or allow Spring to create the `employee_management` database.
2. Update `src/main/resources/application.properties` with MySQL credentials.
3. Run:

```bash
mvn spring-boot:run
```

For PostgreSQL, replace the datasource URL and driver locally:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/employee_management
spring.datasource.driver-class-name=org.postgresql.Driver
```

## Employee endpoints

| Method | URL | Description |
|---|---|---|
| GET | `/api/employees` | List employees |
| GET | `/api/employees?name=Nguyen` | Search by name |
| GET | `/api/employees?department=Engineering` | Search by department |
| GET | `/api/employees/{id}` | Get by ID |
| POST | `/api/employees` | Create employee |
| PUT | `/api/employees/{id}` | Update employee |
| DELETE | `/api/employees/{id}` | Delete employee |

Department endpoints are available under `/api/departments`.

Create a department:

```bash
curl -X POST http://localhost:8080/api/departments \
  -H 'Content-Type: application/json' \
  -d '{"name":"Engineering"}'
```

Create an employee:

```bash
curl -X POST http://localhost:8080/api/employees \
  -H 'Content-Type: application/json' \
  -d '{"name":"Nguyen Van A","email":"a@example.com","departmentId":1}'
```
