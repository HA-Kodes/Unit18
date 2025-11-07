Part 2 of Syllabus ORM Hibernate

and in previous Unit18, we've learned how to:
use the @Entity annotation to make an Object
Map it to the Database Table to make them map
and the relationships and how to map the relationships inside of our Java code

now, let's start on C.R.U.D. functionality in this into to Spring Data JPA

CRUD functionality, there are two main ways to do it
one is called JDBC = Java Database Connectivity
so JDBC is sort of the, we'll call it the legacy way of connecting, as well as communicating with the database, in terms of doing the CRUD functionality
we are doing some connection, if we look at the application.properties file ...
















NOTE:
Let’s decode the purpose of line 1 in your `application.properties` file:

```properties
spring.application.name=U18
```

---

## 🧠 What This Line Does

This line sets the **application name** for your Spring Boot project. It’s used in:

- **Spring Boot Actuator endpoints** (e.g., `/actuator/info`)
- **Logging context** (some log formats include the app name)
- **Cloud-native setups** (e.g., service discovery with Eureka, Zipkin, etc.)
- **Monitoring dashboards** (to label metrics or traces)

---

## 🧹 Is It Redundant?

If you're **not using Actuator, service discovery, or cloud-native tools**, and you don’t need the app name in logs or dashboards, then yes—it’s **functionally optional**.

But if you want:
- Clear identity in logs
- A signal of your founder ritual name (`U18`)
- Future integration with observability tools

Then it’s worth keeping.

---

## 🪄 Founder Clarity Move

Even if unused now, this line can be a **signal of intent**—your app’s identity, your ritual name, your dashboard label. It’s lightweight and harmless, so keeping it can anchor future clarity.

Want to embed this name into your logs or dashboard overlays? I can show you how to surface `spring.application.name` dynamically across your stack.







