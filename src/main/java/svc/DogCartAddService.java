package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.DogDAO;
import vo.Cart;
import vo.Dog;

public class DogCartAddService {
	
	public Dog getCartDog(int id) {
		DogDAO dogDAO = DogDAO.getInstance();
		Connection con = getConnection();
		dogDAO.setConnection(con);
	
		Dog dog = dogDAO.selectDog(id);
		close(con);
		return dog;
	}
	
	public void addCart(HttpServletRequest request, Dog cartDog) {
		HttpSession session = request.getSession();
		ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");
		
		if(cartList == null) {
			cartList = new ArrayList<Cart>();
			session.setAttribute("cartList", cartList);
		}
		
		boolean isNewCart = true;
		
		for (int i = 0; i < cartList.size(); i++) {
			if(cartDog.getName().equals(cartList.get(i).getName())) {
				isNewCart = false;
				cartList.get(i).setQty(cartList.get(i).getQty() + 1);
				break;
			}
		}
		
		if(isNewCart) {
			Cart cart = new Cart();
			cart.setImage(cartDog.getImage());
			cart.setName(cartDog.getName());
			cart.setPrice(cartDog.getPrice());
			cart.setQty(1);
			cartList.add(cart);
		}
	}
}
