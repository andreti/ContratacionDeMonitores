<%@page import="com.umariana.contratacionmonitores.controladores.GestionDependencias"%>
<%@page import="com.umariana.contratacionmonitores.logica.dependencia.Horario"%>
<%@page import="java.util.List"%>
<%@page import="com.umariana.contratacionmonitores.logica.dependencia.Jornada"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.umariana.contratacionmonitores.logica.Dependencia"%>
<script src="../js/modales.js" type="text/javascript"></script>
<link rel="stylesheet" href="../css/estilos.css" type="text/css" media="all">


<%String var=request.getParameter("var");
String mensaje = (String)session.getAttribute("mensaje");
if(var!=null){
    if(var.equals("regDep")){%>
    <form action="GestionDependencias" id="formRegDep" name="formRegDep" method="post"> 
            <%
             Dependencia regDep1=(Dependencia)session.getAttribute("regDep1");
             Dependencia existeD=(Dependencia)session.getAttribute("depExiste");

            if(mensaje!=null){%>
            <h4 style="color: red; font-size: 13px;"><%=mensaje%></h4>
                    <%session.removeAttribute("mensaje");
            }%>
            <input id="accion" name="accion" type="hidden" value="regDep"/>
            <label>Nombre</label>
            <input type="text" name="txt_nombre" id="txt_nombre"  maxlength="25" value="<%if(regDep1!=null){ %><%=regDep1.darNombre() %><%}%>" required>
            <br>
            <br>
            <label>Descripción</label>
            <textarea type="text" name="txt_descripcion" id="txt_descripcion"  maxlength="100" required><%if(regDep1!=null){ %><%=regDep1.darDescripcion()%><%}%><%if(existeD!=null){ %><%=existeD.darDescripcion()%><%}%></textarea> 
            <br>
            <br>
            <input  type="submit"  id="btnForm" name="btnregDep"  value="Enviar" style="display: inline-block;width: 200px;"> 
    </form>
    <form action="GestionDependencias" id="formRegDep" name="formRegDep" method="post" style=" margin-top: 10px;display: inline-block;">
        <input type="submit"  id="btnForm" name="btnregDep"  value="Cancelar" style="color:  red;">
        <input id="accion" name="accion" type="hidden" value="cancelar"/>
    </form>

   <%     
    } 
    if(var.equals("contRegDep")){
       Dependencia regDep2=(Dependencia)session.getAttribute("regDep2");
   %>
           <%if(mensaje!=null){%>
                     <h4><%=mensaje%></h4>
                    <%session.removeAttribute("mensaje");
             }%>      
            <fieldset id="fls_mostrar">
                <legend>Datos de la Nueva Dependencia</legend>
                <table>
                    <tr>
                        <td><p style="color: black;">Dependencia:</p></td>
                        <td style="height: auto; text-align: left; padding-left: 5px;"><p><%if(regDep2!=null){ %><%=regDep2.darNombre() %><%}%></p></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td style="height: auto; text-align: left; padding-left: 5px;"><p><%if(regDep2!=null){ %><%=regDep2.darDescripcion()%><%}%></p></td>
                    </tr>
                </table>      
                <form action="GestionDependencias" id="formRegDep" name="formRegDep" method="post">
                    <input type="submit"  id="btnForm" name="btnregDep"  value="Editar">
                    <input id="accion" name="accion" type="hidden" value="volverRegDep"/>                 
                </form>
            </fieldset>
            <fieldset id="fls_mostrar">
                <legend>Registro de Horarios</legend>
                <%if(mensaje!=null){%>
                <h4 style="color: red; font-size: 13px;"><%=mensaje%></h4>
                        <%session.removeAttribute("mensaje");
                }%>
                <form action="GestionDependencias" id="formRegDep" name="formRegDep" method="post">
                    <table style="text-align: left;">
                        <tr>
                            <td><label>Jornada</label>
                                <select id="slc_jornada" name="slc_jornada" required>
                                    <option value="manana">Mañana</option>
                                    <option value="Tarde">Tarde</option>
                                    <option value="Noche">Noche</option>
                                </select>
                            </td>
                            <td>
                                <label>Número de Cupos</label>
                                <input type="number" id="txtCupos" name="txtCupos" min="1" max="10" required>
                            </td>
                        </tr>
                        <tr>
                            <td><label>Hora de Inicio</label>
                                <input type="number" id="txtHoraInicio" name="txtHoraInicio" min="0" max="23" required>
                            </td>
                            <td>
                                <label>Finalización</label>
                                <input type="number" id="txtHoraFin" name="txtHoraFin" min="0" max="23" required>
                            </td>
                        </tr>
                    </table>
                    <input type="submit"  id="btnForm" name="btnregDep"  value="Agregar">
                    <input id="accion" name="accion" type="hidden" value="agregarHorario"/>
                    <input id="atras" name="atras" type="hidden" value="si"/>
                </form>
            </fieldset>
            <fieldset id="fls_mostrar">
                <legend>Horarios</legend>
                <table>
                    <tr style="height: 30px;" id="tr_rows">
                        <td>Jornada</td><td>Inicio</td><td>Fin</td><td>Cupos</td>
                    </tr>
                <%List<Jornada> jornadas =regDep2.darJornadas(); 
                if(jornadas.isEmpty()){
                %>
                    <tr>
                        <td colspan="4">No hay datos</td>
                    </tr>
                <%
                }
                else{
                    int color=0;
                    for(Jornada j :regDep2.darJornadas()){
                        
                        for(Horario h:j.getHorarios()){
                        
                        %>
                            <tr class="tr_list"  <%if(color==0){%> style="background: white;" <%}else{%>style="background: #aad4ff;"<%}%>>
                                <td ><%=j.getJornada() %></td><td><%=h.getDesde() %> </td><td><%=h.getHasta() %></td><td><%=h.getTotalCupos() %></td>
                           </tr>
                        <%
                        }
                       if(color==0){color=1;}else{color=0;}
                    }
                } 
                
                %>
                </table>
                <form action="GestionDependencias" id="formRegDep" name="formRegDep" method="post" style="display: inline-block;">
                    <input type="submit"  id="btnForm" name="btnregDep"  value="Terminar">
                    <input id="accion" name="accion" type="hidden" value="finalizarRegDep"/>
                </form>
                <form action="GestionDependencias" id="formRegDep" name="formRegDep" method="post" style="display: inline-block;">
                    <input type="submit"  id="btnForm" name="btnregDep"  value="Cancelar" style="color: red;">
                    <input id="accion" name="accion" type="hidden" value="cancelar"/>
                </form>
            </fieldset>
                
            <!--<a id="regDep" href="javascript:hiddenThis(divRegDep);venRegDep();"><input type="button" id="btnregDep" name="btnregDep"  value="Atras"></a>-->
   <%         
   }
    if(var.equals("eliminarDep")){
        String idDependencia=request.getParameter("idDependencia");
        Dependencia eliminarD= GestionDependencias.buscarDependencia(idDependencia);
   %>
         <fieldset id="fls_mostrar">
            <legend>Eliminar Dependencia</legend>  
            <table>
                <th style="font-size: 15px;" colspan="2">¿ Esta seguro que desea eliminar la dependencia ?</th>
                <tr>
                    <td><p style="color: black;">Dependencia:</p></td>
                    <td style="height: auto; text-align: left; padding-left: 5px;"><p><%if(eliminarD!=null){ %><%=eliminarD.darNombre() %><%}%></p></td>
                </tr>
                <tr>
                    <td></td>
                    <td style="height: auto; text-align: left; padding-left: 5px;"><p><%if(eliminarD!=null){ %><%=eliminarD.darDescripcion()%><%}%></p></td>
                </tr>
            </table>
            <form action="GestionDependencias" id="formRegDep" name="formRegDep" method="post" style="display: inline-block;">
              <input type="submit"  id="btnForm" name="btnregDep"  value="Eliminar">
              <input id="idDependencia" name="idDependencia" type="hidden" value="<%=eliminarD.darId() %>"/>
              <input id="accion" name="accion" type="hidden" value="aceptarDelDep"/>
              <input style="width: 200px;" type="button" onclick="javascript:hiddenThis(divDelDep);" id="btnForm" name="btnregDep"  value="Cancelar">
          </form>
        </fieldset>
   <%}
    if(var.equals("actualizarDep")){
        Dependencia actualizarDep=(Dependencia)session.getAttribute("actualizarDep"); 
        String paso=(String)session.getAttribute("actualizarDepPaso");%>
        
         <fieldset id="fls_mostrar">
            <%boolean disabled = paso.equals("paso1")?false:true;%>
                <legend>Datos de la Dependencia</legend>
                <form action="GestionDependencias" id="formRegDep" name="formRegDep" method="post"> 
                    <%if(mensaje!=null && !disabled){%>
                    <h4 style="color: red; font-size: 13px;"><%=mensaje%></h4>
                            <%session.removeAttribute("mensaje");
                    }%>
                    <input id="accion" name="accion" type="hidden" value="actualizarPaso1"/>
                    <table>
                        <tr>
                            <td><p style="color: black;">Nombre:</p></td>
                            <td style="height: auto; text-align: left; padding-left: 5px;"><input type="text" name="txt_nombre" id="txt_nombre"  maxlength="25" value="<%if(actualizarDep!=null){ %><%=actualizarDep.darNombre() %><%}%>" <%if(disabled){%>disabled<%}%>  required></td>
                        </tr>
                        <tr>
                            <td>Descripción:</td>
                            <td style="height: auto; text-align: left; padding-left: 5px;"><textarea style="width: 200px; height: 100px; " type="text" name="txt_descripcion" id="txt_descripcion"  maxlength="100" <%if(disabled){%>disabled<%}%> required><%if(actualizarDep!=null){ %><%=actualizarDep.darDescripcion()%><%}%></textarea> </td>
                        </tr>
                    </table>
                    <%if(!disabled){ %>
                        <input  type="submit"  id="btnForm" name="btnregDep"  value="Siguiente">
                    <%}%>      
                </form>    
            <%if(disabled){ %>
                <form action="GestionDependencias" id="formRegDep" name="formRegDep" method="post" style="display: inline-block;">
                    <input type="submit"  id="btnForm" name="btnregDep"  value="Editar">
                    <input id="accion" name="accion" type="hidden" value="volverPaso1"/>
                </form>
                <%}%>     
        </fieldset>
       <%disabled=paso.equals("paso2")?false:true;%>             
       <fieldset id="fls_mostrar">
                
             
            <form action="" method="post" id="formSend">
                <input id="accion" name="accion" type="hidden" value="1111"/>
                <input id="idHorario" name="idHorario" type="hidden" value=""/>
            </form>
            <legend>Horarios</legend>
            <table>
                <tr style="height: 30px;" id="tr_rows">
                    <td>Jornada</td><td>Inicio</td><td>Fin</td><td>Cupos</td><td colspan="2"></td>
                </tr>
            <%List<Jornada> jornadas =actualizarDep.darJornadas(); 
            if(jornadas.isEmpty()){ %>
                <tr>
                    <td colspan="4">No hay datos</td>
                </tr>
            <%}
            else{
                int color=0;
                for(Jornada j :actualizarDep.darJornadas()){
                    for(Horario h:j.getHorarios()){%>
                        <tr class="tr_list"  <%if(color==0){%> style="background: white;" <%}else{%>style="background: #aad4ff;"<%}%> >
                            <td><%=j.getJornada() %></td>
                            <td><%=h.getDesde() %> </td>
                            <td><%=h.getHasta() %></td>
                            <td><%=h.getTotalCupos() %></td>
                            <td><a class="icono_small"  href="#" onclick="javascript:sendForm('eliminarHorarioDep','<%=h.getId()%>');" <%if(disabled){%> style="visibility: hidden;" <%}%>><img src="img/icon_delete.png" title="Eliminar"/></a></td>
                            <td><a class="icono_small"  href="#" onclick="javascript:sendForm('editarHorario','<%=h.getId() %>');" <%if(disabled){%> style="visibility: hidden;" <%}%>><img src="img/icon_update.png" title="Editar"/></a></td> 
                       </tr>
                    <%}
                   if(color==0){color=1;}else{color=0;}
                }        
            } 
            %>

            </table>          
        </fieldset>
        <fieldset id="fls_mostrar">
                <legend>Registro de Horarios</legend>
                <%if(mensaje!=null && !disabled){%>
                <h4 style="color: red; font-size: 13px;"><%=mensaje%></h4>
                        <%session.removeAttribute("mensaje");
                }%>
                <form action="GestionDependencias" id="formRegDep" name="formRegDep" method="post">
                    <table style="text-align: left;">
                        <tr>
                            <td><label>Jornada</label>
                                <select id="slc_jornada" name="slc_jornada" <%if(disabled){%> disabled="disabled" <%}%> required>
                                    <option value="manana">Mañana</option>
                                    <option value="Tarde">Tarde</option>
                                    <option value="Noche">Noche</option>
                                </select>
                            </td>
                            <td>
                                <label>Número de Cupos</label>
                                <input <%if(disabled){%> disabled="disabled" <%}%> type="number" id="txtCupos" name="txtCupos" min="1" max="10" required>
                            </td>
                        </tr>
                        <tr>
                            <td><label>Hora de Inicio</label>
                                <input <%if(disabled){%> disabled="disabled" <%}%> type="number" id="txtHoraInicio" name="txtHoraInicio" min="0" max="23" required>
                            </td>
                            <td>
                                <label>Finalización</label>
                                <input <%if(disabled){%> disabled="disabled" <%}%> type="number" id="txtHoraFin" name="txtHoraFin" min="0" max="23" required>
                            </td>
                        </tr>
                    </table>
                    <input <%if(disabled){%> style="visibility: hidden;" <%}%> type="submit"  id="btnForm" name="btnregDep"  value="Agregar">
                    <input id="accion" name="accion" type="hidden" value="agregarHorario"/>
                    <input id="atras" name="atras" type="hidden" value="si"/>
                </form>
            </fieldset>    
                <form action="GestionDependencias" id="formRegDep" name="formRegDep" method="post" style="display: inline-block;">
                    <input  type="submit"  id="btnForm" name="btnregDep"  value="Guardar Cambios">
                    <input id="accion" name="accion" type="hidden" value="finalizarActDep"/>
<!--                    <input style="width: 100px;" type="button" onclick="javascript:hiddenThis(divUpdDep);" id="btnForm" name="btnregDep"  value="Aceptar">            -->
                </form>
                <form action="GestionDependencias" id="formRegDep" name="formRegDep" method="post" style="display: inline-block;">
                    <input type="submit"  id="btnForm" name="btnregDep"  value="Cancelar">
                    <input id="accion" name="accion" type="hidden" value="cancelar"/>
                </form>
   <%}
    if(var.equals("verDependencia")){
        String idDependencia=request.getParameter("idDependencia");
        Dependencia buscada= GestionDependencias.buscarDependencia(idDependencia);
        if(buscada!=null){
            %>
            <fieldset id="fls_mostrar">
                <legend>Información de la Dependencia</legend>
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
                            <select style="width: 120px;"  id="slc_jornada" name="slc_jornada">
                                <option onclick="javascript:cambiarHorarioCbx('0')" selected="selected" value="0">Seleccione...</option>       
                                <%for(Jornada j: buscada.darJornadas() ) {%>
                                <option onclick="javascript:cambiarHorarioCbx('<%=j.getId() %>')" value="<%=j.getId()%>"><%=j.getJornada() %></option>
                                <%}%>
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
                
                <form action="" id="formRegDep" name="formRegDep" method="post" style="display: inline-block;">
                    <input style="width: 200px;" type="button" onclick="javascript:hiddenThis(divVerDep);" id="btnForm" name="btnregDep"  value="Aceptar">            
                </form>
            </fieldset>    
            <%
        }
    }
    if(var.equals("cambiarHorario")){
        String idJornada=request.getParameter("idJornada");
        String idHorario=request.getParameter("idHorario");
        if(idHorario!=null)
        {
            if(idHorario.equals("0")){
                %>
                    Cupos:<br>T: 0 | D: 0
                <%
            }
            else{
                Horario h= GestionDependencias.buscarHorario(idHorario); 
                %>
                    Cupos:<br>T: <%if(h!=null){%> <%=h.getTotalCupos()%><%}%>  | D: <%if(h!=null){%> <%=h.getCuposDisponibles() %><%}%> 
                <%
            }
        }
        else if(idJornada!=null){
            if(!idJornada.equals("0")){
                 ArrayList<Horario> horarios = GestionDependencias.darHorariosJornada(idJornada);
                 %>
                 <option onclick="javascript:cambiarCuposCbx('0')" selected="selected" value="0">Seleccione...</option>        
                 <%
                for(Horario h:horarios)
                {%>
                    <option onclick="javascript:cambiarCuposCbx('<%=h.getId() %>')" value="<%=h.getId() %>"><%=h.getDesde() %> - <%=h.getHasta() %></option>
                <%}
            }
            else{
                 {%>
                    <option onclick="javascript:cambiarCuposCbx('0')" selected="selected" value="0">Seleccione...</option>   
                <%}
            }
        }
           
    }
}%>




