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
* run application: ```python3 main.py```  and wait a little

### Backend app
* install [JDK] (https://jdk.java.net/archive/)
* install [Maven] (https://www.javahelps.com/2017/10/install-apache-maven-on-linux.html)
* move to the watermark-algorithm directory: ```cd watermark-api```
* run application: ```mvn compile``` 

## 4. Project members
* Piotr Kowalski
* Marek Rutkowski
* Eliza Kalata
* Wojciech Spoton
