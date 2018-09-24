/**
 * 
 */
package com.mos.eboot.im.command;

import org.jim.common.ImAio;
import org.jim.common.ImPacket;
import org.jim.common.http.HttpConst;
import org.jim.common.http.HttpRequest;
import org.jim.common.packets.Command;
import org.jim.common.packets.LoginReqBody;
import org.jim.common.utils.JsonKit;
import org.jim.server.command.CommandManager;
import org.jim.server.command.handler.LoginReqHandler;
import org.jim.server.command.handler.processor.handshake.WsHandshakeProcessor;
import org.tio.core.ChannelContext;
/**
 * 
* @ClassName: DemoWsHandshakeProcessor 
* @Description: TODO(WS握手方法，返回null 则标识业务层不同意握手，失败) 
* @author Mr.zhou 
* @date 2018年9月22日 下午2:31:01
 */
public class DemoWsHandshakeProcessor extends WsHandshakeProcessor{

	@Override
	public void onAfterHandshaked(ImPacket packet, ChannelContext channelContext) throws Exception {
		LoginReqHandler loginHandler = (LoginReqHandler)CommandManager.getCommand(Command.COMMAND_LOGIN_REQ);
		HttpRequest request = (HttpRequest)packet;
		String username = request.getParams().get("username") == null ? null : (String)request.getParams().get("username")[0];
		String password = request.getParams().get("password") == null ? null : (String)request.getParams().get("password")[0];
		String token = request.getParams().get("token") == null ? null : (String)request.getParams().get("token")[0];
		LoginReqBody loginBody = new LoginReqBody(username,password,token);
		byte[] loginBytes = JsonKit.toJsonBytes(loginBody);
		request.setBody(loginBytes);
		request.setBodyString(new String(loginBytes,HttpConst.CHARSET_NAME));
		ImPacket loginRespPacket = loginHandler.handler(request, channelContext);
		if(loginRespPacket != null){
			ImAio.send(channelContext, loginRespPacket);
		}
	}
}
