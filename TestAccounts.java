package homework;

import java.util.Scanner;
public class TestAccounts
{
    public static void main(String[] args)
    {
        Account testAcct;
        Scanner scan = new Scanner(System.in);
        System.out.println("How many accounts would you like to create?"); int num =
            scan.nextInt();
        for (int i=1; i<=num; i++)
        {
            testAcct = new Account(100, "Name" + i);
            System.out.println("\nCreated account " + testAcct);
            // Your code here: call method to print out how many accounts have been created so far
        }
        System.out.println("Total number of accounts: " + Account.getNumAccounts());

        Account account = new Account(5000,"Akshita");
        System.out.println("Total number of accounts: " + Account.getNumAccounts());
        account.close();
        System.out.println("Total number of accounts after deleting account: " + Account.getNumAccounts());
        System.out.println("\nCreated account " + account);

        //to test consolidate method
        // consolidating 2 accounts with same name

        Account account1 = new Account(10000,"Akshi");
        Account account2 = new Account(20000,"Akshi");
        System.out.println("Total number of accounts: " + Account.getNumAccounts());
        Account newAccount1 = Account.consolidate(account1,account2);
        System.out.println("\nNew account " +newAccount1);
        System.out.println("\nNew account " +account1);
        System.out.println("\nNew account " +account2);
        System.out.println("Total number of accounts: " + Account.getNumAccounts());

        //trying to consolidate accounts having same name and same account number

        Account account3 = new Account(15000,"Siya");
        System.out.println("Total number of accounts: " + Account.getNumAccounts());
        Account newAccount2 = Account.consolidate(account1,account1);
        System.out.println(newAccount2);



    }
}
