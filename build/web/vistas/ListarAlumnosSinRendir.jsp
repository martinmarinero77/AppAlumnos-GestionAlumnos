<%-- 
    Document   : ListarAlumnosSinRendir
    Created on : 3 nov 2025, 8:23:28 p. m.
    Author     : martinLocal
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Alumnos sin Exámenes</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>
<body>
   <h2>Alumnos que no rindieron exámenes en ${anioActual}</h2>
    
    <table border="1">
        <thead>
            <tr>
                <th>ID Alumno</th>
                <th>Nombre</th>
            </tr>
        </thead>
        <tbody>
            <%-- El 'var' es 'alumno' y 'items' es el nombre que pondremos en el servlet --%>
            <c:forEach var="alumno" items="${alumnosSinRendir}">
                <tr>
                    <td>${alumno.idalumnos}</td>
                    <td>${alumno.nombre}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <p><a href="index.jsp" class="btn-enlace">Volver al inicio</a></p>
    
</body>
</html>
