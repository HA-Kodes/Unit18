the concept of ONE2ONE relationship
diving into it, let's take the address Table, from our online_bank Table example,
and we'll copy our schema into a new Java class, and name it Address as a class name, and click Finish
then paste inside our java class, just as a comment, making our coding life easier for creating this Java Object

    /**
     * user_id int PK
        address_line_1 varchar(200)
        address_line_2 varchar(200
        city varchar(100)
        region varchar(100)
        country varchar(100)
        zip_code varchar(15)
     */

so ... BOOM - these are things needed, and remember the ONE2ONE relationship,
the Address has a user_id as a Primary Key, as well as a Foreign Key

that said, we look into how to do that ... it's a bit complex but it's basically, one new annotation we need to look at ...

now, let's go back to the Address.java class and start declaring our instances' variables ...

    private Long userId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String region;
    private String country;
    private String zipCode;

now, the way that we create a ONE2ONE relationship, is that we map Objects ...
when we're mapping relationships, with Hibernate, there's Objects to Objects ... that's how we map
and currently, we have no Object that represents the other side of our relationship here
we have the userId as a Long value, which is going to be the Primary Key of the Table ...
but we have no corresponding relationship to set up the Foreign Key part ...
so that's ... one curve ball here is we also need to add the User Object

this step is easy to forget, but it's a step that's needed ...
not only do we need a Primary Key value as userId as a Long,
but we also need to add in the User Object as the reference for the Foreign Key as well

remember, generate the Getters/Setters in the Address.java class

then ... on the User.java class, since we have the ONE2ONE relationship, the User will map to one Address Object (line 23)
and the Address.java class will map to one User Object (line 13)

again, generate the Getter/Setter in the User.java class

okay, we got the structure in place
now, we need to add the Hibernate annotations

again, locate getAddress, and that's where we annotate what's needed
in both the User.java class
and the Address.java class

now ... as far as mappedBy ... the parent side (the User), we need to tell it how it is mapped on the Address side
so how will we map the User on the Address side?
simple, we map it as "user" ... so if we go to our Address.java class and look in our declaration we've just added ... the instance variable is user
this is more/less we need on the parent side (the User.java class)

let's go over the child's side, here's where things get a bit different ...
to start off, we'll need to mark / annotate the userId, as it will be a Primary Key ... @Id, but it also need to be a Foreign Key






