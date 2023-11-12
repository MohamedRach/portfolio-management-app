package com.example.projets3.dao.UserDao;
import com.example.projets3.bean.UserBean;
import com.example.projets3.dao.DAOException;
import com.example.projets3.dao.daoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {

    private daoFactory DAO;

    public UserDaoImpl(daoFactory daoFactory){this.DAO = daoFactory;};
    private static UserBean map( ResultSet resultSet ) throws SQLException {
        UserBean userBean = new UserBean();
        userBean.setId( resultSet.getInt( "id" ) );
        userBean.setNom( resultSet.getString( "nom" ) );
        userBean.setEmail( resultSet.getString( "email" ) );
        userBean.setPrenom( resultSet.getString( "prenom" ) );

        //System.out.println(personBean);
        return userBean;
    }
    public static PreparedStatement initRequestPrepare( Connection connexion, String sql, Object... objets ) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement( sql );
        for ( int i = 0; i < objets.length; i++ ) {
            preparedStatement.setObject( i + 1, objets[i] );
        }
        return preparedStatement;
    }
    @Override
    public ArrayList<UserBean> getAllUsers() throws DAOException {
        final String SQL_SELECT = "SELECT * FROM user";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UserBean userBean = null;
        ArrayList<UserBean> users = new ArrayList<UserBean>();

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initRequestPrepare( connexion, SQL_SELECT);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while ( resultSet.next() ) {

                userBean = map( resultSet );
                System.out.println(resultSet.getString("nom"));
                users.add(userBean);
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        }
        return users;
    }

    @Override
    public UserBean find(int id) throws DAOException {
        return null;
    }

    @Override
    public void create(UserBean user) throws DAOException {
        //hello
    }

    @Override
    public void update(UserBean user) throws DAOException {
        //
    }

    @Override
    public void delete(int id) throws DAOException {
        //
    }
}
