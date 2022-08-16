package com.company.designstudio.dao.impl;

import com.company.designstudio.connection.DataSource;
import com.company.designstudio.dao.AdministratorDao;
import com.company.designstudio.dao.DesignerDao;
import com.company.designstudio.dao.ProjectInfoDao;
import com.company.designstudio.dao.ProjectDao;
import com.company.designstudio.entity.Administrator;
import com.company.designstudio.entity.Designer;
import com.company.designstudio.entity.Project;
import com.company.designstudio.entity.ProjectInfo;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ProjectDaoImpl implements ProjectDao {
    private static final String FIND_ALL = "SELECT p.id, p.admin_id AS admin, p.designer_id AS designer, "
            + "p.priceM2, p.square "
            + "FROM projects p JOIN administrators ad ON p.admin_id = ad.id JOIN designers d ON p.designer_id = d.id "
            + "WHERE p.deleted = false";

    private static final String FIND_ALL_PAGES = "SELECT p.id, p.admin_id AS admin, p.designer_id AS designer, "
            + "p.priceM2, p.square "
            + "FROM projects p JOIN administrators ad ON p.admin_id = ad.id JOIN designers d ON p.designer_id = d.id "
            + "WHERE p.deleted = false LIMIT ? OFFSET ?";

    private static final String FIND_BY_ID = "SELECT p.id, p.admin_id AS admin, p.designer_id AS designer, "
            + "p.priceM2, p.square "
            + "FROM projects p "
            + "WHERE p.id = ? AND p.deleted = false";

    private static final String INSERT = "INSERT INTO projects (admin_id, designer_id, priceM2, square) "
            + "VALUES (?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE projects SET admin_id = ?, designer_id = ?, "
            + "priceM2 = ?, square = ? "
            + "WHERE id = ? AND p.deleted = false";

    private static final String DELETE = "UPDATE projects SET deleted = true WHERE id = ?";

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
    public List<Project> findAll(int limit, long offset) {
        List<Project> list = new ArrayList<>();
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
            statement.setObject(2, entity.getAdmin());
            statement.setBigDecimal(3, entity.getPriceM2());
            statement.setBigDecimal(4, entity.getSquare());

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
            statement.setObject(2, entity.getAdmin());
            statement.setBigDecimal(3, entity.getPriceM2());
            statement.setBigDecimal(4, entity.getSquare());
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
        entity.setAdmin(administrator);

        Long designerId = resultSet.getLong("designer");
        Designer designer = designerDao.findById(designerId);
        entity.setDesigner(designer);

        entity.setPriceM2(resultSet.getBigDecimal("priceM2"));

        entity.setSquare(resultSet.getBigDecimal("square"));

        List<ProjectInfo> details = projectInfoDao.findByProjectId(entity.getId());
        entity.setDetails(details);

        return entity;
    }

    @Override
    public long count() {
        try (Connection connection = dataSource.getConnection()) {
            log.debug("Query 'find all'");
            PreparedStatement statement = connection.prepareStatement("SELECT count(*) AS total FROM projects");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("total");
            }
        } catch (SQLException e) {
            log.error("Error executing command 'all', ", e);
        }
        throw new RuntimeException("Couldn't count projects");
    }
}