package com.example.projets3.dao.UserDao;

import java.util.ArrayList;
import com.example.projets3.bean.UserBean;
import com.example.projets3.dao.DAOException;

public interface UserDao {
    void create( UserBean user ) throws DAOException;
    void update(UserBean user) throws DAOException;
    void delete(int id) throws DAOException;
    UserBean find( int id ) throws DAOException;
    ArrayList<UserBean> getAllUsers() throws DAOException;
}
