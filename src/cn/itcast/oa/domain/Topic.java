package cn.itcast.oa.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Topic extends Article {
	
	/**
	 * normal Topic
	 */
	public static int TYPE_NORMAL = 0;
	
	/**
	 * best Topic
	 */
	public static int TYPE_BEST = 1;
	
	/**
	 * TOP Topic
	 */
	public static int TYPE_TOP  = 2;
	
	
	private String title;
	private int type;
	
	private int replyCount;
	private Reply lastReply;
	private Date lastUpdateTime;
	
	private Forum forum;
	private Set<Reply> replies = new HashSet<Reply>();
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	public Set<Reply> getReplies() {
		return replies;
	}
	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public Reply getLastReply() {
		return lastReply;
	}
	public void setLastReply(Reply lastReply) {
		this.lastReply = lastReply;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
