<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<body>
    <form method="post" action="/write">

        Name: <input type="text" name="name" id="name">
        <br>
        Subject: <input type="text" width="250" name="subject" id="subject">
        <br>
        Content: <textarea cols="60" rows="20" name="content" id="content"></textarea>
        <br>
        <input type="submit">

    </form>
</body>
</html>