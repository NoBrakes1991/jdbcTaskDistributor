<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Task List</title>
</head>
<body>

<table>
    <tr>
        <th>ID</th>
        <th>Summary</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Assignee</th>
    </tr>
    <c:forEach items="${tasks}" var="task">
        <tr>
            <td>${task.id}</td>
            <td>${task.summary}</td>
            <fmt:setLocale value="en_US"/>
            <td><fmt:formatDate pattern="dd/MMM/yyyy" value="${task.startDate}"/></td>
            <td><fmt:formatDate pattern="dd/MMM/yyyy" value="${task.endDate}"/></td>
            <td>${task.startDate}</td>
            <td>${task.endDate}</td>

            <td>${task.assignee}</td>
        </tr>
    </c:forEach>
</table>


<form method="post" action="filterByDateAndAssignee">
    Start date: <input type="date" name="startDate">
    End date: <input type="date" name="endDate">
    <select name="period" itemtype="text">
        <option value="" label="------Set Period------"/>
        <option value="lastQuarter" label="Last Quarter"/>
        <option value="lastMonth" label="Last Month"/>
        <option value="lastWeek" label="Last Week"/>
        <option value="currentQuarterToDate" label="Current Quarter to Date"/>
        <option value="currentMonthToDate" label="Current Month to Date"/>
        <option value="currentWeekToDate" label="Current Week to Date"/>
    </select>
</form>


</body>
</html>