import java.util.ArrayList;
import java.util.Hashtable;


public class id3Algorithm {

	private static double threshold = 0.6D;
			
	private static String[] categories = {"Babymood","Mothermood","Weather","Traffic","Outings"};
	
	private static Hashtable<String, String[]> catetoryVal = new  Hashtable<String, String[]>();
    
	private static  String[][] data = {
    		{"开心","开心","晴朗","通畅","是"},
    		{"开心","开心","阴暗","拥堵","是"},
    		{"开心","开心","阴暗","通畅","是"},
    		{"开心","开心","下雨","通畅","是"},
    		{"开心","不开心","晴朗","通畅","否"},
    		{"开心","不开心","阴暗","通畅","否"},
    		{"开心","不开心","下雨","通畅","否"},
    		{"开心","不开心","晴朗","拥堵","是"},
    		{"开心","不开心","下雨","拥堵","否"},
    		{"开心","不开心","阴暗","拥堵","否"},
    		{"一般","开心","晴朗","通畅","是"},
    		{"一般","不开心","晴朗","通畅","是"},
    		{"一般","不开心","晴朗","拥堵","是"},
    		{"一般","不开心","阴暗","通畅","是"},
    		{"一般","不开心","阴暗","拥堵","否"},
    		{"一般","不开心","下雨","通畅","否"},
    		{"一般","不开心","下雨","拥堵","否"},
    		{"不开心","不开心","晴朗","通畅","是"},
    		{"不开心","不开心","晴朗","拥堵","否"},
    		{"不开心","不开心","阴暗","通畅","是"},
    		{"不开心","不开心","阴暗","拥堵","否"},
    		{"不开心","不开心","下雨","通畅","否"},
    		{"不开心","不开心","下雨","拥堵","否"}
    		
    };	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub				       
		InitialHashtable();
		double allEntropy = calculateAllEntropy();
		
		double[] valGain = new double[4];
		
		valGain[0] = calculateAttributeGain("Babymood",allEntropy);
		valGain[1] = calculateAttributeGain("Mothermood",allEntropy);
		valGain[2] = calculateAttributeGain("Weather",allEntropy);
		valGain[3]= calculateAttributeGain("Traffic",allEntropy);
		
		double val = 0;
		int index = -1;
		for(int i=0;i<valGain.length;i++)
		{
			if(val<valGain[i])
			{
				val = valGain[i];
				index = i;
			}
		}
		
		System.out.println(String.format("最大增益为%1$s",categories[index]));
		
	}
	
	private static void InitialHashtable()
	{
		String[] attribute0 = {"开心","一般","不开心"};
		catetoryVal.put(categories[0], attribute0);
		
		String[] attribute1 = {"开心","一般","不开心"};
		catetoryVal.put(categories[1], attribute1);
		
		String[] attribute2 = {"晴朗","阴暗","下雨"};
		catetoryVal.put(categories[2], attribute2);
		
		String[] attribute3 = {"通畅","拥堵"};
		catetoryVal.put(categories[3], attribute3);
		
	}
	
	//计算信息增益
	private static double calculateAttributeGain(String skey,double allEntropy)
	{
		int count= data.length;
		
		double[] EntropyVal = null;
		double allPlus = 0;
		
		String[] val = catetoryVal.get(skey);
		for(int i = 0 ;i<val.length;i++)
		{
			EntropyVal = calculateAttributeEntropy(skey,val[i]);
		}
		allPlus += EntropyVal[0]/count*EntropyVal[1];


	    double gain = allEntropy - allPlus;
	    
	    System.out.println(String.format("%1$s,信息增益为%2$f",skey,gain));
	    
	    return gain;
	}
	
	// double[0]为count，  double[1]为信息熵
	private static double[] calculateAttributeEntropy(String skey,String attribute)
	{			                      
        double[] outpercent = calculateAttributeProbability(skey,attribute,"是");
        double[] nooutpercent = calculateAttributeProbability(skey,attribute,"否");
        double entropy = -outpercent[1]*Math.log(outpercent[1]) - nooutpercent[1]*Math.log(nooutpercent[1]);  //-5/14log(5/14) - 9/14log(9/14) = 0.940
              
        System.out.println(String.format("%1$s,去玩的概率是:%2$f，不出去玩的概率是:%3$f,信息熵:%4$f",skey+"——"+attribute,outpercent[1],nooutpercent[1],entropy));
        
        double[] returnval = new double[2];
        returnval[0] = outpercent[0];
        returnval[1] = entropy;
        
        return returnval;        
	}
	
	private static double[] calculateAttributeProbability(String skey,String attribute,String action)
	{
		double[] returnval = new double[2];
		int index = 0;		
		for(int j=0;j<categories.length;j++)
		{
			if(categories[j] == skey)		
			{
				index = j;
				break;
			}			
		}
		
        int count = 0;
        
		for(int j=0;j<data.length;j++)
		{
			if(data[j][index] == attribute)
        	{
				count = count+1;
        	}  			
		}
		
        int attributeCount = 0;
        
        for(int i=0;i<data.length;i++)
        {
        	if(data[i][index] == attribute && data[i][4] == action)
        	{
        		attributeCount = attributeCount+1;
        	}       	    	
        }
        
        double attributePercent = attributeCount*1.0/count;   
        returnval[0] = count;
        returnval[1] = attributePercent;
        return returnval;    		
	}
	
	
	//样本集合的信息熵
	private static double calculateAllEntropy()
	{			                      
        double outpercent = calculateAllProbability("Outings","是");
        double nooutpercent = calculateAllProbability("Outings","否");
        double entropy = -outpercent*Math.log(outpercent) - nooutpercent*Math.log(nooutpercent);  //-5/14log(5/14) - 9/14log(9/14) = 0.940
              
        System.out.println(String.format("总样本集合出去玩的概率是:%1$f，不出去玩的概率是:%2$f,，样本集合的信息熵:%3$f",outpercent,nooutpercent,entropy));
        
        return entropy;        
	}	
	
	
	private static double calculateAllProbability(String skey,String attribute)
	{
		int index = 0;		
		for(int j=0;j<categories.length;j++)
		{
			if(categories[j] == skey)
			{
				index = j;
				break;
			}			
		}
        int count = data.length;
        int attributeCount = 0;
        
        for(int i=0;i<count;i++)
        {
        	if(data[i][index] == attribute)
        	{
        		attributeCount = attributeCount+1;
        	}       	    	
        }
        
        double attributePercent = attributeCount*1.0/count;                
        return attributePercent;    		
	}		
}
