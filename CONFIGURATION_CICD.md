# Configuration du pipeline CI/CD

1. Installer [Jenkins](jenkins.io/download/)
2. Suivre ce [tutorial](https://www.youtube.com/watch?v=Gy4Nk2pIuNs) en cas de problème d'installation
3. Configurer Maven 'M3' dans Tableau de bord > Administrer Jenkins > Configuration globale des outils
   ![image](https://user-images.githubusercontent.com/98686454/191256876-3814776b-cfbb-4218-82a4-8ac569754b0f.png)
4. Créer le pipeline
    1. Nouvelle item
    2. Nommer le pipeline
    3. Cliquer sur pipeline, puis "OK"
    ![image](https://user-images.githubusercontent.com/98686454/191258686-155463c9-5933-46c7-afab-59d81616fed7.png)
5. Configurer le pipeline
    1. Dans Configuration générale descendre tout en bas dans la rubrique Pipeline > Definition
    2. Selectionner la définition "pipeline from SCM"
    3. Avec un SCM "GIT"
    4. Coller URL de ce repo Git dans la section concerné
    ![image](https://user-images.githubusercontent.com/98686454/191260500-11303599-eed2-46dd-a540-4cc4e5e57afb.png)
    5. Veillez à ce que la branche spécifiée soit "main"
    ![image](https://user-images.githubusercontent.com/98686454/191262183-a19c3352-4f55-4731-bd07-27c9d38e1eaf.png)
    6. Veillez également à ce que le nom du fichier sur ce lequel doit se baser Jenkins pour lancement soit bien nommé "Jenkinsfile"
    ![image](https://user-images.githubusercontent.com/98686454/191261621-d8e0f2f8-d248-42e2-890c-af7e1a2be5c7.png)
    7. "Sauver" la configuration du pipeline
6. Lancer le pipeline en cliquer sur "Lancer build"
7. Une fois le build terminé, il est possible d'accèder au résultat de test en cliquant sur le numéro de build qui s'affiche en bas à gauche, puis en selectionnant les résultats des tests
    
    ![image](https://user-images.githubusercontent.com/98686454/191263291-19991a63-b31a-4a52-9b56-eee85179c211.png)
 
