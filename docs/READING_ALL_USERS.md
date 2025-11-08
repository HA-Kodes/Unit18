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





















