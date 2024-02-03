package com.example.projets3.dao.portfolio;

import com.example.projets3.bean.portfolioBean;
import com.example.projets3.dao.DAOException;


import java.util.ArrayList;

public interface portfolioDao {

    ArrayList<portfolioBean> getPortfolios(int user_id) throws DAOException;
    portfolioBean getPortfolio(int id, int user_id) throws DAOException;

    void createPortfolio(portfolioBean portfolio) throws DAOException;
}
