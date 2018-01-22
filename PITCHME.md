## Projet NOSQL

### Presentation du 23/01/2017
#### Nazha Bouchia, Alexandre Durand, Robin Masliah, François Lecerf

---
## Contexte

---?image=assets/image/contexte.png&size=auto 90%

---
- La demande métier est souvent par nature floue et evolutive

- Le metier veut brancher sa dataviz, croiser les données avec d'autres applications existantes, offrir une interface web
- Tout en ayant une architecture souple, perenne qui minimise les coûts de développement, d'évolution et d'intégration à l'existant

---
## Use Case
---
Présenter une vue macroscopique des événements sur un mois donné

---
## Choix des données
---
TODO: A compléter (events + actor + mentions)

---
## Choix du système de stockage

---
- **MongoDB** est le meilleur compromis fonctionnalité et accès aux données / scalabilité et performance / souplesse d'évolution de l'architecture

---
- Le format des documents nous permet d'être flexible et de rajouter des champs à la demande
  - En aval, en fonction de l'évolution des demandes métier
  - En amont, en fonction des évolutions de la base GDELT (format 1.0 -> 2.0)
 - Cela nous permet aussi de faire évoluer notre architecture en plusieurs temps (ajout de champs sans modification immédiate des applications frontales à notre base)

---
 - Programmation simple généralement adaptée aux développeurs et en particulier à notre équipe Data
 - Beaucoup de connecteurs pour accéder à la base (Python, Spark, REST, BI)

---
## Modélisation des données
---
- GDELT: 3 tables avec des liens relationnels. 
- Transformation en une seule collection "events" de documents contenant l'event et ses mentions

---

```json
{
  "globalEventId":655488900,
  "day":20170514,
  "monthyear":201705,
  "year":2017,
  "fractionDate":2017.3670654296875,
  "isRootEvent":0,
  "eventCode":"1124",
  "eventBaseCode":"112",
  "eventRootCode":"11",
  "quadClass":3,
  "goldsteinScale":-2.0,
  "numMentions":2,
  "numSources":1,
  "numArticles":2,
  "avgTone":-1.7426273822784424,
  "dateadded":20170514090000,
  "sourceurl":"http://english.ahram.org.eg/NewsContent/2/9/268726/World/International/Macron-takes-office-as-French-president.aspx",
  "actor1":{
    "code":"RUS",
    "name":"RUSSIAN",
    "countryCode":"RUS",
    "knownGroupCode":"",
    "ethnicCode":"",
    "geography":{
      "type":1,
      "fullname":"Russia",
      "countryCode":"RS",
      "ADM1Code":"RS",
      "ADM2Code":"",
      "coordinates":[
        60.0,
        100.0
      ]
    }
  },
  "actor2":{
    "code":"GOV",
    "name":"AUTHORITIES",
    "countryCode":"",
    "knownGroupCode":"",
    "typeCode":[
      "GOV"
    ],
    "geography":{
      "type":4,
      "fullname":"Paris, France (general), France",
      "countryCode":"FR",
      "ADM1Code":"FR00",
      "ADM2Code":"16282",
      "coordinates":[
        48.86669921875,
        2.333329916000366
      ]
    }
  },
  "actionGeo":{
    "type":4,
    "fullname":"Paris, France (general), France",
    "countryCode":"FR",
    "ADM1Code":"FR00",
    "ADM2Code":"16282",
    "coordinates":[
      48.86669921875,
      2.333329916000366
    ]
  }
}
```

---
## Architecture

---?image=assets/image/_ARCHI.png&size=auto 68%

---?image=assets/image/_ARCHI_white.png&size=auto 68%

---
**- Import des données: MongoSpark**  
Processing et transformation des fichiers GDELT par traitement Spark/Scala  
Sauvegarde des données directement dans notre cluster MongoDB

**- Stockage: MongoDB**  
Un ReplicaSet de 3 machines

**- Visualisation**  
API REST + Notebook Python
---

### Merci de votre attention

### Des questions?



