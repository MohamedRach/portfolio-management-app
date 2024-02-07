package com.example.projets3.dao.UserDao;
import java.util.ArrayList;
import java.util.Set;

import com.example.projets3.bean.HiringBean;
import com.example.projets3.bean.UserBean;
import com.example.projets3.dao.DAOException;
public interface HiringDao {
    void create( HiringBean user ) throws DAOException;
    ArrayList<HiringBean> getAllHiring() throws DAOException;
    public Set<Integer> getHiredConseillersIds(int userId) throws DAOException;
}
