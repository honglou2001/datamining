import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class id3Main {
	
	private static  String[][] data = {
		{"开心1","开心","晴朗","通畅","是"},
		{"开心1","一般","晴朗","通畅","是"},
		{"开心1","一般","晴朗","拥堵","否"},
		{"开心1","一般","阴暗","拥堵","否"},
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
		{"不开心1","不开心","下雨","拥堵","否"},
		{"不开心1","一般","晴朗","通畅","是"},
		{"不开心1","一般","下雨","通畅","否"},
		{"不开心1","一般","阴暗","通畅","否"},
		{"一般1","一般","晴朗","通畅","否"},
		{"一般1","一般","下雨","通畅","否"},
		{"一般1","一般","阴暗","通畅","否"}
		
	};	
	
	private static ArrayList<id3data> allistdata = new ArrayList<id3data>(); // 原始数据 
	
	private static String[] categories = {"Babymood","Mothermood","Weather","Traffic","Outings"};
	
	//private static Hashtable<String, ArrayList<id3Attribute>> catetoryVal = new  Hashtable<String, ArrayList<id3Attribute>>();
	
	private static ArrayList<id3Attribute> allcatetoryVal = new  ArrayList<id3Attribute>();
	
	private static id3tree rootTree = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		
		InitialAttribute();
		
		Initialallistdata();
		
		id3Attribute attribute = null;//new id3Attribute();
		
		double maxIndex = calMaxGain();
		
		 rootTree = new id3tree(0, categories[(int)maxIndex]);
		 
//		 id3tree f1 = null;
//		 f1 = new id3tree(1, "晴朗");
//		 rootTree.addChild(f1);
//		 f1 = new id3tree(2, "阴暗");
//		 rootTree.addChild(f1);
//		 f1 = new id3tree(3, "下雨");
//		 rootTree.addChild(f1);
		 
		 

		
		for(int i=1;i<=3;i++){
			
			attribute =new id3Attribute();
			attribute.setAttributeContent(categories[2]+"("+i+"信息熵)");
			attribute.setAttributeFigure((long)Math.pow(2, 20+i));
			attribute.setAttributeParentFigure(0);
			
			double[] dval1 = calculateAllEntropy(attribute);
			
			attribute =new id3Attribute();
			attribute.setAttributeContent(categories[2]+"("+i+")"+"babymood");
			attribute.setAttributeFigure((long)Math.pow(2, 20+i));		
			attribute.setCategory(0);
			attribute.setAttributeParentFigure((long)Math.pow(2, 20+i)| (long)Math.pow(2, 20));
			calculateAttributeGain(attribute,dval1[0]);
			
			attribute =new id3Attribute();
			attribute.setAttributeContent(categories[2]+"("+i+")"+"mothermood");
			attribute.setAttributeFigure((long)Math.pow(2, 20+i));		
			attribute.setCategory(1);
			attribute.setAttributeParentFigure((long)Math.pow(2, 20+i)| (long)Math.pow(2, 20));
			calculateAttributeGain(attribute,dval1[0]);
			
			
			attribute =new id3Attribute();
			attribute.setAttributeContent(categories[2]+"("+i+")"+"traffic");
			attribute.setAttributeFigure((long)Math.pow(2, 20+i));		
			attribute.setCategory(3);
			attribute.setAttributeParentFigure((long)Math.pow(2, 20+i)| (long)Math.pow(2, 20));
			calculateAttributeGain(attribute,dval1[0]);
		}
		
		for(int i=1;i<=3;i++){
			
			attribute =new id3Attribute();
			attribute.setAttributeContent(categories[0]+"("+i+"信息熵)");
			attribute.setAttributeFigure((long)Math.pow(2, i)|(long)Math.pow(2, 21));
			attribute.setAttributeParentFigure(0);
			
			double[] dval1 = calculateAllEntropy(attribute);						
			
			attribute =new id3Attribute();
			attribute.setAttributeContent(categories[2]+"("+i+")"+"mothermood");
			attribute.setAttributeFigure((long)Math.pow(2, i));		
			attribute.setCategory(1);
			attribute.setAttributeParentFigure((long)Math.pow(2, i)| (long)Math.pow(2, 0)| (long)Math.pow(2, 21)| (long)Math.pow(2, 20));
			calculateAttributeGain(attribute,dval1[0]);
			
			
			attribute =new id3Attribute();
			attribute.setAttributeContent(categories[2]+"("+i+")"+"traffic");
			attribute.setAttributeFigure((long)Math.pow(2, i));		
			attribute.setCategory(3);
			attribute.setAttributeParentFigure((long)Math.pow(2, i)| (long)Math.pow(2, 0)| (long)Math.pow(2, 21)| (long)Math.pow(2, 20));
			calculateAttributeGain(attribute,dval1[0]);
		}
		
		for(int i=1;i<=3;i++){
			
			attribute =new id3Attribute();
			attribute.setAttributeContent(categories[1]+"("+i+"信息熵)");
			attribute.setAttributeFigure((long)Math.pow(2, 10+i)|(long)Math.pow(2, 22));
			attribute.setAttributeParentFigure(0);
			
			double[] dval1 = calculateAllEntropy(attribute);						
			
			attribute =new id3Attribute();
			attribute.setAttributeContent(categories[1]+"("+i+")"+"babymood");
			attribute.setAttributeFigure((long)Math.pow(2, 10+i));		
			attribute.setCategory(0);
			attribute.setAttributeParentFigure((long)Math.pow(2, 10+i)| (long)Math.pow(2, 10)| (long)Math.pow(2, 22)| (long)Math.pow(2, 20));
			calculateAttributeGain(attribute,dval1[0]);
			
			
			attribute =new id3Attribute();
			attribute.setAttributeContent(categories[1]+"("+i+")"+"traffic");
			attribute.setAttributeFigure((long)Math.pow(2, 10+i));		
			attribute.setCategory(3);
			attribute.setAttributeParentFigure((long)Math.pow(2, 10+i)| (long)Math.pow(2, 10)| (long)Math.pow(2, 22)| (long)Math.pow(2, 20));
			calculateAttributeGain(attribute,dval1[0]);
		}
		
		for(int i=1;i<=2;i++){
			
			attribute =new id3Attribute();
			attribute.setAttributeContent(categories[3]+"("+i+"信息熵)");
			attribute.setAttributeFigure((long)Math.pow(2, 30+i)|(long)Math.pow(2, 21)|(long)Math.pow(2, 1));
			attribute.setAttributeParentFigure(0);
			
			double[] dval1 = calculateAllEntropy(attribute);						
			
			attribute =new id3Attribute();
			attribute.setAttributeContent(categories[2]+"("+i+")"+"mothermood");
			attribute.setAttributeFigure((long)Math.pow(2, 30+i));		
			attribute.setCategory(1);
			attribute.setAttributeParentFigure((long)Math.pow(2, i)| (long)Math.pow(2, 0)| (long)Math.pow(2, 21)| (long)Math.pow(2, 20)| (long)Math.pow(2, 31)| (long)Math.pow(2, 30));
			calculateAttributeGain(attribute,dval1[0]);
			
		}
	
		
//		attribute =new id3Attribute();
//		attribute.setAttributeContent(categories[2]+"晴朗111");
//		attribute.setAttributeFigure((long)Math.pow(2, 22));
//		attribute.setAttributeParentFigure((long)Math.pow(2, 22)|(long)Math.pow(2, 20));
//		attribute.setCategory(1);		
//		calculateAttributeGain(attribute,dval1[0]);
		
//		attribute =new id3Attribute();
//		attribute.setAttributeContent(categories[2]+"晴朗11");
//		attribute.setAttributeFigure((long)Math.pow(2, 21)|(long)Math.pow(2, 1));
//		attribute.setAttributeParentFigure(0);
//		 calculateAllEntropy(attribute);
		 

		
        System.out.println("OK1");
        		
	}
	
	private static double calMaxGain()
	{
		id3Attribute attribute = null;//new id3Attribute();
		
		attribute =new id3Attribute();
		attribute.setAttributeContent("根目录");
		attribute.setAttributeFigure((long)Math.pow(2, 0));
		attribute.setAttributeParentFigure(0);
		
		double[] dval = calculateAllEntropy(attribute);
		
		ArrayList<double[]> listLong= new ArrayList<double[]>();
		
		attribute =new id3Attribute();
		attribute.setAttributeContent(categories[0]);
		attribute.setAttributeFigure((long)Math.pow(2, 0));
		attribute.setAttributeParentFigure(0);
		
		listLong.add(calculateAttributeGain(attribute,dval[0]));
		
		attribute =new id3Attribute();
		attribute.setAttributeContent(categories[1]);
		attribute.setAttributeFigure((long)Math.pow(2, 10));
		attribute.setAttributeParentFigure(0);
		
		listLong.add(calculateAttributeGain(attribute,dval[0]));
		
		attribute =new id3Attribute();
		attribute.setAttributeContent(categories[2]);
		attribute.setAttributeFigure((long)Math.pow(2, 20));
		attribute.setAttributeParentFigure(0);
	
		listLong.add(calculateAttributeGain(attribute,dval[0]));
		
		attribute =new id3Attribute();
		attribute.setAttributeContent(categories[3]);
		attribute.setAttributeFigure((long)Math.pow(2, 30));
		attribute.setAttributeParentFigure(0);
		
		listLong.add(calculateAttributeGain(attribute,dval[0]));
		
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
		
		return maxIndex;
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

	//获取某个类别下的所有属性
	private static ArrayList<id3Attribute> GetList(id3Attribute attribute)
	{		
		long figure = attribute.getAttributeFigure();
		
		long parent = attribute.getAttributeParentFigure();
		
		ArrayList<id3Attribute> listLong= new ArrayList<id3Attribute>();	
		
		if(parent == 0){		
			for(int i=0;i<allcatetoryVal.size();i++)
			{
				id3Attribute id3att = allcatetoryVal.get(i);
				
				if((id3att.getAttributeFigure() & figure) == figure)
				{
					listLong.add(id3att);
				}			
			}
		}
		else if(((long)Math.pow(2, 20) & parent) == (long)Math.pow(2, 20)){
			
			for(int i=0;i<allcatetoryVal.size();i++)
			{						
				id3Attribute id3att = allcatetoryVal.get(i);
				
				id3Attribute id3att1= new id3Attribute();
				
				id3att1.setAttributeContent(id3att.getAttributeContent());
				id3att1.setAttributeFigure(id3att.getAttributeFigure());
				id3att1.setAttributeParentFigure(id3att.getAttributeParentFigure());
				id3att1.setCategory(id3att.getCategory());
				
				if(id3att.getAttributeParentFigure() != attribute.getCategory())
					continue;
				
//				if((id3att.getAttributeFigure() & (long)Math.pow(2, 20)) != (long)Math.pow(2, 20))
//				{
				id3att1.setAttributeFigure(id3att.getAttributeFigure() | parent);
					
					listLong.add(id3att1);
//				}			
			}
		}
		return listLong;
	}
	
	//计算在某个集合下的某个类别的信息增益，0 为全体集合，Math.pow(2, 1) 为天气晴朗下的集合，
	//Math.pow(2, 1) | Math.pow(2, 11) 为天气晴朗及babymood下的集合
	private static double[] calculateAttributeGain(id3Attribute attribute,double allEntropy)
	{
		int count= data.length; //整个集合的记录数
		
		int attcount = 0;
		
		if(((long)Math.pow(2, 20) & attribute.getAttributeParentFigure()) == (long)Math.pow(2, 20)){
			
		       for(int i = 0;i < count; i ++){

		              id3data idata = allistdata.get(i);
		              
		              long val = idata.getValid() & attribute.getAttributeFigure();
		              
		              if(val == attribute.getAttributeFigure()){		            	  
			              
			              attcount = attcount+1;			              
		              }		         	  
		          } 
		       count = attcount ;
		}
		
		double[] EntropyVal = null;
		double allPlus = 0;
		
		ArrayList<id3Attribute> listLong= GetList(attribute);//new ArrayList<Long>();		

		double parentcategory= 0;
		
		for(int i = 0 ;i<listLong.size();i++)
		{
			EntropyVal = calculateAllEntropy(listLong.get(i));
			
			parentcategory = listLong.get(i).getAttributeParentFigure(); 	
			
			if(EntropyVal[1]==0){
			
			}
			else{
			   allPlus += EntropyVal[0]/count*EntropyVal[1];
			}
		}


	    double gain = allEntropy - allPlus;
	    
	    System.out.println(String.format("%1$s,信息增益为%2$f",attribute.getAttributeFigure()+attribute.getAttributeContent(),gain));
	    
	    double[] dgain = new double[2];
	    dgain[0] = gain;
	    dgain[1] = parentcategory;
	    return dgain;
	}
	
	
	
	//样本集合的信息熵
	private static double[] calculateAllEntropy(id3Attribute attr)
	{			                      
        double[] outpercent = calculateAllProbability(attr.getAttributeFigure(),"是");
        double[] nooutpercent = calculateAllProbability(attr.getAttributeFigure(),"否");
        double entropy = 0;
        
        double v1 = 0;
        double v2= 0;
        if(outpercent[0]!=0 ){
        	v1 = -outpercent[0]*Math.log(outpercent[0]);
        }
        
        if(nooutpercent[0]!=0 ){
        	v2 = -nooutpercent[0]*Math.log(nooutpercent[0]);
        }
        
       
        	entropy = v1+v2;  //-5/14log(5/14) - 9/14log(9/14) = 0.940
             
        
        System.out.println(String.format("%1$s—总样本集合出去玩的概率是:%2$f，不出去玩的概率是:%3$f,，样本集合的信息熵:%4$f",attr.getAttributeFigure()+attr.getAttributeContent(),outpercent[0],nooutpercent[0],entropy));
        
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
