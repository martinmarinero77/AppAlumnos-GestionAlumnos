package controlador;

import entidades.Alumno;
import entidades.Facultad;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sesiones.FacultadFacade;

import java.io.IOException;
import java.time.Year;
import java.util.List;
import sesiones.AlumnoFacade;
import sesiones.DocenteFacade;
import sesiones.ExamenFacade;

/**
 * Servlet controlador principal que se carga al inicio de la aplicación.
 */
@WebServlet(name = "Manejador", 
            urlPatterns = {"/Manejador",
                           "/Facultad",
                           "/ListarMesasJulio",
                           "/AlumnosSinRendir",
                           "/ListarDocentes"}, 
            loadOnStartup = 1)
public class ManejadorServlet extends HttpServlet {

    @EJB
    private FacultadFacade facu;
    
    @EJB
    private ExamenFacade examenFacade;
    
    @EJB
    private AlumnoFacade alumnoFacade;
    
    @EJB
    private DocenteFacade docenteFacade;

    @Override
    public void init() throws ServletException {
        // Almacena la lista de facultades en el contexto del Servlet
        getServletContext().setAttribute("facultades", facu.findAll());
        getServletContext().setAttribute("anioActual", Year.now().getValue());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String path = request.getServletPath();
        String url; // Variable para guardar la URL de destino
        switch (path) {
            case "/Facultad":
                Integer nroFacultad = Integer.parseInt(request.getParameter("codigoFacu"));
                Facultad miFacu = facu.find(nroFacultad);
                request.setAttribute("facultad", miFacu);
                url = "/vistas/ListarCarreras.jsp";
                request.getRequestDispatcher(url).forward(request, response);
                break;
                
            case "/ListarMesasJulio":
                // 1. Llamamos al método que creaste en el Facade
                List<Object[]> resultados = examenFacade.findExamenesJulioConNotas();
                // 2. Guardamos los resultados para que el JSP los vea
                request.setAttribute("resultadosExamenes", resultados);
                // 3. Definimos el JSP de destino
                url = "/vistas/ListarMesasJulio.jsp";
                request.getRequestDispatcher(url).forward(request, response);
                break;
                
            case "/AlumnosSinRendir":                
                // 1. Llamamos al método que creaste en AlumnoFacade
                List<Alumno> alumnos = alumnoFacade.findAlumnosSinExamenesAnioActual();
                // 2. Guardamos la lista para que el JSP la vea
                request.setAttribute("alumnosSinRendir", alumnos);
                // 3. Definimos el JSP de destino
                url = "/vistas/ListarAlumnosSinRendir.jsp";
                request.getRequestDispatcher(url).forward(request, response);
                break;
                
            case "/ListarDocentes":
                // 1. Llamamos al método de DocenteFacade
                List<String> docentes = docenteFacade.findDocentesConMasDeDosMaterias();
                // 2. Guardamos la lista de nombres para que el JSP la vea
                request.setAttribute("docentesConMasMaterias", docentes);
                // 3. Definimos el JSP de destino
                url = "/vistas/ListarDocentesMaterias.jsp"; 
                request.getRequestDispatcher(url).forward(request, response);
                break;
            
            default:
                // Comportamiento por defecto
                response.sendRedirect("index.jsp");
                break;
        } 
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // De momento, este servlet solo inicializa datos.
    }
}
