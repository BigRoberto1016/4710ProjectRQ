<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>David Smith DashBoard</title>
</head>
<body>

<div align = "center">
	<a href="login.jsp"target ="_self" > logout</a><br><br> 

	
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
            </tr>
            <c:forEach var="tree" items="${listTreeWithoutQuote}">
                <tr style="text-align:center">
                    <td><c:out value="${tree.treeID}" /></td>
                    <td><c:out value="${tree.size}" /></td>
                    <td><c:out value="${tree.height}" /></td>
                    <td><c:out value="${tree.location}" /></td>
                    <td><c:out value="${tree.proximityToHouse}" /></td>
                    <td><c:out value="${tree.treeNo }"/></td>
                    <td><c:out value="${tree.clientID}" /></td>
                    <td>
			            <form action="processQuote.jsp" method="post">
			                <input type="hidden" name="treeID" value="${tree.treeID}" />
			                <input type="hidden" name="clientID" value="${tree.clientID}" />
			                <button type="submit">Send Quote</button>
			            </form>
			        </td>
			        <td>
			            <form action="rejectTree" onsubmit="return confirm('Are you sure you want to reject this request?');">
			                <input type="hidden" name="action" value="rejectTree" />
			                <input type="hidden" name="treeID" value="${tree.treeID}" />
			                <button type="submit">Reject</button>
			            </form>
			        </td>
                </tr>
            </c:forEach>
        </table>
    </div>
     <h1>Quote Messages with Price</h1>
    <table border="1" cellpadding="6">
        <caption><h2>List of Quote Messages</h2></caption>
        <tr>
            <th>Message ID</th>
            <th>User ID</th>
            <th>Quote ID</th>
            <th>Message Time</th>
            <th>Note</th>
            <th>Price</th>
            <th>Action</th>
        </tr>
        <c:forEach var="quoteMessage" items="${listAllQuoteMessagesWithPrice}">
            <tr style="text-align:center">
                <td><c:out value="${quoteMessage.quoteMessageID}" /></td>
                <td><c:out value="${quoteMessage.userID}" /></td>
                <td><c:out value="${quoteMessage.quoteID}" /></td>
                <td><c:out value="${quoteMessage.msgTime}" /></td>
                <td><c:out value="${quoteMessage.note}" /></td>
                <td><c:out value="${quoteMessage.initialPrice}" /></td>
                <td>
                    <form action="DavidSmithRespond.jsp" method="post">
    					<input type="hidden" name="quoteID" value="${quoteMessage.quoteID}" />
    					<button type="submit">Respond</button>
					</form>
                </td>
            </tr>
        </c:forEach>
    </table>
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
            <c:forEach var="order" items="${listAllOrders}">
                <tr style="text-align:center">
                    <td><c:out value="${order.orderID}" /></td>
                    <td><c:out value="${order.quoteID}" /></td>
                    <td><c:out value="${order.clientID }"/></td>
                    <td><c:out value="${order.price}" /></td>
                    <td><c:out value="${order.scheduleStart}" /></td>
                    <td><c:out value="${order.scheduleEnd}" /></td>
                    <td><c:out value="${order.status}"/></td>
                    <c:if test="${order.status != 'Finished'}">
	                    <td>
		                    <form action="finishOrder" onsubmit="return confirm('Are you sure the work is finished?');">
						    <input type="hidden" name="action" value="finishOrder" />
						    <input type="hidden" name="orderID" value="${order.orderID}" />
						    <input type="hidden" name="clientID" value="${order.clientID}" />
						    <input type="hidden" name="price" value="${order.price}" />
						    <button type="submit">Finish</button>
							</form>
						</td>
				    </c:if>
				    <c:if test="${order.status == 'Finished'}">
            			<td></td>
        			</c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
    <h1>Bills</h1>
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
		        <c:forEach var="bill" items="${listAllBills}">
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
		
		<h1>Revenue</h1>
		<div align="center">
		    <table border="1" cellpadding="6">
		        <caption><h2>Revenue Statistics</h2></caption>
		        <tr>
		            <th>Total Income</th>
		            <th>Amount Awaited</th>
		        </tr>
		        <tr style="text-align:center">
		            <td><c:out value="${revenueStats.totalIncome}" /></td>
		            <td><c:out value="${revenueStats.amountAwaited}" /></td>
		        </tr>
		    </table>
		</div>
		
    
	</div>
</body>
</html>