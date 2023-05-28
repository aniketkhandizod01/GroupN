package Trasnsaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import JDBC_Setup.Master;
import POJO.Product;

public class TransactionLogics extends Master {
	static List<Double> priceSum = new ArrayList<>();

	public static int getUserBalance(String UN) throws SQLException {

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement
				.executeQuery("select Balance from " + Table_User + " where username = '" + UN + "'");
		resultSet.next();
		int s = resultSet.getInt("Balance");

		resultSet.close();
		statement.close();
		return s;
	}

	public static void updateBalance(String UN, double balance) throws SQLException {
		System.out.println("************Bank Details*************");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement
				.executeQuery("select Balance from  " + Table_User + " where username ='" + UN + "'");
		resultSet.next();
		System.out.println("Old Balance > " + resultSet.getString("Balance"));
		statement.executeLargeUpdate(
				"UPDATE  " + Table_User + " SET Balance = " + balance + " WHERE username = '" + UN + "'");
		resultSet = statement.executeQuery("select Balance from " + Table_User + " where username ='" + UN + "'");
		resultSet.next();
		System.out.println("New Balance > " + resultSet.getString("Balance"));
		resultSet.close();
		statement.close();
		System.out.println("*************************************");
	}

	public static void deductBalance(String UN, double balance) throws SQLException {
		System.out.println("************Bank Details*************");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement
				.executeQuery("select Balance from  " + Table_User + " where username ='" + UN + "'");
		resultSet.next();
		double bal = resultSet.getDouble("Balance") - balance;
		System.out.println("Old Balance > " + resultSet.getDouble("Balance"));
		statement.executeLargeUpdate(
				"UPDATE  " + Table_User + " SET Balance = " + bal + " WHERE username = '" + UN + "'");
		resultSet = statement.executeQuery("select Balance from " + Table_User + " where username ='" + UN + "'");
		resultSet.next();
		System.out.println("New Balance > " + resultSet.getDouble("Balance"));
		resultSet.close();
		statement.close();
		System.out.println("*************************************");
	}

	public static boolean UserBalanceValidation(String UN, double check) throws SQLException {

		int balance = getUserBalance(UN);
		return balance >= check;
	}

	public static void getProductByID(int ID) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from " + Table_Product + " where id = " + ID + "");
		resultSet.next();
		int id = resultSet.getInt("id");
		String name = resultSet.getString("name");
		double price = resultSet.getDouble("price");
		int quantity = resultSet.getInt("quantity");

		Product product = new Product(id, name, price, quantity);

		resultSet.next();
		double add;
		System.out.println("///////////////////////// your Cart ///////////////////////////");
		System.out.print(" || ID : " + product.getId());
		System.out.print(" || Name : " + product.getName());
		System.out.print(" || Price : " + (add = product.getPrice()));
		System.out.println(" || Quantity : " + product.getQuantity());
		System.out.println("-------------------------------------------------------------");
		priceSum.add(add);
		resultSet.close();
		statement.close();
	}

	public static double choseProductList(List<Integer> ls) throws SQLException {

		ls.stream().forEach(S -> {
			try {
				getProductByID(S);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		Double sum = priceSum.stream().reduce(0.0, Double::sum);
		System.out.println("You need to pay >> " + sum + "/-");
		System.out.println("Please enter your UserName to check your balance >> ");
		String UserName = sc.next();
		boolean flag = UserBalanceValidation(UserName, sum);

		if (flag) {
			System.out.println("You have balance to purchase product do you want to conitnue Billing ? Y/N");
			String input = sc.next();
			if (input.equalsIgnoreCase("Y")) {
				System.out.println("Billing");
				deductBalance(UserName, sum);
			} else {
				System.out.println("Your Skipping Payment .... Visit again !");
			}

		} else {
			System.out.println("You dont have enough  balance to purchase product");
		}
		return sum;
	}

	public static void main(String[] args) throws SQLException {
		// System.out.println(UserBalanceValidation("anu",56));

		choseProductList(Arrays.asList(99999, 12345, 54321));
	}

}
