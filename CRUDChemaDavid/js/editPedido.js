const URL = "http://localhost:8080/JDBCDavidChema/webapi/pedidos";
const URL_BORRAR =
  "http://localhost:8080/JDBCDavidChema/webapi/detallesPedidos";
const URL_PRODUCTO = "http://localhost:8080/JDBCDavidChema/webapi/productos/";
const URL_DETALLE =
  "http://localhost:8080/JDBCDavidChema/webapi/detallesPedidos/pedido/";
const myModal = new bootstrap.Modal(document.getElementById("idModal"));
const myAddLineaModal = new bootstrap.Modal(
  document.getElementById("idAddLineaModal")
);
let detallesPedido;
window.onload = init;

function init() {
  let idPedido;
  if (window.location.search != "") {
    const queryStr = window.location.search.substring(1);
    const parametro = queryStr.split("=");
    idPedido = parametro[1];

    rellenarPedido(idPedido);
  } else {
    document.getElementById("idId").value = "Nuevo Pedido";
    // document.getElementById("idcustomer_id").value = "Nuevo Cliente";
    // document.getElementById("idemployee_id").value = "Nuevo Trabajador";
    document.getElementById("idSalvar").disabled = false;
  }

  document.getElementById("idAddLinea").addEventListener("click", addLinea);
  document.getElementById("idCancel").addEventListener("click", (evt) => {
    evt.preventDefault();
    volver();
  });

  document
    .getElementById("idFormPedido")
    .addEventListener("submit", salvarPedido);

  const peticionHTTP = fetch(URL_DETALLE + idPedido);
  peticionHTTP
    .then((respuesta) => {
      if (respuesta.ok) {
        return respuesta.json();
      } else throw new Error("No se ha podido conectar a la API");
    })
    .then((detalles) => {
      detallesPedido = detalles;

      pintaDetallePedido(detallesPedido);
    });

  const peticionHTTP5 = fetch(URL_DETALLE);
  peticionHTTP5
    .then((respuesta) => {
      if (respuesta.ok) {
        return respuesta.json();
      } else throw new Error("¡Me chachís! No se ha podido conectar a la API");
    })

    .then((detalles) => {
      let tblBody = document.getElementById("id_tblDetalle");
      for (const detalle of detalles) {
        let fila = document.createElement("tr");
        let elemento = document.createElement("td");
        elemento.innerHTML = detalle.product_code;
        fila.appendChild(elemento);

        elemento = document.createElement("td");
        elemento.innerHTML = detalle.product_name;
        fila.appendChild(elemento);

        elemento = document.createElement("td");
        elemento.innerHTML = detalle.unit_price;
        fila.appendChild(elemento);

        elemento = document.createElement("td");
        elemento.innerHTML = detalle.category;
        fila.appendChild(elemento);

        elemento = document.createElement("td");
        elemento.innerHTML = detalle.standard_cost;
        fila.appendChild(elemento);

        elemento = document.createElement("td");
        let input = document.createElement("input");
        input.setAttribute("type", "number");
        input.setAttribute("id", "idquantity_" + detalle.product_id);
        input.setAttribute("name", "quantity");
        input.setAttribute("min", "1");
        elemento.appendChild(input);
        fila.appendChild(elemento);
        elemento = document.createElement("td");

        elemento = document.createElement("td");
        elemento.innerHTML = `<button style="color:yellow;" onclick="añadirProducto(${detalle.product_id})" class="btn btn-link"><i class="bi bi-plus-square-fill"></i></button>`;
        fila.appendChild(elemento);

        tblBody.appendChild(fila);
      }
    });
}

function borrarProducto(idProducto) {
  muestraMsg(
    "¡Oye, Oye!",
    `¿Estás seguro de querer borrar el producto ${idProducto}`,
    true,
    "question",
    "¡Si, pliss!",
    "¡No, no, no!"
  );

  document.getElementById("idMdlOK").addEventListener("click", () => {
    borrarProductoAPI(idProducto);
  });

  document.getElementById("idMdlClose").addEventListener("click", () => {
    location.reload();
  });
}

function borrarProductoAPI(idProducto) {
  myModal.hide();

  opciones = {
    method: "DELETE",
  };

  fetch(URL_BORRAR + "/" + idProducto, opciones)
    .then((respuesta) => {
      if (respuesta.ok) {
        return respuesta.json();
      } else
        throw new Error(
          `¡Jopelines! Fallo al borrar, el servidor response con ${respuesta.status}-${respuesta.statusText} `
        );
    })
    .then((respuesta) => {
      muestraMsg(
        `!Producto ${idProducto} Borrado, de locos!`,
        "Good bye!!",
        false,
        "success"
      );
      document.getElementById("idMdlClose").addEventListener("click", () => {
        location.reload();
        document
          .getElementById("idMdlClose")
          .removeEventListener("click", () => {});
      });
    })
    .catch((error) => {
      muestraMsg(
        "¡Leches! Producto NO borrado",
        "¿Es posible que este pedido tengo algo extraño? <br>" + error,
        false,
        "error"
      );
    });
}

function rellenarPedido(idPedido) {
  const peticionHTTP = fetch(URL + "/" + idPedido);

  peticionHTTP
    .then((respuesta) => {
      if (respuesta.ok) {
        return respuesta.json();
      } else throw new Error("Return not ok.");
    })
    .then((pedido) => {
      let inputs = document.getElementsByTagName("input");
      for (let input of inputs) {
        input.value = pedido[input.name] ?? "";
      }
      document.getElementById("idSalvar").disabled = false;
    })
    .catch((error) => {
      muestraMsg(
        "¡Lechugas! ",
        "No se ha podido recuperar este Pedido " + error,
        false
      );
    });
}

function salvarPedido(evt) {
  evt.preventDefault();

  // Creo un array con todo los datos formulario
  let pedido = {};

  // Relleno un array cliente con todos los campos del formulario
  let inputs = document.getElementsByTagName("input");
  for (const input of inputs) {
    pedido[input.name] = input.value;
  }

  if (pedido.id != "Nuevo Pedido") {
    // Modificamos
    opciones = {
      method: "PUT", // Modificamos la BBDD
      body: JSON.stringify(pedido), // Paso el array cliente a un objeto que luego puedo jsonear
      headers: {
        "Content-Type": "application/json",
      },
    };
  }

  fetch(URL, opciones)
    .then((respuesta) => {
      if (respuesta.ok) {
        return respuesta.json();
      } else throw new Error("Fallo al actualizar: " + respuesta);
    })
    .then((respuesta) => {
      muestraMsg(
        "Datos Actualizados",
        "Todo parace haber ido bien 🎉, menos mal...",
        false,
        "success"
      );
    })
    .catch((error) => {
      muestraMsg(
        "¡Jopetas! ",
        "No he podido actulizar la Base de Datos 🥺 " + error,
        false,
        "error"
      );
    });

  return false;
}

function volver() {
  window.history.back();
}

function muestraMsg(
  titulo,
  mensaje,
  okButton,
  tipoMsg,
  okMsg = "OK",
  closeMsg = "Close"
) {
  document.getElementById("idMdlOK").innerHTML = okMsg;
  document.getElementById("idMdlClose").innerHTML = closeMsg;

  myModal.hide();
  switch (tipoMsg) {
    case "error":
      {
        titulo =
          "<i style='color:red ' class='bi bi-exclamation-octagon-fill'></i> " +
          titulo;
      }
      break;
    case "question":
      {
        titulo =
          "<i style='color:blue' class='bi bi-question-circle-fill'></i> " +
          titulo;
      }
      break;
    default:
      {
        titulo =
          "<i style='color:green' class='bi bi-check-circle-fill'></i> " +
          titulo;
      }
      break;
  }
  document.getElementById("idMdlTitle").innerHTML = titulo;
  document.getElementById("idMdlMsg").innerHTML = mensaje;
  document.getElementById("idMdlOK").style.display = okButton
    ? "block"
    : "none";

  myModal.show();
}

function mostrarClientes() {
  window.location.href = "indexCliente.html";
}

function addLinea(evt) {
  myAddLineaModal.show();
}

function añadirProducto(idproducto) {
  const cantidad = document.getElementById("idquantity_" + idproducto).value;
  myAddLineaModal.hide();
  const peticionHTTP5 = fetch(URL_PRODUCTO + idproducto);
  peticionHTTP5
    .then((respuesta) => {
      if (respuesta.ok) {
        return respuesta.json();
      } else throw new Error("¡Me chachís! No se ha podido conectar a la API");
    })

    .then((producto) => {
      // let tblBody = document.getElementById("id_tblDetalles");
      // let fila = document.createElement("tr");
      // let elemento = document.createElement("td");
      // elemento.innerHTML = producto.product_code;
      // fila.appendChild(elemento);

      // elemento = document.createElement("td");
      // elemento.innerHTML = producto.product_name;
      // fila.appendChild(elemento);

      // elemento = document.createElement("td");
      // elemento.innerHTML = producto.unit_price + " €";
      // fila.appendChild(elemento);

      // elemento = document.createElement("td");
      // elemento.innerHTML = producto.category;
      // fila.appendChild(elemento);

      // elemento = document.createElement("td");
      // elemento.innerHTML = producto.standard_cost + " €";
      // fila.appendChild(elemento);

      // elemento = document.createElement("td");
      // elemento.innerHTML = producto.quantity;
      // fila.appendChild(elemento);

      // elemento = document.createElement("td");
      // elemento.innerHTML =
      // `<button style="color:red;" class="btn btn-link"  onclick="borrarProducto(${producto.product_id})"><i class="bi-x-circle"></i></button>`;
      // fila.appendChild(elemento);

      // tblBody.appendChild(fila);
      detallesPedido.push({
        order_id: producto.order_id,
        product_code: producto.product_code,
        product_name: producto.product_name,
        unit_price: producto.standard_cost,
        category:producto.category,
        standard_cost: producto.standard_cost,
        quantity:producto.quantity

      });
      pintaDetallePedido(detallesPedido);
      
      console.log(producto);
    });
}

function pintaDetallePedido(detalles) {
  let tblBody = document.getElementById("id_tblDetalles");
  tblBody.innerHTML = "";
  for (const detalle of detalles) {
    let fila = document.createElement("tr");
    let elemento = document.createElement("td");
    elemento.innerHTML = detalle.product_code;
    fila.appendChild(elemento);

    elemento = document.createElement("td");
    elemento.innerHTML = detalle.product_name;
    fila.appendChild(elemento);

    elemento = document.createElement("td");
    elemento.innerHTML = detalle.unit_price + " €";
    fila.appendChild(elemento);

    elemento = document.createElement("td");
    elemento.innerHTML = detalle.category;
    fila.appendChild(elemento);

    elemento = document.createElement("td");
    elemento.innerHTML = detalle.quantity;
    fila.appendChild(elemento);

    elemento = document.createElement("td");
    elemento.innerHTML = `<button style="color:red;" class="btn btn-link"  onclick="borrarProducto(${detalle.product_id})"><i class="bi-x-circle"></i></button>`;
    fila.appendChild(elemento);

    tblBody.appendChild(fila);
  }
}
