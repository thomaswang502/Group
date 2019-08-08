package main;

import javax.swing.*;

import view.CalendarPanel;
import view.CalendarView;
import view.EventPanel;

public class CalendarMain extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame mainFrame = new JFrame();
		mainFrame.setTitle("Calendar Demo");
		mainFrame.setSize(1050, 400);
		mainFrame.setLocation(200, 300);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(10);
		splitPane.setDividerLocation(550);
		splitPane.setLeftComponent(new CalendarPanel());
		splitPane.setRightComponent(new CalendarView());
		mainFrame.add(splitPane);

		mainFrame.setVisible(true);
	}

}
