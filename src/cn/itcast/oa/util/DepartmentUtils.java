package cn.itcast.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.itcast.oa.domain.Department;

public class DepartmentUtils {
	public static List<Department> getAllDepartmentList(
			List<Department> topList, Department removedDepartment) {
		List<Department> list = new ArrayList<Department>();
		walkTree(topList, "┣", list, removedDepartment);
		return list;
	}

	private static void walkTree(Collection<Department> topList, String prefix,
			List<Department> list, Department removedDepartment) {
		for (Department top : topList) {
			if (removedDepartment != null
					&& top.getId().equals(removedDepartment.getId()))
				continue;
			// top point
			Department copy = new Department();// 不要修改Session缓存中的对象，最好使用副本
			copy.setId(top.getId());
			copy.setName(prefix + top.getName());
			list.add(copy);// 注意，添加的是copy的对象
			// subTree
			walkTree(top.getChildren(), "　" + prefix, list, removedDepartment);// 要使用全角的空格，要不然在html中只显示一个空格
		}
	}
}
