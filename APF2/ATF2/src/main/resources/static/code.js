const {createApp} = Vue;

createApp({
    data() {
        return {
            asesor: "Hombre",
        };
    },
    computed: {
        imagenAsesor() {
            return this.asesor === "Mujer"
                    ? "assets/img/woman.png"
                    : "assets/img/soporte.png";
        },
    },
    methods: {
        seleccionar(opcion) {
            this.asesor = opcion;
        },
    },
}).mount("#app");

function sugerencia() {
    let sug = prompt("¿Qué aspecto destacarías más? \n1. Precio de los Planes de Internet \n2. Medios y Servicios de Contacto \n3. Beneficios que ofrecemos");

    const resultadoDOM = document.getElementById("resultado");

    if (sug === null) {
        alert("Has cancelado la valoración");
        resultadoDOM.textContent = "Has cancelado la valoración.";
        resultadoDOM.classList.remove("text-success");
        resultadoDOM.classList.add("text-danger");
        return;
    }

    sug = parseInt(sug);
    let mensajePlan = "";

    if (sug === 1) {
        mensajePlan = "Precio de los Planes de Internet";
    } else if (sug === 2) {
        mensajePlan = "Medios y Servicios de Contacto";
    } else if (sug === 3) {
        mensajePlan = "Beneficios que ofrecemos";
    } else {
        alert("Selección no válida. Por favor, elige una opción válida.");
        resultadoDOM.textContent = "Opción no válida seleccionada.";
        resultadoDOM.classList.remove("text-success");
        resultadoDOM.classList.add("text-danger");
        return;
    }

    let mensajeCompra = "Has seleccionado: " + mensajePlan;

    let confirmarCompra = confirm(mensajeCompra + "\n¿Deseas continuar con la valoración?");

    if (confirmarCompra) {
        alert("¡Valoración recibida! Gracias por ayudarnos a mejorar.");
        resultadoDOM.textContent = "¡Valoración recibida sobre: " + mensajePlan + "!";
        resultadoDOM.classList.remove("text-danger");
        resultadoDOM.classList.add("text-success");
    } else {
        alert("Has cancelado la valoración.");
        resultadoDOM.textContent = "Has cancelado la valoración.";
        resultadoDOM.classList.remove("text-success");
        resultadoDOM.classList.add("text-danger");
    }
}

function mensajePlan1() {
    alert("Haz seleccionado el Plan Home Eco");
}

function mensajePlan2() {
    alert("Haz seleccionado el Plan Home Lite");
}

function mensajePlan3() {
    alert("Haz seleccionado el Plan Home Prime");
}