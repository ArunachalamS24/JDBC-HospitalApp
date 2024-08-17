package hospital;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Application {
	
	private static Scanner scan = new Scanner(System.in);
	private static String url = "jdbc:mysql://localhost:3306/company";
	private static String user = "root";
	private static String pass = "root";
	
	public static void main(String[] args) {
		while(true) {
			System.out.println("Enter your choice");
			System.out.println("1 - Register new Patient");
			System.out.println("2 - Update Disease by Id");
			System.out.println("3 - Update Phone number by name and id");
			System.out.println("4 - Fetch all records");
			System.out.println("5 - Fetch by disease");
			System.out.println("6 - Fetch by name");
			System.out.println("7 - Delete by id");
			System.out.println("8 - Search by name");
			System.out.println("0 - Exit");
			System.out.println();
			
			int choice = scan.nextInt();
			if(choice!=0) {
				if(choice==1)
					Insert();
				else if(choice==2)
					UpdateDisease();
				else if(choice==3)
					UpdatePhn();
				else if(choice==4)
					fetchAll();
				else if(choice==5)
					fetchDisease();
				else if(choice==6)
					fetchName();
				else if(choice==7)
					delete();
				else if(choice==8)
					search();
				else
					System.out.println("Invalid Choice");
				
			}
			else {
				break;
			}
		}
		
		scan.close();
	}

	

	public static void Insert() {
		System.out.println("Enter ID, NAME, AGE, GENDER, PHONE, DISEASE, ADMITDATE");
		int id = scan.nextInt();
		String name = scan.next();
		int age = scan.nextInt();
		String gender = scan.next();
		long phn = scan.nextLong();
		String disease = scan.next();
		String admitDate = scan.next();		
		PreparedStatement s = null;
		Connection con = null;

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pass);
			s = con.prepareStatement("insert into hospital values(?,?,?,?,?,?,?)");
			s.setInt(1, id);
			s.setString(2, name);
			s.setInt(3, age);
			s.setString(4, gender);
			s.setLong(5, phn);
			s.setString(6,disease);
			s.setString(7, admitDate);
			System.out.println(s.executeUpdate()+" Rows affected");

		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(s!=null) {
				try {
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			if(con!=null) {
				try {
					con.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

}

	public static void UpdateDisease() {
		System.out.println("Enter ID, DISEASE");
		int id = scan.nextInt();
		String disease = scan.next();		
		
		PreparedStatement s = null;
		Connection con = null;

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pass);
			s = con.prepareStatement("update hospital set disease = ? where id = ?");
			s.setString(1,disease);
			s.setInt(2, id);
			System.out.println(s.executeUpdate()+" Rows affected");

		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(s!=null) {
				try {
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			if(con!=null) {
				try {
					con.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
		
	}

	public static void UpdatePhn() {
		System.out.println("Enter ID, NAME, PHONE");
		int id = scan.nextInt();
		String name = scan.next();
		long phone = scan.nextLong();		
		
		PreparedStatement s = null;
		Connection con = null;

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pass);
			s = con.prepareStatement("update hospital set phone = ? where name = ? and id = ?");
			s.setLong(1,phone);
			s.setString(2,name);
			s.setInt(3, id);
			System.out.println(s.executeUpdate()+" Rows affected");

		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(s!=null) {
				try {
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			if(con!=null) {
				try {
					con.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
		
	}

	public static void fetchAll() {
		
		PreparedStatement s = null;
		Connection con = null;

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pass);
			s = con.prepareStatement("select* from hospital");
			s.execute();
			ResultSet res = s.getResultSet();
			while(res.next()) {
				System.out.println(res.getInt(1)+"  "+res.getString(2)+"  "+res.getInt(3)+"  "+res.getString(4)+"  "+res.getLong(5)+"  "+res.getString(6)+"  "+res.getString(7));
			}
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(s!=null) {
				try {
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			if(con!=null) {
				try {
					con.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	}
	
	public static void fetchDisease() {
		System.out.println("Enter DISEASE");
		String disease = scan.next();
		
		PreparedStatement s = null;
		Connection con = null;

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pass);
			s = con.prepareStatement("select* from hospital where disease = ?");
			s.setString(1, disease);
			s.execute();
			ResultSet res = s.getResultSet();
			while(res.next()) {
				System.out.println(res.getInt(1)+"  "+res.getString(2)+"  "+res.getInt(3)+"  "+res.getString(4)+"  "+res.getLong(5)+"  "+res.getString(6)+"  "+res.getString(7));
			}
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(s!=null) {
				try {
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			if(con!=null) {
				try {
					con.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	}

	public static void fetchName() {
		System.out.println("Enter name");
		String name = scan.next();
		
		PreparedStatement s = null;
		Connection con = null;

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pass);
			s = con.prepareStatement("select* from hospital where name = ?");
			s.setString(1, name);
			s.execute();
			ResultSet res = s.getResultSet();
			while(res.next()) {
				System.out.println(res.getInt(1)+"  "+res.getString(2)+"  "+res.getInt(3)+"  "+res.getString(4)+"  "+res.getLong(5)+"  "+res.getString(6)+"  "+res.getString(7));
			}
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(s!=null) {
				try {
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			if(con!=null) {
				try {
					con.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
		
	}

	public static void delete() {
		System.out.println("Enter id");
		int Id = scan.nextInt();
		
		PreparedStatement s = null;
		Connection con = null;

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pass);
			s = con.prepareStatement("delete from hospital where id = ?");
			s.setInt(1, Id);
			System.out.println(s.executeUpdate()+ " rows affected");
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(s!=null) {
				try {
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			if(con!=null) {
				try {
					con.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
		
	}
	
	public static void search() {
		System.out.println("Enter charecter");
		String ch = scan.next().charAt(0)+ "%";
		
		PreparedStatement s = null;
		Connection con = null;

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pass);
			s = con.prepareStatement("select* from hospital where name like ?");
			s.setString(1, ch);
			s.execute();
			ResultSet res = s.getResultSet();
			while(res.next()) {
				System.out.println(res.getInt(1)+"  "+res.getString(2)+"  "+res.getInt(3)+"  "+res.getString(4)+"  "+res.getLong(5)+"  "+res.getString(6)+"  "+res.getString(7));
			}
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(s!=null) {
				try {
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			if(con!=null) {
				try {
					con.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
		
	}

}
