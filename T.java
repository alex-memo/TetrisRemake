import greenfoot.*;  
public class T extends Tetramino
{
    public T() 
    {
        super(Color.BLACK);     
        dir = Direction.left;
        up = new int[][]{{-1, 0},{0, 0},{1, 0},{0, -1}};
        right = new int[][]{{0, -1},{0, 0},{0, 1},{1, 0 }};
        down = new int[][]{{-1, 0},{0, 0},{1, 0},{0, 1}};
        left = new int[][]{{0, -1},{0, 0},{0, 1},{-1, 0}};
    }    
}
