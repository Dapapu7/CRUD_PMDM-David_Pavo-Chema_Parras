const URL = "http://localhost:8080/JDBCDavidChema/webapi/clientes";
const URLCliente = `http://localhost:8080/JDBCDavidChema/webapi/cliente/`

const myModal = new bootstrap.Modal(document.getElementById("idModal")); // Para los mensajes de error y avisos
const modalWait = new bootstrap.Modal(document.getElementById("idModalWait")); // Para los mensajes de error y avisos

window.onload = init;

function init() {
  const peticionHTTP = fetch(URL);

  peticionHTTP
    .then((respuesta) => {
      if (respuesta.ok) {
        return respuesta.json();
      } else throw new Error("No se ha podido conectar a la API");
    })
    .then((clientes) => {
      rellenarToDatosClientes(clientes);

      // Todo ha ido bien hast aquí, habilito el boton de añadir cliente
      document.getElementById("idAddCliente").addEventListener("click", addCliente);
    })
    .catch((error) => {
      muestraMsg("¡M**rd!", "¡No he podido recuperar el listado de clientes!<br>" + error, false, "error");
    });
}

function rellenarToDatosClientes(clientes) {
  let tblBody = document.getElementById("id_tblClientes");
  let select = document.getElementById("idSeleccionado");

  for (const cliente of clientes) {
    let fila = document.createElement("tr");
    let elemento = document.createElement("td");
    elemento.innerHTML = cliente.id;
    fila.appendChild(elemento);
    elemento = document.createElement("td");
    elemento.innerHTML = cliente.firstName;
    fila.appendChild(elemento);
    elemento = document.createElement("td");
    elemento.innerHTML = cliente.lastName;
    fila.appendChild(elemento);
    elemento = document.createElement("td");
    elemento.innerHTML = cliente.company;
    fila.appendChild(elemento);
    elemento = document.createElement("td");
    elemento.innerHTML = cliente.businessPhone ?? "";
    fila.appendChild(elemento);
    elemento = document.createElement("td");
    elemento.innerHTML = cliente.mobilePhone ?? "";
    fila.appendChild(elemento);
    elemento = document.createElement("td");
    elemento.innerHTML =
      `<button class="btn btn-link" onclick="editaCliente(${cliente.id})"><i class="bi-pencil"></i></button>` +
      `<button style="color:red;" class="btn btn-link"  onclick="borrarCliente(${cliente.id})"><i class="bi-x-circle"></i></button>`;
      fila.appendChild(elemento);

    elemento = document.createElement("td");
    elemento.innerHTML = 
      `<button style="color:green; "class="btn btn-link" onclick="mostrarPedidos()"><i class="bi bi-grid-fill"></i></button>`;
      fila.appendChild(elemento);

    tblBody.appendChild(fila);
  }

  select.addEventListener("change", buscarCliente);
}

function buscarCliente(e) {
  const select = document.getElementById("idSeleccionado");
  const idClienteSeleccionado = select.value;
  const peticionHTTP2 = fetch(URLCliente+idClienteSeleccionado);

  peticionHTTP2
  .then((respuestas) => {
    if (respuestas.ok) {
      return respuestas.json();
    } else throw new Error("No he podido leer la petición");
  })
  .then((clientes) => {
    console.log(clientes);
    rellenarDatosCliente(clientes);
  })
}

function rellenarDatosCliente(clientes){
  limpiarTabla();
}

function editaCliente(idcliente) {
  window.location.href = `editarCliente.html?idcliente=${idcliente}`;
}

function addCliente() {
  window.location.href = "editarCliente.html";
}

function borrarCliente(idcliente) {
  muestraMsg(
    "¡Atención!",
    `¿Estas seguró de querer borrar el cliente ${idcliente}?`,
    true,
    "question",
    "Adelante con los faroles!",
    "Naaa, era broma..."
  );
  document.getElementById("idMdlOK").addEventListener("click", () => {
    
    borrarClienteAPI(idcliente);
  });
}

function mostrarPedidos() {
  window.location.href = "indexPedido.html";
}

function mostrarClientes() {
  window.location.href = "indexCliente.html";
}

function borrarClienteAPI(idcliente) {
  myModal.hide();
  modalWait.show();
  opciones = {
    method: "DELETE", // Modificamos la BBDD
  };

  fetch(URL + "/" + idcliente, opciones)
    .then((respuesta) => {
      if (respuesta.ok) {
        return respuesta.json();
      } else 
      {
        throw new Error(`Fallo al borrar, el servidor responde con ${respuesta.status}-${respuesta.statusText}`);
      }
        
    })
    .then((respuesta) => {
      modalWait.hide();
      muestraMsg(`¡Cliente ${idcliente} Borrado!`, "¡A tomar por saco!", false, "success");
      document.getElementById('idMdlClose').addEventListener("click", () => {
        location.reload();
        document.getElementById('idMdlClose').removeEventListener("click");
      })
      
    })
    .catch((error) => {
      modalWait.hide();
      muestraMsg(
        "Cliente NO borrado",
        "¿Es posible que este cliente tenga algún pedido? 🤔<br>" + error,
        false,
        "error"
      );
    });
}

/**
 * Muestra un mensaje en el modal
 */
function muestraMsg(titulo, mensaje, okButton, tipoMsg, okMsg = "OK", closeMsg = "Close") {
  document.getElementById("idMdlOK").innerHTML = okMsg;
  document.getElementById("idMdlClose").innerHTML = closeMsg;

  myModal.hide();
  switch (tipoMsg) {
    case "error":
      {
        titulo = "<i style='color:red ' class='bi bi-exclamation-octagon-fill'></i> " + titulo;
      }
      break;
    case "question":
      {
        titulo = "<i style='color:blue' class='bi bi-question-circle-fill'></i> " + titulo;
      }
      break;
    default:
      {
        titulo = "<i style='color:green' class='bi bi-check-circle-fill'></i> " + titulo;
      }
      break;
  }
  document.getElementById("idMdlTitle").innerHTML = titulo;
  document.getElementById("idMdlMsg").innerHTML = mensaje;
  document.getElementById("idMdlOK").style.display = okButton ? "block" : "none";

  myModal.show();
}

function limpiarTabla() {
  let bodyTable = document.getElementById("id_tblClientes");
  bodyTable?.remove();
}
