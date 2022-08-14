package com.company.designstudio.dao.impl;

import com.company.designstudio.connection.DataSource;
import com.company.designstudio.dao.DesignerDao;
import com.company.designstudio.dao.ProjectDao;
import com.company.designstudio.dao.ProjectInfoDao;
import com.company.designstudio.entity.*;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ProjectInfoDaoImpl implements ProjectInfoDao {
    private static final String FIND_ALL = "SELECT i.id, i.designer_id AS designer, i_project_id AS project, "
            + "i.totalPrice, s.name AS status "
            + "FROM project_infos i "
            + "JOIN status s ON i.status_id = s.id JOIN projects p ON i.project_id = p.id "
            + "WHERE i.deleted = false";

    private static final String FIND_BY_ID = "SELECT i.id, i.designer_id AS designer, i_project_id AS project, "
            + "i.totalPrice, s.name AS status "
            + "FROM project_infos i "
            + "JOIN status s ON i.status_id = s.id JOIN projects p ON i.project_id = p.id "
            + "WHERE i.id = ? AND i.deleted = false";

    private static final String FIND_BY_PROJECT_ID = "SELECT i.id, i.designer_id AS designer, i.project_id AS project, "
            + "i.totalPrice, s.name AS status "
            + "FROM project_infos i "
            + "JOIN status s ON i.status_id = s.id JOIN projects p ON i.project_id = p.id "
            + "WHERE i.project_id = ? AND i.deleted = false";

    private static final String INSERT = "INSERT INTO project_infos (designer_id, project_id, totalPrice) "
            + "VALUES (?, ?, ?)";

    private static final String UPDATE = "UPDATE project_infos SET designer_id = ?, project_id = ?, totalPrice = ?, "
            + "WHERE id = ? AND i.deleted = false";

    private static final String DELETE = "UPDATE project_infos SET deleted = true WHERE id = ?";


    private final DataSource dataSource;
    private final DesignerDao designerDao;


    public ProjectInfoDaoImpl(DataSource dataSource, DesignerDao designerDao) {
        log.debug("Create dataSource");
        this.dataSource = dataSource;
        this.designerDao = designerDao;
    }

    @Override
    public List<ProjectInfo> findAll() {
        List<ProjectInfo> list = new ArrayList<>();
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
    public ProjectInfo findById(Long id) {
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
    public ProjectInfo save(ProjectInfo entity) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setObject(1, entity.getDesigner());
            statement.setObject(2, entity.getProjectId());
            statement.setBigDecimal(3, entity.getTotalPrice());
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
    public ProjectInfo update(ProjectInfo entity) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setObject(1, entity.getDesigner());
            statement.setObject(2, entity.getProjectId());
            statement.setBigDecimal(3, entity.getTotalPrice());
            statement.setLong(4, entity.getId());

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

    private ProjectInfo process(ResultSet resultSet) throws SQLException {
        ProjectInfo entity = new ProjectInfo();
        entity.setId(resultSet.getLong("id"));
        Long designerId = resultSet.getLong("designer");
        Designer designer = designerDao.findById(designerId);
        entity.setDesigner(designer);
        entity.setProjectId(resultSet.getLong("project"));
        entity.setTotalPrice(resultSet.getBigDecimal("totalPrice"));
        entity.setStatus(Status.valueOf(resultSet.getString("status")));
        return entity;
    }

    @Override
    public List<ProjectInfo> findByProjectId(Long id) {
        List<ProjectInfo> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            log.debug("Query 'find by project id'");
            PreparedStatement statement = connection.prepareStatement(FIND_BY_PROJECT_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(process(resultSet));
            }
        } catch (SQLException e) {
            log.error("Error executing command 'find by project id', ", e);
        }
        return list;
    }
}
