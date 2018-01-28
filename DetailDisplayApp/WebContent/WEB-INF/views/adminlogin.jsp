<!DOCTYPE head PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<h2>Zolan Admin Login</h2>
<form name="adminLoginForm" action="authenticate" method="post">
	<table>
		<tr>
			<td colspan='2' style='text-align: center;'>Please login to see
				Admin Details</td>
		</tr>
		<tr>
			<td>Username:</td>
			<td><input type="text" name="user" size="20"
				value="${user.user}" /></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type="text" name="password" size="20"
				value="${detail.password}" /></td>
		</tr>
		<tr>
			<td><input type="submit" value="Login" /></td>
		</tr>
	</table>
</form>

<script>
$(document).ready(function(){
	if(${uploaded} == true) {
		alert("Please store this ID: ${id} for your records.");
	}
});
</script>
</html>