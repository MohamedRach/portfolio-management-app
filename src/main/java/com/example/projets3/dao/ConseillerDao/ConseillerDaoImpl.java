package com.example.projets3.dao.ConseillerDao;
import com.example.projets3.bean.ConseillerBean;
import com.example.projets3.bean.UserBean;
import com.example.projets3.dao.DAOException;
import com.example.projets3.dao.daoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
// ConseillerDaoImpl.java
public class ConseillerDaoImpl implements ConseillerDao {

    private daoFactory DAO;

    public ConseillerDaoImpl(daoFactory daoFactory) {
        this.DAO = daoFactory;
    }

    private static ConseillerBean map(ResultSet resultSet) throws SQLException {
        ConseillerBean conseillerBean = new ConseillerBean();
        conseillerBean.setId(resultSet.getInt("id"));
        conseillerBean.setNom(resultSet.getString("nom"));
        conseillerBean.setEmail(resultSet.getString("email"));
        conseillerBean.setPrenom(resultSet.getString("prenom"));
        conseillerBean.setImageLink(resultSet.getString("imagelink"));
        conseillerBean.setDescription(resultSet.getString("description"));
        conseillerBean.setRating(resultSet.getInt("rating"));
        conseillerBean.setHired(resultSet.getInt("hired"));
        conseillerBean.setHourly_Rate(resultSet.getInt("Hourly_Rate"));
        conseillerBean.setPostive_Reviews(resultSet.getInt("Postive_Reviews"));
        conseillerBean.setRehired(resultSet.getInt("Rehired"));

        conseillerBean.setPassword(resultSet.getString("password"));

        return conseillerBean;
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
    public ConseillerBean find(String email) throws DAOException {
        ConseillerBean conseiller = null;
        final String SQL_FIND_CONSEILLER_BY_ID = "SELECT * FROM conseiller WHERE email = ?";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_CONSEILLER_BY_ID)) {

            preparedStatement.setNString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                conseiller = map(resultSet);
            }

        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la recherche du conseiller par ID.", e);
        }

        return conseiller;
    }

    @Override
    public ArrayList<ConseillerBean> getAllConseillers() throws DAOException {
        final String SQL_SELECT = "SELECT * FROM conseiller";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ConseillerBean conseillerBean = null;
        ArrayList<ConseillerBean> conseillers = new ArrayList<ConseillerBean>();

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initRequestPrepare(connexion, SQL_SELECT);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                conseillerBean = map(resultSet);

                conseillers.add(conseillerBean);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return conseillers;
    }

    @Override
    public void create(ConseillerBean conseiller) throws DAOException {

    }

    @Override
    public void update(ConseillerBean conseiller) throws DAOException {

    }

    @Override
    public void delete(int id) throws DAOException {

    }

    @Override
    public ConseillerBean findConseiller(int id) throws DAOException {
        ConseillerBean conseiller = null;
        final String SQL_FIND_CONSEILLER_BY_ID = "SELECT * FROM conseiller WHERE id = ?";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_CONSEILLER_BY_ID)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                conseiller = map(resultSet);
            }

        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la recherche du conseiller par ID.", e);
        }

        return conseiller;
    }

    @Override
    public void createConseiller(ConseillerBean conseiller) throws DAOException {
        final String SQL_INSERT = "INSERT INTO conseiller (nom, prenom, email, password,description, imageLink) VALUES (?, ?, ?, ?, ? , ?)";

        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = initRequestPrepare(connexion, SQL_INSERT, conseiller.getNom(),
                     conseiller.getPrenom(), conseiller.getEmail(), conseiller.getPassword(), conseiller.getDescription() , conseiller.getImageLink())) {

            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Échec de la création du conseiller, aucune ligne ajoutée dans la table.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void updateConseiller(ConseillerBean conseiller) throws DAOException {
        final String SQL_UPDATE = "UPDATE conseiller SET nom = ?, prenom = ?, email = ? WHERE id = ?";

        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement preparedStatement = initRequestPrepare(connexion, SQL_UPDATE, conseiller.getNom(),
                     conseiller.getPrenom(), conseiller.getEmail(), conseiller.getId())) {

            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Échec de la mise à jour du conseiller, aucune ligne modifiée dans la table.");
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la mise à jour du conseiller.", e);
        }
    }

    @Override
    public void deleteConseiller(int id) throws DAOException {
        final String SQL_DELETE = "DELETE FROM conseiller WHERE id = ?";

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
    @Override
    public ConseillerBean findByEmail(String email) throws DAOException {
        ConseillerBean conseiller = null;
        final String SQL_FIND_CONSEILLER_BY_ID = "SELECT * FROM conseiller WHERE email = ?";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_CONSEILLER_BY_ID)) {

            preparedStatement.setNString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                conseiller = new ConseillerBean();
                conseiller.setId(resultSet.getInt("id"));
                conseiller.setNom(resultSet.getString("nom"));
                conseiller.setPrenom(resultSet.getString("prenom"));
                conseiller.setEmail(resultSet.getString("email"));
                conseiller.setPassword(resultSet.getString("password"));
                // Ajoutez les autres propriétés de l'utilisateur si nécessaire
            }

        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la recherche de l'utilisateur par ID.", e);
        }

        return conseiller;
    }
}

