Part 2 of Syllabus ORM Hibernate

and in previous Unit18, we've learned how to:
use the @Entity annotation to make an Object
Map it to the Database Table to make them map
and the relationships and how to map the relationships inside of our Java code

now, let's start on C.R.U.D. functionality in this into to Spring Data JPA

CRUD functionality, there are two main ways to do it
one is called JDBC = Java Database Connectivity
so JDBC is sort of the, we'll call it the legacy way to connect, as well as communicate with the database, in terms of doing the CRUD functionality
we are doing some connection, if we look at the application.properties file ... 
notice the jdbc on line 4, for our url, so there is still some of jdbc that we're using,
cuz that's what we use to connect to our database

on top of this, typically, in the world of the past, we would write all of our own SQL script,
inside of our Java repositories

so we would have, for instance, a new Java Class, and there would be named UserRepository, and then inside the UserRepository.java class, there would be a method, like saveUser or something, where we pass in a user Object, like so ...

public class UserRepository {

    public saveUser (User user) {  // then we'll have it return a user Object
        return null;               // but for now, we'll just have it return null 

and then, inside of our method (between line 24 & 25), we would have some sort of query string that we would outline, query string would be something like, you know, we physically would write out a query script, such as query to update the user Table, set username = ?, and then password = ?, and, you know, name = ? ...
or even if we saveUser, we would insert into the user Table, and then we would outline username, password, and then name, with values ?, ?, ? ... 
and then there'd be some more code followed, where we would map this ~user~ being passed in (inside User ~user~), and the properties within that ~user~ to those 3 question marks (above), like so ...
        String query = "update user" or "insert into user (username, password, name) values (?, ?, ?)";
        ... and then more code, if needed

so we have to physically type out the code to make that work ... not going to demonstrate it in this vid
but it's about interacting with Objects or interact with some database Object,
and we would say, you know,
map the 1st question mark to user.username
map the 2nd question mark to user.password
map the 3rd question mark to user.name or get name
so we would get the values from this user Object, the Java Object that's being passed in & mapped it into the 3 question marks, and then execute the code, like 'GO - Execute' this, and you know, behind the scenes, it would do some stuff to actually make it work

the downside to this is coders have to write a lot of SQL scripts themselves
coders would have to write ALL of the SQL code themselves
and it would just be a little bit cumbersome, especially with a lot of what they call 'ceremonial code' that had to be written around this ...
so we'd have to begin a transaction, and roll back or commit the transaction
and depending on if it works or didn't work
we'd have to open a connection or fetch a connection ...
there's just a lot of code that coders have to write, to make, essentially, one insert statement or one update or one select statement, to works properly
so it's a lot of code that need to be written ... that is jdbc ... coders can still do it that way, if coders want to have their own, you know, hardcore - real full control over exactly every step and everything that's happening in their project, then using jdbc is the way to go
BUT, of course, there is a better way, a more efficient way, and less time consuming way to accomplish the same task, and the word better, obviously is subjective, depending on which coder we speak to ...

TP is in the camp that he doesn't necessarily want to have to write all the boiler plate code, the ceremonial code, to get a connection, then open a transaction, and do all of the stuff mentioned ...
and then write the SQL statements
and then manage the SQL statements
when TP doesn't necessarily have to ... TP just doesn't want to do all that extra typing and the work, if he doesn't have to ...
if someone else comes along with a framework that makes TP's life easier, he's gonna tend to lean towards wanting to use that framework

all said, TP just wanted to say that there is a way to do all this work, whereby, a coder can write out all of the codes themselves
and if coders want to do that, they're more than welcome to, they can look up jdbc and how to code everything themselves, and learn all about that stuff ...
but, in this day and age, there are technologies, specifically, Spring technology has has come in tha makes developers' lives so much easier
and what it does is it provides the opportunity to have the default, the basic CRUD functionality built in, without having to write a bunch of code at all
in other words, the insert into the user, the updating a user, the finding users, and the deleting the users (CRUD) ...
all of that, all the SQL scripting, all of the ceremony around it, all the code that's been talked about for the last 5minutes+, you know, all of that is baked into Spring Data JPA, is what it's called,
and Spring Data, for short, is the framework that allows us to get all the default, frequently used, CRUD functionality, sort of 'out of the box', right
no need to write any code, any SQL manually, unless we need to get into some complex SQL query & stuff, we'll get there ...
but all the basic common types of select statement & update & insert & stuff is done for us already, okay

so, Spring Data JPA is a DONE FOR YOU LIBRARY

so instead of having to write: public User updateUser where we pass in a (User user), you know, then having some code followed that ...

or: public void deleteUser, again, pass in a (User user) to delete a specific user or several users, like this: public void deleteUser (User ~user~) {
    String query = "delete from user where user_id = ?";
    ... and then we have to map the question mark to the ~user~

public List of users ... you know, getAllUsers or FindAllUsers and have it return the thing, like so ... public List<User> findAllUsers () {
    String query = "select * from users"

so having to write all this code out
and then having to script all the SQL query for every single one of those lines of code, as exampled in lines 69 thru 76 ... or whatever, right ... we'd have to write out all of these respective queries ourselves, so ...

instead of having to do ALL of that writing / scripting / coding ... just delete them ... we don't need them ...

what we can do is leverage Spring Data JPA

how do we leverage Spring Data JPA?
we do two things:
instead of having public class UserRepository ... change it to an interface
and instead of having a blank interface, we're going to use the extends JPARepository<T, ID>, where T = ___ and ID = 
keyword ... subtle nuance here, with keyword between extends/implements
the difference is, use extends for interface and implements for class

and JPARepository is an interface, which comes from springframework.data.jpa.repository, hence, the name Spring JPA, for short
and what this does is it provide all kinds of methods, such as:
findAll
findAllById
saveAll
saveAndFlush
deleteAll
... you know, the list goes on ...
there's a bunch of built in methods here that we can start taking advantage of ...
and not just that, but we can also go into this extending PagingAndSortingRepository, which has different types of findAll ...

and this PagingAndSortingRepository extends CrudRepository, which CrudRepository has even more ... it has a save, it has a count, a deleteById or DeleteAll

so all of these are hierarchy of interfaces, which comes from Spring Data JPA, brings us a wealth of features, a wealth of Crud stuff that we can leverage, without having to write any code ourselves ...

the only thing we need to do is, with our Repository, we need to:
declare it as an interface
extend the JPARepository
and then we need to map two things (there's some Java generics that it needs to figure out:
T = Type, so the Type that it want is the Type that the domain Object that the UserRepository is going to be working with
again, the Type is the Type of the data class, that is the domain Object, that the UserRepository is going to be interacting with ...

essentially, what it's asking is, 'what's the Entity we're working with?'
bcuz each repository is going to have queries (select statement, update, insert, delete), that correspond with one Table ... 
basically, it's saying 'this repository (UserRepository) is just for a specific Table - what Table is that?'
and we say, 'we define what Table it is, by what domain Object we're working with' ... and in this case, it's User, then we import it from our com.coderscampus ....  that's all it wants, it wants the Type, which is the User.java class, which comes from our com.coderscampus.domain.User (Entity)

thing #2 that it wants, is also the type of the ID
what is the type of the ID that we're using in the User.java class?
... and the type (of the Primary Key) is a Long, as we assigned in our User.java class, so we specify that in the 2nd generic thing that it wants

refer to our UserRepository.java class for final code

and Ladies & Gents, that is it!
we're done
how does it look?
does it look intimidating?  overwhelming?  crazy?

what's incredible is, now we have a fully functioning CRUD repository,
which means, we can actually use it and leverage it to select from:
insert into
update
and delete
from/to ... this <~User~, Long> user Table ... user Entity ...

that's it ...
pretty powerful stuff

let's pause here and jump into next vid
where TP will prove that this is a fully functioning CRUD repository
and we can start query data from it ... get data ... look at the data ...
and do whatever we want with it!


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







