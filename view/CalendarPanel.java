package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

import model.CalendarBean;

public class CalendarPanel extends JPanel implements ActionListener {

    JLabel clickedDayLabel[] = new JLabel[42];
	static int currentYear;
	static int currentMonth;
	static int currentDay;
	static int clickedYear,clickedMonth;
	
	JLabel weekLabel[] = new JLabel[7];
	String weeks[] = { "S", "M", "T", "W", "T", "F", "S" };
	JTextField mText, yText;
	JButton nextBtn, preBtn, searchBtn, todayBtn, create;
	JLabel yTag, mTag, dTag;
	int year, month;
	CalendarBean calendar;
	static String clickedDay;
//	EventPanel eventPanel = new EventPanel();

	public CalendarPanel() {
		// TODO Auto-generated constructor stub
		JPanel calendarPanel = new JPanel();
		calendarPanel.setLayout(new GridLayout(7, 7));

		for (int i = 0; i < 7; i++) {
			weekLabel[i] = new JLabel(weeks[i], JLabel.CENTER);
			calendarPanel.add(weekLabel[i]);
		}
		
			
		for (int i = 0; i < 42; i++) {
			clickedDayLabel[i] = new JLabel("", JLabel.CENTER);

			calendarPanel.add(clickedDayLabel[i]);
			final int j = i;

			clickedDayLabel[j].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
//					
					clickedDay = clickedDayLabel[j].getText();
//					
					
					if (clickedDay != null) {
						String clickedDays[] = calendar.getDays();
//						
							month ++;
							if (month >= 12) {
								month = 1;
								year = year + 1;
								calendar.setMonth(month);
								calendar.setYear(year);
								String clickedDay[] = calendar.getDays();
								
								for (int i = 0; i < 42; i++) {
									clickedDayLabel[i].setText(clickedDay[i]);
									if(String.valueOf(currentDay).equals(clickedDay[i])
											&& currentMonth == calendar.getMonth()
											&& currentYear == calendar.getYear()) {
										clickedDayLabel[i].setOpaque(true);
										clickedDayLabel[i].setBackground(Color.YELLOW);
									}else {
										clickedDayLabel[i].setOpaque(false);
									}
								}
							} else {
								month = month + 1;
								calendar.setMonth(month);
								calendar.setYear(year);
								String clickedDay[] = calendar.getDays();
								for (int i = 0; i < 42; i++) {
									clickedDayLabel[i].setText(clickedDay[i]);
									if(String.valueOf(currentDay).equals(clickedDay[i])
											&& currentMonth == calendar.getMonth()
											&& currentYear == calendar.getYear()) {
										clickedDayLabel[i].setOpaque(true);
										clickedDayLabel[i].setBackground(Color.YELLOW);
									}else {
										clickedDayLabel[i].setOpaque(false);
									}
								}
							}
							month --;
							if (month <= 1) {
								month = 12;
								year = year - 1;
								calendar.setYear(year);
								calendar.setMonth(month);
								String clickedDay[] = calendar.getDays();
								for (int i = 0; i < 42; i++) {
									clickedDayLabel[i].setText(clickedDay[i]);
									if(String.valueOf(currentDay).equals(clickedDay[i])
											&& currentMonth == calendar.getMonth()
											&& currentYear == calendar.getYear()) {
										clickedDayLabel[i].setOpaque(true);
										clickedDayLabel[i].setBackground(Color.YELLOW);
									}else {
										clickedDayLabel[i].setOpaque(false);
									}
								}
							} else {
								month = month - 1;
								calendar.setMonth(month);
								calendar.setYear(year);
								String clickedDay[] = calendar.getDays();
								for (int i = 0; i < 42; i++) {
									clickedDayLabel[i].setText(clickedDay[i]);
									if(String.valueOf(currentDay).equals(clickedDay[i])
											&& currentMonth == calendar.getMonth()
											&& currentYear == calendar.getYear()) {
										clickedDayLabel[i].setOpaque(true);
										clickedDayLabel[i].setBackground(Color.YELLOW);
									}else {
										clickedDayLabel[i].setOpaque(false);
									}
								}
							}
							for (int i = 0; i < 42; i++) {
								clickedDayLabel[i].setText(clickedDays[i]);
								if(String.valueOf(currentDay).equals(clickedDays[i])
										&& currentMonth == calendar.getMonth()
										&& currentYear == calendar.getYear()) {
									clickedDayLabel[i].setOpaque(true);
									clickedDayLabel[i].setBackground(Color.YELLOW);
								}else if(clickedDay.equals(clickedDays[i])){
									clickedDayLabel[i].setOpaque(true);
									clickedDayLabel[i].setBackground(Color.RED);
								}else {
									clickedDayLabel[i].setOpaque(false);
								}
							}
						yText.setText(String.valueOf(calendar.getYear()));
						mText.setText(String.valueOf(calendar.getMonth()));
						clickedYear = calendar.getYear();
						clickedMonth = calendar.getMonth();
						CalendarBean.setClickedDay(clickedDay);
						/**
						 * To-do: check db, if exist, show on panel
						 */
//						JOptionPane.showMessageDialog(getParent(),
//								"no record in " + clickedDay + "/" + calendar.getMonth() + "/" + calendar.getYear());
					}
				}
			});
		}

		calendar = new CalendarBean();
		year = calendar.getYear();
		month = calendar.getMonth();
		calendar.setYear(year);
		calendar.setMonth(month);
		
		clickedDay = String.valueOf(Calendar.getInstance().get(Calendar.DATE));
		calendar.setClickedDay(clickedDay);
		String clickedDay[] = calendar.getDays();
		currentYear = Calendar.getInstance().get(Calendar.YEAR);
		clickedYear = currentYear;
		
		currentMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
		clickedMonth = currentMonth;
		currentDay = Calendar.getInstance().get(Calendar.DATE);
		calendar.setClickedYear(clickedYear);
		calendar.setClickedMonth(clickedMonth);
		
		for (int i = 0; i < 42; i++) {
			clickedDayLabel[i].setText(clickedDay[i]);
			if(String.valueOf(currentDay).equals(clickedDay[i])
					&& currentMonth == calendar.getMonth()
					&& currentYear == calendar.getYear()) {
				clickedDayLabel[i].setOpaque(true);
				clickedDayLabel[i].setBackground(Color.YELLOW);
			}else {
				clickedDayLabel[i].setOpaque(false);
			}
		}
		
		todayBtn = new JButton("Today");
		yTag = new JLabel("Year: ");
		mTag = new JLabel("Month: ");
		searchBtn = new JButton("Search");
		mText = new JTextField(5);
		yText = new JTextField(5);
		nextBtn = new JButton(">");
		preBtn = new JButton("<");
		create = new JButton("Create");
		todayBtn.addActionListener(this);
		searchBtn.addActionListener(this);
		nextBtn.addActionListener(this);
		preBtn.addActionListener(this);
		create.addActionListener(this);
		
		JPanel jpnorth = new JPanel(), jpsouth = new JPanel();

		jpnorth.add(todayBtn);
		jpnorth.add(create);
		jpnorth.add(mTag);
		jpnorth.add(mText);
		jpnorth.add(yTag);
		jpnorth.add(yText);
		jpnorth.add(searchBtn);
		jpnorth.add(preBtn);
		jpnorth.add(nextBtn);
		
//		jpsouth.add(EventPanel.tim);

		yText.setText(String.valueOf(calendar.getYear()));
		mText.setText(String.valueOf(calendar.getMonth()));
		
		ScrollPane scarollpane = new ScrollPane();
		scarollpane.add(calendarPanel);
		this.setLayout(new BorderLayout());
		this.add(scarollpane, BorderLayout.CENTER);
		this.add(jpnorth, BorderLayout.NORTH);
		this.add(jpsouth, BorderLayout.SOUTH);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == nextBtn) {
			if(CalendarBean.getClickedMonth() >= 12) {
//				clickedYear = CalendarPanel.getCurrentYear()+1;
				CalendarBean.setClickedYear(CalendarBean.getClickedYear()+1);
//				clickedMonth = 1;
				CalendarBean.setClickedMonth(1);
			}else {
				CalendarBean.setClickedMonth(CalendarBean.getClickedMonth()+1);
			}
			CalendarBean.setClickedDay("1");
			if (month >= 12) {
				month = 1;
				year = year + 1;
				calendar.setMonth(month);
				calendar.setYear(year);
				String clickedDay[] = calendar.getDays();
				
				for (int i = 0; i < 42; i++) {
					clickedDayLabel[i].setText(clickedDay[i]);
					if(String.valueOf(currentDay).equals(clickedDay[i])
							&& currentMonth == calendar.getMonth()
							&& currentYear == calendar.getYear()) {
						clickedDayLabel[i].setOpaque(true);
						clickedDayLabel[i].setBackground(Color.YELLOW);
					}else {
						clickedDayLabel[i].setOpaque(false);
					}
				}
			} else {
				month = month + 1;
				calendar.setMonth(month);
				calendar.setYear(year);
				String clickedDay[] = calendar.getDays();
				for (int i = 0; i < 42; i++) {
					clickedDayLabel[i].setText(clickedDay[i]);
					if(String.valueOf(currentDay).equals(clickedDay[i])
							&& currentMonth == calendar.getMonth()
							&& currentYear == calendar.getYear()) {
						clickedDayLabel[i].setOpaque(true);
						clickedDayLabel[i].setBackground(Color.YELLOW);
					}else {
						clickedDayLabel[i].setOpaque(false);
					}
				}
			}

			yText.setText("" + calendar.getYear());
			mText.setText("" + calendar.getMonth());

		} else if (e.getSource() == preBtn) {
			if(CalendarBean.getClickedMonth() <= 1) {
				CalendarBean.setClickedMonth(12);
				CalendarBean.setClickedYear(CalendarBean.getClickedYear()-1);
//				clickedYear = clickedYear-1;
//				clickedMonth = 12;
			}else {
				CalendarBean.setClickedMonth(CalendarBean.getClickedMonth()-1);
			}
			CalendarBean.setClickedDay("1");
			System.out.println("sdfdsf "+ clickedMonth);
			if (month <= 1) {
				month = 12;
				year = year - 1;
				calendar.setYear(year);
				calendar.setMonth(month);
				String clickedDay[] = calendar.getDays();
				for (int i = 0; i < 42; i++) {
					clickedDayLabel[i].setText(clickedDay[i]);
					if(String.valueOf(currentDay).equals(clickedDay[i])
							&& currentMonth == calendar.getMonth()
							&& currentYear == calendar.getYear()) {
						clickedDayLabel[i].setOpaque(true);
						clickedDayLabel[i].setBackground(Color.YELLOW);
					}else {
						clickedDayLabel[i].setOpaque(false);
					}
				}
			} else {
				month = month - 1;
				calendar.setMonth(month);
				calendar.setYear(year);
				String clickedDay[] = calendar.getDays();
				for (int i = 0; i < 42; i++) {
					clickedDayLabel[i].setText(clickedDay[i]);
					if(String.valueOf(currentDay).equals(clickedDay[i])
							&& currentMonth == calendar.getMonth()
							&& currentYear == calendar.getYear()) {
						clickedDayLabel[i].setOpaque(true);
						clickedDayLabel[i].setBackground(Color.YELLOW);
					}else {
						clickedDayLabel[i].setOpaque(false);
					}
				}
			}

			yText.setText("" + calendar.getYear());
			mText.setText("" + calendar.getMonth());

		} else if( e.getSource() == todayBtn) {
			calendar.setYear(currentYear);
			calendar.setMonth(currentMonth);
			calendar.setDay(currentDay);
			CalendarBean.setClickedYear(currentYear);
			CalendarBean.setClickedMonth(currentMonth);
			CalendarBean.setClickedDay(String.valueOf(currentDay));
			String clickedDay[] = calendar.getDays();
			for (int i = 0; i < 42; i++) {
				clickedDayLabel[i].setText(clickedDay[i]);
				if(String.valueOf(currentDay).equals(clickedDay[i])
						&& currentMonth == calendar.getMonth()
						&& currentYear == calendar.getYear()) {
					clickedDayLabel[i].setOpaque(true);
					clickedDayLabel[i].setBackground(Color.YELLOW);
				}else {
					clickedDayLabel[i].setOpaque(false);
				}
			}

			yText.setText("" + calendar.getYear());
			mText.setText("" + calendar.getMonth());
		} else if(e.getSource() == create) {
			System.out.print(123);
			JFrame frame = new JFrame();
			frame.setTitle("Create Event");
			frame.setSize(600, 400);
			frame.setLocation(200, 300);
//			frame.dispose();
			frame.dispatchEvent(new WindowEvent(frame,WindowEvent.WINDOW_CLOSING) );
//			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			EventPanel event = new EventPanel(clickedYear,clickedMonth,Integer.valueOf(clickedDay));
			frame.add(event);
			frame.setVisible(true);
		}else {
			year = Integer.parseInt(yText.getText());
			month = Integer.parseInt(mText.getText());
			if (month < 13 && month > 0 && year > 0) {
				calendar.setYear(year);
				calendar.setMonth(month);
				CalendarBean.setClickedMonth(month);
				CalendarBean.setClickedYear(year);
				CalendarBean.setClickedDay("1");
			}
			String clickedDay[] = calendar.getDays();
			for (int i = 0; i < 42; i++) {
				clickedDayLabel[i].setText(clickedDay[i]);
				if(String.valueOf(currentDay).equals(clickedDay[i])
						&& currentMonth == calendar.getMonth()
						&& currentYear == calendar.getYear()) {
					clickedDayLabel[i].setOpaque(true);
					clickedDayLabel[i].setBackground(Color.YELLOW);
				}else {
					clickedDayLabel[i].setOpaque(false);
				}
			}

			yText.setText("" + calendar.getYear());
			mText.setText("" + calendar.getMonth());

		}
	}

	public static int getCurrentYear() {
		return currentYear;
	}

	public static void setCurrentYear(int currentYear) {
		CalendarPanel.currentYear = currentYear;
	}

	public static int getCurrentMonth() {
		return currentMonth;
	}

	public static void setCurrentMonth(int currentMonth) {
		CalendarPanel.currentMonth = currentMonth;
	}

	public static int getCurrentDay() {
		return currentDay;
	}

	public static void setCurrentDay(int currentDay) {
		CalendarPanel.currentDay = currentDay;
	}

	public static int getClickedYear() {
		return clickedYear;
	}

	public static void setClickedYear(int clickedYear) {
		CalendarPanel.clickedYear = clickedYear;
	}

	public static int getClickedMonth() {
		return clickedMonth;
	}

	public static void setClickedMonth(int clickedMonth) {
		CalendarPanel.clickedMonth = clickedMonth;
	}

	public static String getClickedDay() {
		return clickedDay;
	}

	public static void setClickedDay(String clickedDay) {
		CalendarPanel.clickedDay = clickedDay;
	}
}