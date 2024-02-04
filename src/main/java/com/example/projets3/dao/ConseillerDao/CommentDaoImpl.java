package com.example.projets3.dao.ConseillerDao;
import com.example.projets3.bean.CommentBean;
import com.example.projets3.dao.ConseillerDao.CommentDao;
import com.example.projets3.dao.DAOException;
import com.example.projets3.dao.daoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class CommentDaoImpl implements CommentDao {
    private daoFactory DAO;
    public CommentDaoImpl(daoFactory daoFactory){this.DAO = daoFactory;}

    public CommentBean map(ResultSet resultSet) throws SQLException {
        CommentBean comment = new CommentBean();
        comment.setId(resultSet.getInt("id"));
        comment.setComment(resultSet.getString("content"));
        comment.setConseiller_id(resultSet.getInt("conseiller_id"));
        comment.setUser_id(resultSet.getInt("user_id"));
        comment.setCreated_at(resultSet.getDate("created_at"));

        return comment;
    }
    public static PreparedStatement initRequestPrepare(Connection connexion, String sql, Object... objets ) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement( sql );
        for ( int i = 0; i < objets.length; i++ ) {
            preparedStatement.setObject( i + 1, objets[i] );
        }
        return preparedStatement;
    }
    public void create(CommentBean comment) throws DAOException {
        final String SQL_INSERT = "INSERT INTO comments (content,conseiller_id, user_id) VALUES (?,?, ?)";

        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = initRequestPrepare(connexion, SQL_INSERT,comment.getComment(), comment.getConseiller_id(),  comment.getUser_id())) {

            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
    @Override
    public ArrayList<CommentBean> comments(int conseiller_id) throws DAOException{
        final String SQL_SELECT = "SELECT * FROM comments where conseiller_id = ?";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CommentBean comment = null;
        ArrayList<CommentBean> comments = new ArrayList<CommentBean>();
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initRequestPrepare( connexion, SQL_SELECT, conseiller_id);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while ( resultSet.next() ) {

                comment = map( resultSet );
                comments.add(comment);
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        }
        return comments;
    }
}
