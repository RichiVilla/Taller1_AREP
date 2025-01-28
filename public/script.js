document.addEventListener("DOMContentLoaded", () => {
    const structure = `
SimpleHttpServer/
├── .idea/
├── public/
│   ├── images/
│   │   └── eci_logo.png
│   ├── index.html
│   ├── script.js
│   └── script.js
├── src/
│   └── main/
│       └── java/
│           └── my.company.simplehttpserver/
│               └── SimpleHttpServer.java
├── target/
└── pom.xml
`;

    const greetingButton = document.getElementById("greetingButton");
    const structureButton = document.getElementById("structureButton");
    const dateButton = document.getElementById("dateButton");
    const arepButton = document.getElementById("arepButton");
    const messageBox = document.getElementById("messageBox"); // Asegúrate de usar 'messageBox'
    const messageText = document.getElementById("messageText"); // Text area dentro de 'messageBox'

    // Acción para el botón "Saludo"
    greetingButton.addEventListener("click", () => {
        messageBox.innerHTML = '<pre id="messageText"></pre>';
        const newMessageText = document.getElementById("messageText");
        newMessageText.textContent = "Hola, bienvenido a nuestra aplicación de servicios REST.";
    });

    // Acción para el botón "Estructura"
    structureButton.addEventListener("click", () => {
        messageBox.innerHTML = ''; // Limpiar cualquier contenido previo
        const newMessageText = document.createElement('pre'); // Crear un nuevo elemento <pre>
        newMessageText.textContent = "Esta es la estructura de este proyecto:\n" + structure;
        messageBox.appendChild(newMessageText); // Agregar el contenido al mensaje
    });

    dateButton.addEventListener("click", () => {
        messageBox.innerHTML = '<pre id="messageText"></pre>';
        const newMessageText = document.getElementById("messageText");
        const currentDate = new Date().toLocaleString();
        newMessageText.textContent = `Fecha y hora actual: ${currentDate}`;
    });

    arepButton.addEventListener("click", () => {
        messageBox.innerHTML = ''; // Limpiar cualquier contenido previo
        const img = document.createElement('img');
        img.src = '/images/eci_logo.png'; // Ruta de la imagen en la carpeta 'images'
        img.alt = 'Imagen de AREP';
        img.style.width = '100%'; // Ajustar el tamaño si es necesario
        img.style.borderRadius = '10px'; // Estilo opcional para bordes redondeados
        img.onerror = function () {
            messageBox.innerHTML = 'No se pudo cargar la imagen. Verifica la ruta.';
        };
        messageBox.appendChild(img); // Agregar la imagen al cuadro de respuestas
    });
});
