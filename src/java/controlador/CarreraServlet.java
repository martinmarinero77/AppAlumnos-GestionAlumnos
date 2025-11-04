package controlador;

// --- NUEVOS IMPORTS ---
import entidades.Carrera;
import entidades.Facultad; // Necesario para los formularios
import jakarta.ejb.EJB;
import sesiones.CarreraFacade;
import sesiones.FacultadFacade; // Necesario para los formularios
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
@WebServlet("/CarreraServlet")
public class CarreraServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 1. Inyectamos los EJB Facades
    @EJB
    private CarreraFacade carreraFacade;

    @EJB
    private FacultadFacade facultadFacade; // Para los dropdowns del formulario

    // 2. El método init() ya no es necesario (el servidor se encarga con @EJB)

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
                    registrarCarrera(request, response);
                    break;
                case "actualizar":
                    actualizarCarrera(request, response);
                    break;
                default:
                    listarCarreras(request, response);
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
                    eliminarCarrera(request, response);
                    break;
                default:
                    listarCarreras(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listarCarreras(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // CAMBIO: Usamos el Facade y su método estándar
        List<Carrera> listaCarreras = carreraFacade.findAll(); 
        request.setAttribute("listaCarreras", listaCarreras);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/carreras.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // MEJORA: Cargamos la lista de facultades para el dropdown <select>
        List<Facultad> listaFacultades = facultadFacade.findAll();
        request.setAttribute("listaFacultades", listaFacultades);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/carreraForm.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idcarrera = Integer.parseInt(request.getParameter("id"));
        
        // CAMBIO: Usamos el Facade y su método estándar
        Carrera carreraExistente = carreraFacade.find(idcarrera); 
        
        // MEJORA: Cargamos la lista de facultades para el dropdown <select>
        List<Facultad> listaFacultades = facultadFacade.findAll();
        
        request.setAttribute("carrera", carreraExistente);
        request.setAttribute("listaFacultades", listaFacultades);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/carreraForm.jsp");
        dispatcher.forward(request, response);
    }

    private void registrarCarrera(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String nombre = request.getParameter("nombre");
        int facultad_idfacultad = Integer.parseInt(request.getParameter("facultad_idfacultad"));
        
        // CAMBIO: Buscamos la entidad Facultad completa
        Facultad facultad = facultadFacade.find(facultad_idfacultad);

        // CAMBIO: Creamos la entidad y usamos setters (ya no pasamos IDs al constructor)
        Carrera nuevaCarrera = new Carrera();
        nuevaCarrera.setNombre(nombre);
        
        // Asignamos la entidad Facultad, no el ID
        if (facultad != null) {
            // Asumo que tu entidad Carrera tiene este setter (revisa el nombre si da error)
            nuevaCarrera.setFacultadIdfacultad(facultad); 
        }
        
        carreraFacade.create(nuevaCarrera); // CAMBIO
        response.sendRedirect(request.getContextPath() + "/CarreraServlet");
    }

    private void actualizarCarrera(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Asumo que el ID de la carrera viene en un campo oculto "idcarrera"
        int idcarrera = Integer.parseInt(request.getParameter("idcarrera")); 
        String nombre = request.getParameter("nombre");
        int facultad_idfacultad = Integer.parseInt(request.getParameter("facultad_idfacultad"));

        // CAMBIO: Buscamos la entidad Facultad completa
        Facultad facultad = facultadFacade.find(facultad_idfacultad);

        // CAMBIO: Creamos la entidad y usamos setters
        Carrera carrera = new Carrera();
        carrera.setIdcarrera(idcarrera); // Asumo que el setter es setIdcarrera
        carrera.setNombre(nombre);
        
        if (facultad != null) {
            carrera.setFacultadIdfacultad(facultad);
        }
        
        carreraFacade.edit(carrera); // CAMBIO
        response.sendRedirect(request.getContextPath() + "/CarreraServlet");
    }

    private void eliminarCarrera(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int idcarrera = Integer.parseInt(request.getParameter("id"));
        
        // CAMBIO: Usamos el mismo patrón de AlumnoServlet
        // (Esto requiere que agregues 'eliminarPorId' a tu CarreraFacade)
        carreraFacade.eliminarPorId(idcarrera); 
        
        response.sendRedirect(request.getContextPath() + "/CarreraServlet");
    }
}