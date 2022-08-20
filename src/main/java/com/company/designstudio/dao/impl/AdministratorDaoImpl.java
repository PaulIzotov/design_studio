package com.company.designstudio.dao.impl;


import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.company.designstudio.dao.AdministratorDao;
import com.company.designstudio.connection.DataSource;
import com.company.designstudio.entity.Administrator;
import com.company.designstudio.entity.Role;

@Log4j2
public class AdministratorDaoImpl implements AdministratorDao {
    private static final String FIND_ALL = "SELECT ad.id, ad.firstName, ad.lastName, ad.email, ad.password, "
            + "r.name AS role "
            + "FROM administrators ad "
            + "JOIN roles r ON ad.role_id = r.id WHERE ad.deleted = false";

    private static final String FIND_ALL_PAGES = "SELECT ad.id, ad.firstName, ad.lastName, ad.email, ad.password, "
            + "r.name AS role "
            + "FROM administrators ad "
            + "JOIN roles r ON ad.role_id = r.id WHERE ad.deleted = false LIMIT ? OFFSET ?";

    private static final String FIND_BY_ID = "SELECT ad.id, ad.firstName, ad.lastName, ad.email, ad.password, "
            + "r.name AS role "
            + "FROM administrators ad "
            + "JOIN roles r ON ad.role_id = r.id "
            + "WHERE ad.id = ? AND ad.deleted = false";

    private static final String FIND_BY_EMAIL = "SELECT ad.id, ad.firstName, ad.lastName, ad.email, ad.password, "
            + "r.name AS role "
            + "FROM administrators ad "
            + "JOIN roles r ON ad.role_id = r.id "
            + "WHERE ad.email = ? AND ad.deleted = false";

    private static final String INSERT = "INSERT INTO administrators (firstName, lastName, email, password, role_id) "
            + "VALUES (?, ?, ?, ?, (SELECT id FROM roles WHERE name = ?))";

    private static final String UPDATE = "UPDATE administrators SET firstName = ?, lastName = ?, "
            + "email = ?, password = ? "
            + "WHERE id = ? AND deleted = false";

    private static final String DELETE = "UPDATE administrators SET deleted = true WHERE id = ?";


    private final DataSource dataSource;

    public AdministratorDaoImpl(DataSource dataSource) {
        log.debug("Create dataSource");
        this.dataSource = dataSource;
    }

    @Override
    public List<Administrator> findAll(int limit, long offset) {
        List<Administrator> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            log.debug("Query 'find all'");
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_PAGES);
            statement.setInt(1, limit);
            statement.setLong(2, offset);
            ResultSet resultSet = statement.executeQuery();
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
    public Administrator findByEmail(String email) {
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
    public Administrator save(Administrator entity) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getPassword());
            statement.setString(5, "ADMIN");

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

    @Override
    public long count() {
        try (Connection connection = dataSource.getConnection()) {
            log.debug("Query 'find all'");
            PreparedStatement statement = connection.prepareStatement("SELECT count(*) AS total FROM project_infos");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("total");
            }
        } catch (SQLException e) {
            log.error("Error executing command 'all', ", e);
        }
        throw new RuntimeException("Couldn't count project_infos");
    }
}