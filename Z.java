import greenfoot.*; 
public class Z extends Tetramino
{
    public Z() 
    {
        super(Color.BLACK); 
        up = new int[][]{{0, 0},{1, 0},{-1, -1},{0, -1}};
        right = new int[][]{{-1, 1},{-1, 0},{0, 0},{0, -1}};
        down = new int[][]{{-1, 0},{0, 0},{0, 1},{1, 1}};
        left = new int[][]{{1, 0},{1, -1},{0, 1},{0, 0}};
    }    
}
