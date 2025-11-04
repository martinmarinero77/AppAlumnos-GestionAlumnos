package controlador;

// --- NUEVOS IMPORTS ---
import entidades.Materia;
import jakarta.ejb.EJB;
import sesiones.MateriaFacade;
// ------------------------

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/MateriaServlet")
public class MateriaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 1. Inyectamos el EJB Facade
    @EJB
    private MateriaFacade materiaFacade;

    // 2. El método init() ya no es necesario

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
                    registrarMateria(request, response);
                    break;
                case "actualizar":
                    actualizarMateria(request, response);
                    break;
                default:
                    listarMaterias(request, response);
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
                    eliminarMateria(request, response);
                    break;
                default:
                    listarMaterias(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listarMaterias(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Materia> listaMaterias = materiaFacade.findAll();
        request.setAttribute("listaMaterias", listaMaterias);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/materias.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // El formulario de materia es simple, no necesita listas de otras entidades
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/materiaForm.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idmateria = Integer.parseInt(request.getParameter("id"));
        Materia materiaExistente = materiaFacade.find(idmateria);
        request.setAttribute("materia", materiaExistente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/materiaForm.jsp");
        dispatcher.forward(request, response);
    }

    private void registrarMateria(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String nombre = request.getParameter("nombre");

        Materia nuevaMateria = new Materia();
        nuevaMateria.setNombre(nombre);
        // Aquí podrías setear otros campos si los tuvieras (ej: año)
        
        materiaFacade.create(nuevaMateria);
        response.sendRedirect(request.getContextPath() + "/MateriaServlet");
    }

    private void actualizarMateria(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int idmateria = Integer.parseInt(request.getParameter("idmateria"));
        String nombre = request.getParameter("nombre");

        Materia materia = new Materia();
        materia.setIdmateria(idmateria); // Asumo este es el setter del ID
        materia.setNombre(nombre);
        
        materiaFacade.edit(materia);
        response.sendRedirect(request.getContextPath() + "/MateriaServlet");
    }

    private void eliminarMateria(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int idmateria = Integer.parseInt(request.getParameter("id"));
        
        // Asume que agregarás 'eliminarPorId' a tu MateriaFacade
        materiaFacade.eliminarPorId(idmateria); 
        
        response.sendRedirect(request.getContextPath() + "/MateriaServlet");
    }
}