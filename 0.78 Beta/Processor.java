import java.awt.* ;
import java.util.*;

public class Processor extends Panel
{

	private Display			display = null;	// references to the display panel
	private ControlPanel	control = null;	// references to the control panel

	private Game			game = null;	// references to the game itself.
	private Timer 			tm = new Timer();		//timer

	// constructor
  	public Processor( )
 	{
		this.setBackground(Color.LIGHT_GRAY);
		this.setLayout(new BorderLayout());

		game = new Game(this) ;

		display = new Display(this);

		control = new ControlPanel (this);//add param later

		this.add(display, BorderLayout.CENTER);
		this.add(control, BorderLayout.NORTH);

		tm.scheduleAtFixedRate(ttask,0,50);
 	}

	TimerTask ttask = new TimerTask()
	{
		public void run(){
			game.move(0.05);	//move all elements
			display.repaint();//repaint display
			showStats();
		}
	};

	public void newGame()
	{
		remove(display) ;
		tm.cancel();
	}


 	public void display(Graphics g, Dimension d)
 	{
		game.display(g,d);
	}

	public void stopTimer()
	{
		System.out.println("Here");
		try{
			ttask.wait(10000);
		}catch (Exception wait){System.out.println("NOT WORK");}
	}

 	// public methods
 	public void exit()
	{
		remove(display) ;

		remove(control) ;

		repaint() ;

	}

	public void paint( Graphics g)
	{
		Dimension d = this.getSize() ;

		String str = "The End !";

		FontMetrics fm = getFontMetrics( this.getFont() ) ;

		int w = fm.stringWidth( str ) ;

		int h = fm.getHeight() ;

		g.drawString( str , d.width/2 - w/2, d.height/2 + h/4 ) ;
	}

	//for mousepressed/clicked
	public void process (int button, Point p)
	{
		game.process(button, p);
	}

	//keypressed of a charater
	public void process (char ch)
	{
		game.process(ch);
	}

	//keypressed of a function
	public void process (int key)
	{
		game.process(key);
	}

	//keypressed of a charater
	public void processR (char ch)
	{
		game.processR(ch);
	}

	//keypressed of a function
	public void processR (int key)
	{
		game.processR(key);
	}

	public void showStats()
	{
		control.showStats(game.getStats());
	}

}
