# SMA-Jeu-de-la-vie
Simulation de Systèmes Multi-Agents grâce aux automates cellulaires.

---

![Appercu de l'application](./Jeu_de_la_vie.jpg "Appercu de l'application")

---

# Contexte du projet

Ce projet Java vise à créer une application graphique pour simuler des systèmes multi-agents, en mettant en lumière trois automates cellulaires (Jeu de la Vie de Conway, jeu de l'immigration, modèle de ségrégation de Schelling) et le modèle de Boids pour le mouvement d'essaims. Il offre une opportunité d'explorer les principes de la programmation orientée objet, intégrant des concepts tels que l'encapsulation, la délégation, l'héritage, l'abstraction, et l'utilisation des collections Java. La conception orientée objet, axée sur la flexibilité des classes, favorise la généralisation du code entre les différents systèmes multi-agents étudiés, établissant ainsi une plateforme expérimentale pour comprendre les dynamiques émergentes et le comportement collectif des entités autonomes au sein d'un système multi-agents.

# Contenu du projet

Les ressources distribuées contiennent:

- le sujet
- une librairie d'affichage graphique d'un simulateur (lib/gui.jar) et sa documentation (doc/index.html)
- un fichier de démonstration du simulateur (src/TestInvader.java)

Une mini-introdution à la notation UML des diagrammes de classes est disponibe pour faciliter la lecture du sujet ([Fiche 7](https://programmation-orientee-objet.pages.ensimag.fr/poo/resources/fiches/07-UML/)).


## Compilation & exécution
### Avec un makefile?
Un fichier Makefile est distribué pour facilement compiler et exécuter le fichier TestInvader.java

Mais vu la taille de ce projet, il est ***très fortement recommandé d'utiliser un IDE*** pour compiler, exécuter et déboguer votre code!

### IDE Idea Intellij
- créer un nouveau projet:
    - menu *File/New Project*
    - si le répertoire distribué est dans "$HOME/Ensimag/2A/POO/TPL_2A_POO", alors paramétrer les champs `Name` avec "TPL_2A_POO" et `Location` avec "$HOME/Ensimag/2A/POO/"
- configurer l'utilisation de la librairie
    - menu *File/Project Structure* puis *Projet setting/Modules*
    - clicker sur(*Add* puis "JARs & Directories" et sélectionner ~/Ensimag/2A/POO/TPL_2A_POO/lib
    - voir ici pour plus d'aide: https://stackoverflow.com/questions/1051640/correct-way-to-add-external-jars-lib-jar-to-an-intellij-idea-project
- vous pouvez bien sûr utiliser git via l'interface d'idea Intellij

### IDE VS Code
* dans "~/Ensimag/2A/POO/TPL_2A_POO", lancer *code ."

* si vous avez installé les bonnes extensions java (exécution, debogage...) il est possible que tout fonctionne sans rien faire de spécial.

* s'il ne trouve pas la librairie, vous devez alors créer un vrai "projet" et configurer l'import du .jar.

* pas vraiment d'aide pour ça, vous trouverez

* vous pouvez bien sûr utiliser git via l'interface de VS code
