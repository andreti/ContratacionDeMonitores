package com.umariana.contratacionmonitores.logica;

import com.umariana.contratacionmonitores.datos.ContratacionMonitoresDAO;
import com.umariana.contratacionmonitores.excepciones.ExcepcionNoExiste;
import com.umariana.contratacionmonitores.excepciones.ExcepcionYaExiste;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
* Es la clase principal del sistema Contratacion de Monitores
* @author CocoSoft
*/
public class ContratacionMonitores {
    //
    //ATRIBUTOS
    //
    /**
     * Es la lista de dependencias existentes en el sistema
     */
    private ArrayList<Dependencia> dependencias;
    /**
     * Es la lista de monitores existentes en el sistema
     */
    private ArrayList<Monitor> monitores;
    /**
     * Es la lista de resultados existentes en el sistema
     */
    private ArrayList<Resultado> resultados;
    /**
     * Es la lista de aspirantes al cargo de monitor
     */
    private ArrayList<Aspirante> aspirantes;

    /**
     * Es la comunicacion con la clase que controla la base de datos
     */
    private ContratacionMonitoresDAO  cmDAO;
    //
    //CONSTRUCTOR
    //
    /**
     * Es el constructor de la clase Principal
     */
    public ContratacionMonitores() 
    {             
        
        cmDAO = new ContratacionMonitoresDAO();
        aspirantes   =  cmDAO.darAspirantesRegistrados();
        resultados   =  cmDAO.darResultadosRegistrados();
        monitores    =  cmDAO.darMonitoresRegistrados();
        dependencias =  cmDAO.darDependenciasRegistrados();   
        
        
        registrosDePrueba();
        
       
        
        
        
    }           
    //
    //METODOS
    //
    void registrosDePrueba(){
        try {
            registrarAspirante("aspirante1","segundoNombre", "primerApellido","segundoApellido", 1, "estadoMatricula", null,1.0,1,"101");
            registrarAspirante("aspirante2","segundoNombre2", "primerApellido2","segundoApellido2", 2, "estadoMatricula2", null,2.0,2,"102");
            registrarAspirante("aspirante3","segundoNombre3", "primerApellido3","segundoApellido3", 3, "estadoMatricula3", null,3.0,3,"103");
            registrarAspirante("aspirante4","segundoNombre3", "primerApellido3","segundoApellido3", 4, "estadoMatricula3", null,3.0,3,"104");
            
            
            registrarAspirante("aspirante5","segundoNombre", "primerApellido","segundoApellido", 5, "estadoMatricula", null,1.0,1,"105");
            registrarAspirante("aspirante6","segundoNombre6", "primerApellido","segundoApellido2", 6, "estadoMatricula", null,2.0,2,"106");
            registrarAspirante("aspirante7","segundoNombre7", "primerApellido3","segundoApellido", 7, "estadoMatricula", null,3.0,3,"107");
            registrarAspirante("aspirante8","segundoNombre8", "primerApellido","segundoApellido", 8, "estadoMatricula", null,3.0,3,"108");
            
            /*registrarMonitor("monitor1","segundoNombre", "primerApellido","segundoApellido", 1, "estadoMatricula", null,1.0,1,"105");
            registrarMonitor("monitor2","segundoNombre2", "primerApellido2","segundoApellido2", 2, "estadoMatricula2", null,2.0,2,"106");
            registrarMonitor("monitor3","segundoNombre3", "primerApellido3","segundoApellido3", 3, "estadoMatricula3", null,3.0,3,"107");
            registrarMonitor("monitor4","segundoNombre3", "primerApellido3","segundoApellido3", 3, "estadoMatricula3", null,3.0,3,"108");    */                      
            
            agregarDependencia("Cod1", "Dependencia 1", "Esta es la Dependencia 1", "Tarde", 5);        
            agregarDependencia("Cod2", "Dependencia 2", "Esta es la Dependencia 2", "Mañana", 4);
            agregarDependencia("Cod3", "Dependencia 3", "Esta es la Dependencia 3", "Tarde", 3);
            agregarDependencia("Cod4", "Dependencia 4", "Esta es la Dependencia 4", "Mañana", 2);
            
            agregarPostulacionAspirante("101", "Cod1");
            agregarPostulacionAspirante("101", "Cod2");
            agregarPostulacionAspirante("102", "Cod2");
            
            pasarAspiranteAMonitor("105", "Cod1");
            pasarAspiranteAMonitor("106", "Cod2");
      
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Retorna la lista de monitores
     * @return monitores
     */
    public ArrayList<Monitor> darMonitores() 
    {
        return monitores;
    }

    /**
     * Cambia la lista de monitores por la nueva lista
     * @param monitores ArrayList<Monitor>
     */
    public void cambiarMonitores(ArrayList<Monitor> monitores) 
    {
        this.monitores = monitores;
    }

    /**
     * Retorna la lista de dependencias
     * @return dependencias
     */
    public ArrayList<Dependencia> darDependencias() 
    {
        return dependencias;
    }

    /**
     * Cambia la lista de dependencias por la nueva lista
     * @param dependencias ArrayList<Dependencia>
     */
    public void cambiarDependencias(ArrayList<Dependencia> dependencias) 
    {
        this.dependencias = dependencias;
    }

    /**
     * Retorna la lista de resultados
     * @return  resultados
     */
    public ArrayList<Resultado> darResultados() 
    {
        return resultados;
    }

    /**
     * Cambia la lista de resultados por la nueva lista
     * @param resultados ArrayList<Resultado>
     */
    public void cambiarResultados(ArrayList<Resultado> resultados) 
    {
        this.resultados = resultados;
    }

    /**
     * Retorna la lista de aspirantes
     * @return aspirantes ArrayList<Aspirante>
     */
    public ArrayList<Aspirante> darAspirantes() 
    {
        return aspirantes;
    }

    /**
     * Cambia la lista de aspirantes por la lista nueva
     * @param aspirantes ArrayList<Aspirante>
     */
    public void cambiarAspirantes(ArrayList<Aspirante> aspirantes) 
    {
        this.aspirantes = aspirantes;
    }
    /**
     * El metodo se encarga de verificar si el estudiante es un aspirante, monitor o no esta registrado 
     * pasandole como parametro unicamente la identificacion
     * @param identificacion != null && !=""
     * @return  Retorna 1 en caso de que la cedula pertenesca a un estudiante que es aspirante existente en el sistema
     *          Retorna 2 en caso de que la cedula pertenesca a un estudiante que es monitor existente en el sistema
     *          Retorna 3 en caso de que la cedula pertenesca a un estudiante que no este registrado en el sistema
     *          Retorna 4 en caso de que la cedula NO pertenesca a ningun estudiante de la universidad
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ExcepcionNoExiste lanza la excepcion si la cedula no esta en la base de datos de estudiantes de la univeridad
     */
    public int ingreso(String identificacion) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, ExcepcionNoExiste{
        
        Aspirante existeAspirante = buscarAspirante(identificacion );
        if( existeAspirante != null )
            return 1;
          
        Monitor monitorExiste = buscarMonitor(identificacion );
        if( monitorExiste != null )
            return 2;      
        
        Estudiante estudiante = buscarEstudianteUniversidad(identificacion);
        if(estudiante!=null)
            return 3;
    
        return 4;
    }
    /**
     * El metodo se encarga de pasar un aspirante que paso las pruebas a un monitor con su respectiva dependencia
     * @param identificacion != null && !=""
     * @param codDependencia != null && !=""
     * @throws ExcepcionNoExiste Lanza la excepcion en caso de que el aspirante no esta registrado en el sistema y tambien si la dependencia no esta registrada
     * @throws ExcepcionYaExiste Lanza la excepcion en caso de que el estudiante ya esta registrado como monitor
     * @throws Exception 
     */
    public void pasarAspiranteAMonitor(String identificacion , String codDependencia) throws ExcepcionNoExiste, ExcepcionYaExiste, Exception{        
        Aspirante aspirante= buscarAspirante(identificacion);
        if(aspirante==null)
            throw  new ExcepcionNoExiste("El aspirante no esta registrado en el sistema");
        else
        {
            Monitor monitor=buscarMonitor(identificacion);
            if(monitor!=null)
                throw  new ExcepcionYaExiste("El estudiante con la identificaicon : "+ identificacion+" ya se encuentra registrado como monitor");
            else
            {
                Dependencia dependencia= buscarDependencia(codDependencia);
                if(dependencia==null)
                    throw  new ExcepcionNoExiste("La dependencia que desea agregar al monitor no existe");
                else{
                    monitor= new Monitor(aspirante.darPrimerNombre(), aspirante.darSegundoNombre(),   
                                         aspirante.darPrimerApellido(), aspirante.darSegundoApellido(),
                                         aspirante.darCodigo(), aspirante.darEstadoMatricula(), 
                                         aspirante.darFoto() ,aspirante.darPromedioAcumulado(), 
                                         aspirante.darSemestreActual(), identificacion, dependencia);                   
                    eliminarAspirante(identificacion);                  
                    monitores.add(monitor);
                    cmDAO.registrarMonitorEnBD();

                }
            }
        }           
    }
    /**
     * 
     * @param identificacion
     * @return
     * @throws ExcepcionYaExiste
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    public Aspirante registrarAspirante2(String identificacion) throws ExcepcionYaExiste, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        Aspirante aspirante = buscarAspirante(identificacion);
        if(aspirante!=null)
            throw new ExcepcionYaExiste("El estudiante ya esta registrado como aspirante!!");
        
        Monitor monitor = buscarMonitor(identificacion);
        if(monitor!=null)
            throw new ExcepcionYaExiste("El estudiante ya esta registrado como monitor");           
        
        aspirante = cmDAO.registrarAspiranteEnBD(identificacion);
        aspirantes.add(aspirante);
        return aspirante;
    }
    
    public Estudiante buscarEstudianteSistema(String identificacion) throws ExcepcionNoExiste{
        Aspirante aspirante = buscarAspirante(identificacion);
        if(aspirante!=null)
            return aspirante;
        
        Monitor monitor = buscarMonitor(identificacion);
        if(monitor!=null)
            return monitor;
        
        if(aspirante== null && monitor==null)
            throw  new ExcepcionNoExiste("El estudiante con identificaicon : "+identificacion+" no existe!!");
        return null;
    }
    /**
     * El metodo se encarga de registrar un aspirante en el sistema
     * <PostCondiciones> Se registro correctamente el aspirante al sistema 
     * @param primerNombre != null && != ""
     * @param segundoNombre
     * @param primerApellido != null && != ""
     * @param segundoApellido 
     * @param codigo != null && != ""
     * @param estadoMatricula != null && != ""
     * @param foto 
     * @param promedioAcumulado > 0 && <=5
     * @param semestreActual > 0 && <=10
     * @param identificacion != null && != ""
     * @throws Exception 
     */
    public void registrarAspirante(String primerNombre,String segundoNombre , String primerApellido, String segundoApellido, int codigo, String estadoMatricula, File foto, double promedioAcumulado, int semestreActual, String identificacion)throws Exception
    {
            Aspirante nuevo = buscarAspirante(identificacion );
            if( nuevo != null )
            {
                //throw new Exception("El Estudiante que desea registrar ya existe !!");
            }
            else{      
                nuevo= new Aspirante(primerNombre, segundoNombre, primerApellido, segundoApellido, codigo, estadoMatricula, foto, promedioAcumulado, semestreActual, identificacion);
                aspirantes.add( nuevo );
            }
    }
    
     /**
     * El metodo se encarga de modificar un aspirante en el sistema
     * <PostCondiciones> Se modifico correctamente el usuario al sistema 
     * @param primerNombre!= null && != ""
     * @param segundoNombre
     * @param primerApellido != null && != ""
     * @param segundoApellido
     * @param identificacion != null && != ""
     * @param foto
     * @param semestre > 0 && <=10
     * @throws Exception 
     */
    public void modificarAspirante(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String identificacion, File foto, int semestre) throws Exception
    {
        Aspirante modificar = buscarAspirante(identificacion );
        if( modificar == null )
        {
            throw new Exception("El Estudiante que desea modificar no existe");
        }
        else
        {
            modificar.cambiarPrimerNombre(primerNombre);
            modificar.cambiarSegundoNombre(segundoNombre);
            modificar.cambiarPrimerApellido(primerApellido);  
            modificar.cambiarSegundoApellido(segundoApellido);  
            modificar.cambiarFoto(foto);
            modificar.cambiarSemestreActual(semestre);                    
        }
    }
     /**
     * El metodo se encarga de modificar un Monitor en el sistema
     * <PostCondiciones> Se modifico correctamente el usuario en el sistema 
     * @param primerNombre!= null && != ""
     * @param segundoNombre
     * @param primerApellido != null && != ""
     * @param segundoApellido
     * @param identificacion != null && != ""
     * @param foto
     * @param semestre > 0 && <=10
     * @param promedioAcum > 0 && <= 5
     * @throws Exception 
     */
    public void modificarMonitor(String primerNombre,String segundoNombre , String primerApellido, String segundoApellido, String identificacion, File foto, int semestre, double promedioAcum  ) throws Exception
    {
        Monitor modificar = buscarMonitor(identificacion );
        if( modificar == null )
        {
            throw new Exception("El Estudiante que desea modificar no existe");
        }
        else
        {
            modificar.cambiarPrimerNombre(primerNombre);
            modificar.cambiarSegundoNombre(segundoNombre);
            modificar.cambiarPrimerApellido(primerApellido);  
            modificar.cambiarSegundoApellido(segundoApellido);  
            modificar.cambiarFoto(foto);
            modificar.cambiarPromedioAcumulado(promedioAcum);
            modificar.cambiarSemestreActual(semestre);                    
        }
    }
     /**
     * El metodo se encarga de registrar un monitor en el sistema
     * <PostCondiciones> Se registro correctamente el monitor al sistema 
     * @param primerNombre != null && != ""
     * @param segundoNombre
     * @param primerApellido != null && != ""
     * @param segundoApellido 
     * @param codigo != null && != ""
     * @param estadoMatricula != null && != ""
     * @param foto 
     * @param promedioAcumulado > 0 && <=5
     * @param semestreActual > 0 && <=10
     * @param identificacion != null && != ""
     * @throws Exception 
     */
    public void registrarMonitor(String primerNombre,String segundoNombre , String primerApellido, String segundoApellido, int codigo, String estadoMatricula, File foto, double promedioAcumulado, int semestreActual, String identificacion)throws Exception
    {
            Monitor nuevo = buscarMonitor(identificacion );
            if( nuevo != null )
            {
                //throw new Exception("El Estudiante que desea registrar ya existe !!");
            }
            else{      
                nuevo= new Monitor(primerNombre, segundoNombre, primerApellido, segundoApellido, codigo, estadoMatricula, foto, promedioAcumulado, semestreActual, identificacion, null);
                monitores.add( nuevo );
            }
    }
    /**
     * El metodo elimina un aspirante registrado en el sistema
     * @param identificacion != null && != ""
     */
    public void eliminarAspirante(String identificacion) throws Exception
    {
        Aspirante eliminar = buscarAspirante(identificacion );
            if( eliminar != null )
            {                   
                aspirantes.remove(eliminar);
                cmDAO.eliminarAspiranteEnBD();
            }
            else
            {
                throw new Exception("El Estudiante que desea eliminar no existe!!!");
            }		
    }
    /**
     * El metodo elimina un monitor registrado en el sistema
     * @param identificacion != null && != ""
     */
    public void eliminarMonitor(String identificacion) throws Exception
    {
        Monitor eliminar = buscarMonitor(identificacion );
            if( eliminar != null )
            {                   
                monitores.remove(eliminar);
            }
            else
            {
                throw new Exception("El Estudiante que desea eliminar no existe!!!");
            }		
    }
    
    /**
     * El metodo busca un aspirante dado su identificacion
     * @param identificacion != null && != ""
     * @return Aspirante
     */
    public Aspirante buscarAspirante(String identificacion)
    {
        Aspirante aspiranteBuscado = null;
        for (int i=0;i < aspirantes.size() ;i++ ) {
            aspiranteBuscado = aspirantes.get(i);
            if(aspiranteBuscado.darIdentificacion().equals(identificacion))
            {              
                return aspiranteBuscado;
            }
        }

        return null;
    }    
    
    /**
     * El metodo se encarga de agregar una postulacion al aspirante
     * @param identificacion != null && !=""
     * @param idDependencia != null && !=""
     * @throws ExcepcionYaExiste Lanza la excepcion si la postulacion que desea quitar no existe
     * @throws ExcepcionNoExiste Lanza la excepcion si si el aspirante no existe
     */
    public void agregarPostulacionAspirante(String identificacion, String idDependencia) throws ExcepcionYaExiste, ExcepcionNoExiste{
        Aspirante aspirante = buscarAspirante(identificacion);
        if(aspirante!=null){ 
            Dependencia buscada= buscarDependencia(idDependencia);
            if(buscada!=null)
                aspirante.agregarPostulacion(buscada.darId(), new Date(), identificacion);
        }
        else
            throw new ExcepcionNoExiste("No se puede agregar la postulacion al aspirante con identificacion :"+identificacion);
    }
    
    /**
     * El metodo se encarga de quitar una postulacion al aspirante
     * @param identificacion
     * @param idDependencia
     * @throws ExcepcionNoExiste 
     */
    public void quitarPostualacionAspirante(String identificacion, String idDependencia) throws ExcepcionNoExiste{
        Aspirante aspirante= buscarAspirante(identificacion);
        if(aspirante!=null){    
            Dependencia buscada= buscarDependencia(idDependencia);
            if(buscada!=null)
                aspirante.quitarPostulacion(buscada.darId());
        }
        else
            throw new ExcepcionNoExiste("No se puede quitar la postulacion al aspirante con identificacion :"+identificacion);
    }
    /**
     * El metodo busca un monitor dado su identificacion
     * @param identificacion != null && != ""
     * @return Monitor
     */
    public Monitor buscarMonitor(String identificacion)
    {
        Monitor monitorBuscado = null;
        for (int i=0;i < monitores.size() ;i++ ) {
            monitorBuscado = monitores.get(i);
            if(monitorBuscado.darIdentificacion().equals(identificacion))
            {              
                return monitorBuscado;
            }
        }

        return null;
    }    

    /**
     * El metodo se encarga de agregar una Dependencia en el sistema
     * <PostCondiciones> Se agrego correctamente la Dependencia en el sistema 
     * @param nombre != null && != ""e
     * @param apellido != null && != ""
     * @param identificacion != null && != ""
     * @param foto != null
     * @param semestre > 0 && <=10
     * @param promedioAcum > 0 && <= 5
     */
    public void agregarDependencia(String nId, String nNombre, String nDescripcion, String nHorario, int cupos)throws Exception
    {
            Dependencia buscarDep = buscarDependencia(nId);
            if( buscarDep != null )
            {
                throw new Exception("La Dependencia que desea agregar ya existe !!");
            }
            else
            {
                Dependencia nuevaDependencia = new Dependencia( nId, nNombre, nDescripcion, nHorario, cupos);
                dependencias.add( nuevaDependencia );
            }
    }
    /**
     * Metodo que se encarga de eliminar una dependencia del sistema
     * @param codigo != null && !=""
     * @throws Exception 
     */
    public void eliminarDependencia(String codigo) throws Exception{
        
        Dependencia dependenciaEliminar= buscarDependencia(codigo);
        
        if(dependenciaEliminar==null){
            //throw new Exception("La Dependencia que desea buscar no existe !!");
        }
        else
            dependencias.remove(dependenciaEliminar);      
    }

    /**
    * Metodo que se encarga de buscar una dependencia segun su codigo si la encuentra la retorna 
    * en caso contrario retorna null
    * @param nId != null && != ""
    * @return Dependencia 
    */
    public Dependencia buscarDependencia(String nId)
    {       
        for(Dependencia dependenciaBuscada: dependencias ){
            if(dependenciaBuscada.darId().equals(nId)){
                return dependenciaBuscada;
            }
        }     
        return null;
    }  

    public void modificarDependencia(String nId, String nNombre, String nDescripcion, String nHorario) throws ExcepcionNoExiste{
        Dependencia dependenciaModificar= buscarDependencia(nId);
        if(dependenciaModificar==null){
            //throw  new ExcepcionNoExiste("La dependencia que desea");
        }
        else
        {
            dependenciaModificar.cambiarNombre(nNombre);
            dependenciaModificar.cambiarDescripcion(nDescripcion);
            dependenciaModificar.cambiarHorario(nHorario);
        }
    }
    
    
    public void agregarResultado(String id, String idEstudiante, int resultadoPrueba, int resultadoEntrevista){
        Resultado resultadoBuscado = buscarResultado(id);
       // if(resultadoBuscado=)
    }
    public Resultado buscarResultado(String identificacion){
        for(Resultado resultadoBuscado: resultados){
            if(resultadoBuscado.darIdEstudiante().equals(identificacion))
                return resultadoBuscado;
        }
        return null;
    }
    
    
    
    public Estudiante buscarEstudianteUniversidad(String identificacion) throws ExcepcionNoExiste {
        
         Estudiante estudiante = null;
         
         estudiante= cmDAO.buscarEstudiante(identificacion);
         if(estudiante!=null)
             return estudiante;
         
         return null;
    }
    
    public void eliminarEstudianteSistema(String identificacion) throws Exception {
        Aspirante aspirante = buscarAspirante(identificacion);
        if(aspirante!=null)
            eliminarAspirante(identificacion);
        
        Monitor monitor = buscarMonitor(identificacion);
        if(monitor!=null)
            eliminarMonitor(identificacion);
    }
    /**
     * Metodo para hacer pruebas
     * @param args 
     */
    public static void main(String[] args) 
    {                
        try {
            ContratacionMonitores cm = new ContratacionMonitores();   
            
            
            //Pruebas para Aspirante
            Aspirante a1 = new Aspirante("primerNombre","segundoNombre", "primerApellido","segundoApellido", 1, "estadoMatricula", null,1.0,1,"101");
            
            System.out.println(a1.toString());
            
            Aspirante a2 = new Aspirante("primerNombre2","segundoNombre2", "primerApellido2","segundoApellido2", 2, "estadoMatricula2", null,2.0,2,"102");
            System.out.println(a2.toString());
            
            //Pruebas para Monitor
            Monitor m1 = new Monitor("primerNombreM1","segundoNombreM1", "primerApellidoM1","segundoApellidoM1", 1, "estadoMatriculaM1", null,1.0,1,"101", null);
            System.out.println(m1.toString());
            Monitor m2 = new Monitor("primerNombreM2","segundoNombreM2", "primerApellidoM2","segundoApellidoM2", 2, "estadoMatriculaM2", null,1.0,1,"102", null);
            System.out.println(m2.toString());

            cm.registrarAspirante("primerNombre","segundoNombre", "primerApellido","segundoApellido", 1, "estadoMatricula", null,1.0,1,"101");
            cm.registrarAspirante("primerNombre2","segundoNombre2", "primerApellido2","segundoApellido2", 2, "estadoMatricula2", null,2.0,2,"102");
            cm.registrarAspirante("primerNombre3","segundoNombre3", "primerApellido3","segundoApellido3", 3, "estadoMatricula3", null,3.0,3,"103");
            cm.registrarAspirante("primerNombre3","segundoNombre3", "primerApellido3","segundoApellido3", 3, "estadoMatricula3", null,3.0,3,"103");
            
            

            //Pruebas para Dependencia
            cm.agregarDependencia("Cod1 ", "Dependencia 1", "Esta es la Dependencia 1", "Tarde", 5);
            cm.agregarDependencia("Cod2 ", "Dependencia 2", "Esta es la Dependencia 2", "Mañana", 4);
            cm.agregarDependencia("Cod3 ", "Dependencia 3", "Esta es la Dependencia 3", "Tarde", 3);
            cm.agregarDependencia("Cod4 ", "Dependencia 4", "Esta es la Dependencia 4", "Mañana", 2);
            for (Dependencia d : cm.darDependencias()) {                
                System.out.println("Dependencia: "+d.toString());
            }
            System.out.println("Numero Dependencias: "+cm.darDependencias().size());
          
        } catch (Exception ex) {          
            System.out.println("Error: "+ex.getMessage());
        }
    }

    public Administrador ingresoAdmin(String usuario, String password) throws ExcepcionNoExiste {
        return cmDAO.buscarAdministrador(usuario,password);
    }

 

    
}
