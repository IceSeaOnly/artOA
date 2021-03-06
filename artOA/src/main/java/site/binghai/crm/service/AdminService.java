package site.binghai.crm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import site.binghai.crm.dao.AdminDao;
import site.binghai.crm.entity.Admin;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by binghai on 2018/1/22.
 *
 * @ artOA
 */
@Service
public class AdminService {
    private static Logger logger = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private AdminDao adminDao;

    /**
     * 验证登录
     */
    public Admin adminLogin(Admin admin) {
        if (admin != null && !StringUtils.isEmpty(admin.getUsername()) && !StringUtils.isEmpty(admin.getPassword())) {
            List<Admin> ls = adminDao.findByPhoneAndPassword(admin.getUsername(), admin.getPassword());
            ls = ls.stream().filter(v -> !v.isDeleted()).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(ls) || ls.size() > 1) {
                logger.error("登录校验失败:{},rs = {}", admin, ls);
                return null;
            }
            return ls.get(0);
        }
        return null;
    }


    public List<Admin> findAll() {
        List<Admin> all = adminDao.findAll();
        return all.stream().filter(v -> !v.isDeleted()).collect(Collectors.toList());
    }

    public Admin findByPhone(String phone) {
        List<Admin> ads = adminDao.findByPhone(phone);
        ads = ads.stream().filter(v -> !v.isDeleted()).collect(Collectors.toList());
        return CollectionUtils.isEmpty(ads) ? null : ads.get(0);
    }

    @Transactional
    public void save(Admin add) {
        adminDao.save(add);
    }

    public Admin findById(Integer id) {
        return adminDao.findOne(id);
    }
}
