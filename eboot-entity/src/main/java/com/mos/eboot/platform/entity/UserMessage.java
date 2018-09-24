package com.mos.eboot.platform.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 用户发送消息记录表
 * </p>
 *
 * @author zlb
 * @since 2018-09-22
 */
@TableName("user_message")
public class UserMessage extends Model<UserMessage> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 信息来自ID
     */
	@TableField("from_id")
	private String fromId;
    /**
     * 消息推送ID
     */
	@TableField("to_id")
	private String toId;
    /**
     * 消息类型;(如：0:text、1:image、2:voice、3:vedio、4:music、5:news)
     */
	@TableField("msg_type")
	private Integer msgType;
    /**
     * 聊天类型;(如公聊、私聊)
     */
	@TableField("chat_type")
	private String chatType;
    /**
     * 消息内容
     */
	private String content;
    /**
     * 消息发送到那个群组 ID
     */
	@TableField("group_id")
	private String groupId;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public String getToId() {
		return toId;
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	public String getChatType() {
		return chatType;
	}

	public void setChatType(String chatType) {
		this.chatType = chatType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UserMessage{" +
			", id=" + id +
			", fromId=" + fromId +
			", toId=" + toId +
			", msgType=" + msgType +
			", chatType=" + chatType +
			", content=" + content +
			", groupId=" + groupId +
			", createTime=" + createTime +
			"}";
	}
}
