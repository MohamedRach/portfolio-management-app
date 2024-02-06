package com.example.projets3.dao.stock;
import com.example.projets3.bean.stockBean;
import com.example.projets3.bean.stockBean;
import com.example.projets3.dao.DAOException;
import com.example.projets3.dao.daoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class stockDaoImpl implements stockDao{

    private daoFactory DAO;

    public stockDaoImpl(daoFactory dao) {this.DAO = dao;}
    private static stockBean map(ResultSet resultSet) throws SQLException {
        stockBean stock = new stockBean();
        stock.setId(resultSet.getInt("id"));
        stock.setName(resultSet.getString("name"));
        stock.setPurchaseDate(resultSet.getString("purshaseDate"));
        stock.setPurchasePrice(resultSet.getFloat("purshasePrice"));
        stock.setQuantity(resultSet.getInt("quantity"));
        stock.setPortfolio_id(resultSet.getInt("portfolio_id"));
        return stock;
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
    public ArrayList<stockBean> getStocks(int portfolio) throws DAOException {
        final String SQL_SELECT = "SELECT * FROM stock WHERE portfolio_id = ?";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        stockBean stock = null;
        ArrayList<stockBean> stocks = new ArrayList<stockBean>();
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initRequestPrepare(connexion, SQL_SELECT, portfolio);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                stock = map(resultSet);
                stocks.add(stock);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return stocks;
    }

    @Override
    public stockBean getStock(int portfolio, int id) throws DAOException {
        final String SQL_SELECT = "SELECT * FROM stock WHERE portfolio_id = ? and id = ?";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        stockBean stock = null;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initRequestPrepare(connexion, SQL_SELECT, portfolio);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                stock = map(resultSet);

            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return stock;
    }

    @Override
    public void addStock(stockBean stock) throws DAOException {
        final String SQL_INSERT = "INSERT INTO stock(name, purshasePrice, purshaseDate, quantity, portfolio_id) VALUES (?,?,?,?,?)";
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = initRequestPrepare(connexion, SQL_INSERT, stock.getName(), stock.getPurchasePrice(), stock.getPurchaseDate(),stock.getQuantity(), stock.getPortfolio_id())) {
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Échec de la création de stock, aucune ligne ajoutée dans la table.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void deleteStock(int id) throws DAOException {
        final String SQL_DELETE = "DELETE FROM stock WHERE id = ?";
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = initRequestPrepare(connexion, SQL_DELETE, id)) {
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Échec de la supression de stock, aucune ligne suprimée dans la table.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void updateStock(stockBean stock) throws DAOException {
        final String SQL_UPDATE = "UPDATE stock SET name = ?, purshaseDate = ? , purshasePrice = ?, quantity = ? WHERE portfolio_id = ?";
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = initRequestPrepare(connexion, SQL_UPDATE, stock.getName(), stock.getPurchaseDate(), stock.getPurchasePrice(), stock.getQuantity(), stock.getId())) {
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Échec de la mise à jour de stock.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
