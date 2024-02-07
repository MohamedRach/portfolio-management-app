package com.example.projets3.dao.adminDao;

import com.example.projets3.bean.adminBean;
import com.example.projets3.dao.DAOException;

public interface adminDao {
    adminBean findByEmail(String email) throws DAOException;
}
