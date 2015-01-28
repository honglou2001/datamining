import java.util.ArrayList;
import java.util.Hashtable;



public class id3Algorithm {

	private static double threshold = 0.6D;
			
	private static String[] categories = {"Babymood","Mothermood","Weather","Traffic","Outings"};
	
	private static Hashtable<String, String[]> catetoryVal = new  Hashtable<String, String[]>();
	
	private static ArrayList<id3data> allistdata = new ArrayList<id3data>();; // 原始数据 
    
	private static  String[][] data = {
		{"开心","开心","晴朗","通畅","是"},
		{"开心","一般","晴朗","通畅","是"},
		{"开心","开心","晴朗","拥堵","否"},
		{"开心","开心","阴暗","拥堵","是"},
		{"开心","开心","阴暗","通畅","是"},
		{"开心","开心","下雨","通畅","是"},
		{"开心","开心","下雨","拥堵","否"},
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
		
		Initialallistdata();
		
		int maxGainIndex = CalculateMaxGain(-1,"");		
		
		String[] val = catetoryVal.get(categories[maxGainIndex]);
		
		for(int i = 0 ;i<val.length;i++)
		{
			 CalculateMaxGain(maxGainIndex,val[i]);		
		}
	}
	
	
	private static void Initialallistdata()
	{		
		for(int i = 0;i<data.length;i++)
		{
			id3data iddata = new id3data();
			iddata.setArrdata(data[i]);
			//1|2|4|8 =15
			iddata.setValid(15);
			allistdata.add(iddata);
		}
		
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
	
	private static int CalculateMaxGain(int attrIndex,String attrVal)
	{
		double allEntropy = calculateAllEntropy(attrIndex,attrVal);
		
		double[] valGain = new double[4];
		
		valGain[0] = calculateAttributeGain("Babymood",allEntropy,attrIndex,attrVal);
		valGain[1] = calculateAttributeGain("Mothermood",allEntropy,attrIndex,attrVal);
		
		if(attrIndex==-1){			
			valGain[2] = calculateAttributeGain("Weather",allEntropy,attrIndex,attrVal);
		}
		valGain[3]= calculateAttributeGain("Traffic",allEntropy,attrIndex,attrVal);
		
		double val = 0;
		int index = -1;
		for(int i=0;i<valGain.length;i++)
		{
			if(attrIndex==2 && i==2)
			{
				continue;
			}
			
			if(val<valGain[i])
			{							
				val = valGain[i];							
				index = i;					
			}
		}
		
		try{
		 System.out.println(String.format("最大增益为%1$s",attrVal+" "+categories[index]));
		}
		catch(Exception ex)
		{
			System.out.println(String.format("Err!!!!!---最大增益为%1$s",attrVal));
			
		}
		
		return index;
	}
		
	//计算信息增益
	private static double calculateAttributeGain(String skey,double allEntropy,int attrIndex,String attrVal)
	{
		int count= data.length;
		
		double[] EntropyVal = null;
		double allPlus = 0;
		
		String[] val = catetoryVal.get(skey);
		for(int i = 0 ;i<val.length;i++)
		{
			EntropyVal = calculateAttributeEntropy(skey,val[i],attrIndex,attrVal);
			
			
			if(EntropyVal[1]==0){
			
			}
			else{
			   allPlus += EntropyVal[0]/count*EntropyVal[1];
			}
		}


	    double gain = allEntropy - allPlus;
	    
	    System.out.println(String.format("%1$s,信息增益为%2$f",skey+"("+attrVal+")",gain));
	    
	    return gain;
	}
	
	// double[0]为count，  double[1]为信息熵
	private static double[] calculateAttributeEntropy(String skey,String attribute,int attrIndex,String attrVal)
	{			                      
        double[] outpercent = calculateAttributeProbability(skey,attribute,"是",attrIndex,attrVal);
        double[] nooutpercent = calculateAttributeProbability(skey,attribute,"否",attrIndex,attrVal);
        double entropy = 0D;
        
        if ( outpercent[1]==0 || nooutpercent[1]==0)
        {
        	
        }
        else
        {
        	entropy =	-outpercent[1]*Math.log(outpercent[1]) - nooutpercent[1]*Math.log(nooutpercent[1]);  
        }
              
        System.out.println(String.format("%1$s,去玩的概率是:%2$f，不出去玩的概率是:%3$f,信息熵:%4$f",skey+"——"+attribute+"("+attrVal+")",outpercent[1],nooutpercent[1],entropy));
        
        double[] returnval = new double[2];
        returnval[0] = outpercent[0];
        returnval[1] = entropy;
        
        return returnval;        
	}
	
	
	private static double[] calculateAttributeProbability(String skey,String attribute,String action,int attrIndex,String attrVal)
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
        
        //int attCount = 0;
        //如不是全集合
        if(attrIndex!=-1)
        {
			for(int j=0;j<data.length;j++)
			{
				if(data[j][index] == attribute&&data[j][attrIndex] == attrVal)
	        	{
					count = count+1;
	        	}  			
			}	        	
        }
        else{
			for(int j=0;j<data.length;j++)
			{
				if(data[j][index] == attribute)
	        	{
					count = count+1;
	        	}  			
			}	
        }
        int attributeCount = 0;
        
        //如不是全集合
        if(attrIndex!=-1)
        {
	        for(int i=0;i<data.length;i++)
	        {
	        	if(data[i][index] == attribute && data[i][4] == action && data[i][attrIndex] == attrVal)
	        	{
	        		attributeCount = attributeCount+1;
	        	}       	    	
	        }
	        
	        
        }else{
	        for(int i=0;i<data.length;i++)
	        {
	        	if(data[i][index] == attribute && data[i][4] == action)
	        	{
	        		attributeCount = attributeCount+1;
	        	}       	    	
	        }
        }
        
        double attributePercent = 0;  
        if(count !=0){
          attributePercent = attributeCount*1.0/count; 
        }
        returnval[0] = count;
        returnval[1] = attributePercent;
        return returnval;    		
	}
	
	
	//样本集合的信息熵
	private static double calculateAllEntropy(int attrIndex,String attrVal)
	{			                      
        double outpercent = calculateAllProbability("Outings","是",attrIndex,attrVal);
        double nooutpercent = calculateAllProbability("Outings","否",attrIndex,attrVal);
        double entropy = -outpercent*Math.log(outpercent) - nooutpercent*Math.log(nooutpercent);  //-5/14log(5/14) - 9/14log(9/14) = 0.940
              
        System.out.println(String.format("%1$s总样本集合出去玩的概率是:%2$f，不出去玩的概率是:%3$f,，样本集合的信息熵:%4$f",attrVal,outpercent,nooutpercent,entropy));
        
        return entropy;        
	}	
	
	//目标属性在某样本集合的发生的概率,attrIndex=-1为全集合
	private static double calculateAllProbability(String skey,String attribute,int attrIndex,String attrVal)
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
        
        int attCount = 0;
        //如不是全集合
        if(attrIndex!=-1)
        {
            for(int i=0;i<count;i++)
            {
            	if(data[i][attrIndex] == attrVal )
            	{
            		attCount = attCount+1;
            	}       	    	
            }
        }
        
        int attributeCount = 0;
        
        //如不是全集合
        if(attrIndex!=-1)
        {
	        for(int i=0;i<count;i++)
	        {
	        	if(data[i][index] == attribute && data[i][attrIndex] == attrVal)
	        	{
	        		attributeCount = attributeCount+1;
	        	}       	    	
	        }   
	        
	        count = attCount;
        
        }
        else{
	        for(int i=0;i<count;i++)
	        {
	        	if(data[i][index] == attribute)
	        	{
	        		attributeCount = attributeCount+1;
	        	}       	    	
	        }
        }
        
        double attributePercent = attributeCount*1.0/count;                
        return attributePercent;    		
	}		
}

