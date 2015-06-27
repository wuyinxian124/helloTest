package edu.scut.mybatis.inter;

import edu.scut.mybatis.model.User;

/**
 * @author Bryan-zhou
 * @date 2015年6月27日下午5:11:55
 **/
public interface IUserOperation {

	public User selectUserByID(int id);
}
