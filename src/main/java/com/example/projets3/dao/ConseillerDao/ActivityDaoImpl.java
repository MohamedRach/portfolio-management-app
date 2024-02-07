package com.example.projets3.dao.ConseillerDao;
import com.example.projets3.bean.ActivityBean;
import com.example.projets3.bean.CommentBean;
import com.example.projets3.bean.ConseillerBean;
import com.example.projets3.dao.ConseillerDao.ActivityDao;
import com.example.projets3.dao.DAOException;
import com.example.projets3.dao.daoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ActivityDaoImpl implements ActivityDao {
    private daoFactory DAO ;
    public ActivityDaoImpl(daoFactory daoFactory){this.DAO = daoFactory; }
    public ActivityBean map(ResultSet resultSet) throws SQLException {
        ActivityBean activity = new ActivityBean();
        activity.setId(resultSet.getInt("id"));
        activity.setId_conseiller(resultSet.getInt("id_conseiller"));
        activity.setId_user(resultSet.getInt("id_user"));
        activity.setDesc_buy(resultSet.getString("desc_buy"));
        activity.setDesc_sell(resultSet.getString("desc_sell"));

        return activity;
    }
    public static PreparedStatement initRequestPrepare(Connection connexion, String sql, Object... objets ) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement( sql );
        for ( int i = 0; i < objets.length; i++ ) {
            preparedStatement.setObject( i + 1, objets[i] );
        }
        return preparedStatement;
    }
    public void create(ActivityBean activity) throws DAOException {
        final String SQL_INSERT = "INSERT INTO activity (id_conseiller,id_user, desc_buy ,desc_sell) VALUES (?,?, ?, ?)";

        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = initRequestPrepare(connexion, SQL_INSERT,activity.getId_conseiller(), activity.getId_user(),  activity.getDesc_buy() , activity.getDesc_sell())) {

            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Échec de la création de l'activity, aucune ligne ajoutée dans la table.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
    @Override
    public ArrayList<ActivityBean> activity(int conseiller_id) throws DAOException{
        final String SQL_SELECT = "SELECT * FROM activity where conseiller_id = ?";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ActivityBean activity = null;
        ArrayList<ActivityBean> activitys = new ArrayList<ActivityBean>();
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initRequestPrepare( connexion, SQL_SELECT, conseiller_id);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while ( resultSet.next() ) {

                activity = map( resultSet );
                activitys.add(activity);
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        }
        return activitys;
    }
    @Override
    public void updateActivity(ActivityBean activity) throws DAOException {
        final String SQL_UPDATE = "UPDATE activity SET desc_buy = ?, desc_sell = ? WHERE id = ?";

        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = initRequestPrepare(connexion, SQL_UPDATE, activity.getDesc_buy() , activity.getDesc_sell(), activity.getId_user())) {

            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Échec de la mise à jour du activity, aucune ligne modifiée dans la table.");
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la mise à jour du activity.", e);
        }
    }

    @Override
    public void deleteActivity(int id) throws DAOException {
        final String SQL_DELETE = "DELETE FROM activity WHERE id = ?";

        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = initRequestPrepare(connexion, SQL_DELETE, id)) {

            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Échec de la suppression du conseiller, aucune ligne supprimée dans la table.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
    public ArrayList<ActivityBean> getAllActivity() throws DAOException {
        final String SQL_SELECT = "SELECT * FROM activity";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ActivityBean activityBean = null;
        ArrayList<ActivityBean> activities = new ArrayList<ActivityBean>();

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initRequestPrepare(connexion, SQL_SELECT);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                activityBean = map(resultSet);

                activities.add(activityBean);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return activities;
    }
}
