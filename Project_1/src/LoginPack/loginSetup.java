package LoginPack;

import java.sql.SQLException;

import Display.AdminClass;
import Display.GuestClass;
import Display.UserClass;
import JDBC_Setup.Master;
import getPack.GetAdminField;

public class loginSetup extends Master {

	public static boolean LoginSignIN() throws SQLException {
		int index = 1;
		boolean b = false;
		System.out.println((index++) + " > LogIn");
		System.out.println((index++) + " > SignIn");
		System.out.println((index++) + " > Guest");
		int UserInput = sc.nextInt();
		if (UserInput == 1) {
			System.out.println("Enter  UserName");
			String UserName = sc.next();
			System.out.println("Enter Password ");
			String Passowrd = sc.next();
			
			boolean flag = LoginLogics.loginValidation(UserName, Passowrd);
			if (UserName.equals("Admin") && Passowrd.equals("Admin")) {
				System.out.println("Hello Admin " + getMSG());
				AdminClass.admin();
				b = true;
				admin =true;
			}
			else if (!(UserName.equals("Admin") && Passowrd.equals("Admin")) && !flag) {
				System.out.println("Logic for user ");
				System.out.println(getMSG() + " " + GetAdminField.getUserNameByUserName(UserName)+ " Your Login Succesfully ......");
				UserClass.user();
				b = true;
				admin =false;
			} else {
				System.out.println("Login Fail ......");
				b = false;
				admin =false;
			}
		}

		if (UserInput == 2) {
			System.out.println("Enter Unique UserName");
			String UserName = sc.next();
			System.out.println("Enter Unique Password ");
			String Passowrd = sc.next();
			System.out.println("Enter Your Name ");
			String Name = sc.next();
			System.out.println("Enter  PhoneNumber");
			long Phone = sc.nextLong();
			LoginLogics.SignIn(UserName, Passowrd, Name, Phone);
			b = true;
		}
		if (UserInput == 3) {
			GuestClass.guest();
			b = true;
			guest =true;
		}
		else {
			System.out.println("Select Valid Inpute");
		}
		return b;
	}

}
