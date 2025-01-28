# Workshop: Design and Structuring of Distributed Applications on the Internet

## Project Summary

This workshop demonstrates the implementation of a basic web server in Java that supports multiple sequential (non-concurrent) requests. The server serves static HTML pages, JavaScript files, CSS, and images from the local disk, while also handling REST services for asynchronous communication.

The client web application uses JavaScript, CSS, and images to test the server and perform asynchronous requests.

## Architecture
```
SimpleHttpServer/
├── .idea/                                   # Project configuration files (e.g., IntelliJ IDEA).
├── public/                                  # Static content exposed by the server to clients.
│   ├── images/                              # Static images for the website.
│   │   └── eci_logo.png                     # Example logo image.
│   ├── index.html                           # Main HTML page of the application.
│   ├── script.js                            # JavaScript file associated with the web page.
│   └── style.css                            # Stylesheet for the page's design.
├── src/                                     # Server source code.
│   └── main/
│       └── java/
│           └── my.company.simplehttpserver/
│               └── SimpleHttpServer.java    # Main class implementing the HTTP server.
├── target/                                  # Directory generated with compiled files (by Maven).
└── pom.xml                                  # Maven configuration file for dependency management.
```

## Testing the Server Locally

To use the server locally, follow these steps:

1. Clone the repository into your desired folder:
``` 
  git clone https://github.com/RichiVilla/Taller1_AREP.git
```

2. Open the cloned folder in your preferred IDE:
   
  ![image](https://github.com/user-attachments/assets/d60f29be-75f6-4443-81d4-3375993afa48)

3. Run the "SimpleHttpServer" file:
   
![image](https://github.com/user-attachments/assets/4e47b32d-c060-46a9-b5fb-33f0ebfd579e)

4. Open the following address in your browser:
```
   http://localhost:8080
```

  ![image](https://github.com/user-attachments/assets/15280288-e8c0-47d7-9c0b-0601c481d90f)

  
## Testing and Deployment

Once the server is running, you can use the following four REST services:

1. "Saludo": Provides a brief greeting.
![image](https://github.com/user-attachments/assets/fc2e1738-4120-4481-8c20-fc48fb596bb8)

2. "Structure": Displays the project structure.
![image](https://github.com/user-attachments/assets/ac6fe7e9-0085-450f-83a4-f9593828b0f0)

3. "Fecha": Shows the current date and time of the device.
![image](https://github.com/user-attachments/assets/1cc538a6-5f5d-4087-8642-1105cfde0e59)

4. "AREP": Displays an image of the Escuela Colombiana de Ingeniería logo.
![image](https://github.com/user-attachments/assets/6dbacf25-c4c3-4c32-b88f-9c9385696f88)
