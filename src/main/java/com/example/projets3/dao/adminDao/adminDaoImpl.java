package com.example.projets3.dao.adminDao;

import com.example.projets3.bean.UserBean;
import com.example.projets3.bean.adminBean;
import com.example.projets3.dao.DAOException;
import com.example.projets3.dao.daoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class adminDaoImpl implements adminDao{

    private daoFactory DAO;

    public adminDaoImpl(daoFactory daoFactory){this.DAO = daoFactory;};
    private static adminBean map(ResultSet resultSet ) throws SQLException {
        adminBean admin = new adminBean();
        admin.setId( resultSet.getInt( "id" ) );
        admin.setNom( resultSet.getString( "nom" ) );
        admin.setEmail( resultSet.getString( "email" ) );
        admin.setPrenom( resultSet.getString( "prenom" ) );
        admin.setPassword(resultSet.getString("password"));

        //System.out.println(personBean);
        return admin;
    }
    public static PreparedStatement initRequestPrepare(Connection connexion, String sql, Object... objets ) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement( sql );
        for ( int i = 0; i < objets.length; i++ ) {
            preparedStatement.setObject( i + 1, objets[i] );
        }
        return preparedStatement;
    }
    public adminBean findByEmail(String email) throws DAOException {
        adminBean admin = null;
        final String SQL_FIND_USER_BY_ID = "SELECT * FROM admin WHERE email = ?";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {

            preparedStatement.setNString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                admin = map(resultSet);

            }

        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la recherche de l'utilisateur par ID.", e);
        }

        return admin;
    }
}
