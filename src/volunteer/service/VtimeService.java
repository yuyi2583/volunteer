package volunteer.service;

import java.util.ArrayList;
import java.util.List;

import volunteer.dao.IVtimeDAO;
import volunteer.dao.VtimeDAO;
import volunteer.po.ManHour;
import volunteer.po.ManHourPK;
import volunteer.po.User;

public class VtimeService implements IVtimeService {
	private List userList = new ArrayList<>();
	private IVtimeDAO dao;

	public IVtimeDAO getDao() {
		return dao;
	}

	public void setDao(VtimeDAO dao) {
		this.dao = dao;
	}

	public void setDao(IVtimeDAO dao) {
		this.dao = dao;
	}

	/*
	 * @根据学号查询该学号所有工时记录 传入参数 No 学号 返回该学号所有的工时记录 数据格式按照方法内
	 */
	public List vtimeSearch(String No) {
		System.out.println("根据学号查询该学号所有工时记录");
		return userList = dao.vtimeSearch(No);
	}

	/*
	 * @根据活动日期名称添加工时记录 用户补充缺漏的工时 前提：该学号已在平台注册 传入参数 Aname 活动名称 Adate 活动日期 No 学号 vtime
	 * 工时数 添加成功返回该学号所有人的姓名 添加失败 返回 "no"
	 */
	public String addVtime(ManHour manhour) {
		System.out.println("根据活动日期名称添加工时记录");
		
		//ManHour temp = dao.addVtime(manhour);
		dao.getUser(manhour);
		System.out.println("Now:"+manhour.getUsername());
		if(manhour.getUsername().isEmpty()||manhour.getUsername().equals(""))
			return "no";
		else {
			ManHour temp = dao.addVtime(manhour);
			if (temp != null)
				return temp.getUsername();
			else
				return "overlap";
		}
	}

	/*
	 * @根据活动名称日期学号，修改工时 传入参数 Aname 活动名称 Adate 活动日期 No 学号 vtime 工时数 修改成功返回true
	 * 修改失败返回false
	 */
	public boolean alertVtime(ManHour manhour) {
		System.out.println("根据活动名称日期学号，修改工时");
		if (dao.alterVtime(manhour).equals("success"))
			return true;
		else
			return false;
	}

	/*
	 * @根据活动名称日期学号删除工时记录 传入参数 Aname 活动名称 Adate 活动日期 No 学号 删除成功返回 true 删除失败返回false
	 */
	public boolean vtimeDelete(ManHourPK pk) {
		System.out.println("根据活动名称日期学号删除工时记录");
		if (dao.vtimeDelete(pk).equals("success"))
			return true;
		else
			return false;
	}

	/*
	 * @根据活动名称活动日期返回活动所有工时信息 传入参数 Aname 活动名称 Adate 活动日期 查找成功 返回数据按照方法内格式 查找失败返回null
	 */
	public List vtimeDetail(String Aname, String Adate) {
		System.out.println("根据活动名称活动日期返回活动所有工时信息");
		userList = dao.vtimeDetail(Aname, Adate);
		for (int i = 0; i < userList.size(); i++) {
			System.out.println("FSFSF:" + ((ManHour) (userList.get(i))).getPk().getNo());
			dao.getUser((ManHour) (userList.get(i)));
		}
		return userList;
	}
}
