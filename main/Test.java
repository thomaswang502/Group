package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Test extends JFrame implements ActionListener {
	JFrame frame;
	JPanel panel;
	JPanel containe;
	JPanel panel2;
	JButton button1 = null;
	JButton button2 = null;
	javax.swing.Timer time = new Timer(80, this);

	Test() {
		time.start();
		frame = new JFrame();
		containe = (JPanel) frame.getContentPane();
		panel = new JPanel();
		panel2 = new JPanel();
		button1 = new JButton("按钮1");
		button2 = new JButton("按钮2");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel2.setBackground(Color.RED);
				panel2.add(button2);
				containe.removeAll();
				containe.add(panel2);
				containe.updateUI();
				System.out.println("red");
			}
		});
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setBackground(Color.BLACK);
				panel.add(button1);
				containe.removeAll();
				containe.add(panel);
				containe.updateUI();// 更改组件UI外观即可
				System.out.println("balck");
			}
		});
		panel.setBackground(Color.BLACK);
		panel2.setBackground(Color.RED);
		panel.add(button1);
		panel2.add(button2);
		containe.add(panel);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
//		new Test();
//		String reg = "^\\d{4}$";
//		String t = "10.5";
		
//		String reg = "^[1-9]|10|11|12$";
//		String t = "12";
		
		
//		String reg = "^(1?[0-9]|2[0-4])$";
//		String t = "0";
		
		String reg = "^S|M|T|W|H|F|A$";
		String t = "s";
		System.out.print(t.matches(reg));
	}

	public void actionPerformed(ActionEvent e) {
	}
}