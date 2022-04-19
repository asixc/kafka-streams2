# Ejercicio 1 - 23/03/2022

# Ejercicio 1

Siguiendo el siguiendo esquema:

1. Borrar topics (si es ya existen)
2. Generar datos demo (Producer)
3. Kafka Stream (con cualquiera de las siguientes opciones)
4. Comprobar datos (Consumer)

Crear un proyecto java maven (sin spring) donde se puedan probar los 4 pasos mencionados desde un main. Para simplificar el proceso nos podemos apoyar en clases de utilidad como hemos hecho en clase.

Ejemplo clases

* utils
    * Deleter
    * Producer
    * Processor
    * Consumer
* Main.java: invoca los métodos creados en las otras clases.

El alumno elige los datos y el procesamiento que quiere hacer sobre los mismos, por ejemplo: filter, mapValues, count.

## Proceso:
### Añadir dependencias:
```shell
<dependencies>
        <!-- https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients -->
        <dependency>
            <groupId>org.apache.kafka</groupId>
            <artifactId>kafka-clients</artifactId>
            <version>3.1.0</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-api -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>2.0.0-alpha7</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-log4j12 -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>2.0.0-alpha7</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.apache.kafka/kafka-streams -->
        <dependency>
            <groupId>org.apache.kafka</groupId>
            <artifactId>kafka-streams</artifactId>
            <version>3.1.0</version>
        </dependency>
    </dependencies>
```

### Añadir el fichero: `log4j.properties` con el contenido:
```shell
log4j.rootCategory=INFO, stdout
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{dd/MM/yyyy HH:mm:ss:SSS} %5p %t %c{2}:%L - %m%n
```
