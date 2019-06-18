package jweb.di;

import java.lang.reflect.InvocationTargetException;

public class JWDIBean {

	// 1=单例，2=多例， 还是多例 -1=class
	public final int sort;
	
	private final Class<?> diClass;
	// 单例缓存的对象
	private final Object obj;

	// 多个单例时，采用对象池
//	private final Object objs[];
//	//对象池下标
//	private int objsIndex=0;
	
	//当前类的构造方法的参数类型
	private final Class<?> constructorParamType[];

	//当前类的默认传参
	private final Object[] constructorParamObjs;
	
	public JWDIBean(Class<?> c, int sort,Class<?> constructorParamType[], Object[] constructorParamObjs) {
		this.sort = sort;
		this.diClass=c;
		this.constructorParamType=constructorParamType;
		this.constructorParamObjs=constructorParamObjs;
		//sort=1表示单例
		obj = sort == 1 ? newObject(c, this.constructorParamType, this.constructorParamObjs) : null;
		//sort>1表示使用对象池来管理对象
//		objs = sort > 1 ? newObjects(sort, c, this.constructorParamType, this.constructorParamObjs) : null;
		//sort=-1,表示多例。来一次，new一次
	}
	
	public Object getObject() {
		switch(sort) {
			case -1:return newObject(this.diClass,this.constructorParamType,this.constructorParamObjs);
			case 1:return this.obj;
			default :{
				
			}
		}
		return null;
	}
	
//	private static Object[] newObjects(int n, Class<?> c, Class<?>[] types, Object[] value) {
//		List<Object> list = new ArrayList<>();
//		for (int i = 0; i < n; i++) {
//			list.add(newObject(c, types, value));
//		}
//		return list.toArray(new Object[list.size()]);
//	}

	private static Object newObject(Class<?> c, Class<?>[] types, Object[] value) {
		try {
			return c.getConstructor(types).newInstance(value);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}
}
