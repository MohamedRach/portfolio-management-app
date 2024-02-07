package com.example.projets3.dao.ConseillerDao;
import com.example.projets3.bean.ActivityBean;
import com.example.projets3.bean.ConseillerBean;
import com.example.projets3.dao.DAOException;
import java.util.ArrayList;
public interface ActivityDao
{
    public void create (ActivityBean activity) throws DAOException;
    public ArrayList<ActivityBean> activity(int conseiller_id) throws DAOException;
    ArrayList<ActivityBean> getAllActivity() throws DAOException;
    void updateActivity(ActivityBean activity) throws DAOException;

    void deleteActivity(int id) throws DAOException;
}
