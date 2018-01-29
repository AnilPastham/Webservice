<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Zolon Admin Display Details</title>
</head>
<style>
.headerData {
	text-align: center;
	font-weight: bold;
	width: 100px;
	height: 20px;
	border-style: solid;
	border-width: 2px;
}

.bodyData {
	text-align: center;
	width: 40px;
	height: 100px;
	color: green;
	border: 1px;
	border-style: solid;
	border-width: 2px;
}
</style>
<body>
	<h1>Zolon Admin Display Details</h1>

	<c:choose>
		<c:when test="${fn:length(details) > 0}">
			<table>
				<thead>
					<tr>
						<th class="headerData"><span>Image</span></th>
						<th class="headerData"><span>Id</span></th>
						<th class="headerData"><span>Name</span></th>
						<th class="headerData"><span>Email</span></th>
						<th class="headerData"><span>Date of Birth</span></th>
						<th class="headerData"><span>Street</span></th>
						<th class="headerData"><span>City</span></th>
						<th class="headerData"><span>State</span></th>
						<th class="headerData"><span>Zip Code</span></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${details}" var="detail">
						<tr>
							<td><img alt="img"
								src="data:image/jpg;base64,${detail.image}" height="200"
								width="200" /></td>
							<td class="bodyData"><span>${detail.id}</span></td>
							<td class="bodyData"><span>${detail.name}</span></td>
							<td class="bodyData"><span>${detail.email}</span></td>
							<td class="bodyData"><span>${detail.dob}</span></td>
							<td class="bodyData"><span>${detail.address.street}</span></td>
							<td class="bodyData"><span>${detail.address.city}</span></td>
							<td class="bodyData"><span>${detail.address.state}</span></td>
							<td class="bodyData"><span>${detail.address.zip}</span></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<h3>No results to display. Please wait for uploads.</h3>
		</c:otherwise>
	</c:choose>
</body>
</html>