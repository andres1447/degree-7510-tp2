# 75.10 - Tecnicas de Diseño, TP2


Este repositorio contiene el trabajo práctico 2 de la materia (75.10) Técnicas
de Diseño en la FIUBA.

## Introducción

El trabajo práctico consiste en crear una aplicación de línea de comandos para
administrar los productos y ofertas en cajas de una línea de supermercados,
focalizandose en el diseño del modelo de dominio.

## Estructura del repositorio

El repositorio está estructurado siguiendo las convenciones de maven y gradle:

* **src**: Código fuente y todo lo necesario para construir la aplicación, como
  recursos gráficos, icónos y librerías locales.

    * **main**: Contiene el código fuente y recursos del programa en si.

    * **test**: Contiene el código fuente y recursos de la suite de tests
      automatizados. Cabe destacar el subdirectorio `resources/samples`, que
contiene varios archivos de proyectos mda de ejemplo listos para importar en la
aplicación

    * **docs**: Documentación. Incluye el código fuente del informe presentado,
      el enunciado original y los diagramas del diseño.

## Ambiente de desarrollo

### Prerequisitos

La aplicación está desarrollada con java 7, utilizando
[gradle](http://www.gradle.org/) como build system, por lo que será necesario
instalar ambos para poder compilar la aplicación:

1. Java JDK 7: Dependiendo del sistema operativo en el que se vaya a
   desarrollar. Se ha desarrollado tanto en windows con Oracle JDK 7 como en
Ubuntu Linux con OpenJdk 7.

2. Gradle: En [la documentación oficial de
   gradle](http://www.gradle.org/docs/current/userguide/userguide_single.html#installation)
hay una sección sobre su instalación.

Para generar la documentación, es necesario contar con `pdflatex` y `texlive`.
Desde cualquier sistema operativo basado en Debian, se pueden instalar todas
las dependencias usando el siguiente comando:

```
sudo apt-get install -y pdflatex texlive texlive-lang-all aspell aspell-es
```

### Gradle

Gradle es un build system basado en [Ant](http://ant.apache.org/),
[Maven](http://maven.apache.org/) e [Ivy](http://ant.apache.org/ivy/) que
automatiza el proceso de build, testing y publicación y deploy de paquetes de
software. Es una herramienta de linea de comandos que se encarga de resolver
las dependencias del proyecto, compilarlo, ejecutar los tests y empaquetar el
software.

Desde la linea de comandos, ubicado en el directorio base del proyecto, se
ejecuta `gradle [TASK]` para realizar cada una de estas actividades, donde TASK
es una tarea particular que define qué es lo que se quiere hacer, sea compilar,
ejecutar tests o empaquetar y lanzar la aplicación.

Algunos ejemplos de comandos útiles:

* `gradle test`: Descarga y resuelve las dependencias de la aplicación,
  compila el código fuente, compila los tests y los ejecuta.

* `gradle run`: Idem a `gradle test`, pero además si los tests se ejecutan
  correctamente ejecuta la aplicación en si misma.

* `gradle distZip`: Idem a `gradle test`, pero además si los tests se ejecutan
  correctamente construye un zip de instalación para poder distribuir la
aplicación.

* `gradle clean`: Elimina todos los archivos generados durante el build.

* `gradle tasks`: Lista toda las tareas disponibles.

* `gradle eclipse`: Genera todos los archivos necesarios para importar el
  proyecto en eclipse.

* `gradle docs`: Compila el informe del trabajo práctico, generando el pdf
  final en `build/docs`.

Se recomienda leer la documentación de
[gradle](http://www.gradle.org/docs/current/userguide/userguide_single.html)
para más información.
