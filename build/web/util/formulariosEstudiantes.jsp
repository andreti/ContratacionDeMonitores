<%@page import="com.umariana.contratacionmonitores.logica.dependencia.Horario"%>
<%@page import="com.umariana.contratacionmonitores.logica.dependencia.Jornada"%>
<%@page import="com.umariana.contratacionmonitores.logica.Dependencia"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.umariana.contratacionmonitores.logica.Postulacion"%>
<%@page import="com.umariana.contratacionmonitores.controladores.GestionEstudiantes"%>
<%@page import="com.umariana.contratacionmonitores.controladores.GestionDependencias"%>
<%@page import="com.umariana.contratacionmonitores.logica.Estudiante"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="../js/modales.js" type="text/javascript"></script>
<link rel="stylesheet" href="../css/estilos.css" type="text/css" media="all">

<%String var=request.getParameter("var");
String mensaje = (String)session.getAttribute("mensaje");
if(var!=null){
    if(var.equals("registrarEstudiante")){
        Estudiante estudiante = (Estudiante)session.getAttribute("estudiante");
        %>
        <fieldset id="fls_mostrar">
            <legend>Registrar Estudiante</legend>  
            <table id="tbl_reg_est">
                <th colspan="4">¿Se desea registrar para aplicar como aspirante a una monitoria?</th>
                <tr>
                    <td>Identificacion:</td>
                    <td><%=estudiante.darIdentificacion()%></td>
                    <td rowspan="4" colspan="2" style="background: green; width: 70px; height: 50px;">Foto</td>
                </tr>
                <tr>
                    <td>Nombres:</td>
                    <td ><%=estudiante.darPrimerNombre() %><%if(estudiante.darSegundoNombre()!=null){%> <%=estudiante.darSegundoNombre() %><%}%> </td>
                    <td></td><td></td>
                </tr>
                <tr>
                    <td>Apellidos:</td>
                    <td ><%=estudiante.darPrimerApellido() %><%if(estudiante.darSegundoApellido()!=null){%> <%=estudiante.darSegundoApellido() %><%}%> </td>
                    <td></td><td></td>
                </tr>
                <tr>
                    <td>Semestre:</td>
                    <td><%=estudiante.darSemestreActual() %> </td>
                    <td></td>
                </tr>              
            </table>
            <form action="GestionEstudiantes" id="formRegDep" name="formRegDep" method="post" style="display: inline-block;">
              <input type="submit"  id="btnForm" name="btnregDep"  value="Postularme">
              <input id="accion" name="accion" type="hidden" value="regEst"/>
          </form>
           <form action="" id="formRegDep" name="formRegDep" method="post" style="display: inline-block;">
              <input type="button" onclick="javascript:hiddenThis(divRegEst)" id="btnForm" name="btnregDep"  value="Cancelar">
          </form>
        </fieldset>
        <%
    }
    if(var.equals("ingresar")){%>
        <fieldset id="fls_mostrar">
         <legend>Ingreso para Estudiantes</legend> 
         <form action="GestionSesionDeEstudiantes" id="formRegDep" name="formRegDep" method="post" style="display: inline-block; width: 250px; height: 80px;">
              <input type="hidden" id="accion" name="accion" value="ingreso">   
              <table id="tbl_ingreso">   
                  <tr style="height: auto;">
                   <td>  <label>Identificacion</label></td>
                   <td><input style="width: 120px;" type="text" id="txt_identificacion" name="txt_identificacion" required></td>
               </tr>
               <tr>
                   <td>
                       <input style="width: 120px;" type="submit" id="btnForm" name="btnregDep" value="Entrar">
                   </td>
                   <td>
                     <input style="width: 120px; margin-left: 5px;" type="button" onclick="javascript:hiddenThis(divIngreso)" id="btnForm" name="btnregDep"  value="Cancelar">
                   </td>
               </tr>
            </table>
        </form>
        </fieldset>
   <%}
    if(var.equals("agregarPostulacion")){
        //ArrayList<Postulacion> postulaciones=(ArrayList<Postulacion>)session.getAttribute("listaPostulacionesTemp");
        //String identificacion = request.getParameter("identificacion");
        String idDependencia = request.getParameter("idDependencia");
        Dependencia buscada=GestionDependencias.buscarDependencia(idDependencia);
        %>
        <fieldset id="fls_mostrar">
            <legend>Registrar postulación</legend>
            <form action="GestionEstudiantes" id="formRegDep" name="formRegDep" method="post" style="display: inline-block;">
                <table style="text-align: left;">
                    <tr>
                        <td colspan="3"><%=buscada.darNombre() %>, <%=buscada.darDescripcion()%></td>
                    </tr>
                    <tr>
                        <td>Cupos Totales: <%=buscada.getTotalCupos() %></td><td>Cupos Disponibles: <%=buscada.getTotalCuposDisponibles() %></td>
                    </tr>
                    <tr>
                        <td>
                            Jornada<br>
                            <select style="width: 120px;"  id="slc_jornada" name="slc_jornada" >
                                <option onclick="javascript:cambiarHorarioCbx('0')" selected="selected" value="0">Seleccione...</option>       
                                <%for(Jornada j: buscada.darJornadas() ) {
                                    if(j.getTotalCuposDisponibles()>0){%>
                                        <option onclick="javascript:cambiarHorarioCbx('<%=j.getId() %>')" value="<%=j.getId()%>"><%=j.getJornada() %></option>
                                     <%}
                                }%>
                            </select>
                        </td>
                        <td>
                            Horario<br>
                            <select style="width: 120px;" id="slc_horario" name="slc_horario">
                               <option onclick="javascript:cambiarCuposCbx('0')" selected="selected" value="0">Seleccione...</option>       
                            </select>
                        </td>    
                        <td id="tdCupos">Cupos:<br>T: 0  |  D: 0</td>
                    </tr>
                        <tr style="text-align: left; font-size: 10px; font-family: cursive;">
                        <td></td><td></td><td colspan="3">T : Totales<br>D : Disponibles</td>
                    </tr>
                </table>     
                <input type="submit"  id="btnForm" name="btnregDep"  value="Agregar">
                <input id="accion" name="accion" type="hidden" value="agregarPostulacion"/>
                <input id="idDependencia" name="idDependencia" type="hidden" value="<%=idDependencia%>"/>
                <input style="width: 150px;" type="button" onclick="javascript:hiddenThis(divRegPostEst)" id="btnForm" name="btnregDep"  value="Cancelar">
            </form>
        </fieldset>
    <%} 
    if(var.equals("eliminarPostulacion")){
        String idDependencia = request.getParameter("idDependencia");
        %>
        <fieldset id="fls_mostrar">
            <legend>Quitar postulación</legend>
            <label style="text-align: center;">¿Desea quitar la postulacion seleccionada?</label><br>
            <br>
            <form action="GestionEstudiantes" id="formRegDep" name="formRegDep" method="post" style="display: inline-block;">
                <input style="width: 150px;" type="submit"  id="btnForm" name="btnregDep"  value="Quitar">
                <input id="accion" name="accion" type="hidden" value="eliminarPostulacion"/>
                <input id="idDependencia" name="idDependencia" type="hidden" value="<%=idDependencia%>"/>
            </form>
            <form action="" id="formRegDep" name="formRegDep" method="post" style="display: inline-block;">
                <input style="width: 150px;" type="button" onclick="javascript:hiddenThis(divDelPostEst)" id="btnForm" name="btnregDep"  value="Cancelar">
            </form>
        </fieldset>
    <%
    }
       
}%>
