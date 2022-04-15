Cordial saludo,

Para ejecutar el servicio mutant los pasos son los siguientes:
-Se debe dirigir a la aplicación POSTMAN O SOAP UI.
-Se debe seleccionar la opción POST para realizar el consumo del servicio.
-Se debe digitar la siguiente URL pública https://brandoon-arias-prueba-ml.herokuapp.com/mutant
-Seleccionar la sección "Body", subsección "Raw", Formato JSON.
-Digitar un adn en el espacio en blanco, ejemplo

{
    "adn":["BTGCGA","CAGTGC","TTATGT","AGAAGG","TCCCTA","TCACTG"]
}


-Presionar el botón send.
-En la respuesta se evidenciará si el adn es mutante o no, con su respectivo código de estado(200-OK,403-Forbidden)
---------------------------------------------------------------------------------------------------------------------------

Para ejecutar el servicio stats los pasos son los siguientes:

-Abrir un navegador(Chrome, Internet Explorer, etc)
-Digitar la siguiente url pública:

https://brandoon-arias-prueba-ml.herokuapp.com/stats

-Allí se evidenciaran las estadisticas respecto a humanos.
----------------------------------------------------------------------------------------------------------------------------

El reporte de porcentaje Code Coverage se encuentra se encuentra en la siguiente ruta de GIT.

\mercadoLibre\src\test\Report_Test_Code_Coverage.

GRACIAS.


Att: Brandoon Smith Arias Rodriguez