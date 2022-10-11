import greenfoot.*;
public class Blocks extends Actor
{
    private Color color;
    public Tetramino tetramino;
    private boolean passive = false;
    public Blocks(Tetramino tetramino, Color color) {
        tetramino = tetramino;
        color = color;
    }
    public boolean isGrounded() {
        Tetramino underT = null;
        if(getY()+1>=getWorld().getHeight()){
            return true;
        }
        try {
            Blocks bunder = (Blocks)getOneObjectAtOffset(0,1,Blocks.class);
            if(bunder == null) {
                return false;
            }else {
                if(bunder.tetramino.equals(this.tetramino)) {
                    return false;
                }
                if(bunder.isGrounded() || bunder.tetramino.isGround() || bunder.passive) {
                    return true;
                }
            }
        }catch(NullPointerException e) {
            return true;
        }catch(StackOverflowError e) {
            return false;
        }
        return false;
    }
    public Blocks() {
        color = Color.RED;
    }
    public void addedToWorld(World world) {
        GreenfootImage image = new GreenfootImage(world.getCellSize(), world.getCellSize());
        image.setColor(color);
        //image.fill();
        image.setColor(new Color(0,0,0, 30));
        image.drawRect(0,0,image.getWidth()-1,image.getHeight()-1);
        setImage(image);
          }
    public void act() 
    {
        if(passive && getY() < 2) {
            MyWorld world = (MyWorld) getWorld();
            //world.gameLose();
        }
    }  
    public void Passive() {
        passive = true;
        tetramino = null;
    }
}
