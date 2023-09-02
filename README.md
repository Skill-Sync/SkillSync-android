
# Project Structure
We've decided to use the modularity approach for the Android project. In terms of the Android build system (Gradle) <br>
The modularity helps the building time to reduce significantly because of the parallel compilation of modules. <br>

### Modules
Structure is divided into two main base modules
- :core
- :feature

**Core Module**<br>
  Core modules represent the business logic of the application, they contain the data repositories, the network manager, the database, and a lot more. <br>
Also, they can represent the Android specific-logic but not intended for a specific _feature_ like _:core:notifications_ and _:core:navigation_

**Feature Module**<br>
  Feature modules represent the implementation of the User Interface. Each module represents a screen in the application like _:feature:home_ or _:feature:onboarding_. <br>
These modules must not depend on each other but depend on the core modules.



![Android Project Architecture Diagram(2)](https://github.com/Skill-Sync/SkillSync-android/assets/84887514/74780347-4503-4078-8e62-b96f6552891a)


# Libraries used

- Dagger Hilt for dependency injection
- Jetpack Compose for UI
- Compose Destinations for navigation
- Retrofit for making Network calls (Rest API)
- Room for local database (SQLite)
- Coil for image loading
- Timber for logging
- Detekt for Static code analysis
- Ktlint for Static code analysis

# SDK used
- Firebase
  - Crashlytics
  - FCM (Push notification)
  
