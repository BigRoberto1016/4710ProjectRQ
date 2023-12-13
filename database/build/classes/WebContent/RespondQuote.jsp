<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head><title>Respond to Quote</title></head>
<body>
    <h2>Respond to Quote</h2>
    <form action="respondQuote" >
        <input type="hidden" name="quoteID" value="${param.quoteID}" />
        <input type="hidden" name="price" value="${param.price}" />
        <input type="hidden" name="scheduleStart" value="${param.scheduleStart}" />
        <input type="hidden" name="scheduleEnd" value="${param.scheduleEnd}" />
        <input type="radio" id="decline" name="response" value="decline">
        <label for="decline">Decline</label><br>
        <input type="radio" id="negotiate" name="response" value="negotiate">
        <label for="negotiate">Negotiate</label><br>
        <div id="negotiateOptions" style="display:none;">
            <select name="reason">
                <option value="time">Time</option>
                <option value="price">Price</option>
            </select>
            <textarea name="note" rows="4" cols="50"></textarea>
        </div>
        <input type="submit" value="Submit Response">
    </form>
<a href="login.jsp" target="_self">Return to Login Page</a>
    <script>
        document.getElementById("negotiate").onclick = function() {
            document.getElementById("negotiateOptions").style.display = 'block';
        };
        document.getElementById("agree").onclick = function() {
            document.getElementById("negotiateOptions").style.display = 'none';
        };
        document.getElementById("decline").onclick = function() {
            document.getElementById("negotiateOptions").style.display = 'none';
        };
    </script>
</body>
</html>
