<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Borrow List - Admin Panel</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            font-family: 'Arial', sans-serif;
        }
        .container {
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            margin-top: 50px;
            padding: 30px;
        }
        .btn-primary {
            background-color: #667eea;
            border-color: #667eea;
        }
        .btn-primary:hover {
            background-color: #5a67d8;
            border-color: #5a67d8;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="mb-4">Borrow List Management</h2>
        
        <c:if test="${not empty borrowLists}">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Member</th>
                        <th scope="col">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="borrowList" items="${borrowLists}">
                        <tr>
                            <td><c:out value="${borrowList.id}"/></td>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty borrowList.member}">
                                        <c:out value="${borrowList.member.username}"/>
                                    </c:when>
                                    <c:otherwise>
                                        Unknown Member
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <form action="/admin/borrowlist/delete" method="get" style="display: inline;">
                                    <input type="hidden" name="id" value="${borrowList.id}">
                                    <input type="submit" value="Delete" class="btn btn-danger btn-sm" 
                                           onclick="return confirm('Are you sure you want to delete this borrow list?')">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        
        <c:if test="${empty borrowLists}">
            <div class="alert alert-info">
                <h4>No Borrow Lists Found</h4>
                <p>There are currently no borrow lists in the system.</p>
            </div>
        </c:if>
        
        <div class="mt-4">
            <a href="/admin/" class="btn btn-secondary">Back to Admin Panel</a>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
