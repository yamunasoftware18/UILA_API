# UILA_API
Easy to Use, Universal ML Classification Clustering Algorithm, Written in Java

## Installation: 
Download the repo and use the source in your projects, or use Maven or Gradle:

Maven:
```XML
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.github.yamunasoftware18</groupId>
    <artifactId>UILA_API</artifactId>
    <version>Tag</version>
  </dependency>
</dependencies>
```

Gradle:
```Java
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}

dependencies {
  implementation 'com.github.yamunasoftware18:UILA_API:Tag'
}
```

## Instructions:

### Training:
     
The training method creates a box around the data point and and initializes a midpoint to fit the data. 
It then saves the information into the text files to create a model to use for running and using the value.
It does this by removing outliers and creating a box around the clustered data set and initializing a midpoint based on means.
This system is efficient because it is much faster when dealing with large data sets of an x, y data plane. 
     
### Running:
     
The running methods use either a singular box check for checking if a data point is within a box or, if unsure it can check if it is within multiple boxes and uses a distance method to calculate a distValue which acts as a tie breaker, in case it fits within multiple boxes. 
The distValue is a combination of the distance of the point to the nearest box landmark (top left corner, top right corner, etc) and the distance to the midpoint.
The combination of these two pieces of data allows for accurate classification when dealing with nearby data sets that have very small differences. 
The advantage to this setup is that it is much faster to run when dealing with large data sets.

### Coding: 

There are sample methods in the Trainer class, that you can override and customize, you can also use or fork functions into your new class:

```Java
//Adds Data to Data Set:
populate(String typeOfData, double data);

//Removes Data from Data set:
removeData(String typeOfData, int dataPoint);

//Trains the Data (Trainer.java):
@Override 
public void train() {
  //See ExampleRun.java for information on training methods...
}

//Classifies the Data (Neuron.java):
@Override 
public void classify(double xData, double yData) {
  //See Neuron.java and ExampleRun.java for implementation...
}
```
