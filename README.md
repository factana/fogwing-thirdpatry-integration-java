# **_Java program to send OpenWeatherMap(OWM) data to Fogwing_** 

This repository contains a Java program, that allows you to retrieve weather data from the OpenWeatherMap API and send it to the Fogwing IoTHub API. The program can be run using Eclipse IDE.

>**Note:** This program is specific to send OpenWeatherMap (OWM) to Fogwing. It may require additional modifications and configurations to work properly with your specific use case.

## **OpenWeatherMap to Fogwing Client Program**

To run the `OWMToFogwingClient` program in Eclipse IDE, follow the step-by-step instructions below:

### Pre-requisites:
- [Eclipse IDE](https://www.eclipse.org/downloads)

### **Step 1: Clone the Repository**
- Clone the [fogwing-thirdpatry-integration-java](https://github.com/factana/fogwing-thirdpatry-integration-java) repository containing the program code to your local machine.

### **Step 2: Install and Launch Eclipse IDE** 
- Visit the official [Eclipse website](https://www.eclipse.org/downloads/packages/installer) to download the Eclipse installer and install Eclipse IDE on your machine.
- Launch Eclipse IDE once the installation is complete.

### **Step 3: Import the Project**
- Click on **File** in the menu bar and select **Open Projects from File System**, in the dialog that appears, click on the "Directory" button and navigate to the location where you cloned the repository. Select the **OWMFogwingAPIIntegration** folder and click "Finish" to import the project into Eclipse.
- Once the project is imported, locate the **OWMToFogwingClient.java** file in the Eclipse Project Explorer. It should be located in the **OWMFogwingAPIIntegration** package.
  
### **Step 4: Update Credentials**
- Locate the **credentials.json** file in **OWMFogwingAPIIntegration** package, open the file and you will find sections for OWM and Fogwing credentials,update the respective fields with your valid OWM and Fogwing credentials.

>**Note:** Ensure that you provide valid OWM and Fogwing IoTHub credentials in the **credentials.json** file for successful data transmission.

### **Step 6: Run the Program**
- Right-click on the **OWMToFogwingClient.java** file and select **Run As** > **Java Application** from the context menu.
- The program will start executing, and the output will be displayed in the Eclipse console.

>**Note:** If everything goes according to the instructions mentioned above, you should see `{"data":"Successfully published","description":"The Request Has Succeeded","message":"Created","statusCode":201}` message displayed in the Eclipse console. In case of any errors or issues, an error message will be shown instead.

### **Step 7. Analyze Your Data on the Fogwing Platform**
- Now you are ready to analyze your data at [Fogwing Platform](https://portal.fogwing.net/) portal, you can check all the data within the data storage in the portal.
  
## **Where to Find Help and Resources for Fogwing**
- [Fogwing Open APIs Docs.](https://api.fogwing.net/)
- [OpenWeatherMap API Docs.](https://openweathermap.org/api)
- [Fogwing Platform Forum.](https://community.fogwing.io/)
- [Fogwing Platform Docs.](https://docs.fogwing.io/)
 

## Please visit https://www.fogwing.io/industrial-iot-platform/ for more information about Fogwing Industrial IoT Platform. ##
