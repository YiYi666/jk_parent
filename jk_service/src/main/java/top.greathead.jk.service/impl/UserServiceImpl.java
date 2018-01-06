package top.greathead.jk.service.impl;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.greathead.jk.dao.BaseDao;
import top.greathead.jk.entity.LoginLog;
import top.greathead.jk.entity.Role;
import top.greathead.jk.entity.User;
import top.greathead.jk.service.RoleService;
import top.greathead.jk.service.UserService;
import top.greathead.jk.utils.Encrypt;
import top.greathead.jk.utils.MailUtils;
import top.greathead.jk.utils.Pagination;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private BaseDao<User,String> userDao;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MailUtils mailUtils;
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private BaseDao<LoginLog,String> loginLogDao;



    private Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    @Transactional(readOnly = true)
    public Pagination findlist(Pagination page, User user) {
        String hql = "from User where 1=1";
        if(user.getUserInfo().getDegree()==2){
            hql = hql + " and create_dept = '" + user.getDept().getDeptName() +"'";
        }else if(user.getUserInfo().getDegree()==3){
            hql = hql + " and user_name = '" + user.getUserName() +"'";
        }
        page = userDao.pageByHql(hql, page.getPageNo(), page.getPageSize());
        return page;
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(String id) {
        return userDao.get(User.class,id);
    }

    @Override
    public void insert(User model) {
        model.getUserInfo().setUser(model);
        String password = "123456";
        model.setPassword(Encrypt.md5(password,model.getUserName()));
        userDao.save(model);
        String title = "欢迎来到本公司";
        String text = "已成功为你注册用户，用户名/密码："+model.getUserName()+"/"+password;
        if(null!=model.getUserInfo().getEmail()&&!model.getUserInfo().getEmail().isEmpty()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean isOk = false;
                    int num = 0;
                    while (!isOk&&num<3){
                        try {
                            mailUtils.send(title,text,model.getUserInfo().getEmail());
                            System.out.println("邮件发送成功！");
                            isOk = true;
                            num++;
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            logger.error("发送第\""+num+"\"次失败!5秒过后再次尝试。");
                        }
                    }
                }
            }).start();
        }
    }

    @Override
    @Transactional
    public void update(User model) {
        User user = userDao.get(User.class, model.getId());
        user.setDept(model.getDept());
        user.setUserName(model.getUserName());
        user.setState(model.getState());
        user.setUpdateTime(new Date());
        userDao.update(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userDao.getListByHQL("from User");
    }

    @Override
    public void delete(String[] ids) {
        for (String id:ids) {
            userDao.deleteById(User.class,id);
        }
    }

    @Override
    public void updateRoles(String id, String[] roleIds) {
        User user = userDao.get(User.class, id);
        Set<Role> roleSet = new HashSet<Role>();
        for (String roleId:roleIds ) {
            Role role = roleService.findRoleById(roleId);
            roleSet.add(role);
        }
        user.setRoleSet(roleSet);
        userDao.update(user);
    }

    @Override
    public User findUserByUserName(String username) {
        return  userDao.getByHQL("from User where user_name = ?", username);
    }

    @Override
    public List<Object[]> findOnlineInfo() {
        Session session = sessionFactory.openSession();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT b.a1, nvl(countnum , 0) from (SELECT A1 from ONLINE_INFO_T) b\n" +
                "  LEFT JOIN\n" +
                "  (select TO_CHAR(LOGIN_TIME,'hh24') h,count(TO_CHAR(LOGIN_TIME,'hh24')) countnum from LOGIN_LOG_P group by TO_CHAR(LOGIN_TIME,'hh24')) s\n" +
                "  on b.A1 = s.h ORDER BY b.a1");
        List list = sqlQuery.list();
        session.close();
        return list;
    }

    @Override
    public void recordLoginLog(String userName, String ipAddr) {
        LoginLog loginLog = new LoginLog();
        loginLog.setLoginName(userName);
        loginLog.setIpAddress(ipAddr);
        loginLog.setLoginTime(new Date());
        loginLogDao.save(loginLog);
    }
}
