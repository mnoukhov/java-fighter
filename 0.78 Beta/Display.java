//diplay.java
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class Display extends Panel implements MouseListener, KeyListener
{
	private BufferedImage osi = null;	//off screen image
	private Graphics osg = null;		//off screen graphics
	private Processor proc = null;		//processor

	//constructor
	Display(Processor proc)
	{
		this.proc = proc;
		addMouseListener(this);
		addKeyListener(this);
		setBackground(Color.LIGHT_GRAY);
	}

	//paint method
	public void paint (Graphics g)
	{
		Dimension d = getSize();
		osi = new BufferedImage(d.width, d.height,BufferedImage.TYPE_INT_RGB);//required for bufffered image painting
		osg = osi.getGraphics();
		update(g);
	}

	//update the paint method and display the image on the screen
	public void update(Graphics g)
	{
		proc.display(osg, getSize());
		g.drawImage(osi,0,0,this);
	}

	//if user presses a key (moves character)
	public void keyPressed(KeyEvent ke)
	{
		char ch = ke.getKeyChar();
		if(ch == KeyEvent.CHAR_UNDEFINED)
			proc.process(ke.getKeyCode());
		else
			proc.process(ch);
		repaint();
	}
	public void keyReleased(KeyEvent ke)
	{
		char ch = ke.getKeyChar();
		if(ch == KeyEvent.CHAR_UNDEFINED)
			proc.processR(ke.getKeyCode());
		else
			proc.processR(ch);
		repaint();
	}
	public void keyTyped(KeyEvent ke) {}//doesn't inlcude function keys
	public void mouseClicked(MouseEvent me) {}
	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {}

	//if user presses a point on the panel with the mouse
	public void mousePressed(MouseEvent me)
	{
		proc.process(me.getButton(),me.getPoint());
		repaint();
	}

	public void mouseReleased(MouseEvent me) {}
}



