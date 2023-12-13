import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

/**
 * Servlet implementation class Connect
 */
@WebServlet("/userDAO")
public class userDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public userDAO(){}
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
    	//uses default connection to the database
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false&user=john&password=pass1234");
            System.out.println(connect);
        }
    }
    
    public boolean database_login(String email, String password) throws SQLException{
    	try {
    		connect_func("root","pass1234");
    		String sql = "select * from user where email = ?";
    		preparedStatement = connect.prepareStatement(sql);
    		preparedStatement.setString(1, email);
    		ResultSet rs = preparedStatement.executeQuery();
    		return rs.next();
    	}
    	catch(SQLException e) {
    		System.out.println("failed login");
    		return false;
    	}
    }
	//connect to the database 
    public void connect_func(String username, String password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/userdb?"
  			          + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }
    

	protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
	
	
//---------------------------------------------------------------------------------------------------------------------------------------------
//CRUD methods for User
	
    public void insert(user users) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into User(email, firstName, lastName, password, creditCardNumber, phoneNumber, role, adress_street_num, adress_street, adress_city, adress_state, adress_zip_code) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, users.getEmail());
		preparedStatement.setString(2, users.getFirstName());
		preparedStatement.setString(3, users.getLastName());			preparedStatement.setString(4, users.getPassword());
		preparedStatement.setString(5, users.getCreditCardNumber());
		preparedStatement.setString(6, users.getPhoneNumber());
		preparedStatement.setString(7, users.getRole());
		preparedStatement.setString(8, users.getAdress_street_num());		
		preparedStatement.setString(9, users.getAdress_street());		
		preparedStatement.setString(10, users.getAdress_city());		
		preparedStatement.setString(11, users.getAdress_state());		
		preparedStatement.setString(12, users.getAdress_zip_code());	
	
	
		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    
    public List<user> listAllUsers() throws SQLException {
        List<user> listUser = new ArrayList<user>();        
        String sql = "SELECT * FROM User ORDER BY id";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	String id = resultSet.getString("id");
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String creditCardNumber = resultSet.getString("creditCardNumber");
            String phoneNumber = resultSet.getString("phoneNumber");
            String role = resultSet.getString("role");
            String adress_street_num = resultSet.getString("adress_street_num"); 
            String adress_street = resultSet.getString("adress_street"); 
            String adress_city = resultSet.getString("adress_city"); 
            String adress_state = resultSet.getString("adress_state"); 
            String adress_zip_code = resultSet.getString("adress_zip_code");
   
            user users = new user(id, email,firstName, lastName, password, creditCardNumber,phoneNumber,role, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code);
            listUser.add(users);
        }        
        resultSet.close();
        disconnect();        
        return listUser;
    }

    
    public user getUser(String email) throws SQLException {
    	user user = null;
        String sql = "SELECT * FROM User WHERE email = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(2, email);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
        	String id = resultSet.getString("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String creditCardNumber = resultSet.getString("creditCardNumber");
            String phoneNumber = resultSet.getString("phoneNumber");
            String role = resultSet.getString("role");
            String adress_street_num = resultSet.getString("adress_street_num"); 
            String adress_street = resultSet.getString("adress_street"); 
            String adress_city = resultSet.getString("adress_city"); 
            String adress_state = resultSet.getString("adress_state"); 
            String adress_zip_code = resultSet.getString("adress_zip_code"); 

            user = new user(id, email, firstName, lastName, password, creditCardNumber, phoneNumber, role, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }
    
    
    public boolean update(user users) throws SQLException {
    	PreparedStatement preparedStatement = null;
        String sql = "update User set firstName=?, lastName =?,password = ?, creditCardNumber=?, phoneNumber=?, role=?, adress_street_num =?, adress_street=?,adress_city=?,adress_state=?,adress_zip_code=?, where email = ?";
        connect_func();
        preparedStatement = connect.prepareStatement(sql);
		preparedStatement.setString(2, users.getEmail());
		preparedStatement.setString(3, users.getFirstName());
		preparedStatement.setString(4, users.getLastName());
		preparedStatement.setString(5, users.getPassword());
		preparedStatement.setString(6, users.getCreditCardNumber());
		preparedStatement.setString(7, users.getPhoneNumber());
		preparedStatement.setString(8, users.getRole());
		preparedStatement.setString(9, users.getAdress_street_num());		
		preparedStatement.setString(10, users.getAdress_street());		
		preparedStatement.setString(11, users.getAdress_city());		
		preparedStatement.setString(12, users.getAdress_state());		
		preparedStatement.setString(13, users.getAdress_zip_code());	
		         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    

    public boolean delete(String email) throws SQLException {
        String sql = "DELETE FROM User WHERE email = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(2, email);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     

    
 //--------------------------------------------------------------------------------------------------------------------------------------------
 //CRUD methods for Tree
    
    public void insertTree(Tree tree) throws SQLException{
    	PreparedStatement preparedStatement = null;
    	try {
        	connect_func("root", "pass1234");
        	String sql= "insert into Tree(size, height, location, proximityToHouse, treeNo, clientID, quoteID) values (?, ?, ?, ?, ?, ?, ?)";
        	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        	preparedStatement.setString(1, tree.getSize());
        	preparedStatement.setString(2, tree.getHeight());
        	preparedStatement.setString(3, tree.getLocation());
        	preparedStatement.setString(4, tree.getProximityToHouse());
        	preparedStatement.setString(5, tree.getTreeNo());
        	preparedStatement.setString(6, tree.getClientID());
        	preparedStatement.setString(7, tree.getQuoteID());
        	
        	preparedStatement.executeUpdate();
    	}catch (SQLException e) {
    		//Handle exception
    		throw e;
    	}finally {
    		if (preparedStatement != null) {
    			preparedStatement.close();
    		}
    	}
    }
    
    public List<Tree> listAllTrees() throws SQLException{
    	List<Tree> listTree = new ArrayList<Tree>();
    	String sql = "SELECT * FROM Tree ORDER BY treeID";
    	connect_func();
    	statement = (Statement)connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	while(resultSet.next()) {
    		String treeID = resultSet.getString("treeID");
    		String size = resultSet.getString("size");
    		String height = resultSet.getString("height");
    		String location = resultSet.getString("location");
    		String proximityToHouse = resultSet.getString("proximityToHouse");
    		String treeNo = resultSet.getString("treeNo");
    		String clientID = resultSet.getString("clientID");
    		String quoteID = resultSet.getString("quoteID");
    		
    		Tree tree = new Tree(treeID, size, height, location, proximityToHouse, treeNo, clientID, quoteID);
    		listTree.add(tree);
    	}
    	resultSet.close();
    	disconnect();
    	return listTree;
    }
    

    public void deleteTree(String treeID) throws SQLException {
        connect_func();
        String sql = "DELETE FROM Tree WHERE treeID = ?";
        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setString(1, treeID);
            preparedStatement.executeUpdate();
        } finally {
            disconnect();
        }
    }
    
  //---------------------------------------------------------------------------------------------------------------------------------------------------------
  //CRUD methods for Quote;
    public void insertQuote(Quote quote, String treeID) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            connect_func(); // Connect to the database
            String insertSql = "INSERT INTO Quote (initialPrice, scheduleStart, scheduleEnd, status, clientID, contractorID) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connect.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, quote.getInitialPrice());
            preparedStatement.setString(2, quote.getScheduleStart());
            preparedStatement.setString(3, quote.getScheduleEnd());
            preparedStatement.setString(4, quote.getStatus());
            preparedStatement.setString(5, quote.getClientID());
            preparedStatement.setString(6, quote.getContractorID());
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating quote failed, no rows affected.");
            }

            // Retrieve the generated quoteID
            generatedKeys = preparedStatement.getGeneratedKeys();
            long quoteID;
            if (generatedKeys.next()) {
                quoteID = generatedKeys.getLong(1); // This gets the generated quoteID

                // Update the tree table with this quoteID
                String updateTreeSql = "UPDATE Tree SET quoteID = ? WHERE treeID = ?";
                try (PreparedStatement updateStmt = connect.prepareStatement(updateTreeSql)) {
                    updateStmt.setLong(1, quoteID);
                    updateStmt.setString(2, treeID); // Use the passed treeID
                    updateStmt.executeUpdate();
                }
            } else {
                throw new SQLException("Creating quote failed, no ID obtained.");
            }
        } catch (SQLException e) {
            // Handle exception
            throw e;
        } finally {
            if (generatedKeys != null) generatedKeys.close();
            if (preparedStatement != null) preparedStatement.close();
        }
    }

    
    public List<Quote> listAllQuotes() throws SQLException{
    	List<Quote> listQuote = new ArrayList<Quote>();
    	String sql = "SELECT * FROM Quote ORDER BY quoteID";
    	connect_func();
    	statement = (Statement)connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	while(resultSet.next()) {
    		String quoteID = resultSet.getString("quoteID");
    		String initialPrice = resultSet.getString("initialPrice");
    		String scheduleStart = resultSet.getString("scheduleStart");
    		String scheduleEnd = resultSet.getString("scheduleEnd");
    		String status = resultSet.getString("status");
    		String clientID = resultSet.getString("clientID");
    		String contractorID = resultSet.getString("contractorID");
    		
    		Quote quote = new Quote(quoteID, initialPrice, scheduleStart, scheduleEnd, status, clientID, contractorID);
    		listQuote.add(quote);
    	}
    	resultSet.close();
    	disconnect();
    	return listQuote;
    }
    

    
    public boolean deleteQuote(String quoteID) throws SQLException{
    	String sql = "DELETE FROM Quote Where quoteID = ?";
    	String deleteQuoteMessage = "DELETE FROM QuoteMessages WHERE quoteID = ?";
    	String deleteTreeQuote = "DELETE FROM Tree WHERE quoteID = ?";
    	
    	
    		try(PreparedStatement preparedStatementMessages = connect.prepareStatement(deleteQuoteMessage))
    		{
    			preparedStatementMessages.setString(1, quoteID);
        		int rowsAffectedMessages = preparedStatementMessages.executeUpdate();
        		
        		try(PreparedStatement preparedStatementTree = connect.prepareStatement(deleteTreeQuote))
        		{
            		preparedStatementTree.setString(1, quoteID);
                	int rowsAffectedTree = preparedStatementTree.executeUpdate();
                	
                	try(PreparedStatement preparedStatementQuote = connect.prepareStatement(sql))
            		{
                		preparedStatementQuote.setString(1, quoteID);
                    	int rowsAffected = preparedStatementQuote.executeUpdate();
                    	return rowsAffected > 0;
            		}
        		}
   
    	} catch (SQLException e)
    	{
    		e.printStackTrace();
    		return false;
    	}
    }
    
   //----------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
    // CRUD methods for QuoteMessages

    // Create (Insert)
    public void insertQuoteMessage(QuoteMessages quoteMessage) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            connect_func(); 
            String sql = "INSERT INTO QuoteMessages (userID, quoteID, msgTime, note) VALUES (?, ?, ?, ?)";
            preparedStatement = connect.prepareStatement(sql);

            preparedStatement.setString(1, quoteMessage.getUserID());
            preparedStatement.setString(2, quoteMessage.getQuoteID());
            preparedStatement.setString(3, quoteMessage.getMsgTime());
            preparedStatement.setString(4, quoteMessage.getNote());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    // Read (List All)
    public List<QuoteMessages> listAllQuoteMessages() throws SQLException {
        List<QuoteMessages> listQuoteMessage = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connect_func();
            String sql = "SELECT * FROM QuoteMessages";
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String quoteMessageID = resultSet.getString("quoteMessageID");
                String userID = resultSet.getString("userID");
                String quoteID = resultSet.getString("quoteID");
                String msgTime = resultSet.getString("msgTime");
                String note = resultSet.getString("note");

                QuoteMessages quoteMessage = new QuoteMessages(quoteMessageID, userID, quoteID, msgTime, note);
                listQuoteMessage.add(quoteMessage);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return listQuoteMessage;
    }


    
 // CRUD methods for Bills

    // Create (Insert)
    public void insertBill(Bills bill) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            connect_func();
            String sql = "INSERT INTO Bills (orderID, clientID, price, dateGenerated, datePaid, status) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connect.prepareStatement(sql);

            preparedStatement.setString(1, bill.getOrderID());
            preparedStatement.setString(2, bill.getClientID());
            preparedStatement.setString(3, bill.getPrice());
            preparedStatement.setString(4, bill.getDateGenerated());
            preparedStatement.setString(5, bill.getDatePaid());
            preparedStatement.setString(6, bill.getStatus());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    // Read (List All)
    public List<Bills> listAllBills() throws SQLException {
        List<Bills> listBills = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connect_func(); 
            String sql = "SELECT * FROM Bills";
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String billID = resultSet.getString("billID");
                String orderID = resultSet.getString("orderID");
                String clientID = resultSet.getString("clientID");
                String price = resultSet.getString("price");
                String dateGenerated = resultSet.getString("dateGenerated");
                String datePaid = resultSet.getString("datePaid");
                String status = resultSet.getString("status");

                Bills bill = new Bills(billID, orderID, clientID,  price, dateGenerated, datePaid, status);
                listBills.add(bill);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return listBills;
    }


    // Update
    public boolean updateBill(Bills bill) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            connect_func();
            String sql = "UPDATE Bills SET orderID = ?, clientID = ?, price = ?, dateGenerated = ?, datePaid = ?, status = ? WHERE billID = ?";

            preparedStatement = connect.prepareStatement(sql);

            preparedStatement.setString(1, bill.getOrderID());
            preparedStatement.setString(2, bill.getClientID());
            preparedStatement.setString(3, bill.getPrice());
            preparedStatement.setString(4, bill.getDateGenerated());
            preparedStatement.setString(5, bill.getDatePaid());
            preparedStatement.setString(6, bill.getStatus());
            preparedStatement.setString(7, bill.getBillID());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    
 // CRUD methods for Order

    // Create (Insert)
    public void insertOrder(Order order) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            connect_func(); 
            String sql = "INSERT INTO Order (quoteID, clientID, price, scheduleStart, scheduleEnd, status) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connect.prepareStatement(sql);

            preparedStatement.setString(1, order.getQuoteID());
            preparedStatement.setString(2, order.getClientID());
            preparedStatement.setString(3, order.getPrice());
            preparedStatement.setString(4, order.getScheduleStart());
            preparedStatement.setString(5, order.getScheduleEnd());
            preparedStatement.setString(6, order.getStatus());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    // Read (List All)
    public List<Order> listAllOrders() throws SQLException {
        List<Order> listOrders = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connect_func(); 
            String sql = "SELECT * FROM Orders";
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String orderID = resultSet.getString("orderID");
                String quoteID = resultSet.getString("quoteID");
                String clientID = resultSet.getString("clientID");
                String price = resultSet.getString("price");
                String scheduleStart = resultSet.getString("scheduleStart");
                String scheduleEnd = resultSet.getString("scheduleEnd");
                String status = resultSet.getString("status");

                Order order = new Order(orderID, quoteID, clientID, price, scheduleStart, scheduleEnd, status);
                listOrders.add(order);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return listOrders;
    }

 
    
 // CRUD methods for BillMessages

    // Create (Insert)
    public void insertBillMessage(BillMessages billMessage) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            connect_func();
            String sql = "INSERT INTO BillMessages (clientID, billID, msgTime, price, note) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connect.prepareStatement(sql);

            preparedStatement.setString(1, billMessage.getClientID());
            preparedStatement.setString(2, billMessage.getBillID());
            preparedStatement.setString(3, billMessage.getMsgTime()); // Assuming msgTime is a String
            preparedStatement.setString(4, billMessage.getPrice());
            preparedStatement.setString(5, billMessage.getNote());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            disconnect();
        }
    }

    // Read (List All)
    public List<BillMessages> listAllBillMessages() throws SQLException {
        List<BillMessages> listBillMessages = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connect_func(); 
            String sql = "SELECT * FROM BillMessages";
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String billMessageID = resultSet.getString("billMessageID");
                String clientID = resultSet.getString("clientID");
                String billID = resultSet.getString("billID");
                String msgTime = resultSet.getString("msgTime");
                String price = resultSet.getString("price");
                String note = resultSet.getString("note");

                BillMessages billMessage = new BillMessages(billMessageID, clientID, billID, msgTime, price, note);
                listBillMessages.add(billMessage);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return listBillMessages;
    }


    // Update
    public boolean updateBillMessage(BillMessages billMessage) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            connect_func(); 
            String sql = "UPDATE BillMessages SET clientID = ?, billID = ?, msgTime = ?, price = ?, note = ? WHERE billMessageID = ?";

            preparedStatement = connect.prepareStatement(sql);

            preparedStatement.setString(1, billMessage.getClientID());
            preparedStatement.setString(2, billMessage.getBillID());
            preparedStatement.setString(3, billMessage.getMsgTime());
            preparedStatement.setString(4, billMessage.getPrice());
            preparedStatement.setString(5, billMessage.getNote());
            preparedStatement.setString(6, billMessage.getBillMessageID());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    
    public List<Quote> getQuotesByClientID(String clientID) throws SQLException {
        List<Quote> listQuote = new ArrayList<Quote>();
        String sql = "SELECT * FROM Quote WHERE clientID = ?";
        
        try {
            connect_func();
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, clientID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String quoteID = resultSet.getString("quoteID");
                String initialPrice = resultSet.getString("initialPrice");
                String scheduleStart = resultSet.getString("scheduleStart");
                String scheduleEnd = resultSet.getString("scheduleEnd");
                String status = resultSet.getString("status");
                String contractorID = resultSet.getString("contractorID");
                
                Quote quote = new Quote(quoteID, initialPrice, scheduleStart, scheduleEnd, status, clientID, contractorID);
                listQuote.add(quote);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
            disconnect();
        }
        return listQuote;
    }
    
    
    
    public List<Bills> getBillsByClientID(String clientID) throws SQLException {
        List<Bills> listBills = new ArrayList<>();
        String sql = "SELECT * FROM Bills WHERE clientID = ?";
        connect_func();

        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setString(1, clientID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String billID = resultSet.getString("billID");
                String orderID = resultSet.getString("orderID");
                String price = resultSet.getString("price");
                String dateGenerated = resultSet.getString("dateGenerated");
                String datePaid = resultSet.getString("datePaid");
                String status = resultSet.getString("status");

                Bills bill = new Bills(billID, orderID, clientID, price, dateGenerated, datePaid, status);
                listBills.add(bill);
            }
        } finally {
            disconnect();
        }

        return listBills;
    }

    
    public boolean checkEmail(String email) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE email = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
    	return checks;
    }
    
    public boolean checkPassword(String password) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE password = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
       	return checks;
    }
    
    
    
    public boolean isValid(String email, String password) throws SQLException
    {
    	String sql = "SELECT * FROM User"; 
    	connect_func();
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	resultSet.last();
    	
    	int setSize = resultSet.getRow();
    	resultSet.beforeFirst();
    	
    	for(int i = 0; i < setSize; i++)
    	{
    		resultSet.next();
    		if(resultSet.getString("email").equals(email) && resultSet.getString("password").equals(password)) {
    			return true;
    		}		
    	}
    	return false;
    }
    
    public boolean checkPhoneNumber(String phoneNumber) throws SQLException {
        String sql = "SELECT * FROM User WHERE phoneNumber = ?";
        connect_func();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, phoneNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }
    
    public String getUserIDByEmail(String email) throws SQLException {
        String id = null;
        String sql = "SELECT id FROM User WHERE email = ?";

        try {
            connect_func();
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getString("id");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return id;
    }



    public List<Tree> listTreesWithoutQuote() throws SQLException {
        List<Tree> listTree = new ArrayList<>();
        String sql = "SELECT * FROM Tree WHERE quoteID IS NULL OR quoteID = ''";
        connect_func(); 
        try (Statement statement = connect.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Tree tree = new Tree(
                    resultSet.getString("treeID"),
                    resultSet.getString("size"),
                    resultSet.getString("height"),
                    resultSet.getString("location"),
                    resultSet.getString("proximityToHouse"),
                    resultSet.getString("treeNo"),
                    resultSet.getString("clientID"),
                    resultSet.getString("quoteID")
                );
                listTree.add(tree);
            }
        }
        return listTree;
    }

    public List<QuoteMessageWithPrice> listAllQuoteMessagesWithPrice() throws SQLException {
        List<QuoteMessageWithPrice> list = new ArrayList<>();
        String sql = "SELECT qm.quoteMessageID, qm.userID, qm.quoteID, qm.msgTime, qm.note, q.initialPrice " +
                     "FROM QuoteMessages qm INNER JOIN Quote q ON qm.quoteID = q.quoteID";

        connect_func(); // Ensure connection is open
        try (Statement statement = connect.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                QuoteMessageWithPrice qmwp = new QuoteMessageWithPrice(
                    resultSet.getString("quoteMessageID"),
                    resultSet.getString("userID"),
                    resultSet.getString("quoteID"),
                    resultSet.getString("msgTime"),
                    resultSet.getString("note"),
                    resultSet.getDouble("initialPrice")
                );
                list.add(qmwp);
            }
        }
        return list;
    }

    public void updateQuoteDetails(String quoteID, String newPrice, String newScheduleStart, String newScheduleEnd) throws SQLException {
        String sql = "UPDATE Quote SET ";

        List<String> updates = new ArrayList<>();
        if (newPrice != null && !newPrice.trim().isEmpty()) {
            updates.add("initialPrice = " + newPrice);
        }
        if (newScheduleStart != null && !newScheduleStart.trim().isEmpty()) {
            updates.add("scheduleStart = '" + newScheduleStart + "'");
        }
        if (newScheduleEnd != null && !newScheduleEnd.trim().isEmpty()) {
            updates.add("scheduleEnd = '" + newScheduleEnd + "'");
        }
        sql += String.join(", ", updates);
        sql += " WHERE quoteID = ?";

        if (!updates.isEmpty()) {
            connect_func();
            try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
                preparedStatement.setInt(1, Integer.parseInt(quoteID));
                preparedStatement.executeUpdate();
            } finally {
                disconnect();
            }
        }
    }

    public void agreeToQuote(String quoteID, String clientID, String price, String scheduleStart, String scheduleEnd) throws SQLException {
        try {
            connect_func(); // Connect to the database

            // Insert a new order
            String insertOrderSql = "INSERT INTO Orders (quoteID, clientID, price, scheduleStart, scheduleEnd, status) VALUES (?, ?, ?, ?, ?, 'In Progress')";
            try (PreparedStatement insertOrderStmt = connect.prepareStatement(insertOrderSql)) {
                insertOrderStmt.setString(1, quoteID);
                insertOrderStmt.setString(2, clientID);
                insertOrderStmt.setString(3, price);
                insertOrderStmt.setString(4, scheduleStart);
                insertOrderStmt.setString(5, scheduleEnd);
                insertOrderStmt.executeUpdate();
            }

            // Update the quote status
            String updateQuoteSql = "UPDATE Quote SET status = 'Confirmed' WHERE quoteID = ?";
            try (PreparedStatement updateQuoteStmt = connect.prepareStatement(updateQuoteSql)) {
                updateQuoteStmt.setString(1, quoteID);
                updateQuoteStmt.executeUpdate();
            }
        } finally {
            disconnect();
        }
    }

 // Method to create a new bill
    public void createBill(String orderID, String clientID, String price, java.sql.Date dateGenerated) 
            throws SQLException {
        connect_func();
        String sql = "INSERT INTO Bills (orderID, clientID, price, dateGenerated, datePaid, status) VALUES (?, ?, ?, ?, NULL, 'Unpaid')";
        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setString(1, orderID);
            preparedStatement.setString(2, clientID);
            preparedStatement.setString(3, price);
            preparedStatement.setDate(4, dateGenerated);
            preparedStatement.executeUpdate();
        } finally {
            disconnect();
        }
    }

    // Method to update the order status
    public void updateOrderStatus(String orderID, String status) throws SQLException {
        connect_func();
        String sql = "UPDATE Orders SET status = ? WHERE orderID = ?";
        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, orderID);
            preparedStatement.executeUpdate();
        } finally {
            disconnect();
        }
    }


    public void payBill(String billID, java.sql.Date datePaid) throws SQLException {
        connect_func();
        String sql = "UPDATE Bills SET status = 'Paid', datePaid = ? WHERE billID = ?";
        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setDate(1, datePaid);
            preparedStatement.setString(2, billID);
            preparedStatement.executeUpdate();
        } finally {
            disconnect();
        }
    }
    
    public List<BigClient> listBigClients() throws SQLException {
        List<BigClient> bigClients = new ArrayList<>();
        String sql = "SELECT " +
	                "    u.firstName, " +
	                "    u.lastName, " +
	                "    o.clientID, " +
	                "    SUM(t.treeNo) AS totalTreesCut " +
	                "FROM " +
	                "    User u " +
	                "JOIN " +
	                "    Orders o ON u.id = o.clientID " +
	                "JOIN " +
	                "    Tree t ON o.quoteID = t.quoteID " +
	                "WHERE " +
	                "    o.status = 'Finished' " +
	                "GROUP BY " +
	                "    o.clientID " +
	                "HAVING " +
	                "    SUM(t.treeNo) = ( " +
	                "        SELECT MAX(totalTrees) FROM ( " +
	                "            SELECT " +
	                "                SUM(treeNo) as totalTrees " +
	                "            FROM " +
	                "                Orders " +
	                "            JOIN " +
	                "                Tree ON Orders.quoteID = Tree.quoteID " +
	                "            WHERE " +
	                "                Orders.status = 'Finished' " +
	                "            GROUP BY " +
	                "                Orders.clientID " +
	                "        ) AS subquery " +
	                "    ) " +
	                "ORDER BY " +
	                "    totalTreesCut DESC;";

        connect_func();
        try (Statement statement = connect.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                BigClient bigClient = new BigClient(
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getInt("clientID"),
                    resultSet.getInt("totalTreesCut")
                );
                bigClients.add(bigClient);
            }
        } finally {
            disconnect();
        }
        return bigClients;
    }
    
    public List<EasyClient> listEasyClients() throws SQLException {
        List<EasyClient> easyClients = new ArrayList<>();
        String sql = "SELECT " +
                     "    u.firstName, " +
                     "    u.lastName, " +
                     "    q.clientID " +
                     "FROM " +
                     "    User u " +
                     "JOIN " +
                     "    Quote q ON u.id = q.clientID " +
                     "LEFT JOIN " +
                     "    QuoteMessages qm ON q.quoteID = qm.quoteID " +
                     "WHERE " +
                     "    q.status = 'Confirmed' " +
                     "GROUP BY " +
                     "    u.id " +
                     "HAVING " +
                     "    COUNT(qm.quoteID) = 0;";

        connect_func();
        try (Statement statement = connect.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                EasyClient easyClient = new EasyClient(
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getInt("clientID")
                );
                easyClients.add(easyClient);
            }
        } finally {
            disconnect();
        }
        return easyClients;
    }

    public List<OneTreeQuote> listOneTreeQuotes() throws SQLException {
        List<OneTreeQuote> oneTreeQuotes = new ArrayList<>();
        String sql = "SELECT " +
                     "    q.quoteID, " +
                     "    q.initialPrice, " +
                     "    q.scheduleStart, " +
                     "    q.scheduleEnd, " +
                     "    q.status " +
                     "FROM " +
                     "    Quote q " +
                     "JOIN " +
                     "    (SELECT quoteID, SUM(treeNo) as totalTrees FROM Tree GROUP BY quoteID) t ON q.quoteID = t.quoteID " +
                     "WHERE " +
                     "    q.status = 'Confirmed' AND t.totalTrees = 1.0;";

        connect_func();
        try (Statement statement = connect.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                OneTreeQuote oneTreeQuote = new OneTreeQuote(
                    resultSet.getInt("quoteID"),
                    resultSet.getDouble("initialPrice"),
                    resultSet.getDate("scheduleStart"),
                    resultSet.getDate("scheduleEnd"),
                    resultSet.getString("status")
                );
                oneTreeQuotes.add(oneTreeQuote);
            }
        } finally {
            disconnect();
        }
        return oneTreeQuotes;
    }

    public List<ProspectiveClient> listProspectiveClients() throws SQLException {
        List<ProspectiveClient> prospectiveClients = new ArrayList<>();
        String sql = "SELECT u.id, u.firstName, u.lastName " +
                     "FROM User u " +
                     "WHERE NOT EXISTS (" +
                     "    SELECT 1 FROM Quote q WHERE q.clientID = u.id AND q.status <> 'Pending'" +
                     ") AND EXISTS (" +
                     "    SELECT 1 FROM Quote q WHERE q.clientID = u.id AND q.status = 'Pending'" +
                     ");";

        connect_func();
        try (Statement statement = connect.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                ProspectiveClient client = new ProspectiveClient(
                    resultSet.getInt("id"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName")
                );
                prospectiveClients.add(client);
            }
        } finally {
            disconnect();
        }
        return prospectiveClients;
    }


    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = {"drop database if exists testdb; ",
					        "create database testdb; ",
					        "use testdb; ",
					        "drop table if exists Tree;",
					        "drop table if exists Quote;",
					        "drop table if exists User; ",
					        "drop table if exists QuoteMessages;",
					        "drop table if exists Orders;",
					        "drop table if exists Bills;",
					        "drop table if exists BillMessages;",
					        "CREATE TABLE if not exists User( " + 
					        	"id INT AUTO_INCREMENT UNIQUE, " +
					            "email VARCHAR(50) NOT NULL, " + 
					            "firstName VARCHAR(10) NOT NULL, " +
					            "lastName VARCHAR(10) NOT NULL, " +
					            "password VARCHAR(20) NOT NULL, " +
					            "creditCardNumber VARCHAR(20) NOT NULL, " +
					            "phoneNumber VARCHAR(20) NOT NULL, " +
					            "role VARCHAR(20) NOT NULL, " +
					            "adress_street_num VARCHAR(4) , "+ 
					            "adress_street VARCHAR(30) , "+ 
					            "adress_city VARCHAR(20)," + 
					            "adress_state VARCHAR(2),"+ 
					            "adress_zip_code VARCHAR(5),"+ 
					            "UNIQUE(email),"+
					            "PRIMARY KEY (id) "+
					        "); ",
					        "CREATE TABLE if not exists Quote(" +
					        	"quoteID INT AUTO_INCREMENT PRIMARY KEY, " +
					            "initialPrice DOUBLE, " +
					            "scheduleStart DATE, "+
					            "scheduleEnd DATE, "+
					            "status VARCHAR(50), " +
					            "clientID INT, " +
					            "contractorID INT, "+
					            "FOREIGN KEY (contractorID) REFERENCES User(id),"+
					            "FOREIGN KEY (clientID) REFERENCES User(id) " +
					         ");",
					         "CREATE TABLE if not exists Tree( " +
					            "treeID INT AUTO_INCREMENT PRIMARY KEY, " +
					            "size DOUBLE, " +
					            "height DOUBLE, " +
					            "location VARCHAR(255), " +
					            "proximityToHouse DOUBLE, " +
					            "treeNo DOUBLE, "+
					            "clientID INT, " +
					            "quoteID INT," +
					            "FOREIGN KEY (quoteID) REFERENCES Quote(quoteID)," +
					            "FOREIGN KEY (clientID) REFERENCES User(id)" +
					         ");",
					         "CREATE TABLE if not exists QuoteMessages( "+
					          	"quoteMessageID INT AUTO_INCREMENT PRIMARY KEY, "+
					        	"userID INTEGER, "+
					          	"quoteID INTEGER, "+
					        	"msgTime DATETIME, "+	
					        	"note VARCHAR(200), "+
					          	"FOREIGN KEY(userID) REFERENCES User(id),"+
					        	"FOREIGN KEY(quoteID) REFERENCES Quote(quoteID)"+
					         ");",
					         "CREATE TABLE if not exists Orders( "+
					            "orderID INT AUTO_INCREMENT PRIMARY KEY, "+
					        	"quoteID INTEGER, "+
					            "clientID INTEGER, "+
					            "price DOUBLE, "+
					        	"scheduleStart DATE, "+
					            "scheduleEnd DATE, "+
					        	"status VARCHAR(50), "+
					            "FOREIGN KEY(clientID) REFERENCES User(id), "+
					            "FOREIGN KEY(quoteID) REFERENCES Quote(quoteID)"+
					         ");",
					         "CREATE TABLE if not exists Bills( "+
					            "billID INT AUTO_INCREMENT PRIMARY KEY, "+
					        	"orderID INTEGER, "+
					            "clientID INTEGER, "+
					            "price DOUBLE, "+
					        	"dateGenerated DATE, "+
					            "datePaid DATE, "+
					        	"status VARCHAR(20), "+
					            "FOREIGN KEY(clientID) REFERENCES User(id), "+
					            "FOREIGN KEY(orderID) REFERENCES Orders(orderID)"+
					         ");",
					         "CREATE TABLE if not exists BillMessages( "+
					            "billMessageID INT AUTO_INCREMENT PRIMARY KEY, "+
					            "clientID INTEGER, "+
					            "billID INTEGER, "+
					            "msgTime DATETIME, "+
					            "price DOUBLE, "+
					            "note VARCHAR(200), "+
					            "FOREIGN KEY(clientID) REFERENCES User(id), "+
					            "FOREIGN KEY(billID) REFERENCES Bills(billID)"+
					         ");",
					            
					          	
					          
        					};
        String[] TUPLES = {("insert into User(id, email, firstName, lastName, password, creditCardNumber, phoneNumber, role, adress_street_num, adress_street, adress_city, adress_state, adress_zip_code)"+
        			"values (id,'root', 'default', 'default','pass1234', '1234-3456-4567-5678','123-234-4567', 'root', '0000', 'Default', 'Default', '0', '00000'),"+
        					"(id,'davidsmith@gmail.com', 'David', 'Smith','david1234', '0000-0000-0000-0000','000-000-0000', 'David Smith', '0000', 'Default', 'Default', '0', '00000')," +
			    		 	"(id,'don@gmail.com', 'Don', 'Cummings','don1234', '4972-9215-7861-2529','453-921-1233', 'Client', '1000', 'hi street', 'mama', 'MO', '12345'),"+
			    	 	 	"(id,'margarita@gmail.com', 'Margarita', 'Lawson','margarita1234', '0551-1797-8053-2646','281-966-2201', 'Client', '1234', 'ivan street', 'tata','CO','12561'),"+
			    		 	"(id,'jo@gmail.com', 'Jo', 'Brady','jo1234', '1558-7516-4276-7312','121-717-1085', 'Client', '3214','marko street', 'brat', 'DU', '54321'),"+
			    		 	"(id,'wallace@gmail.com', 'Wallace', 'Moore','wallace1234', '7798-1489-2513-1024','859-738-0661', 'Client', '4500', 'frey street', 'sestra', 'MI', '48202'),"+
			    		 	"(id,'amelia@gmail.com', 'Amelia', 'Phillips','amelia1234', '3939-6766-6025-7909','157-417-5433', 'Client', '1245', 'm8s street', 'baka', 'IL', '48000'),"+
			    			"(id,'sophie@gmail.com', 'Sophie', 'Pierce','sophie1234', '2821-0436-9879-0137','814-722-1464', 'Client', '2468', 'yolos street', 'ides', 'CM', '24680'),"+
			    			"(id,'angelo@gmail.com', 'Angelo', 'Francis','angelo1234', '8275-0022-7017-0368','767-805-6058', 'Client', '4680', 'egypt street', 'lolas', 'DT', '13579'),"+
			    			"(id,'rudy@gmail.com', 'Rudy', 'Smith','rudy1234', '0860-6191-8026-5295','408-200-2252', 'Client', '1234', 'sign street', 'samo ne tu','MH', '09876'),"+
			    			"(id,'jeannette@gmail.com', 'Jeannette ', 'Stone','jeannette1234', '7738-9880-5284-3227','606-059-0950', 'Client', '0981', 'snoop street', 'kojik', 'HW', '87654'),"+
			    			"(id, 'susie@gmail.com', 'Susie ', 'Guzman', 'susie1234', '2038-7418-0996-1404','215-638-3029', 'Client', '1234', 'whatever street', 'detroit', 'MI', '48202');"),
			        		("INSERT INTO Quote (initialPrice, scheduleStart, scheduleEnd, status, clientID, contractorID) VALUES" +
			    		 	"(200.50, '2023-11-15', '2023-11-16', 'Pending', 3, 1),"+
			    		 	"(150.75, '2023-11-20', '2023-11-21', 'Confirmed', 3, 1),"+
			    		 	"(300.00, '2023-11-25', '2023-11-26', 'Pending', 3, 1),"+
			    		 	"(175.25, '2023-12-01', '2023-12-02', 'Confirmed', 4, 1),"+
			    		 	"(220.40, '2023-12-05', '2023-12-06', 'Pending', 5, 1),"+
			    		 	"(180.90, '2023-12-10', '2023-12-11', 'Confirmed', 6, 1),"+
			    		 	"(250.30, '2023-12-15', '2023-12-16', 'Pending', 7, 1),"+
			    		 	"(160.00, '2023-12-20', '2023-12-21', 'Confirmed', 8, 1),"+
			    		 	"(190.85, '2023-12-25', '2023-12-26', 'Pending', 9, 1),"+
			    		 	"(210.60, '2023-12-30', '2023-12-31', 'Confirmed', 10, 1);"),
        		 			("INSERT INTO Tree (size, height, location, proximityToHouse, treeNo, clientID, quoteID) VALUES" +
        				    "(5.5, 20, '123 Oak Street', 10, 2, 3, 1)," +
        				    "(3.2, 15, '456 Pine Lane', 15, 2, 3, 2)," +
        				    "(6.7, 25, '789 Maple Ave', 5, 1, 3, 3)," +
        				    "(4.5, 18, '321 Birch Road', 8, 1, 4, NULL)," +
        				    "(2.8, 10, '654 Cedar Blvd', 12, 4, 5, NULL)," +
        				    "(5.0, 22, '987 Elm Street', 7, 5, 6, NULL)," +
        				    "(3.6, 16, '123 Spruce Lane', 11, 3, 7, NULL)," +
        				    "(4.8, 20, '456 Fir Avenue', 9, 1, 8, NULL)," +
        				    "(7.1, 28, '789 Redwood Drive', 4, 1, 9, NULL)," +
        				    "(3.0, 14, '321 Willow Way', 13, 2, 10, NULL);"),
        		 			("INSERT INTO QuoteMessages (userID, quoteID, msgTime, note) VALUES" +
        		 			"(3, 1, '2023-11-12 10:00:00', 'First message note')," +
        		 			"(3, 2, '2023-11-13 11:00:00', 'Second message note')," +
        		 			"(3, 3, '2023-11-14 12:00:00', 'Third message note');"),        		 			
        		 			("INSERT INTO Orders (quoteID, clientID, price, scheduleStart, scheduleEnd, status) VALUES" +
        		 			"(1, 3, 200.50, '2023-11-15', '2023-11-16', 'In Progress')," +
        		 			"(2, 3, 150.75, '2023-11-20', '2023-11-21', 'In Progress')," +
        		 			"(3, 3, 300.00, '2023-11-25', '2023-11-26', 'In Progress')," +
        		 			"(4, 4, 175.25, '2023-12-01', '2023-12-02', 'In Progress')," +
        		 			"(5, 5, 220.40, '2023-12-05', '2023-12-06', 'In Progress')," +
        		 			"(6, 6, 180.90, '2023-12-10', '2023-12-11', 'In Progress')," +
        		 			"(7, 7, 250.30, '2023-12-15', '2023-12-16', 'In Progress')," +
        		 			"(8, 8, 160.00, '2023-12-20', '2023-12-21', 'In Progress')," +
        		 			"(9, 9, 190.85, '2023-12-25', '2023-12-26', 'In Progress')," +
        		 			"(10, 10, 210.60, '2023-12-30', '2023-12-31', 'In Progress');"),       		 			
        		 			("INSERT INTO Bills (orderID, clientID, price, dateGenerated, datePaid, status) VALUES" +
        		 			"(1, 3, 200.50, '2023-09-10', NULL, 'Unpaid')," +
        		 			"(2, 3, 150.75, '2023-10-10', NULL, 'Unpaid')," +
        		 			"(3, 3, 300.00, '2023-09-28', NULL, 'Unpaid')," +
        		 			"(4, 4, 175.25, '2023-06-15', '2023-06-16', 'Paid')," +
        		 			"(5, 5, 220.40, '2023-07-10', NULL, 'Unpaid')," +
        		 			"(6, 6, 180.90, '2023-05-12', '2023-05-13', 'Paid')," +
        		 			"(7, 7, 250.30, '2023-11-15', NULL, 'Unpaid')," +
        		 			"(8, 8, 160.00, '2023-10-13', NULL, 'Unpaid')," +
        		 			"(9, 9, 190.85, '2023-11-22', '2023-12-11', 'Paid')," +
        		 			"(10, 10, 210.60, '2023-01-05', NULL, 'Unpaid');"),        		 			
        		 			("INSERT INTO BillMessages (clientID, billID, msgTime, price, note) VALUES" +
        		 			"(3, 1, '2023-11-12 10:30:00', 200.50, 'First bill message')," +
        		 			"(3, 2, '2023-11-13 11:30:00', 150.75, 'Second bill message')," +
        		 			"(3, 3, '2023-11-14 12:30:00', 300.00, 'Third bill message')," +
        		 			"(4, 4, '2023-11-15 13:30:00', 175.25, 'Fourth bill message')," +
        		 			"(5, 5, '2023-11-16 14:30:00', 220.40, 'Fifth bill message')," +
        		 			"(6, 6, '2023-11-17 15:30:00', 180.90, 'Sixth bill message')," +
        		 			"(7, 7, '2023-11-18 16:30:00', 250.30, 'Seventh bill message')," +
        		 			"(8, 8, '2023-11-19 17:30:00', 160.00, 'Eighth bill message')," +
        		 			"(9, 9, '2023-11-20 18:30:00', 190.85, 'Ninth bill message')," +
        		 			"(10, 10, '2023-11-21 19:30:00', 210.60, 'Tenth bill message');"),

			    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        disconnect();
    }
    
    
   
    
    
    
    
    
	
	

}
