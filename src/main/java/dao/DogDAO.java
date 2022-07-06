package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static db.JdbcUtil.*;

import vo.Dog;

public class DogDAO {
	Connection con;
	private static DogDAO boardDAO;
	
	private DogDAO() {}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public static DogDAO getInstance() {
		if(boardDAO == null) {
			boardDAO = new DogDAO();
		}
		
		return boardDAO;
	}
	
	public ArrayList<Dog> selectDogList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Dog> dogList = null;
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM cloth");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dogList = new ArrayList<Dog>();
				
				do {
					dogList.add(new Dog (
						rs.getInt("id"),
						rs.getString("name"),
						rs.getInt("price"),
						rs.getString("image"),
						rs.getString("c_size"),
						rs.getString("content"),
						rs.getInt("readcount")
					));
				} while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return dogList;
	}
	
	public Dog selectDog(int id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Dog dog = null;
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM cloth WHERE id=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dog = new Dog (
						rs.getInt("id"),
						rs.getString("name"),
						rs.getInt("price"),
						rs.getString("image"),
						rs.getString("c_size"),
						rs.getString("content"),
						rs.getInt("readcount")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return dog;
	}
	
	public int UpdateReadCount(int id) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "";
		
		try {
			sql = "UPDATE cloth SET readcount = readcount + 1 WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}
	
	public int insertDog(Dog dog) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		int insertCount = 0;
		String sql = "";
		String sql2 = "SELECT COUNT(*) FROM cloth";
		
		try {
			sql = "INSERT INTO cloth VALUES(?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt2 = con.prepareStatement(sql2);
			rs = pstmt2.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			
			pstmt.setInt(1, count+1);
			pstmt.setString(2, dog.getName());
			pstmt.setInt(3, dog.getPrice());
			pstmt.setString(4, dog.getImage());
			pstmt.setString(5, dog.getC_size());
			pstmt.setString(6, dog.getContent());
			pstmt.setInt(7, dog.getReadcount());
			
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(pstmt2);
			close(rs);
		}
		
		return insertCount;
	}
}
