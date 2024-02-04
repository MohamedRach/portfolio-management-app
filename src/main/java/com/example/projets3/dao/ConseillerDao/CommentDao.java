package com.example.projets3.dao.ConseillerDao;
import com.example.projets3.bean.CommentBean;
import com.example.projets3.dao.DAOException;
import java.util.ArrayList;
public interface CommentDao {
    public void create (CommentBean comment) throws DAOException;
    public ArrayList<CommentBean> comments(int conseiller_id) throws DAOException;
}
