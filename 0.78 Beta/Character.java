import java.awt.*;

public abstract class Character
{
	private int x;		//top left corner x coordinate of character
	private int y;		//top left corner y coordinate of character
	private int width;	//width of character
	private int height;	//height of character
	private int vx = 0;	//horizontal velocity of character
	private int vy = 0;	//vertical velocity of character
	private boolean punch;	//if character is punching
	private boolean falling;	//is character is falling


	public Character(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}

	//checks collisions for character e for horizontal (checks == 'h') or vertical (checks == 'v')
	public char isColliding(Character e, char checks)
	{
		char rv = 'z';

		int leftSide = getX();
		int rightSide = getX() + getWidth();
		int top = getY();
		int bottom = getY() + getHeight();

		int eLeftSide = e.getX();
		int eRightSide = e.getX() + e.getWidth();
		int eTop = e.getY();
		int eBottom = e.getY() + e.getHeight();

		rv = 'z';
		/* left and right checks
		1: top is between top and bottom
		2: bottom is between top and bottom
		3: etop and ebottom are between top and bottom
		*/

		if(checks == 'h')
		{
			if(rightSide >= eLeftSide && leftSide < eLeftSide){	//right collision
				if((top >= eTop && top <= eBottom) || (bottom > eTop && bottom <= eBottom) || (eTop >= top && eBottom <= bottom)){
					//System.out.println("Right Collision");
					rv = 'r';
				}
			}

			if(leftSide <= eRightSide && rightSide > eRightSide){	//left collision
				if((top >= eTop && top <= eBottom) || (bottom > eTop && bottom <= eBottom) || (eTop >= top && eBottom <= bottom)){
					rv = 'l';
					//System.out.println("Left Collision");
				}
			}
		}

		/* up and down checks
		1: right is between eright and eleft
		2: left is between eright and eleft
		3: eright and eleft are between right and left
		*/

		if(checks == 'v')
		{
			if(bottom >= eTop && top < eTop){	//down collision
				if((rightSide > eLeftSide && rightSide < eRightSide) || (leftSide > eLeftSide && leftSide < eRightSide) || (eRightSide > rightSide && eLeftSide < leftSide)){
					rv = 'd';
					//System.out.println("Down Collision");
				}
			}

			if(top <= eBottom && bottom > eBottom){	//up collision
				if((rightSide > eLeftSide && rightSide < eRightSide) || (leftSide > eLeftSide && leftSide < eRightSide)
					|| (eRightSide > rightSide && eLeftSide < leftSide)){
					rv = 'u';
					//System.out.println("Up Collision");
				}
			}
		}

		return rv;

	}

	//used to check if a collision will occur between character and character e when character shifts
	//xshift to the right and yshift down.

	public char checkCollision(double timestep, Character e, boolean collide)
	{
		char rv = 'z';
		char rvTemp = 'z';

		setX(getX() + (int)(vx* timestep));
		setY(getY() + (int)(vy* timestep));

		e.setX(e.getX() + (int)(e.getVX() * timestep));
		e.setY(e.getY() + (int)(e.getVY() * timestep));

		rvTemp = isColliding(e, 'h');

		if(rvTemp != 'z'){	//if collides horizontally
			setX(getX() - (int)(vx* timestep));

			if(collide == true)
				collide(timestep, e, rvTemp);
		}

		rv = isColliding(e, 'v');

		if(rv != 'z'){//collides vertically
			setY(getY() - (int)(vy* timestep));

			if(collide == true)
				collide(timestep, e, rv);
		}

		if(rv == 'z'){
			setY(getY() - (int)(vy* timestep));
			setX(getX() - (int)(vx* timestep));
			rv = rvTemp;
		}

		e.setX(e.getX() - (int)(e.getVX() * timestep));
		e.setY(e.getY() - (int)(e.getVY() * timestep));

		return rv;
	}

	public void collide (double timestep, Character e, int rv)
	{
		switch(rv)
		{
			case 'r':	setX(e.getX() - getWidth());
						//System.out.println("RightCollide");
			break;

			case 'l':	setX(e.getX() + e.getWidth());
						//System.out.println("L");
			break;

			case 'd':	setY(e.getY() - getHeight());
						//System.out.println("D");
			break;

			case 'u':	setY(e.getY() + e.getHeight());
						//System.out.println("U");
			break;
		}
	}


	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setVX(int vx) {
		this.vx = vx;
	}

	public void setVY(int vy) {
		this.vy = vy;
	}

	public int getVX() {
		return vx;
	}

	public int getVY() {
		return vy;
	}

	public void updateX(double time){
		x += vx * time;
	}

	public void updateY(double time){
		y += vy * time;
	}

	public void setPunch(boolean punch) {
		this.punch = punch;
	}

	public boolean getPunch() {
		return punch;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean getFalling() {
		return falling;
	}

	abstract void draw(Graphics g);
	abstract void update();
}