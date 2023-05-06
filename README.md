# autograder-intellij-plugin
IntelliJ plugin for Eastern Washington University's Advancement Programming Exam. 
Note: Main branch contains solution for uploading a single file, Folder branch contains partial solution for uploading a folder

# Instructions
- Create an "IntelliJ Platform Plugin" project in IntelliJ
- Pull this project such that the .java files end up in this directory (you may need to right click and add directory manually):

![image](https://user-images.githubusercontent.com/97702896/236650674-887f259c-9c4f-405b-be29-73a171d88881.png)

- In IntelliJ, select the "Run Plugin" run configuration
- A new IntelliJ window will pop up, this is the student view/testing environment. Create a new project
- On the left hand side, in the filetree, select the folder you wish to upload
- Go to tools > submit

# Server
- Apache 
- The included index.php listens for a post request and performs operations when one is recieved. Replace this with your test cases.
