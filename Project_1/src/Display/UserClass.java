package Display;

import java.sql.SQLException;

import JDBC_Setup.Master;
import Trasnsaction.PurchaseProduct;
import getPack.GetUserField;

public class UserClass extends Master {

	public static void user() throws SQLException {
		
		int val = 1;
		System.out.println((val++) + " Display all products ");
		System.out.println((val++) + " Purchase Product ");
		switch (sc.nextInt()) {
		case 1: {
			GetUserField.displayProductList();
		}
			break;
		case 2:
			PurchaseProduct.buyProducts();
			break;

		default:
			System.out.println("Enter Valid Input");
			break;
		}

	}

}
