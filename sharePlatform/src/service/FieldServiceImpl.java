package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.FieldMapper;
import po.Field;

@Service("fieldService")
public class FieldServiceImpl implements FieldService {
	@Autowired
	private FieldMapper fieldMapper;

	@Override
	public List<Field> getAllFields() {
		return fieldMapper.selectAllFields();
	}

	@Override
	public void addField(Field field) {
		fieldMapper.insert(field);
	}
}
