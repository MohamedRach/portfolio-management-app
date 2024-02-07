package com.example.projets3.dao.UserDao;

import com.example.projets3.bean.HiringBean;
import com.example.projets3.bean.UserBean;
import com.example.projets3.dao.daoFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.projets3.dao.DAOException;
import com.example.projets3.dao.daoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class HiringDaoImpl implements HiringDao {

    private daoFactory DAO;
    public HiringDaoImpl(daoFactory daoFactory){this.DAO = daoFactory;};
    private static HiringBean map(ResultSet resultSet ) throws SQLException {
        HiringBean hiringBean = new HiringBean();
        hiringBean.setId( resultSet.getInt( "id" ) );
        hiringBean.setId_conseiller( resultSet.getInt( "id_conseiller" ) );
        hiringBean.setId_user( resultSet.getInt( "id_user" ) );


        //System.out.println(personBean);
        return hiringBean;
    }



    public static PreparedStatement initRequestPrepare( Connection connexion, String sql, Object... objets ) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement( sql );
        for ( int i = 0; i < objets.length; i++ ) {
            preparedStatement.setObject( i + 1, objets[i] );
        }
        return preparedStatement;
    }
    @Override
    public ArrayList<HiringBean> getAllHiring() throws DAOException {
        final String SQL_SELECT = "SELECT * FROM hiring";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HiringBean hiringBean = null;
        ArrayList<HiringBean> hirings = new ArrayList<HiringBean>();

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initRequestPrepare( connexion, SQL_SELECT);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while ( resultSet.next() ) {

                hiringBean = map( resultSet );

                hirings.add(hiringBean);
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        }
        return hirings;
    }
    public void create(HiringBean hiring) throws DAOException {
        final String SQL_INSERT = "INSERT INTO hiring (id_user, id_conseiller) VALUES (?, ?)";

        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = initRequestPrepare(connexion, SQL_INSERT, hiring.getId_user(), hiring.getId_conseiller())) {

            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Échec du hiring, aucune ligne ajoutée dans la table.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
    @Override
    public Set<Integer> getHiredConseillersIds(int id_user) throws DAOException {
        final String SQL_SELECT = "SELECT id_conseiller FROM hiring WHERE id_user = ?";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Set<Integer> hiredConseillersIds = new HashSet<>();

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = HiringDaoImpl.initRequestPrepare(connexion, SQL_SELECT, id_user);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int conseillerId = resultSet.getInt("id_conseiller");
                hiredConseillersIds.add(conseillerId);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // Fermeture des ressources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connexion != null) connexion.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }

        return hiredConseillersIds;
    }

}
