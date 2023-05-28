package getPack;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import JDBC_Setup.Master;
import POJO.User;

public class GetAdminField extends Master {

	public static List<User> getUserList() {
		List<User> products = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM " + Table_User + "");

			while (resultSet.next()) {
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				long Phone = (long) resultSet.getDouble("Phone");
				double Balance = resultSet.getDouble("Balance");
				String Name = resultSet.getString("Name");

				User product = new User(username, password, Name, Phone, Balance);
				products.add(product);
			}

			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return products;
	}

	public static String displayUserList() throws SQLException {
		System.out.println("///////////////////////// Product List ///////////////////////////");
		for (User user : getUserList()) {
			System.out.print(" || UserName : " + user.getUsername());
			System.out.print(" || Password : " + user.getPassword());
			System.out.print(" || Name : " + user.getName());
			System.out.print(" || PhoneNumber : " + user.getPhone());
			System.out.println(" || Balance : " + user.getBalance());
			System.out.println("-------------------------------------------------------------");
		}
		System.out.println("Select any user By UserName");
		return sc.next();
	}

	public static String getUserNameByUserName(String UN) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement
				.executeQuery("select Name from " + Table_User + " where username = '" + UN + "'");
		resultSet.next();
		String s = resultSet.getString("Name");

		resultSet.close();
		statement.close();
		return s;
	}

	public static void updateCount(int id) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select quantity from products where id =" + id + "");
		resultSet.next();
		System.out.print("Initial quantity >> " + resultSet.getInt("quantity") + " || ");
		int reduce = resultSet.getInt("quantity") - 1;
		statement
				.executeLargeUpdate("UPDATE " + Table_Product + " SET quantity = " + reduce + " WHERE id = " + id + "");
		resultSet = statement.executeQuery("select quantity from products where id =" + id + "");
		resultSet.next();
		System.out.println("Remening quantity >> " + resultSet.getInt("quantity"));
		resultSet.close();
		statement.close();
	}

	public static void updatePassowrd() throws SQLException {
		System.out.println("Enter User Name");
		String UN = sc.next();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement
				.executeQuery("select password from  " + Table_User + " where username ='" + UN + "'");
		resultSet.next();
		System.out.println("Old password > " + resultSet.getString("password"));
		System.out.println("Enter new Password");
		statement.executeLargeUpdate(
				"UPDATE  " + Table_User + " SET password = '" + sc.next() + "' WHERE username = '" + UN + "'");
		resultSet = statement.executeQuery("select password from " + Table_User + " where username ='" + UN + "'");
		resultSet.next();
		System.out.println("New password > " + resultSet.getString("password"));
		resultSet.close();
		statement.close();
	}

	public static void updateUserName() throws SQLException {
		System.out.println("Enter User Password");
		String PS = sc.next();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement
				.executeQuery("select username from  " + Table_User + " where password ='" + PS + "'");
		resultSet.next();
		System.out.println("Old username > " + resultSet.getString("username"));
		System.out.println("Enter new username");
		statement.executeLargeUpdate(
				"UPDATE  " + Table_User + " SET username = '" + sc.next() + "' WHERE password = '" + PS + "'");
		resultSet = statement.executeQuery("select username from " + Table_User + " where password ='" + PS + "'");
		resultSet.next();
		System.out.println("New username > " + resultSet.getString("username"));
		resultSet.close();
		statement.close();
	}

	public static void updateBalance(String PS, double balance) throws SQLException {
		System.out.println("************Bank Details*************");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement
				.executeQuery("select Balance from  " + Table_User + " where username ='" + PS + "'");
		resultSet.next();
		System.out.println("Old Balance > " + resultSet.getString("Balance"));
		statement.executeLargeUpdate(
				"UPDATE  " + Table_User + " SET Balance = " + balance + " WHERE username = '" + PS + "'");
		resultSet = statement.executeQuery("select Balance from " + Table_User + " where username ='" + PS + "'");
		resultSet.next();
		System.out.println("New Balance > " + resultSet.getString("Balance"));
		resultSet.close();
		statement.close();
		System.out.println("*************************************");
	}

	public static void updateBalance() throws SQLException {
		System.out.println("************Enter UserName************");
		String PS = sc.next();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement
				.executeQuery("select Balance from  " + Table_User + " where username ='" + PS + "'");
		resultSet.next();
		System.out.println("Old Balance > " + resultSet.getString("Balance"));
		System.out.println("Enter new Balance");
		statement.executeLargeUpdate(
				"UPDATE  " + Table_User + " SET Balance = " + sc.nextDouble() + " WHERE username = '" + PS + "'");
		resultSet = statement.executeQuery("select Balance from " + Table_User + " where username ='" + PS + "'");
		resultSet.next();
		System.out.println("New Balance > " + resultSet.getString("Balance"));
		resultSet.close();
		statement.close();
		System.out.println("*************************************");
	}

	public static void addProductToDB() throws SQLException {
		PreparedStatement statement = connection
				.prepareStatement("INSERT INTO products (id, name, price, quantity) VALUES(?,?,?,?)");
		statement.setInt(1,GetUserField. getMaxID() + 1);
		System.out.println("Enter Product Name >> ");
		statement.setString(2, sc.next());
		System.out.println("Enter Price of Product >> ");
		statement.setInt(3, sc.nextInt());
		System.out.println("Enter Quantity of Product >> ");
		statement.setInt(4, sc.nextInt());

		int response = statement.executeUpdate();
		if (response > 0) {
			System.out.println("Priduct Added Successfully ..... ");
			GetUserField.displayProductByID(GetUserField.getMaxID());
		} else {
			System.out.println("Priduct Adding Fails ..... ");
		}
		statement.close();
	}

	public static void removeProductFromDB(int id) throws SQLException {

		Statement statement = connection.createStatement();
		GetUserField.displayProductByID(id);
		long resultSet = statement.executeLargeUpdate(" DELETE FROM " + Table_Product + " WHERE id=" + id);
		if (resultSet > 0) {
			System.out.println("Product Deleted Successfully.... ");
		} else {
			System.out.println("Product deletion fail .... ");
		}
		statement.close();
	}

	public static void removeUserFromDB(String UN) throws SQLException {

		Statement statement = connection.createStatement();
		long resultSet = statement
				.executeLargeUpdate(" DELETE FROM " + Table_User + " WHERE username=" + "'" + UN + "'");
		if (resultSet > 0) {
			System.out.println("User Deleted Successfully.... ");
		} else {
			System.out.println("User deletion fail .... ");
		}
		statement.close();
	}

}
