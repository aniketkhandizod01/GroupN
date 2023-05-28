package POJO;

public class User {

	private String username;
	private String password;
	private String Name;
	private long Phone;
	private double Balance;

	public User(String username, String password, String name, long phone, double balance) {
		this.username = username;
		this.password = password;
		this.Name = name;
		this.Phone = phone;
		this.Balance = balance;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public long getPhone() {
		return Phone;
	}

	public void setPhone(long phone) {
		Phone = phone;
	}

	public double getBalance() {
		return Balance;
	}

	public void setBalance(double balance) {
		Balance = balance;
	}

}