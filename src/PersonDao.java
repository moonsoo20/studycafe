import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonDao {
	
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "jspid";
	String pw = "1234";
	Connection conn=null;

	public PersonDao() {
		try {
			Class.forName(driver);
			System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로드 실패");
		}
	}

	public void getConnection() {
		try {
			
			conn=DriverManager.getConnection(url,id,pw);
			System.out.println("접속 성공");
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속 실패");
		}

	}

	public int insertPerson(int seatNum, String name, String pass, String formatedNow) {
		
		getConnection();
		PreparedStatement ps = null;
		int cnt=-1;
		
		String sql = "insert into person values(?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1,seatNum);
			ps.setString(2, name);
			ps.setString(3,pass);
			ps.setString(4,formatedNow);
			cnt = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null)
					conn.close();
				if(ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;

	}
	
	public int searchPerson(int seatNum) {
		getConnection();
		PreparedStatement ps = null;
		int cnt=-1;
		
		String sql="select * from person where seatnum=?";
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1,seatNum);
			
			cnt= ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null)
					conn.close();
				if(ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	return cnt;
		
	}

	public ArrayList<PersonBean> searchPerson(String name, String pass) {
		int cnt=-1;
		getConnection();
		ResultSet rs=null;
		PreparedStatement ps=null;

		ArrayList<PersonBean> lists= new ArrayList<PersonBean>();

		String sql="select * from person where name=? and pass=?";

		try {
			ps=conn.prepareStatement(sql);
			
			ps.setString(1, name);
			ps.setString(2, pass);
			
			 cnt=ps.executeUpdate();
	
			rs=ps.executeQuery();
			
			while(rs.next()) {
			PersonBean pb= new PersonBean();
			pb.setSeat(rs.getInt("seatNum"));
			pb.setName(rs.getString("name"));
			pb.setPass(rs.getString("pass"));
			pb.setTime(rs.getString("time"));
			lists.add(pb);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(ps != null) {
					ps.close();
				}
				if(rs != null) { 
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lists;
		
	}

	public int deletePerson(int seat) {
		int cnt=-1;
		getConnection();
		PreparedStatement ps=null;


		String sql="delete person where seatnum=?";

		try {
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, seat);
			
			cnt=ps.executeUpdate();
	

		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(ps != null) {
					ps.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

	public int UpdatePerson(int seat, int newSeat) {
	 
		int cnt=-1;
		getConnection();
		PreparedStatement ps=null;
		


		String sql="update person set seatNum=? where seatnum=?";

		try {
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, newSeat);
			ps.setInt(2, seat);
			
			cnt=ps.executeUpdate();
	

		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(ps != null) {
					ps.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
		
}



