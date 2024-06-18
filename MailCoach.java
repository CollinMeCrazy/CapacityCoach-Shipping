
/**
 * Class MailCoach that holds instance variables and methods used in Class Project 3.
 *
 * @author Collins Ramsey
 * @version 9/30/2022 @ 11:34 am
 */
public class MailCoach
{
    // instance variables
    private String route;
    private int capacity;
    private int letters;
    private int packages;
    private boolean dispatched;

    /**
     * Standard MailCoach Constructor
     */
    public MailCoach() {
    
        // initialise instance variables
        route = "";
        capacity = 0;
        letters = 0;
        packages = 0;
        dispatched = false;
    }
    
    /**
     * Altered MailCoach Constructor to initialize route and capacity with objects from Project 3
     */
    public MailCoach(String route, int capacity) {
        this.route = route;
        this.capacity = capacity;
        letters = 0;
        packages = 0;
        dispatched = false;
    }

    /**
     * Method getRoute takes no param and returns String route
     *
     * @param  none
     * @return String
     */
    public String getRoute() {
        return route;
    }
    
    /**
     * Method getCapacity takes no param and returns int capacity
     * 
     * @param none
     * @return int
     */
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * Method getLetters takes no param and returns int letters
     * 
     * @param none
     * @return int
     */
    public int getLetters() {
        return letters;
    }
    
    /**
     * Method getPackages takes no param and returns int packages
     * 
     * @param none
     * @return int
     */
    public int getPackages() {
        return packages;
    }
    
    /**
     * Method getDispatched takes no param and returns boolean dispatched
     * 
     * @param none
     * @return boolean
     */
    public boolean getDispatched() {
        return dispatched;
    }
    
    /**
     * Method setLetters takes int and returns int zero by default or overflow if the condition is true,
     * intakes number of letters from the user input inside Project 3
     * 
     * @param int
     * @return int
     */
    public int setLetters(int lettersToLoad) {
        letters += lettersToLoad;
        capacity = capacity - lettersToLoad;
        if (letters >= capacity + letters) {
            int overflow = capacity * -1;
            letters -= overflow;
            capacity = 0;
            dispatched = true;
            return overflow;
        }
        else {
            return 0;
    }
    }
    
    /**
     * Method setPackages takes int and returns int zero by default or overflow if condition is true,
     * intakes number of packages from user input inside Project 3
     * 
     * @param int
     * @return int
     */
    public int setPackages(int packagesToLoad) {
        packages += packagesToLoad;
        capacity = capacity - (packagesToLoad * 20);
        if ((packages * 20) >= capacity + (packages * 20)) {
            int overflow = capacity * -1;
            overflow = (int)(Math.ceil(overflow / 20.0));
            packages -= overflow;
            if ((packages * 20) == capacity + (packages * 20)) {
                capacity = 0;
            }
            else {
                capacity = Math.abs(((packages * 20) + capacity) % 20);
            }
            dispatched = true;
            return overflow;
        }
        else {
            return 0;
    }
    }
}
