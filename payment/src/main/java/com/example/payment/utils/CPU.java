
package com.example.payment.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.LinkedHashMap;


public class CPU {
	
	// https://docs.oracle.com/javase/8/docs/api/index.html?java/lang/management/OperatingSystemMXBean.html
	private static final OperatingSystemMXBean osMXBean
						= ManagementFactory.getOperatingSystemMXBean();
	private static final LinkedHashMap<String, Method> methodsMap
						= new LinkedHashMap<String, Method>();
	/**
	 * Set up the CPU Stats with all the necessary Methods
	 */
	static {
		loadMethods();
	}
	
	/**
	 * Load All Methods
	 */
	private static void loadMethods() {
		// System.out.println("Initializing CPU..");
		for (Method method : osMXBean.getClass().getDeclaredMethods()) {
			method.setAccessible(true);
			if (method.getName().startsWith("get")  && Modifier.isPublic(method.getModifiers())) {
				methodsMap.put(method.getName(), method);
				// System.out.println("Loading methods... "+method.getName()+"()");
			}
		} // Loop
	}
	
	/**
	 * Invoke the Method 
	 * @param _methodName
	 * @return
	 */
	private static Object invoke(String _methodName, OperatingSystemMXBean _osMXBean) {
		if(_methodName == null) {
			System.out.println("Method "+_methodName+"() Invalid!");
			return "";
		}
		if(_osMXBean == null) {
			System.out.println("OS MXBean "+_osMXBean+"() Invalid!");
			return "";
		}
		Object value = null;
		Method method = methodsMap.get(_methodName);
		if(method != null) {
	 		try {
				value = method.invoke(_osMXBean);
			} catch (Exception e) {
				System.out.println("Exception in method "+_methodName+".invoke(_osMXBean) "
									+"ERROR="+e.getMessage());
				e.printStackTrace();
			}
		} else {
			System.out.println("Method "+_methodName+"() Not Found!");
		}
		// System.out.println(method.getName() + " = " + value);
		return value;
	}

	// ---------------------------------------------------------------------------

	/**
	 * Prints All the CPU Stats
	 */
	public static void printAllCpuStats() {
		for(String methodName : methodsMap.keySet()) {
			System.out.println(methodName+"() = "+invoke(methodName, osMXBean));
		}
	}
	
	// ============  All CPU Stat Public Methods =================================	
	//  Memory Details -----------------------------------------------------------
	
	/**
	 * Get Committed Virtual Memory Size
	 * @return
	 */
	public static long getCommittedVirtualMemorySize() {
		try {	return (Long) invoke("getCommittedVirtualMemorySize", osMXBean); } 
		catch (Exception ignored) { ignored.printStackTrace(); }
		return 0;
	}
	
	/**
	 * Get Total Swap Space
	 * @return
	 */
	public static long getTotalSwapSpaceSize() {
		try {	return (Long) invoke("getTotalSwapSpaceSize", osMXBean); } 
		catch (Exception ignored) { ignored.printStackTrace(); }
		return 0;
	}
	
	/**
	 * Get Free Swap Space
	 * @return
	 */
	public static long getFreeSwapSpaceSize() {
		try {	return (Long) invoke("getFreeSwapSpaceSize", osMXBean); } 
		catch (Exception ignored) { ignored.printStackTrace(); }
		return 0;
	}
	 
	/**
	 * Returns CPU Process Time
	 * @return
	 */
	public static int getProcessCpuTime() {
		try {	return (Integer) invoke("getProcessCpuTime", osMXBean); } 
		catch (Exception ignored) { ignored.printStackTrace(); }
		return 0;
	}
	
	/**
	 * Get Free Physical Memory
	 * @return
	 */
	public static long getFreePhysicalMemorySize() {
		try {	return (Long) invoke("getFreePhysicalMemorySize", osMXBean); } 
		catch (Exception ignored) { ignored.printStackTrace(); }
		return 0;
	}
	
	/**
	 * Get Total Physical Memory 
	 * @return
	 */
	public static long getTotalPhysicalMemorySize() {
		try {	return (Long) invoke("getTotalPhysicalMemorySize", osMXBean); } 
		catch (Exception ignored) { ignored.printStackTrace(); }
		return 0;
	}
	
	//  File Descriptor Count ----------------------------------------------------
	
	/**
	 * Get Open File Descriptor Count
	 * @return
	 */
	public static long getOpenFileDescriptorCount() {
		try {	return  (Long) invoke("getOpenFileDescriptorCount", osMXBean); } 
		catch (Exception ignored) {
			/* ignored.printStackTrace(); */ }
		return 0;
	}
	
	/**
	 * Get Max File Descriptor Count
	 * @return
	 */
	public static long getMaxFileDescriptorCount() {
		try {	return  (Long) invoke("getMaxFileDescriptorCount", osMXBean); } 
		catch (Exception ignored) {
			/* ignored.printStackTrace(); */}
		return 0;
	}
	
	// CPU LOAD -------------------------------------------------------------------
	
	/**
	 * Get System CPU Load
	 * @return
	 */
	public static double getSystemCpuLoad2() {
		// System.out.println("getSystemCpuLoad() <=> "+invoke("getSystemCpuLoad"));
		try {	return  (Double) invoke("getSystemCpuLoad",osMXBean); } 
		catch (Exception ignored) {
			/* ignored.printStackTrace(); */}		
		return 0.0;
	}
	
	public static Object getSystemCpuLoad() {
		// System.out.println("getSystemCpuLoad() <=> "+invoke("getSystemCpuLoad"));
		try {	return  invoke("getSystemCpuLoad", osMXBean); } 
		catch (Exception ignored) { ignored.printStackTrace(); }		
		return "0.0";
	}
	
	/**
	 * Get Process CPU Load
	 * @return
	 */
	public static double getProcessCpuLoad2() {
		try {	return  (Double) invoke("getProcessCpuLoad", osMXBean); } 
		catch (Exception ignored) { ignored.printStackTrace(); }
		return 0.0;
	}
	public static Object getProcessCpuLoad() {
		try {	return  invoke("getProcessCpuLoad", osMXBean); } 
		catch (Exception ignored) { ignored.printStackTrace(); }
		return "0.0";
	}
	
	/**
	 * Returns in MB
	 * @param _bytes
	 * @return
	 */
	public static long toMB(long _bytes) {
		return (_bytes > 0) ? _bytes / (1024 * 1024) : 0;
	}
	
	/**
	 * 
	 * @param _bytes
	 * @return
	 */
	public static String toMBString(long _bytes) {
		long mb = toMB(_bytes);
		return (mb > 1024) ? String.format("%.02f",((double)mb/1024)) + " GB" : mb + " MB";
	}
	
	/**
	 * Returns Free Memory
	 * @return
	 */
	public static long freeMemory() {
		return Runtime.getRuntime().freeMemory();
	}
	
	/**
	 * Returns Total Memory
	 * @return
	 */
	public static long totalMemory() {
		return Runtime.getRuntime().totalMemory();
	}
	
	/**
	 * Returns Max Memory
	 * @return
	 */
	public static long maxMemory() {
		return Runtime.getRuntime().maxMemory();
	}
	
	/**
	 * Returns available processors
	 * @return
	 */
	public static int availableProcessors() {
		return Runtime.getRuntime().availableProcessors();
	}
	
	/**
	 * Prints Complete CPU Stats
	 * @return
	 */
	public static String printCpuStats() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("|cpus=").append(availableProcessors());
		
		sb.append("|PCPU=").append(String.format("%.02f", CPU.getProcessCpuLoad()));
		sb.append("|SCPU=").append(String.format("%.02f", CPU.getSystemCpuLoad()));
		
		sb.append("|FM=").append(toMBString(freeMemory()));
		sb.append("|TM=").append(toMBString(totalMemory()));
		
		sb.append("|FSMem=").append(toMBString(CPU.getFreePhysicalMemorySize()));
		sb.append("|TSMem=").append(toMBString(CPU.getTotalPhysicalMemorySize()));
		
		sb.append("|OFD=").append(CPU.getOpenFileDescriptorCount()).append("/");
							sb.append(CPU.getMaxFileDescriptorCount());
							
		sb.append("|FSwap=").append(toMBString(CPU.getFreeSwapSpaceSize()));
		sb.append("|TSwap=").append(toMBString(CPU.getTotalSwapSpaceSize()));
		
		return sb.toString();
	}
	
	/**
	 * Test CPU Utility
	 * 
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		CPU.printAllCpuStats();
		
		int sleepTime = 3000;
		for(int x=0; x<7; x++) {
			System.out.println(x+")> Sleeping for "+sleepTime+" ms : "+new Date());
			Thread.sleep(sleepTime);
			System.out.println(new Date()+printCpuStats());
		}
		System.out.println(new Date()+printCpuStats());
	}

}
