package com.example.projets3.dao.hiringDao;

import com.example.projets3.bean.HiringBean;
import com.example.projets3.dao.DAOException;

import java.util.ArrayList;

public interface hiringDao {
    ArrayList<HiringBean> getAllClients(int id_conseiller) throws DAOException;
}
