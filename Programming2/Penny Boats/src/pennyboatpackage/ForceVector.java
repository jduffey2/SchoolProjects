/*
 * @author Jason Duffey
 * @version 1.3. 4/18/14
 *
 * Creates a ForceVector arrow to display the forces that are at play when a boat
 * is floating on water
 */

package pennyboatpackage;

import java.awt.geom.Point2D;
import javax.swing.JComponent;
import java.awt.*;

/**
 *
 * @author Jason Duffey
 * @version 1.3 4/18/14
 * 
 * Creates a ForceVector arrow to display the forces that are at play when a boat
 * is floating on water
 */
public class ForceVector extends JComponent {
    /**
     * Used for offset for drawing the head of the arrow, pixel in each direction
     * the head will extend
     */
    public static final int ARROW_HEAD = 15;
    
    private Point2D.Double rootPoint; //point for the base of the vector arrow
    private Point2D.Double vectorTip; //point for the the tip of the vector arrow
    
    /**
     * Creates a new ForceVector between the two points given
     * @param root point to place the base of the vector arrow
     * @param point point to place the tip of the vector arrow
     */
    public ForceVector (Point2D.Double root, Point2D.Double point) {
        rootPoint = root;
        vectorTip = point;
    }
     
    /**
     * Get the coordinate for the root point of the vector
     * @return Point2D.Double for the root of the vector
     */
    public Point2D.Double getRootPoint () {
        return rootPoint;
    }
    
    /**
     * Get the coordinate for the tip of the vector
     * @return Point2D.Double for the tip of the vector
     */
    public Point2D.Double getVectorTip () {
        return vectorTip;
    }
    
    /**
     * Get the length of the vector from the tip to the root
     * vectors will only be vertical so y point is the only one needed
     * @return integer for the length of the vector from root to tip
     */
    public int getLength () {
        return Math.abs((int)rootPoint.y - (int)vectorTip.y);
    }
    /**
     * Paint the Force Vector
     * @param g Graphics object for drawing
     */
    
    public void drawForceVector(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        //draw the body of the vector
        g2.drawLine((int)rootPoint.x, (int)rootPoint.y, (int)vectorTip.x, (int)vectorTip.y);
        
        //drawing the arrow head, checking to see if it is pointing up or down;
        //vector points down
        if (vectorTip.y > rootPoint.y) {
            g2.drawLine((int)vectorTip.x, (int)vectorTip.y, (int)vectorTip.x - ARROW_HEAD, (int)vectorTip.y - ARROW_HEAD);
            g2.drawLine((int)vectorTip.x, (int)vectorTip.y, (int)vectorTip.x + ARROW_HEAD, (int)vectorTip.y - ARROW_HEAD);
        }
        //vector points up
        else {
            g2.drawLine((int)vectorTip.x, (int)vectorTip.y, (int)vectorTip.x - ARROW_HEAD, (int)vectorTip.y + ARROW_HEAD);
            g2.drawLine((int)vectorTip.x, (int)vectorTip.y, (int)vectorTip.x + ARROW_HEAD, (int)vectorTip.y + ARROW_HEAD);
        }
    }
}
