## Projet NOSQL

### Presentation du 23/01/2017
#### Nazha Bouchia, Alexandre Durand, Robin Masliah, François Lecerf

---
## Etude de cas
---
- La demande métier est par nature floue et evolutive
---?image=assets/image/business_analyst.jpg&size=auto 90%
---
- La demande métier est par nature floue et evolutive
- Le metier veut brancher sa dataviz
---
- La demande métier est par nature floue et evolutive
- Le metier veut brancher sa datawiz
- Et se connecter avec d autres applications
---
- La demande métier est par nature floue et evolutive
- Le metier veut brancher sa datawiz
- Et se connecter avec d autres applications
- Tout en conservant une application réactive
---
## Architecture
---
- MongoDB est le meilleur compromis souplesse / scalabilité
---
- Le format JSON permet de rajouter des champs à la demande
 - En fonction des demandes métier
 - En fonction des évolutions de la base Gdelt (format 1.0 -> 2.0)
---
- La technologie facilite la livraison
 - Technologie web créée par des développeurs, pour des développeurs
 - Beaucoup de connecteurs pour attaquer la base (Python, BI, Spark, REST)
---
## Modélisation des données
---
- GDelt: 3 tables avec des liens relationnels. On lie les tables, on les applatit, et on ne conserve que les champs qui nous intéressent
---
### Import: Spark
---
- Le format de stockage dans S3 (zip) ne nous permet pas de traiter directement la donnée
- Nous avons donc directement récupéré les données de la base GDelt vers une machine S3
- Ceux ci sont ensuite processés par un traitement Scala qui integre les fichiers dans une base de données dans un cluster MongoDB
---
### Storage: MongoDB
---
- Cluster de 3 machines S3
---
### Requêtes
---
- A faire
---
## Visualisation

---

## Tips!

<br>

@fa[arrows gp-tip](Press F to go Fullscreen)

@fa[microphone gp-tip](Press S for Speaker Notes)

---

## Template Features

- Code Presenting |
- Repo Source, Static Blocks, GIST |
- Custom CSS Styling |
- Slideshow Background Image |
- Slide-specific Background Images |
- Custom Logo, TOC, and Footnotes |

---?code=src/go/server.go&lang=golang&title=Golang File

@[1,3-6](Present code found within any repo source file.)
@[8-18](Without ever leaving your slideshow.)
@[19-28](Using GitPitch code-presenting with (optional) annotations.)

---

@title[JavaScript Block]

<p><span class="slide-title">JavaScript Block</span></p>

```javascript
// Include http module.
var http = require("http");

// Create the server. Function passed as parameter
// is called on every request made.
http.createServer(function (request, response) {
  // Attach listener on end event.  This event is
  // called when client sent, awaiting response.
  request.on("end", function () {
    // Write headers to the response.
    // HTTP 200 status, Content-Type text/plain.
    response.writeHead(200, {
      'Content-Type': 'text/plain'
    });
    // Send data and end response.
    response.end('Hello HTTP!');
  });

// Listen on the 8080 port.
}).listen(8080);
```

@[1,2](You can present code inlined within your slide markdown too.)
@[9-17](Displayed using code-syntax highlighting just like your IDE.)
@[19-20](Again, all of this without ever leaving your slideshow.)

---?gist=onetapbeyond/494e0fecaf0d6a2aa2acadfb8eb9d6e8&lang=scala&title=Scala GIST

@[23](You can even present code found within any GitHub GIST.)
@[41-53](GIST source code is beautifully rendered on any slide.)
@[57-62](And code-presenting works seamlessly for GIST too, both online and offline.)

---

## Template Help

- [Code Presenting](https://github.com/gitpitch/gitpitch/wiki/Code-Presenting)
  + [Repo Source](https://github.com/gitpitch/gitpitch/wiki/Code-Delimiter-Slides), [Static Blocks](https://github.com/gitpitch/gitpitch/wiki/Code-Slides), [GIST](https://github.com/gitpitch/gitpitch/wiki/GIST-Slides) 
- [Custom CSS Styling](https://github.com/gitpitch/gitpitch/wiki/Slideshow-Custom-CSS)
- [Slideshow Background Image](https://github.com/gitpitch/gitpitch/wiki/Background-Setting)
- [Slide-specific Background Images](https://github.com/gitpitch/gitpitch/wiki/Image-Slides#background)
- [Custom Logo](https://github.com/gitpitch/gitpitch/wiki/Logo-Setting) [TOC](https://github.com/gitpitch/gitpitch/wiki/Table-of-Contents) [Footnotes](https://github.com/gitpitch/gitpitch/wiki/Footnote-Setting)

---

### Template Versions

- #### [Base Template  @fa[external-link gp-download]](https://gitpitch.com/gitpitch/templates/black)
- #### [Code Maximized @fa[external-link gp-download]](https://gitpitch.com/gitpitch/templates/black?p=codemax)
- #### [Speaker Notes @fa[external-link gp-download]](https://gitpitch.com/gitpitch/templates/black?p=speaker)

---

### Questions?

<br>

@fa[twitter gp-contact](@gitpitch)

@fa[github gp-contact](gitpitch)

@fa[medium gp-contact](@gitpitch)

---?image=assets/image/gitpitch-audience.jpg&opacity=100

@title[Download this Template!]

### Get your presentation started!
### [Download this template @fa[external-link gp-download]](https://gitpitch.com/template/download/black)

