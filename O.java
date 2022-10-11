import greenfoot.*;  
public class O extends Tetramino
{
    public O() 
    {
        super(Color.BLACK); 
        up = new int[][]{{0, 0},{1, 0},{0, 1},{1, 1}};
        right = up;
        down = up;
        left = up;
    }    
}
