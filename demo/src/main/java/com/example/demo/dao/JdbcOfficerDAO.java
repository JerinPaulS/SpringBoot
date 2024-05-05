package com.example.demo.dao;

import com.example.demo.entities.Rank;
import com.example.demo.json.Officer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class JdbcOfficerDAO implements OfficerDAO {

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcInsert insertOfficer;

    private final RowMapper<Officer> officerMapper =
            (rs, rowNum) -> new Officer(rs.getInt("id"),
                Rank.valueOf(rs.getString("rank")),
                rs.getString("first_name"),
                rs.getString("last_name")
            );

    @Autowired
    public JdbcOfficerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertOfficer = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("officers")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Officer save(Officer officer) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(officer);
        Number newId = insertOfficer.executeAndReturnKey(parameters);
        return new Officer(newId.intValue(),
                officer.getRank(),
                officer.getFirstName(),
                officer.getLastName());
    }

    @Override
    public Optional<Officer> findById(Integer id) {
        try (Stream<Officer> stream =
                jdbcTemplate.queryForStream(
                        "SELECT * FROM officers WHERE id=?",
                        officerMapper,
                        id)) {
            return stream.findFirst();
        }
    }

    @Override
    public List<Officer> finadAll() {
        return jdbcTemplate.query("SELECT * FROM officers", officerMapper);
    }

    @Override
    public long count() {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM officers", Long.class);
    }

    @Override
    public void delete(Officer officer) {
        jdbcTemplate.update("DELETE FROM officers WHERE id=?", officer.getId());
    }

    @Override
    public boolean existsById(Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT EXISTS(SELECT 1 FROM officers WHERE id=?)",
                Boolean.class, id);
    }
}
