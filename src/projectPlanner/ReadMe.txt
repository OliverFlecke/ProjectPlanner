Readme for ProjectPlanner 4000 by group 44, for the course 02161 software engineering.
By
Sebastian Ake Aaen - s144463
Oliver Fleckenstein - s144472
Mark Bo Jensen - s144474





Included
=================
The program (.jar)
Eclipse project
This Readme
Repport Part 3
Updated program design (.pdf)
Updated database design (.pdf)

Logging into the Server
---------------------------------------
This program needs internet access to function properly.
Furthermore you need to access
the internet from DTU, Lynbgy. In order to gain access to the server from elswere,
please send an email to s144472@student.dtu.dk containing your external IP-address.
An email will be sendt back as soon as the IP-address has been added to the database.


Structure of the eclipse project
---------------------------------------
All source code is in the src folder.
All tests are in the test folder.
External libraries are in the external_Libraries folder.
External libraries included in the project are:
	-jcommon-1.0.23.jar
	-jdatepicker-1.3.4.jar
	-jfreechart-1.0.19.jar
	-jinq-jpa-1.6.2-sources.jar
	-mockito-all-1.9.5.jar
	-pdfbox-app-1.8.9.jar
	-sqlijdbc4-2.0.jar
Eclipse plugins used:
	-Junit4 (Used to set up a test driven IDE.)
	-Infinitest (set up to auto run tests automatically.)
	-EclEmma (Used to check code coverage of tests.)





How To Use the program
---------------------------------------
1) Doubleclick the .jar file

2) A LoginDialog will appear, and you wil be asked to log in.

3 a) To log in as admin type in the following:
		Username: admin
		Password: admin


3 b) To log in as a projectLeader type in the following:
		Username: Oliver
		Password: 1234


3 c) To Log in as an Employee type in the following:
		Username: olej
		Password: 1234


Printing a report
----------------------------------------
Printing a report generates a .pdf file, that will be stored locally.
The Project save dialog defaults to user\currentUser\documents
A filename must be specified i order to save a report.
Upon saving the .pdf will be generated and open by itself.




Notes
---------------------------------------
The program runs in one thread, so minor waiting times may occure once in a while.
Please report any bugs you find to s144472@student.dtu.dk