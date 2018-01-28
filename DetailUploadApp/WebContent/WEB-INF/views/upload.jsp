<!DOCTYPE head PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<h2>Zolan File Upload</h2>  
<form name="uploadForm" action="upload" method="post" enctype="multipart/form-data">  
       <table>
       <tr><td colspan = '2' style = 'text-align:center;'> User Information</td></tr>
       
       <tr>
       <td>Name: </td>
       <td><input type="text" name="name" size="20" value="${detail.name}"/></td>
       </tr>  
       <tr>
       <td>Date of Birth: </td>
       <td><input type="text" name="dob" size="20" value="${detail.dob}"/></td>
       </tr>     
       <tr>
       <td>Email: </td>
       <td><input type="text" name="email" size="20" value="${detail.email}"/></td>
       </tr>
       <tr><td colspan = '2' style = 'text-align:center;'> Adress Information</td></tr>
       
       <tr>
       <td>Street Address: </td>
       <td><input type="text" name="street" size="20" value="${detail.address.street}"/></td>
       </tr>
              <tr>
       <td>City: </td>
       <td><input type="text" name="city" size="20" value="${detail.address.city}"/></td>
       </tr>
              <tr>
       <td>State: </td>
       <td><input type="text" name="state" size="20" value="${detail.address.state}"/></td>
       </tr>
              <tr>
       <td>Zip Code: </td>
       <td><input type="text" name="zip" size="20" value="${detail.address.zip}"/></td>
       </tr>
       </table>
       <p>  
        Select a file : <input type="file" name="file" size="45" />          
       </p>  
       <input type="submit" value="Upload File" />  
</form>  

<script>
$(document).ready(function(){
	if(${uploaded} == true) {
		alert("Please store this ID: ${id} for your records.");
	}
});
</script>
</html>