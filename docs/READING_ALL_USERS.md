The MAGIC of leveraging all the functionality in JPARepository Extension on our UserRepository interface

How it works, conventionally:
    |-->    Have Controllers, which intercept requests, they send off a flow of code
            |-->    to the Service
                    |-->    Service talks to the Repositories

this is the flow, do not jump jump the gun & skip from Controller to the Repository
we want the code inside Controller to only be the Controller related stuff
in essence, we want to separate our concerns
if the Controller talks to the Repository, what happens there,
the implication is that the Controller will start to do more Service oriented behavior
and Controller is not where we should put Service oriented behavior
Service oriented behavior in programming should be in a Service class
okay, again, separate our concerns

having said that & the reason why it's brought up is bcuz ...
we need to create a UserService class next
and let's put it in the .service package, so to give it its own distinction

TIP: Annotate @Service, before we forget
     good habit to follow, remember to Annotate where needed, eventho Spring can detect certain fields / keywords & does it automatically under the hood for us, even if it's redundant, just make it a habit

UserService, what can we do?  well ...
first let's create a method in our UserService, that will find all users

so this will return a List of users
we'll all this findAll () {  // again, there's a reason why we label it findAll
we'll say return userRepo dot findAll();

now, this UserRepository, we haven't autowired it in yet
so, @Autowired in the private UserRepository class - we'll call it userRepo;

and that block looks like so:
@Autowired
    private UserRepository userRepo;

    public List<User> findAll () {
        return userRepo.findAll();
    }

then bring in the proper imports ...

so, notice there's no error in this block of code at all, right
we did use .userRepo.findAll();   but there's no error here ...
it's not saying, 'hey, error - i can't find this method'
eventho, in the UserRepository, there's literally nothing defined inside our UserRepository.java class that we've created ...
eventho there's nothing in that class, it's still allowing us to say, '.uerRepo.findAll();'
so what's up!!!  right!

well, the behavior comes from the extension of JpaRepository, which extends the PagingAndSortingRepository, which extends the CrudRepository,
which extends ... which extends ...

so the behavior is all inherited from someone else's code
specifically, these Springframework.data.jpa code .....

so, if we, with the userRepo.findAll();    do a CTRL spacebar, after the dot ... up comes a window, displaying all 33 methods we have access to ...
a whole bunch of CRUD methods that we can leverage without having to write any of the code ourselves!!!

we didn't write the code for that .findAll() method
and if we right click on it, JpaRepository.class will open to display all the code written for us

another thing to note that eventho the UserRepository is an interface, we can still Autowired it

so, at some point behind the scene, there will be a concrete class created
and it will handle all of that complexity for us

in other words, it will instantiate a concrete class,
and it will assign that concrete class TO the UserRepo,
which we will then be able to use in our code

so all of that detail is extracted away, under the hood,
meaning, someone else is handling someone's code,
that it has been written to handle all this on our behalf
so that we don't have to worry about it
in other words, that means abstraction

so, when we use this now, let's see what happens

let's create a Controller, cuz we don't have a Controller yet

new class, UserController
and we'll say   dot web, is the package that we use

again, Annotate immediately, using @Controller  :)

TP could've made this a restController, but i want to bake in some UI stuff here, cuz TP wants to keep on building what we have touched on in prior vids, with respect to Frontend & whatnot ... ummm, templates, etcetcetc ...

so, UserController

    at GetMapping, let's say slash users
now, this is typical endpoint for ...
uh, when we see slash users, with a GetMapping ... any ...
any name domain Object with an  ~ s ~, would imply that we're finding / getting All the users - that's what it implies

so this is like a get all users method: @GetMapping("/users")
so this is a get all users method

so let's do that, let's continue ...

    public List<User>  ...  sorry, no, should be
    public String<User> cuz we have a regular Controller annotated ...

NOTE:   use List<User> if annotation is @RestController, as this is for 'views'
        use String<User> if annotation is @Controller

so to use a String, we'd have to map that to a template, which is just an html file, and we'll call this getAllUsers () {   doesn't matter what we call it, but we'll call it that

we will need to inject a (ModelMap model) {
    and inside of here, we can say model.put, and we'll put the "users", into the model, which is users);
    like so: model.put("users"), users); // "users" = key, users = value,
            and a model is just a hashmap(key, value)

but there are no users yet, so how do we populate this with the value ...
well, we bring in ... we @Autowired in our Service, like so ...
    @Autowired
    private UserService userService;

remember - Controller talks to Services
           Service talks to Repositories

then where (ModelMap model) {   ends, we need to find what now ...
we need to findAll ... userService.findAll();  // we want to get ALL of the users, which is a List of <User> users, which we'll call the variable that we use within this line here, which is ~ users ~

so ... List of users, put that onto the model,
and then we'll return the views, we'll call it "users";   will be the view
which implies that we need a template ...

REFER TO UserController.java class for code, based on notes taken above

NOW - onto our html file ...

so, in our src --> resources, template folder - this is where we get the views, so we need to create a user.html file
in other words, we need an html file that matches what we're returning, which is ~ user ~

to reiterate, our new html file is named users.html cuz we're returning "users" from our @Controller

our html file will be a plain old html page ...

<!DOCTYPE html>
<html xmlns:th="http://thymeLeaf.org">
    the <head> will have a
    <Title>that just says Users</Title>
    we will have a <body>
    which will also have an <h1>tag for Users for now</h1>
    and then we'll have a <div>that will list out all the users that are on the model.  remember, we put on the model, the word ~ users ~, okay, so that's on the model ... on the model is what we use on the view, that we can talk to, interact with, and do stuff with ... so on the model, we have users as a variable, so we can thymeLeaf - th:each="" ... th is not going to recognize, unless we outline exactly what's needed, like so ---> xmlns:th="http://thymeLeaf.org" <---  (in the html tag at top) so that it's happy.   So we can th:each="" ... we can do a for each loop ... around each of the users (which is: <div th:each="user : ${users}">on the model ... remember, this is how we th:each syntax ... again, i wish the Spring refresher language here - the ${ symbols would wrap the entire thing, but that's just the way the syntax is ... we read the users on the model; again, the reason why we can put the users in the syntax cuz it exists in our model of our UserController.java class, where model.put("users"), users); // refer to lines 111 & 112 above --> "users" = key, users = value, and a model is just a hashmap(key, value) ... and so, ${users} is the key to match up what we read that's on the model, and what we're doing here (in our div th:each="user...) is we assign each to a new variable/a new variable name (value).  And so with that variable name of user that we're declaring here, we can then iterate thru each one & get data from them.
    So we <span tag and do a th:text="$, and we can say {user.username}", for instance></span>  and we could do an output of username that we have on file </div></div>
    </body>
    </head>
</html>

so now that we visit this html page, it's going to iterate through each of the users, and just spit out the username of each of the users that we have on the model.

where are we getting the users from the model?
we're getting it from the userService.findAll, in our UserController.java class

what is the UserService findAll?
oh, it's the userRepo.findAll();   in our UserService.java class

and what is our userRepo.findAll?
oh, it's a method inside of JpaRepository, and this is going to automatically do a select * from usersTable ... it's gonna write that query for us, under the hood, automatically

we should see it in our console as an output when we run the app
so let's run it as a java application now
hopefully it boots up correctly

okay, only warnings & info, no errors
looks like it booted up in 5 seconds
let's go to localhost:8080/users in our browser
okay, remember, in our UserController.java class,
we have @GetMapping("/users")

so, localhost:8080/users, will hit the method in lines 19 thru 23,
which will do a query to the database
get all the users in the database
put them onto the model
and throw (return) that model to the users' view
which would be rendered as users.html page

so when we hit enter, in our localhost:8080/users, hopefully ...
oh, Whitelabel Error Page ...
so ...
there was an exception ...
there's unexpected error ...
let's see what that's all about ...
let's go back to our console and let's see where we dropped the ball ...

NOTE: if there's a 404 Error, double check pom.xml
      make sure thymeLeaf dependency, so that things get mapped properly
      when we get to localhost:8080/users --> enter





















