const URL = "http://localhost:8080/JDBCDavidChema/webapi/pedidos";

window.onload = init;

function init() {
    const peticionHTTP = fetch(URL);
    peticionHTTP
    .then((respuesta) => {
        if(respuesta.ok){
            return respuesta.json();
        } else throw new Error("¡No me jorobes! No se ha podido recuperar los datos de la API");
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

            tblBody.appendChild(fila);
        }
    })

    
}