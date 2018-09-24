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
 * 用户信息表
 * </p>
 *
 * @author zlb
 * @since 2018-09-20
 */
@TableName("user_info")
public class UserInfo extends Model<UserInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 用户昵称
     */
	@TableField("nick")
	private String nick;
    /**
     * 用户登录账户
     */
	@TableField("login_account")
	private String loginAccount;
    /**
     * 用户登录密码
     */
	@TableField("login_password")
	private String loginPassword;
    /**
     * 用户号码
     */
	@TableField("user_phone")
	private String userPhone;
    /**
     * 用户邮箱
     */
	@TableField("user_mail")
	private String userMail;
    /**
     * 用户性别：1男 2女
     */
	@TableField("user_sex")
	private Integer userSex;
    /**
     * 在线状态(online、offline)
     */
	@TableField("user_status")
	private String userStatus;
    /**
     * 用户图像
     */
	@TableField("user_avatar")
	private String userAvatar;
    /**
     * 用户个性签名
     */
	@TableField("user_sign")
	private String userSign;
    /**
     * 用户所属终端
     */
	@TableField("user_terminal")
	private String userTerminal;
    /**
     * 预留字段
     */
	@TableField("extras")
	private String extras;
    /**
     * 创建时间
     */
	@TableField("creation_time")
	private Date creationTime;
    /**
     * 修改时间
     */
	@TableField("update_time")
	private Date updateTime;
    /**
     * 是否删除 1否 2是
     */
	@TableField("is_del")
	private Integer isDel;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public Integer getUserSex() {
		return userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public String getUserSign() {
		return userSign;
	}

	public void setUserSign(String userSign) {
		this.userSign = userSign;
	}

	public String getUserTerminal() {
		return userTerminal;
	}

	public void setUserTerminal(String userTerminal) {
		this.userTerminal = userTerminal;
	}

	public String getExtras() {
		return extras;
	}

	public void setExtras(String extras) {
		this.extras = extras;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UserInfo{" +
			", id=" + id +
			", nick=" + nick +
			", loginAccount=" + loginAccount +
			", loginPassword=" + loginPassword +
			", userPhone=" + userPhone +
			", userMail=" + userMail +
			", userSex=" + userSex +
			", userStatus=" + userStatus +
			", userAvatar=" + userAvatar +
			", userSign=" + userSign +
			", userTerminal=" + userTerminal +
			", extras=" + extras +
			", creationTime=" + creationTime +
			", updateTime=" + updateTime +
			", isDel=" + isDel +
			"}";
	}
}
