package jweb.di;

import java.util.HashMap;
import java.util.Map;

public class JWDI {
	//存放单例的数据池
	private static final Map<Class<?>,Object>SINGLE_POOLS=new HashMap<>();
	
	
	public static Object get() {
		return null;
	}
}
