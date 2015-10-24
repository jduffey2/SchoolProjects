/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pennyboatpackage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 * A boat that can be drawn, contains physical aspects, and has to ability to 
 * change size
 * @author Logan Pfeifer
 * Created 13 April 2014
 */
public class Boat extends JComponent{
    private Point2D.Double  origin;               //the center of the top of the boat
    private int             length;               //the length the boat will be
    private final int       triangleLength = 35;  //the horizontal length of the triangle
    public static final int HEIGHT = 50;          //the height the boat will be
    public static final int VECTOR_DISTANCE = 35; //the distance between vectors
    private ArrayList<ForceVector> forces = new ArrayList<>(); //holds all of the force Vectors
    public static final int VECTOR_LENGTH = 40;   //the length of the force vectors
    
    
    /**
     * Constructs a boat
     * @param rootPoint origin of the boat
     * @param length 
     */
    public Boat( Point2D.Double rootPoint, int length ) {
        this.origin = rootPoint;    //the center of the top of the boat
        this.length = length;       //the length of the top of the boat
        setVectors();
    }
    
    /**
     * allows the origin of the boat to change
     * @param d the new point that serves as the boats origin
     */
    public void setRootPoint( Point2D.Double d) {
        this.origin = d;
    }
    
    /**
     * Draws the boat
     * @param g 
     */
    public void drawBoat( Graphics g ) {
        Graphics2D g2 = (Graphics2D) g;
        
        //get the starting location and length of the boat
        double boxXStart = ((origin.x)-(length/2)+triangleLength);
        double boxYStart =  (origin.y);
        double boxLength = length - (2*triangleLength);
        
        //Get the coordinates to make the triangles
        int [] triangle1xCoords = new int [3];
            triangle1xCoords[0] =  (int)((origin.x) - (length/2) + 0.5);
            triangle1xCoords[1] =  (int)((origin.x) - (boxLength/2) + 0.5);
            triangle1xCoords[2] = triangle1xCoords[1];
        int [] triangle1yCoords = new int [3];
            triangle1yCoords[0] =  (int)(origin.y+ 0.5);
            triangle1yCoords[1] =  (int)(origin.y+ 0.5); 
            triangle1yCoords[2] =  (int)(origin.y + HEIGHT + 0.5);
        int [] triangle2xCoords = new int [3];
            triangle2xCoords[0] =  (int)((origin.x + 0.5) + (length/2));
            triangle2xCoords[1] =  (int)((origin.x + 0.5) + (boxLength/2));
            triangle2xCoords[2] = triangle2xCoords[1];
            
        //set the boat color to red because it's cool
        g2.setColor(Color.RED);
        
        //fill out the boat
        
        g2.fillRect((int)boxXStart, (int)boxYStart, (int)boxLength, HEIGHT);
        g2.fillPolygon(triangle1xCoords, triangle1yCoords, 3);
        g2.fillPolygon(triangle2xCoords, triangle1yCoords, 3);
        
        //remove this later
        //System.out.println("add");
        g2.setColor(Color.BLACK);
        
        // g2.drawLine((int)origin.x, (int)origin.y+100, (int)origin.x, (int)origin.y-100);
       for (int i = 0; i < forces.size(); i++) {
            forces.get(i).drawForceVector(g);
        }
    }
    
    /**
     * sets the length of the boat
     * @param length the new length in pixels
     */
    public void setLength(int length){
        
        this.length = length;
        setVectors();
        repaint();
    }
    
    /**
     * automatically sets the vectors added to the boat
     */
    private void setVectors() {
        forces.clear();
        int vectorPlane = (int) (origin.y + HEIGHT);
        
        
        
        int bottomLeft  = (int) (origin.x + 0.5) - (length/2) + triangleLength;
        int bottomRight = (int) (origin.x + 0.5) + (length/2) - triangleLength;
        int current     = (int) (origin.x + 0.5);
        
        while (current > bottomLeft) {
        forces.add(new ForceVector(new Point2D.Double (current, origin.y+ HEIGHT + VECTOR_LENGTH), new Point2D.Double (current, origin.y+HEIGHT)));
            current -= VECTOR_DISTANCE;
        }
        current+= VECTOR_DISTANCE;
        while (current < bottomRight) {
        forces.add(new ForceVector(new Point2D.Double (current, origin.y+ HEIGHT + VECTOR_LENGTH), new Point2D.Double (current, origin.y+HEIGHT)));
            current += VECTOR_DISTANCE;
        }
//        while (bottomRight > origin.x) {
//            
//        }
//        
//        while (bottomLeft < origin.x) {
//            forces.add(new ForceVector(new Point2D.Double( bottomLeft, origin.y + HEIGHT + VECTOR_LENGTH), new Point2D.Double (bottomLeft, origin.y+HEIGHT) ));
//            bottomLeft += VECTOR_DISTANCE;
//        }
        
        
        
        
        
    }
}
