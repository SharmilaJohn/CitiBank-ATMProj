       import java.util.Scanner;

public class ATMCashOut {

          public static void main(String args[]) {

             

                BankATM.calculateTotalCurrency();

            

               Scanner sc = new Scanner(System.in);

                System.out.print("Enter money to be withdrawn for 1st customer:");

                int  withdraw1 = sc.nextInt();

                if(withdraw1%10!=0){

                    System.out.println("Enter amount in multiples of 10");

                }

                else{

                     BankATM t1=new BankATM(withdraw1);

                      t1.start();

                }

                System.out.print("Enter money to be withdrawn for 2nd customer:");

                 withdraw1= sc.nextInt();

                if(withdraw1%10!=0){

                    System.out.println("Enter amount in multiples of 10");

                }

                else{

                     BankATM t2=new BankATM(withdraw1);

               //     BankATM.calculateTotalCurrency();

                        t2.start();

                }

           }

}

class BankATM extends Thread

{

           /* Constant Currency Denominations. */

           protected static final int[] currencyDenom = { 1000, 500, 100, 50 , 10 };

 

           /* Currencies for each denomination*/

           protected static int[] currencyNo = {1,4,2,2,10};

 

           protected  int[] count = { 0, 0, 0, 0 ,0};

           protected static int totalCurrency = 0;

           protected  int amount=0;

 

         

           public BankATM(int amount){

               this.amount=amount;

           }

 

            public static void calculateTotalCurrency(){      

               for(int i = 0; i < currencyDenom.length; i++){

                   totalCurrency=totalCurrency+currencyDenom[i]*currencyNo[i];

                   System.out.println("Total Currency: " +totalCurrency);

               }

           }

 

           /* Cash Withdraw   */

           public  synchronized  void  withdrawCash(){

               if(amount<=totalCurrency){

                   for (int i = 0; i < currencyDenom.length; i++) {

                       if (currencyDenom[i] <= amount) {

                           int countNote = amount / currencyDenom[i];

                           if(currencyNo[i]>0){

                               count[i] = countNote>=currencyNo[i]?currencyNo[i]:countNote;

                               currencyNo[i] =  countNote>=currencyNo[i]?0:currencyNo[i]- countNote;

                               totalCurrency=totalCurrency-(count[i]*currencyDenom[i]);

                               amount = amount -(count[i]*currencyDenom[i]);

                           }               

                       }

                   }

                   getNotes();

                   getBalanceNotes();

 

               }

               else{

                   System.out.println("Cannot dispense cash");

               }

 

           }

 

 

           public void run()

           {

               withdrawCash();

 

           }

 

                 private void getNotes(){

               for (int i = 0; i < count.length; i++) {

                   if (count[i] != 0) {

                       System.out.println(currencyDenom[i] + " * " + count[i] + " = "+ (currencyDenom[i] * count[i]));

                   }

               }

           }

 

      

           private void getBalanceNotes(){

               for(int i = 0; i < currencyDenom.length; i++){

                   System.out.println("Notes of "+currencyDenom[i]+" left are "+currencyNo[i]);

               }

           }

       }