<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entidades.Carrera" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Carreras</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

    <h1>Gestión de Carreras</h1>

    <a href="${pageContext.request.contextPath}/CarreraServlet?action=nuevo" class="add-button">Agregar Nueva Carrera</a>
    <br><br>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>ID Facultad</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${not empty listaCarreras}">
                    <c:forEach var="carrera" items="${listaCarreras}">
                        <tr>
                            <td>${carrera.idcarrera}</td>
                            <td>${carrera.nombre}</td>
                            
                            <%-- Mostramos el nombre de la facultad, no el ID --%>
                            <td>${carrera.facultadIdfacultad.nombre}</td>
                            
                            <td class="actions">
                                <a href="${pageContext.request.contextPath}/CarreraServlet?action=editar&id=${carrera.idcarrera}">Editar</a>
                                <a href="${pageContext.request.contextPath}/CarreraServlet?action=eliminar&id=${carrera.idcarrera}" onclick="return confirm('¿Estás seguro de que deseas eliminar esta carrera?')">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="4">No hay carreras registradas.</td>
                    </tr>
                </c:otherwise>
            </c:choose>
            
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/index.jsp" class="btn-enlace">Volver al Menú Principal</a>

</body>
</html>
