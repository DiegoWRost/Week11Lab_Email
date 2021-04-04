<%-- 
    Document   : reset
    Created on : Apr 3, 2021, 9:27:59 PM
    Author     : 806509
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
    </head>
    <body>
        <h1>Reset Password</h1>
        <p>Please enter your email address to reset your password.</p>
        <form action="reset" method="post">
            Email Address: <input type="email" name="email">
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
