<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario de Carrera</title>
    <%-- Enlazamos el CSS usando el context path --%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <%-- 1. Determinamos si es "Nuevo" o "Editar"--%>
    <c:set var="esNuevo" value="${empty carrera}" />

    <h1><c:if test="${esNuevo}">Registrar Nueva Carrera</c:if><c:if test="${!esNuevo}">Editar Carrera</c:if></h1>

    <form action="${pageContext.request.contextPath}/CarreraServlet" method="post">
        
        <%-- 2. Lógica para EDITAR --%>
        <c:if test="${!esNuevo}">
            <input type="hidden" name="idcarrera" value="${carrera.idcarrera}">
            <input type="hidden" name="action" value="actualizar">
        </c:if>
        
        <%-- 3. Lógica para REGISTRAR  --%>
        <c:if test="${esNuevo}">
            <input type="hidden" name="action" value="registrar">
        </c:if>

        <%-- Campo Nombre --%>
        <div class="form-group">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" value="${carrera.nombre}" required>
        </div>

        <%-- 4. Campo Select para Facultad (la lista viene del servlet) --%>
        <div class="form-group">
            <label for="facultad_idfacultad">Facultad:</label>
            <select id="facultad_idfacultad" name="facultad_idfacultad" required>
                <c:forEach var="facu" items="${listaFacultades}">
                    <%-- 5. Lógica para auto-seleccionar la facultad (igual que en alumnoForm) --%>
                    <option value="${facu.idfacultad}" 
                        ${facu.idfacultad == carrera.facultadIdfacultad.idfacultad ? 'selected' : ''}>
                        ${facu.nombre}
                    </option>
                </c:forEach>
            </select>
        </div>

        <%-- Botón dinámico --%>
        <input type="submit" value="${esNuevo ? 'Registrar Carrera' : 'Actualizar Carrera'}">
    </form>

    <br>
    <%-- Botón "Volver"  --%>
    <div style="text-align: center;">
        <a href="${pageContext.request.contextPath}/CarreraServlet" class="btn-enlace">Volver al Listado</a>
    </div>

</body>
</html>