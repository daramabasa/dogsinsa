package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DogDAO;
import vo.Dog;

public class DogViewService {
	
	public Dog getDogView(int id) {
		DogDAO dogDAO = DogDAO.getInstance();
		Connection con = getConnection();
		dogDAO.setConnection(con);
		int updateCount = dogDAO.UpdateReadCount(id);
		
		if(updateCount > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		Dog dog = dogDAO.selectDog(id);
		close(con);
		return dog;
	}
}
