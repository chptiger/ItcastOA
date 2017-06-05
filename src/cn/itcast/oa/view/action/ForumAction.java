package cn.itcast.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ForumAction extends BaseAction<Forum> {
	/**
	 * forum list
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		return "list";
	}
	
	/**
	 * show single forum
	 * @return
	 * @throws Exception
	 */
	public String show() throws Exception {
//		prepare forum
		Forum forum = forumService.getById(model.getId());
		ActionContext.getContext().put("forum", forum);
		
//		prepare topicList
		List<Topic> topicList = topicService.findByFrom(forum);
		ActionContext.getContext().put("topicList", topicList);
		return "show";
	}
}
