package com.example.demo.dao;

import com.example.demo.json.Officer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaOfficerDAO implements OfficerDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Officer save(Officer officer) {
        entityManager.persist(officer);
        return officer;
    }

    @Override
    public Optional<Officer> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Officer.class, id));
    }

    @Override
    public List<Officer> finadAll() {
        return entityManager.createQuery("SELECT o FROM Officer o", Officer.class)
                .getResultList();
    }

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(o.id) FROM Officer o", Long.class)
                .getSingleResult();
    }

    @Override
    public void delete(Officer officer) {
        entityManager.remove(officer);
    }

    @Override
    public boolean existsById(Integer id) {
        Long count = entityManager.createQuery("SELECT COUNT(o.id) FROM Officer o WHERE o.id=:id", Long.class)
                .setParameter("id", id)
                .getSingleResult();
        return count > 0;
    }
}
