const URL = "http://localhost:8080/JDBCDavidChema/webapi/pedidos";
const myModal = new bootstrap.Modal(document.getElementById("idModal"));

window.onload = init;

function init() {
    if(window.location.search != "") {
        const queryStr = window.location.search.substring(1);
        const parametro = queryStr.split("=");
        idPedido = parametro[1];

        rellenarPedido(idPedido);
    } else {
        document.getElementById("idId").value = "Nuevo Pedido";
        document.getElementById("idSalvar").disabled = false;
    }

    document.getElementById("idCancel").addEventListener("click", (evt) => {
        evt.preventDefault();
        volver();
    })

    document.getElementById("idFormPedido").addEventListener("submit", salvarPedido);
}

function rellenarPedido(idPedido) {
    const peticionHTTP = fetch(URL + "/" + idPedido);

    peticionHTTP
    .then((respuesta) => {
        if (respuesta.ok) {
            return respuesta.json();
        } else throw new Error("Return not ok");
    })
    .then((pedido) => {
        let inputs = document.getElementsByTagName("input");
        for(let input of inputs) {
            input.value = pedido[input.name] ?? "";
        }
        document.getElementById("idSalvar").disabled = false;
    })
    .catch((error) => {
        muestraMsg("¡Jopetas!" , "No se ha podido recuperar este Pedido " + error, false);
    });
}

function salvarCliente(evt) {
    evt.preventDefault();

    let pedido = {};

    let inputs = document.getElementsByTagName("input");
    for(let input of inputs) {
        pedido[input.name] = input.value;
    }

    if (pedido.id = "Nuevo Pedido") {
        delete pedido.id;
        opciones = {
            method: "POST",
            body: JSON.stringify(pedido),
            headers: {
                "Content-Type": "application/json",
            },
        };
    } else {
        opciones = {
            method = "PUT",
            body: JSON.stringify(pedido),
            headers: {
                "Content-Type": "application/json",
            },
        };
    }

    fetch(URL, opciones)
    .then((respuesta) => {
        if(respuesta.ok) {
            return respuesta.json();
        } else throw new Error("Fallo al actualizar: " + respuesta);
    })
    .then((respuesta) => {
        muestraMsg("Datos Actualizados", "Todo parece haber ido bien 🎉", false, "success");
    })
    .catch((error) => {
        muestraMsg("Me cachís", "No se ha podido actualizar la BBDD 🥺 " + error, false, "error");
    });

    return false;
}

function volver() {
    window.history.back()
}


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