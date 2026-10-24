<div align="center">

# RBJ Khata

### A simple digital khata for keeping customer records and tracking dues.

An Android app concept for replacing scattered paper ledger notes with a clear, searchable home for customer details and account activity.

**Native Android · Kotlin · Jetpack Compose · Material 3 · Room**

[Overview](#what-is-rbj-khata) · [Screenshots](#screenshots) · [Features](#what-it-does) · [Tech stack](#tech-stack) · [Run locally](#run-locally)

</div>

---

## What is RBJ Khata?

RBJ Khata is a mobile-first digital ledger project. It is designed to help a small shop or lending business keep customer contact details together with balances, interest dues, and payment history, so everyday account-keeping is easier to review than a paper khata.

The repository contains a native Android application built with Kotlin and Jetpack Compose. Its current code includes a customer list and add-customer flow backed by a local Room database. The supplied screenshots show the broader product experience, including dashboard, loan, transaction, and due views. Those screens are presented as product captures; they should not be read as proof that every pictured workflow is already implemented in this code snapshot.

## Screenshots

Explore the RBJ Khata experience across the dashboard, customer list, workspace menu, active loans, transactions, and due-today view.

<table>
  <tr>
    <td align="center"><img src="docs/screenshots/WhatsApp%20Image%202026-10-24%20at%2011.17.33%20AM.jpeg" alt="RBJ Khata dashboard showing workspace and account summaries" width="220" /></td>
    <td align="center"><img src="docs/screenshots/WhatsApp%20Image%202026-10-24%20at%2011.17.33%20AM%20(1).jpeg" alt="RBJ Khata customer list with search and balance summaries" width="220" /></td>
    <td align="center"><img src="docs/screenshots/WhatsApp%20Image%202026-10-24%20at%2011.17.34%20AM%20(1).jpeg" alt="RBJ Khata More screen" width="220" /></td>
  </tr>
  <tr>
    <td align="center"><strong>Dashboard</strong></td>
    <td align="center"><strong>Customers</strong></td>
    <td align="center"><strong>Workspace menu</strong></td>
  </tr>
  <tr>
    <td align="center"><img src="docs/screenshots/WhatsApp%20Image%202026-10-24%20at%2011.17.34%20AM%20(3).jpeg" alt="RBJ Khata active loans summary" width="220" /></td>
    <td align="center"><img src="docs/screenshots/WhatsApp%20Image%202026-10-24%20at%2011.17.34%20AM.jpeg" alt="RBJ Khata transactions list with loan and payment totals" width="220" /></td>
    <td align="center"><img src="docs/screenshots/WhatsApp%20Image%202026-10-24%20at%2011.17.34%20AM%20(2).jpeg" alt="RBJ Khata due-today view" width="220" /></td>
  </tr>
  <tr>
    <td align="center"><strong>Active loans</strong></td>
    <td align="center"><strong>Transactions</strong></td>
    <td align="center"><strong>Due today</strong></td>
  </tr>
</table>

## What it does

### Present in the codebase

- Add a customer with a name, mobile number, and village.
- View customer records in a scrollable list.
- Show each customer’s recorded due and interest totals.
- Persist customer data locally with Room.
- Navigate between the dashboard, customer area, alerts, and More sections.

### Product direction shown in the captures

- A dashboard for due-today and overdue interest.
- Loan and payment summaries with transaction history.
- Customer search and account details.
- A quick action for adding a record.

The dashboard values and some navigation sections in the current source are still sample or placeholder content. Loan lifecycle, payment posting, synchronization, and authentication are not described here as completed features.

## Tech stack

| Area | Technology |
| --- | --- |
| Language | Kotlin |
| UI | Jetpack Compose and Material 3 |
| Persistence | Room with Kotlin coroutines/Flow |
| Architecture | ViewModel and repository pattern |
| Build | Gradle Kotlin DSL |

## Project layout

```text
rbj-khata/
├── app/
│   └── src/main/java/com/rbj/khata/
│       ├── data/       # Room database, entities, DAO, repository
│       ├── ui/         # Customer screens and app theme
│       └── MainActivity.kt
├── docs/
│   └── screenshots/   # App screenshots
├── gradle/            # Gradle wrapper configuration
└── README.md
```

## Run locally

1. Install Android Studio with an Android SDK.
2. Clone this repository and open the project folder in Android Studio.
3. Allow Gradle to sync, then run the **app** configuration on an emulator or Android device.

You can also build a debug APK from a terminal with the Gradle wrapper:

```bash
./gradlew assembleDebug
```

On Windows, use `gradlew.bat assembleDebug`. A locally installed Android SDK is required for the build.

## Data storage

Customer records are currently stored locally on the device with Room. Cloud synchronization and account backup are not included in the current code snapshot.

## Contributing

Suggestions and improvements are welcome. Open an issue to describe a bug or feature idea, or submit a pull request with a focused change. Please do not commit credentials or secrets.

---

<div align="center">

Made to make everyday khata-keeping a little clearer.

</div>
