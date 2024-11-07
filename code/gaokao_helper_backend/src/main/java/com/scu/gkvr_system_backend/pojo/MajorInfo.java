package com.scu.gkvr_system_backend.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @TableName major_info
 */
@TableName(value = "major_info")
@Data
public class MajorInfo implements Serializable {
    @TableId
    private Integer majorId;

    private String type;

    private String majorName;

    private String majorCode;

    private String level;

    private String years;

    private String degree;

    @Serial
    private static final long serialVersionUID = 1L;
}
