import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.*;


public class Reservation extends JFrame {
	JButton[][] seat_btn;
	Container c = getContentPane();
	JLabel seatLabel;
	JLabel nameLabel;
	JLabel passLabel;
	JLabel timeLabel;
	JButton checkButton;
	JPanel optionPanel = new JPanel();
	PersonBean pb= new PersonBean();
	PersonDao pd= new PersonDao();
	int seatNum = 1;
	int count=0; // 좌석 사용수를 체크하는 변수
	
	
	
	private JTextField txtSeat = new JTextField();
	private JTextField txtName = new JTextField();
	private JTextField txtPass = new JTextField();

	
	public Reservation(String title) {
		super(title);
		
		button_Compose();
		option_Compose();
		setEvent();
		super.setSize(600,730);
		setLocation(350,10);
		setVisible(true);
		setResizable(false);
		
	}

	private void clearAll() {
		txtSeat.setText(" ");
		txtName.setText(" ");
		txtPass.setText(" ");
		
	}
	
	private void option_Compose() {
		
		optionPanel.setBounds(0,500, 600, 300);
		optionPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		c.add(optionPanel);
		optionPanel.setLayout(null); //이거 안쓰면 위치 조정안됨...
		
		seatLabel= new JLabel("선택된 좌석:");
		nameLabel= new JLabel("이름:");
		passLabel= new JLabel("비밀번호:");
		checkButton=new JButton("확인");
	
		int vertical_position = 30;
		
		seatLabel.setBounds(20,1*vertical_position,100,10);
		nameLabel.setBounds(20,2*vertical_position,100,10);
		passLabel.setBounds(20,3*vertical_position,100,10);
		
		
		txtSeat.setBounds(200,1*vertical_position,100,20);
		txtName.setBounds(200,2*vertical_position,100,20);
		txtPass.setBounds(200,3*vertical_position,100,20);
		checkButton.setBounds(400,4*vertical_position,90,30);
		
		optionPanel.add(seatLabel);
		optionPanel.add(nameLabel);
		optionPanel.add(passLabel);
		
		optionPanel.add(txtSeat);
		txtSeat.setEnabled(false);
		
		optionPanel.add(txtName);
		optionPanel.add(txtPass);
		
		optionPanel.add(checkButton);
		
		sendAction listener=new sendAction();
		checkButton.addActionListener(listener);
		
	
	}

	private void setEvent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void button_Compose() {
		c.setLayout(null);
	
		
		seat_btn=new JButton[6][6];
		
		for(int i=0; i<seat_btn.length; i++) {
			for(int j=0; j<seat_btn[i].length; j++) {
			seat_btn[i][j]= new JButton();
			seat_btn[i][j].setText(seatNum+"");
			seat_btn[i][j].setBounds(40 + (i * 90), 100 + (j * 58), 60, 60);
			
			c.add(seat_btn[i][j]);
			
			
			setAction listener = new setAction();
			seat_btn[i][j].addActionListener(listener);
			
			int cnt=pd.searchPerson(seatNum);
//			System.out.println("seatNum:"+seatNum);
//			System.out.println("cnt:"+cnt);
			
			if(cnt==0){
				seat_btn[i][j].setBackground(Color.green);
			}
			else {
				seat_btn[i][j].setBackground(Color.red);
				count++;
			}
			seatNum+=1;
			
			}
		}
		JLabel seatCount= new JLabel("사용중인 좌석 수 :"+count);
		seatCount.setBounds(5, 10, 150, 80);
		c.add(seatCount);
		
	}
	
	class sendAction implements ActionListener{
		public void actionPerformed(ActionEvent e) { 
			
			int cnt2=0;
		
		LocalTime now = LocalTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		
		int seatNum=Integer.parseInt(txtSeat.getText());
		String name=txtName.getText();
		String pass= txtPass.getText();
		     
		String time = now.format(formatter);
	
		int cnt=pd.searchPerson(seatNum);
		
			if(cnt==0){

			cnt2=pd.insertPerson(seatNum,name,pass,time);
			
			}
			else {
				JOptionPane aa=new JOptionPane();
				JOptionPane.showMessageDialog(aa, "이미 사용 중인 좌석입니다","에러 발생",JOptionPane.YES_NO_CANCEL_OPTION);
				
			}
			clearAll();
			System.out.println(cnt2);
			
			c.repaint();
		}
		
	}

	class setAction implements ActionListener{
		public void actionPerformed(ActionEvent e) { 
		String seatNum=e.getActionCommand();
		
		txtSeat.setText(seatNum);	
		
	}
		
	
	
	}

	public static void main(String[] args) {
		new Reservation("스터디카페");

	}

}
