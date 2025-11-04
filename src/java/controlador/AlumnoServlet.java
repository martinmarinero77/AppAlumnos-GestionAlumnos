package controlador;

// --- NUEVOS IMPORTS ---
import entidades.Alumno;
import entidades.Carrera;
import jakarta.ejb.EJB;
import sesiones.AlumnoFacade;
import sesiones.CarreraFacade;
// ------------------------

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Servlet modificado para usar EJB Facade en lugar de DAO.
 */
@WebServlet("/AlumnoServlet")
public class AlumnoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 1. Inyectamos los EJB Facades.
    @EJB
    private AlumnoFacade alumnoFacade;

    @EJB
    private CarreraFacade carreraFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "listar";
        }
        try {
            switch (action) {
                case "registrar":
                    registrarAlumno(request, response);
                    break;
                case "actualizar":
                    actualizarAlumno(request, response);
                    break;
                default:
                    listarAlumnos(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if (action == null) {
            action = "listar";
        }

        try {
            switch (action) {
                case "nuevo":
                    mostrarFormularioNuevo(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditar(request, response);
                    break;
                case "eliminar":
                    eliminarAlumno(request, response);
                    break;
                default:
                    listarAlumnos(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listarAlumnos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Alumno> listaAlumnos = alumnoFacade.findAll();
        request.setAttribute("lista", listaAlumnos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/alumnos.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Carrera> listaCarreras = carreraFacade.findAll();
        request.setAttribute("listaCarreras", listaCarreras);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/alumnoForm.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idalumno = Integer.parseInt(request.getParameter("id"));
        Alumno alumnoExistente = alumnoFacade.find(idalumno);
        List<Carrera> listaCarreras = carreraFacade.findAll();
        request.setAttribute("alumno", alumnoExistente);
        request.setAttribute("listaCarreras", listaCarreras);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/alumnoForm.jsp");
        dispatcher.forward(request, response);
    }

    private void registrarAlumno(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String nombre = request.getParameter("nombre");
        String registro = request.getParameter("registro");
        int carrera_idcarrera = Integer.parseInt(request.getParameter("carrera_idcarrera"));
        Carrera carreraDelAlumno = carreraFacade.find(carrera_idcarrera);

        Alumno nuevoAlumno = new Alumno();
        nuevoAlumno.setNombre(nombre);
        nuevoAlumno.setRegistro(registro);
        if (carreraDelAlumno != null) {
            nuevoAlumno.setCarreraIdcarrera(carreraDelAlumno);
        }
        
        alumnoFacade.create(nuevoAlumno);
        response.sendRedirect(request.getContextPath() + "/AlumnoServlet");
    }

    private void actualizarAlumno(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int idalumno = Integer.parseInt(request.getParameter("idalumnos"));
        String nombre = request.getParameter("nombre");
        String registro = request.getParameter("registro");
        int carrera_idcarrera = Integer.parseInt(request.getParameter("carrera_idcarrera"));
        Carrera carreraDelAlumno = carreraFacade.find(carrera_idcarrera);

        Alumno alumno = new Alumno();
        alumno.setIdalumnos(idalumno);
        alumno.setNombre(nombre);
        alumno.setRegistro(registro);
        if (carreraDelAlumno != null) {
            alumno.setCarreraIdcarrera(carreraDelAlumno);
        }

        alumnoFacade.edit(alumno);
        response.sendRedirect(request.getContextPath() + "/AlumnoServlet");
    }

    /**
     * MÉTODO ELIMINAR CORREGIDO
     * Ahora solo llama al Facade, que se encarga de la transacción.
     */
    private void eliminarAlumno(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        // 1. Obtenemos el ID
        int idalumno = Integer.parseInt(request.getParameter("id"));
        
        // 2. ¡Llamamos al nuevo método en el Facade!
        // (Esto asume que ya agregaste eliminarPorId a AlumnoFacade.java)
        alumnoFacade.eliminarPorId(idalumno); 
        
        // 3. Redirigimos
        response.sendRedirect(request.getContextPath() + "/AlumnoServlet");
    }
}