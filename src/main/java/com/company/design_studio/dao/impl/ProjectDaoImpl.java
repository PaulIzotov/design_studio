package com.company.design_studio.dao.impl;

import com.company.design_studio.connection.DataSource;
import com.company.design_studio.dao.AdministratorDao;
import com.company.design_studio.dao.DesignerDao;
import com.company.design_studio.dao.ProjectInfoDao;
import com.company.design_studio.dao.ProjectDao;
import com.company.design_studio.entity.Administrator;
import com.company.design_studio.entity.Designer;
import com.company.design_studio.entity.Project;
import com.company.design_studio.entity.ProjectInfo;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ProjectDaoImpl implements ProjectDao {
    private static final String FIND_ALL = "SELECT p.admin_id AS administrator, p.designer_id AS designer, "
            + "p.price_for_m2, p.square "
            + "FROM projects p "
            + "WHERE p.deleted = false";

    private static final String FIND_BY_ID = "SELECT p.admin_id AS administrator, p.designer_id AS designer, "
            + "p.price_for_m2, p.square "
            + "FROM projects p "
            + "WHERE p.id = ? AND p.deleted = false";

    private static final String INSERT = "INSERT INTO projects p (admin_id, designer_id, price_for_m2, square) "
            + "VALUES (?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE projects p SET admin_id = ?, designer_id = ?, "
            + "price_for_m2 = ?, square = ? "
            + "WHERE id = ? AND p.deleted = false";

    private static final String DELETE = "UPDATE projects p SET deleted = true WHERE id = ?";

    private final DataSource dataSource;
    private final DesignerDao designerDao;
    private final AdministratorDao administratorDao;
    private final ProjectInfoDao projectInfoDao;

    public ProjectDaoImpl(DataSource dataSource, DesignerDao designerDao, AdministratorDao administratorDao,
                          ProjectInfoDao projectInfoDao) {
        log.debug("Create dataSource");
        this.dataSource = dataSource;
        this.designerDao = designerDao;
        this.administratorDao = administratorDao;
        this.projectInfoDao = projectInfoDao;

    }
    @Override
    public List<Project> findAll() {
        List<Project> list = new ArrayList<>();
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
    public Project findById(Long id) {
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
    public Project save(Project entity) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setObject(1, entity.getDesigner());
            statement.setObject(2, entity.getAdministrator());
            statement.setDouble(3, entity.getPrice_for_m2());
            statement.setDouble(4, entity.getSquare());

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
    public Project update(Project entity) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setObject(1, entity.getDesigner());
            statement.setObject(2, entity.getAdministrator());
            statement.setDouble(3, entity.getPrice_for_m2());
            statement.setDouble(4, entity.getSquare());
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

    private Project process(ResultSet resultSet) throws SQLException {
        Project entity = new Project();
        entity.setId(resultSet.getLong("id"));

        Long adminId = resultSet.getLong("admin");
        Administrator administrator = administratorDao.findById(adminId);
        entity.setAdministrator(administrator);

        Long designerId = resultSet.getLong("designer");
        Designer designer = designerDao.findById(designerId);
        entity.setDesigner(designer);

        entity.setPrice_for_m2(resultSet.getDouble("price_for_m2"));

        entity.setSquare(resultSet.getDouble("square"));

        return entity;
    }
}