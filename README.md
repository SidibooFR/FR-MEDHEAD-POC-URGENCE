# FR-MEDHEAD-POC-URGENCE
Projet 12 : Openclassrooms : "Faites adhérer les parties prenantes avec un POC" : Mise en place d'une API traitant les urgences hospitalières

### Projet : Consortium MedHead
### Client : Preuve de concept (l'allocation de lits d'hôpital pour les urgences)

# 1. Repertoire de code
Ce repertoire fournit :
* Trois modules clonable sur un IDE au choix. Les trois modules peuvent être build et run au travers de l'IDE pour les tests Postman.
* La configuration d'une configuration de pipeline CI/CD Jenkins
* Une description du pipeline CI/CD associé et des pointeurs vers toute configuration. -> CONFIGURATION_CICD
* Tests de projet auto-décrivant un plan de test automatisé. -> Dans Jenkins 
* Scénarios BDD définissant les tests d'acceptation à livrer -> Postman
# 2. Contenu
## 2.1. Modules
Les modules sont instanciés à l'aide de JAVA Spring.
* **Api gateway** : outil de gestion des interfaces de programmation d'application (API) qui se positionne entre un client et une collection de services back-end.
* **Urgence** : Module au coeur de ce projet qui permet de compléter des urgences envoyées en entré avec l'hopital de destination
* **Hopital** : Module connexe à l'urgence permettant de fournir un hopital le plus proche selon une pathologie et les coordonnées d'origine (de l'urgence). 
## 2.2. Jenkinsfile
Permet la construction d'un pipeline CI/CD afin d'exécuter le build et les tests des modules Urgence et Hopital.
## 2.3. Postmanfile
Fichier à **importer** dans [Postman](https://www.postman.com/downloads/) pour réaliser les tests E2E.
Nom du fichier : BDD POC URGENCE.postman_collection.json
