package site.binghai.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import site.binghai.crm.dao.PlanDao;
import site.binghai.crm.entity.Plan;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by binghai on 2018/1/25.
 *
 * @ artOA
 */
@Service
public class PlanService {

    @Autowired
    private PlanDao planDao;

    @Transactional
    public void save(Plan plan) {
        planDao.save(plan);
    }

    public List<Plan> all() {
        return planDao.findAll().stream()
                .filter(v -> !v.isDeleted())
                .sorted((a, b) -> b.getId() - a.getId()).collect(Collectors.toList());
    }

    public Plan findById(Integer id) {
        return planDao.findOne(id);
    }

    public Plan findByQrCode(String qrCode) {
        List<Plan> t = planDao.findByQrPass(qrCode);
        return CollectionUtils.isEmpty(t) ? null : t.get(0);
    }
}
