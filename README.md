# Projet Bigdata : Analyser l’année 2017 via la base de données GDELT

L’objectif du projet est de concevoir un système de stockage distribué performant sur AWS qui permet d’analyser les événements de l’année 2017 à travers leur recit dans les médias du monde collectés par GDELT.

Pour plus d'information: http://andreiarion.github.io/Projet2017.pdf.

## Pitch

Notre présentation, basée sur GitPich, est contenue dans le fichier [PITCHME.md](PITCHME.md), et les paramètres du template dans le fichier [PITCHME.yaml](PITCHME.yaml).

> ### Pour accéder à la présentation : https://gitpitch.com/bambi2511/INF728_project

## Chargement des données

Le chargement des données GDELT a été testé de plusieurs façons.

Les fichiers correspondant sont dans le répertoire src/main/python et scr/main/scala:

```
.
├── src
│   └── main
|       └── scala
|           └── load-events.scala
|       └── python
|           └── update-events.py
```

## Mise en place du cluster Mongo

La mise en place du cluster Mongo a été automatisé au moyen de script.

Les fichiers correspondant sont dans le répertoire src/main/scripts/:


```
.
├── src
│   └── main
|       └── scripts
|           └── script_install_mongo.sh
```

## Accès aux données et visualisation

Nous avons mis en place au moyen du framework Bottle une API REST et utilisé un Jupyter Notebook pour présenter un cas d'utilisation basé sur nos données.

Les fichiers correspondant sont dans le répertoire src/main/viz:

```
.
├── src
│   └── main
|       └── viz
|           └── gdelt_apirest.py
|           └── dashboard.ipynb
```



