//Character
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import java.io.*;

public class Type1b extends Character
{
	BufferedImage	sprite			= null; 					//Sprite in use
	BufferedImage 	sprite_r 		= null; 					//Stand facing Right
	BufferedImage	sprite_l 		= null; 					//Stand facing Left
	BufferedImage[]	spr_run_l		= new BufferedImage[10];	//Running facing left
	BufferedImage[]	spr_run_r		= new BufferedImage[10];	//Running facing right
	BufferedImage	spr_dash_r 		= null;						//Dash facing right (NOT USED)
	BufferedImage	spr_dash_l 		= null;						//Dash facing left  (NOT USED)
	BufferedImage	spr_jump_r		= null;						//Jump facing right
	BufferedImage	spr_jump_l		= null;						//Jump facing
	BufferedImage	spr_fall_r		= null;						//Fall facing right
	BufferedImage	spr_fall_l		= null;						//Fall facing left
	BufferedImage	spr_punch_r		= null;						//Punching Right
	BufferedImage	spr_punch_l		= null;						//Punching Left

	private int 	hp 				= 100;						//Health Points
	private boolean	doubleJump 		= false;					//Whether the person has used his second jump, such that he cannot continuously jump.
	private int 	dir				= 1; 						//-1 = Right, 1 = Left
	int 			runAt			= 0;						//designates which running sprite is used for the animation.

	Type1b(int x, int y)
	{
		super(x,y, 10,10);

		try{
		sprite_r = ImageIO.read(new File("M_R.png"));
		sprite_l = ImageIO.read(new File("M_L.png"));

		spr_run_l[0] = ImageIO.read(new File("M_Run_l_1.png"));
		spr_run_l[1] = ImageIO.read(new File("M_Run_l_2.png"));
		spr_run_l[2] = ImageIO.read(new File("M_Run_l_3.png"));
		spr_run_l[3] = ImageIO.read(new File("M_Run_l_4.png"));
		spr_run_l[4] = ImageIO.read(new File("M_Run_l_5.png"));
		spr_run_l[5] = ImageIO.read(new File("M_Run_l_6.png"));
		spr_run_l[6] = ImageIO.read(new File("M_Run_l_7.png"));
		spr_run_l[7] = ImageIO.read(new File("M_Run_l_8.png"));
		spr_run_l[8] = ImageIO.read(new File("M_Run_l_9.png"));
		spr_run_l[9] = ImageIO.read(new File("M_Run_l_10.png"));

		spr_run_r[0] = ImageIO.read(new File("M_Run_r_1.png"));
		spr_run_r[1] = ImageIO.read(new File("M_Run_r_2.png"));
		spr_run_r[2] = ImageIO.read(new File("M_Run_r_3.png"));
		spr_run_r[3] = ImageIO.read(new File("M_Run_r_4.png"));
		spr_run_r[4] = ImageIO.read(new File("M_Run_r_5.png"));
		spr_run_r[5] = ImageIO.read(new File("M_Run_r_6.png"));
		spr_run_r[6] = ImageIO.read(new File("M_Run_r_7.png"));
		spr_run_r[7] = ImageIO.read(new File("M_Run_r_8.png"));
		spr_run_r[8] = ImageIO.read(new File("M_Run_r_9.png"));
		spr_run_r[9] = ImageIO.read(new File("M_Run_r_10.png"));

		spr_dash_r = ImageIO.read(new File("M_Dash_R.png"));
		spr_dash_l = ImageIO.read(new File("M_Dash_L.png"));

		spr_jump_r = ImageIO.read(new File("M_Jump_R.png"));
		spr_jump_l = ImageIO.read(new File("M_Jump_L.png"));

		spr_fall_r = ImageIO.read(new File("M_Fall_R.png"));
		spr_fall_l = ImageIO.read(new File("M_Fall_L.png"));

		spr_punch_l = ImageIO.read(new File("M_Punch_L.png"));
		spr_punch_r = ImageIO.read(new File("M_Punch_R.png"));


		} catch(Exception ex) {
			System.out.println("Cannot Read");}

		sprite = sprite_r;

		setHeight(sprite.getHeight());
		setWidth(sprite.getWidth());
	}

	public void update()
	{

	}
	public void draw(Graphics g)
	{
		g.drawImage(sprite, getX() , getY(), null);
		//g.drawRect(getX(), getY(), getWidth(),getHeight());
	}
	public void repaint (Graphics g)
	{
	}

	//returns direction 1 is right, -1 is left.
	public int getDir()
	{
		return dir;
	}

	public int getHP()
	{
		return hp;
	}

	public void addHP(int amount)
	{
		hp += amount;
	}

	public void changeV(int xfactor)
	{
		if(getVX() * xfactor > 0){
			if(Math.abs(getVX()) < 100){
				setVX(getVX() + xfactor);
			}
			else{}
		}
		else{
			stopped();

			dir = (int)Math.signum(xfactor);	//change direction

			setVX(50*getDir());
		}
	}

	//change TO DIRECTION double direction when vx == 0
	public void changeDir(double direction)
	{
		if(direction < 0)
		{
			if(sprite == sprite_r)
				sprite = sprite_l;

			else if(sprite == spr_dash_r)
				sprite = spr_dash_l;

			else if(sprite == spr_jump_r)
				sprite = spr_jump_l;

			else if(sprite == spr_fall_r)
				sprite = spr_fall_l;
		}
		else
		{
			if(sprite == sprite_l)
				sprite = sprite_r;

			else if(sprite == spr_dash_l)
				sprite = spr_dash_r;

			else if(sprite == spr_jump_l)
				sprite = spr_jump_r;

			else if(sprite == spr_fall_l)
				sprite = spr_fall_r;
		}
	}

	public void stopped()
	{
		setVX(0);
	}
	public void fall()
	{
		setVY(getVY() + 10);
	}
	public void jump()
	{
		boolean falling = getFalling();

		if(falling == false)
		{
			doubleJump = false;

			setY(getY() - 10);
			setVY(-201);
		}

		else if(falling == true && doubleJump == false)
		{
			doubleJump = true;

			setY(getY() - 10);
			setVY(-201);
		}
	}
	public void dash(int xShift)
	{
		if(getDir() == 1)
		{
			sprite = spr_dash_r;
		}
		else
		{
			sprite = spr_dash_l;
		}

		setX(getX() + xShift*getDir());
	}

	public void move(double time, boolean movedX, boolean movedY, boolean rightcol, boolean downcol)
	{
		int prevH = sprite.getHeight();	//used to account for the difference in height between sprites
		int prevW = sprite.getWidth();	//used to account for the difference in width between sprites

		boolean hadPunchedL = false;

		if(sprite == spr_punch_l)
			hadPunchedL = true;

		if(getDir() == -1)	//moving left
		{
			if(getPunch() == true)
				sprite = spr_punch_l;

			else if(getVY() == 0){	//not falling or jumping

				if(getVX() == 0)		//no horizontal movement
					sprite = sprite_l;

				else if(sprite == sprite_l)//run from standing
				{
					runAt = 0;
					sprite = spr_run_l[runAt];

				}
				else	//continue running
				{
					runAt = (runAt + 1)%spr_run_l.length;
					sprite = spr_run_l[runAt];
				}
			}
			else if(getVY() > 0)	//falling
				sprite = spr_fall_l;

			else				//jumping
				sprite = spr_jump_l;

		}
		if(getDir() == 1)	//moving right
		{
			if(getPunch() == true)
				sprite = spr_punch_r;

			else if(getVY() == 0){	//not falling or jumping

				if(getVX() == 0)		//no horizontal movement
					sprite = sprite_r;

				else if(sprite == sprite_r)//run from standing
				{
					runAt = 0;
					sprite = spr_run_r[runAt];

				}
				else	//continue running
				{
					runAt = (runAt + 1)%spr_run_r.length;
					sprite = spr_run_r[runAt];
				}
			}
			else if(getVY() > 0)	//falling
				sprite = spr_fall_r;

			else 				//jumping
				sprite = spr_jump_r;

		}

		if(!movedX)
			updateX(time);
		if(!movedY)
			updateY(time);

		setHeight(sprite.getHeight());		//comment out to stop the running and standing glitch (while sprites are not the same height)
		setWidth(sprite.getWidth());

		//System.out.println("Prev H " + prevH + " CurH " + getHeight());
		//System.out.println("Prev Y " + getY() + " CurY " + (getY() + (prevH - getHeight())));

		if(downcol == true)
			setY(getY() + (prevH - getHeight()));

		if(rightcol == true || (getPunch() == true && getDir() == -1)|| (hadPunchedL == true && getPunch() == false))
			setX(getX() + (prevW - getWidth()));


	}
}
