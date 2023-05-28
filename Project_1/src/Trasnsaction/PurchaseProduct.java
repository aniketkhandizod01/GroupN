package Trasnsaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBC_Setup.Master;
import getPack.GetUserField;

public class PurchaseProduct extends Master {

	public static void buyProducts() throws SQLException {
		List<Integer> priceCollection = new ArrayList<Integer>();

		do {
			priceCollection.add(GetUserField.displayProductList());
			System.out.println("1 -> exit || 0 -> Add more");
		} while (sc.nextInt() == 0);
		TransactionLogics.choseProductList(priceCollection);

	}

	public static void main(String[] args) throws SQLException {
		buyProducts();
	}
}
