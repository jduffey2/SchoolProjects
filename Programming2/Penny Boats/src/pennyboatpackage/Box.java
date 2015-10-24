/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pennyboatpackage;


import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Simulates a box and handles logic for adding force vectors to itself.
 * @author Jeff Campbell
 * Version 1.0 - 13 April 2014
 */
public class Box extends JComponent{
    //anchor point and length
    private Point2D.Double rootPoint;
    private int length;
    private ArrayList<ForceVector> forces = new ArrayList<ForceVector>();
    
    //globally accessible constants
    public static final int BOX_HEIGHT = 50;
    public static final int BOX_VECTOR_LENGTH = 100;
    public static final int VECTOR_GAP = 35;
    
    /**
     * Creates a new box.
     * @param rootPoint The new box's anchor point for drawing (midpoint of bottom line).
     * @param length The length of the box.
     */
    public Box(Point2D.Double rootPoint, int length){
        this.rootPoint = rootPoint;
        this.length = length;
        generateVectors();
    }
    
    /**
     * Sets the length of the box.
     * @param newLength The new length.
     */
    public void setLength(int newLength){
        length = newLength;
        generateVectors();
        repaint();
    }
    
    /**
     * Calculates the points to put force vectors on.
     */
    private void generateVectors(){
        forces.clear();
        
        int halfLength = length / 2;
        double leftEnd = rootPoint.x - halfLength;
        double rightEnd = rootPoint.x + halfLength;
        
        int current = (int)rootPoint.x;
        while(current <= rightEnd){
            forces.add(new ForceVector(new Point2D.Double(current, rootPoint.y - 2 * BOX_HEIGHT), new Point2D.Double(current, rootPoint.y - BOX_HEIGHT)));
            current += VECTOR_GAP;
        }
        
        current = (int)rootPoint.x;
        while(current >= leftEnd){
            forces.add(new ForceVector(new Point2D.Double(current, rootPoint.y - 2 * BOX_HEIGHT), new Point2D.Double(current, rootPoint.y - BOX_HEIGHT)));
            current -= VECTOR_GAP;
        }
        
        /*
        Point2D.Double current = new Point2D.Double(rootPoint.x, rootPoint.y);
        current.y -= BOX_HEIGHT;
        //draw vectors on right half
        while(current.x < rightEnd){
            forces.add(new ForceVector(new Point2D.Double(current.x, current.y - 50), current));
            current.x += VECTOR_GAP;
        }
        
        current = new Point2D.Double(rootPoint.x, rootPoint.y);
        current.y -= BOX_HEIGHT;
        //draw vectors on left half
        while(current.x > leftEnd){
            forces.add(new ForceVector(new Point2D.Double(current.x, current.y - 50), current));
            current.x -= VECTOR_GAP;
        }*/
    }
    
    /**
     * Paints the box.
     * @param g Graphics thing.
     */
    public void drawBox(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        int halfLength = length / 2;
        Point2D.Double upperLeft = new Point2D.Double(rootPoint.x - halfLength, rootPoint.y - BOX_HEIGHT);
        Point2D.Double bottomRight = new Point2D.Double(rootPoint.x + halfLength, rootPoint.y);
        int width = (int) (bottomRight.x - upperLeft.x);
        int height = (int) (bottomRight.y - upperLeft.y);
        //draw rectangle from bottom left point to top right.
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect((int)upperLeft.x, (int)upperLeft.y, width, height);
        
        for(ForceVector x : forces)
            x.drawForceVector(g);
    }
    
    public ArrayList<ForceVector> getForces(){
        return forces;
    }
}
