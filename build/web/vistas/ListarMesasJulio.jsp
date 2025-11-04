<%-- 
    Document   : ListarMesasJulio
    Created on : 3 nov 2025, 7:43:25 p. m.
    Author     : martinLocal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mesas de Examen (Julio)</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">    
    </head>
<body>
    <h2>Mesas de Examen - Turno Julio</h2>
    
    <table border="1">
        <thead>
            <tr>
                <th>Alumno</th>
                <th>Nota</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${resultadosExamenes}">
                <tr>
                    <td>${item[0]}</td>
                    <td>${item[1]}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <p><a href="index.jsp" class="btn-enlace">Volver al inicio</a></p>
    
</body>
</html>

