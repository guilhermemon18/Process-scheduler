package model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class Model {
	
	public abstract Integer getId();//método deve ser implementado na classe que herda.
	public abstract void setId(int value);
	public abstract void swap(Model other);
	
	public List<String> getClassAttribute() {
		Field[] fields = this.getClass().getDeclaredFields();
		List<String> sl = new  ArrayList<String>();
		for (Field field : fields) {
			sl.add(field.getName());
		}		
		return sl;
	}

	public Object get ( String attr_name ) throws Exception {
		Field field = this.getClass().getDeclaredField(attr_name);
		return field.get(this);    	
	}

	public void set ( String attr_name, Object value  )  throws Exception {
		Field field =  this.getClass().getDeclaredField(attr_name);
		field.set(this, value);
	}

	
	public Model clone ()  {
		Model model = null;
		try {
			model = this.getClass().newInstance();

			List<String> list = getClassAttribute();
			for (String attr : list) {
				model.set(attr, this.get(attr));
			} 
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
		return model;
	}

	
}