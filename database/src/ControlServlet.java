import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.regex.Pattern;
import java.time.LocalDateTime;


public class ControlServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private userDAO userDAO = new userDAO();
	    private String currentUser;
	    private HttpSession session=null;
	    
	    public ControlServlet()
	    {
	    	
	    }
	    
	    public void init()
	    {
	    	userDAO = new userDAO();
	    	currentUser= "";
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getServletPath();
	        System.out.println(action);
	    
	    try {
        	switch(action) {  
        	case "/login":
        		login(request,response);
        		break;
        	case "/register":
        		register(request, response);
        		break;
        	case "/initialQuote":
        		initialQuote(request, response);
        		break;
        	case "/initialRequest":
        		initialRequest(request, response);
        		break;
        	case "/respondQuote":
                respondToQuote(request, response);
                break;
        	case "/rejectTree":
        	    rejectTree(request, response);
        	    break;     
        	case "/initialize":
        		userDAO.init();
        		System.out.println("Database successfully initialized!");
        		rootPage(request,response,"");
        		break;
        	case "/root":
        		rootPage(request,response, "");
        		break;
        	case "/davidSmith":
        		davidSmithDashBoard(request, response, "");
        		break;
        	case "/updateQuote":
                updateQuoteDetails(request, response);
                break;
        	case "/logout":
        		logout(request,response);
        		break;
        	case "/list": 
                System.out.println("The action is: list");
                listUser(request, response);           	
                break;
        	case "/listTree":
        		System.out.println("The action is: listTree");
        		listTree(request, response);
        		break;
        	case "/listQuote":
        		System.out.println("The action is: listQuote");
        		listQuote(request, response);
        		break;
        	case "/listQuoteMessages":
        		System.out.println("The action is: listQuoteMessages");
        		listQuoteMessages(request, response);
        		break;
        	case "/listOrder":
        		System.out.println("The action is: listOrder");
        		listOrders(request, response);
        		break;
        	case "/listBills":
        		System.out.println("The action is: listBills");
        		listBills(request, response);
        		break;
        	case "/listBillMessages":
        		System.out.println("The action is: listBillMessages");
        		listBillMessages(request, response);
        		break;
        	case "/agreeToQuote":
                agreeToQuote(request, response);
                break;
        	case "/finishOrder":
        	    finishOrder(request, response);
        	    break;
        	case "/payBill":
        	    payBill(request, response);
        	    break;
        	case "/negotiateBill":
        		negotiateBill(request, response);
	    	}
	    }
	    catch(Exception ex) {
        	System.out.println(ex.getMessage());
	    	}
	    }
        	
	    private void listUser(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listUser started: 00000000000000000000000000000000000");

	     
	        List<user> listUser = userDAO.listAllUsers();
	        request.setAttribute("listUser", listUser);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listPeople finished: 111111111111111111111111111111111111");
	    }
	    
	    private void listTree(HttpServletRequest request, HttpServletResponse response)
	    		throws SQLException, IOException, ServletException {
	    	System.out.println("listTree started: 00000000000000000000000000000000000");
	    	
	    	List<Tree> listTree = userDAO.listAllTrees();
	    	request.setAttribute("listTree", listTree);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("rootView.jsp");
	    	dispatcher.forward(request, response);
	    	
	    	System.out.println("listTree finished: 111111111111111111111111111111111111");
	    }
	    
	    private void listQuote(HttpServletRequest request, HttpServletResponse response)
	    		throws SQLException, IOException, ServletException {
	    	System.out.println("listQuote started: 00000000000000000000000000000000000");
	    	
	    	List<Quote> listQuote = userDAO.listAllQuotes();
	    	request.setAttribute("listQuote", listQuote);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("rootView.jsp");
	    	dispatcher.forward(request, response);
	    	
	    	System.out.println("listQuote finished: 111111111111111111111111111111111111");
	    }
	    
	    private void listQuoteMessages(HttpServletRequest request, HttpServletResponse response)
	    		throws SQLException, IOException, ServletException {
	    	System.out.println("listQuoteMessages started: 00000000000000000000000000000000000");
	    	
	    	List<QuoteMessages> listQuoteMessages = userDAO.listAllQuoteMessages();
	    	request.setAttribute("listQuoteMessages", listQuoteMessages);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("rootView.jsp");
	    	dispatcher.forward(request, response);
	    	
	    	System.out.println("listQuoteMessages finished: 111111111111111111111111111111111111");
	    }
	    
	    private void listOrders(HttpServletRequest request, HttpServletResponse response)
	    		throws SQLException, IOException, ServletException {
	    	System.out.println("listOrders started: 00000000000000000000000000000000000");
	    	
	    	List<Order> listOrders = userDAO.listAllOrders();
	    	request.setAttribute("listOrders", listOrders);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("rootView.jsp");
	    	dispatcher.forward(request, response);
	    	
	    	System.out.println("listOrders finished: 111111111111111111111111111111111111");
	    }
	    
	    private void listBills(HttpServletRequest request, HttpServletResponse response)
	    		throws SQLException, IOException, ServletException {
	    	System.out.println("listBills started: 00000000000000000000000000000000000");
	    	
	    	List<Bills> listBills = userDAO.listAllBills();
	    	request.setAttribute("listBills", listBills);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("rootView.jsp");
	    	dispatcher.forward(request, response);
	    	
	    	System.out.println("listBills finished: 111111111111111111111111111111111111");
	    }
	    
	    private void listBillMessages(HttpServletRequest request, HttpServletResponse response)
	    		throws SQLException, IOException, ServletException {
	    	System.out.println("listBillMessages started: 00000000000000000000000000000000000");
	    	
	    	List<BillMessages> listBillMessages = userDAO.listAllBillMessages();
	    	request.setAttribute("listBillMessages", listBillMessages);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("rootView.jsp");
	    	dispatcher.forward(request, response);
	    	
	    	System.out.println("listBills finished: 111111111111111111111111111111111111");
	    }
	    
	    private void rootPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("root view");
			request.setAttribute("listUser", userDAO.listAllUsers());
			request.setAttribute("listTree", userDAO.listAllTrees());
			request.setAttribute("listQuote", userDAO.listAllQuotes());
			request.setAttribute("listQuoteMessages", userDAO.listAllQuoteMessages());
			request.setAttribute("listOrders", userDAO.listAllOrders());
			request.setAttribute("listBills", userDAO.listAllBills());
			request.setAttribute("listBillMessages", userDAO.listAllBillMessages());
		    request.setAttribute("listBigClients", userDAO.listBigClients());
		    request.setAttribute("listEasyClients", userDAO.listEasyClients());
		    request.setAttribute("listOneTreeQuotes", userDAO.listOneTreeQuotes());
		    request.setAttribute("listProspectiveClients", userDAO.listProspectiveClients());
		    request.setAttribute("listHighestTrees", userDAO.listHighestTrees());
		    request.setAttribute("listOverdueBills", userDAO.listOverdueBills());
		    request.setAttribute("listBadClients", userDAO.listBadClients());
		    request.setAttribute("listGoodClients", userDAO.listGoodClients());
		    request.setAttribute("listClientStatistics", userDAO.listClientStatistics());
	    	request.getRequestDispatcher("rootView.jsp").forward(request, response);
	    }
	    
	    private void davidSmithDashBoard(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	List<Tree> listTreeWithoutQuote = userDAO.listTreesWithoutQuote();
	        request.setAttribute("listTreeWithoutQuote", listTreeWithoutQuote);
	        List<QuoteMessageWithPrice>listAllQuoteMessagesWithPrice = userDAO.listAllQuoteMessagesWithPrice();
	        request.setAttribute("listAllQuoteMessagesWithPrice", listAllQuoteMessagesWithPrice);
	        request.setAttribute("listAllOrders", userDAO.listAllOrders());
	        RevenueStatistics revenueStats = userDAO.getRevenueStatistics();
	        request.setAttribute("listAllBills", userDAO.listAllBills());
	        request.setAttribute("revenueStats", revenueStats);
	    	request.getRequestDispatcher("davidSmithDashboard.jsp").forward(request, response);
	    }
	    
	    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	 String email = request.getParameter("email");
	    	 String password = request.getParameter("password");
	    	 
	    	 if (email.equals("root") && password.equals("pass1234")) {
				 System.out.println("Login Successful! Redirecting to root");
				 session = request.getSession();
				 session.setAttribute("username", email);
				 rootPage(request, response, "");
	    	 }
	    	 
	    	 else if(email.equals("davidsmith@gmail.com") && password.equals("david1234"))
	    	 {
	    		 System.out.println("Welcome David Smith! Redirecing to your dashboard");
	    		 session = request.getSession();
	    		 session.setAttribute("username", email);
	    		 davidSmithDashBoard(request, response, "");
	    	 }
	    	 
	    	 else if(userDAO.isValid(email, password)) 
	    	 {
	    		 String clientID = userDAO.getUserIDByEmail(email);
	    	        HttpSession session = request.getSession();
	    	        session.setAttribute("username", email); 
	    	        session.setAttribute("userID", clientID);

	    	        userDAO userDAO = new userDAO();
	    	        List<Quote> userQuotes = userDAO.getQuotesByClientID(clientID);
	    	        session.setAttribute("userQuotes", userQuotes);
	    	        
	    	        List<Bills> userBills = userDAO.getBillsByClientID(clientID); // Assuming you have such a method
	    	        session.setAttribute("userBills", userBills);

	    	        RequestDispatcher dispatcher = request.getRequestDispatcher("activitypage.jsp");
	    	        dispatcher.forward(request, response);
	    	    } else {
	    	        request.setAttribute("loginFailedStr", "Login Failed: Wrong password or username.");
	    	        request.getRequestDispatcher("login.jsp").forward(request, response);
	    	    }
	    }
	           
	    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String id = String.valueOf(request.getParameter("id"));
	    	String email = request.getParameter("email");
	   	 	String firstName = request.getParameter("firstName");
	   	 	String lastName = request.getParameter("lastName");
	   	 	String password = request.getParameter("password");
	   	 	String creditCardNumber = request.getParameter("creditCardNumber");
	   	    String phoneNumber = request.getParameter("phoneNumber");
	   	    String role = request.getParameter("role");
	   	 	String adress_street_num = request.getParameter("adress_street_num"); 
	   	 	String adress_street = request.getParameter("adress_street"); 
	   	 	String adress_city = request.getParameter("adress_city"); 
	   	 	String adress_state = request.getParameter("adress_state"); 
	   	 	String adress_zip_code = request.getParameter("adress_zip_code"); 	 
	   	 	String confirm = request.getParameter("confirmation");
	   	 	
	   	 	Pattern namePattern = Pattern.compile("^[a-zA-Z]+$");
			Pattern emailPattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
			Pattern phonePattern = Pattern.compile("^\\d{3}-\\d{3}-\\d{4}$");
			Pattern creditCardPattern = Pattern.compile("^\\d{4}-\\d{4}-\\d{4}-\\d{4}$");
	 
			if (!namePattern.matcher(firstName).matches() || !namePattern.matcher(lastName).matches()) {
				request.setAttribute("errorOne", "Names can only contain letters.");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
	 
			if (!emailPattern.matcher(email).matches()) {
				request.setAttribute("errorOne", "Invalid email format.");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
	 
			if (!phonePattern.matcher(phoneNumber).matches()) {
				request.setAttribute("errorOne", "Phone number format should be XXX-XXX-XXXX.");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
	 
			if (!creditCardPattern.matcher(creditCardNumber).matches()) {
				request.setAttribute("errorOne", "Credit card format should be XXXX-XXXX-XXXX-XXXX.");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
	   	 	
	   	 	if (password.equals(confirm)) {
		   	 	if (userDAO.checkPhoneNumber(phoneNumber)) {
			   	     request.setAttribute("errorOne", "Phone number already in use.");
			   	     request.getRequestDispatcher("register.jsp").forward(request, response);
			   	     return;
			   	 }
	   	 		if (!userDAO.checkEmail(email)) {
		   	 		System.out.println("Registration Successful! Added to database");
		            user users = new user(id, email,firstName, lastName, password, creditCardNumber, phoneNumber, role, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code);
		   	 		userDAO.insert(users);
		   	 		response.sendRedirect("login.jsp");
	   	 		}
		   	 	else {
		   	 		System.out.println("Username taken, please enter new username");
		    		 request.setAttribute("errorOne","Registration failed: Username taken, please enter a new username.");
		    		 request.getRequestDispatcher("register.jsp").forward(request, response);
		   	 	}
	   	 	}
	   	 	else {
	   	 		System.out.println("Password and Password Confirmation do not match");
	   		 request.setAttribute("errorTwo","Registration failed: Password and Password Confirmation do not match.");
	   		 request.getRequestDispatcher("register.jsp").forward(request, response);
	   	 	}
	    }   
	    
	    private void initialQuote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String initialPrice = request.getParameter("initialPrice");
	    	String scheduleStart = request.getParameter("scheduleStart");
	    	String scheduleEnd = request.getParameter("scheduleEnd");
	    	String treeID = request.getParameter("treeID");
	    	if (treeID == null || treeID.isEmpty()) {
	    	    request.setAttribute("errorMessage", "Tree ID is required.");
	    	    request.getRequestDispatcher("processQuote.jsp").forward(request, response);
	    	    return;
	    	    }
	        String status = request.getParameter("status");
	        if (status == null || status.isEmpty()) {
	            status = "Pending";
	        }

	        String clientID = request.getParameter("clientID");
	        if (clientID == null || clientID.isEmpty()) {
	            clientID = null;
	        }

	        String contractorID = request.getParameter("contractorID");
	        if (contractorID == null || contractorID.isEmpty()) {
	            contractorID = "1";
	        }
	        
	        if (initialPrice == null || initialPrice.isEmpty() ||
	        		scheduleStart == null || scheduleStart.isEmpty() ||
	        				scheduleEnd == null || scheduleEnd.isEmpty() ||
	        		        	clientID == null || clientID.isEmpty()) {

		            request.setAttribute("errorMessage", "All fields are required.");
		            request.getRequestDispatcher("processQuote.jsp").forward(request, response);
		        } else {
		            Quote quote = new Quote(initialPrice, scheduleStart, scheduleEnd, status, clientID, contractorID);
		            userDAO.insertQuote(quote, treeID);
		            
		            request.getSession().setAttribute("successMessage", "Request Submitted Successfully. Please wait for a response.");
		            davidSmithDashBoard(request, response, "");
		            response.sendRedirect("davidSmithDashboard.jsp");
		        }
	    }
	    
	    private void initialRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException, SQLException {

	        String size = request.getParameter("size");
	        String height = request.getParameter("height");
	        String location = request.getParameter("location");
	        String proximityToHouse = request.getParameter("proximityToHouse");
	        String treeNo = request.getParameter("treeNo");
	        String clientID = request.getParameter("clientID");
	        String quoteID = request.getParameter("quoteID");


	        if (size == null || size.isEmpty() ||
	            height == null || height.isEmpty() ||
	            location == null || location.isEmpty() ||
	            proximityToHouse == null || proximityToHouse.isEmpty() ||
	            clientID == null || clientID.isEmpty()) {


	            request.setAttribute("errorMessage", "All fields are required.");
	            request.getRequestDispatcher("activitypage.jsp").forward(request, response);
	        } else {
	            Tree tree = new Tree(size, height, location, proximityToHouse, treeNo,  clientID, quoteID);
	            userDAO.insertTree(tree);
	            
	            request.getSession().setAttribute("successMessage", "Request Submitted Successfully. Please wait for a response.");
	            response.sendRedirect("activitypage.jsp");
	        }
	    }


	    private void respondToQuote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String quoteID = request.getParameter("quoteID");
	        String responseAction = request.getParameter("response");
	        String userID = (String) request.getSession().getAttribute("userID");
	    
	        
	        userDAO dao = new userDAO();

	        try {
	            if (quoteID == null || quoteID.isEmpty() || responseAction == null || responseAction.isEmpty()) {
	                request.setAttribute("errorMessage", "Quote ID and Response Action are required.");
	                request.getRequestDispatcher("RespondQuote.jsp").forward(request, response);
	                return;
	            }

	            if ("decline".equals(responseAction)) {
	                userDAO.deleteQuote(quoteID);
	            } else if ("negotiate".equals(responseAction)) {
	                String note = request.getParameter("note");
	                if (note == null || note.isEmpty()) {
	                    request.setAttribute("errorMessage", "Note is required for negotiation.");
	                    request.getRequestDispatcher("RespondQuote.jsp").forward(request, response);
	                    return;
	                }
	                String msgTime = LocalDateTime.now().toString();
	                QuoteMessages quoteMessage = new QuoteMessages(userID, quoteID, msgTime, note);
	                userDAO.insertQuoteMessage(quoteMessage);
	            }

	            HttpSession session = request.getSession();
	            String clientID = (String) session.getAttribute("userID");
	            List<Quote> updatedQuotes = dao.getQuotesByClientID(clientID);

	            session.setAttribute("userQuotes", updatedQuotes);
	            response.sendRedirect("activitypage.jsp"); 
	        } catch (SQLException e) {
	            request.setAttribute("errorMessage", "Database access error: " + e.getMessage());
	            request.getRequestDispatcher("RespondQuote.jsp").forward(request, response);
	        }
	    }

	    private void updateQuoteDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String quoteID = request.getParameter("quoteID");
	        String newPrice = request.getParameter("price");
	        String newScheduleStart = request.getParameter("scheduleStart");
	        String newScheduleEnd = request.getParameter("scheduleEnd");

	        try {
	            userDAO.updateQuoteDetails(quoteID, newPrice, newScheduleStart, newScheduleEnd);
	            request.setAttribute("message", "Quote updated successfully");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("davidSmithDashboard.jsp");
	            davidSmithDashBoard(request, response, "");
	            dispatcher.forward(request, response);
	        } catch (SQLException e) {
	            throw new ServletException("Database access error: " + e.getMessage(), e);
	        }
	    }
	    
	    private void agreeToQuote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String quoteID = request.getParameter("quoteID");
	        String clientID = request.getParameter("clientID");
	        String price = request.getParameter("initialPrice");
	        String scheduleStart = request.getParameter("scheduleStart");
	        String scheduleEnd = request.getParameter("scheduleEnd");

	        userDAO dao = new userDAO();
	        try {
	            dao.agreeToQuote(quoteID, clientID, price, scheduleStart, scheduleEnd);

	            HttpSession session = request.getSession();
	            String userID = (String) session.getAttribute("userID");
	            List<Quote> updatedQuotes = dao.getQuotesByClientID(userID);

	            session.setAttribute("userQuotes", updatedQuotes);
	            response.sendRedirect("activitypage.jsp"); 
	        } catch (SQLException e) {
	            throw new ServletException("Database error: " + e.getMessage());
	        }
	    }
	    
	    private void finishOrder(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException, SQLException {
	        String orderID = request.getParameter("orderID");
	        String clientID = request.getParameter("clientID");
	        String price = request.getParameter("price");

	        userDAO.createBill(orderID, clientID, price, new java.sql.Date(System.currentTimeMillis()));
	        userDAO.updateOrderStatus(orderID, "Finished");
	        
	        List<Tree> listTreeWithoutQuote = userDAO.listTreesWithoutQuote();
	        List<QuoteMessageWithPrice> listAllQuoteMessagesWithPrice = userDAO.listAllQuoteMessagesWithPrice();
	        List<Order> listAllOrders = userDAO.listAllOrders();

	        HttpSession session = request.getSession();
	        session.setAttribute("listTreeWithoutQuote", listTreeWithoutQuote);
	        session.setAttribute("listAllQuoteMessagesWithPrice", listAllQuoteMessagesWithPrice);
	        session.setAttribute("listAllOrders", listAllOrders);

	        response.sendRedirect("davidSmithDashboard.jsp");
	    }
	    
	    
	    private void rejectTree(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException, SQLException {
	        String treeID = request.getParameter("treeID");

	        userDAO.deleteTree(treeID);

	        List<Tree> listTreeWithoutQuote = userDAO.listTreesWithoutQuote();
	        List<QuoteMessageWithPrice> listAllQuoteMessagesWithPrice = userDAO.listAllQuoteMessagesWithPrice();
	        List<Order> listAllOrders = userDAO.listAllOrders();
	        HttpSession session = request.getSession();
	        session.setAttribute("listTreeWithoutQuote", listTreeWithoutQuote);
	        session.setAttribute("listAllQuoteMessagesWithPrice", listAllQuoteMessagesWithPrice);
	        session.setAttribute("listAllOrders", listAllOrders);

	        response.sendRedirect("davidSmithDashboard.jsp");
	    }
	    
	    private void payBill(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException, SQLException {
	        String billID = request.getParameter("billID");
	        String clientID = (String) request.getSession().getAttribute("userID");

	        userDAO.payBill(billID, new java.sql.Date(System.currentTimeMillis()));
	        
	        List<Bills> userBills = userDAO.getBillsByClientID(clientID);
	        HttpSession session = request.getSession();
	        session.setAttribute("userBills", userBills);

	        response.sendRedirect("activitypage.jsp"); 
	        
	    }
	    
	    private void negotiateBill(HttpServletRequest request, HttpServletResponse response) 
	            throws SQLException, IOException, ServletException {
	        String billID = request.getParameter("billID");
	        String clientID = request.getParameter("clientID");
	        String price = request.getParameter("price");
	        String note = request.getParameter("note");

	        userDAO dao = new userDAO();
	        dao.insertBillMessage(new BillMessages(clientID, billID, LocalDateTime.now().toString(), price, note));

	        // Redirect to the appropriate page or show confirmation
	        // For example, redirect back to the activity page
	        response.sendRedirect("activitypage.jsp"); // or any other relevant JSP page
	    }
	    
	    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	currentUser = "";
        		response.sendRedirect("login.jsp");
        	}
	    
	    
}
	        
	        
	    
	        
	        
	        
	    


