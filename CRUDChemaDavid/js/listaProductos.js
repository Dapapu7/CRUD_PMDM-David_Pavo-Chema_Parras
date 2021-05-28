const URL_DETALLE = "http://localhost:8080/JDBCDavidChema/webapi/detallesPedidos/pedido";

window.onload = init;

function init(){
    const peticionHTTP = fetch(URL_DETALLE);
    peticionHTTP
    .then((respuesta) => {
        if(respuesta.ok){
            return respuesta.json();
        } else throw new Error("¡Me chachís! No se ha podido conectar a la API");
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