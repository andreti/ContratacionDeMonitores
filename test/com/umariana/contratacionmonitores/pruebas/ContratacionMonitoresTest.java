/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.umariana.contratacionmonitores.pruebas;

import com.umariana.contratacionmonitores.logica.Aspirante;
import com.umariana.contratacionmonitores.logica.ContratacionMonitores;
import com.umariana.contratacionmonitores.logica.Dependencia;
import com.umariana.contratacionmonitores.logica.Monitor;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andres
 */
public class ContratacionMonitoresTest {
    
    ContratacionMonitores cm;
    
    public ContratacionMonitoresTest() {
        cm = new ContratacionMonitores();
    }

     /**
     * Test of registrarAspirante method, of class ContratacionMonitores.
     */
    @Test
    public void testRegistrarAspirante() throws Exception  {
        System.out.println("registrarAspirante");     
        
        cm.registrarAspirante("primerNombre","segundoNombre", "primerApellido","segundoApellido", 1, "estadoMatricula", null,1.0,1,"101");
        assertTrue(cm.darAspirantes().size()==1);
        cm.registrarAspirante("primerNombre2","segundoNombre2", "primerApellido2","segundoApellido2", 2, "estadoMatricula2", null,2.0,2,"102");
        assertTrue(cm.darAspirantes().size()==2);
        cm.registrarAspirante("primerNombre3","segundoNombre3", "primerApellido3","segundoApellido3", 3, "estadoMatricula3", null,3.0,3,"103");
        assertTrue(cm.darAspirantes().size()==3);
        cm.registrarAspirante("primerNombre3","segundoNombre3", "primerApellido3","segundoApellido3", 3, "estadoMatricula3", null,3.0,3,"104");
        assertTrue(cm.darAspirantes().size()==4);
        cm.registrarAspirante("primerNombre3","segundoNombre3", "primerApellido3","segundoApellido3", 3, "estadoMatricula3", null,3.0,3,"104");     
        assertTrue(cm.darAspirantes().size()==4);
        
    }

    /**
     * Test of modificarAspirante method, of class ContratacionMonitores.
     */
    @Test
    public void testModificarAspirante() throws Exception {
        
        System.out.println("modificarAspirante");
        cm.registrarAspirante("primerNombre","segundoNombre", "primerApellido","segundoApellido", 1, "estadoMatricula", null,1.0,1,"101");
        System.out.println(cm.buscarAspirante("101").toString());
        
        cm.modificarAspirante("primerNombreModificado","segundoNombreModificado", "primerApellidoModificado","segundoApellidoModificado", "101", null,2);
        System.out.println(cm.buscarAspirante("101").toString());
        Aspirante aspiranteModificado=cm.buscarAspirante("101");
        assertTrue(aspiranteModificado.darPrimerNombre().equals("primerNombreModificado"));
        assertTrue(aspiranteModificado.darSegundoNombre().equals("segundoNombreModificado"));
        assertTrue(aspiranteModificado.darPrimerApellido().equals("primerApellidoModificado"));
        assertTrue(aspiranteModificado.darSegundoApellido().equals("segundoApellidoModificado"));
        assertTrue(aspiranteModificado.darEstadoMatricula().equals("estadoMatricula"));
        assertTrue(aspiranteModificado.darPromedioAcumulado() == 2.0);
        assertTrue(aspiranteModificado.darSemestreActual()== 2);  
    }

    /**
     * Test of eliminarAspirante method, of class ContratacionMonitores.
     */
    @Test
    public void testEliminarAspirante() throws Exception {
        System.out.println("eliminarAspirante");
        cm.registrarAspirante("primerNombre","segundoNombre", "primerApellido","segundoApellido", 1, "estadoMatricula", null,1.0,1,"101");
        cm.registrarAspirante("primerNombre2","segundoNombre2", "primerApellido2","segundoApellido2", 2, "estadoMatricula2", null,2.0,2,"102");
        cm.registrarAspirante("primerNombre3","segundoNombre3", "primerApellido3","segundoApellido3", 3, "estadoMatricula3", null,3.0,3,"103");
        cm.registrarAspirante("primerNombre3","segundoNombre3", "primerApellido3","segundoApellido3", 4, "estadoMatricula3", null,3.0,3,"104");    
        
        cm.eliminarAspirante("101");
        assertTrue(cm.darAspirantes().size()==3);
        
        Aspirante aspiranteEliminado= cm.buscarAspirante("101");
        assertTrue(aspiranteEliminado == null);
        
        aspiranteEliminado= cm.buscarAspirante("102");
        assertTrue(aspiranteEliminado != null);
        
        cm.eliminarAspirante("103");
        aspiranteEliminado= cm.buscarAspirante("103");
        assertTrue(aspiranteEliminado == null);
        
        aspiranteEliminado= cm.buscarAspirante("104");
        assertTrue(aspiranteEliminado != null);
    }

    /**
     * Test of eliminarMonitor method, of class ContratacionMonitores.
     */
    @Test
    public void testEliminarMonitor() throws Exception {
        System.out.println("eliminarMonitor");
        cm.registrarMonitor("primerNombre","segundoNombre", "primerApellido","segundoApellido", 1, "estadoMatricula", null,1.0,1,"101");
        cm.registrarMonitor("primerNombre2","segundoNombre2", "primerApellido2","segundoApellido2", 2, "estadoMatricula2", null,2.0,2,"102");
        cm.registrarMonitor("primerNombre3","segundoNombre3", "primerApellido3","segundoApellido3", 3, "estadoMatricula3", null,3.0,3,"103");
        cm.registrarMonitor("primerNombre3","segundoNombre3", "primerApellido3","segundoApellido3", 4, "estadoMatricula3", null,3.0,3,"104");    
        
        cm.eliminarMonitor("101");
        assertTrue(cm.darMonitores().size()==3);
        
        Monitor monitorEliminado= cm.buscarMonitor("101");
        assertTrue(monitorEliminado == null);
        
        monitorEliminado= cm.buscarMonitor("102");
        assertTrue(monitorEliminado != null);
        
        cm.eliminarMonitor("103");
        monitorEliminado= cm.buscarMonitor("103");
        assertTrue(monitorEliminado == null);
        
        monitorEliminado= cm.buscarMonitor("104");
        assertTrue(monitorEliminado != null);              
    }
    
     /**
     * Test of registrarMonitor method, of class ContratacionMonitores.
     */
    @Test
    public void testRegistrarMonitor() throws Exception  {
        System.out.println("registrarMonitor");     
        
        cm.registrarMonitor("primerNombre","segundoNombre", "primerApellido","segundoApellido", 1, "estadoMatricula", null,1.0,1,"101");
        assertTrue(cm.darMonitores().size()==1);
        cm.registrarMonitor("primerNombre2","segundoNombre2", "primerApellido2","segundoApellido2", 2, "estadoMatricula2", null,2.0,2,"102");
        assertTrue(cm.darMonitores().size()==2);
        cm.registrarMonitor("primerNombre3","segundoNombre3", "primerApellido3","segundoApellido3", 3, "estadoMatricula3", null,3.0,3,"103");
        assertTrue(cm.darMonitores().size()==3);
        cm.registrarMonitor("primerNombre3","segundoNombre3", "primerApellido3","segundoApellido3", 3, "estadoMatricula3", null,3.0,3,"104");
        assertTrue(cm.darMonitores().size()==4);
        cm.registrarMonitor("primerNombre3","segundoNombre3", "primerApellido3","segundoApellido3", 3, "estadoMatricula3", null,3.0,3,"104");     
        assertTrue(cm.darMonitores().size()==4);
        
    }

    /**
     * Test of modificarAspirante method, of class ContratacionMonitores.
     */
    @Test
    public void testModificarMonitor() throws Exception {
        
        System.out.println("modificarMonitor");
        cm.registrarMonitor("primerNombre","segundoNombre", "primerApellido","segundoApellido", 1, "estadoMatricula", null,1.0,1,"101");
        System.out.println(cm.buscarMonitor("101").toString());
        
        cm.modificarMonitor("primerNombreModificado","segundoNombreModificado", "primerApellidoModificado","segundoApellidoModificado", "101", null,2,2.0);
        System.out.println(cm.buscarMonitor("101").toString());
        Monitor monitorModificado=cm.buscarMonitor("101");
        assertTrue(monitorModificado.darPrimerNombre().equals("primerNombreModificado"));
        assertTrue(monitorModificado.darSegundoNombre().equals("segundoNombreModificado"));
        assertTrue(monitorModificado.darPrimerApellido().equals("primerApellidoModificado"));
        assertTrue(monitorModificado.darSegundoApellido().equals("segundoApellidoModificado"));
        assertTrue(monitorModificado.darEstadoMatricula().equals("estadoMatricula"));
        assertTrue(monitorModificado.darPromedioAcumulado() == 2.0);
        assertTrue(monitorModificado.darSemestreActual()== 2);

        
    }

    /**
     * Test of buscarAspirante method, of class ContratacionMonitores.
     */
    @Test
    public void testBuscarAspirante() throws Exception {
        System.out.println("buscarAspirante");

        cm.registrarAspirante("primerNombre","segundoNombre", "primerApellido","segundoApellido", 1, "estadoMatricula", null,1.0,1,"101");
        cm.registrarAspirante("primerNombre2","segundoNombre2", "primerApellido2","segundoApellido2", 2, "estadoMatricula2", null,2.0,2,"102");
        cm.registrarAspirante("primerNombre3","segundoNombre3", "primerApellido3","segundoApellido3", 3, "estadoMatricula3", null,3.0,3,"103");
        cm.registrarAspirante("primerNombre3","segundoNombre3", "primerApellido3","segundoApellido3", 3, "estadoMatricula3", null,3.0,3,"104");        
            
        Aspirante result = cm.buscarAspirante("103");
        Aspirante expResult = null;
        
        assertTrue(expResult != result);
        result = cm.buscarAspirante("105");
        assertTrue(expResult == result);
    }

    /**
     * Test of buscarMonitor method, of class ContratacionMonitores.
     */
    @Test
    public void testBuscarMonitor() throws Exception {
        System.out.println("buscarAspirante");

        cm.registrarMonitor("primerNombre","segundoNombre", "primerApellido","segundoApellido", 1, "estadoMatricula", null,1.0,1,"101");
        cm.registrarMonitor("primerNombre2","segundoNombre2", "primerApellido2","segundoApellido2", 2, "estadoMatricula2", null,2.0,2,"102");
        cm.registrarMonitor("primerNombre3","segundoNombre3", "primerApellido3","segundoApellido3", 3, "estadoMatricula3", null,3.0,3,"103");
        cm.registrarMonitor("primerNombre3","segundoNombre3", "primerApellido3","segundoApellido3", 3, "estadoMatricula3", null,3.0,3,"104");        
            
        Monitor result = cm.buscarMonitor("103");
        Monitor expResult = null;
        
        assertTrue(expResult != result);
        result = cm.buscarMonitor("105");
        assertTrue(expResult == result);
    }

    /**
     * Test of agregarDependencia method, of class ContratacionMonitores.
     */
    @Test
    public void testAgregarDependencia() throws Exception {
        System.out.println("agregarDependencia");
        
        cm.agregarDependencia("Cod1 ", "Dependencia 1", "Esta es la Dependencia 1", "Tarde");
        assertTrue(cm.darDependencias().size()== 1);
        cm.agregarDependencia("Cod2 ", "Dependencia 2", "Esta es la Dependencia 2", "Mañana");
        assertTrue(cm.darDependencias().size()== 2);
        cm.agregarDependencia("Cod3 ", "Dependencia 3", "Esta es la Dependencia 3", "Tarde");
        assertTrue(cm.darDependencias().size()== 3);
        cm.agregarDependencia("Cod4 ", "Dependencia 4", "Esta es la Dependencia 4", "Mañana");
        assertTrue(cm.darDependencias().size()== 4);
        
        cm.agregarDependencia("Cod4 ", "Dependencia 4", "Esta es la Dependencia 4", "Mañana");
        assertTrue(cm.darDependencias().size()== 4);

    }

    /**
     * Test of buscarDependencia method, of class ContratacionMonitores.
     */
    @Test
    public void testBuscarDependencia() throws Exception {
        System.out.println("buscarDependencia");
        
        cm.agregarDependencia("Cod1", "Dependencia 1", "Esta es la Dependencia 1", "Tarde");
        assertTrue(cm.darDependencias().size()== 1);
        cm.agregarDependencia("Cod2", "Dependencia 2", "Esta es la Dependencia 2", "Mañana");
        assertTrue(cm.darDependencias().size()== 2);
        cm.agregarDependencia("Cod3", "Dependencia 3", "Esta es la Dependencia 3", "Tarde");
        assertTrue(cm.darDependencias().size()== 3);
        cm.agregarDependencia("Cod4", "Dependencia 4", "Esta es la Dependencia 4", "Mañana");
        assertTrue(cm.darDependencias().size()== 4);
        
        Dependencia dependenciaBuscada = cm.buscarDependencia("Cod1");
        assertTrue(dependenciaBuscada!=null && dependenciaBuscada.darId().equals("Cod1"));
        
        dependenciaBuscada = cm.buscarDependencia("Cod2");
        assertTrue(dependenciaBuscada!=null && dependenciaBuscada.darId().equals("Cod2"));
        
        dependenciaBuscada = cm.buscarDependencia("Cod100");
        assertTrue(dependenciaBuscada==null );   
    }
    
     /**
     * Test of EliminarDependencia method, of class ContratacionMonitores.
     */
    @Test
    public void testEliminarDependencia() throws Exception {
        System.out.println("EliminarDependencia");
        
        cm.agregarDependencia("Cod1", "Dependencia 1", "Esta es la Dependencia 1", "Tarde");        
        cm.agregarDependencia("Cod2", "Dependencia 2", "Esta es la Dependencia 2", "Mañana");      
        cm.agregarDependencia("Cod3", "Dependencia 3", "Esta es la Dependencia 3", "Tarde");       
        cm.agregarDependencia("Cod4", "Dependencia 4", "Esta es la Dependencia 4", "Mañana");
        
        cm.eliminarDependencia("Cod1");
        Dependencia dependenciaEliminada= cm.buscarDependencia("Cod1");
        assertTrue(dependenciaEliminada == null);
        
        cm.eliminarDependencia("Cod2");
        dependenciaEliminada= cm.buscarDependencia("Cod2");
        assertTrue(dependenciaEliminada == null);
        
        cm.eliminarDependencia("Cod100");
        dependenciaEliminada= cm.buscarDependencia("Cod100");
        assertTrue(dependenciaEliminada == null);
        
        assertTrue(cm.darDependencias().size()==2);
    }
    
     /**
     * Test of ModificarDependencia method, of class ContratacionMonitores.
     */
    @Test
    public void testModificarDependencia() throws Exception {
        System.out.println("ModificarDependencia");
        
        cm.agregarDependencia("Cod1", "Dependencia 1", "Esta es la Dependencia 1", "Tarde");        
        cm.agregarDependencia("Cod2", "Dependencia 2", "Esta es la Dependencia 2", "Mañana"); 
        System.out.println(cm.buscarDependencia("Cod1").toString());
        
        cm.modificarDependencia("Cod1", "Dependencia 1 Modificado", "Esta es la Dependencia 1 Modificado", "TardeModificado");   
        assertTrue(cm.buscarDependencia("Cod1").darNombre().equals("Dependencia 1 Modificado"));
        assertTrue(cm.buscarDependencia("Cod1").darDescripcion().equals("Esta es la Dependencia 1 Modificado"));
        assertTrue(cm.buscarDependencia("Cod1").darHorario().equals("TardeModificado"));
        System.out.println(cm.buscarDependencia("Cod1").toString());
    
    }
}
