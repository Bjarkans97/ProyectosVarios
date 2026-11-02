# Proyectos Java

Colección de aplicaciones que construí mientras aprendía el ecosistema Java, desde consola hasta web y escritorio, casi todas con **MySQL** como base de datos.

## Proyectos

| Proyecto | Descripción | Tecnologías |
| :-- | :-- | :-- |
| [ZonaFit (consola)](Java/Only%20Java/ZonaFit) | Gestión de clientes de un gimnasio | Java, JDBC, MySQL |
| [ZonaFit Spring](Java/Spring%20boot/ZonaFitSpring) | Misma app migrada a Spring Boot y JPA | Spring Boot, JPA, MySQL |
| [ZonaFit Web](Java/Spring%20-%20JSF%20PrimeFaces/ZonaFitSpring) | Versión web de ZonaFit | Spring Boot, JSF, PrimeFaces |
| [ZonaFit Escritorio](Java/Swing/ZonaFitSpring-Swing) | Versión de escritorio de ZonaFit | Spring Boot, Swing |
| [Login Swing](Java/Swing/ProyectoSwing) | Primeros pasos con formularios de escritorio | Swing |
| [Tareas](Java/Varios/tareas) | Sistema básico de tareas | Spring Boot, JavaFX |
| [Empleados](Java/Varios/empleados) | CRUD de empleados con vistas JSP | Spring Boot, JSP |
| [Contactos](Java/Varios/contactos) | CRUD de contactos | Spring Boot, Thymeleaf |
| [Inventarios (API)](Java/Varios/inventarios) | API REST de productos | Spring Boot, JPA |
| [Inventario App](Java/Angular/inventarios-new-app) | Frontend del sistema de inventarios | Angular |
| [Máquina de snacks](Java/Only%20Java/Maquina%20de%20snacks) | Simulador de máquina expendedora en consola | Java |
| [Sistema de ventas](Java/Only%20Java/proyectoSistemaVentas) | Ventas básicas con POO | Java |

## Requisitos generales

- JDK 21 o superior (algunos proyectos usan Java 25)
- Maven
- MySQL 8 corriendo en `localhost:3306`

## Configuración de la base de datos

Las credenciales no están en el código. Cada proyecto las lee de variables de entorno:

| Variable | Descripción | Valor por defecto |
| :-- | :-- | :-- |
| `DB_USERNAME` | Usuario de MySQL | `root` |
| `DB_PASSWORD` | Contraseña de MySQL | (ninguno, es obligatoria) |

Ejemplo en Linux o macOS:

```bash
export DB_PASSWORD=tu_contraseña
cd "Java/Varios/contactos"
mvn spring-boot:run
```

En Windows (PowerShell):

```powershell
$env:DB_PASSWORD = "tu_contraseña"
cd "Java/Varios/contactos"
mvn spring-boot:run
```

En IntelliJ también puedes definirlas en *Run → Edit Configurations → Environment variables*.

Los proyectos con `createDatabaseIfNotExist=true` crean su base automáticamente. Los de ZonaFit necesitan que exista la base `zonafit`.
