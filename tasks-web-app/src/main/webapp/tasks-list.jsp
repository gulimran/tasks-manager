<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <div>
        <div">
            <div>
                <div>
                    <h1>My tasks</h1>
                </div>
                <table border="1">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Description</th>
                        <th>Priority</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.tasksList}" var="task">
                        <tr>
                            <td>${task.id}</td>
                            <td>${task.description}</td>
                            <td>${task.priority}</td>
                            <td>
                                <form action="/tasks/update?taskId=${task.id}"" method="get">
                                    <button type="submit">Edit</button>
                                </form>
                                <form action="/tasks/delete" method="post">
                                    <input type="hidden" name="taskId" value="${task.id}">
                                    <button type="submit">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <c:if test="${empty requestScope.tasksList}">
                    <div>
                        <div align="center">No tasks <tasks></tasks>'</div>
                    </div>
                </c:if>

            </div>
        </div>
    </div>
</div>

