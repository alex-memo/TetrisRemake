import greenfoot.*; 
public class J extends Tetramino
{
    public J() {
        super(Color.RED); 
        up = new int[][]{{0, -1},{0, 0},{0, 1},{-1, 1}};
        right = new int[][]{{-1, -1},{-1, 0},{0, 0},{1, 0}};
        down = new int[][]{{1, -1},{0, -1},{0, 0},{0, 1}};
        left = new int[][]{{-1, 0},{0, 0},{1, 0},{1, 1}};
 }    
}
