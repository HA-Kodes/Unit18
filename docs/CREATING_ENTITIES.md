First relationship for users is to go to account (refer to online_bank Tables in Workbench)
and a users to account is a many to many relationship, but let's start with a one-to-many relationship and build up to that many o many ... let's understand the basics first
so the one-to-many relationship what we have in this (online_bank Tables) is between account and transactions
and that is observed thru the transactions Table, having a foreign key to the account Table, via the account id ... this is a one-to-many relationship
so, let's create both the account and transactions Tables in our IDE, using various @Entities to accomplish this

now, let's create a new Java Class, like we've always done, and let's name it Account

package com.coderscampus.U18.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity (hover over @Entity to import javax.persistence.Entity; and import will populate automatically, as shown on line 11)
and also bcuz we want to over the naming to accounts, we annotate @Table(name="accounts"), then select import again for .Table
now, we only have two more Annotations to go, as we've covered the two Annotations needed: @Entity and @Table to override the naming we want to view in appropriate column within that Table

moving forward, we can continue with all the fields needed for our POJO, starting at line 21 below

@Entity
@Table(name="accounts")  // override name, as Account is a reserved name in Java 
public class Account {
    private Long accountID;
    private String accountName;

ready for our creating a Getter / Setter (do a right click and select Generate, select what's needed, then click Generate)

    @Id // import javax.persistence
    @GeneratedValue(strategy = GenerationType.IDENTITY) // what this does is it uses the auto increment field for mySQL specifically.  So mySQL has a built in mechanism for automatic incrementing, which is the auto attribute we've assigned ... and we get that by GenerationType.IDENTITY          // remember to import javax.persistence fo this Annotation
    public Long getAccountId() {  // accountId is a Primary Key, so we need to identify as such
                                  // and we also want it to auto.increment as well, so identifying that is also needed  
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Column(length = 100)   // import javax.persistence
    public String getAccountName() {
        return accountName;

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}

also, we talked about changing accountName, override it to use a varchar of 100 as oppose to the defaulted length of 255

so if we boot up our Table right now, it will create accounts
with a primary key of accountId ... non-nullable and auto-increment
and it would create an accountName column, with a defaulted varchar of 255 datatype
if we want to override the length of varchar, which we do ...
we can override by annotating an @Column at any of the getters, for the appropriate Column that we want to update
since we want to change the accountName configuration (in line 41), we'd inject the @Column on the getter for the AccountName
remember to import this Annotation from javax.persistence.Column
and when we open a parenthesis and hit space, it lists a bunch of things we can do ... one of which is length, so select that
and type in the length we wanted, which is 100 in length (refer to line 40 to see how it's annotated)
line 40 is how we tell it to override the default functionality of assigning 255 characters to that column & reduce the length

now, if we boot up our application ... boom, there's a create Table statement for accounts (with an s, as overridden)
(account_id bigint not null auto_increment) - as annotated in line 30
account_name varchar(100) - as override on line 40)
and it sets primary key to (account_id) - refer to line 29


Next, we create the transactions Table
back to the domain package, create a new Java Class, name it Transaction

package com.coderscampus.U18.domain;

@Entity
@Table(name="transactions")  // bcuz Transaction is a reserved name in Java, just as Account
                             // Ctrl Shft O to import javax.persistence
public class Transaction {
    private Long transactionId;
    private LocalDateTime transactionDate; // let's see if we can use LocalDateTime, if it doesn't, we'll just use Date instead
    private Double amount;
    private String type;  // type is also a reserved word, let's see if 

    // again, right click to generate Getter / Setter from here onward
    // ... so this vid is all about practicing creating Entities and how to use the Annotations appropriately
    // to be able to create various Tables

    @Id     // to say, 'hey, this is Primary Key enable)
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // this auto-increment with non-null already pre-set
    public Long getTransactionId() {  // so remember, TransactionId is the Primary Key
        return transactionId;
    }

    public Long setTramsactionId(Long transactionId) {
        this.tranactionId = transactionId;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Column(length = 1)
    public String getType() {  // might need to rename this to transactionType, as it's a reserved name in Java, will see ....
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}


upon boot up, console output the following:
create table transactions - which matched line 76 above)
(transaction_id bigint not null auto_increment) - which comes from line 79 in transactionId, and annotated in line 88 & 89
amount double precision - ooh, did not know it could do that ... what does it do?  Will need to look & see if it's set to something specific ....
transaction_date datetime(6) - okay!  Looks like it works, but not sure what the (6) is
type varchar(255) - ooh, right, forgot to set type varchar to 1 in length, so let's annotate that right now, with @Column in line 113 now ....  okay, great example of whoopsie daisie ... the column created (255) characters & that's not what we wanted
if we reboot the server, it's not smart enough to pick our annotation and update that one column, idt, so ... yeah, console confirmed that it doesn't figure it out ... that's an example of the update setting in our application.properties  ...great example that it's not working properly
so it's not quite smart enough to process, 'oh, we need to alter that specific column'
and this is where we manually update it inside mySQL database Table, and there are two ways to fix this:
#1) script it in appropriate file within mySQL, or you could be lazy and do the 2nd option below ... but first, refresh
#2) after refresh, right click, select Alter ... a window opens up, locate type, replace 255 in varchar to 1, click Apply
a Script pops up, click Apply again
BOOM - we manually had to do that, and now, we've corrected the length of our varchar for type column - YAY!

now, let's go back and look at transaction_date with datetime(6) ... what is that 6 about ... not used to seeing that notation (6) ... what does datetime(6) do ... does it give any indication as to what that means ... hmmm ... 

let's query the following in our Workbench ... if we:

1 insert into transactions (amount, transaction_date, type)
2 values (100.00, `2020-01-01 15:00', 'C'); // values idk one hundred dollars, datetime 2020-01-01 at 15 hrs, and the type is 'C' for now ... let's see if that works when we run it ... okay, console outputs data

now ...
3 select * from transactions;  // console shows a precision in the hour in milliseconds - huh! interesting
                               // usually we never need to get to that level of accuracy/craziness ... this is in nanoseconds

also, we want to double check on the double number, so let's insert another record with slightly different data...
4 insert into transactions (amount, transaction_date, type)
5 values (100.01, `2020-01-01 16:00', 'C');  // console confirmed new data, so that means our double datatype is correct for decimal purposes

There we go!  we now have created our Tables 
the next step is to establish the relationships between these three Tables, essentially, right ... there might be a fourth one as well, which is the address Table (a one-to-one relationship), but we follow step-by-step, which is the one-to-many, then the many-to-many, and finally, the one-to-many for last




