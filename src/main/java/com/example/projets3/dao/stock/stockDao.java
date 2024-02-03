package com.example.projets3.dao.stock;

import com.example.projets3.bean.stockBean;
import com.example.projets3.dao.DAOException;

import java.util.ArrayList;

public interface stockDao {

    ArrayList<stockBean> getStocks(int portfolio) throws DAOException;
    stockBean getStock(int portfolio, int id) throws DAOException;

    void addStock(stockBean stock) throws DAOException;

    void deleteStock(int id) throws DAOException;
    void updateStock(stockBean stock) throws DAOException;
}
