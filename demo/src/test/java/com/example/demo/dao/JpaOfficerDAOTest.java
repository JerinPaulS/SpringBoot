package com.example.demo.dao;

import com.example.demo.entities.Rank;
import com.example.demo.json.Officer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class JpaOfficerDAOTest {

    @Autowired
    private JpaOfficerDAO dao;

    @Autowired
    private JdbcTemplate template;

    private List<Integer> ids() {
        return template.query("SELECT id FROM officers",
                (rs, num) -> rs.getInt("id"));
    }

    @Test
    public void save() {
        Officer officer = new Officer(null, Rank.ENSIGN,
                "Wesley", "Crusher");
        officer = dao.save(officer);
        assertNotNull(officer.getId());
    }

    @Test
    public void findByGetIdThatExists() {
        ids().forEach(id -> {
                    Optional<Officer> officer = dao.findById(id);
                    assertAll(
                            () -> assertTrue(officer.isPresent()),
                            () -> assertEquals(id, officer.get().getId())
                    );
                });
    }

    @Test
    public void findByGetIdThatDoesNotExist() {
        assertThat(ids()).doesNotContain(999);
        Optional<Officer> officer = dao.findById(999);
        assertFalse(officer.isPresent());
    }

    @Test
    public void count() {
        assertEquals(ids().size(), dao.count());
    }

    @Test
    public void findAll() {
        List<String> dbNames = dao.finadAll().stream()
            .map(Officer::getLastName)
            .toList();
        assertThat(dbNames).containsAll(List.of(
                "Archer", "Burnham", "Freeman", "Janeway", "Kirk", "Picard", "Pike", "Sisko"
        ));
    }

    @Test
    public void delete() {
        ids().forEach(id -> {
            Optional<Officer> officer = dao.findById(id);
            assertTrue(officer.isPresent());
            dao.delete(officer.get());
        });
        assertEquals(0, dao.count());
    }


    @Test
    public void existsByGetId() {
        ids().forEach(id -> assertTrue(dao.existsById(id)));
    }
}
