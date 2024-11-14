<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${msg} a Book</title>
</head>

<body>
	<c:if test="${msg == 'Update'}">
		<form:form modelAttribute="book" method="post" action="${pageContext.request.contextPath}/books/${book.id}">
			<!-- <form:hidden path="id" /> -->
			<form:errors path="*" cssClass="errorblock" element="div" />
			<table>
				<tr>
					<td>Title:</td>
					<td>
						<!-- <input type="text" name="title" value="${book.title}" /> -->
						<form:input path="title" />
						<form:errors path="title" cssClass="error" />
					</td>
				</tr>
				<tr>
					<td>ISBN:</td>
					<td>
						<!-- <input type="text" name="ISBN" value="${book.ISBN}" /> -->
						<form:input path="ISBN" />
						<form:errors path="ISBN" cssClass="error" />
					</td>
				</tr>
				<tr>
					<td>Author:</td>
					<td>
						<!-- <input type="text" name="author" value="${book.author}" /> -->
						<form:input path="author" />
						<form:errors path="author" cssClass="error" />
					</td>
				</tr>
				<tr>
					<td>Price:</td>
					<td>
						<!-- <input type="text" name="price" value="${book.price}" /> -->
						<form:input path="price" />
						<form:errors path="price" cssClass="error" />
					</td>
				</tr>
			</table>
			<input type="submit" value="${msg}"/>
		</form:form>
		<form:form modelAttribute="book" action="${pageContext.request.contextPath}/books/delete" method="post">
			<form:hidden path="id" />
			<button type="submit">Delete</button>
		</form:form>
	</c:if>
	<c:if test="${msg == 'Add'}">
		<form:form modelAttribute="book" method="post" action="${pageContext.request.contextPath}/books">
			<form:errors path="*" cssClass="errorblock" element="div" />
			<table>
				<tr>
					<td>Title:</td>
					<td>
						<!-- <input type="text" name="title" value="${book.title}" /> -->
						<form:input path="title" />
						<form:errors path="title" cssClass="error" />
					</td>
				</tr>
				<tr>
					<td>ISBN:</td>
					<td>
						<!-- <input type="text" name="ISBN" value="${book.ISBN}" /> -->
						<form:input path="ISBN" />
						<form:errors path="ISBN" cssClass="error" />
					</td>
				</tr>
				<tr>
					<td>Author:</td>
					<td>
						<!-- <input type="text" name="author" value="${book.author}" /> -->
						<form:input path="author" />
						<form:errors path="author" cssClass="error" />
					</td>
				</tr>
				<tr>
					<td>Price:</td>
					<td>
						<!-- <input type="text" name="price" value="${book.price}" /> -->
						<form:input path="price" />
						<form:errors path="price" cssClass="error" />
					</td>
				</tr>
			</table>
			<input type="submit" value="${msg}"/>
		</form:form>
	</c:if>
</body>

</html>