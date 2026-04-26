
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;

public class Main extends JApplet implements MouseListener {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @author Satyam Sharan
	 */
	private JButton clearButton = new JButton("Clear Connection");
	private JButton connectButton = new JButton();
	private JButton connectMButton = new JButton();
	private ImageIcon phase = new ImageIcon("phase.png");
	private ImageIcon sm = new ImageIcon("sm.png");
	private JLabel phaseLabel = new JLabel(phase);
	private JLabel smLabel = new JLabel(sm);
	private JRadioButton r1 = new JRadioButton();
	private JRadioButton r2 = new JRadioButton();
	private JRadioButton r3 = new JRadioButton();
	private JRadioButton r4 = new JRadioButton();
	private JRadioButton r5 = new JRadioButton();
	private JRadioButton r6 = new JRadioButton();
	private JPanel rPanel = new JPanel();
	private JPanel mainPanel = new JPanel();
	private int flag = 0;
	private JLabel message = new JLabel();
	private JLabel result = new JLabel();
	private JLabel resultText = new JLabel();
	private Node node = new Node();
	private boolean connected = false;
	private boolean connectedM = false;
	private int imgCount = 0;

	private ImageIcon[] imgA = { new ImageIcon("a2.png"),
			new ImageIcon("a3.png"), new ImageIcon("a4.png"),
			new ImageIcon("a1.png") };

	private ImageIcon[] imgC = { new ImageIcon("c2.png"),
			new ImageIcon("c3.png"), new ImageIcon("c4.png"),
			new ImageIcon("c1.png") };

	Timer ta = new Timer(250, new ActionListener() {
		
		public void actionPerformed(ActionEvent arg0) {
			if (imgCount == 4)
				imgCount = 0;
			result.setIcon(imgA[imgCount++]);
		}
	});

	private void runA() {
		resultText.setText("Rotation: Anticlockwise");
		result.setIcon(imgA[3]);
		ta.start();
	}

	private void stopA() {
		resultText.setText("");
		result.setIcon(null);
		ta.stop();

	}

	Timer tc = new Timer(250, new ActionListener() {
		
		public void actionPerformed(ActionEvent arg0) {
			if (imgCount == 4)
				imgCount = 0;
			result.setIcon(imgC[imgCount++]);
		}
	});

	private void runC() {
		resultText.setText("Rotation: Clockwise");
		result.setIcon(imgC[3]);
		tc.start();
	}

	private void stopC() {
		resultText.setText("");
		result.setIcon(null);
		tc.stop();

	}

	
	public void init() {

		setSize(840, 390);
		setLayout(null);
		//setBackground(Color.white);
		mainPanel.setBounds(60, 0, 720, 390);
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.white);

		message.setBounds(100, 260, 400, 30);
		message.setForeground(Color.red);
		message.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		clearButton.setBounds(90, 300, 200, 30);
		connectButton.setBounds(0, 0, 60, 250);
		connectMButton.setBounds(780, 71, 60, 66);
		phaseLabel.setBounds(0, 0, 140, 250);
		smLabel.setBounds(220, 0, 500, 250);

		result.setBounds(450, 250, 100, 100);
		resultText.setBounds(420, 350, 200, 30);

		r1.setBounds(0, 32, 20, 20);
		r4.setBounds(60, 32, 20, 20);

		r2.setBounds(0, 118, 20, 20);
		r5.setBounds(60, 118, 20, 20);

		r3.setBounds(0, 200, 20, 20);
		r6.setBounds(60, 200, 20, 20);
		connectButton.setIcon(new ImageIcon("os.png"));
		connectMButton.setIcon(new ImageIcon("om.png"));

		rPanel.add(r1);
		rPanel.add(r2);
		rPanel.add(r3);
		rPanel.add(r4);
		rPanel.add(r5);
		rPanel.add(r6);
		rPanel.setLayout(null);
		rPanel.setBounds(140, 0, 80, 250);
		rPanel.setBackground(Color.white);
		mainPanel.add(phaseLabel);
		mainPanel.add(rPanel);
		mainPanel.add(smLabel);
		mainPanel.add(clearButton);
		mainPanel.add(message);
		mainPanel.add(result);
		mainPanel.add(resultText);

		r1.addMouseListener(this);
		r2.addMouseListener(this);
		r3.addMouseListener(this);
		r4.addMouseListener(this);
		r5.addMouseListener(this);
		r6.addMouseListener(this);

		r1.setSelected(true);
		r2.setSelected(true);
		r3.setSelected(true);
		r4.setSelected(true);
		r5.setSelected(true);
		r6.setSelected(true);

		add(mainPanel);
		add(connectButton);
		add(connectMButton);

		connectMButton.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				if (connectedM) {
					connectMButton.setIcon(new ImageIcon("om.png"));
					connectedM = false;
					stopA();
				} else {
					connectMButton.setIcon(new ImageIcon("cm.png"));
					connectedM = true;
					if (connected) {
						connectButton.setIcon(new ImageIcon("os.png"));
						connected = false;
						stopC();
					}
					runA();
				}

			}
		});

		connectButton.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				if (connected) {
					connectButton.setIcon(new ImageIcon("os.png"));
					connected = false;
					stopA();
					stopC();
				} else {
					connectButton.setIcon(new ImageIcon("cs.png"));
					connected = true;
					message.setText("");
					if (connectedM) {
						connectMButton.setIcon(new ImageIcon("om.png"));
						connectedM = false;
						stopA();
					}
					checkRotation();
				}

			}
		});
		clearButton.addMouseListener(new MouseListener() {

			
			public void mouseReleased(MouseEvent arg0) {

			}

			
			public void mousePressed(MouseEvent arg0) {


			}

			
			public void mouseExited(MouseEvent arg0) {


			}

			
			public void mouseEntered(MouseEvent arg0) {


			}

			
			public void mouseClicked(MouseEvent arg0) {
				clear();
				connected = false;
				connectButton.setIcon(new ImageIcon("os.png"));
				message.setText("Cleared!");
				stopA();
				stopC();
			}
		});
	}

	private boolean checkFree(int n) {
		if (node.n[n - 1] != 0) {
			flag = 0;
			message.setText("Node allready connected");
			return false;
		}
		return true;
	}

	private void clear() {
		message.setText("");
		rPanel.getGraphics().clearRect(20, 20, 40, 200);
		node.clear();
	}

	private void checkCon(int oldFlag, int newFlag) {
		flag = 0;
		if (oldFlag <= 3 && newFlag > 3) {
			char c = 'G';
			switch (newFlag) {
			case 4:
				c = 'R';
				break;
			case 5:
				c = 'Y';
				break;
			case 6:
				c = 'B';
				break;
			default:
				break;
			}

			message.setText(oldFlag + " - " + c + " Connected");
			connect(oldFlag, newFlag);
		} else if (oldFlag > 3 && newFlag <= 3) {
			char c = 'G';
			switch (oldFlag) {
			case 4:
				c = 'R';
				break;
			case 5:
				c = 'Y';
				break;
			case 6:
				c = 'B';
				break;
			default:
				break;
			}

			message.setText(newFlag + " - " + c + " Connected");
			connect(oldFlag, newFlag);
		} else {
			clear();
			message.setText("Error in Connection");
		}
	}

	private void connect(int n1, int n2) {
		int y1, y2, x1, x2;

		switch (n1) {
		case 1:
			y1 = 42;
			x1 = 20;
			break;
		case 2:
			y1 = 128;
			x1 = 20;
			break;
		case 3:
			y1 = 210;
			x1 = 20;
			break;
		case 4:
			y1 = 42;
			x1 = 60;
			break;
		case 5:
			y1 = 128;
			x1 = 60;
			break;
		case 6:
			y1 = 210;
			x1 = 60;
			break;
		default:
			y1 = 0;
			x1 = 0;
			break;
		}

		switch (n2) {
		case 1:
			y2 = 42;
			x2 = 20;
			break;
		case 2:
			y2 = 128;
			x2 = 20;
			break;
		case 3:
			y2 = 210;
			x2 = 20;
			break;
		case 4:
			y2 = 42;
			x2 = 60;
			break;
		case 5:
			y2 = 128;
			x2 = 60;
			break;
		case 6:
			y2 = 210;
			x2 = 60;
			break;
		default:
			y2 = 0;
			x2 = 0;
			break;
		}
		node.n[n1 - 1] = n2;
		node.n[n2 - 1] = n1;
		rPanel.getGraphics().drawLine(x1, y1, x2, y2);

	}

	
	public void mouseClicked(MouseEvent arg0) {
		JRadioButton r = (JRadioButton) arg0.getSource();
		r.setSelected(true);

		if (r == r1) {
			if (flag == 0) {
				if (checkFree(1)) {
					flag = 1;
					message.setText("Selected '1'");
				}
			} else {
				if (checkFree(1)) {
					checkCon(flag, 1);
				}
			}
		} else if (r == r2) {
			if (flag == 0) {
				if (checkFree(2)) {
					flag = 2;
					message.setText("Selected '2'");
				}
			} else {
				if (checkFree(2)) {
					checkCon(flag, 2);
				}
			}
		} else if (r == r3) {
			if (flag == 0) {
				if (checkFree(3)) {
					flag = 3;
					message.setText("Selected '3'");
				}
			} else {
				if (checkFree(3)) {
					checkCon(flag, 3);
				}
			}
		} else if (r == r4) {
			if (flag == 0) {
				if (checkFree(4)) {
					flag = 4;
					message.setText("Selected 'R'");
				}
			} else {
				if (checkFree(4)) {
					checkCon(flag, 4);
				}
			}
		} else if (r == r5) {
			if (flag == 0) {
				if (checkFree(5)) {
					flag = 5;
					message.setText("Selected 'Y'");
				}
			} else {
				if (checkFree(5)) {
					checkCon(flag, 5);
				}
			}
		} else if (r == r6) {
			if (flag == 0) {
				if (checkFree(6)) {
					flag = 6;
					message.setText("Selected 'B'");
				}
			} else {
				if (checkFree(6)) {
					checkCon(flag, 6);
				}
			}
		}

		if (connected) {
			stopC();
			stopA();
			connected = false;
			connectButton.setIcon(new ImageIcon("os.png"));
			message.setText("Always work on open circuit! " + message.getText());
		}

	}

	private void checkRotation() {
		if (node.check()) {
			int conCase = 0;
			message.setText("");

			if (node.n[0] == 4) {
				if (node.n[1] == 5) {
					if (node.n[2] == 6) {
						conCase = 1;
					}
				} else if (node.n[1] == 6) {
					if (node.n[2] == 5) {
						conCase = 2;
					}
				}

			} else if (node.n[0] == 5) {
				if (node.n[1] == 4) {
					if (node.n[2] == 6) {
						conCase = 3;
					}
				} else if (node.n[1] == 6) {
					if (node.n[2] == 4) {
						conCase = 4;
					}
				}
			} else if (node.n[0] == 6) {
				if (node.n[1] == 5) {
					if (node.n[2] == 4) {
						conCase = 5;
					}
				} else if (node.n[1] == 4) {
					if (node.n[2] == 5) {
						conCase = 6;
					}
				}
			}

			switch (conCase) {
			case 1:
				runC();
				break;
			case 2:
				runA();
				break;
			case 3:
				runA();
				break;
			case 4:
				runC();
				break;
			case 5:
				runA();
				break;
			case 6:
				runC();
				break;
			default:
				message.setText("Unexpected Error!");
				break;
			}
		} else {
			message.setText("Insufficient Connections!");
		}

	}

	
	public void mouseEntered(MouseEvent arg0) {


	}

	
	public void mouseExited(MouseEvent arg0) {


	}

	
	public void mousePressed(MouseEvent arg0) {


	}

	
	public void mouseReleased(MouseEvent arg0) {


	}
}

class Node {
	int n[] = { 0, 0, 0, 0, 0, 0 };

	public void clear() {
		for (int i = 0; i < 6; i++) {
			n[i] = 0;
		}

	}

	boolean check() {
		for (int i = 0; i < 6; i++) {
			if (n[i] == 0)
				return false;
		}
		return true;
	}

}


