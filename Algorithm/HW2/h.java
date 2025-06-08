import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class HashMapDemo {  
	public static void main(String args[]) {  
  
		HashMap<String, Double> hm = new HashMap<String, Double>();  
      
		hm.put("John Doe", new Double(3434.34));  	 // Map�� (Ű, ��) �� �߰� 
		hm.put("John Doe", 3434.34);
		hm.put("Tom Smith", new Double(123.22));  
		hm.put("Jane Baker", new Double(1378.00));  
		hm.put("Tod Hall", new Double(99.22));  
		hm.put("Ralph Smith", new Double(-19.08));  

		System.out.println("Map�� toString() ����: " + hm);
		// Map�� ����� �ֵ��� ��� ���
		Set<Map.Entry<String, Double>> set = hm.entrySet();  
		for(Map.Entry<String, Double> me : set)
			System.out.println(me);
//			System.out.println(me.getKey() + ": " + me.getValue());  

		// John Doe�� ������ 1000���� �߰� 
		double balance = hm.get("John Doe");  
		hm.put("John Doe", balance + 1000);  
		System.out.println("New balance: " +  hm.get("John Doe"));
	}  
}