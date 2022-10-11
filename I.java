import greenfoot.*;
public class I extends Tetramino
{
    public I(){
        super(Color.CYAN);
        up = new int[][]{{0, -1},{0, 0},{0, 1},{0, 2}};
        right = new int[][]{{-1, 0},{0, 0},{1, 0},{2, 0}};
        down = new int[][]{{1, -1},{1, 0},{1, 1},{1, 2}};
        left = new int[][]{{-1, 0},{0, 0},{1, 0},{2, 0}};
    }  
}
