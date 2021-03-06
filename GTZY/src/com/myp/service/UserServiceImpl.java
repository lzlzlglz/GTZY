package com.myp.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myp.dao.BaseDao;
import com.myp.model.User;
import com.myp.util.StringUtils;

@Service("userService")
public class UserServiceImpl implements UserService {

	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	@Resource
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void delete(User user) {
		baseDao.deleteObject(user);
	}

	@Override
	public void add(User user) {
		baseDao.addObject(user);
	}

	@Override
	public void update(User user) {
		baseDao.updateObject(user);
	}

	@Override
	public int getRows() {
		Object rows = baseDao.getObject("select count(*) from User");
		long result = (Long) rows;
		return (int) result;
	}

	@Override
	public int getRowsWithCondition(String Bt, String nd, String position,
			String wh, String mj, String flh, String Bgqx, String ajh,
			String jh, String startTime, String endTime) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("Bt", Bt);
		params.put("nd", nd);
		params.put("position", position);
		params.put("wh", wh);
		params.put("mj", mj);
		params.put("flh", flh);
		params.put("Bgqx", Bgqx);
		params.put("ajh", ajh);
		params.put("jh", jh);
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		Object rows = baseDao.getObject(getHql(new StringBuffer(
				"select count(*) from User"), params));
		long result = (Long) rows;
		return (int) result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findWithPage(int page, int rows) {
		return (List<User>) baseDao.findWithPage(page, rows, "from User");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findWithPageAndCondition(String Bt, String nd,
			String position, String wh, String mj, String flh, String Bgqx,
			String ajh, String jh, String startTime, String endTime, int page,
			int rows) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("Bt", Bt);
		params.put("nd", nd);
		params.put("position", position);
		params.put("wh", wh);
		params.put("mj", mj);
		params.put("flh", flh);
		params.put("Bgqx", Bgqx);
		params.put("ajh", ajh);
		params.put("jh", jh);
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		return (List<User>) baseDao.findWithPage(page, rows,
				getHql(new StringBuffer("from User where 1=1"), params));
	}

	@Override
	public User login(User user) {
		return (User) baseDao.getObject("from User where userId="
				+ user.getUserId() + " and password=" + user.getPassword());
	}

	@SuppressWarnings("rawtypes")
	private String getHql(StringBuffer sb, Map<String, String> params) {
		for (Iterator iterator = params.entrySet().iterator(); iterator
				.hasNext();) {
			Map.Entry entry = (Map.Entry) iterator.next();
			String key = (String) entry.getKey();
			String val = (String) entry.getValue();
			if (!StringUtils.isBlank(val)) {
				sb.append(" and " + key + " like '%" + val.trim() + "%'");
			}
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		return (List<User>) baseDao.findAll("from User");
	}
}
