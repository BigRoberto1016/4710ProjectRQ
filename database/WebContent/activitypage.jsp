<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head><title>Initial Request</title></head>
<body>
	<center><h1>Initial Request</h1></center>
	<div align="center">
		<p>Your client ID is: ${sessionScope.userID}</p>
	 <!-- Display error message if present -->
        <c:if test="${not empty errorMessage}">
            <p style="color: red;">${errorMessage}</p>
        </c:if>

        <!-- Display success message from session if present -->
        <c:if test="${not empty sessionScope.successMessage}">
            <p style="color: green;">${sessionScope.successMessage}</p>
            <% session.removeAttribute("successMessage"); // Remove after displaying %>
        </c:if>
		<form action="initialRequest">
			<table border="1" cellpadding="5">
				<tr>
					<th>Tree Size(In feet): </th>
					<td align="center" colspan="3">
						<input type="text" name="size" size="45"  value="" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Tree Height(In feet): </th>
					<td align="center" colspan="3">
						<input type="text" name="height" size="45" value="" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Location: </th>
					<td align="center" colspan="3">
						<input type="text" name="location" size="45" value="" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
				    <th>Distance From House(In feet): </th>
				    <td align="center" colspan="3">
				        <input type="text" name="proximityToHouse" size="45" value="" onfocus="this.value=''">
				    </td>
				</tr>
				<tr>
				    <th>Number of Trees: </th>
				    <td align="center" colspan="3">
				        <input type="text" name="treeNo" size="45" value="" onfocus="this.value=''">
				    </td>
				</tr>
								
				<tr>
				    <th>Your Client ID: </th>
				    <td align="center" colspan="3">
				        <input type="text" name="clientID" size="45" value="${sessionScope.userID}" readonly>
				    </td>
				</tr>
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Get Your Quote"/>
					</td>
				</tr>
			</table>
		</form>
		<h2>Your Quotes</h2>
    <table border="1" cellpadding="5">
        <tr>
            <th>Quote ID</th>
            <th>Client ID</th>
            <th>Initial Price</th>
            <th>Schedule Start</th>
            <th>Schedule End</th>
            <th>Status</th>
        </tr>
        <c:forEach var="quote" items="${sessionScope.userQuotes}">
            <tr>
                <td><c:out value="${quote.quoteID}" /></td>
                <td><c:out value="${quote.clientID }"/></td>
                <td><c:out value="${quote.initialPrice}" /></td>
                <td><c:out value="${quote.scheduleStart}" /></td>
                <td><c:out value="${quote.scheduleEnd}" /></td>
                <td><c:out value="${quote.status}" /></td>
                <td>
            <c:if test="${quote.status != 'Confirmed'}">
                <form action="RespondQuote.jsp" style="display:inline;">
                    <input type="hidden" name="quoteID" value="${quote.quoteID}" />
                    <input type="submit" value="Respond Quote" />
                </form>
                <form action="agreeToQuote"  style="display:inline;" onsubmit="return confirm('Are you sure you want to agree to this quote?');">
                    <input type="hidden" name="quoteID" value="${quote.quoteID}" />
                    <input type="hidden" name="clientID" value="${quote.clientID }"/>
                    <input type="hidden" name="initialPrice" value="${quote.initialPrice}" />
                    <input type="hidden" name="scheduleStart" value="${quote.scheduleStart}" />
                    <input type="hidden" name="scheduleEnd" value="${quote.scheduleEnd}" />
                    <input type="submit" value="Agree" />
                </form>
            </c:if>
        </td>
    </tr>
</c:forEach>
    </table>
    <h2>Your Bills</h2>
<table border="1" cellpadding="5">
    <tr>
        <th>Bill ID</th>
        <th>Order ID</th>
        <th>Price</th>
        <th>Date Generated</th>
        <th>Date Paid</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <c:forEach var="bill" items="${sessionScope.userBills}">
        <tr>
            <td><c:out value="${bill.billID}" /></td>
            <td><c:out value="${bill.orderID}" /></td>
            <td><c:out value="${bill.price}" /></td>
            <td><c:out value="${bill.dateGenerated}" /></td>
            <td><c:out value="${bill.datePaid}" /></td>
            <td><c:out value="${bill.status}" /></td>
            <td>
                <c:if test="${bill.status != 'Paid'}">
                    <form action="payBill" style="display:inline;">
                        <input type="hidden" name="action" value="payBill" />
                        <input type="hidden" name="billID" value="${bill.billID}" />
                        <button type="submit">Pay</button>
                    </form>
                </c:if>
	                <form action="negotiateBill" style="display:inline;">
		                <input type="hidden" name="action" value="negotiateBill" />
		                <input type="hidden" name="billID" value="${bill.billID}" />
		                <input type="hidden" name="clientID" value="${bill.clientID}" />
		                <input type="hidden" name="price" value="${bill.price}" />
		                <button type="button" onclick="showNegotiationBox(this)">Negotiate</button>
		                <div style="display:none;">
		                    <textarea name="note" rows="2" cols="30"></textarea>
		                    <button type="submit">Submit Negotiation</button>
		                </div>
	            	</form>
            </td>
        </tr>
    </c:forEach>
</table>

<script>
    function showNegotiationBox(button) {
        var div = button.nextElementSibling;
        div.style.display = 'block';
    }
</script>
    
<a href="login.jsp" target="_self">Return to Login Page</a>
		
	</div>
</body>