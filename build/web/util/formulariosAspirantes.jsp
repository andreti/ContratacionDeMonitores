<%@page import="com.umariana.contratacionmonitores.logica.Postulacion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.umariana.contratacionmonitores.logica.Dependencia"%>
<%@page import="com.umariana.contratacionmonitores.controladores.GestionDependencias"%>
<%@page import="com.umariana.contratacionmonitores.controladores.GestionAspirantes"%>
<%@page import="com.umariana.contratacionmonitores.logica.Aspirante"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="../js/modales.js" type="text/javascript"></script>
<link rel="stylesheet" href="../css/estilos.css" type="text/css" media="all">

<%String var=request.getParameter("var");
String mensaje = (String)session.getAttribute("mensaje");
if(var!=null){
    if(var.equals("eliminarAspirante")){
        String identificacion=request.getParameter("identificacion");
        Aspirante eliminar=GestionAspirantes.buscarAspirante(identificacion);%>
        <fieldset id="fls_mostrar">
            <legend>Eliminar Aspirante</legend>  
            <div style="text-align: center;">¿Desea eliminar el aspirante?</div>
            <br>
            <table id="tbl_ven">
                <tr>
                    <td>Identificación:</td><td><%=eliminar.darIdentificacion() %></td>
                </tr>
                <tr>
                    <td>Nombres:</td><td><%=eliminar.darPrimerNombre()%><%if(eliminar.darSegundoNombre()!=null){%> <%=eliminar.darSegundoNombre() %><%}%></td>
                </tr>
                <tr>
                    <td></td><td><%=eliminar.darPrimerApellido() %><%if(eliminar.darSegundoApellido()!=null){%> <%=eliminar.darSegundoApellido() %><%}%></td>
                </tr>
            </table>
            <br>
            <div style="text-align: center;">
                <form action="GestionAspirantes" id="formRegDep" name="formRegDep" method="post" style="display: inline-block;">
                  <input style="width: 100px;" type="submit"  id="btnForm" name="btnregDep"  value="Eliminar">
                  <input  id="accion" name="accion" type="hidden" value="eliminarAspirante"/>
                  <input  id="accion" name="identificacion" type="hidden" value="<%=eliminar.darIdentificacion() %>"/>
                </form>
                 <form action="" id="formRegDep" name="formRegDep" method="post" style="display: inline-block; ">
                     <input style="width: 100px;" type="button" onclick="javascript:hiddenThis(divDelAsp);" id="btnForm" name="btnregDep"  value="Cancelar">
                </form>
            </div>
        </fieldset>
    <%}
    if(var.equals("eliminarPostulacion")){
        String idHorario=request.getParameter("idHorario");
        Dependencia dependencia = GestionDependencias.buscarDependenciaPorHorario(idHorario);%>
        <fieldset id="fls_mostrar">
            <legend>Eliminar Aspirante</legend>  
            <div style="text-align: center;">¿Desea eliminar la postulación?</div>
            <br>
            <table id="tbl_ven">
                <tr>
                    <td>Dependencia:</td><td><%=dependencia.darNombre() %></td>
                </tr>
                <tr>
                    <td></td><td><%=dependencia.darJornadas().get(0).getJornada()%><br><%=dependencia.darJornadas().get(0).getHorarios().get(0).toString() %></td>
                </tr>
            </table>
            <br>
            <div style="text-align: center;">
                <form action="GestionAspirantes" id="formRegDep" name="formRegDep" method="post" style="display: inline-block;">
                  <input style="width: 100px;" type="submit"  id="btnForm" name="btnregDep"  value="Eliminar">
                  <input  id="accion" name="accion" type="hidden" value="eliminarPostulacion"/>
                  <input  id="accion" name="idHorario" type="hidden" value="<%=idHorario %>"/>
                </form>
                 <form action="" id="formRegDep" name="formRegDep" method="post" style="display: inline-block; ">
                     <input style="width: 100px;" type="button" onclick="javascript:hiddenThis(divDelPostAsp);" id="btnForm" name="btnregDep"  value="Cancelar">
                </form>
            </div>
        </fieldset>
        <%}
    if(var.equals("seleccionarAspirante")){
        String identificacion=request.getParameter("identificacion");
        Aspirante seleccionar=GestionAspirantes.buscarAspirante(identificacion);%>
        <fieldset id="fls_mostrar">
            <legend>Seleccionar Aspirante</legend>  
            <form action="GestionAspirantes" id="formRegDep" name="formRegDep" method="post">
            <div style="text-align: center;">Seleccione una dependencia para quel el aspirante realize su monitoria.</div>
            <br>
            <%ArrayList<Postulacion> pst=GestionAspirantes.buscarPostulacionesAspirante(seleccionar.darIdentificacion()); 
            if(!pst.isEmpty()){%>
             <div style=" padding: 5px;">
                 <%
                if(pst!=null){
                    for(Postulacion p: pst){%> 
                    <div style="margin: 0px; margin-bottom: 3px; border-bottom: 1px solid gray;">
                        <div><input type="radio" id="cbx_postulacion" name="cbx_postulacion" value="<%=p.getIdHorario()%>" required> <%=p.getDependencia().darNombre() %></div>
                        <input  id="idHorario" name="idHorario" type="hidden" value="<%=p.getIdHorario() %>"/>
                        <div style="margin-bottom: 10px; "><label style="margin-left: 20px; color: black;">► <%=p.getDependencia().darJornadas().get(0).getJornada() %>, <%=p.getDependencia().darJornadas().get(0).getHorarios().get(0).toString() %></label></div>
                        </div>
                    <%}           
                }%>
             </div>
            <%}else{%>
             N/A
            <%}%>
            <div style="text-align: center;">
                <input style="width: 100px;" type="submit"  id="btnForm" name="btnregDep"  value="Seleccionar">
                <input  id="accion" name="accion" type="hidden" value="seleccionarAspirante"/>
                <input  id="identificacion" name="identificacion" type="hidden" value="<%=seleccionar.darIdentificacion() %>"/>
                <input style="width: 100px;" type="button" onclick="javascript:hiddenThis(divSelAsp);" id="btnForm" name="btnregDep"  value="Cancelar">
            </div>
            </form>
        </fieldset>
    <%}
}%>

