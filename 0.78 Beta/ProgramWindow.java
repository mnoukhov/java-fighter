//class ProgramWindow

import java.awt.*;
import java.awt.event.*;

public class ProgramWindow extends Frame implements WindowListener
{
	Processor proc = new Processor();		//the processor (starts it)

	public ProgramWindow ()
	{
		setTitle("Panel");
		setSize(960,670);
		setLocation(50,50);
		setResizable(true);
		add(proc);
		setVisible(true);
		addWindowListener(this);
	}

	public void windowActivated(WindowEvent we){}
	public void windowClosed(WindowEvent we){System.exit(0);}
	public void windowClosing(WindowEvent we){dispose();}
	public void windowDeactivated(WindowEvent we){}
	public void windowDeiconified(WindowEvent we){}
	public void windowIconified(WindowEvent we){}
	public void windowOpened(WindowEvent we){}
}