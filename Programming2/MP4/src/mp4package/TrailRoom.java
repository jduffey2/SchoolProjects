/*
 * @author Jason Duffey
 * @author Caleb Smith
 * @version 1.7 - 4/9/14
 *
 * The trail room object class. Represents the places the player can be. 
 * Contains the room numbers that are adjacent, the image, objects in it, and
 * its description.
 */
package mp4package;

import java.io.*;
import java.util.Scanner;
import javax.swing.Icon;
import javax.swing.ImageIcon;


public class TrailRoom {
    //these will be -1 if there is none
    private int northRoom; //room to the north
    private int southRoom; //room to the south
    private int eastRoom;  // "     "    east
    private int westRoom;  // "     "    west
    
    private int roomNumber; //current room number
    
    private String objectName; //name of the object in the room
    private String shortDesc = ""; //short description of the room
    private String longDesc = "";  //long description of the room
    private String objectNum;      //number of the object in the room, as stored in the files
    
   private String pictureLink;     //string containing the name of the picture file
   
   /**
    * instantiate a new room object
    * @param roomNumber the number of the room to instantiate
    */
   public TrailRoom(int roomNumber)  {
       try {
       newRoom(roomNumber);
       } catch (Exception e) {};
   }
   
   /**
    * get the image of the room from the link provided
    * @return the image of the room
    */
   public Icon getPicture () {
       Icon picture = new ImageIcon("pictures\\" + pictureLink);
       return picture;
   }
   
   /**
    * Create a new room based on the passed integer that is the room number.
    * @param roomNumber - the number of the room that is to be instantiated
    * @throws IOException - in case we can't find the room file
    */
   public void newRoom(int roomNumber) throws IOException {
       //set the room number, and open the stream
       this.roomNumber = roomNumber;
       String roomString = "rooms\\room" + roomNumber + ".txt";
       BufferedReader stream;

       File roomFile = new File(roomString);
       FileReader in = new FileReader(roomFile);
       stream = new BufferedReader(in);
       
       //read in all the information from the file
       String header = stream.readLine(); //header that has room number
       String directions = stream.readLine(); //the numbers of the rooms connected
       assignRooms(directions);
       
       objectNum = stream.readLine(); //number of the object in the room
       objectName = stream.readLine(); //name of the object in the room
       pictureLink = stream.readLine(); //link to the picture of the room
       shortDesc =  stream.readLine(); //short one line description of the room
       
       //read in the rest, which is the long description
       String c ;
       while ( (c = stream.readLine()) != null) {
           longDesc += c + "\n";
       }
       stream.close();
   }
   
   /**
    * The room to the north
    * @return integer number of the room to the north (-1 if none)
    */
   public int getNorth () {
       return northRoom;
   }
   
   /**
    * the room to the south
    * @return integer number of the room to the south (-1 if none)
    */
   public int getSouth () {
       return southRoom;
   }
   
   /**
    * the room to the east
    * @return the integer number of the room to the east (-1 if none)
    */
   public int getEast () {
       return eastRoom;
   }
   
   /**
    * the room to the west
    * @return the integer number of the room to the west (-1 if none)
    */
   public int getWest () {
       return westRoom;
   }
   
   /**
    * get the short, one line description of the room
    * @return string containing the short description
    */
   public String getShort () {
       return shortDesc;
   }
   
   /**
    * get the long, multi-line description of the room
    * @return string containing the long description of the room, as formatted
    * in the room file
    */
   public String getLong () {
       return longDesc;
   }
   
   /**
    * the number of the room that the object represents
    * @return integer containing the room number as stored in the files
    */
   public int roomNumber () {
       return roomNumber;
   }
   
   /**
    * returns the number of the object, if any is in the room (0 if no objects present
    * @return the integer of the object in the room as stored in files
    */
   public int getObjectNum() {
       int objNum = Integer.parseInt(objectNum);
       return objNum;
   }
   
   /**
    * turn the string of room numbers from the file into the appropriate integers
    * and store in the appropriate locations of direction
    * @param roomLine the string containing the numbers of the rooms adjacent
    */
   private void assignRooms (String roomLine) {
       //file is formatted N S E W
       Scanner roomScanner = new Scanner(roomLine);
       northRoom = roomScanner.nextInt(); 
       southRoom = roomScanner.nextInt();
       eastRoom = roomScanner.nextInt();
       westRoom = roomScanner.nextInt();
   }
   
   /**
    * get the name of the objects in the room
    * @return the name of the object in the room
    */
   public String getObjectName() {
       //check to be sure that item has not been picked up already
       if(TrailObject.isPickedUp(Integer.parseInt(objectNum))) {
           return "";
       }
       return objectName;
   }
}
