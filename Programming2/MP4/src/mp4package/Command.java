/*
 * @author Jason Duffey
 * @author Caleb Smith
 * @version 1.7 - 4/9/14
 *
 * game engine to parse all the user inputs, processes user commands, updates
 * all variables based on player action, and returns appropriate responses
 */
package mp4package;

import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class Command {
    private String[] words = new String[5];//no command should have more than 5 words
    private int num_Words = 0; //companion variable for number of words in the command
    private Player gamePlayer = new Player(); //player playing the game
    
    public Command() {
        //set the player's initial room to room 0, the beginning of the park
        gamePlayer.moveRoom(new TrailRoom(0));
    }
    
    /**
     * Needed to pass the player to the Frame so that the current info can be
     * retrieved there to display
     * @return the Player object that is playing the game
     */
    public Player getPlayer () {
        return gamePlayer;
    }
    
    /**
     * parses the player's command and calls the appropriate method to respond
     * @param cmd the string containing the player's command
     * @return a String containing an appropriate response to the input
     * @throws IOException in case a new room or object file cannot be found
     */
    public String parseCommand (String cmd) throws IOException {
        String response = ""; //response to the input

        //convert command to lowercase and open a scanner to get the words
        cmd = cmd.toLowerCase();
        
        Scanner in = new Scanner(cmd);
        
        num_Words = 0;
        //read in all the words
        while(in.hasNext()) {
            words[num_Words] = in.next();
            num_Words++;
        }
        
        //call the appropriate method based on the first word of the command
        switch (words[0]) {
            case "north":
            case "south":
            case "east":
            case "west": words[1] = words[0];
            case "go":
            case "move":     response = move();      break;
            case "look":     response = look();      break;
            case "inventory":response = inventory(); break;
            case "take":     response = take();      break;
            case "drop":     response = drop();      break;
            case "open":     response = open();      break;
            case "score":    response = score();     break;
            case "help":     response = help();      break;
            case "quit":     response = quit();      break;
            default:         response = invalid();
        }
        return response;
    }
    
    /**
     * open command for use on the chest with the key
     * @return appropriate String depending on if criteria are met
     * @throws IOException  incase the room file cannot be found
     */
    private String open () throws IOException {
        String response = "";
        TrailRoom current = gamePlayer.getCurrentRoom();
        //if they are in the room with the chest and have the key
        if (current.roomNumber() == 4 && isObjectInInventory("Key")) {
            response = "You open the chest with the key and find a vial of "
                    + "liquid inside. You take the vial of liquid.";
            gamePlayer.addInventory(new TrailObject(1));
        }
        //if they do not have the key and are in the room
        else if (current.roomNumber() == 4 && !isObjectInInventory("Key")) {
            response = "The chest is locked. You need a key to open it.";
        }
        //if they are in a room without the chest
        else {
            response = "There is nothing here that needs opening.";
        }
        
        return response;
    }
    
    /**
     * get the string containing the players score that is displayed
     * @return string phrase containing score message
     */
    private String score () {
        String response;
        int score = gamePlayer.getScore();
        response = "You have " + Integer.toString(score) + " / " + 
                Integer.toString(Player.MAX_SCORE) + " points";
        return response;
    }
    
    /**
     * move the player from room to room depending on direction given
     * @return - message if something prevents them from moving in given direction
     * @throws IOException - if the room file when moving cannot be found
     */
    private String move () throws IOException {
        //get the player's current room
        TrailRoom current = gamePlayer.getCurrentRoom();
        TrailRoom newRoom;
        String response = "";
        switch(words[1]) {
         //checks if the appropriate direction can be moved into, moves player if so   
            case "north": 
                if (current.getNorth() == -1) {
                    response = "You better not wander off the path!";
                    return response;
                }
                newRoom = new TrailRoom(current.getNorth()); break;
            case "south":
                if (current.getSouth() == -1) {
                    response = "You better not wander off the path!";
                    return response;
                }
                newRoom = new TrailRoom(current.getSouth()); break;
            case "east":
                if (current.getEast() == -1) {
                    response = "You better not wander off the path!";
                    return response;
                }
                newRoom = new TrailRoom(current.getEast()); break;
            case "west":
                //handles trying to move past the zombie at the youth tents
                // 10 - is the room with the zombie in it, cannot move past it,
                // unless they have the sharp staff
                if(current.roomNumber() == 10 && isObjectInInventory("Hiking staff")){
                    newRoom = new TrailRoom(current.getWest());
                    response = "You use the sharp hiking staff to send the zombie"
                            + "fleeing. \n But it won't be gone for long";
                    break;
                }
                else if (current.roomNumber() == 10 && !isObjectInInventory("Hiking staff")) {
                    response = "A zombie is blocking the path and you cant get through."
                            + " If only you had something sharp to kill it with!";
                    newRoom = new TrailRoom(10); //Keep the player in the same room
                    break;
                }
                
                //normal checks for direction
                if (current.getWest() == -1) {
                    response = "You better not wander off the path!";
                    return response;
                }
                newRoom = new TrailRoom(current.getWest()); break;
                
            default: invalid(); return ""; 
        }
        //move the player into the new room
        gamePlayer.moveRoom(newRoom);
        return response;
    }
    
    /**
     * look around the room and get the long description
     * @return string containing the long description as formatted in the file
     */
    private String look () {
        TrailRoom current = gamePlayer.getCurrentRoom();
        String longDesc = current.getLong();
        return longDesc;
    }
    
    /**
     * get the list of objects currently in the player's inventory
     * @return string containing the list of object names
     */
    private String inventory () {
        String inventoryList = "";
        //traverse their inventory looking for TrailObjects
        for (TrailObject inv: gamePlayer.getInventory()) {
            if(inv != null) {
                inventoryList += inv.getName() + ", ";
            }      
        }
        //if there are no objects in the inventory return string "None"
        if (inventoryList.equals("")) {
        return "None";
        }
        return inventoryList;
    }
    
    /**
     * take an object that is on the ground and puts it in inventory
     * @return string incase something goes wrong
     */
    private String take () {
        //get the object in the room
        TrailRoom current = gamePlayer.getCurrentRoom();
        int roomObj = current.getObjectNum();
        if(!TrailObject.isPickedUp(roomObj)) {
            try {
                //check to see if they take the water without the canteen
                //must have canteen to take spring water
                if(roomObj == 3 && !isObjectInInventory("Canteen")) {
                        return "You don't have anything to store it in!";
                }
                if(roomObj == 1 && !isObjectInInventory("Key")) {
                    return "The vial is locked in the chest, you need a key to open it";
                }
                TrailObject newObj = new TrailObject(roomObj);
                
                //add item to the inventory
                gamePlayer.addInventory(newObj);
                return newObj.getDescription();
            } catch(Exception e){
                return e.getMessage() ;
            }
        } else {
            //if no objects are in the room
            String response = "There are no objects in here!";
            return response;
        }
    }
    
    /**
     * drop objects, can only be dropped in the Inn
     * @return 
     */
    private String drop () {
        //see if they are at the Inn, don't let them drop if not in Inn
        TrailRoom current = gamePlayer.getCurrentRoom();
        if (current.roomNumber() != 3) {
            String response = "You might need that for later, better hang on to it!";
            return response;
        }
        else {
            //add score for dropping the object in the inn
            for(TrailObject inv: gamePlayer.getInventory()) {
                gamePlayer.setScore(inv);
                
            }
            //get rid of items in inventory
            gamePlayer.clearInventory();
        }
        return "";
    }
    
    /**
     * show commands that can be used
     * @return empty string since a dialog box is used
     */
    private String help() {
        String response = "";
       //string of different commands that can be used
        String commands = "move [direction] \n"
                +         "go [direction]   \n"
                +         "[direction] \n"
                +         "take \n"
                +         "drop \n"
                +         "open \n"
                +         "score \n"
                +         "inventory \n"
                +         "quit";
                
        //show the message dialog of commands
        JOptionPane.showMessageDialog(null, commands);
        
        return response;
    }
    
    /**
     * quit the game
     * @return empty string since no action will be taken if they choose to continue
     *  and the frame will close if they exit
     */
    private String quit() {
        int score = gamePlayer.getScore();
        String message = "Good Job! You final score is: " + score + " / " + Player.MAX_SCORE + "\n"
                + "Are you sure you want to quit?";
        int choice = JOptionPane.showConfirmDialog(null, "Quit Game", message, JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION) {
            return "";
        }
        else {
            System.exit(0);
        }
        return "";
    }
    
    /**
     * if they type in a command that is not recognized
     * @return string containing message that the action is not allowed
     */
    private String invalid() {
        String invalid = "That's not going to be very helpful now. \n"
                + "To see valid commands type 'help'.";
        return invalid;
    }
    
    /**
     * checks to see if an object is in the inventory
     * @param obj - object to check if it is in inventory
     * @return true if it is in the inventory, false if not
     */
    private boolean isObjectInInventory(String obj) {
        for(TrailObject inv: gamePlayer.getInventory()) {
            if((inv.getName()).equals(obj)) return true;
        }
        return false;
    }
}
