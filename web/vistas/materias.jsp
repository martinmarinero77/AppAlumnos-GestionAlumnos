<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

<%-- Importamos la entidad Materia --%>
<%@ page import="entidades.Materia" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Materias</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <h1>Gestión de Materias</h1>

    <a href="${pageContext.request.contextPath}/MateriaServlet?action=nuevo" class="btn-enlace">Agregar Nueva Materia</a>
    <br><br>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
        
            <%-- Usamos 'listaMaterias' (el nombre que pusimos en el servlet) --%>
            <c:choose>
                <c:when test="${not empty listaMaterias}">
                    <c:forEach var="materia" items="${listaMaterias}">
                        <tr>
                            <td>${materia.idmateria}</td>
                            <td>${materia.nombre}</td>
                            
                            <td class="actions">
                                <a href="${pageContext.request.contextPath}/MateriaServlet?action=editar&id=${materia.idmateria}">Editar</a>
                                <a href="${pageContext.request.contextPath}/MateriaServlet?action=eliminar&id=${materia.idmateria}" onclick="return confirm('¿Estás seguro de que deseas eliminar esta materia?')">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <%-- El colspan es 3 (ID, Nombre, Acciones) --%>
                    <tr>
                        <td colspan="3">No hay materias registradas.</td>
                    </tr>
                </c:otherwise>
            </c:choose>
            
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/index.jsp" class="btn-enlace">Volver al Menú Principal</a>

</body>
</html>