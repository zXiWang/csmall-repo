package cn.tedu.mall.pojo.ums.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户登录日志表
 * </p>
 *
 * @author tedu.cn
 * @since 2022-02-22
 */
@Data
public class UserLoginLog implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名（冗余）
     */
    private String username;
    /**
     * 昵称（冗余）
     */
    private String nickname;

    /**
     * 登录IP地址
     */
    private String ip;
    /**
     * 浏览器内核
     */
    private String userAgent;
    /**
     * 登录时间
     */
    private LocalDateTime gmtLogin;
    /**
     * 数据创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 数据最后修改时间
     */
    private LocalDateTime gmtModified;


}
