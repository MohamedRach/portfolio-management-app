package com.example.projets3.dao.portfolio;

import com.example.projets3.bean.ConseillerBean;
import com.example.projets3.bean.portfolioBean;
import com.example.projets3.bean.portfolioBean;
import com.example.projets3.dao.DAOException;
import com.example.projets3.dao.daoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class portfolioDaoImpl implements portfolioDao{

    private daoFactory DAO;

    public portfolioDaoImpl(daoFactory daoFactory) {
        this.DAO = daoFactory;
    }

    private static portfolioBean map(ResultSet resultSet) throws SQLException {
        portfolioBean portfolio = new portfolioBean();
        portfolio.setId(resultSet.getInt("id"));
        portfolio.setName(resultSet.getString("name"));
        portfolio.setDescription(resultSet.getString("description"));
        portfolio.setUser_id(resultSet.getInt("user_id"));
        return portfolio;
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
    public void createPortfolio(portfolioBean portfolio) throws DAOException {
        final String SQL_INSERT = "INSERT INTO portfolio (name, description, user_id) VALUES (?, ?, ?)";

        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = initRequestPrepare(connexion, SQL_INSERT, portfolio.getName(), portfolio.getDescription(), portfolio.getUser_id())) {

            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
    @Override
    public portfolioBean getPortfolio(int id) throws DAOException {
        final String SQL_SELECT = "SELECT * FROM portfolio where id = ?";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        portfolioBean portfolio = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initRequestPrepare(connexion, SQL_SELECT, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                portfolio = map(resultSet);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return portfolio;
    }
    public ArrayList<portfolioBean> getPortfolios(int user_id) throws DAOException {
        final String SQL_SELECT = "SELECT * FROM portfolio WHERE user_id = ?";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        portfolioBean portfolio = null;
        ArrayList<portfolioBean> portfolios = new ArrayList<portfolioBean>();
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initRequestPrepare(connexion, SQL_SELECT, user_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                portfolio = map(resultSet);
                portfolios.add(portfolio);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return portfolios;
    }

    @Override
    public void deletePortfolio(int id) throws DAOException {
        final String SQL_delete = "DELETE FROM portfolio WHERE id = ?";
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = initRequestPrepare(connexion, SQL_delete, id)) {
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Échec de la supression de stock, aucune ligne suprimée dans la table.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }


}
