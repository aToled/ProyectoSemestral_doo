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
> La implementació del Observer es con la finalidad de que PanelSolicitudes se pueda actualizar autdomaticamente.
Este pude suscribirce a GestorSolicitudes si se inicia mediante el medoto suscribirse. Así, PanelSolicitud estaria constantemente
validando cambios en las solicitudes
> 
> La finalidad principal de esto es poder ceparar la interfaz de usuario de la parte logica y todo lo que esta
detras de esto. Si se quiere modificar una solicitud esto es notificado a PanelSolicitudes y de esta manera
se puede tener todo actualizado a lo más reciente posible, con esto logramos que el programa esté actualizado
con los datos más recientes
- Factory Method:
> Las clases Estudiante y Profesor presentan una complejidad considerable debido a la gran cantidad de
parámetros y métodos que contienen. Sin embargo, con el patrón Factory Method, facilitamos la creación
de nuevas instancias de estas personas. 
> Este patrón nos permite, además, gestionar un archivo JSON. Así, al solicitar una nueva instancia, 
esta puede ser agregada automáticamente al sistema. También podemos crear solicitudes, obtener listas
y disponer de otras funciones necesarias para el uso normal del programa.
> 
>  Clases Factory
> - ProfesorFactory
> - EstudianteFactory
- Singleton:
> El patrón Singleton es un patrón de diseño utilizado comúnmente en la implementación de elementos
como calendarios o ventanas. En general, este patrón se implementa con la finalidad de trabajar con
una sola instancia de un objeto, evitando así los problemas que podría traer la clonación de esos objetos.
Al reducir la duplicación, se minimizan los desfases de información y, además, se logra mejorar la seguridad
del código.
> 
> Donde está presente el Singleton en nuestro codigo
> - Ventana
> - GestorSolicitudes
> - Calendario
> - ProfesorFactory
> - EstudianteFactory
> - ManejoClasesJSON
- Mediator:
> La implementación del patrón Mediator nos ayudó con la comunicación entre clases. Un ejemplo claro en
nuestro código sería la clase Ventana, porque este patrón nos permite ejecutar acciones que trascienden
a través de más de una clase. Sin embargo, mediante el Mediator, esto mejora tanto la calidad del código
como también reduce la cantidad de errores, ya que podemos simplificar varias cosas gracias a esto.

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

  
- Manejar las diferentes estrategias fue una implementación usada al momento de la distribución de alumnos para la
creación de clases particulares. De esta manera, se pueden tener en cuenta diferentes parámetros, dependiendo de 
las preferencias del estudiante. De esta forma, se puede facilitar un poco el uso de algoritmos y así también no
generar conflictos más adelante, dada la existencia de tantos condicionales. Esto permite mejorar el mantenimiento 
del código y tener mayor legibilidad.

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
- A pesar de haber aplicado la mayoría de nuestros conocimientos actuales, la seguridad del programa es bastante precaria.
Esto se debe a que, para los registros y datos obtenidos de archivos, no contamos con ningún tipo de
codificación ni una seguridad muy elevada.
Con nuestras herramientas actuales, la información se trabaja con tipos de datos int o string,
lo que implica una seguridad bastante baja. Así, cualquier persona con conocimientos medios de
programación podría acceder y modificar estos datos a conveniencia.

## Mejoras Adicionales a Futuro: 
Gracias a nuestra corta experiencia como programadores, generamos diversas ideas en las cual se puede mejorar este programa a futuro. Aquí
presentamos las áreas clave en las cuales nos gustaría mejorar a futuro:
- Seguridad de los datos
- Conexión entre clases y archivos del programa
- Mejoras Visuales para ser mas llamativo
- Conectar con un servidor externo para el almacenamiento de datos en la nuve
- Tener un código sostenible en el tiempo
- Mejor desarrollo de las interfaces para Estudiantes y Admin para que sean más simples aún
- Agragar la opcion para ingresar como profesor
- Sistema de notificaciones aserca de las clases y solicitudes

## Observaciones
- Al ejecuatar el programa la CONTRASEÑA / USUARIO del admin son:
- - - admin / admin
- Lamentamos la entrega tardía de este trabajo y entendemos que es responsabilidad
  nuestra, por eso agradecemos su comprensión.