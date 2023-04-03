import javax.swing.*;
import javax.swing.plaf.OptionPaneUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Update extends JFrame implements ActionListener{
	Container c = getContentPane();
	JButton checkButton;
	JLabel priceLabel;
	JLabel priceLabel2;
	
	JButton updateButton;
	JPanel optionPanel= new JPanel();
	JPanel explainPanel= new JPanel();
	PersonBean pb= new PersonBean();
	
	private JTextField txtName = new JTextField();
	private JTextField txtPass = new JTextField();
	private JTextField txtSeat= new JTextField();
	
	PersonDao pd= new PersonDao();
	
	public Update(String title) {
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
		
		priceLabel = new JLabel();
		priceLabel2= new JLabel();
		
		priceLabel.setBounds(20, 10,500, 30);
		priceLabel2.setBounds(20, 30,500, 30);
		txtSeat.setBounds(200,36,70,20);
	
	
		explainPanel.add(priceLabel);
		explainPanel.add(priceLabel2);
		explainPanel.add(updateButton);
		updateButton.setVisible(false);
		
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
		
		
		updateButton = new JButton("변경하기");
		updateButton.setBounds(400, 100, 100, 30);

		updateButton.addActionListener(this);
		
	}
	
	public void clearAll() {
		txtName.setText(" ");
		txtPass.setText(" ");
		priceLabel.setText(" ");
		priceLabel2.setText(" ");
		txtSeat.setVisible(false);
		updateButton.setVisible(false);

		
	}
	
	
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();
		
		if(obj==checkButton) {
			searchPerson();
		}
		if(obj==updateButton) {
			UpdatePerson();
		}
		
	}
	

	private void UpdatePerson() {
		System.out.println("변경 버튼 눌림");
		
		int newSeat=Integer.parseInt(txtSeat.getText());
		
		int cnt=pd.searchPerson(newSeat);
		
		if(cnt==0) {
		
		int cnt2=pd.UpdatePerson(pb.getSeat(),newSeat);
		clearAll();
		if(cnt2!=-1) {
			System.out.println("변경완료");
			priceLabel.setText("변경 되었습니다 안녕히 가세요");
			
		}
		else {
			priceLabel.setText("변경 실패");
		}
		
		}
		else {
			JOptionPane aa=new JOptionPane();
			JOptionPane.showMessageDialog(aa, "이미 사용 중인 좌석입니다","에러 발생",JOptionPane.YES_NO_CANCEL_OPTION);
			
		}
		
	}

	private void searchPerson() {
		System.out.println("체크 버트 눌림");
		updateButton.setVisible(true);
		
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
			
			priceLabel.setText(pb.getName()+" 고객님께서 선택하신 좌석은 "+pb.getSeat()+" 번 입니다");
			priceLabel2.setText("변경하시려는 좌석을 적으세요");
			explainPanel.add(txtSeat);
			
		}
		

	}


	public static void main(String[] args) {
		new Update("스터디 카페");

	}

}

