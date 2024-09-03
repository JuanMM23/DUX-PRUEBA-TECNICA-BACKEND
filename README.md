# DUX-PRUEBA-TECNICA-BACKEND
## Es una API para gestionar información de equipos de fútbol.
## Pasos a seguir para desplegar localmente esta API:
- Clona el proyecto: https://github.com/JuanMM23/DUX-PRUEBA-TECNICA-BACKEND
- En una terminal dentro del directorio del proyecto crea una imagen de Docker con el siguiente comando: [docker build -t nombre-imagen:1.1 .]
- Correr imagen en una terminal dentro de el directorio del proyecto con el siguiente comando: [docker run -p8080:8080 --name app-equipos app-equipos:1.1]

## Utiliza base de datos en memoria
## Extras:
- Los datos necesarios para poblar la base de datos se encuentran dentro del proyecto en el archivo [data.sql] y se crean automaticamente al levantar el mismo.
- Una vez levantado el proyecto podes acceder a la interfaz de Swagger mediante el siguiente link: http://localhost:8080/swagger-ui/index.html
