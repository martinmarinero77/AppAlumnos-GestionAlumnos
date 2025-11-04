<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario de Alumno</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <c:set var="esNuevo" value="${empty alumno}" />

    <h1><c:if test="${esNuevo}">Registrar Nuevo Alumno</c:if><c:if test="${!esNuevo}">Editar Alumno</c:if></h1>

    <form action="${pageContext.request.contextPath}/AlumnoServlet" method="post">
        
        <c:if test="${!esNuevo}">
            <input type="hidden" name="idalumnos" value="${alumno.idalumnos}">
            <input type="hidden" name="action" value="actualizar">
        </c:if>
        <c:if test="${esNuevo}">
            <input type="hidden" name="action" value="registrar">
        </c:if>

        <div class="form-group">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" value="${alumno.nombre}" required>
        </div>

        <div class="form-group">
            <label for="registro">Registro:</label>
            <input type="text" id="registro" name="registro" value="${alumno.registro}" required>
        </div>

        <div class="form-group">
            <label for="carrera_idcarrera">Carrera:</label>
            <select id="carrera_idcarrera" name="carrera_idcarrera" required>
                <c:forEach var="carrera" items="${listaCarreras}">
                    <option value="${carrera.idcarrera}" ${carrera.idcarrera == alumno.carreraIdcarrera.idcarrera ? 'selected' : ''}>
                        ${carrera.nombre}
                    </option>
                </c:forEach>
            </select>
        </div>

        <input type="submit" value="${esNuevo ? 'Registrar' : 'Actualizar'}">
    </form>

    <br>
    <div style="text-align: center;">
        <a href="${pageContext.request.contextPath}/AlumnoServlet" class="btn-enlace">Volver al Listado</a>
    </div>

</body>
</html>
