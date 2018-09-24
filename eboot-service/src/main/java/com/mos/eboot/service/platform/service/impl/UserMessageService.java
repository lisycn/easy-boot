package com.mos.eboot.service.platform.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mos.eboot.platform.entity.UserMessage;
import com.mos.eboot.service.platform.mapper.UserMessageMapper;
import com.mos.eboot.service.platform.service.IUserMessageService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户发送消息记录表 服务实现类
 * </p>
 *
 * @author zlb
 * @since 2018-09-22
 */
@Service
public class UserMessageService extends ServiceImpl<UserMessageMapper, UserMessage> implements IUserMessageService {
	
}
