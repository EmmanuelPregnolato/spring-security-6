# spring-security-6

# Spring Security 6 & JWT - Tutoriel

Projet d'étude basé sur le cours complet de *Daily Code Buffer* pour maîtriser les concepts de sécurité moderne avec Spring Boot 3/4.

📺 **Lien du tutoriel Suivi :** [Spring Security 6 with Spring Boot 3 and JWT Tutorial](https://www.youtube.com/watch?v=oQV2WGin0mc)
## 🛠️ Stack Technique
* **Java:** 25 (OpenJDK Corretto)
* **Framework:** Spring Boot 3
* **Sécurité:** Spring Security 6 (Stateless / JWT)
* **Gestionnaire de build:** Maven

## 🎯 Objectifs d'apprentissage
* Comprendre le cycle de vie de la chaîne de filtres (`SecurityFilterChain`).
* Implémenter une authentification Stateless via des jetons **JWT** (JSON Web Tokens).
* Configurer la gestion des rôles et des autorisations sur les endpoints REST.
* Préparer l'intégration de l'architecture de sécurité avec une API Gateway.

## 🚀 Démarrage
```bash
# Pour compiler le projet
mvn clean compile
```

## Basic Auth l'application
* **username:** user
* **password:** In the terminal on start up
* **HEADER X-CSRF-TOKEN:** localhost:8080/csrf