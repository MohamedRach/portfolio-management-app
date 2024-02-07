package com.example.projets3.dao.hiringDao;

import com.example.projets3.bean.HiringBean;
import com.example.projets3.dao.DAOException;
import com.example.projets3.dao.daoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class hiringDaoImpl implements hiringDao{
    private daoFactory DAO;
    public hiringDaoImpl(daoFactory daoFactory){this.DAO = daoFactory;}
    public static PreparedStatement initRequestPrepare(Connection connexion, String sql, Object... objets)
            throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement(sql);
        for (int i = 0; i < objets.length; i++) {
            preparedStatement.setObject(i + 1, objets[i]);
        }
        return preparedStatement;
    }
    private static HiringBean map(ResultSet resultSet) throws SQLException {
        HiringBean hiring = new HiringBean();
        hiring.setId(resultSet.getInt("id"));
        hiring.setId_conseiller(resultSet.getInt("id_conseiller"));
        hiring.setId_user(resultSet.getInt("id_user"));
        return hiring;
    }

    @Override
    public ArrayList<HiringBean> getAllClients(int id_conseiller) throws DAOException {
        final String SQL_SELECT = "SELECT * FROM hiring WHERE id_conseiller = ?";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<HiringBean> hirings = new ArrayList<>();
        HiringBean hiring = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initRequestPrepare(connexion, SQL_SELECT, id_conseiller);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                hiring = map(resultSet);
                hirings.add(hiring);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return hirings;
    }
}
