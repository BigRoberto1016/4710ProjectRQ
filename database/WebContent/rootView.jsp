<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Root page</title>
</head>
<body>

<div align = "center">
	
	<form action = "initialize">
		<input type = "submit" value = "Initialize the Database"/>
	</form>
	<a href="login.jsp"target ="_self" > logout</a><br><br> 

<h1>List all users</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Users</h2></caption>
            <tr>
            	<th>ID</th>
                <th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Credit Card Number</th>
                <th>Phone Number</th>
                <th>Role</th>
                <th>Address</th>
                <th>Password</th>
            </tr> 
            <c:forEach var="users" items="${listUser}">
                <tr style="text-align:center">
                	<td><c:out value="${users.id}" /></td>
                    <td><c:out value="${users.email}" /></td>
                    <td><c:out value="${users.firstName}" /></td>
                    <td><c:out value="${users.lastName}" /></td>
                    <td><c:out value="${users.creditCardNumber}" /></td>
			        <td><c:out value="${users.phoneNumber}" /></td>
			        <td><c:out value="${users.role}" /></td>
                    <td><c:out value="${users.adress_street_num} ${users.adress_street} ${users.adress_city} ${users.adress_state} ${users.adress_zip_code}" /></td>
                    <td><c:out value="${users.password}" /></td>

            </c:forEach>
        </table>
	</div>
	
<h1>List all trees</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Trees</h2></caption>
            <tr>
                <th>Tree ID</th>
                <th>Size</th>
                <th>Height</th>
                <th>Location</th>
                <th>Distance to House</th>
                <th>Number of Trees</th>
                <th>Client ID</th>
                <th>Quote ID</th>
            </tr>
            <c:forEach var="tree" items="${listTree}">
                <tr style="text-align:center">
                    <td><c:out value="${tree.treeID}" /></td>
                    <td><c:out value="${tree.size}" /></td>
                    <td><c:out value="${tree.height}" /></td>
                    <td><c:out value="${tree.location}" /></td>
                    <td><c:out value="${tree.proximityToHouse}" /></td>
                    <td><c:out value="${tree.treeNo }"/></td>
                    <td><c:out value="${tree.clientID}" /></td>
                    <td><c:out value="${tree.quoteID}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    
<h1>List all quotes</h1>
    <!-- Quote Table -->
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Quotes</h2></caption>
            <tr>
                <th>Quote ID</th>
                <th>Initial Price</th>
                <th>Schedule Start</th>
                <th>Schedule End</th>
                <th>Status</th>
                <th>Client ID</th>
                <th>Contractor ID</th>
            </tr>
            <c:forEach var="quote" items="${listQuote}">
                <tr style="text-align:center">
                    <td><c:out value="${quote.quoteID}" /></td>
                    <td><c:out value="${quote.initialPrice}" /></td>
                    <td><c:out value="${quote.scheduleStart}" /></td>
                    <td><c:out value="${quote.scheduleEnd}" /></td>
                    <td><c:out value="${quote.status}" /></td>
                    <td><c:out value="${quote.clientID}" /></td>
                    <td><c:out value="${quote.contractorID}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    
 <h1>List all quote messages</h1>
    <!-- Quote Message Table -->
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Quote Messages</h2></caption>
            <tr>
                <th>Quote Message ID</th>
                <th>User ID</th>
                <th>Quote ID</th>
                <th>Message Time</th>
                <th>Note</th>
            </tr>
            <c:forEach var="quoteMessage" items="${listQuoteMessages}">
                <tr style="text-align:center">
                    <td><c:out value="${quoteMessage.quoteMessageID}" /></td>
                    <td><c:out value="${quoteMessage.userID}" /></td>
                    <td><c:out value="${quoteMessage.quoteID}" /></td>
                    <td><c:out value="${quoteMessage.msgTime}" /></td>
                    <td><c:out value="${quoteMessage.note}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
	
	<h1>List all orders</h1>
    <!-- Order Table -->
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Orders</h2></caption>
            <tr>
                <th>Order ID</th>
                <th>Quote ID</th>
                <th>Client ID</th>
                <th>Price</th>
                <th>Schedule Start</th>
                <th>Schedule End</th>
                <th>Status</th>
            </tr>
            <c:forEach var="order" items="${listOrders}">
                <tr style="text-align:center">
                    <td><c:out value="${order.orderID}" /></td>
                    <td><c:out value="${order.quoteID}" /></td>
                    <td><c:out value="${order.clientID }"/></td>
                    <td><c:out value="${order.price}" /></td>
                    <td><c:out value="${order.scheduleStart}" /></td>
                    <td><c:out value="${order.scheduleEnd}" /></td>
                    <td><c:out value="${order.status}"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
	
	<h1>List all bills</h1>
    <!-- Bill Table -->
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Bills</h2></caption>
            <tr>
                <th>Bill ID</th>
                <th>Order ID</th>
                <th>Client ID</th>
                <th>Price</th>
                <th>Date Generated</th>
                <th>Date Paid</th>
                <th>Status</th>
            </tr>
            <c:forEach var="bill" items="${listBills}">
                <tr style="text-align:center">
                    <td><c:out value="${bill.billID}" /></td>
                    <td><c:out value="${bill.orderID}" /></td>
                    <td><c:out value="${bill.clientID}" /></td>
                    <td><c:out value="${bill.price}" /></td>
                    <td><c:out value="${bill.dateGenerated}" /></td>
                    <td><c:out value="${bill.datePaid}" /></td>
                    <td><c:out value="${bill.status}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    
    <h1>List all bill messages</h1>
    <!-- Bill Message Table -->
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Bill Messages</h2></caption>
            <tr>
                <th>Bill Message ID</th>
                <th>Client ID</th>
                <th>Bill ID</th>
                <th>Message Time</th>
                <th>Price</th>
                <th>Note</th>
            </tr>
            <c:forEach var="billMessage" items="${listBillMessages}">
                <tr style="text-align:center">
                    <td><c:out value="${billMessage.billMessageID}" /></td>
                    <td><c:out value="${billMessage.clientID}" /></td>
                    <td><c:out value="${billMessage.billID}" /></td>
                    <td><c:out value="${billMessage.msgTime}" /></td>
                    <td><c:out value="${billMessage.price}" /></td>
                    <td><c:out value="${billMessage.note}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <h1>Big Clients</h1>
		<div align="center">
		    <table border="1" cellpadding="6">
		        <caption><h2>Big Clients</h2></caption>
		        <tr>
		            <th>First Name</th>
		            <th>Last Name</th>
		            <th>Client ID</th>
		            <th>Tree Cutted</th>
		        </tr>
		        <c:forEach var="bigClient" items="${listBigClients}">
		            <tr style="text-align:center">
		                <td><c:out value="${bigClient.firstName}" /></td>
		                <td><c:out value="${bigClient.lastName}" /></td>
		                <td><c:out value="${bigClient.clientID}" /></td>
		                <td><c:out value="${bigClient.treeCutted}" /></td>
		            </tr>
		        </c:forEach>
		    </table>
		</div>
		<h1>Easy Clients</h1>
			<div align="center">
			    <table border="1" cellpadding="6">
			        <caption><h2>Easy Clients</h2></caption>
			        <tr>
			            <th>First Name</th>
			            <th>Last Name</th>
			            <th>Client ID</th>
			        </tr>
			        <c:forEach var="easyClient" items="${listEasyClients}">
			            <tr style="text-align:center">
			                <td><c:out value="${easyClient.firstName}" /></td>
			                <td><c:out value="${easyClient.lastName}" /></td>
			                <td><c:out value="${easyClient.clientID}" /></td>
			            </tr>
			        </c:forEach>
			    </table>
			</div>
			<h1>One Tree Quotes</h1>
				<div align="center">
				    <table border="1" cellpadding="6">
				        <caption><h2>One Tree Quotes</h2></caption>
				        <tr>
				            <th>Quote ID</th>
				            <th>Price</th>
				            <th>Schedule Start</th>
				            <th>Schedule End</th>
				            <th>Status</th>
				        </tr>
				        <c:forEach var="oneTreeQuote" items="${listOneTreeQuotes}">
				            <tr style="text-align:center">
				                <td><c:out value="${oneTreeQuote.quoteID}" /></td>
				                <td><c:out value="${oneTreeQuote.initialPrice}" /></td>
				                <td><c:out value="${oneTreeQuote.scheduleStart}" /></td>
				                <td><c:out value="${oneTreeQuote.scheduleEnd}" /></td>
				                <td><c:out value="${oneTreeQuote.status}" /></td>
				            </tr>
				        </c:forEach>
				    </table>
				</div>
				<h1>Prospective Clients</h1>
				<div align="center">
				    <table border="1" cellpadding="6">
				        <caption><h2>Prospective Clients</h2></caption>
				        <tr>
				            <th>Client ID</th>
				            <th>First Name</th>
				            <th>Last Name</th>
				        </tr>
				        <c:forEach var="client" items="${listProspectiveClients}">
				            <tr style="text-align:center">
				                <td><c:out value="${client.clientID}" /></td>
				                <td><c:out value="${client.firstName}" /></td>
				                <td><c:out value="${client.lastName}" /></td>
				            </tr>
				        </c:forEach>
				    </table>
				</div>
				<h1>Highest Trees in Finished Orders</h1>
				<div align="center">
				    <table border="1" cellpadding="6">
				        <caption><h2>Highest Trees</h2></caption>
				        <tr>
				            <th>Tree ID</th>
				            <th>Height</th>
				            <th>Location</th>
				        </tr>
				        <c:forEach var="tree" items="${listHighestTrees}">
				            <tr style="text-align:center">
				                <td><c:out value="${tree.treeID}" /></td>
				                <td><c:out value="${tree.height}" /></td>
				                <td><c:out value="${tree.location}" /></td>
				            </tr>
				        </c:forEach>
				    </table>
				</div>
				
				<h1>Overdue Bills</h1>
				<div align="center">
				    <table border="1" cellpadding="6">
				        <caption><h2>Overdue Bills</h2></caption>
				        <tr>
				            <th>Bill ID</th>
				            <th>Order ID</th>
				            <th>Client ID</th>
				            <th>Price</th>
				            <th>Date Generated</th>
				            <th>Date Paid</th>
				            <th>Status</th>
				        </tr>
				        <c:forEach var="bill" items="${listOverdueBills}">
				            <tr style="text-align:center">
				                <td><c:out value="${bill.billID}" /></td>
				                <td><c:out value="${bill.orderID}" /></td>
				                <td><c:out value="${bill.clientID}" /></td>
				                <td><c:out value="${bill.price}" /></td>
				                <td><c:out value="${bill.dateGenerated}" /></td>
				                <td><c:out value="${bill.datePaid}" /></td>
				                <td><c:out value="${bill.status}" /></td>
				            </tr>
				        </c:forEach>
				    </table>
				</div>
				
				<h1>Bad Clients</h1>
				<div align="center">
				    <table border="1" cellpadding="6">
				        <caption><h2>Bad Clients</h2></caption>
				        <tr>
				            <th>Client ID</th>
				            <th>First Name</th>
				            <th>Last Name</th>
				        </tr>
				        <c:forEach var="client" items="${listBadClients}">
				            <tr style="text-align:center">
				                <td><c:out value="${client.clientID}" /></td>
				                <td><c:out value="${client.firstName}" /></td>
				                <td><c:out value="${client.lastName}" /></td>
				            </tr>
				        </c:forEach>
				    </table>
				</div>
				<h1>Good Clients</h1>
				<div align="center">
				    <table border="1" cellpadding="6">
				        <caption><h2>Good Clients</h2></caption>
				        <tr>
				            <th>Client ID</th>
				            <th>First Name</th>
				            <th>Last Name</th>
				        </tr>
				        <c:forEach var="client" items="${listGoodClients}">
				            <tr style="text-align:center">
				                <td><c:out value="${client.clientID}" /></td>
				                <td><c:out value="${client.firstName}" /></td>
				                <td><c:out value="${client.lastName}" /></td>
				            </tr>
				        </c:forEach>
				    </table>
				</div>
				
				<h1>Client Statistics</h1>
				<div align="center">
				    <table border="1" cellpadding="6">
				        <caption><h2>Client Statistics</h2></caption>
				        <tr>
				            <th>Client ID</th>
				            <th>First Name</th>
				            <th>Last Name</th>
				            <th>Total Due Amount</th>
				            <th>Total Paid Amount</th>
				            <th>Finish Date</th>
				        </tr>
				        <c:forEach var="stat" items="${listClientStatistics}">
				            <tr style="text-align:center">
				                <td><c:out value="${stat.clientID}" /></td>
				                <td><c:out value="${stat.firstName}" /></td>
				                <td><c:out value="${stat.lastName}" /></td>
				                <td><c:out value="${stat.totalDueAmount}" /></td>
				                <td><c:out value="${stat.totalPaidAmount}" /></td>
				                <td><c:out value="${stat.finishDate == null ? 'N/A' : stat.finishDate}" /></td>
				            </tr>
				        </c:forEach>
				    </table>
				</div>

				
				
				
							
	</div>

</body>
</html>