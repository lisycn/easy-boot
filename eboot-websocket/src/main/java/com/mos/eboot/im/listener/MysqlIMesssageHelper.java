package com.mos.eboot.im.listener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jim.common.Const;
import org.jim.common.http.HttpConst;
import org.jim.common.listener.ImBindListener;
import org.jim.common.message.IMesssageHelper;
import org.jim.common.packets.ChatBody;
import org.jim.common.packets.Group;
import org.jim.common.packets.User;
import org.jim.common.packets.UserMessageData;
import org.jim.common.utils.Md5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tio.core.ChannelContext;

import com.mos.eboot.im.api.IUserInfoService;
import com.mos.eboot.im.api.IUserMessageService;
import com.mos.eboot.im.service.ImgMnService;
import com.mos.eboot.im.service.LoginServiceProcessor;
import com.mos.eboot.platform.entity.UserInfo;
import com.mos.eboot.platform.entity.UserMessage;
/**
 * 
* @ClassName: MysqlIMesssageHelper 
* @Description: TODO(mysql 消息持久化助手，消息同步) 
* @author Mr.zhou 
* @date 2018年9月22日 下午3:23:00
 */
@Component
public class MysqlIMesssageHelper implements IMesssageHelper,Const{
	private static IUserMessageService userMessageService;

	@Autowired
	public void setWxgzBuriedFileFacade(IUserMessageService userMessageService) {
		MysqlIMesssageHelper.userMessageService = userMessageService;
	}
	private static IUserInfoService userInfoService;

	@Autowired
	public void setWxgzBuriedFileFacade(IUserInfoService userInfoService) {
		MysqlIMesssageHelper.userInfoService = userInfoService;
	}
	public static final Map<String, User> tokenMap = new HashMap<>();
	private Logger logger = LoggerFactory.getLogger(LoginServiceProcessor.class);
	@Override
	public ImBindListener getBindListener() {
		logger.info("11");
		return new ImBindListener() {
			
			@Override
			public void onAfterUserUnbind(ChannelContext channelContext, String userid) throws Exception {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAfterUserBind(ChannelContext channelContext, String userid) throws Exception {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAfterGroupUnbind(ChannelContext channelContext, String group) throws Exception {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAfterGroupBind(ChannelContext channelContext, String group) throws Exception {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void initUserTerminal(ChannelContext channelContext, String terminal, String status) {
				// TODO Auto-generated method stub
				
			}
		};
	}
	/**
	 * 判断一个用户是否在线
	 */
	@Override
	public boolean isOnline(String userid) {
		// TODO Auto-generated method stub
		logger.info("12");
		return true;
	}

	@Override
	public Group getGroupUsers(String group_id, Integer type) {
		logger.info("123");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group> getAllGroupUsers(String user_id, Integer type) {
		logger.info("124");
		List<Group> friends = new ArrayList<Group>();
		List<User> myFriendGroupUsers = new ArrayList<User>();
		Group myFriend = new Group("1","我的好友");
		UserInfo user2=new UserInfo();
		user2.setIsDel(1);
		List<UserInfo> userinfoList=userInfoService.all(user2);
		if(userinfoList.size()>0) {
			for (UserInfo userInfo : userinfoList) {
				User user1 = new User();
				user1.setId(userInfo.getLoginAccount());
				user1.setNick(userInfo.getNick());
				user1.setAvatar(nextImg());
				myFriendGroupUsers.add(user1);
			}
		}
		myFriend.setUsers(myFriendGroupUsers);
		friends.add(myFriend);
		return friends;
	}

	@Override
	public Group getFriendUsers(String user_id, String friend_group_id, Integer type) {
		logger.info("125");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group> getAllFriendUsers(String user_id, Integer type) {
		logger.info("126");
			List<Group> friends = new ArrayList<Group>();
			List<User> myFriendGroupUsers = new ArrayList<User>();
			Group myFriend = new Group("1","我的好友");
			UserInfo user2=new UserInfo();
			user2.setIsDel(1);
			List<UserInfo> userinfoList=userInfoService.all(user2);
			if(userinfoList.size()>0) {
				for (UserInfo userInfo : userinfoList) {
					User user1 = new User();
					user1.setId(userInfo.getLoginAccount());
					user1.setNick(userInfo.getNick());
					user1.setAvatar(nextImg());
					myFriendGroupUsers.add(user1);
				}
			}
			myFriend.setUsers(myFriendGroupUsers);
			friends.add(myFriend);
			return friends;
	}

	@Override
	public User getUserByType(String userid, Integer type) {
		logger.info("127");
		UserInfo userInfo =userInfoService.getByUsername(userid);
		String text = userInfo.getLoginAccount()+userInfo.getLoginPassword();
		String key = Const.authkey;
		String token = Md5.sign(text, key, HttpConst.CHARSET_NAME);
		User user = getUser(token,userInfo);
		user.setId(userid);
		return user;
	}

	@Override
	public void addGroupUser(String userid, String group_id) {
		logger.info("128");
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getGroupUsers(String group_id) {
		logger.info("129");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getGroups(String user_id) {
		logger.info("120");
		List<String> list=new ArrayList<>();
		list.add("好友群");
		list.add("炮友群");
		list.add("J-IM朋友圈");
		// TODO Auto-generated method stub
		return list;
	}
	/**
	 * 消息持久到数据库
	 */
	@Override
	public void writeMessage(String timelineTable, String timelineId, ChatBody chatBody) {
		logger.info("11");
		System.out.println(timelineTable);
		System.out.println(timelineId);
		logger.info("开始持久化数据了····");
		UserMessage userMessage =new UserMessage();
		userMessage.setChatType(chatBody.getChatType().toString());
		userMessage.setContent(chatBody.getContent());
		userMessage.setCreateTime(new Date());
		userMessage.setFromId(chatBody.getFrom());
		userMessage.setGroupId(chatBody.getGroup_id());
		userMessage.setMsgType(chatBody.getMsgType());
		userMessage.setToId(chatBody.getTo());
		userMessageService.addUserMessage(userMessage);
	}

	@Override
	public void removeGroupUser(String userid, String group_id) {
		logger.info("12444");
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserMessageData getFriendsOfflineMessage(String userid, String fromUserId) {
		logger.info("12555");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserMessageData getFriendsOfflineMessage(String userid) {
		logger.info("12666");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserMessageData getGroupOfflineMessage(String userid, String groupid) {
		logger.info("12777");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserMessageData getFriendHistoryMessage(String userid, String fromUerId, Double beginTime, Double endTime,
			Integer offset, Integer count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserMessageData getGroupHistoryMessage(String userid, String groupid, Double beginTime, Double endTime,
			Integer offset, Integer count) {
		logger.info("12888");
		// TODO Auto-generated method stub
		return null;
	}
	public String nextImg() {
		return ImgMnService.nextImg();
	}
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
		groups.add(new Group("101","好友群"));
		groups.add(new Group("102","炮友群"));
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
				user1.setId(userInfo.getLoginAccount());
				user1.setNick(userInfo.getNick());
				user1.setAvatar(nextImg());
				myFriendGroupUsers.add(user1);
			}
		}
		myFriend.setUsers(myFriendGroupUsers);
		friends.add(myFriend);
		return friends;
	}
}
