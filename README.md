# Motion-Detection-System

APPLICATION:
Have you ever been in the fear of someone stealing your computer and accessing your files? With this project, a user is enabled to monitor any intrusion or motion detected by his/her computer webcam. Security has never been simpler before!

A security system to detect motion and capture a photo. 
The image is then sent to a MySQL server for processing. 
The server stores all the images taken by the client's webcam at a 1000 millisecond interval and process it in realtime to 
detect a face using Google Vision API. 
The server later sends the face-detected image to a Java program using sockets (socket.io) which is programmed on a third computer. 
This program downloads this image onto a local host directory. Using HTML, CSS, and Javascript, the website is user interactive 
and it successfully displays the image captured by the web cam!


FOR USE--
1. Download project ZIP file
2. Setup a local host on the third computer for the website to run and connect with other machines
3. With preferrably three different computers, run the program on each.
4. The website will load an image captured by the webcam after motion detected.
