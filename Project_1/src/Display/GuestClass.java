package Display;

import java.sql.SQLException;

import JDBC_Setup.Master;
import LoginPack.LoginLogics;
import Trasnsaction.PurchaseProduct;
import getPack.GetUserField;

public class GuestClass extends Master {

	public static void guest() throws SQLException {

		int val = 1;
		System.out.println((val++) + " Display all products ");
		System.out.println((val++) + " Purchase Product ");
		switch (sc.nextInt()) {
		case 1: {
			GetUserField.displayProductList();
		}
			break;
		case 2:{
			System.out.println("You Need TO sign in First Befor Purchase ");
			signin();
			PurchaseProduct.buyProducts();}
			break;

		default:
			System.out.println("Enter Valid Input");
			break;
		}

	}
	public static void signin() throws SQLException {
		System.out.println("Enter Unique UserName");
		String UserName = sc.next();
		System.out.println("Enter Unique Password ");
		String Passowrd = sc.next();
		System.out.println("Enter Your Name ");
		String Name = sc.next();
		System.out.println("Enter  PhoneNumber");
		long Phone = sc.nextLong();
		LoginLogics.SignIn(UserName, Passowrd, Name, Phone);
	}
}
