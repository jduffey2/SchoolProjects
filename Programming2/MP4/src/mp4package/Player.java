  /*
 * @author Jason Duffey
 * @author Caleb Smith
 * @version 1.7 - 4/9/14
 *
 * The player class for Escape to Pokagon. Represents the player walking through
 * the park, contains current room, inventory, and score
 */
package mp4package;

import java.util.ArrayList;


public class Player {
    private int score = 0; //player's current score
    private TrailRoom currentRoom = new TrailRoom(0); //current room the player is in
    private ArrayList<TrailObject> inventory = new <TrailObject> ArrayList(); //inventory of objects player has picked up
    public static final int MAX_SCORE = 80;
    
    /**
     * get the ArrayList of objects the player has picked up
     * @return array list of TrailObjects player has on them
     */
    public ArrayList<TrailObject> getInventory () {
        return inventory;
    }
    
    /**
     * Get the current player score based on how many objects have been returned
     * to the drop-off zone
     * @return current player score
     */
    public int getScore () {
        return score;
    }
    
    /**
     * Add a TrailObject's score value to the players score
     * @param obj - the TrailObject that is to be scored
     */
    public void setScore (TrailObject obj) {
        score += obj.getValue();
    }
    
    /**
     * Get the number of the room that the player is currently standing in
     * @return - the room number the player is in
     */
    public TrailRoom getCurrentRoom () {
        return currentRoom;
    }
    
    /**
     * Change the current room that the player is standing in
     * @param newRoom the number of the room to move the player to
     */
    public void moveRoom (TrailRoom newRoom) {
        currentRoom = newRoom;
    }
    
    /**
     * Add a TrailObject to the player's inventory
     * @param obj the TrailObject to add to the player's inventory
     */
    public void addInventory (TrailObject obj) {
        inventory.add(obj);
    }
    
    /**
     * Remove all objects from the player's inventory
     */
    public void clearInventory () {
        inventory.clear();
    }
}
