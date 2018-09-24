/**
 * 
 */
package com.mos.eboot.im.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.jim.common.Const;
import org.jim.common.ImPacket;
import org.jim.common.ImSessionContext;
import org.jim.common.ImStatus;
import org.jim.common.http.HttpConst;
import org.jim.common.packets.Command;
import org.jim.common.packets.Group;
import org.jim.common.packets.LoginReqBody;
import org.jim.common.packets.LoginRespBody;
import org.jim.common.packets.User;
import org.jim.common.session.id.impl.UUIDSessionIdGenerator;
import org.jim.common.utils.JsonKit;
import org.jim.common.utils.Md5;
import org.jim.server.command.CommandManager;
import org.jim.server.command.handler.JoinGroupReqHandler;
import org.jim.server.command.handler.processor.login.LoginProcessorIntf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.tio.core.ChannelContext;

import com.mos.eboot.im.api.IUserInfoService;
import com.mos.eboot.platform.entity.UserInfo;

import cn.hutool.core.util.RandomUtil;

/**
 * 
* @ClassName: LoginServiceProcessor 
* @Description: TODO(登录业务处理类) 
* @author Mr.zhou 
* @date 2018年9月22日 下午3:10:23
 */
@Component
public class LoginServiceProcessor implements LoginProcessorIntf{
	private static final String passwordKey="{bcrypt}$2a$10$C/TJe8I4IRsUvmnTfSU39uPNyLRyfGDQw/dXrYRCWrVNZewWIO9dS";
	private static IUserInfoService userInfoService;

	@Autowired
	public void setWxgzBuriedFileFacade(IUserInfoService userInfoService) {
		LoginServiceProcessor.userInfoService = userInfoService;
	}
	private Logger logger = LoggerFactory.getLogger(LoginServiceProcessor.class);

	public static final Map<String, User> tokenMap = new HashMap<>();
	/**
	 * 根据用户名和密码获取用户
	 * @param loginname
	 * @param password
	 * @return
	 * @author: WChao
	 */
	public User getUser(String loginname, String password,UserInfo userinfo) {
		String text = loginname+password;
		String key = Const.authkey;
		String token = Md5.sign(text, key, HttpConst.CHARSET_NAME);
		User user = getUser(token,userinfo);
		user.setId(loginname);
		return user;
	}
	/**
	 * 根据token获取用户信息
	 * @param token
	 * @return
	 * @author: WChao
	 */
	public User getUser(String token,UserInfo userinfo) {
		//demo中用map，生产环境需要用cache
		User user = tokenMap.get(token);
		if (user == null) {
			user = new User();
			user.setId(userinfo.getLoginAccount());
			user.setNick(userinfo.getNick());
			user.setGroups(initGroups(user));
			user.setFriends(initFriends(user));
			user.setAvatar(nextImg());
			
			if (tokenMap.size() > 10000) {
				tokenMap.clear();
			}
			tokenMap.put(token, user);
		}
		return user;
	}
	
	public List<Group> initGroups(User user){
		//模拟的群组;正式根据业务去查数据库或者缓存;
		List<Group> groups = new ArrayList<Group>();
		groups.add(new Group("100","J-IM朋友圈"));
		return groups;
	}
	
	public List<Group> initFriends(User user){
		List<Group> friends = new ArrayList<Group>();
		List<User> myFriendGroupUsers = new ArrayList<User>();
		Group myFriend = new Group("1","我的好友");
		UserInfo user2=new UserInfo();
		user2.setIsDel(1);
		List<UserInfo> userinfoList=userInfoService.all(user2);
		if(userinfoList.size()>0) {
			for (UserInfo userInfo : userinfoList) {
				User user1 = new User();
				user1.setId(userInfo.getId().toString());
				user1.setNick(userInfo.getNick());
				user1.setAvatar(nextImg());
				myFriendGroupUsers.add(user1);
			}
		}
		myFriend.setUsers(myFriendGroupUsers);
		friends.add(myFriend);
		return friends;
	}
	
	public String nextImg() {
		return ImgMnService.nextImg();
	}

	public String newToken() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 * doLogin方法注意：J-IM登陆命令是根据user是否为空判断是否登陆成功,
	 * 
	 * 当登陆失败时设置user属性需要为空，相反登陆成功user属性是必须非空的;
	 */
	@Override
	public LoginRespBody doLogin(LoginReqBody loginReqBody , ChannelContext channelContext) {
		String loginname = loginReqBody.getLoginname();
		String password = loginReqBody.getPassword();
		logger.info("-------------》根据用户名字获取微服务");
		UserInfo userInfo =userInfoService.getByUsername(loginname);
		if(userInfo!=null) {
			//对密码进行加密
			String encode = Md5.sign(password, passwordKey, HttpConst.CHARSET_NAME);
			if(userInfo.getLoginPassword().equals(encode)) {
				ImSessionContext imSessionContext = (ImSessionContext)channelContext.getAttribute();
				String handshakeToken = imSessionContext.getToken();
				User user = new User();
				LoginRespBody loginRespBody;
				if (!StringUtils.isBlank(handshakeToken)) {
					user = this.getUser(handshakeToken,userInfo);
				}else{
//					user.setId(userInfo.getId().toString());
//					user.setAvatar(userInfo.getUserAvatar());
//					//获取用户的好友列表：需要去数据库里面查询
//					user.setFriends(initGroups(user));
//					//获取群组：也需要去查
//					user.setGroups(initFriends(user));
//					user.setNick(userInfo.getNick());
//					user.setSign(userInfo.getUserSign());
//					user.setStatus(userInfo.getUserStatus());
//					user.setTerminal(userInfo.getUserTerminal());
					user = this.getUser(loginname, password,userInfo);
				}
				if(user == null){
					loginRespBody = new LoginRespBody(Command.COMMAND_LOGIN_RESP,ImStatus.C10008);
				}else{
					loginRespBody = new LoginRespBody(Command.COMMAND_LOGIN_RESP,ImStatus.C10007,user);
				}
				return loginRespBody;
			}else {
				logger.error("密码错误");
			}
		}else {
			logger.error("用户名错误");
		}
		return null;
	}

	@Override
	public void onSuccess(ChannelContext channelContext) {
		logger.info("登录成功回调方法");
		ImSessionContext imSessionContext = (ImSessionContext)channelContext.getAttribute();
		User user = imSessionContext.getClient().getUser();
		if(user.getGroups() != null){
			for(Group group : user.getGroups()){//发送加入群组通知
				ImPacket groupPacket = new ImPacket(Command.COMMAND_JOIN_GROUP_REQ,JsonKit.toJsonBytes(group));
				try {
					JoinGroupReqHandler joinGroupReqHandler = CommandManager.getCommand(Command.COMMAND_JOIN_GROUP_REQ, JoinGroupReqHandler.class);
					joinGroupReqHandler.joinGroupNotify(groupPacket, channelContext);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public boolean isProtocol(ChannelContext channelContext) {
		 
		return true;
	}

	@Override
	public String name() {
		
		return "default";
	}
	public static void main(String[] args) {
		//对密码进行加密
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encode = passwordEncoder.encode("1");
		System.out.println(encode);
	}
}
