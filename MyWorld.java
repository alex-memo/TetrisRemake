import greenfoot.*;
import java.util.*;

public class MyWorld extends World
{
    Set<Tetramino> bag;
    Iterator<Tetramino> iBag;
    Tetramino currentTetramino;
    int bagCount;
    boolean gameLose= false;
   // Counter bagCounter = new Counter("Tetris Bag: ");
    //Counter scoreCounter = new Counter("Your Score: ");
    int speed=45;
    public MyWorld()
    {    
        super(10, 20, 25); 
        bag = newBag();
       // bagCounter.add(1);
        Greenfoot.setSpeed(speed);
       // addObject(scoreCounter, 3, 0);
       // addObject(bagCounter, 3, 1);
    }
    public void act() {
        if(((currentTetramino == null) || currentTetramino.isInactive) && !gameLose) {
            if(bag.isEmpty()) {
                bag = newBag();
                 //bagCounter.add(1);
                bagCount++;
                if(bagCount >= 3) {
                    Greenfoot.setSpeed(speed + 2);
                    bagCount = 0;
                }
            }
            iBag = bag.iterator();            
            currentTetramino = iBag.next();
            bag.remove(currentTetramino);
            addObject(currentTetramino, getWidth()/2, 1);
            scan();
        }
    }
    private Set<Tetramino> newBag() {
        bag = new HashSet(7);        
        bag.add(new I());
        bag.add(new T());
        bag.add(new O());
        bag.add(new L());
        bag.add(new J());
        bag.add(new S());
        bag.add(new Z());
        return bag;
    }
    public boolean isLineFull(int y) {
        for(int x=0; x<getWidth(); x++) {
            List li = getObjectsAt(x, y, Blocks.class);
            Iterator io = li.iterator();
            if(io.hasNext()) 
            {
                Blocks a = (Blocks) io.next();
            }else {
                return false;
            }
        }
        return true;
    }
    public void deleteLine(int y) {
        for(int x=0; x<getWidth(); x++) {
            removeObjects(getObjectsAt(x, y, Blocks.class));
        }
        List objects = getObjects(Blocks.class);
        Iterator oi = objects.iterator();
        while(oi.hasNext()) {
            Actor moveDown = (Actor) oi.next();
            if(moveDown.getY() < y) {
                moveDown.setLocation(moveDown.getX(), moveDown.getY()+1);
            }
        }
    }
    public void scan() {
        int multiplier = 0;
        for(int y=0; y<getHeight(); y++ ){
            if(isLineFull(y)) {
                deleteLine(y);
                multiplier++;
            }
        }
        //scoreCounter.add(speed*(multiplier*2));
    }
    public void play(int pitch, int length){}
    public void stopped() {}
}
