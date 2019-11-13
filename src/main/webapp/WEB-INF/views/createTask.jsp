<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Task</title>
</head>
<body>
<form method="post" action="add">
    <input type="hidden" name="""${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="text" name="summary" placeholder="enter summary"/>
    <input type="text" name="assignee" placeholder="enter assignee"/>
    Start date: <input type="date" name="startDate" placeholder="enter start Date">
    End date: <input type="date" name="endDate" placeholder="enter end Date">
    <button type="submit">Add new Task</button>
</form>
</body>
</html>