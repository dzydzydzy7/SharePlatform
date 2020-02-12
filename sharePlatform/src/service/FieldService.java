package service;

import java.util.List;

import po.Field;

public interface FieldService {
	/** 获取所有领域 */
	public List<Field> getAllFields();
	
	/** 增加领域 */
	public void addField(Field field);
}
