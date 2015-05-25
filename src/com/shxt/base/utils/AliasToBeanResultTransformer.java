package com.shxt.base.utils;

import java.lang.reflect.Field;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.property.ChainedPropertyAccessor;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.PropertyAccessorFactory;
import org.hibernate.property.Setter;
import org.hibernate.transform.ResultTransformer;
/**
 * 
 * @描述: 辅助类-我的规则是传输的对象总的都是小写单词
 * @作者:刘文铭
 * @版本:1.0
 * @版权所有:
 * @时间 2014-12-19 下午01:39:23
 */
public class AliasToBeanResultTransformer implements ResultTransformer {

	private static final long serialVersionUID = 1L;
	private final Class<?> resultClass;
	private Setter[] setters;
	private PropertyAccessor propertyAccessor;

	public AliasToBeanResultTransformer(Class<?> resultClass) {
		if (resultClass == null)
			throw new IllegalArgumentException("resultClass cannot be null");
		this.resultClass = resultClass;
		propertyAccessor = new ChainedPropertyAccessor(new PropertyAccessor[] {
				PropertyAccessorFactory.getPropertyAccessor(resultClass, null),
				PropertyAccessorFactory.getPropertyAccessor("field") });
	}

	public Object transformTuple(Object[] tuple, String[] aliases) {
		Object result;

		try {
			if (setters == null) {// 首先初始化，取得目标POJO类的所有SETTER方法
				setters = new Setter[aliases.length];
				for (int i = 0; i < aliases.length; i++) {
					String alias = aliases[i];
					if (alias != null) {
						// 我的逻辑主要是在getSetterByColumnName方法里面，其它都是HIBERNATE的另一个类中COPY的
						// 这里填充所需要的SETTER方法
						setters[i] = getSetterByColumnName(alias);
					}
				}
			}
			result = resultClass.newInstance();

			// 这里使用SETTER方法填充POJO对象
			for (int i = 0; i < aliases.length; i++) {
				if (setters[i] != null) {
					setters[i].set(result, tuple[i], null);
				}
			}
		} catch (InstantiationException e) {
			throw new HibernateException("Could not instantiate resultclass: "
					+ resultClass.getName());
		} catch (IllegalAccessException e) {
			throw new HibernateException("Could not instantiate resultclass: "
					+ resultClass.getName());
		}

		return result;
	}

	public List transformList(List collection) {
		return collection;
	}

	// 根据数据库字段名在POJO查找JAVA属性名，参数就是数据库字段名，如：USER_ID
	private Setter getSetterByColumnName(String alias) {
		// 取得POJO所有属性名
		Field[] fields = resultClass.getDeclaredFields();
		if (fields == null || fields.length == 0) {
			throw new RuntimeException("实体" + resultClass.getName() + "不含任何属性");
		}
		// 把字段名中所有的下杠去除
		// String proName = alias.replaceAll("_", "").toLowerCase();
		String proName = alias.toLowerCase();
		for (Field field : fields) {
			// 去除下杠的字段名如果和属性名对得上，就取这个SETTER方法
			if (field.getName().toLowerCase().equals(proName)) {
				return propertyAccessor.getSetter(resultClass, field.getName());
			}
		}
		throw new RuntimeException(
				"找不到数据库字段 ："
						+ alias
						+ " 对应的POJO属性或其getter方法，比如数据库字段为USER_ID或USERID，那么JAVA属性应为userId");
	}

}
