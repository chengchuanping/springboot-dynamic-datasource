package io.springboot.demo.datasource;

/**
 * 
 * @author KevinBlandy
 *
 */
public class DataSourceKeyHolder {
	
	private static final ThreadLocal<Object> context = new ThreadLocal<Object>();
	
	public static Object get () {
		return context.get();
	}
	
	public static void set(Object key) {
		context.set(key);
	}
	
	public static void clear () {
		context.remove();
	}
}
