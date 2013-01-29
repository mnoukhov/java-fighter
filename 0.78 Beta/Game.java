//Game.java

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import java.io.*;

public class Game
{
	private int[] stats = new int[17];
	private char col = 'z';//collision
	private char col2 = 'z';
	private boolean falling = false;
	private boolean falling2 = false;
	private BufferedImage bg = null;


	Processor proc;

	private Type1b player1 = new Type1b(20,120);
	private Type2 player2 = new Type2 (50, 120);
	private Stage[] stageParts = new Stage[12];

	Character[] elements = new Character[1];

	public Game(Processor proc) //,Dimension d
	{
		this.proc = proc;

		try{
			 bg = ImageIO.read(new File("stage2.png"));
		} catch(Exception ex) {	System.out.println("Cannot Read");}

		stageParts[0] = new Stage(-3, 0, 5, 600);
		stageParts[1] = new Stage(940, 0, 10, 600);
		stageParts[2] = new Stage(0, 222, 174, 24);
		stageParts[3] = new Stage(156, 237, 216, 30);
		stageParts[4] = new Stage(252,362,219,25);
		stageParts[5] = new Stage(435,509,215,23);
		stageParts[6] = new Stage(58,469,215,23);
		stageParts[7] = new Stage(545,176,213,22);
		stageParts[8] = new Stage(542,309,216,20);
		stageParts[9] = new Stage(790,433,170,19);
		stageParts[10] = new Stage(865,175,75,19);
		stageParts[11] = new Stage(0,585,960,15);

	}

	public void move(double timestep)
	{
		boolean rightcol = false;
		boolean movedY = false;
		boolean movedX = false;
		boolean rightcol2 = false;
		boolean movedY2 = false;
		boolean movedX2 = false;

		char playerCol = 'z';

		falling = true;
		falling2 = true;

		stats[3] = player1.getX();
		stats[4] = player1.getY();
		stats[5] = player1.getWidth();
		stats[6] = player1.getHeight();
		stats[11] = player1.getVX();
		stats[12] = player1.getVY();
		stats[15] = player1.getHP();

		stats[7] = player2.getX();
		stats[8] = player2.getY();
		stats[9] = player2.getWidth();
		stats[10] = player2.getHeight();
		stats[13] = player2.getVX();
		stats[14] = player2.getVY();
		stats[16] = player2.getHP();

		for(int i = 0; i < stageParts.length;i ++)
		{
			col = player1.checkCollision(timestep, stageParts[i], true);
			col2 = player2.checkCollision(timestep, stageParts[i], true);

			if(col == 'd'){
				falling = false;
				movedY = true;
			}
			else if(col == 'r'){
				rightcol = true;
				movedX = true;
			}
			else if(col == 'l'){
				movedX = true;
			}
			else if(col == 'u'){
				movedY = true;
				player1.setVY(1);
				player1.fall();
			}
			if(col2 == 'd'){
				falling2 = false;
				movedY2 = true;
			}
			else if(col2 == 'r'){
				rightcol2 = true;
				movedX2 = true;
			}
			else if(col2 == 'l'){
				movedX2 = true;
			}
			else if(col2 == 'u'){
				movedY2 = true;
				player2.setVY(1);
				player1.fall();
			}
		}

		System.out.println(player1.getVY());


		playerCol = player2.checkCollision(timestep, player1, false);

		if(player1.getPunch() == true){
			if(player2.getPunch() == true){
				if(playerCol != 'z'){
					player2.collide(timestep, player1, playerCol);
					movedX2 = true;
					movedX = true;
					//collide both of them equally
					//perhaps something needed
				}
			}
			else if(playerCol != 'z'){
				player2.collide(timestep, player1, playerCol);
				movedX2 = true;
				player2.addHP(-5);
			}
		}

		else if(player2.getPunch() == true){

			playerCol = player1.checkCollision(timestep, player2, false);

			if(playerCol != 'z'){

				if(player2.getDir() == 1)
					playerCol = 'l';
				else
					playerCol = 'r';

				player1.collide(timestep, player2, playerCol);
				movedX = true;
				player1.addHP(-5);
			}
		}


		if(falling == true){
			player1.setFalling(falling);
			player1.fall();
		}

		else if(falling == false){
			player1.setVY(0);
			player1.setFalling(false);
		}

		if(falling2 == true){
			player2.setFalling(falling2);
			player2.fall();
		}

		else if(falling2 == false){
			player2.setVY(0);
			player2.setFalling(false);
		}

		player1.move(timestep,movedX, movedY, rightcol,!falling);
		player2.move(timestep,movedX2, movedY2, rightcol2,!falling2);
	}

	public void display (Graphics g, Dimension d)
	{
		//fill Background
		g.drawImage(bg, 0,0, null);

		player1.draw(g);
		player2.draw(g);

		for(int i = 0; i < stageParts.length;i ++)
		{
			stageParts[i].draw(g);
		}

	}


	//mousepressed
	public void process (int button, Point p)
	{
		stats[0] = button;
		stats[1] = p.x;
		stats[2] = p.y;
	}

	//keypressed char
	public void process(int key)
	{
		if(key == KeyEvent.VK_RIGHT) {
			if(col != 'r')	{
				player1.changeV(40);
			}
		}

		else if(key == KeyEvent.VK_LEFT) {
			if(col != 'l') {
				player1.changeV(-40);
			}
		}

		else if(key == KeyEvent.VK_UP) {
			if(col != 'u') {
				player1.jump();
			}
		}

		else if(key == KeyEvent.VK_DOWN) {
			player1.setPunch(true);
		}
	}

	//keypressed function
	public void process(char ch)
	{
		if(ch == 'd') {
			player2.changeV(30);
		}

		else if(ch == 'a') {
			player2.changeV(-30);
		}

		else if(ch == 'w') {
			player2.jump();
		}

		else if(ch == 's') {
			player2.setPunch(true);
			/*
			for(int i = 50; i > 0; i -= 5)
			{
				if(player2.checkCollision(player2.getDir() * i,0, player1) == 'z'){
					player2.dash(i,0);
				}
			}
			*/
		}
	}

	public void processR(int key)
	{
		if(key == KeyEvent.VK_RIGHT){
			player1.stopped();
		}
		if(key == KeyEvent.VK_LEFT){
			player1.stopped();
		}
		if(key == KeyEvent.VK_UP)
			player1.setFalling(true);
		if(key == KeyEvent.VK_DOWN);
			player1.setPunch(false);
	}

	public void processR(char ch)
	{
		if(ch == 'a')
			player2.stopped();
		if(ch == 'd')
			player2.stopped();
		if(ch == 's')
			player2.setPunch(false);
		if(ch == 'w')
			player2.setFalling(true);
	}

	public String[] getStats()
	{
		String[] message = new String[] {"Mouse " + stats[0] + " clicked at " + stats[1] + ", " + stats[2],
						 				 "Player 1 at (" + stats[3] + " - " + (stats[3] + stats[5]) + " , "
						 				 		+ stats[4] + " - " + (stats[4] + stats[6]) + " )" + " Vx " +
						 				 		stats[11] + " Vy " + stats[12] + " HP : " + stats[15],
						 				 "Player 2 at (" + stats[7] + " - " + (stats[7] + stats[9]) + " , "
						 				 		+ stats[8] + " - " + (stats[8] + stats[10]) + " )"+  " Vx " +
						 				 		stats[13] + " Vy " + stats[14] + " HP : " + stats[16]};
		return message;
	}


}