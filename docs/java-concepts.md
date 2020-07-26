[TOC]

# Java Concepts

## Java Editions
- Java SE (Standard Edition): is the core Java programming platform. 
- Java platform EE (Enterprise Edition): differs from the Java SE in that it adds libraries which provide functionality to deploy fault-tolerant, distributed, multi-tier Java software, based largely on modular components running on an application server. 
- Java ME (Micro Edition) is the platform for developing applications for mobile devices and embedded system.

## Java Beans
JavaBeans are classes that encapsulate many objects (variables, data structures and methods) into a single object (the bean). A JavaBean is a Java class that should follow the following conventions:
- It should have a no-arg constructor.
- It should be Serializable.
- It should provide methods to set and get the values of the properties, known as getter and setter methods.

Basically, a JavaBean is a Java class which satisfies the above mentioned rules. The advantages of using Java Beans:
- The JavaBean properties and methods can be exposed to another application.
- It provides an easiness to reuse the software components.

```java
public class Employee implements java.io.Serializable {  
    private int id;  
    private String name;
  
    public Employee(){}
  
    public void setId(int id){ this.id=id; }
  
    public int getId(){ return id; } 
 
    public void setName(String name){ this.name=name; }  

    public String getName(){ return name; }  
}  

```


# Servlet
A servlet is a web component that is deployed on the server to create a dynamic web page. A servlet extends the capabilities of the servers and responds to the incoming requests. Think of a servlet as a tiny server whose job is to accept requests and issue responses.

Servlets could in principle communicate over any client–server protocol, but they are most often used with HTTP. Thus "servlet" is often used as shorthand for "HTTP servlet".

To deploy and run a servlet, a web container must be used. The web container is responsible for managing the lifecycle of servlets, mapping a URL to a particular servlet and ensuring that the URL requester has the correct access rights.

