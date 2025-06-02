# 📚 API de gestion de bibliothèque avec Spring Boot

Ce projet est une application REST complète développée avec **Spring Boot**, permettant de gérer une bibliothèque en ligne.  
Il implémente un système d'authentification sécurisé, la gestion des rôles, et des opérations métier comme l'emprunt et le retour de livres.

## 🚀 Fonctionnalités principales

### 🔐 Authentification et rôles (Spring Security)
- Authentification via **email + mot de passe** (BCrypt)
- Rôles définis en base : `ROLE_READER` (lecteur), `ROLE_LIBRARIAN` (bibliothécaire)
- Contrôle des accès aux endpoints via `@PreAuthorize`

### 📚 Gestion des livres
- `GET /books` : liste des livres disponibles (utilisateurs connectés)
- `POST /books` : ajouter un livre (**réservé au bibliothécaire**)
- `PUT /books/{id}` : supprimer un livre (**réservé au bibliothécaire**)

### 🔁 Emprunts de livres
- `POST /loans` : emprunter un livre (**lecteur uniquement**)
- `PUT /loans/{id}/return` : rendre un livre (**lecteur uniquement**)
- `GET /loans/mine` : consulter ses emprunts en cours (**lecteur**)
- `GET /loans` : consulter tous les emprunts (**bibliothécaire**)

## 🧱 Architecture

- **Spring Boot 3**
- **Spring Data JPA** avec H2 en mémoire
- **Spring Security** (authentification + autorisation)
- **Validation JSR-380** (`@Valid`, `@NotBlank`, etc.)
- **DTOs** pour structurer les données entrantes et sortantes
- **Service layer** pour gérer la logique métier
- **Contrôleurs REST** exposant les fonctionnalités
- **Réponses JSON structurées** avec `ApiResponse<T>`

## 🔧 Configuration de sécurité

- Authentification HTTP Basic
- Chargement dynamique des utilisateurs depuis la base
- Hachage des mots de passe avec BCrypt
- Filtrage des endpoints par rôle avec `@PreAuthorize`

## 📁 Structure du projet
src/
├── controller/ # Contrôleurs REST (auth, livres, emprunts)
├── dto/ # Objets de transfert de données (DTOs)
├── entity/ # Entités JPA (User, Role, Book, Loan)
├── repository/ # Interfaces JPA pour accès base
├── service/ # Logique métier (AuthService, LoanService...)
├── security/ # Configuration de Spring Security
└── resources/
├── application.properties
└── data.sql # Données de démarrage

## ✅ À venir (pistes d'évolution)

- Passage à JWT pour une authentification stateless
- Ajout de Swagger/OpenAPI pour documenter l'API
- Ajout de tests unitaires et d'intégration
- Fonctionnalité de réinitialisation de mot de passe
- Support multirôle (un utilisateur avec plusieurs rôles)

---

## 🤝 Contribuer

Ce projet est un exercice personnel pour approfondir Spring Boot et Spring Security.  
N'hésitez pas à proposer des améliorations ou à vous en inspirer pour vos propres projets !
