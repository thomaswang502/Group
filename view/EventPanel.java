package view;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class EventPanel extends JPanel implements ActionListener {
	public static JTextArea area = new JTextArea();

	JButton save, delete;
	JLabel noteLabel;

	JPanel botPanel;
	int year,month,day;
	String filePath = "input.txt";
	
	public EventPanel(int year,int month,int day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		noteLabel = new JLabel();
		this.noteLabel.setBounds(0, 0, 600, 300);

		area.setLineWrap(true); // switch line if the text too long
		area.setText("");
		area.setText(String.valueOf(year) + " " + String.valueOf(month) + " " + String.valueOf(day));
		botPanel = new JPanel();

		save = new JButton("Save");
		save.addActionListener(this);
		delete = new JButton("Delete");
		delete.addActionListener(this);
		botPanel.add(save);
//		botPanel.add(delete);

		this.setLayout(new BorderLayout());
		this.add(botPanel, BorderLayout.SOUTH);
		this.add(new JScrollPane(area), BorderLayout.CENTER);


	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// Math Class;2014;1;2;MWF;17;18;
		String yearReg = "^\\d{4}$";
		String monthReg = "^[1-9]|10|11|12$";
		String hourReg = "^(1?[0-9]|2[0-4])$";
		String weekReg = "^S|M|T|W|H|F|A$";
		if (e.getSource() == save) {
			System.out.println("save");
			String[] input = area.getText().split(";");
			System.out.println(input.length);
			if(input.length == 7 &&
					input[1].matches(yearReg) &&
					input[2].matches(monthReg) &&
					input[3].matches(monthReg) &&
					input[5].matches(hourReg) &&
					input[6].matches(hourReg)) {
				char[] dayOfWeek = input[4].toCharArray();
				boolean flag = true;
				for(char c : dayOfWeek) {
					if(String.valueOf(c).matches(weekReg))
						continue;
					else
						flag = false;
				}
				if(flag) {
					File file = new File(filePath);
					try {
						BufferedReader reader = new BufferedReader(new FileReader(file));
						ArrayList<String> events = new ArrayList<String>();
						String line = "";
						while((line = reader.readLine()) != null) {
							events.add(line);
						}
						for(String t : events) {
							String[] temp = t.split(";");
 							System.out.println(t);
							if(input[1].equals(temp[1]) && Integer.valueOf(input[2]) <= Integer.valueOf(input[3])) {
								if(Integer.valueOf(input[2]) >= Integer.valueOf(temp[2]) && Integer.valueOf(input[2]) <= Integer.valueOf(temp[3])
										|| Integer.valueOf(input[3]) >= Integer.valueOf(temp[2]) && Integer.valueOf(input[3]) <= Integer.valueOf(temp[3])) {
									char[] exist = temp[4].toCharArray();
									for(char c : dayOfWeek) {
										for(char ex : exist) {
											if (ex == c) {
												if(Integer.valueOf(input[5]) <= Integer.valueOf(input[6])) {
													if(Integer.valueOf(input[5]) >= Integer.valueOf(temp[5]) && Integer.valueOf(input[5]) < Integer.valueOf(temp[6])
															|| Integer.valueOf(input[6]) > Integer.valueOf(temp[5]) && Integer.valueOf(input[6]) <= Integer.valueOf(temp[6])
															|| Integer.valueOf(input[5]) <= Integer.valueOf(temp[5]) && Integer.valueOf(input[6]) >= Integer.valueOf(temp[6])) {
														flag = false;
													}
												}else
													flag = false;
											}
										}
									}
								}
							}
						}
						if(flag) {
							events.add(area.getText());
							BufferedWriter writer = new BufferedWriter(new FileWriter(file));
							for(String t : events) {
								writer.write(t);
								writer.newLine();
							}
							writer.flush();
							writer.close();
						}
						reader.close();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(!flag) {
					JOptionPane.showMessageDialog(getParent(),
							"input error, check your input please");
				}
			}else {
				JOptionPane.showMessageDialog(getParent(),
						"input error, check your input please");
			}
		} 
//		else if (e.getSource() == delete) {
//			System.out.println("delete");
//			/**
//			 * To-do: check db, if exist, delete on db
//			 */
//		}
	}

}