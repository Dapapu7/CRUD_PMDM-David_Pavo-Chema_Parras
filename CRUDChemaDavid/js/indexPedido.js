const URL = "http://localhost:8080/JDBCDavidChema/webapi/pedidos/cliente/";
const myModal = new bootstrap.Modal(document.getElementById("idModal"));
const modalWait = new bootstrap.Modal(document.getElementById("idModalWait"));

window.onload = init;

function init() {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const idCliente = urlParams.get('idCliente')
    const peticionHTTP = fetch(URL+idCliente);

    peticionHTTP
    .then((respuesta) => {
        if (respuesta.ok) {
            return respuesta.json();
        } else throw new Error("No se ha podido conectar a la API");
    })
    .then((pedidos) => {
        let tblBody = document.getElementById("id_tblPedidos");
        for (const pedido of pedidos) {
            let fila = document.createElement("tr");
            let elemento = document.createElement("td");
            elemento.innerHTML = pedido.id;
            fila.appendChild(elemento);

            elemento = document.createElement("td");
            elemento.innerHTML = pedido.shipper_id;
            fila.appendChild(elemento);

            elemento = document.createElement("td");
            const fecha = new Date(pedido.order_date);
            elemento.innerHTML = fecha.getDay()+"/"+(fecha.getMonth()+1)+"/"+fecha.getFullYear();
            
            fila.appendChild(elemento);

            elemento = document.createElement("td");
            elemento.innerHTML = pedido.ship_address ?? "";
            fila.appendChild(elemento);

            elemento = document.createElement("td");
            elemento.innerHTML = pedido.ship_state_province;
            fila.appendChild(elemento);

            elemento = document.createElement("td");
            elemento.innerHTML = pedido.status_id;
            fila.appendChild(elemento);

            elemento = document.createElement("td");
            elemento.innerHTML = 
              `<button class="btn btn-link" onclick="editarPedido(${pedido.id})"><i class="bi-pencil"></i></button>` +
              `<button style="color:red;" class="btn btn-link"  onclick="borrarPedido(${pedido.id})"><i class="bi-x-circle"></i></button>`;
            fila.appendChild(elemento);

            tblBody.appendChild(fila);
        }

        document.getElementById("idAddPedido").addEventListener("click", addPedido);
    })
}

function addPedido() {
    window.location.href = "editarPedido.html";
}


function editarPedido (idPedido) {
    window.location.href = `editarPedido.html?idPedido=${idPedido}`;
}

function borrarPedido (idPedido) {
    muestraMsg(
        "¡Atención!",
        `¿Estás seguro de querer borrar el pedido ${idPedido}?`, true,
        "question",
        "De una!",
        "No por favor!!"
    );

    document.getElementById("idMdlOK").addEventListener("click", () => {
        borrarPedidoAPI(idPedido);
    });
}

function mostrarClientes() {
    window.location.href = "indexCliente.html";
}

function borrarPedidoAPI(idPedido){
    myModal.hide();
    modalWait.show();

    opciones = {
        method: "DELETE",
    };

    fetch(URL + "/" + idPedido, opciones)
    .then((respuesta) => {
        if (respuesta.ok){
            return respuesta.json();
        } else throw Error(`Fallo al borrar, el servidor responde con ${respuesta.status}-${respuesta.statusText}`);
    })
    .then((respuesta) => {
        modalWait.hide();
        muestraMsg(`¡Pedido ${idPedido} Borrado!`, "Good bye!!", false, "success");
        document.getElementById("idMdlClose").addEventListener("click", () => {
            location.reload();
            document.getElementById("idMdlClose").removeEventListener("click");
        })
    })
    .catch((error) => {
        modalWait.hide();
        muestraMsg(
            "Cliente NO borrado",
            "¿Es posible que este pedido tengo algo extraño? <br>" + error, false,
            "error"
        );
    });
    
}

function muestraMsg(titulo, mensaje, okButton, tipoMsg, okMsg = "OK", closeMsg = "CLOSE") {
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