package LoginPack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import JDBC_Setup.Master;
import POJO.User;
import getPack.GetAdminField;

public class LoginLogics extends Master {

	public static boolean loginValidation(String UN, String PS) throws SQLException {
		List<User> products = new ArrayList<>();
		boolean result = false;
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM " + Table_User + "");

		while (resultSet.next()) {
			String username = resultSet.getString("username");
			String password = resultSet.getString("password");
			String Name = resultSet.getString("Name");
			long Phone = (long) resultSet.getDouble("Phone");
			double Balance =  resultSet.getDouble("Balance");
			User product = new User(username, password, Name, Phone,Balance);
			products.add(product);
		}
		result = (products.stream().filter(row -> row.getUsername().equals(UN) && row.getPassword().equals(PS))
				.count() == 0);
		if (result) {
			// System.out.println("Welcome "+);
		}
		resultSet.close();
		statement.close();

		return result;
	}

	public static boolean loginValidationOR(String UN, String PS) throws SQLException {
		List<User> products = new ArrayList<>();
		boolean result = false;
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM " + Table_User + "");

		while (resultSet.next()) {
			String username = resultSet.getString("username");
			String password = resultSet.getString("password");
			String Name = resultSet.getString("Name");
			long Phone = (long) resultSet.getDouble("Phone");
			double Balance = resultSet.getDouble("Balance");
			User product = new User(username, password, Name, Phone, Balance);
			products.add(product);
		}
		result = (products.stream().filter(row -> row.getUsername().equals(UN) || row.getPassword().equals(PS))
				.count() == 0);

		resultSet.close();
		statement.close();

		return result;
	}

	public static List<User> SignIn(String UN, String PS, String NM, double PH) throws SQLException {
		List<User> products = new ArrayList<>();
		Statement statement = connection.createStatement();

		if (loginValidationOR(UN, PS)) {
			statement.executeLargeUpdate("INSERT INTO users (username ,password,Name,Phone) VALUES('" + UN + "','" + PS
					+ "','" + NM + "'," + PH + ");");
			System.out.println(
					getMSG() + " " + GetAdminField.getUserNameByUserName(UN) + " you Sign in Successfully.......");
			System.out.println("Your UserName = " + UN);
			System.out.println("Your Password = " + PS);
		} else {
			System.out.println("Ur Credentials alredy exist please try again");
		}
		statement.close();

		return products;
	}
}
