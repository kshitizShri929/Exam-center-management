package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {

    @PersistenceContext
    EntityManager entityManager;

    @GET
    public List<Student> getAllStudents() {
        return entityManager.createNamedQuery("Student.findAll", Student.class).getResultList();
    }

    @POST
    @Transactional
    public Response addStudent(Student student) {
        entityManager.persist(student);
        return Response.status(Response.Status.CREATED).entity(student).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateStudent(@PathParam("id") Long id, Student student) {
        Student existingStudent = entityManager.find(Student.class, id);
        if (existingStudent == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingStudent.setName(student.getName());
        existingStudent.setScore(student.getScore());
        existingStudent.setExamCenter(student.getExamCenter());
        return Response.ok(existingStudent).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteStudent(@PathParam("id") Long id) {
        Student student = entityManager.find(Student.class, id);
        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        entityManager.remove(student);
        return Response.noContent().build();
    }
}
