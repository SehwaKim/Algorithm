<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <style>
        .tb_view{
            border: 1px solid black;
        }
    </style>
</head>
<body>
<table class="tb_view">
    <tr>
        <td>no.</td>
        <td>${board.no}</td>
        <td>subject</td>
        <td width="250">${board.subject}</td>
        <td>name</td>
        <td width="100">${board.name}</td>
    </tr>
    <tr aria-colspan="6">
        <td>${board.content}</td>
    </tr>
</table>
<input type="button" value="go back" onclick="location.href='/list'">
</body>
</html>