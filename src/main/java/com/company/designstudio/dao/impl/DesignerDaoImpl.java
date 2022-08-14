package com.company.designstudio.dao.impl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.company.designstudio.entity.Role;
import com.company.designstudio.entity.Designer;
import com.company.designstudio.entity.Specialization;
import lombok.extern.log4j.Log4j2;

import com.company.designstudio.dao.DesignerDao;
import com.company.designstudio.connection.DataSource;

@Log4j2
public class DesignerDaoImpl implements DesignerDao {
    private static final String FIND_ALL = "SELECT d.id, d.firstName, d.lastName, d.experience, d.email, d.password, "
            + "sp.name AS specialization, r.name AS role "
            + "FROM designers d "
            + "JOIN specializations sp ON d.specialization_id = sp.id "
            + "JOIN roles r ON d.role_id = r.id "
            + "WHERE d.deleted = false";

    private static final String FIND_BY_ID = "SELECT d.id, d.firstName, d.lastName, d.experience, d.email, d.password, "
            + "sp.name AS specialization , r.name AS role "
            + "FROM designers d "
            + "JOIN specializations sp ON d.specialization_id = sp.id "
            + "JOIN roles r ON d.role_id = r.id "
            + "WHERE d.id = ? AND d.deleted = false";

    private static final String FIND_BY_EMAIL = "SELECT d.id, d.firstName, d.lastName, d.experience, d.email, "
            + "d.password, sp.name AS specialization , r.name AS role "
            + "FROM designers d "
            + "JOIN specializations sp ON d.specialization_id = sp.id "
            + "JOIN roles r ON d.role_id = r.id "
            + "WHERE d.email = ? AND d.deleted = false";

    private static final String INSERT = "INSERT INTO designers (firstName, lastName, experience, email, "
            + "password, specialization_id, role_id) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE designers SET firstName = ?, lastName = ?, "
            + "experience = ?, email = ?, password = ? "
            + "WHERE id = ? AND d.deleted = false";

    private static final String DELETE = "UPDATE designers SET deleted = true WHERE id = ?";


    private final DataSource dataSource;

    public DesignerDaoImpl(DataSource dataSource) {
        log.debug("Create dataSource");
        this.dataSource = dataSource;
    }

    @Override
    public List<Designer> findAll() {
        List<Designer> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            log.debug("Query 'find all'");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                list.add(process(resultSet));
            }
        } catch (SQLException e) {
            log.error("Error executing command 'all', ", e);
        }
        return list;
    }

    @Override
    public Designer findById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return process(resultSet);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return null;

    }public Designer findByEmail(String email) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_EMAIL);
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return process(resultSet);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Designer save(Designer entity) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setLong(3, entity.getExperience());
            statement.setString(4, entity.getEmail());
            statement.setString(5, entity.getPassword());

            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                Long id = keys.getLong("id");
                return findById(id);
            }

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        throw new RuntimeException("Couldn't create new entity: " + entity);
    }

    @Override
    public Designer update(Designer entity) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setLong(3, entity.getExperience());
            statement.setString(4, entity.getEmail());
            statement.setString(5, entity.getPassword());
            statement.setLong(6, entity.getId());

            if (statement.executeUpdate() == 1) {
                return findById(entity.getId());
            }

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        throw new RuntimeException("Couldn't update entity: " + entity);
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setLong(1, id);

            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted == 1;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    private Designer process(ResultSet resultSet) throws SQLException {
        Designer entity = new Designer();
        entity.setId(resultSet.getLong("id"));
        entity.setFirstName(resultSet.getString("firstName"));
        entity.setLastName(resultSet.getString("lastName"));
        entity.setExperience(resultSet.getLong("experience"));
        entity.setEmail(resultSet.getString("email"));
        entity.setPassword(resultSet.getString("password"));
        entity.setSpecialization(Specialization.valueOf(resultSet.getString("specialization")));
        entity.setRole(Role.valueOf(resultSet.getString("role")));
        return entity;
    }
}