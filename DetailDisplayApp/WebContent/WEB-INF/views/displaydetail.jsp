<%-- <%@ page contentType="text/html;charset=UTF-8" language="java" %>
 --%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Zolon Admin Display Details</title>
</head>
<body>
    <h1>Zolon Admin Display Details</h1>

    <c:if test="${details != null}">
    <table>
    <tr> 
        <td class = "headerData"><span>Image</span></td>
        <td class = "headerData"><span>Id</span></td>
        <td class = "headerData"><span>Name</span></td>
        <td class = "headerData"><span>Email</span></td>
        <td class = "headerData"><span>Date of Birth</span></td>
        <td class = "headerData"><span>Street</span></td>
        <td class = "headerData"><span>City</span></td>
        <td class = "headerData"><span>State</span></td>
        <td class = "headerData"><span>Zip Code</span></td>
	</tr>
        <c:forEach items="${details}" var="detail">
        <tr>
	        <td> <img alt="img" src="data:image/jpg;base64,${detail.image}" height = "200" width = "200"/></td>
 	        <td>${detail.id}</td>
	        <td>${detail.name}</td>
	        <td><span>${detail.email}</span></td>
	        <td><span>${detail.dob}</span></td>
	        <td><span>${detail.address.street}</span></td>
	        <td><span>${detail.address.city}</span></td>
	        <td><span>${detail.address.state}</span></td>
	        <td><span>${detail.address.zip}</span></td>        	
        </tr>
        </c:forEach>
    </table>
    </c:if>

</body>
</html>