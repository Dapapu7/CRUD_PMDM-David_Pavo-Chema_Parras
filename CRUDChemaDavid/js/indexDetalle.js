const URL_PRODUCTO = "http://localhost:8080/JDBCDavidChema/webapi/productos/";
const URL_DETALLE = "http://localhost:8080/JDBCDavidChema/webapi/detallesPedidos/pedido/";
const myModal = new bootstrap.Modal(document.getElementById("idModal"));
const modalWait = new bootstrap.Modal(document.getElementById("idModalWait"));

window.onload = init();

function init() {
    const queryString = window.location.search;
    console.log(queryString);
    const urlParams = new URLSearchParams(queryString);
    const idPedido = urlParams.get('idPedido');

    const peticionHTTP = fetch(URL_DETALLE+idPedido);
    peticionHTTP
    .then((respuesta) => {
        if(respuesta.ok){
            return respuesta.json();
        } else throw new Error("No se ha podido conectar a la API");
    })
    .then((detalles) => {
        let tblBody = document.getElementById("id_tblDetalles");
        for(const detalle of detalles) {
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

            tblBody.appendChild(fila);
        }
    })
}

function mostrarPedido(){
    window.history.back();
    
}