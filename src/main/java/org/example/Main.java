package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Main {

    public static void main(String[] args) {

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            final Transaction transaction = session.beginTransaction();

            DepartmentEntity weightDivision = DepartmentEntity.builder()
                    .name("Lightweight")
                    .build();

            session.save(weightDivision);

            EmployeeEntity fighter = new EmployeeEntity();
            fighter.setName("Conor McGregor");
            fighter.setEmail("conor@ufc.com");
            fighter.setDepartment(weightDivision);

            session.save(fighter);

            fighter = new EmployeeEntity();
            fighter.setName("Khabib Nurmagomedov");
            fighter.setEmail("khabib@ufc.com");
            fighter.setDepartment(weightDivision);

            session.save(fighter);

            transaction.commit(); // Genera el insert

            // hql => lenguaje de consulta de hibernate
            // Genera el mapeo
            Query<EmployeeEntity> query = session.createQuery("FROM EmployeeEntity e JOIN FETCH e.department", EmployeeEntity.class);

            query.list().forEach(System.out::println);
        }

    }

}