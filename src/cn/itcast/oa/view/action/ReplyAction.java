package cn.itcast.oa.view.action;

import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Reply;

@Controller
public class ReplyAction extends BaseAction<Reply> {
	/**
	 * add reply UI
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		return "addUI";
	}
	
	/**
	 * add reply
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return "toTopicShow"; //转到当前这个新回复所属的主题显示页面
	}
}
