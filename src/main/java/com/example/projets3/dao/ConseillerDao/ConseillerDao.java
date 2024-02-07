package com.example.projets3.dao.ConseillerDao;
import java.util.ArrayList;
import com.example.projets3.bean.ConseillerBean;
import com.example.projets3.bean.UserBean;
import com.example.projets3.dao.DAOException;
public interface ConseillerDao{
        ConseillerBean find(int id) throws DAOException;
         ArrayList<ConseillerBean> getAllConseillers() throws DAOException;
        void create(ConseillerBean conseiller) throws DAOException;
        void update(ConseillerBean conseiller) throws DAOException;
        void delete(int id) throws DAOException;

    ConseillerBean findConseiller(int id) throws DAOException;
    ConseillerBean findByEmail(String email) throws DAOException;

    void createConseiller(ConseillerBean conseiller) throws DAOException;

    void updateConseiller(ConseillerBean conseiller) throws DAOException;

    void deleteConseiller(int id) throws DAOException;
}
