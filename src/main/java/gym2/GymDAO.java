package gym2;
import java.sql.*;

public class GymDAO {
	String url = "jdbc:mysql://localhost:3300/gym2";
	//connection
	Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, "root", "");
	}
	//validate login
	public String validateUser(String uname, String upassword) {
		String query = "SELECT role FROM user WHERE uname=? AND upassword=?";
		try {
			Connection con=getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, uname);
			pstmt.setString(2, upassword);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString("role");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//registeration
	public boolean registerAdmin(int id, String name, String fname, String phone, int weight, int height) {
		String insertString = "INSERT INTO admin(id, name, fname, phone, weight, height) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			Connection con=getConnection();
			PreparedStatement pstmt = con.prepareStatement(insertString);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, fname);
			pstmt.setString(4, phone);
			pstmt.setInt(5, weight);
			pstmt.setInt(6, height);
			return pstmt.executeUpdate()>0;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//update
	public boolean updateAdmin(int id, String name, String fname, String phone, int weight, int height) {
		String updateString = "UPDATE admin SET name=?, fname=?, phone=?, weight=?, height=? where id=?";
		try {
			Connection con=getConnection();
			PreparedStatement pstmt = con.prepareStatement(updateString);
			pstmt.setString(1, name);
			pstmt.setString(2, fname);
			pstmt.setString(3, phone);
			pstmt.setInt(4, weight);
			pstmt.setInt(5, height);
			pstmt.setInt(6, id);
			return pstmt.executeUpdate()>0;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//delete
	public boolean deleteAdmin(int id) {
		String deleteString = "DELETE FROM admin WHERE id=?";
		try {
			Connection con=getConnection();
			PreparedStatement pstmt= con.prepareStatement(deleteString);
			pstmt.setInt(1, id);
			return pstmt.executeUpdate()>0;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//view
	public members getAdminDetails(int id) {
		String query="select * from admin where id=?";
		members member=null;
		try {
			Connection con= getConnection();
			PreparedStatement pstmt= con.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
		    member=new members();
			member.setid(rs.getInt("id"));
			member.setname(rs.getString("name"));
			member.setfname(rs.getString("fname"));
			member.setphone(rs.getString("phone"));
			member.setweight(rs.getInt("weight"));
			member.setheight(rs.getInt("height"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return member;
	}
}
