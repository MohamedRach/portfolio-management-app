package com.example.projets3.dao.reportDao;

import com.example.projets3.bean.reportBean;
import com.example.projets3.dao.DAOException;

import java.util.ArrayList;

public interface reportDao {

    public reportBean getReportById(int id) throws DAOException;
    public reportBean getReportByPortfolio(int id_portfolio) throws DAOException;
    public ArrayList<reportBean> getReports(int id_conseiller) throws DAOException;
    public void createReport(reportBean reportBean) throws DAOException;
}
