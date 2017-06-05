package cn.itcast.oa.view.action;

import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Topic;

@Controller
public class TopicAction extends BaseAction<Topic> {
	/**
	 * show single topic
	 * @return
	 * @throws Exception
	 */
	public String show() throws Exception {
		return "show";
	}
	
	/**
	 * add new topic UI
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		return "addUI";
	}
	
	/**
	 * add new topic
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return "toShow"; //转到当前这个新主题的显示页面
	}
}
