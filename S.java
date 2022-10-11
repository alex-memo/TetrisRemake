import greenfoot.*;  
public class S extends Tetramino
{
    public S() 
    {
       super(Color.BLACK); 
       up = new int[][]{{-1, 0},{0, 0},{0, -1},{1, -1}};
        right = new int[][]{{0, 0},{0, 1},{-1, 0},{-1, -1}};
        down = new int[][]{{0, 0},{1, 0},{0, 1},{-1, 1}};
        left = new int[][]{{0, -1},{0, 0},{1, 0},{1, 1}};
    }    
}
