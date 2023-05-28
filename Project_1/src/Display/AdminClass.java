package Display;

import java.sql.SQLException;

import JDBC_Setup.Master;
import getPack.GetAdminField;
import getPack.GetUserField;

public class AdminClass extends Master {

	public static void admin() throws SQLException {

		int val = 1;
		System.out.println((val++) + " Display User List ");
		System.out.println((val++) + " Display all products ");
		System.out.println((val++) + " Remove Product From DB ");
		System.out.println((val++) + " Remove User From DB ");
		System.out.println((val++) + " Add Product in DB ");
		System.out.println((val++) + " Update User Balance ");

		switch (sc.nextInt()) {
		case 1: {
			GetAdminField.displayUserList();
		}
			break;
		case 2:
			GetUserField.displayProductList();
			break;
		case 3: {
			System.out.println("Enter ID of Product ");
			GetAdminField.removeProductFromDB(sc.nextInt());
		}
			break;
		case 4: {
			System.out.println("Enter UserName of User ");
			GetAdminField.removeUserFromDB(sc.next());
		}
			break;
		case 5:
			GetAdminField.addProductToDB();
			break;
		case 6:
			GetAdminField.updateBalance();
			break;

		default:
			System.out.println("Enter Valid Input");
			break;
		}
	}

}
