# Watermarks

### Table of content
1. App description
2. Technologies
3. Installation guide
4. Project members

## 1. App description
The aim of the work is to design and implement the application for the web browser that will allow to add watermark to image.

#### Primary features
Features that must be implemented in scope of this project:
* upload image for watermarking
* allow to store image
  * allow to create an account
  * login to the system
  * log out
* remove picture
* present history of watermarked pictures
  * present stored images
* watermark detection
  * true / false

Features that may be implemented:
* watermark identification (to who this picture belong)
* additional image format
* unique watermark (creating one image for one social service)
* visible watermark

## 2. Technologies
* [Angular](https://angular.io/)
* [Python](https://www.python.org/)
* [PostgreSQL](https://www.postgresql.org.pl/)
* [Cloudinary](https://cloudinary.com/)
* [Heroku](https://www.heroku.com/)
* [TravisCI](https://travis-ci.com/)

## 3. Installation guide
This section contains information needed to run application as a developer.
#### Frontend app
* install latest version of [Node.js](https://nodejs.org/en/download/package-manager/).
* move to the watermark-client directory: ```cd watermark-client```
* install latest version of Angular: ```npm install -g @angular/cli```
* install project: ```npm install```
* run application: ```ng serve```

#### Algorithm
* install [Python3](https://www.python.org/downloads/)
* install [numpy](https://numpy.org/install/), [opencv](https://pypi.org/project/opencv-python/)
* move to the watermark-algorithm directory: ```cd watermark-algorithm```
* when testing:
  * change TESTING_ALGORITHM to 1 (```global_methods.py```)
  * run ```test.py```
* when running http application:
  * change TESTING_ALGORITHM to 0 (```global_methods.py```)
  * set up SAVE_DIRECTORY to directory that contains the uploaded files
  * run ```app.py```

#### Backend app
* install [JDK](https://jdk.java.net/archive/) version 11 or higher
* install [Maven](https://www.javahelps.com/2017/10/install-apache-maven-on-linux.html)
* in application.properties decide where to save uploaded images: ```file-repository.save-directory```
* run the database: ```docker run --name postgres-mmvc -p 5432:5432 -e POSTGRES_PASSWORD=Multimedia2020! -e POSTGRES_DB=spring-test -d postgres```
* move to the watermark-api directory: ```cd watermark-api```
* compile application: ```mvn clean install```
* run app: ```java -jar target/watermark-api-0.0.1-SNAPSHOT.jar```
* open your browser on address: ```http://localhost:8080/```
* to login you can use accounts:
  * john.smith@example.com / test
  * jan.nowak@example.com / test

## 4. Project members
* Piotr Kowalski
* Marek Rutkowski
* Eliza Kalata
* Wojciech Spoton
