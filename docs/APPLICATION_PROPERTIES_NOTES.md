spring.application.name=U18

// here is were we set up all the settings to connect Hibernate to our Database

spring.datasource.driverClassName = com.mysql.cj.jdbc.Driver  // here is where Hibernate talks to mySQL
spring.datasource.url = jdbc:mysql://localhost:3306/hibernate_example?createDatabaseIfNotExist=true  // here is where we tell it to connect to our Database ... a generic string of instructions
spring.datasource.username = root  // my user name (use unique username when public)
spring.datasource.password = root  // my local workbench password (use stronger pw when public)

# none, create, create-drop, update  // Lines 10 thru 12 are Hibernate specific settings/properties, to tell it that we want Hibernate to manage the Creation of a Database Tables on our behalf
spring.jpa.hibernate.ddl-auto = update  // what this equates to really is whether or not we want Hibernate to auto manage certain things with respect to our Database
spring.jpa.show-sql = true  // this just let us see the actual Scripts statement that Hibernate runs for us in the output (terminal)

# once run, Hibernate will handle the SQL Script automatically by translating our Java user Class to mySQL Scripts

# create (on line 10) what this tells Hibernate to do is to physically create a Database Tables when the Application gets booted up
# but, what it will also do is it will drop them as well, as soon as it gets booted up
# so, it will try to drop any Tables that we've specified, any Entity that we've created, it will drop the entire Table, meaning, it will delete everything
# and then it will re-create them from scratch (as indicated on line 11)
# they'll re-create them, based on our Entity or our Classes, however they are defined - it will re-create them
# so the create setting is great for when we're in Dev, when we're doing Dev work, and we're constantly changing or potentially changing our schema ...
# where we do a lot of changes there and we wanted to start a fresh clean Database every single time ... that's when this is useful
# or it's useful when creating test cases where we want to start with a fresh Databases & we go do a bunch of automated test cases ... that's where this is helpful
# when we're testing, specifically around Dev Testing Environment or in Dev
# NEVER USE THIS create in Production NEVER
# NEVER USE THIS LIVE WHILE ON THE INTERNET
# cuz we never want to drop our Tables in the real world ... deleting all our data ... that would be catastrophic!!!

# SO... for the two settings: create, create-drop ... PLEASE USE THEM CAREFULLY

# then, the update setting is far more prevalent
# what update does is it scans to see if there's any difference between our code (in the user class ...
# it scans to see if there's any difference between our Entity and what's being declared on the Database side
# and if it detects a difference, it will update our schemas
# in other words, it won't drop any Tables, it won't delete any data, it will just update our Tables to reflect any changes we may have made in our Entities
# most of the time it works, and some of the time it doesn't ... but it can't figure it out & need us to manually intervene ... and that's just the way it is
# not the most perfect technology, but most of the time it gets it right, so ....

# and the none just won't do anything for us, it's just a default value, which is why nothing happens
# on line 7, we did not set this property, so it's defaulted to none, which means, it did not create any of our Tables for us, it just ignores everything
# sometimes that's useful too, if you want to manually create yur own Tables, if you want to create our own Database Scripts
# nd we don't want Hibernate to meddle with our Tables & whatnot, we just use the none setting and we'll be fine, okay!
# some companies do that, some want to manage their own Scripts for creating Tables and so forth
# personally, it's easier to just let Hibernate do it, but other options also there to please everybody

# with regards to Dialect (output in Terminal when run App), if we want to change the Dialect that's being used, we could,
# but by default, it just uses the most recent MySQL Dialect bcus mySQL version is 8, and it figured out that current version being run is 8, as mySQL provider,
# so it automatically sets the Dialect to be mySQL 8 Dialect, which is great.
# now, there are different Dialects bcuz there are different versions of mySQL... there are different versions of Database systems out there, right ...
# and the syntax in mySQL5 is slightly different than mySQL8
# there were small changes made in terms of the actual Scripts that we right, what's valid / not valid
# so if we point to the mySQL5 Dialect, but we have the mySQL8 Database, we could run into problems where using mySQL5 way of writing the Scripts,
# but that's not the correct syntax bcuz we're using mySQL8 Database, which is where we want to set our Dialect or vice versa, right

# CONSOLE / OUTPUT:
# on boot, when we use the create setting (as coded on line 11 above (cleaned up as update, by TP), console output 'drop table if exists
# so it tries to drop user Table cuz users is the Table name we've identified to override in our Java class as Annotated by Entity / Object / POJO we've created there;
# so it tries to drop user if users exists, and if it doesn't exist, nothing happens, but then it creates a Table (again, line 11 indicates update, by TP, but in his vid, it's create)
# follow along the string of output in console, it creates a Table called users, with a column named (user_id bigint (refer to POJO, notice Long datatype was assigned), userID is two words cuz Id is capitalized, and that's how user_id is output in console,
# set to be not null cuz we wrote @Id on line 16 of POJO (refer to User Java Class
# console output auto_increment cuz we've Annotated in our POJO to @GeneratedValue(strategy = GenerationType.IDENTITY)
# so we can see what prints out in the console is based on what we've Annotated, basically
# and then name is varchar(255) in console cuz in POJO we've set it as String, and it defaults to varchar(255), when hover over the word, we can change it, but in next lesson
# password is also varchar(255) cuz, again, it's been set as String in our POJO, hence, it defaults to varchar(255), as output in console
# the same goes for username ... console output varchar(255) cuz it's been set as String in our POJO
# console output primary key (user_id) cuz we Annotated @Id in our POJO / users class; so not only @Id sets it to no null, but it also identified primary key is the (user_id)
# and then it added engine=InnoDB, which TP doesn't want to get into ... this little guy ... we don't have to worry about him  ... he's beyond the scope of this talk right now :)
# so that's basically it ... our table is created for us and all we have to do is assign a few Annotations on the JAVA side, to our existing users POJO class, and it created a Table for us
# to prove that, go to Workbench in mySQL, hit refresh, and have it look ... sure enough, it now has a users Table.  Also, we can Script in select * from users; hit run / execute, and console Terminal output the columns, as set
# we had to set some properties, as coded in our users POJO
# we tell it how to connect to the Database, as instructed in the application.properties file
# we tell it to enable Hibernate, and line 11 above essentially does that (if update is create, as done in TP's vid)
# and we tell it that we want to see the SQL output in the console once booted

# now, about the create, create-drop on line 10 above ...
# the difference is that the drop statement in console output, when we use the word create, it happens on boot ...
# so as soon as we boot it (meaning Run App), it drops the Table, it tried to anyway, and then it create a Table ...
# then create-drop does NOT drop the Table on start ... it drops the Table on stop ... so when we stop running our App (red square button), it will drop all the Tables
# as oppose to create, when we hit stop (red square button), nothing happens ... however, it did not seem to do this, when TP tied to demonstrate ... it made him look like a liar :))))
# therefore, it doesn't seem to have a difference between the two: create and create-drop
# so, 90% of the time, TP uses either create or update, just these two commands only
# having said that, it is highly recommended to use update; hence, this file outlined update on line 11, by TP
# cuz update is the safest setting and it's just the best way to go, in TP's opinion
# TP does not see a great use case to go drop the Table all the time ... whereas, using update, it doesn't drop any Tables whatsoever ... there's no drop statement in console output to evidence that
# again, safest setting is update, and we can manually write our own script to drop our Table(s) through Workbench, if needed; that way, we have control over it

# now that we see how important it is to create Database Tables, like we've Annotated in our POJO
# it's also important to know how to establish relationships between Tables, cuz when we do, there's a whole bunch of columns that go into play, right
# if it's 1:m ... there's a foreign key that goes to the child's Table
# if it's 1:1 ... the child has a foreign key as the primary key
# if it's a m:m ... there's a join Table that gets created
# so ... it's going to need to know, how to create these Tables, and there's a way to do, so let's learn that next ....

