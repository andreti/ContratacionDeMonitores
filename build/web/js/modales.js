function venRegDep() {
    $(document).ready(function(){
        $(this.remove);
          $('#divRegDep').load('util/formularios.jsp?var=regDep');
    });	
    $("#divRegDep").dialog({
        closeOnEscape: false,
        open: function(event, ui) { $(".ui-dialog-titlebar-close").hide();},
        width : 260,
        height : 320,
        resizable : "false",
        modal : "true"
        //autOpen:false,
        //show : "fold",
//        show:{
//            //effect :"fold",
//            duration:0
//        },
//        hide:{
//            //effect :"fold",
//            duration:0
//        },
        
        //hide : "pulsate",
        
    });
}
//Dependencias
function venContRegDep() {
    $(document).ready(function(){
          $('#divRegDep').load('util/formularios.jsp?var=contRegDep');
    });	
    $("#divRegDep").dialog({
        closeOnEscape: false,
        open: function(event, ui) { $(".ui-dialog-titlebar-close").hide();},
        width : 500,
        height : 600,
        resizable : "false",
        modal : "true"
    });
}

function venDeleteDep($idDependencia){
     $(document).ready(function(){
          $('#divDelDep').load('util/formularios.jsp?var=eliminarDep&idDependencia='+$idDependencia);
    });	
    $("#divDelDep").dialog({
        width : 500,
        height : 320,
        resizable : "false",
        modal : "true"
    });
}
function venUpdateDep(){
     $(document).ready(function(){
          $('#divUpdDep').load('util/formularios.jsp?var=actualizarDep');
    });	
    $("#divUpdDep").dialog({
        closeOnEscape: false,
        open: function(event, ui) { $(".ui-dialog-titlebar-close").hide();},
        width : 500,
        height : 600,
        resizable : "false",
        modal : "true"
    });
}
function venVerDep($idDependencia){
    $(document).ready(function(){
          $('#divVerDep').load('util/formularios.jsp?var=verDependencia&idDependencia='+$idDependencia);
    });	
    $("#divVerDep").dialog({
        width : 500,
        height : 350,
        resizable : "false",
        modal : "true"
    });
}
//Estudiantes
function venRegEst(){
    $(document).ready(function(){
          $('#divRegEst').load('util/formulariosEstudiantes.jsp?var=registrarEstudiante');
    });	
    $("#divRegEst").dialog({
        width : 500,
        height : 350,
        resizable : "false",
        modal : "true"
    });
}
function venIngresoEstudiantes(){
    $(document).ready(function(){
          $('#divIngreso').load('util/formulariosEstudiantes.jsp?var=ingresar');
    });	
    $("#divIngreso").dialog({
        width : 300,
        height : 190,
        resizable : "false",
        modal : "true"
    });
}
function venPostEstu($idDependencia){
    $(document).ready(function(){
          $('#divRegPostEst').load('util/formulariosEstudiantes.jsp?var=agregarPostulacion&idDependencia='+$idDependencia);
    });	
    $("#divRegPostEst").dialog({
        width : 500,
        height : 350,
        resizable : "false",
        modal : "true"
    });
}
function venDelPostEstu($idDependencia){
    $(document).ready(function(){
          $('#divDelPostEst').load('util/formulariosEstudiantes.jsp?var=eliminarPostulacion&idDependencia='+$idDependencia);
    });	
    $("#divDelPostEst").dialog({
        width : 380,
        height : 200,
        resizable : "false",
        modal : "true"
    });
}
//aspirantes
function venDelAsp($identificacion){
    $(document).ready(function(){
          $('#divDelAsp').load('util/formulariosAspirantes.jsp?var=eliminarAspirante&identificacion='+$identificacion);
          
    });	
    $("#divDelAsp").dialog({
        width : 400,
        height : 290,
        resizable : "false",
        modal : "true"
    });
}
function venDelPostEstuBd($idHorario){
    $(document).ready(function(){
          $('#divDelPostAsp').load('util/formulariosAspirantes.jsp?var=eliminarPostulacion&idHorario='+$idHorario);
          
    });	
    $("#divDelPostAsp").dialog({
        width : 400,
        height : 290,
        resizable : "false",
        modal : "true"
    });
}
function venSelAsp($identificacion){
    $(document).ready(function(){
          $('#divSelAsp').load('util/formulariosAspirantes.jsp?var=seleccionarAspirante&identificacion='+$identificacion);
          
    });	
    $("#divSelAsp").dialog({
        width : 400,
        height : 320,
        resizable : "false",
        modal : "true"
    });
}


//monitores
function venVerDepMon($identificacion){
    $(document).ready(function(){
          $('#divVerDepMon').load('util/formulariosMonitores.jsp?var=verDependenciaM&identificacion='+$identificacion);       
    });	
    $("#divVerDepMon").dialog({
        width : 500,
        height : 330,
        resizable : "false",
        modal : "true"
    });
}
function venDelMon($identificacion){
    $(document).ready(function(){
          $('#divDelMon').load('util/formulariosMonitores.jsp?var=eliminarMonitor&identificacion='+$identificacion);  
          
    });	
    $("#divDelMon").dialog({
        width : 400,
        height : 290,
        resizable : "false",
        modal : "true"
    });
}
function venVerResMon($identificacion){
    $(document).ready(function(){
          $('#divVerResMon').load('util/formulariosMonitores.jsp?var=verResultados&identificacion='+$identificacion);  
          
    });	
    $("#divVerResMon").dialog({
        width : 400,
        height : 290,
        resizable : "false",
        modal : "true"
    });
}



//combos anidados
function cambiarHorarioCbx($idJornada){
    $('#slc_horario').load('util/formularios.jsp?var=cambiarHorario&idJornada='+$idJornada);
    cambiarCuposCbx("0");
}
function cambiarCuposCbx($idHorario){
    $('#tdCupos').load('util/formularios.jsp?var=cambiarHorario&idHorario='+$idHorario);
}
$(document).ready(function(){
    $('#slc_jornada').change(function(){
        var idJornada=$('#slc_jornada').val();
        $('#slc_horario').load('util/formularios.jsp?var=cambiarHorario&idJornada='+idJornada);
    });    
});

//enviar form
function sendForm($var,$id){
    if($var==='eliminarHorarioDep'){
        var forma = document.getElementById("formSend");
	forma.action = "GestionDependencias";
        var accion = forma.accion;
        var idHorario = forma.idHorario;
        accion.value=$var;
        idHorario.value=$id;
	forma.submit();
	return true;
    }
    else if($var==='editarHorario'){
        alert("Peticion en mantenimiento :) :O :D");
//        var forma = document.getElementById("formSend");
//	forma.action = "GestionDependencias";
//        var accion = forma.accion;
//        var idHorario = forma.idHorario;
//        accion.value=$var;
//        idHorario.value=$id;
//	forma.submit();
//	return true;
    }
    
    
}

//util
function hiddenThis($ventana){
    $($ventana).dialog("close");
}