import greenfoot.*;
public class Tetramino extends Actor
{
    public boolean isInactive = false;
    private boolean isGround = false;
    protected int[][] up = new int[4][2];
    protected int[][] right = new int[4][2];
    protected int[][] down = new int[4][2];
    protected int[][] left = new int[4][2];
    private Blocks[] blocks = new Blocks[4];
    boolean canSlide = true;
    protected Direction dir = Direction.right;
    enum Direction {
        up, right, down, left;
        public static Direction next(Direction dir) {
            Direction[] dirs = Direction.values();
            int ord = dir.ordinal();
            return dirs[(ord+1)%dirs.length];
        }       
        public static Direction prev(Direction dir) {
            Direction[] dirs = Direction.values();
            int ord = dir.ordinal();
            return (ord-1<0)?dirs[Direction.values().length-1]:dirs[ord-1];
        }
    }
    public void addedToWorld(World world) {
        world.addObject(blocks[0], getX(), getY());
        world.addObject(blocks[1], getX(), getY());
        world.addObject(blocks[2], getX(), getY());
        world.addObject(blocks[3], getX(), getY());
        updateBlocksPosition();
    }
        private void updateBlocksPosition() {
        int[][] nextArrayPos = getArray(dir);
        for(int i=0;i<nextArrayPos.length;i++) {
            blocks[i].setLocation(getX()+nextArrayPos[i][0], getY()+nextArrayPos[i][1]);
        }
    } 
    public boolean isGround() {
        try {
            isGround = (blocks[0].isGrounded() || blocks[1].isGrounded() || blocks[2].isGrounded() || blocks[3].isGrounded());
        }catch(Exception e) {
            return false;
        }
        return isGround;
    }
    private void moveRight() { 
        if(canRight() && !isGround()) { 
            setLocation(getX()+1, getY()); 
            ((MyWorld)getWorld()).play(88, 100);
           ((MyWorld)getWorld()).play(90, 100);
            updateBlocksPosition();
        }else if(isGround() && canRight() && canSlide) { 
            setLocation(getX()+1, getY()); 
            canSlide = false; 
        }
    }  
    private void moveLeft() {
        if(canLeft() && !isGround()){  
            setLocation(getX()-1, getY());            
            ((MyWorld)getWorld()).play(88, 100);
            ((MyWorld)getWorld()).play(90, 100);
            updateBlocksPosition();
        }else if(isGround() && canLeft() && canSlide) {
            setLocation(getX()-1, getY());
            canSlide = false;
        }
    }
       private boolean canRight() {
        if(isGround() && isInactive) {return false;}
        int[][] dirArray = getArray(dir);
        boolean fb = isPosValid(dirArray[0][0]+1, dirArray[0][1]);
        boolean sb = isPosValid(dirArray[1][0]+1, dirArray[1][1]);
        boolean tb = isPosValid(dirArray[2][0]+1, dirArray[2][1]);
        boolean rb = isPosValid(dirArray[3][0]+1, dirArray[3][1]);
        return (fb&&sb&tb&&rb);
    }
    private boolean canLeft() {
        if(isGround() && isInactive) {return false;}
        int[][] dirArray = getArray(dir);
        boolean fb = isPosValid(dirArray[0][0]-1, dirArray[0][1]);
        boolean sb = isPosValid(dirArray[1][0]-1, dirArray[1][1]);
        boolean tb = isPosValid(dirArray[2][0]-1, dirArray[2][1]);
        boolean rb = isPosValid(dirArray[3][0]-1, dirArray[3][1]);
        return (fb&&sb&tb&&rb);
    }
    private int w, speed = 10;
    public void act() 
    {
        String key = Greenfoot.getKey();
        if("right".equals(key)) {
            moveRight();
        }else if("left".equals(key)) {
            moveLeft();
        }
        else if("up".equals(key)) {
            if(isDirValid(getArray(Direction.next(dir)))) {
                nextDir();
            }
        }
     //if(Greenfoot.isKeyDown("right"))  {
      //   moveRight();
      //  }
     //if (Greenfoot.isKeyDown("left")){
       //  moveLeft();
      //  }
       // if(Greenfoot.isKeyDown("space")){
       //  if(isDirValid(getArray(Direction.next(dir)))) {
       //      nextDir();
       //     }
         
       // }      
        if(w > speed) {
            if(!isGround()) {
            //    canSlide = true;
                setLocation(getX(), getY()+1);
            }
            //else if(isGround() && canSlide == true){
                //canSlide = false; 
            //}
            else {
                inactive();
            }
            w=0;
        }
        try {
            updateBlocksPosition();
        }catch(IllegalStateException e) {

        }
        w++; 
    } 
    private int[][] getArray(Direction direction) {
        switch(direction) {
            case up: return up;
            case right:  return right;
            case down: return down;
            case left:  return left;
            default: return null;
        }
    }
    private boolean isPosValid(int x, int y) {
        if(!(getX()+x >= 10 || getX()+x < 0)) { 
            Blocks b = (Blocks) getOneObjectAtOffset(x,y, Blocks.class);
            if(b == null) {
                return  true; 
            }else if(b.tetramino == this) {
                return true;
            }else{ 
                return false; 
            }
        }
        return false;
    }
    private boolean isDirValid(int[][] nextBlockPos) {
        if(isGround()) {return false;}
        boolean fb = isPosValid(nextBlockPos[0][0], nextBlockPos[0][1]);
        boolean sb = isPosValid(nextBlockPos[1][0], nextBlockPos[1][1]);
        boolean tb = isPosValid(nextBlockPos[2][0], nextBlockPos[2][1]);
        boolean rb = isPosValid(nextBlockPos[3][0], nextBlockPos[3][1]);
        return (fb&&sb&tb&&rb);
    }
    public void nextDir() {
        if(isGround()){ return; } 
        dir = Direction.next(dir);
        updateBlocksPosition();
        //((MyWorld)getWorld()).play(90, 100);
        //((MyWorld)getWorld()).play(92, 100);
        //((MyWorld)getWorld()).play(95, 100);
    }
       public Tetramino(Color color) {
        for(int i=0;i<blocks.length;i++) {
            blocks[i] = new Blocks(this, color);
        }
        setImage(new GreenfootImage(1,1));
    }
    public void inactive() {
        isInactive = true;
        for(Blocks b : blocks) {
            b.Passive();
        }
        getWorld().removeObject(this);
    }
}