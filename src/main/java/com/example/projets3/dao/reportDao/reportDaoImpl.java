package com.example.projets3.dao.reportDao;
import com.example.projets3.dao.DAOException;
import com.example.projets3.dao.daoFactory;
import com.example.projets3.bean.reportBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class reportDaoImpl implements reportDao {
    private daoFactory DAO;

    public reportDaoImpl(daoFactory dao) {this.DAO = dao;}

    private static reportBean map(ResultSet resultSet) throws SQLException {
        reportBean report = new reportBean();
        report.setId(resultSet.getInt("id"));
        report.setName(resultSet.getString("name"));
        report.setFirst_period_start(resultSet.getString("first_period_start"));
        report.setFirst_period_end(resultSet.getString("first_period_end"));
        report.setSecond_period_start(resultSet.getString("second_period_start"));
        report.setSecond_period_end(resultSet.getString("second_period_end"));
        report.setThird_period_start(resultSet.getString("third_period_start"));
        report.setThird_period_end(resultSet.getString("third_period_end"));
        report.setConsultant_id(resultSet.getInt("consultant_id"));
        report.setPortolio_id(resultSet.getInt("portfolio_id"));
        return report;
    }
    public static PreparedStatement initRequestPrepare(Connection connexion, String sql, Object... objets)
            throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement(sql);
        for (int i = 0; i < objets.length; i++) {
            preparedStatement.setObject(i + 1, objets[i]);
        }
        return preparedStatement;
    }

    @Override
    public reportBean getReportById(int id) throws DAOException {
        final String SQL_SELECT = "SELECT * FROM report WHERE id = ?";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        reportBean report = null;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initRequestPrepare(connexion, SQL_SELECT, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                report= map(resultSet);
            }
        }catch (SQLException e) {
            throw new DAOException(e);
        }
        return report;
    }

    @Override
    public reportBean getReportByPortfolio(int id_portfolio) throws DAOException {
        final String SQL_SELECT = "SELECT * FROM report WHERE portfolio_id = ?";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        reportBean report = null;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initRequestPrepare(connexion, SQL_SELECT, id_portfolio);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                report= map(resultSet);
            }
        }catch (SQLException e) {
            throw new DAOException(e);
        }
        return report;
    }

    @Override
    public ArrayList<reportBean> getReports(int id_conseiller) throws DAOException {
        final String SQL_SELECT = "SELECT * FROM report where consultant_id = ?";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        reportBean report = null;
        ArrayList<reportBean> reports = new ArrayList<>();
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initRequestPrepare(connexion, SQL_SELECT, id_conseiller);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                report= map(resultSet);
                reports.add(report);
            }
        }catch (SQLException e) {
            throw new DAOException(e);
        }
        return reports;
    }

    @Override
    public void createReport(reportBean reportBean) throws DAOException {
        final String SQL_INSERT = "INSERT INTO report (name, first_period_start, first_period_end,second_period_start, second_period_end, third_period_start, third_period_end, portfolio_id, consultant_id) VALUES (?, ?, ?,?,?,?,?,?,?)";

        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = initRequestPrepare(connexion, SQL_INSERT, reportBean.getName(), reportBean.getFirst_period_start(), reportBean.getFirst_period_end(), reportBean.getSecond_period_start(), reportBean.getSecond_period_end(), reportBean.getThird_period_start(), reportBean.getThird_period_end(), reportBean.getPortolio_id(), reportBean.getConsultant_id())) {

            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
