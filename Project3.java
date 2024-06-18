import java.util.Scanner;
public class Project3 {
    //DATE AND TIME YOU FIRST START WORKING ON THIS ASSIGNMENT (date AND time): <--------------------
    //ANSWER: 9/30/2022 @ 11:34 am  <--------------------
    
    //main method which creates MailCoach objects and calls upon each method in Project 3
    //DO NOT ALTER THE MAIN METHOD
    public static void main( String[] args ) {
        //use this method to test your entity class, comment it out when you've passed all tests
        //testEntityClass();
        
        MailCoach stolat = new MailCoach( "Stolat", 1200 );
        MailCoach borogravia = new MailCoach( "Borogravia", 750 );
        MailCoach quirm = new MailCoach( "Quirm", 366 );
        
        System.out.println( "Welcome to the Ankh-Morpork Post Office new routes mail coach dispatch!" );
        //print the initial menu
        printMenu( stolat, borogravia, quirm );
        //call go and load up as much mail as is possible
        go( stolat, borogravia, quirm );
        //print report when all mail coaches have been dispatched
        printReport( stolat, borogravia, quirm );
    } //DO NOT ALTER THE MAIN METHOD
    
    //test case method for testing MailCoach Class
    //DO NOT ALTER THE testEntityClass METHOD
    public static void testEntityClass() {
        MailCoach test1 = new MailCoach();
        
        assert test1.getRoute().equals( "" ) && 
                test1.getCapacity() + test1.getLetters() + test1.getPackages() == 0  && 
                !test1.getDispatched() : "standard constructor fail";
        
        MailCoach test2 = new MailCoach( "City", 134 );
        assert test2.getRoute().equals( "City" ) : "second constructor instance variable route not properly set";
        assert test2.getLetters() + test2.getPackages() == 0 : "second constructor instance variables letters and/or packages not properly set";
        assert test2.getCapacity() == 134 : "second constructor instance variables capacity not properly set";
        assert !test2.getDispatched() : "second constructor instance variables dispatched not properly set";
        
        assert test2.setLetters( 20 ) == 0 : "setLetters not correct return value";
        assert test2.getLetters() == 20 : "setLetters not correct updating letters loaded";
        assert test2.getCapacity() == 114 : "setLetters not correct updating capacity";
        assert test2.setLetters( 150 ) == 36 : "setLetters not correct return value";
        assert test2.getLetters() == 134 : "setLetters not correct updating letters loaded";
        assert test2.getCapacity() == 0 : "setLetters not correct updating capacity";
        assert test2.getDispatched() : "setLetters not correct updating dispatch status";
        
        MailCoach test3 = new MailCoach( "City", 134 );
        assert test3.setPackages( 2 ) == 0 : "setPackages not correct return value";
        assert test3.getPackages() == 2 : "setPackages not correct updating letters loaded";
        assert test3.getCapacity() == 94 : "setPackages not correct updating capacity";
        assert test3.setPackages( 10 ) == 6 : "setPackages not correct return value";
        assert test3.getPackages() == 6 : "setPackages not correct updating letters loaded";
        assert test3.getCapacity() == 14 : "setPackages not correct updating capacity";
        assert test3.setLetters( 15 ) == 1 : "setLetters not correct return value";
        assert test3.getCapacity() == 0 : "setPackages not correct updating capacity";
        assert test3.getDispatched() : "setPackages not correct updating dispatch status";
        
        System.out.print( "MailCoach class all tests passed.\n" );        
    } //DO NOT ALTER THE testEntityClass METHOD
    
    /**
     * Method go takes 3 MailCoach objects and returns nothing,
     * heavily involves logic to compute destinations, types of mail, and quantity of mail,
     * continues inside while loop until condition is met
     * 
     * @param object
     * @return nothing
     */
    public static void go( MailCoach stolat, MailCoach borogravia, MailCoach quirm ) {
        while (stolat.getCapacity() > 0 || borogravia.getCapacity() > 0 || quirm.getCapacity() > 0) {
            System.out.print("What is the destination? ");
            
            Scanner kbrd = new Scanner(System.in);
            String dest = kbrd.nextLine().toLowerCase().trim();
            
            switch (dest) {
                case "stolat":
                case "1":
                    if (stolat.getCapacity() == 0) {
                        System.out.println("The mail coach to Stolat has been dispatched. Try again tomorrow.");
                        System.out.println("");
                        printMenu(stolat, borogravia, quirm);
                        continue;
                    }
                    else {
                        System.out.print("What are you shipping to Stolat? ");
                        break;
                    }
                case "borogravia":
                case "2":
                    if (borogravia.getCapacity() == 0) {
                        System.out.println("The mail coach to Borogravia has been dispatched. Try again tomorrow.");
                        System.out.println("");
                        printMenu(stolat, borogravia, quirm);
                        continue;
                    }
                    else {
                        System.out.print("What are you shipping to Borogravia? ");
                        break;
                    }
                case "quirm":
                case "3":
                    if (quirm.getCapacity() == 0) {
                        System.out.println("The mail coach to Quirm has been dispatched. Try again tomorrow.");
                        System.out.println("");
                        printMenu(stolat, borogravia, quirm);
                        continue;
                    }
                    else {
                        System.out.print("What are you shipping to Quirm? ");
                        break;
                    }
                default:
                    System.out.printf("We don't deliver mail to %s. Back of the line!%n", dest);
                    System.out.println("");
                    printMenu(stolat, borogravia, quirm);
                    continue;
            }

            String letterOrPackage = kbrd.nextLine().trim();
            int quantityOfMail = kbrd.nextInt();
            
            if (quantityOfMail <= 0) {
                System.out.println("This is not a valid amount. Back of the line!");
                System.out.println("");
                printMenu(stolat, borogravia, quirm);
                continue;
            }
            else {
                switch (letterOrPackage) {
                case "letter":
                case "letters":
                case "package":
                case "packages":
                    break;
                default:
                    System.out.printf("We don't ship %s. Back of the line!%n", letterOrPackage);
                    System.out.println("");
                    printMenu(stolat, borogravia, quirm);
                    continue;
                }
            }
            
            if (dest.equals("stolat") || dest.equals("1")) {
                loadUp(stolat, letterOrPackage, quantityOfMail);
            }
            else if (dest.equals("borogravia") || dest.equals("2")) {
                loadUp(borogravia, letterOrPackage, quantityOfMail);
            }
            else if (dest.equals("quirm") || dest.equals("3")) {
                loadUp(quirm, letterOrPackage, quantityOfMail);
            }
            
            if (stolat.getCapacity() > 0 || borogravia.getCapacity() > 0 || quirm.getCapacity() > 0) {
                printMenu(stolat, borogravia, quirm);
            }
            else {
                break;
            }
        }
    }
    
    /**
     * Method printMenu takes 3 MailCoach objects and returns nothing,
     * prints the amount of capacity remaining for each location and determines if any are dispatched
     * 
     * @param object
     * @return nothing
     */
    public static void printMenu( MailCoach stolat, MailCoach borogravia, MailCoach quirm ) {
        System.out.println("Remaining mail coach capacity: ");
        if (stolat.getCapacity() == 0 && stolat.getDispatched() == true) {
            System.out.println("   Stolat - dispatched");
        }
        else {
            System.out.printf("   1. Stolat: remaining capacity %d %n", stolat.getCapacity());
        }
        if (borogravia.getCapacity() == 0 && borogravia.getDispatched() == true) {
            System.out.println("   Borogravia - dispatched");
        }
        else {
            System.out.printf("   2. Borogravia: remaining capacity %d %n", borogravia.getCapacity());
        }
        if (quirm.getCapacity() == 0 && quirm.getDispatched() == true) {
            System.out.println("   Quirm - dispatched");
        }
        else {
            System.out.printf("   3. Quirm: remaining capacity %d %n", quirm.getCapacity());
        }
    }
    
    /**
     * Method loadUp takes MailCoach object, String, and int and returns nothing,
     * inputs come from go Method when loadUp is called, 
     * determines which type of mail to load and calls upon the setters in MailCoach accordingly,
     * prints out statements based on loading amount and type
     * 
     * @param object, String, int
     * @return nothing
     */
    public static void loadUp( MailCoach d, String type, int quantity ) {
        //IMPLEMENT THIS METHOD loads letters or packages if applicable
        if (type.equals("letter") || type.equals("letters")) {
            int overflow = d.setLetters(quantity);
            if (overflow != 0) {
                System.out.printf("%d of your %d letters couldn't be dispatched today, bring them back tomorrow.%n", 
                overflow, quantity);
                System.out.println("");
            }
            else {
                if (type.equals("letter")) {
                    type = type.concat("s");
                }
            System.out.printf("Your %d %s have been loaded for delivery.%n", quantity, type);
            System.out.println("");
            }
        }
        if (type.equals("package") || type.equals("packages")) {
            int overflow = d.setPackages(quantity);
            if (d.getDispatched() == true) {
                System.out.printf("%d of your %d packages couldn't be dispatched today, bring them back tomorrow.%n", overflow, quantity);
                System.out.println("");
            }
            else {
                if (type.equals("package")) {
                    type = type.concat("s");
                }
            System.out.printf("Your %d %s have been loaded for delivery.%n", quantity, type);
            System.out.println("");
            }
        }
    }
    
    /**
     * Method printReport 3 MailCoach objects and returns nothing,
     * used for printing the final report for each location and the quantity of mail sent out by each one,
     * only is called on when all coaches have been dispatched
     * 
     * @param object
     * @return nothing
     */
    public static void printReport( MailCoach stolat, MailCoach borogravia, MailCoach quirm ) {
        //IMPLEMENT THIS METHOD prints everything tallied for each location, uses getters, ends with message
        System.out.println("All mail coaches have been dispatched for the day.");
        System.out.printf("Dispatched: mail coach to %-11s-%4d letters,%4d packages%n",stolat.getRoute(), stolat.getLetters(), stolat.getPackages());
        System.out.printf("Dispatched: mail coach to %-11s-%4d letters,%4d packages%n",borogravia.getRoute(), borogravia.getLetters(), borogravia.getPackages());
        System.out.printf("Dispatched: mail coach to %-11s-%4d letters,%4d packages%n",quirm.getRoute(), quirm.getLetters(), quirm.getPackages());
        System.out.println("");
        System.out.print("Thank you for using the Ankh-Morpork Post Office. Goodbye!");
    }
}
