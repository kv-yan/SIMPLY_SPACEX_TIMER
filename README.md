SimplyIntershipSpaceXProject


# SpaceX Rockets App 🚀
This project is built with modern Android development principles, following **SOLID** design principles, **Clean Architecture**, and utilizing the **MVI (Model-View-Intent)** pattern to ensure maintainability, scalability, and testability.

## Table of Contents
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)

## Features
- 🚀 Fetch a list of SpaceX rockets.
- 📷 View rocket details including images and descriptions.
- ⏱ Countdown for upcoming rocket launches.
- 🔄 Real-time updates using **GraphQL**.
- 🌙 Dark/Light mode support for improved user experience.

This includes your additional feature for Dark/Light mode support!

## Tech Stack
This project leverages the latest Android technologies and best practices:

- **Kotlin** - First-class language for Android development.
- **Coroutines** - For efficient and asynchronous tasks.
- **Retrofit** - REST client for API requests.
- **GraphQL (Apollo)** - For fetching SpaceX rocket data.
- **Jetpack Compose** - Modern UI toolkit for building native Android UIs.
- **Coil** - Image loading library for Compose.
- **Koin** - Dependency injection for better modularity.
- **MVI Architecture** - Ensures unidirectional data flow and predictability.

## Architecture

This project follows **Clean Architecture** and **SOLID** design principles, splitting the codebase into distinct layers:

### Layers:
- **Domain Layer**: Business logic, including use cases and domain models.
- **Data Layer**: Handles data operations, including network (via Retrofit/GraphQL) and local storage (via Room).
- **Presentation Layer**: View models and UI components (Jetpack Compose), adhering to the MVI pattern.

### Flow:
1. **User Interaction** triggers intents in the View.
2. **ViewModel** processes the intents and updates the UI state.
3. **Use Cases** in the domain layer handle business logic.
4. **Repository** manages data sources, deciding whether to fetch from a local database or make network requests.


https://github.com/user-attachments/assets/cc6c96eb-914c-4c7a-8e8a-e10e1f9b5bda



