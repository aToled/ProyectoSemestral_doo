# ProyectoSemestral_doo

## Grupo 19: 

- Alonso Toledo Aceituno
- Benjamin Poblete Castillo

## Patrones Usados: 
Strategy, Observer, Factory Method, Singleton, Mediator
 - Strategy:  
> Elegimos este patron porque permite seleccionar un algoritmo en tiempo de ejecuccion, 
> lo cual es ideal para encapsular los multiples algoritmos que se usan para la
> resolucion de solicitudes sin modificar la clase base.
> 
> Concretamente en el proyecto, los estudiantes pueden enviar solicitudes de asignaturas,
> las cuales son resueltas por el GestorSolicitudes usando distintas estrategias (como asignar
> la clase más barata, la con menos estudiantes, la que tenga cupo, etc.), las cuales eligen Clases a 
> las que el Estudiante se puede unir con base en sus atributos.  
> Gracias a Strategy podemos definir esta familia de metodos de resolucion de solicitudes
> e intercambiarlos facilemente sin cambiar el nucleo del Gestor o de la Solicitud.  
>   
> Clases Involucradas:
> - EstrategiaSolicitud: Interface de Strategy.
> - EstrategiaDefault: La estrategia que se selecciona en caso de que todas las otras fallen.
> - EstrategiaBloqueHorarioPreferido: Elige las clases que coinciden con los BloqueHorario que el Estudiante
> marco en sus preferencias.
> - EstrategiaDiaPreferido: Como la BloqueHorarioPreferido pero con Días.
> - EstrategiaHorarioPreferido: Como la BloqueHorarioPreferido pero con Horarios.
> - EstrategiaConMenosEstudiantes: Elige la clase con menos estudiantes a la que se pueda unir el Estudiante
> si este marco que esa era una de sus preferencias.
> - EstrategiaMenorTarifa: Como la ConMenosEstudiantes pero con tarifas.
> - GestorSolicitudes: Contexto que aplica la/s estrategia/s.
> - "ClaseInterfazHipotetica": Contexto que instancia la/s estrategia/s.

 - Observer:
> d
- Factory Method:
> d
- Singleton:
> d
- Mediator:
> d

## Decisiones Importantes:
Algunas de las decisiones importantes que tuvimos que tomar fueron:  
- El formato de los archivos que almacenan los Profesores/Estudiantes/Solicitudes, originalmente 
se iban a almacenar en archivos .txt, pero el profesor nos mencionó que sería mejor usar XML o JSON,
entonces tras investigar un poco acerca de ambos decidimos usar JSON y la libreria GSON para el manejo.  
  

- Como el administrador registraba y gestionaba la información de los Estudiantes, desde que 
empezamos a pensar en como implementar el Tema 3 decidimos que los Estudiantes iban a poder registrarse 
ellos mismos en vez de que el Administrador lo haga.  


- El uso de Sets para la creacion de Profesores/Estudiantes/Clases, fue algo muy significativo porque con ese cambio los
profesores podrian impartir multiples clases en multiples días y horarios con multiples cantidades maximas de 
alumnos y tarifas, y lo mismo para los Estudiantes, podrian unirse a multiples clases e indicar multiples preferencias.

  
- S

## Problemas Identificados: 
Algunos de los problemas que pudimos identificar fueron:
- El manejo del tiempo y separacion de responsabilidades: definitivamente manejamos pobremente el tiempo, puesto que estuvimos pensando
mucho en como podriamos implementar las cosas, qué patrones usar, como se podria ver el programa, en vez de empezar a programar,
aunque igual reconozco que una de las partes más importantes es pensar en como hacer el proyecto y organizarse bien, encuentro
que fuimos poco eficientes en esa etapa.
- Si tuviera que criticar nuestra gestion definitivamente me centraria en la interfaz, porque
la empezamos a desarollar muy encima de la fecha de entrega, lo cual eso deja poco tiempo para pulir el programa
y agregar nuevas funcionalidades, además, lo grafico es lo más notorio, es lo primero en lo que uno se fija al correr
el programa, puede estar muy bueno el codigo, pero si la interfaz es mediocre uno no queda del todo contento con los resultados.