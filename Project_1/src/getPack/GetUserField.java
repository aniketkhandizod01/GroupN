package getPack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import JDBC_Setup.Master;
import POJO.Product;

public class GetUserField extends Master {

	public static List<Product> getProductList() {
		List<Product> products = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM " + Table_Product + "");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				double price = resultSet.getDouble("price");
				int quantity = resultSet.getInt("quantity");

				Product product = new Product(id, name, price, quantity);
				products.add(product);
			}

			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return products;
	}

	public static int displayProductList() throws SQLException {
		System.out.println("///////////////////////// Product List ///////////////////////////");
		for (Product product : getProductList()) {
			System.out.print(" || ID : " + product.getId());
			System.out.print(" || Name : " + product.getName());
			System.out.print(" || Price : " + product.getPrice());
			System.out.println(" || Quantity : " + product.getQuantity());
			System.out.println("-------------------------------------------------------------");
		}
		System.out.println("Purchase any one Product By ID");
		return sc.nextInt();
	}

	public static void displayProductByID(int id) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from products where id = " + id);
		resultSet.next();
		System.out.println("--------------------- Your New Product Details  ----------------------");
		System.out.print(" || ID : " + resultSet.getInt("id"));
		System.out.print(" || Name : " + resultSet.getString("name"));
		System.out.print(" || Price : " + resultSet.getDouble("price"));
		System.out.println(" || Quantity : " + resultSet.getInt("quantity"));
		System.out.println("-----------------------------*********--------------------------------");

		resultSet.close();
		statement.close();
	}

	public static void checkProductQuantity(int productId) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement
				.executeQuery("SELECT quantity FROM " + Table_Product + " WHERE id = " + productId);

		if (resultSet.next()) {
			int quantity = resultSet.getInt("quantity");
			System.out.println("Quantity for Product ID " + productId + ": " + quantity);
		} else {
			System.out.println("Product not found!");
		}

		resultSet.close();
		statement.close();
	}
	public static int getMaxID() throws SQLException {
		int MaxID = 0;
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT MAX(id) as id FROM " + Table_Product);

		resultSet.next();
		MaxID = resultSet.getInt("id");

		resultSet.close();
		statement.close();
		return MaxID;
	}
public static void main(String[] args) throws SQLException {
	
}
}
