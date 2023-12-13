<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Respond to Quote</title>
</head>
<body>
    <div align="center">
        <h1>Respond to Quote</h1>
        <form action="updateQuote">
            <input type="hidden" name="quoteID" value="${param.quoteID}" />
            <label for="price">New Price (leave blank to keep current):</label>
            <input type="text" name="price" id="price" /><br><br>
            <label for="scheduleStart">New Schedule Start (leave blank to keep current):</label>
            <input type="text" name="scheduleStart" id="scheduleStart" /><br><br>
            <label for="scheduleEnd">New Schedule End (leave blank to keep current):</label>
            <input type="text" name="scheduleEnd" id="scheduleEnd" /><br><br>
            <button type="submit">Update Quote</button>
        </form>
    </div>
</body>
</html>
