# save-document
Save user document application


This project was developed with Spring boot and Java 8.

Architecture Design:

![save-document-flow-architectur](https://github.com/SemihSz/save-document/assets/37926760/42177086-3b18-4473-98b7-a92a8057dfe6)
In general, services come from the service interface through the controller. In the ServiceImpl part, it continues its operations by calling the executable that the services need to do. 
In this way, that executable service can be called to other places repeatedly.


Project General Document Operations Flow:

![save-document-flow](https://github.com/SemihSz/save-document/assets/37926760/32313c41-fc7c-4262-b04b-9c8406a5bce4)


This application is a file management system that allows the user to save documents, retrieve these documents from the database, download, edit, and delete them. 

Dependencies List:
![image](https://github.com/SemihSz/save-document/assets/37926760/904fae5f-c571-4697-a9bf-b0ea1a43fece)

Postman Request List:

![img.png](postman-request-list.png)

Some file inside of project

- application-document-project.postman_collection.json
- save-document-flow.drawio
- save-document-flow.png
- save-document-flow-architectur.png
- Lorem_ipsum.pdf
- Dummy_data.xlsx
