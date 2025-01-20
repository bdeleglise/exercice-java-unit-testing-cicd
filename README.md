# 🧪 S'initier aux Tests Unitaires en Java avec JUnit

## 👀 Vue d'ensemble 

Ce dépôt propose une série d'exercices pratiques pour vous aider à acquérir les fondamentaux des tests unitaires et du TDD. À travers des exercices pratiques, découpés sous forme de répertoires,  vous découvrirez comment écrire des tests efficaces pour garantir la qualité de votre code.

## 🛠️ Comment Utiliser ce Dépôt

Les différents exercices de ce TP se trouvent tous dans le répertoire `exercices`. Chaque exercice est contenu dans un répertoire numéroté de façon croissante (01, 02, 0n...), suivi du mot `exercice`. Par exemple, le premier exercice se trouve dans le répertoire `./exercices/01_exercice`.

Commencez par installer les dépendances nécessaires :
```bash
mvn clean install -DskipTests
```

Ensuite, naviguez vers le répertoire de l'exercice voulu :

```bash
cd ./exercices/01_exercice
```

Les consignes propres à chaque exercice se trouvent dans le fichier README du répertoire correspondant. Pour chaque exercice, c'est tout ce qui se trouve dans le dossier `src` qui va nous intéresser.

## 🧩 Structure des Exercices
Pour travailler sur un exercice, basculez sur le répertoire correspondant et suivez les instructions du README ou les commentaires dans le code.

### 1. 🌍 Hello World | 01_exercice |
Implémentez des tests unitaires pour une méthode simple.

### 2. 🔤 StringHelper | 02_exercice |
Implémentez des tests unitaires plus complets pour trois méthodes déjà écrites.

### 3. 💰 MoneyBag | 03_exercice |
Dans cet exercice, vous mettrez en pratique la démarche du Test Driven Development dans un exemple plus complexe.

### 4. 👥 Application CRM | 04_exercice
Dans cet exercice, vous travaillerez sur une application de gestion de comptes clients avec un `UserRepository` pour la récupération des utilisateurs et un service fournissant des fonctions find et create. Écrivez des tests unitaires couvrant ces fonctions et leurs cas limites en simulant les appels à UserRepository.

## 📌 Mentions
- Les exercices 1 et 3 ont été empruntés (/adaptés) à Frédérique LAFOREST, professeure au département Informatique.
- Les exercices 2 et 4 ont été conçus par Rahul RAMSAHA, développeur chez Onepoint.



