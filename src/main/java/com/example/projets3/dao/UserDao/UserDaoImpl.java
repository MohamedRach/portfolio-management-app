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
// UserDaoImpl.java

    public UserBean find(int id) throws DAOException {
        UserBean user = null;
        final String SQL_FIND_USER_BY_ID = "SELECT * FROM user WHERE id = ?";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new UserBean();
                user.setId(resultSet.getInt("id"));
                user.setNom(resultSet.getString("nom"));
                user.setPrenom(resultSet.getString("prenom"));
                user.setEmail(resultSet.getString("email"));
                // Ajoutez les autres propriétés de l'utilisateur si nécessaire
            }

        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la recherche de l'utilisateur par ID.", e);
        }

        return user;
    }


    @Override
    public void create(UserBean user) throws DAOException {
        final String SQL_INSERT = "INSERT INTO user (nom, prenom, email, password) VALUES (?, ?, ?, ?)";

        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = initRequestPrepare(connexion, SQL_INSERT, user.getNom(), user.getPrenom(), user.getEmail(), user.getPassword())) {

            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(UserBean user) throws DAOException {
        final String SQL_UPDATE = "UPDATE user SET nom = ?, prenom = ?, email = ? WHERE id = ?";

        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = initRequestPrepare(connexion, SQL_UPDATE, user.getNom(), user.getPrenom(), user.getEmail(), user.getId())) {

            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Échec de la mise à jour de l'utilisateur, aucune ligne modifiée dans la table.");
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la mise à jour de l'utilisateur.", e);
        }
    }


    @Override
    public void delete(int id) throws DAOException {
        final String SQL_DELETE = "DELETE FROM user WHERE id = ?";

        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = initRequestPrepare(connexion, SQL_DELETE, id)) {

            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Échec de la suppression de l'utilisateur, aucune ligne supprimée dans la table.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public UserBean findByEmail(String email) throws DAOException {
        UserBean user = null;
        final String SQL_FIND_USER_BY_ID = "SELECT * FROM user WHERE email = ?";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {

            preparedStatement.setNString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new UserBean();
                user.setId(resultSet.getInt("id"));
                user.setNom(resultSet.getString("nom"));
                user.setPrenom(resultSet.getString("prenom"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                // Ajoutez les autres propriétés de l'utilisateur si nécessaire
            }

        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la recherche de l'utilisateur par ID.", e);
        }

        return user;
    }
}
