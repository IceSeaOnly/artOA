package site.binghai.crm.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by binghai on 2018/1/22.
 *
 * @ artOA
 */
@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String phone;
    private String openId;
    private String qrCode;
    private boolean wxBind;
    private boolean isDeleted;
    @Column(columnDefinition = "TEXT")
    private String info; // 大json串，其他追加字段
}
