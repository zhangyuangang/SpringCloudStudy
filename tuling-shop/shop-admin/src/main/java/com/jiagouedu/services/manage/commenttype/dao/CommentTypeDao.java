package com.jiagouedu.services.manage.commenttype.dao;import com.jiagouedu.core.DaoManager;import com.jiagouedu.services.manage.commenttype.bean.CommentType;public interface CommentTypeDao extends DaoManager<CommentType> {	/**	 * 更新所有的评论插件为未选中	 */	void updateAllN();}