<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%@ include file="/WEB-INF/jsp/includes/includeHead.jsp" %>
        <title>Login Page</title>
    </head>
    <body>
        <form name='f' action='j_spring_security_check' method='POST'>
            <table bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
                <tr>
                    <td>User:</td><td><input type='text' name='j_username' value=''></td>
                </tr>
                <tr>
                    <td>Password:</td><td><input type='password' name='j_password'/></td>
                </tr>
                <tr>
                    <td colspan='2'>
                        <input type='checkbox' name='_spring_security_remember_me'/> Remember me on this computer.
                    </td>
                </tr>
                <tr>
                    <td colspan='2'><input name="submit" type="submit"/><input name="reset" type="reset"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>
