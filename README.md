# Dev-Env-Deps
- [Download and install Apache Tomcat 8](https://tomcat.apache.org/download-80.cgi)
- [Download and install Eclipse IDE](http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/marsr)
- [Download](https://maven.apache.org/download.cgi) and [Install](https://maven.apache.org/install.html) Maven
- [Install M2E (Maven to Eclipse Plugin)](http://stackoverflow.com/a/13640110)

# Installation
- Import as Maven Project on Eclipse;
- Right click on imported project in Project Explorer Panel;
- `Maven` > `Update Project`;
- Select your project > `Enter`;

# Deploy Eclipse Configuration
- Open `Run` > `Run Configurations...`;

#### Development
- Right click on `Maven Build` > `New`;
- Set configurations below:
  - `Name`: agenda-academica-api-local
  - `Base Directory`: `Browse Workspace` > Select your project
  - `Goals`: **tomcat:run**

#### Production (Heroku)
- Right click on `Maven Build` > `New`;
- Set configurations below:
  - `Name`: agenda-academica-api-heroku
  - `Base Directory`: `Browse Workspace` > Select your project
  - `Goals`: **heroku:deploy-war**

# Enjoy!
RESTful URL: http://127.0.0.1:8080/agenda-academica-api/rest/*
