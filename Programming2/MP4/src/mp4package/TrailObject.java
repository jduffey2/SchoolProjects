/*
 * @author Jason Duffey
 * @author Caleb Smith
 * @version 1.7 - 4/9/14
 *
 * The TrailObject class. Represents the different objects on the trail that
 * can be handled by the player.
 */
package mp4package;
import java.io.*;
import java.util.Scanner;


public class TrailObject {
    private int scoreValue; //value of the object when it is deposited in the Inn
    private String name = ""; //name of the object
    private static boolean hasBeenPickedUp[] = new boolean[14]; //Array for if the 14 objects are picked up
    private int objNumber; //number of the object as stored in the files
    private String description = ""; //description of the object when it is picked up
    
    /**
     * create a new trail object, read in from a file
     * @param objNumber the number of the object as stored in the files
     * @throws IOException if it cannot find the object file 
     */
    public TrailObject (int objNumber) throws IOException {
        this.objNumber = objNumber; 
        String fileName = "ObjectDescriptions\\Object" + objNumber +".txt";
        
        //Open the input stream
        BufferedReader stream;
        File objectFile = new File(fileName);
        FileReader in = new FileReader(objectFile);
        stream = new BufferedReader(in);
        
        String header = stream.readLine(); //read the header
        String name = stream.readLine(); //read the name of the object
        String scoreValue = stream.readLine(); //read the score when deposited
        
        //set the local variables to what was read in
        this.name = name;
        this.scoreValue = Integer.parseInt(scoreValue);
        
        //read in the description of the object
        String c;
        while( (c = stream.readLine()) != null) {
            description += c + "\n";
        }
        
        //objects are not instantiated until picked up, so set this to true
        hasBeenPickedUp[objNumber] = true;
        
    }
    
    /**
     * set whether an object is picked up or not
     * @param objNum the number of the object that has been picked up or dropped
     * @param isPickedUp <true> if picked up, <false> if dropped
     */
    public void setPickup(int objNum, boolean isPickedUp) {
        hasBeenPickedUp[objNum] = isPickedUp;
    }
    
    /**
     * get the value the object is worth for dropping it off at the Inn
     * @return integer value that the object is worth
     */
    public int getValue () {
        return scoreValue;
    }
    
    /**
     * get the name of the object
     * @return String containing the name of the object
     */
    public String getName () {
        return name;
    }
    
   /**
    * get whether a specific object is picked up or on the ground
    * @param objNumber - the number of the object to check
    * @return true if is picked up, false if not
    */
    public static boolean isPickedUp (int objNumber) {
        return hasBeenPickedUp[objNumber];
    }
    
    /**
     * get the description of the object that is displayed when picked up
     * @return String containing the description as formatted in the file
     */
    public String getDescription() {
        return description;
    }
    
    
}
