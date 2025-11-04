<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 1. IMPORTAMOS LA LIBRERÍA JSTL "CORE" (c) --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Alumnos</title>
   <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

    <h1>Listado de Alumnos</h1>

    <div style="margin-bottom: 20px;">
        <a href="${pageContext.request.contextPath}/AlumnoServlet?action=nuevo" class="btn-enlace">Registrar Nuevo Alumno</a>
    </div>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Registro</th>
                <%-- 2. CAMBIAMOS LOS TÍTULOS DE LAS COLUMNAS --%>
                <th>Carrera</th>
                <th>Facultad</th> 
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <%-- 3. USAMOS JSTL (c:forEach) EN LUGAR DE SCRIPLETS --%>
            <%-- El servlet nos pasó la variable "lista" --%>
            <c:forEach var="a" items="${lista}">
                <tr>
                    <%-- 4. USAMOS EXPRESSION LANGUAGE (EL) ${...} PARA MOSTRAR DATOS --%>
                    
                   
                    <td>${a.idalumnos}</td> 
                    
                    <td>${a.nombre}</td>
                    <td>${a.registro}</td>
                    
                    <%-- a.getCarreraIdcarrera().getNombre() --%>
                    <td>${a.carreraIdcarrera.nombre}</td> 
                    
                    <%-- a.getCarreraIdcarrera().getFacultadIdfacultad().getNombre() --%>
                    <td>${a.carreraIdcarrera.facultadIdfacultad.nombre}</td>
                    
                    <td class="actions">
                        <%-- 6. CORREGIMOS EL LINK DE ELIMINAR --%>
                        <a href="${pageContext.request.contextPath}/AlumnoServlet?action=editar&id=${a.idalumnos}">Editar</a>
                        <a href="${pageContext.request.contextPath}/AlumnoServlet?action=eliminar&id=${a.idalumnos}" 
                           class="delete-link"
                           onclick="return confirm('¿Estás seguro de que deseas eliminar a este alumno?');">
                           Eliminar
                        </a>
                    </td>
                </tr>
            </c:forEach>
            
            <%-- 7. FORMA JSTL DE MANEJAR UNA LISTA VACÍA --%>
            <c:if test="${empty lista}">
                <tr>
                    <td colspan="6">No hay alumnos registrados.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/index.jsp">Volver al Menú Principal</a>

</body>
</html>