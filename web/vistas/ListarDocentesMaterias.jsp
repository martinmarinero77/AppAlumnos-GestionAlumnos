<%-- 
    Document   : ListarDocentesMaterias
    Created on : 3 nov 2025, 10:00:55 p. m.
    Author     : martinLocal
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Reporte de Docentes</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>
<body>
    <h2>Docentes que dictan más de 2 materias</h2>
    
    <table border="1">
        <thead>
            <tr>
                <th>Nombre del Docente</th>
            </tr>
        </thead>
        <tbody>
            <%-- 'docentesConMasMaterias' es la variable del servlet --%>
            <%-- 'nombreDocente' es solo un String --%>
            <c:forEach var="nombreDocente" items="${docentesConMasMaterias}">
                <tr>
                    <td>${nombreDocente}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <p><a href="index.jsp" class="btn-enlace">Volver al inicio</a></p>
    
</body>
</html>
