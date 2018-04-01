<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <style>
        .tb_board{
            border: 1px solid black;
        }
    </style>
</head>
<body>
    <h2>Board</h2>
    <input type="button" value="Go to Write" onclick="location.href='/writeform'">
    <br>
    <table class="tb_board">
        <tr>
            <td width="50">
                no.
            </td>
            <td width="250">
                Subject
            </td>
            <td width="100">
                Writer
            </td>
            <td width="100">
                RegDate
            </td>
        </tr>
        <c:forEach items="${iter}" var="b">
            <tr>
                <td width="50">
                    ${b.no}
                </td>
                <td width="250">
                    <a href="/view?no=${b.no}">${b.subject}</a>
                </td>
                <td width="100">
                    ${b.name}
                </td>
                <td width="100">
                    ${b.regdate}
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>