# Ejercicio 1 - 23/03/2022

Crear un proyecto java maven con Kafka clients y Kafka streams.

Crear datos demo en un topic con una clase Producer.

En otra clase separada utilizar Kafka Streams para aplicar transformaciones sobre los datos del topic original enviarlos a otro topic, por ejemplo leer las longitudes de cadenas de texto, filtrar datos, aplicar una operación map, etc.

El alumno elegirá los datos para el topic de entrada y de salida.

Utilizar tipos básicos como por ejemplo String Double Long.

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
