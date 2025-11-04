<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>App Alumno</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">

    </head>
    <body>
        <h1>Gestión Académica</h1>
        <h2>Menú Principal</h2>
        <ul>
            <li><a href="${pageContext.request.contextPath}/AlumnoServlet">Gestionar Alumnos</a></li>
            <li><a href="${pageContext.request.contextPath}/CarreraServlet">Gestionar Carreras</a></li>
            <li><a href="${pageContext.request.contextPath}/MateriaServlet">Gestionar Materias</a></li>

            <!-- Menú dinámico de Facultades -->
            <li id="menu-facultades">
                <a href="#">Facultades</a>
                <ul>
                    <c:forEach var="facu" items="${facultades}">
                        <li><a href="Facultad?codigoFacu=${facu.idfacultad}">${facu.nombre}</a></li>
                    </c:forEach>
                </ul>
                    
            </li>
            <h3>Reportes</h3>
                <ul> <li><a href="ListarMesasJulio">Ver Mesas de Julio</a></li>
                    <li><a href="AlumnosSinRendir">Ver alumnos sin Rendir en ${anioActual}</a></li>
                    <li><a href="ListarDocentes">Ver docentes (+2 materias)</a></li>
                </ul>
            </li>
        </ul>
         
        <script src="js/menu.js"></script>

    </body>
</html>