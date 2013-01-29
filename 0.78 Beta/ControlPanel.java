// File ControlPanel.java
import java.awt.* ;
import java.awt.event.* ;

public class ControlPanel extends Panel implements ActionListener
{
	Processor proc;	//processor

	Label[] labels = null;	//labels showing the stats
	//private Button			newGame = null;	//new game button



	ControlPanel(Processor proc)
	{
		this.proc = proc;
		labels = new Label[3];
		//newGame = new Button("New Game");



		for(int i = 0; i < labels.length; i ++)
		{
			labels[i] = new Label("");
		}

		this.setLayout(new GridLayout(1,4));
		this.add(labels[0]);
		this.add(labels[1]);
		this.add(labels[2]);
		//this.add(newGame);




	}

	public void showStats (String[] stats)
	{
		labels[0].setText(stats[0]);
		labels[1].setText(stats[1]);
		labels[2].setText(stats[2]);
	}

	public void actionPerformed( ActionEvent ae )
	{	// get the reference to the AWT control that caused the event !
		Object eventSource = ae.getSource() ;

		/*
		if ( eventSource == (Object) newGame )
		{
			proc.newGame();
		}
		*/
	}
}
