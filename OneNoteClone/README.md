# OneNoteClone
OneNote clone with literally only one note.

Diese Applikation entstand im Rahmen einer Projektarbeit für das Modul 223. Es wurde dabei eine Client-Server-Applikation geplant und dann mit Spring Boot als Rest-Api und React.js als Frontend entwickelt. Diese OneNote clone kann einerseits User nach CRUD verwalten und andererseits ist es möglich mit einem Userkonto eine einzige Notiz zu verwalten. Der Fokus lag dabei nicht beim Frontend und React.js, sondern beim Backend und der sauberen Umsetzung eines Rest-Api's.

### Setup
1. React starten -> ``` /onenote_frontend/ yarn install && yarn start ```
2. Spring starten -> ``` /onenote/ ``` build gradle öffnen
 => Sobald die beiden Teile gebuildet wurde können diese gestartet werden.
3. DB nach den Vorgaben von ```/onenote/src/main/resources/application.properties ``` aufsetzen (MYSQL oder MariaDB)

***[React: HTTP:3000, SPRING: HTTP:8080, DB: PORT:3306]***

## Applikation
![HOME](https://github.com/LewinGerber/OneNoteClone/blob/main/res/home_view.png)
![NOTE](https://github.com/LewinGerber/OneNoteClone/blob/main/res/drawing_view.png)
![EDIT](https://github.com/LewinGerber/OneNoteClone/blob/main/res/manage_view.png)
