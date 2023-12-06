# nasa-demo-application
Application for showing the Asteroids details using NASA API using React and SpringBoot

# NASA Asteroid Information Application

## Overview

The NASA Asteroid Information Application is a web-based application built using Spring Boot for the backend and React for the frontend. It is using NASA Asteroids API and provides endpoints to show a list of all asteroids that passed the Earth during a specific week sorted by miss distance and indicate which ones are potentially hazardous. This is shown on the landing page.
Selecting an item in the list should bring the user to the details page for that specific asteroid.
Details page show details about the currently selected asteroid.


## Installation

To get started with the application, follow these steps:

Clone the Git repository: git clone https://github.com/rohansingh13/nasa-demo-application.git

## Running the Application

#### Running the React Frontend:


1. Install Dependencies:

* Ensure that you have Node.js and npm installed on your machine.
* Open a terminal and navigate to the nasa-frontend folder.

2. Install Node Modules:

```bash
npm install
```

3. Start the React App:

```bash
npm start
```
This command will start the development server, and you can access the app at http://localhost:3000 in your web browser.


#### Running the Spring Boot Backend:

1. Install Dependencies:

Ensure that you have Java and Maven installed on your machine.

2. Navigate to the demo-backend Folder:

```bash
cd demo-backend
```

3. Build the Project:

```bash
mvn clean install
```

4. Run the Spring Boot Application:

```bash
mvn spring-boot:run
```
This will start the Spring Boot application, and it will be accessible at http://localhost:9898.

