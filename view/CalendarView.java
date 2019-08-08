package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import model.CalendarBean;

public class CalendarView extends JPanel implements ActionListener {

	public static JTextArea area = new JTextArea();
	String filePath = "input.txt";
	JPanel calendarPanel;
	JButton save, delete;
	JLabel noteLabel;
	static JScrollPane jsp;
	JButton dayViewBtn, weekViewBtn, monthViewBtn, create, loadfile, agenda;
	JPanel botPanel;
	JTable table;
	String currentView = "DAY";
	JList<String> jlist;
	JLabel clickedDayLabel[] = new JLabel[42];
	String[][] Hours = {
			{"0:00",""},
			{"1:00",""},
			{"2:00",""},
			{"3:00",""},
			{"4:00",""},
			{"5:00",""},
			{"7:00",""},
			{"8:00",""},
			{"9:00",""},
			{"10:00",""},
			{"11:00",""},
			{"12:00",""},
			{"13:00",""},
			{"14:00",""},
			{"15:00",""},
			{"16:00",""},
			{"17:00",""},
			{"18:00",""},
			{"19:00",""},
			{"20:00",""},
			{"21:00",""},
			{"22:00",""},
			{"23:00",""}};
	String[] dayTitle = {"time","event"};
	String[] weekTitle = {"time","S", "M", "T", "W", "H", "F", "A" };
	String[] monthTitle = {"S", "M", "T", "W", "H", "F", "A" };
	String[] agendaTitle = {"event"};
//	String[][] weekEvent = {{"","","","","","",""}};
	String[][] weekEvent = {
			{"0:00","","","","","","",""},
			{"1:00","","","","","","",""},
			{"2:00","","","","","","",""},
			{"3:00","","","","","","",""},
			{"4:00","","","","","","",""},
			{"5:00","","","","","","",""},
			{"7:00","","","","","","",""},
			{"8:00","","","","","","",""},
			{"9:00","","","","","","",""},
			{"10:00","","","","","","",""},
			{"11:00","","","","","","",""},
			{"12:00","","","","","","",""},
			{"13:00","","","","","","",""},
			{"14:00","","","","","","",""},
			{"15:00","","","","","","",""},
			{"16:00","","","","","","",""},
			{"17:00","","","","","","",""},
			{"18:00","","","","","","",""},
			{"19:00","","","","","","",""},
			{"20:00","","","","","","",""},
			{"21:00","","","","","","",""},
			{"22:00","","","","","","",""},
			{"23:00","","","","","","",""}};
	String[][] monthEvent = new String[12][7];
	JLabel weekLabel[] = new JLabel[7];
	public CalendarView() {
		super();
//		calendarPanel = new JPanel();
		JPanel jpnorth = new JPanel();
		dayViewBtn = new JButton("Day");
		weekViewBtn = new JButton("Week");
		monthViewBtn = new JButton("Month");
		
		loadfile = new JButton("LoadFile");
		agenda = new JButton("Agenda");
		jpnorth.add(dayViewBtn);
		jpnorth.add(weekViewBtn);
		jpnorth.add(monthViewBtn);
		jpnorth.add(agenda);
		
		jpnorth.add(loadfile);
		
		dayViewBtn.addActionListener(this);
		weekViewBtn.addActionListener(this);
		monthViewBtn.addActionListener(this);
		
		loadfile.addActionListener(this);
		agenda.addActionListener(this);
		table = new JTable(Hours,dayTitle);
		jsp = new JScrollPane(table);
//		calendarPanel.add(table);
		noteLabel = new JLabel();
		this.noteLabel.setBounds(0, 0, 600, 300);

		this.setLayout(new BorderLayout());
//		this.add(botPanel, BorderLayout.SOUTH);
		this.add(jpnorth, BorderLayout.NORTH);
		this.add(jsp, BorderLayout.CENTER);
//		this.remove(jsp);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == dayViewBtn) {
			File file = new File(filePath);
			BufferedReader reader;
			ArrayList<String> events = new ArrayList<String>();
			for(int i = 0; i < 23; i++) {
				Hours[i][1] = "";
			}
			try {
				reader = new BufferedReader(new FileReader(file));
				String line = "";
				while((line = reader.readLine()) != null) {
					events.add(line);
				}
//				CalendarPanel c = new CalendarPanel();
				for(String t : events) {
					String[] temp = t.split(";");
					System.out.println(CalendarBean.getClickedYear());
					System.out.println(CalendarBean.getClickedMonth());
					System.out.println(CalendarBean.getClickedDay());
					System.out.println(Integer.valueOf(temp[1]));
					System.out.println(Integer.valueOf(temp[2]));
					System.out.println(Integer.valueOf(temp[3]));
					if(CalendarBean.getClickedYear() == Integer.valueOf(temp[1])
							&& CalendarBean.getClickedMonth() >= Integer.valueOf(temp[2]) && CalendarBean.getClickedMonth() <= Integer.valueOf(temp[3])){
						System.out.println("tttttttttt");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
						Calendar cl = Calendar.getInstance();   
						cl.setTime(sdf.parse(CalendarBean.getClickedYear() + "-" + CalendarBean.getClickedMonth() + "-" + CalendarBean.getClickedDay()));
						int selectDay = cl.get(Calendar.DAY_OF_WEEK);
						System.out.println(selectDay);
//						System.out.println(cl.get(Calendar.DAY_OF_WEEK));
						char[] dayOfWeek = temp[4].toCharArray();
						boolean flag = false;
						for(char c : dayOfWeek) {
							if(c == 'S' && selectDay == 1)
								flag = true;
							else if(c == 'M' && selectDay == 2)
								flag = true;
							else if(c == 'T' && selectDay == 3)
								flag = true;
							else if(c == 'W' && selectDay == 4)
								flag = true;
							else if(c == 'H' && selectDay == 5)
								flag = true;
							else if(c == 'F' && selectDay == 6)
								flag = true;
							else if(c == 'A' && selectDay == 7)
								flag = true;
						}
						System.out.println(flag);
						if(flag) {
							for(int k = Integer.valueOf(temp[5])-1; k < Integer.valueOf(temp[6]); k++) {
								Hours[k][1] = temp[0];
							}
						}
					}
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			DefaultTableModel defaultTableModel = new DefaultTableModel(Hours,dayTitle);
			table.setModel(defaultTableModel);
		}else if(e.getSource() == weekViewBtn) {
			File file = new File(filePath);
			BufferedReader reader;
			ArrayList<String> events = new ArrayList<String>();
			for(int i = 0; i < 23; i++) {
				for(int j = 1; j < 7; j++)
					weekEvent[i][j] = "";
			}
			try {
				reader = new BufferedReader(new FileReader(file));
				String line = "";
				while((line = reader.readLine()) != null) {
					events.add(line);
				}
//				CalendarPanel c = new CalendarPanel();
				for(String t : events) {
					String[] temp = t.split(";");
					System.out.println(CalendarBean.getClickedYear());
					System.out.println(CalendarBean.getClickedMonth());
					System.out.println(CalendarBean.getClickedDay());
					System.out.println(Integer.valueOf(temp[1]));
					System.out.println(Integer.valueOf(temp[2]));
					System.out.println(Integer.valueOf(temp[3]));
					if(CalendarBean.getClickedYear() == Integer.valueOf(temp[1])
							&& CalendarBean.getClickedMonth() >= Integer.valueOf(temp[2]) && CalendarBean.getClickedMonth() <= Integer.valueOf(temp[3])){
						
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
						Calendar cl = Calendar.getInstance();   
						cl.setTime(sdf.parse(CalendarBean.getClickedYear() + "-" + CalendarBean.getClickedMonth() + "-" + CalendarBean.getClickedDay()));
						int selectDay = cl.get(Calendar.DAY_OF_WEEK);
						cl.add(Calendar.DATE, 1-selectDay);
//						System.out.println("tetdf " + cl.get(Calendar.DAY_OF_WEEK));
						for(int i = 0; i < 7; i++) {
							selectDay = cl.get(Calendar.DAY_OF_WEEK);
							System.out.println(cl.get(Calendar.DAY_OF_WEEK));
							char[] dayOfWeek = temp[4].toCharArray();
							boolean flag = false;
							for(char c : dayOfWeek) {
								if(c == 'S' && selectDay == 1)
									flag = true;
								else if(c == 'M' && selectDay == 2)
									flag = true;
								else if(c == 'T' && selectDay == 3)
									flag = true;
								else if(c == 'W' && selectDay == 4)
									flag = true;
								else if(c == 'H' && selectDay == 5)
									flag = true;
								else if(c == 'F' && selectDay == 6)
									flag = true;
								else if(c == 'A' && selectDay == 7)
									flag = true;
							}
							System.out.println(flag);
							if(flag) {
								for(int k = Integer.valueOf(temp[5])-1; k < Integer.valueOf(temp[6]); k++) {
									weekEvent[k][i+1] = temp[0];
								}
							}
							cl.add(Calendar.DATE, 1);
						}
					}
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(int i = 0; i < 7; i ++) {
//				weekEvent[0][i] =  
			}
			DefaultTableModel defaultTableModel = new DefaultTableModel(weekEvent,weekTitle);
			table.setModel(defaultTableModel);
		}else if(e.getSource() == monthViewBtn){
			CalendarBean calendar = new CalendarBean();
			String[] days = calendar.getDays();
 			for(int i = 0; i < 6;i ++) {
				for(int j = 0; j < 7; j++) {
					monthEvent[i*2][j] = days[i*7+j];
					monthEvent[i*2+1][j] = "";
				}
			}
 			File file = new File(filePath);
			BufferedReader reader;
			ArrayList<String> events = new ArrayList<String>();
			System.out.println(CalendarBean.getClickedYear());
			System.out.println(CalendarBean.getClickedMonth());
			System.out.println(CalendarBean.getClickedDay());
			try {
				reader = new BufferedReader(new FileReader(file));
				String line = "";
				while((line = reader.readLine()) != null) {
					events.add(line);
				}
//				CalendarPanel c = new CalendarPanel();
				for(String t : events) {
					String[] temp = t.split(";");
					
					System.out.println(Integer.valueOf(temp[1]));
					System.out.println(Integer.valueOf(temp[2]));
					System.out.println(Integer.valueOf(temp[3]));
					if(CalendarBean.getClickedYear() == Integer.valueOf(temp[1])
							&& CalendarBean.getClickedMonth() >= Integer.valueOf(temp[2]) && CalendarBean.getClickedMonth() <= Integer.valueOf(temp[3])){
						
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
						Calendar cl = Calendar.getInstance();   
						cl.setTime(sdf.parse(CalendarBean.getClickedYear() + "-" + CalendarBean.getClickedMonth() + "-" + CalendarBean.getClickedDay()));
						int selectDay = cl.get(Calendar.DAY_OF_MONTH);
						
						cl.add(Calendar.DATE, 1-selectDay);
						int firstDay = cl.get(Calendar.DAY_OF_WEEK);
//						System.out.println("tetdf " + cl.get(Calendar.DAY_OF_WEEK));
						int maxDay = cl.getActualMaximum(Calendar.DAY_OF_MONTH);
						System.out.println(cl.getActualMaximum(Calendar.DAY_OF_MONTH));
						for(int i = 0; i < maxDay; i++) {
							selectDay = cl.get(Calendar.DAY_OF_WEEK);
							System.out.println(cl.get(Calendar.DAY_OF_MONTH));
							char[] dayOfWeek = temp[4].toCharArray();
							boolean flag = false;
							for(char c : dayOfWeek) {
								if(c == 'S' && selectDay == 1)
									flag = true;
								else if(c == 'M' && selectDay == 2)
									flag = true;
								else if(c == 'T' && selectDay == 3)
									flag = true;
								else if(c == 'W' && selectDay == 4)
									flag = true;
								else if(c == 'H' && selectDay == 5)
									flag = true;
								else if(c == 'F' && selectDay == 6)
									flag = true;
								else if(c == 'A' && selectDay == 7)
									flag = true;
							}
							System.out.println(flag);
							if(flag) {
								monthEvent[((i+firstDay)/7)*2+1][(i+firstDay)%7-1] += temp[0] + ";\n";
							}
							cl.add(Calendar.DATE, 1);
						}
					}
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			DefaultTableModel defaultTableModel = new DefaultTableModel(monthEvent,monthTitle);
			table.setModel(defaultTableModel);
		}else if(e.getSource() == agenda){
			File file = new File(filePath);
			BufferedReader reader;
			ArrayList<String> events = new ArrayList<String>();
			String[][] agendaEvent = null;
			try {
				reader = new BufferedReader(new FileReader(file));
				String line = "";
				while((line = reader.readLine()) != null) {
					events.add(line);
				}
				System.out.println(events.size());
				agendaEvent = new String[events.size()][1];
				int i = 0;
				for(String t : events) {
					agendaEvent[i][0] = t;
					i++;
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
			DefaultTableModel defaultTableModel = new DefaultTableModel(agendaEvent,agendaTitle);
			table.setModel(defaultTableModel);
		}else {
			JFileChooser jfc=new JFileChooser();
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY );
			jfc.showDialog(new JLabel(), "select");
			File file=jfc.getSelectedFile();
			if(file != null)
				filePath = file.getAbsolutePath();
//			System.out.println(filePath);
		}
	}

}
