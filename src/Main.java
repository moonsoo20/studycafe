import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
	Container c = getContentPane();
	JButton reserv;
	JButton check;
	JButton update;
	
	public Main(String title) {
		super(title);
		super.setSize(500,680);
		setLocation(350,10);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		compose();
		
		reserv.addActionListener(this);
		check.addActionListener(this);
		update.addActionListener(this);
		
		 MyPanel panel1 = new MyPanel();
		 c.add(panel1);
		 panel1.setBounds(20, 70, 450, 430);
		 JLabel hell= new JLabel("스터디카페에 오신걸 환영합니다");
		 c.add(hell);
		 hell.setBounds(150, 0, 500, 70);
	}
	
	class MyPanel extends JPanel {
		ImageIcon Main_img= new ImageIcon("main.jpeg");
		Image img= Main_img.getImage();
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
		
	}
	
	public void compose(){
		c.setLayout(null);
		reserv= new JButton("예약");
		check= new JButton("퇴실");
		update= new JButton("변경");
		
		
		reserv.setBounds(190,550, 90, 40);
		check.setBounds(350,550,90,40);
		update.setBounds(30, 550, 90, 40);
		
		c.add(check);
		c.add(reserv);
		c.add(update);
		
	}

	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();
		System.out.println(obj);
		if(obj == check) {
			System.out.println("체크");
			new Check("스터디 카페");
		}
		else if(obj == reserv) {
			System.out.println("예약");
			new Reservation("스터디 카페");
		}
		else if(obj==update) {
			System.out.println("변경");
			new Update("스터디 카페");
		}
}

	public static void main(String[] args) {
		new Main("스터디 카페");

	}


}
