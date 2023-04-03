import javax.swing.*;
import javax.swing.plaf.OptionPaneUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Check extends JFrame implements ActionListener{
	Container c = getContentPane();
	JButton checkButton;
	JLabel priceLabel;
	JLabel priceLabel2;
	JLabel priceLabel3;
	JButton exitButton;
	JPanel optionPanel= new JPanel();
	JPanel explainPanel= new JPanel();
	PersonBean pb= new PersonBean();
	
	private JTextField txtName = new JTextField();
	private JTextField txtPass = new JTextField();
	
	PersonDao pd= new PersonDao();
	
	public Check(String title) {
		super(title);
		super.setSize(600,380);
		setLocation(350,10);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		option_compose();
		explain_compose();
		c.setLayout(null);
		checkButton.addActionListener(this);
	}

	private void explain_compose() {
		explainPanel.setLayout(null);
		explainPanel.setBounds(0,200,600,180);
		
		priceLabel = new JLabel("좌석:");
		priceLabel2= new JLabel("사용시간:");
		priceLabel3= new JLabel("총 가격:");
		
		priceLabel.setBounds(20, 10,500, 30);
		priceLabel2.setBounds(20, 30,500, 30);
		priceLabel3.setBounds(20, 50,500, 30);
	
		explainPanel.add(priceLabel);
		explainPanel.add(priceLabel2);
		explainPanel.add(priceLabel3);
		explainPanel.add(exitButton);
		c.add(explainPanel);
		
	}

	private void option_compose() {
		
		
		optionPanel.setLayout(null);
		
		optionPanel.setBounds(0,0, 600, 200);
		optionPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		JLabel nameLabel= new JLabel("이름:");
		JLabel passLabel= new JLabel("비밀번호:");
		checkButton=new JButton("확인");
		
		
		nameLabel.setBounds(20,60,100,10);
		passLabel.setBounds(20,90,100,10);
		
		txtName.setBounds(200,60,100,20);
		txtPass.setBounds(200,90,100,20);
		checkButton.setBounds(400,120,90,30);
		
		c.add(optionPanel);
		optionPanel.add(nameLabel);
		optionPanel.add(passLabel);
		
		
		optionPanel.add(txtName);
		optionPanel.add(txtPass);
		
		optionPanel.add(checkButton);
		
		exitButton = new JButton("퇴실하기");
		exitButton.setBounds(400, 100, 100, 30);
		
		exitButton.addActionListener(this);
		
	}
	
	public void clearAll() {
		txtName.setText(" ");
		txtPass.setText(" ");
		priceLabel.setText(" ");
		priceLabel2.setText(" ");
		priceLabel3.setText(" ");
		exitButton.setVisible(false);
		
	}
	
	
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();
		
		if(obj==checkButton) {
			searchPerson();
		}
		if(obj==exitButton) {
			deletePerson();
		}
		
	}
	

	private void deletePerson() {
		System.out.println("퇴실 버튼 눌림");
		
		int cnt=pd.deletePerson(pb.getSeat());
		clearAll();
		if(cnt!=-1) {
			System.out.println("삭제완료");
			priceLabel.setText("퇴실 되었습니다 안녕히 가세요");
			
		}
		else {
			priceLabel.setText("퇴실 실패");
		}
		
	}

	private void searchPerson() {
		System.out.println("체크 버트 눌림");
		exitButton.setVisible(true);
		String name=txtName.getText();
		String pass= txtPass.getText();
		
		ArrayList<PersonBean> lists=pd.searchPerson(name,pass);
		
		if(lists.size()==0) {
			priceLabel.setText("찾으시는 정보가 없습니다");
		}
		else {
			for(int i=0; i<lists.size(); i++) {
				pb=lists.get(i);
			
			}
			
			long minute =calcu(pb.getTime());
			long price= minute *100;
			
			priceLabel.setText(pb.getName()+" 고객님께서 선택하신 좌석은 "+pb.getSeat()+" 번 입니다");
			priceLabel2.setText("입장 시간은 "+pb.getTime()+" 으로 사용 시간은 "+ minute+" 입니다");
			priceLabel3.setText("총 가격: "+price+" 입니다");
			
		}
		

	}

	private long calcu(String reqDateStr) {
		long minute = 0;
		
		Date curDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		//요청시간을 Date로 parsing 후 time가져오기
		Date reqDate;
		try {
			reqDate = dateFormat.parse(reqDateStr);
			long reqDateTime = reqDate.getTime();
			//현재시간을 요청시간의 형태로 format 후 time 가져오기
			curDate = dateFormat.parse(dateFormat.format(curDate));
			long curDateTime = curDate.getTime();
			//분으로 표현
			 minute = (curDateTime - reqDateTime) / 60000;
			System.out.println("요청시간 : " + reqDate);
			System.out.println("현재시간 : " + curDate);
			System.out.println(minute+"분 차이");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return minute;
	}

	public static void main(String[] args) {
		new Check("스터디 카페");

	}

}
