# Vodafone Assignment App

A modern Android application built using **Kotlin**, **Jetpack Compose**, and **Clean Architecture (MVVM + MVI)**.  
The app demonstrates secure user authentication, session management, encrypted local storage, and navigation using Jetpack Compose.

---

## 🚀 Features

- **User Authentication**
  - Secure login with remote API integration.
  - Automatic session check using saved tokens.
  
- **Secure Storage**
  - Encrypted storage for authentication tokens using `EncryptedSharedPreferences`.
  - Automatic token clearance on logout.

- **Modern UI**
  - Built with **Jetpack Compose** for a clean and reactive UI.
  - Material 3 design components for consistent styling.

- **Navigation**
  - Jetpack Compose Navigation for screen transitions.
  - `NavHost`-based architecture for single-activity navigation.

- **MVI Architecture**
  - `Intent` → `State` → `Effect` pattern.
  - Unidirectional data flow for predictable UI updates.
  - Kotlin Coroutines and `StateFlow` for reactive state management.

---

## 🧩 Architecture Overview
data/
├─ network/ # (Retrofit for API calls)
├─ storage/ # Secure token storage using EncryptedSharedPreferences
└─ repository/ # Implementation of domain repositories

domain/
├─ model/ # Core data models (User, Auth data, etc.)
├─ repository/ # Repository interfaces
└─ usecase/ # Business logic (Login, Logout, GetCurrentUser)

presentation/
├─ mvi/ # ViewModel, State, Intent, and Effect classes
├─ screens/ # Compose UI screens (LoginScreen, HomeScreen)
└─ navigation/ # AppNavHost and route definitions

**Navigation Flow**

Login Screen -> Successful Login ->  Token Saved in Secure -> Home Screen -> Logout -> Clear Token & User Data -> Login Screen
