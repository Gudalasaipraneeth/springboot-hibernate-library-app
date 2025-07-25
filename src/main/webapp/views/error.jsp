<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error - Spring Library</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f5f5f5; }
        .error-container { max-width: 600px; margin: 0 auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        .error-title { color: #d32f2f; font-size: 24px; margin-bottom: 20px; }
        .error-message { color: #666; font-size: 16px; margin-bottom: 20px; }
        .error-code { color: #999; font-size: 14px; margin-bottom: 30px; }
        .back-link { display: inline-block; background: #1976d2; color: white; padding: 10px 20px; text-decoration: none; border-radius: 4px; }
        .back-link:hover { background: #1565c0; }
    </style>
</head>
<body>
    <div class="error-container">
        <h1 class="error-title">Oops! Something went wrong</h1>
        <p class="error-message">
            <c:choose>
                <c:when test="${not empty errorMessage}">
                    ${errorMessage}
                </c:when>
                <c:otherwise>
                    We encountered an unexpected error while processing your request.
                </c:otherwise>
            </c:choose>
        </p>
        <p class="error-code">
            Error Code: <c:out value="${errorCode}" default="UNKNOWN_ERROR"/>
        </p>
        <a href="javascript:history.back()" class="back-link">Go Back</a>
        <a href="/" class="back-link" style="margin-left: 10px;">Home</a>
    </div>
</body>
</html>
