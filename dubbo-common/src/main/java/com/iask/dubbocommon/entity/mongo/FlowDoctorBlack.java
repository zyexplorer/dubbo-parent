package com.iask.dubbocommon.entity.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @描述: 流量市场任务黑名单医生
 * @版权: Copyright (c) 2017
 * @作者: xiongwei
 * @创建日期: 2017年10月11日
 * @创建时间: 下午15:52:28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "dr.flow.black")
public class FlowDoctorBlack implements Serializable {

	private static final long serialVersionUID = -2769682774922466612L;
	private String id;
	private String publishDocId; 				// 发布医生ID
	private String publishDocName; 				// 发布医生姓名
	private String disabledDocId; 				// 被拉黑医生ID
	private String disabledDocName; 			// 被拉黑医生名称
	private Date blackTime;						// 拉黑时间
	private String taskDoctorId; 				// 医生任务id
}
