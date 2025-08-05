<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Đăng Nhập</title>
</head>
<body>
<h2>Đăng Nhập</h2>

<c:if test="${not empty error}">
  <p>${error}</p>
</c:if>

<form method="post" action="login">
  Tên đăng nhập:<br>
  <input type="text" name="username"/><br><br>
  Mật khẩu:<br>
  <input type="password" name="password"/><br><br>
  <button type="submit">Đăng Nhập</button>
</form>
</body>
</html>
