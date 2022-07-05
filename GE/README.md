# gestionEcole


# Sommaire

1. [Prérequis](#prérequis)
2. [Lancer l'application](#app)
4. [Tester notre application](#test)


# prérequis

- jdk 11 & jre 11 ou supérieur
- maven version 3.8.4 ou supérieur

# Lancer l'application <a href="app"></a>

exécuter la commande `mvn clean install spring-boot:run` dans un terminal à la racine du projet.

# Tester notre application <a href="test"></a>

Accéder l'application à l'adresse : http://localhost:8080/

`/etudiants` --> lister tous les étudiants de la base (redirection vers la page de login si vous n'êtes pas connecté).
`/creationEtudiant` --> Créer un étudiant et l'enregistrer dans la base de donnée.
 `/login` --> page de login pour se connecter.
