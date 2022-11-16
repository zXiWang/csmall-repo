package cn.tedu.mall.sso.pojo.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AdminAutority implements Serializable, GrantedAuthority {
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 值
     */
    private String value;

    /**
     * 描述
     */
    private String description;

    /**
     * 自定义排序序号
     */
    private Integer sort;

    /**
     * 数据创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */
    private LocalDateTime gmtModified;

    @Override
    public String getAuthority() {
        return value;
    }

    /**
     * 写入数据
     *
     * @return
     */
    private void setAuthority(String authority) {
        this.value = authority;
    }
}
