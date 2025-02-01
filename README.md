# Rick & Morty Android Compose App

A sample Android app built with `Kotlin` & `Jetpack Compose`, showcasing clean architecture principles and modern Android development best practices.  
This app provides a delightful experience for fans of the Rick & Morty universe. 🤓

## Features 💡

*   **Dashboard:** Explore a comprehensive list of Rick & Morty characters.
*   **Character Details:** Dive deep into character information, including full name, status, species, gender, origin, current location, and a list of episodes they've appeared in.
*   **Episode Details:** Learn more about each episode, including its name, season, air date, and the featured characters.
*   **Location Details:** Discover details about various locations in the Rick & Morty universe, such as name, type, dimension, and a list of residents.

## App Architecture 🛠️

This app follows a clean architecture approach, promoting maintainability, testability, and scalability.  The core components are organized into distinct layers:

<p align="center" width="100%">
  <img src="https://github.com/skaradimitriou/elmepa-uni-app/assets/64270931/e1e2cb57-4ff2-4c60-9f59-b581b7b8a529" alt="mvvm_clean_architecture" width="30%" height="20%" />
</p>

*   **Presentation Layer (UI):** Built with Jetpack Compose, responsible for displaying data and handling user interactions.  Utilizes the MVVM (Model-View-ViewModel) pattern.
*   **Domain Layer (Use Cases):** Contains the business logic of the application, independent of any specific implementation details.  Use cases orchestrate data flow between the data and presentation layers.
*   **Data Layer (Repositories):** Provides an abstraction for data access, retrieving data from various sources (e.g., network, database).  Implements the Repository pattern.
*   **Data Source Layer:** Handles the specifics of data retrieval, including network requests (using Ktor) and local database operations (using Room).

## Tech Stack ⚙️

This project leverages the following technologies:

*   **Languages:** [Kotlin](https://kotlinlang.org/)
*   **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) -  A modern toolkit for building native Android UI.
*   **Architecture:** [MVVM (Model-View-ViewModel)](https://developer.android.com/topic/libraries/architecture/viewmodel-savedstate) -  A robust architecture pattern for separating concerns.
*   **Asynchronous Operations:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Kotlin Flows](https://kotlinlang.org/docs/flow.html) - For handling background tasks and reactive streams.
*   **Networking:** [Ktor](https://ktor.io/) - A lightweight and multiplatform HTTP client.
*   **Dependency Injection:** [Koin](https://insert-koin.io/) - A pragmatic dependency injection framework.
*   **Image Loading:** [Coil](https://coil-kt.github.io/coil/) - An image loading library backed by Kotlin Coroutines.
*   **Local Persistence:** [Room](https://developer.android.com/training/data-storage/room) - A persistence library providing an abstraction over SQLite.
*   **Data Serialization:** [Gson](https://github.com/google/gson) - A Java library that can be used to convert Java Objects into their JSON representation and vice-versa.
*   **Build:** [Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) -  Using Kotlin for Gradle build scripts.
*   **Custom Gradle Plugins:**  Enhancing the build process with custom plugins. (See details below)
*   **Testing:** [Mockk](https://mockk.io/) -  A mocking library for Kotlin.

## Contributing 

Contributions are welcome! You may suggest a new feature, point out a bug or even open a PR 🚀
