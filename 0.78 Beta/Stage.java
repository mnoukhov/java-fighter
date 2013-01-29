import java.awt.*;

public class Stage extends Character
{
	private int x;			//x is the x coordinate of the top left corner
	private int y;			//y is the y coordinate of the top left corner
	private int width;	//width of stage part
	private int height; 	//height of stage part

									
																	
	public Stage (int x, int y, int width, int height)
	{
		super(x,y,width,height);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public void update()
	{}

	public void draw(Graphics g)
	{
		//g.drawRect(x,y,width,height);
	}
}