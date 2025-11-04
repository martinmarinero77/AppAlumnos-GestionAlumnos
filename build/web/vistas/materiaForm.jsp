<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario de Materia</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <%-- 1. Determinamos si es "Nuevo" o "Editar" (usando la variable "materia") --%>
    <c:set var="esNuevo" value="${empty materia}" />

    <h1><c:if test="${esNuevo}">Registrar Nueva Materia</c:if><c:if test="${!esNuevo}">Editar Materia</c:if></h1>

    <form action="${pageContext.request.contextPath}/MateriaServlet" method="post">
        
        <%-- 2. Lógica para EDITAR --%>
        <c:if test="${!esNuevo}">
            <%-- El 'name' debe ser 'idmateria' para que el servlet lo reciba --%>
            <input type="hidden" name="idmateria" value="${materia.idmateria}">
            <input type="hidden" name="action" value="actualizar">
        </c:if>
        
        <%-- 3. Lógica para REGISTRAR --%>
        <c:if test="${esNuevo}">
            <input type="hidden" name="action" value="registrar">
        </c:if>

        <%-- Campo Nombre --%>
        <div class="form-group">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" value="${materia.nombre}" required>
        </div>

        <%-- Botón dinámico --%>
        <input type="submit" value="${esNuevo ? 'Registrar Materia' : 'Actualizar Materia'}">
    </form>

    <br>
    <%-- Botón "Volver" --%>
    <div style="text-align: center;">
        <a href="${pageContext.request.contextPath}/MateriaServlet" class="btn-enlace">Volver al Listado</a>
    </div>

</body>
</html>