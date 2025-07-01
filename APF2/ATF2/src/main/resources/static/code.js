const {createApp} = Vue;

createApp({
    data() {
        return {
            asesorDisplay: "Hombre",
            asesorValue: "MASCULINO",
        };
    },
    computed: {
        imagenAsesor() {
            if (this.asesorValue === "FEMENINO") {
                return "assets/img/woman.png";
            } else {
                return "assets/img/soporte.png";
            }
        },
    },
    methods: {
        seleccionarAsesor(value, display) {
            this.asesorValue = value;
            this.asesorDisplay = display;
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


function mensajePlan(nombreDelPlan) {
    alert("Has seleccionado el " + nombreDelPlan + ". ¡Pronto nos pondremos en contacto contigo!");
    // Aquí puedes añadir más lógica, como redirigir a una página de contratación
    // o abrir un modal con un formulario de contacto específico para ese plan.
}
