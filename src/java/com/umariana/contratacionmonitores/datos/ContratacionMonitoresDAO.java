package com.umariana.contratacionmonitores.datos;

import com.umariana.contratacionmonitores.excepciones.ExcepcionNoExiste;
import com.umariana.contratacionmonitores.excepciones.ExcepcionYaExiste;
import com.umariana.contratacionmonitores.logica.Administrador;
import com.umariana.contratacionmonitores.logica.Aspirante;
import com.umariana.contratacionmonitores.logica.Dependencia;
import com.umariana.contratacionmonitores.logica.Estudiante;
import com.umariana.contratacionmonitores.logica.Monitor;
import com.umariana.contratacionmonitores.logica.Resultado;
import java.beans.Statement;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Es la clase principal del paquete DATOS que se encarga de manejar de manejar las Bases de Datos del Sistema
 * @author Andres
 */
public class ContratacionMonitoresDAO {
    
    // -----------------------------------------------------------------
    // ATRIBUTOS
    // -----------------------------------------------------------------   
    /**
     * Es la conexion con la Base de datos de estudiantes de la Univerisidad
     */
    private Connection conexionUniversidad;
    /**
     * Es la conexion con la Base de datos del Sistema Contratacion de Monitores
     */
    private Connection conexionContratacionMonitores;
    /**
     * Es el Statement que conecta con la Base de datos de estudiantes de la Univerisidad
     */
    private Statement stBdUniversidad;
    /**
     * Es el Statement que conecta con la Base de datos del Sistema Contratacion de Monitores
     */
    private Statement stBdContratacionMonitores;
    
    private AspiranteDAO aspiranteDAO;
    private MonitorDAO monitorDAO;
    private ResultadoDAO resultadoDAO;
    private DependenciaDAO dependenciaDAO;   
    private EstudianteUniversidadDAO estudianteUDAO;
    private AdministradorDAO adminDAO;

    
    public ContratacionMonitoresDAO() {      
        //conectarBdSistema();
        
        aspiranteDAO = new AspiranteDAO();
        monitorDAO = new MonitorDAO();
        resultadoDAO = new ResultadoDAO();
        dependenciaDAO = new DependenciaDAO();
        adminDAO = new AdministradorDAO();
        estudianteUDAO = new EstudianteUniversidadDAO();
        
    }
    /**
     * Metodo que se encarga de realizar la conexion con la Base de Datos
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    public void conectarBdSistema() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
              
        String driver="org.postgresql.Driver";
        String conectionString="jdbc:postgresql";
        String bd="contratacionMonitores";
        String usuario="postgres";
        String password="123";
        String servidor="";//"192.168.0.13";//192.168.0.11
        int puerto=5432;   
        //stBdSistema = null ;
        
        Class.forName(driver).newInstance();

        this.conexionContratacionMonitores = DriverManager.getConnection(conectionString+":"+"//"+servidor+":"+puerto+"/"+bd,usuario,password);

        this.stBdContratacionMonitores=(Statement) conexionContratacionMonitores.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        System.out.println("Conectado con la base de datos del sistema contratacion de monitores...");
    }
    /**
     * Metodo que se encarga de realizar la conexion con la Base de Datos
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    public void conectarBdUniversidad() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
              
        String driver="org.postgresql.Driver";
        String conectionString="jdbc:postgresql";
        String bd="estudiantes";
        String usuario="postgres";
        String password="123";
        String servidor="";//"192.168.0.13";//192.168.0.11
        int puerto=5432;   

        
        Class.forName(driver).newInstance();

        this.conexionUniversidad = DriverManager.getConnection(conectionString+":"+"//"+servidor+":"+puerto+"/"+bd,usuario,password);

        this.stBdUniversidad=(Statement) conexionUniversidad.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        System.out.println("Conectado con la base de datos de estudiantes de la Universidad...");
    }
    
    
    /**
     * Metodo que se encarga de desconectar el sistema con la base de datos
     */
    public void desconectarBdContratacionMonitores(){
        try 
        {
            this.conexionContratacionMonitores.close();

            System.out.println("Desconectado...");
        } 
        catch (SQLException e) 
        {		
            System.out.println("Error al cerrar la conexion + "+e.toString());
        }
    }
    
    /**
     * Metodo que se encarga de desconectar el sistema con la base de datos
     */
    public void desconectarBdUniversidad(){
        try 
        {
            this.conexionUniversidad.close();

            System.out.println("Desconectado...");
        } 
        catch (SQLException e) 
        {		
            System.out.println("Error al cerrar la conexion + "+e.toString());
        }
    }
    
    public Aspirante registrarAspiranteEnBD(String identificacion) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, ExcepcionYaExiste{
        
        //conectarBdUniversidad();
        
        Estudiante estudiante = estudianteUDAO.buscarEstudiante(identificacion);
        Aspirante encontrado = null;
        if(estudiante==null)
            throw new ExcepcionYaExiste("La identificacion no scorresponde a ningun estudiante");
        else
            encontrado = aspiranteDAO.resgistrarAspiranteEnBD(estudiante);           
        
        //desconectarBdUniversidad();

        return encontrado;
        
    }
    public void actualizarAspiranteEnBD(){
        
    }    
    public void eliminarAspiranteEnBD(){
        
    }
    
    
    public void registrarMonitorEnBD(){
        
    }
    public void eliminarMonitorEnBD(){
        
    }
    public void registrarResultadoEnBD(){
        
    }
     public void eliminarResultadoEnBD(){
        
    }
    public void registrarDependenciaEnBD(){
        
    }
    public void eliminarDependenciaEnBD(){
        
    }
    
    /**
     * Metodo que retorna la lista de aspirantes que estan registrados en la base de datos del sistema
     * @return ArrayList<Aspirante>
     */
    public ArrayList<Aspirante> darAspirantesRegistrados(){
    
        return new ArrayList<Aspirante>();
    }
    /**
     * Metodo que retorna la lista de Monitores que estan registrados en la base de datos del sistema
     * @return ArrayList<Monitor>
     */
    public ArrayList<Monitor> darMonitoresRegistrados(){
    
        return new ArrayList<Monitor>();
    }
    /**
     * Metodo que retorna la lista de Resultados que estan registrados en la base de datos del sistema
     * @return ArrayList<Resultado>
     */
    public ArrayList<Resultado> darResultadosRegistrados(){
    
        return new ArrayList<Resultado>();
    }
    /**
     * Metodo que retorna la lista de Dependenciaa que estan registradas en la base de datos del sistema
     * @return ArrayList<Dependencia>
     */
    public ArrayList<Dependencia> darDependenciasRegistrados(){  
        return new ArrayList<Dependencia>();
    }

    public Estudiante buscarEstudiante(String identificacion) throws ExcepcionNoExiste {
        Estudiante estudiante = estudianteUDAO.buscarEstudiante(identificacion); 
        if(estudiante!=null)
            return  estudiante;   
        else
            throw  new ExcepcionNoExiste("La identificación: "+ identificacion+" no corresponde a ningun estudiante de la Universidad");
    }

    public Administrador buscarAdministrador(String usuario, String password) throws ExcepcionNoExiste {
        return adminDAO.buscarAdministrador(usuario, password);
    }
    
}
