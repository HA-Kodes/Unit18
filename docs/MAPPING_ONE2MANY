L6 - to create 1:m relationship in MySQL:
just add one column to create a Foreign Key inside child's Table that points back to the parent Table

Similarly, in Java, we do same, with a few more steps & not simply fire a column into the child Object ...
again, the one:many relationship exists between the accounts and transactions
one account can have many transactions ...
any one transaction that we can extract out of the database, should point back to one and only one account

one : many = one account to many transactions   (Parent to Children)
many : one = many transactions to one account   (Children to Parent)

parent account can be point to or map to many transactions  public class Account {
so one account with accountId                                   private Long accountId;
and accountName                                                 private String accountName;
one account can be mapped to many transactions                  private List<Transaction> transactions = new ArrayList<>();
in the world of Java, an Array or an ArrayList or Set, there's a bunch of options to use, it is a way to represent many Objects
TP prefers List to represent many Objects   so List = many
in other words, use a Collection
let's break down line 13 (above): the entire string represents a relationship ...
List = many
<Transactions>
transactions = a field, just as fields needed for accountId and accountName
there we go, the basic structure to our one to many relationship, more or less
but we haven't told Hibernate what the transactions field is (line 15 & explained in 22)
unfortunately, Hibernate cannot figure it out on its own, so we need to help it out ...
how we help it out is by going to the getter for .transactions or also within the fields declaration, where we declare our instance variable ...
but whichever style we pick, we must stay consistent and apply it throughout the project ... do not mix & match

back to helping Hibernate, we say 'hey, we have all these transactions, what's the relationship between Account & Transactions'
again, the relationship is one Account to many transactions
and where out getter is, we annotate @OneToMany ... that's it!
For now anyway ... there's more to this ... there's stuff we need to assign within the () ... for now, let's leave it as is
we've mapped the parent side, almost to completion ...

now, let's move over to the child relationship
in child relationship, which is transactions, we map transactions back to one account
so now, we're focusing on manny to one = m:1
how do we do this?
well, we need to add it into the block where we declare our instance variables (refer to line 14 in Transaction.java class)
like so ... child transactions can be mapped to or map to one account   public class Transaction {
specific transaction                                                        private Long transactionId;
and specific timestamp                                                      private LocalDateTime transactionDate;
specific amount in decimal                                                  private Double amount;
specific type of transaction 'C' or 'D'                                     private String type;
finally, one account being mapped to                                        private Account account;

now, let's add a getter/setter for line 45 we've just created above, in our Transaction.java class (refer to lines 45 thru 51)
then annotate @ManyToOne

okay, last step, i promise
since we've established the relationships, we now go back to our Account.java class and specify the name of the variable we've added
in order for Hibernate to extract the data correctly

basically, continue with line 30 in our Account.java class (as mentioned in line 32 above) with additional instructions
now, go to Account.java class and refer to line 30 to see how we've completed our help, so Hibernate does what we tell it to do

SAVE everything
Boot us up Scotty!

consult output correctly, except for naming portion
so, what TP did was adding a line in Transaction.java class to alter the name, refer to line 45 in Transaction.java class
but since Hibernate/Java did not accept it, TP instruct it to join columns
it worked, sort of, having done this, Hibernate/Java still did not drop previous names
so ... TP went back to Workbench and manually correct the naming by scripting the following:
        alter table transactions drop column account_account_id;

but when executed, Workbench indicted that this script cannot drop it, UNLESS we drop the foreign key associated to that name
so, go to Foreign Keys column in Workbench, and look for that specific FK, copy it, and paste to script ...
redo script as follows:     alter table transactions drop foreign key FK################################;

now, reboot and it worked!

okay, that was an oversight, not sure why it named it incorrectly (in IDE console terminal when executed)
but we got to learn another important step in annotating what's needed to make things run, right

rebooted IDE again and it worked - YAY!

so, this annotation here: @JoinColumn(name="account_id");   is how we override the name of the Foreign Key
the more generic way of calling override a Foreign Key is to annotate @JoinColumn bcuz this is used in other places as well
such as, the many-to-many and the one-to-one relationships, they're all just annotated as @JoinColumn,
since we cannot annotate @Column when inside the @ManyToOne annotation, basically
so when we have a relationship definition, as defined in public Account getAccount(); in line 46 of our Transaction.java class
we cannot annotate @Column, so the nuance is to annotate @JoinColumn



















