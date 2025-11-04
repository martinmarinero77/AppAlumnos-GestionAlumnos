<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Carreras de ${facultad.nombre}</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>
<body>

    <h1>Facultad: ${facultad.nombre}</h1>
    <h2>Carreras Disponibles</h2>

    <ul>
        <c:forEach var="carrera" items="${facultad.carreraCollection}">
            <li>${carrera.nombre}</li>
        </c:forEach>
    </ul>

    <br/>
    <a href="index.jsp" class="btn-enlace">Volver al Menú Principal</a>

</body>
</html>
