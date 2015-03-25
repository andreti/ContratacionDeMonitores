function validarRegDep(){
    var nombre=document.getElementById("txt_nombre");
    var descripcion=document.getElementById("txt_descripcion");
    
    if(nombre.value==""){
        alert("Digite un nombre");
        nombre.focus();
        return false;
    }
    if(descripcion.value==""){
        alert("Digite una descripcion");
        descripcion.focus();
        return false;
    }
    return true;
}