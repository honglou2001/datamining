import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class id3Main {
	
	private static  String[][] data = {
		{"开心1","开心","晴朗","通畅","是"},
		{"开心1","一般","晴朗","通畅","是"},
		{"开心1","开心","晴朗","拥堵","否"},
		{"开心1","开心","阴暗","拥堵","是"},
		{"开心1","开心","阴暗","通畅","是"},
		{"开心1","开心","下雨","通畅","是"},
		{"开心1","开心","下雨","拥堵","否"},
		{"开心1","不开心","晴朗","通畅","否"},
		{"开心1","不开心","阴暗","通畅","否"},
		{"开心1","不开心","下雨","通畅","否"},
		{"开心1","不开心","晴朗","拥堵","是"},
		{"开心1","不开心","下雨","拥堵","否"},
		{"开心1","不开心","阴暗","拥堵","否"},
		{"一般1","开心","晴朗","通畅","是"},
		{"一般1","不开心","晴朗","通畅","是"},
		{"一般1","不开心","晴朗","拥堵","是"},
		{"一般1","不开心","阴暗","通畅","是"},
		{"一般1","不开心","阴暗","拥堵","否"},
		{"一般1","不开心","下雨","通畅","否"},
		{"一般1","不开心","下雨","拥堵","否"},
		{"不开心1","不开心","晴朗","通畅","是"},
		{"不开心1","不开心","晴朗","拥堵","否"},
		{"不开心1","不开心","阴暗","通畅","是"},
		{"不开心1","不开心","阴暗","拥堵","否"},
		{"不开心1","不开心","下雨","通畅","否"},
		{"不开心1","不开心","下雨","拥堵","否"}
		
	};	
	
	private static ArrayList<id3data> allistdata = new ArrayList<id3data>(); // 原始数据 
	
	private static String[] categories = {"Babymood","Mothermood","Weather","Traffic","Outings"};
	
	//private static Hashtable<String, ArrayList<id3Attribute>> catetoryVal = new  Hashtable<String, ArrayList<id3Attribute>>();
	
	private static ArrayList<id3Attribute> allcatetoryVal = new  ArrayList<id3Attribute>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		
		InitialAttribute();
		
		Initialallistdata();
		
		
		double[] dval = calculateAllEntropy((long)Math.pow(2, 0));
		
		ArrayList<double[]> listLong= new ArrayList<double[]>();		
		
		listLong.add(calculateAttributeGain((long)Math.pow(2, 0),dval[0]));
		
		listLong.add(calculateAttributeGain((long)Math.pow(2, 10),dval[0]));
	
		listLong.add(calculateAttributeGain((long)Math.pow(2, 20),dval[0]));
		
		listLong.add(calculateAttributeGain((long)Math.pow(2, 30),dval[0]));
		
		double maxIndex = 0;
		double maxGain = 0;
		for(int i=0;i<listLong.size();i++)
		{
			double[] d1 = listLong.get(i);
			if(d1[0]>maxGain)
			{
				maxGain = d1[0];
				maxIndex = d1[1];
			}			
		}
		
		try{
			System.out.println(String.format("最大增益为%1$s",maxGain+" "+categories[(int)maxIndex]));
		}
		catch(Exception ex){
			System.out.println(String.format("Err!!!!!---最大增益为%1$s",maxGain));			
		}
//		calculateAllEntropy((long)Math.pow(2, 1) | (long)Math.pow(2, 11) | (long)Math.pow(2, 21));
	
//		calculateAllEntropy((long)Math.pow(2, 1));
//		
//		calculateAllEntropy((long)Math.pow(2, 2));
////		
//		calculateAllEntropy((long)Math.pow(2, 3));
//		
//		calculateAllEntropy((long)Math.pow(2, 11));
//		
//		calculateAllEntropy((long)Math.pow(2, 12));
//		
//		calculateAllEntropy((long)Math.pow(2, 13));
//		
//		calculateAllEntropy((long)Math.pow(2, 21));
//		
//		calculateAllEntropy((long)Math.pow(2, 22));
//		
//		calculateAllEntropy((long)Math.pow(2, 23));
		
//		calculateAllEntropy((long)Math.pow(2, 21)|(long)Math.pow(2, 0));
//		calculateAllEntropy((long)Math.pow(2, 21)|(long)Math.pow(2, 1));
//		calculateAllEntropy((long)Math.pow(2, 21)|(long)Math.pow(2, 2));
//		
//		calculateAllEntropy((long)Math.pow(2, 21)|(long)Math.pow(2, 11));
//		calculateAllEntropy((long)Math.pow(2, 21)|(long)Math.pow(2, 12));
//		calculateAllEntropy((long)Math.pow(2, 21)|(long)Math.pow(2, 13));
//		
//		calculateAllEntropy((long)Math.pow(2, 21)|(long)Math.pow(2, 31));
//		calculateAllEntropy((long)Math.pow(2, 21)|(long)Math.pow(2, 32));

		
        System.out.println("OK1");
        		
	}
	
	
	private static void Initialallistdata()
	{		
		for(int i = 0;i<data.length;i++)
		{
			id3data iddata = new id3data();
			
			iddata.setArrdata(data[i]);
			
			long  val = 0;
			  
			for(int j=0;j<4;j++)
			{
				long  val1= GetValByContent(data[i][j]);
				
				val = val| val1;
			}

			iddata.setValid(val);
			
			allistdata.add(iddata);
		}		
	}
	
	private static long  GetValByContent(String content)
	{
       for(int i = 0;i < allcatetoryVal.size(); i ++){
           if(allcatetoryVal.get(i).getAttributeContent() == content)
           {       	         	   
        	   return allcatetoryVal.get(i).getAttributeFigure();
           }
         }	       
		return 0;
	}
	
	private static void InitialAttribute()
	{	
		id3Attribute attr = null;
				
		attr = new id3Attribute();
		
		attr.setAttributeContent("开心1");
		attr.setAttributeParentFigure(0);
		attr.setAttributeFigure((long)Math.pow(2, 1) | (long)Math.pow(2, 0) );
		allcatetoryVal.add(attr);
		
		attr = new id3Attribute();		
		attr.setAttributeContent("一般1");
		attr.setAttributeParentFigure(0);
		attr.setAttributeFigure((long)Math.pow(2, 2) | (long)Math.pow(2, 0));
		allcatetoryVal.add(attr);
		
		attr = new id3Attribute();		
		attr.setAttributeContent("不开心1");
		attr.setAttributeParentFigure(0);
		attr.setAttributeFigure((long)Math.pow(2, 3) | (long)Math.pow(2, 0));
		allcatetoryVal.add(attr);		
		
		attr = new id3Attribute();		
		attr.setAttributeContent("开心");
		attr.setAttributeParentFigure(1);
		attr.setAttributeFigure((long)Math.pow(2, 11) | (long)Math.pow(2, 10));
		allcatetoryVal.add(attr);
		
		attr = new id3Attribute();		
		attr.setAttributeContent("一般");
		attr.setAttributeParentFigure(1);
		attr.setAttributeFigure((long)Math.pow(2, 12) | (long)Math.pow(2, 10));
		allcatetoryVal.add(attr);
		
		attr = new id3Attribute();		
		attr.setAttributeContent("不开心");
		attr.setAttributeParentFigure(1);
		attr.setAttributeFigure((long)Math.pow(2, 13) | (long)Math.pow(2, 10));
		allcatetoryVal.add(attr);			
		
		attr = new id3Attribute();		
		attr.setAttributeContent("晴朗");
		attr.setAttributeParentFigure(2);
		attr.setAttributeFigure((long)Math.pow(2, 21) | (long)Math.pow(2, 20));
		allcatetoryVal.add(attr);
		
		attr = new id3Attribute();		
		attr.setAttributeContent("阴暗");
		attr.setAttributeParentFigure(2);
		attr.setAttributeFigure((long)Math.pow(2, 22) | (long)Math.pow(2, 20));
		allcatetoryVal.add(attr);
		
		attr = new id3Attribute();		
		attr.setAttributeContent("下雨");
		attr.setAttributeParentFigure(2);
		attr.setAttributeFigure((long)Math.pow(2, 23) | (long)Math.pow(2, 20));
		allcatetoryVal.add(attr);		
				
		attr = new id3Attribute();		
		attr.setAttributeContent("通畅");
		attr.setAttributeParentFigure(3);
		attr.setAttributeFigure((long)Math.pow(2, 31) | (long)Math.pow(2, 30));
		allcatetoryVal.add(attr);
		
		attr = new id3Attribute();		
		attr.setAttributeContent("拥堵");
		attr.setAttributeParentFigure(3);
		attr.setAttributeFigure((long)Math.pow(2, 32) | (long)Math.pow(2, 30));
		allcatetoryVal.add(attr);	
				
	}

	
	private static ArrayList<id3Attribute> GetList(long parent)
	{
		//parent = (long)Math.pow(2, parent*10);
		
		ArrayList<id3Attribute> listLong= new ArrayList<id3Attribute>();	
		
		for(int i=0;i<allcatetoryVal.size();i++)
		{
			id3Attribute id3att = allcatetoryVal.get(i);
			if((id3att.getAttributeFigure() & parent) == parent)
			{
				listLong.add(id3att);
			}
			
		}		
		return listLong;
	}
	
	//计算信息增益
	private static double[] calculateAttributeGain(long infigure,double allEntropy)
	{
		int count= data.length;
		
		double[] EntropyVal = null;
		double allPlus = 0;
		
		
//		Map<Long,ArrayList<Long>> attrHs =new HashMap<Long,ArrayList<Long>>();
//				
//		if(attrHs.containsKey((long)Math.pow(2, 0)))
//		{
//			ArrayList<Long> listLong= attrHs.get((long)Math.pow(2, 0));
//			listLong.add((long)Math.pow(2, 1));
//			
//		}else
//		{
//			ArrayList<Long> listLong=  new ArrayList<Long>(); 
//			listLong.add((long)Math.pow(2, 1));
//			attrHs.put((long)Math.pow(2, 0), listLong);
//		}
		
		ArrayList<id3Attribute> listLong= GetList(infigure);//new ArrayList<Long>();		
		
//		if(infigure == 0)
//		{
//			listLong.add((long)Math.pow(2, 1));
//			listLong.add((long)Math.pow(2, 2));
//			listLong.add((long)Math.pow(2, 3));		
//		}
//		else if(infigure == 1)
//		{
//			listLong.add((long)Math.pow(2, 11));
//			listLong.add((long)Math.pow(2, 12));
//			listLong.add((long)Math.pow(2, 13));			
//		}	
//		else if(infigure ==2)
//		{
//			listLong.add((long)Math.pow(2, 21));
//			listLong.add((long)Math.pow(2, 22));
//			listLong.add((long)Math.pow(2, 23));			
//		}	
//		else if(infigure ==3)
//		{
//			listLong.add((long)Math.pow(2, 31));
//			listLong.add((long)Math.pow(2, 32));			
//		}

		double parentcategory= 0;
		
		for(int i = 0 ;i<listLong.size();i++)
		{
			EntropyVal = calculateAllEntropy(listLong.get(i).getAttributeFigure());
			
			parentcategory = listLong.get(i).getAttributeParentFigure(); 	
			
			if(EntropyVal[1]==0){
			
			}
			else{
			   allPlus += EntropyVal[0]/count*EntropyVal[1];
			}
		}


	    double gain = allEntropy - allPlus;
	    
	    System.out.println(String.format("%1$s,信息增益为%2$f",infigure,gain));
	    
	    double[] dgain = new double[2];
	    dgain[0] = gain;
	    dgain[1] = parentcategory;
	    return dgain;
	}
	
	
	
	//样本集合的信息熵
	private static double[] calculateAllEntropy(long infigure)
	{			                      
        double[] outpercent = calculateAllProbability(infigure,"是");
        double[] nooutpercent = calculateAllProbability(infigure,"否");
        double entropy = 0;
        if(outpercent[0]!=0 && outpercent[0]!=0)
        entropy = -outpercent[0]*Math.log(outpercent[0]) - nooutpercent[0]*Math.log(nooutpercent[0]);  //-5/14log(5/14) - 9/14log(9/14) = 0.940
              
        System.out.println(String.format("%1$s—总样本集合出去玩的概率是:%2$f，不出去玩的概率是:%3$f,，样本集合的信息熵:%4$f",infigure,outpercent[0],nooutpercent[0],entropy));
        
        double[] entropyArr = new double[2];
        entropyArr[0] = entropy;
        entropyArr[1] = outpercent[1];
        return entropyArr;        
	}	
	
	private static double[] calculateAllProbability(long infigure,String targetVal)
	{
        int count = allistdata.size();        
        int attributeCount = 0;

        int attcount = 0; 
        double[] Probability = new double[2];        
        for(int i = 0;i < count; i ++){

              id3data idata = allistdata.get(i);
              
              long val = idata.getValid() & infigure;
              
              if(val == infigure){
            	  
	              String[] strArr = idata.getArrdata();
	              
	              attcount = attcount+1;
	              
	              if(strArr[4]== targetVal)
	              {
	            	  attributeCount = attributeCount+1;
	              }
              }
         	  
          }    
        if (attcount ==0)
        {
        	return Probability;
        }
        double attributePercent = attributeCount*1.0/attcount;   
        Probability[0] = attributePercent;
        Probability[1] = attcount;
        return Probability;    		
	}	

}
