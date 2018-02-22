package com.impetus.kundera;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.impetus.kundera.entities.Department;
import com.impetus.kundera.entities.University;

public class KunderaOneToManyTest {

    public static void main(String[] args) {

        Map<String, String> props = new java.util.HashMap<>();
        props.put("cql.version", "3.0.0");
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("cassandra", props);
        EntityManager em = emf.createEntityManager();

        Department d1 = new Department();
        d1.setId(1);
        d1.setName("Dep1");

        Department d2 = new Department();
        d2.setId(2);
        d2.setName("Dep2");

        List<Department> depList = new ArrayList<Department>();
        depList.add(d1);
        depList.add(d2);

        University u1 = new University();
        u1.setId(1);
        u1.setName("Uni1");
        u1.setDepartments(depList);

        em.persist(u1);

        University findUni = em.find(University.class, 1);
        List<Department> deptList = findUni.getDepartments();

        for (Department dep : deptList) {
            System.out.println(dep.getId());
            System.out.println(dep.getName());
        }

        EntityManager em1 = emf.createEntityManager();

        University findUni1 = em1.find(University.class, 1);
        List<Department> deptList1 = findUni1.getDepartments();

        for (Department dep : deptList1) {
            System.out.println(dep.getId());
            System.out.println(dep.getName());

        }

    }

}
