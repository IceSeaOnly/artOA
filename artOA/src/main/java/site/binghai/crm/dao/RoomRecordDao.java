package site.binghai.crm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import site.binghai.crm.entity.RoomRecord;

import java.util.List;

/**
 * Created by binghai on 2018/2/4.
 *
 * @ artOA
 */
public interface RoomRecordDao extends JpaRepository<RoomRecord,Integer>{
    List<RoomRecord> findByPlanDetailId(int pdId);
}
