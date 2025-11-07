the concept of ManyToMany Mapping is about a relationship between a user and an account
as we may have a joint account, such as a partner, a spouse, a child, a sibling, etc.
and if we think of this concept in terms of bank, it means that we can have a joint account with someone, right
hence, this creates a MANY2MANY relationship
for example: one user can map to many bank accounts (1:n)
             and one bank account can only map back to one user (1:1)
             and then we can also have one bank account mapping back to one or more users (n:n)
             then there's the parent:child relationship, in this case, the parent is the owning owning side of this user class
             conversely, the child side of this mapping is the account(s)

so, how do we have user in our bank account expressed in our system?
well, we set up the User.java class and the Account.java class
then how do we create a MANY2MANY relationship with this structure in place?
to reiterate, any ONE user, could map to MANY bank accounts
now let's map this MANY accounts relationship inside the world of Java, shall we

how do we have MANY of anything?  MANY = Collection, in the world of Java
how do we map a Collection of Objects in Java?
we create a List of Account objects, called accounts, like so ... private List<account> accounts = new ArrayList<>();
then we import javax.persistence.List

that is the start of our project
of course, it needs respective Getters/Setters

again, the Getter is where the magic will happen ...
then complete the annotations process, as follows:

@ManyToMany
@JoinTable(name = "user_account"),   // here, we get to name the Table however we like, but combine existing names of two Tables' is simplest
            joinColumns = @JoinColumn(name = "user_id"),    // then we map out the join Columns' properties
            inverseJoinColumns = @JoinColumn(name = "account_id"))    // here, we complete the loop of annotations, unless there's more, then keep on stringing

basically, what's been done is nesting annotations within annotations, it'll be very simple once repeating the syntax several times
no shame in looking it up, if forgotten, so look it up, just to have the right syntax

okay, so let's summarize line 28 thru 31:
in the ManyToMay annotation
we map the Table out on the owning side (parent), specify the name of the joining Table
the join column, which is the "user_id" on the owning side
and the inverse columns is the child side

so, in this case, we have the one column, which is the "user_id"
and the inverse side is the "account_id"

we annotated all that in the Getter for the getAccounts() Object or field, whatever the convention is called
so this is the parent side ... annotated in User.java class

now, we move to the child side ... Account.java class
we follow the same process, starting with the declaration of instance variable(s) block
and bcuz it's MANY2MANY, the child's side also maps back to MANY potential users ...
again, MANY = Collection and this maps back to a whole List of users as well to an array list of users, like so ...
        private List<User> users = new ArrayList<>();

then generate Getters/Setters for that instance we've just added

now, how do we map this to the child side?
well, we also have the @Many2MANY() relationship
and in the (), we specify the mappedBy = the accounts, as declared in the instance variable inside the User.java class, like so ...
        @ManyToMany(mappedBy = "accounts"); // timestamp 05:25 of vid 07

to reiterate, the child side of the relationship (Account.java class) needs to know, 'hey, what is the variable assigned to map me?'
and if forgotten, we to to User.java class to double check what's been declared, which is "accounts"

same concept in the ONE2MANY relationship specification

now, let's fire it up!  fingers crossed ...

CONSOLE OUTPUT:

create table user_account   // good
(user_id bigint not null,  account_id bigint ot null)  // just as annotated in our User.java class
now we need to confirm that our Foreign Keys are correct:
    Hibernate: alter table user_account add constraint FK########################## foreign key (account_id) references accounts (account_id)
    Hibernate: alter table user_account add constraint FK########################## foreign key (user_id) references users (user_id)

PERFECT!

line 73 would have been catastrophically wrong, if (account_id) references users (user_id)
so it's crucially important to always check, double check, triple check, quadruple check our work
bcuz, for instance, if we had flipped the name = in lines 30 and 31 above ... Hibernate would have referenced the wrong Table
the danger is that Maven would have compiled, however, the compilation would have pointed to the wrong thing, which would be a huge BUG to fix

again, always double check what Hibernate does under the cover,
and remember there's a downside in allowing Hibernate to manage translating/converting the data, as annotated
in other words, leveraging line 9 in application.properties: spring.jpa.hibernate.ddl-auto = update, instead of none
means that we need to super check our work - making sure Hibernate doesn't do anything 'weird'

so what we've covered is the way of mapping MANY2MANY
and one thing that we'll cover in next vid is the fetching ... if we look at the @ManyToMany() on the parent side ...
there's a few other things that matter ... such as, the fetch type, and the cascade type ...
fetching is important, in terms of performance, speed of the application
cascading is important when we do things like: deleting existing data, and how we should function when we delete certain things ...
these types will become super important to understand ...
cuz once we start using these ... we create data, inserting it, fetching, reading it & modifying ... all that good stuff ...
there's some more intricacies that we need to dive into ...


REPLACE Jarkata with this library
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;




