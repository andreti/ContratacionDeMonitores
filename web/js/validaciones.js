function validarRegDep() {
    var nombre = document.getElementById("txt_nombre");
    var descripcion = document.getElementById("txt_descripcion");

    if (nombre.value === "") {
        alert("Digite un nombre");
        nombre.focus();
        return false;
    }
    if (descripcion.value === "") {
        alert("Digite una descripcion");
        descripcion.focus();
        return false;
    }
    return true;
}
var enviarForm = true;
var seguir = false;
var n1;
var n2;
var operador;
var expre = /(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$/;
var antiRobotString;
function validarFormularioAdmin() {
   
    elementos = document.getElementsByClassName("requerid");
    todoCorrecto = true;
    if (!validarCampos(elementos)) 
        return false;
    
    elementos = document.getElementsByClassName("passwordForm");
    if (!validarContrasenia(elementos)) 
        return false;

    seguir = false;
    validarNick();
    wait(1);
    if (!validarAntiRobot(document.getElementById("txtAntiRobot"))) 
        return false;
    
    if (enviarForm) {
        document.getElementById('divFormularioAdmin').style.display = 'none';
        return true;
    } else {
        document.getElementById("txt_nick").value = "";
        return false;
    }
}

function wait(nsegundos) {
    objetivo = (new Date()).getTime() + 1000 * Math.abs(nsegundos);
    while ((new Date()).getTime() < objetivo)
        ;
}



function validarCampos(elementos) {
    for (i = 0; i < elementos.length; i++) {
        elemento = elementos[i];
        if (elemento.value === null || elemento.value === "" || elemento.value === "vacio" || elemento.value === " ") {
            elemento.focus();
            elemento.style.borderColor = "red";
            setTimeout(function () {
                elemento.style.borderColor = "transparent";
            }, 2000);
            elemento.value = "";
            return false;
        }
    }
    return true;
}
function validarContrasenia(elementos) {
    correcto = true;
    for (i = 0; i < elementos.length && correcto; i++) {
        if (i > 0) {
            anterior = elementos[i - 1].value;
            actual = elementos[i].value;
            correcto = actual === anterior ? true : false;
        }
        if (!correcto) {
            elementos[i - 1].value = "";
            elementos[i - 1].focus();
            elementos[i].value = "";
            mostrarMensaje("Las contraseñas no coinciden", "mensajeFormulario", 2000);
        }
    }
    return correcto ? validarContrasenaSegura(elementos[0].value) : false;
}

function validarContrasenaSegura(password) {

    if (expre.test(password)) {
        return true;
    } else {
        mostrarMensaje("La contraseña debe contener minimo 8 caracteres entre numeros, simbolos y letras, por ejemplo: C0ntr@señaS3gura ", "mensajeFormulario", 10000);
    }
    return false;
}
function mostrarMensaje(mensaje, idLabel, milisegundos) {
    msg = document.getElementById(idLabel);
    msg.style.display = "inherit";
    msg.innerHTML = mensaje;
    setTimeout(function () {
        msg.style.display = "none";
        msg.innerHTML = "";
    }, milisegundos);
}
function animarFormulario() {
    estado = document.getElementById('divFormularioAdmin').style.display;
    if (estado === 'none' || estado === '') {
        document.getElementById('divFormularioAdmin').style.display = 'inherit';
        cargarAntirobot();
    }
    else
        document.getElementById('divFormularioAdmin').style.display = 'none';
}
function cargarAntirobot() {
    n1 = Math.round(Math.random() * 10);
    n2 = Math.round(Math.random() * 10);
    operador = null;
    while (operador === null) {
        n = Math.round(Math.random() * 10);
        if (n > 0 && n < 4) {
            switch (n) {
                case 1:
                    operador = "+";
                    break;
                case 2:
                    operador = "-";
                    break;
                case 3:
                    operador = "*";
                    break;
            }
        }
    }
    antiRobotString = n1 + operador + n2;
    document.getElementById("antiRobot").innerHTML = antiRobotString + " = ";
}
function validarAntiRobot(ele) {
    resul = eval(antiRobotString);
    if (!isNaN(ele.value) && parseFloat(ele.value) === resul)
        return true;
    else {
        mostrarMensaje("Validación antirobot incorrecta", "mensajeAntirobot", 2000);
        cargarAntirobot();
        document.getElementById("txtAntiRobot").value = "";
        document.getElementById("txtAntiRobot").focus();
        return false;
    }
}


var xhr;
function validarNick() {
    if (window.ActiveXObject) {
        xhr = new ActiveXObject("Microsoft.XMLHttp");
    }
    else if ((window.XMLHttpRequest) ||
            (typeof XMLHttpRequest) !== undefined) {
        xhr = new XMLHttpRequest();
    }
    else {
        alert("Su navegador no tiene soporte para AJAX");
        return;
    }
    enviaPeticion();
}
function enviaPeticion() {

    xhr.open("POST", "GestionAdministrador", true);
    xhr.onreadystatechange = procesaResultado;
//definición del tipo de contenido del cuerpo
//para formularios HTML
    xhr.setRequestHeader("Content-Type",
            "application/x-www-form-urlencoded");
    var datos = "nick=" + document.getElementById("txt_nick").value + "&accion=validarNick";
//se envían los datos en el cuerpo de la petición
    xhr.send(datos);
}
function obtenerDatos() {
//se obtiene la colección de controles que hay
//en el formulario
    var controles = document.forms[0].elements;
    var datos = new Array();
    var cad = "";
    for (var i = 0; i < controles.length; i++) {
        cad = encodeURIComponent(controles[i].name) + "=";
        cad += encodeURIComponent(controles[i].value);
        datos.push(cad);
    }
//se forma la cadena con el método join() del array
//para evitar múltiples concatenaciones
    cad = datos.join("&");
    return cad;
}
function procesaResultado() {
    if (xhr.readyState === 4) {
        if (xhr.responseText > 0) {
            mostrarMensaje("El nick ya se encuentra registrado", "mensajeNick", 2000);
            document.getElementById("txt_nick").value = "";
            document.getElementById("txt_nick").focus();
            enviarForm = false;
        } else
            enviarForm = true;

        seguir = true;
    }
}

function validarTeclas(e) {
    key = e.keyCode;
    if (key === 32 || key === 17)
        e.preventDefault();
    desactivarCopiarPegarCortar(e);
}

function noArrastrar(e) {
    e.preventDefault();
}

function disableCopyPaste(elm) {
    // Disable cut/copy/paste key events
    elm.onkeydown = interceptKeys

    // Disable right click events
    elm.oncontextmenu = function () {
        return false
    }
}

function interceptKeys(evt) {
    evt = evt || window.event // IE support
    var c = evt.keyCode
    var ctrlDown = evt.ctrlKey || evt.metaKey // Mac support

    // Check for Alt+Gr (http://en.wikipedia.org/wiki/AltGr_key)
    if (ctrlDown && evt.altKey)
        return true

    // Check for ctrl+c, v and x
    else if (ctrlDown && c == 67)
        return false // c
    else if (ctrlDown && c == 86)
        return false // v
    else if (ctrlDown && c == 88)
        return false // x

    // Otherwise allow
    return true
}
