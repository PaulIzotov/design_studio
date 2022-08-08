package com.company.design_studio.dao.impl;


import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.company.design_studio.dao.AdministratorDao;
import com.company.design_studio.connection.DataSource;
import com.company.design_studio.entity.Administrator;
import com.company.design_studio.entity.Role;

@Log4j2
public class AdministratorDaoImpl implements AdministratorDao {
    private static final String FIND_ALL = "SELECT ad.id, ad.firstName, ad.lastName, ad.email, ad.password, "
            + "r.name AS role "
            + "FROM administrators ad "
            + "JOIN roles r ON ad.role_id = r.id WHERE ad.deleted = false";

    private static final String FIND_BY_ID = "SELECT ad.id, ad.firstName, ad.lastName, ad.email, ad.password, "
            + "r.name AS role "
            + "FROM administrators ad "
            + "JOIN roles r ON ad.role_id = r.id "
            + "WHERE ad.id = ? AND ad.deleted = false";

    private static final String INSERT = "INSERT INTO administrators ad (firstName, lastName, email, password) "
            + "VALUES (?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE administrators ad SET firstName = ?, lastName = ?, "
            + "email = ?, password = ? "
            + "WHERE id = ? AND ad.deleted = false";

    private static final String DELETE = "UPDATE administrators ad SET deleted = true WHERE id = ?";


    private final DataSource dataSource;

    public AdministratorDaoImpl(DataSource dataSource) {
        log.debug("Create dataSource");
        this.dataSource = dataSource;
    }

    @Override
    public List<Administrator> findAll() {
        List<Administrator> list = new ArrayList<>();
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
    public Administrator findById(Long id) {
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
    }

    @Override
    public Administrator save(Administrator entity) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getPassword());

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
    public Administrator update(Administrator entity) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getPassword());
            statement.setLong(5, entity.getId());

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
    private Administrator process(ResultSet resultSet) throws SQLException {
        Administrator entity = new Administrator();
        entity.setId(resultSet.getLong("id"));
        entity.setFirstName(resultSet.getString("firstName"));
        entity.setLastName(resultSet.getString("lastName"));
        entity.setEmail(resultSet.getString("email"));
        entity.setPassword(resultSet.getString("password"));
        entity.setRole(Role.valueOf(resultSet.getString("role")));
        return entity;
    }
}