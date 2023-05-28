package JDBC_Setup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import POJO.Product;

public class Master {
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/Shop";
	static final String USER = "root";
	static final String PASS = "XYZ789rk@";
	static public Scanner sc = new Scanner(System.in);
	static public Statement st;
	public static Connection connection;
	public static final String Table_Product = "products";
	public static final String Table_User = "users";
	public static boolean guest;
	public static boolean admin;

	static {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("**** WelCome to Java Batch 'N' project ...****");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println();
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String space(List<Product> in, String s) {
		int lengthOflongestString = in.stream().map(S -> S.getName()).map(String::length).max(Integer::compare).get();
		int diff = lengthOflongestString - s.length();
		for (int i = 0; i < diff; i++) {
			return(" ");
		}
		return "";
	}

	public static String getMSG() {
		Calendar c = Calendar.getInstance();
		int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

		if (timeOfDay >= 0 && timeOfDay < 12) {
			return("Good Morning ");
		} else if (timeOfDay >= 12 && timeOfDay < 16) {
			return("Good Afternoon ");
		} else if (timeOfDay >= 16 && timeOfDay < 21) {
			return("Good Evening ");
		} else if (timeOfDay >= 21 && timeOfDay < 24) {
			return("Good Night ");
		}
		return null;
	}
	
}
