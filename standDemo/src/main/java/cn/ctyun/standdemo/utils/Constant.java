package cn.ctyun.standdemo.utils;

public class Constant {

	public static final Integer DELSTATUS = 9999; // 表示逻辑删除
	public static final Integer STATUS = 0; // 表示未删除

	/**
	 * 工单完成操作
	 */
	public static final String ISCOMPLETE = "1";

	// ////////////// 工单常量 start ///////////////////////////////////////////
	// 问题工单用户类型
//	public static final Integer WORK_ORDER_LIST_TYPE_DEFAULT = 0; // 工单列表类型  未领取或未接受
//	public static final Integer WORK_ORDER_LIST_TYPE_PROCESSING = 1; // 工单列表类型  处理中
//	public static final Integer WORK_ORDER_LIST_TYPE_CLOSE = 2; // 工单列表类型  已关闭
	//附件
	public static final Integer PROBLEM_ACCESSORY_IMG = 0; // 图片附件
	public static final Integer PROBLEM_ACCESSORY_FILE = 1; // 文件附件
	// 问题工单用户类型
	/** admin */
	public static final Integer WORK_ORDER_USER_TYPE_ADMIN = 3; // admin
	/** 客服 */
	public static final Integer WORK_ORDER_USER_TYPE_SERVICE = 0; // 客服
	/** 技术人员 */
	public static final Integer WORK_ORDER_USER_TYPE_TECHNOLOGY = 1; // 技术人员
	/** 客户 */
	public static final Integer WORK_ORDER_USER_TYPE_CUSTOMER = 2; // 客户
	// 问题回复类型
	/** 解决中 */
	public static final Integer PROBLEM_REPLY_TYPE_SOLVE = 0; // 解决中
	/** 已解决 */
	public static final Integer PROBLEM_REPLY_TYPE_RESOLVED = 1; // 已解决
	// 问题添加类型
	/** 新问题 */
	public static final int PROBLEM_ADD_TYPE_DEFAULT = 0; // 新问题
	/** 追加问题 */
	public static final int PROBLEM_ADD_TYPE_ADD = 1; // 追加问题
	// 工单类型
	/** 问题工单 */
	public static final Integer WORK_ORDER_TYPE_PROBLEM = 0; // 问题工单
	/** 其它工单 */
	public static final Integer WORK_ORDER_TYPE_OTHER = 1; // 其它工单
	// 工单级别
	public static final Integer WORK_ORDER_RANK_DEFAULT = 0; // 默认
	public static final Integer WORK_ORDER_RANK_0 = -1; // 低
	public static final Integer WORK_ORDER_RANK_1 = 0; // 一般
	public static final Integer WORK_ORDER_RANK_2 = 1; // 紧急
	public static final Integer WORK_ORDER_RANK_3 = 2; // 特急
	// 工单状态
	/** 默认工单状态, 客服的未领取，技术的未分配 */
	public static final Integer WORK_ORDER_STATUS_DEFAULT = 0; // 默认 未领取和未分配
	/** 指派和未接受, 客服的指派，技术的未接受 */
	public static final Integer WORK_ORDER_STATUS_DESIGNATE = 1; // 指派和未接受
	/** 处理中 */
	public static final Integer WORK_ORDER_STATUS_PROCESSING = 2; // 处理中
	/** 流转 */
	public static final Integer WORK_ORDER_STATUS_MOVING = 3; // 流转
	/** 完成 */
	public static final Integer WORK_ORDER_STATUS_COMPLETE = 4; // 完成
	/** 关闭 */
	public static final Integer WORK_ORDER_STATUS_CLOSE = 5; // 关闭
	/** 自动关闭 */
	public static final Integer WORK_ORDER_STATUS_FORCE_CLOSE = 6; // 关闭
	/** 取消 */
	public static final Integer WORK_ORDER_STATUS_CANCEL = 7; // 取消
	/** 追加问题 */
	public static final Integer WORK_ORDER_STATUS_ADD_PROBLEM = 10; // 追加问题
	// 工单短信提醒类型
	/** 指派 */
	public static final Integer WORK_ORDER_REMIND_TYPE_DESIGNATE = 0; // 指派
	/** 接受 */
	public static final Integer WORK_ORDER_REMIND_TYPE_ACCEPT = 1; // 接受
	/** 流转 */
	public static final Integer WORK_ORDER_REMIND_TYPE_MOVING = 2; // 流转
	/** 完成 */
	public static final Integer WORK_ORDER_REMIND_TYPE_COMPLETE = 3; // 完成
	/** 催办 */
	public static final Integer WORK_ORDER_REMIND_TYPE_URGE = 4; // 催办
	
	/** 编号前缀 */
	public static final String WORK_ORDER_NO_PREFIX = "yjs";
	/** 问题工单编号中间部分 */
	public static final String WORK_ORDER_NO_CENTRE_PROBLEM = "wtgd";
	/** 其它工单编号中间部分 */
	public static final String WORK_ORDER_NO_CENTRE_OTHER = "qtgd";
	// ////////////// 工单常量 end /////////////////////////////////////////
	
	
	// ////////////// 质检 start /////////////////////////////////////////
	/** 父节点ID */
	public static final String CODE_INFO_PARENT_ID_START = "-1";
	/** 质检未通过 **/
	public static final Integer QUALITY_IS_NOT_QUALIFIED = 0;
	/** 质检通过 **/
	public static final Integer QUALITY_IS_QUALIFIED = 1;
	/** 没有更改过工单类型**/
	public static final Integer QUALITY_IS_NOT_UPDATE_ORDERTYPE = 0;
	/** 更改过工单类型**/
	public static final Integer QUALITY_IS_UPDATE_ORDERTYPE = 1;
	/** 没有回访**/
	public static final Integer QUALITY_IS_NOT_RETURNVISIT = 0;
	/** 回访**/
	public static final Integer QUALITY_IS_RETURNVISIT = 1;
	// ////////////// 质检 end /////////////////////////////////////////

}
