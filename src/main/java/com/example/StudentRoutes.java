package com.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration()
            .bindingMode(RestBindingMode.json)
            .dataFormatProperty("prettyPrint", "true");

        // REST API Routes
        rest("/students")
            .get()
            .to("direct:getAllStudents")
            .post()
            .to("direct:addStudent");

        from("direct:getAllStudents")
            .to("jpa://com.example.Student?namedQuery=Student.findAll")
            .marshal().json();

        from("direct:addStudent")
            .unmarshal().json()
            .to("jpa://com.example.Student")
            .setBody().constant("Student added successfully.");
    }
}

