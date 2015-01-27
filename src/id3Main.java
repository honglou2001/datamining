import java.util.ArrayList;
import java.util.Hashtable;

public class id3Main {
	
	private static  String[][] data = {
		{"开心","开心","晴朗","通畅","是"},
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
	
	private static ArrayList<id3data> allistdata = new ArrayList<id3data>(); // 原始数据 
	
	private static String[] categories = {"Babymood","Mothermood","Weather","Traffic","Outings"};
	
	//private static Hashtable<String, ArrayList<id3Attribute>> catetoryVal = new  Hashtable<String, ArrayList<id3Attribute>>();
	
	private static ArrayList<id3Attribute> allcatetoryVal = new  ArrayList<id3Attribute>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		
		InitialHashtable();
		
		Initialallistdata();
		
		calculateAllEntropy(0);
		
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
				long  val1= GetValByContent(data[i][j],j);
				
				val = val| val1;
			}

			iddata.setValid(val);
			
			allistdata.add(iddata);
		}		
	}
	
	private static long  GetValByContent(String content,long index)
	{
       for(int i = 0;i < allcatetoryVal.size(); i ++){
           if(allcatetoryVal.get(i).getAttributeContent() == content)
           {
        	   long parentfigure = (long)Math.pow(2, index*10);
        	   
        	   if(allcatetoryVal.get(i).getAttributeParentFigure() == parentfigure)
        	   {
        		   return allcatetoryVal.get(i).getAttributeFigure();
        	   }
           }
         }
	       
		return 0;
	}
	
	private static void InitialHashtable()
	{	
		id3Attribute attr = null;
				
		attr = new id3Attribute();
		
		attr.setAttributeContent("开心");
		attr.setAttributeParentFigure((long)Math.pow(2, 0));
		attr.setAttributeFigure((long)Math.pow(2, 1));
		allcatetoryVal.add(attr);
		
		attr = new id3Attribute();		
		attr.setAttributeContent("一般");
		attr.setAttributeParentFigure((long)Math.pow(2, 0));
		attr.setAttributeFigure((long)Math.pow(2, 2));
		allcatetoryVal.add(attr);
		
		attr = new id3Attribute();		
		attr.setAttributeContent("不开心");
		attr.setAttributeParentFigure((long)Math.pow(2, 0));
		attr.setAttributeFigure((long)Math.pow(2, 3));
		allcatetoryVal.add(attr);		
		
		attr = new id3Attribute();		
		attr.setAttributeContent("开心");
		attr.setAttributeParentFigure((long)Math.pow(2, 10));
		attr.setAttributeFigure((long)Math.pow(2, 11));
		allcatetoryVal.add(attr);
		
		attr = new id3Attribute();		
		attr.setAttributeContent("一般");
		attr.setAttributeParentFigure((long)Math.pow(2, 10));
		attr.setAttributeFigure((long)Math.pow(2, 12));
		allcatetoryVal.add(attr);
		
		attr = new id3Attribute();		
		attr.setAttributeContent("不开心");
		attr.setAttributeParentFigure((long)Math.pow(2, 10));
		attr.setAttributeFigure((long)Math.pow(2, 13));
		allcatetoryVal.add(attr);			
		
		attr = new id3Attribute();		
		attr.setAttributeContent("晴朗");
		attr.setAttributeParentFigure((long)Math.pow(2, 20));
		attr.setAttributeFigure((long)Math.pow(2, 21));
		allcatetoryVal.add(attr);
		
		attr = new id3Attribute();		
		attr.setAttributeContent("阴暗");
		attr.setAttributeParentFigure((long)Math.pow(2, 20));
		attr.setAttributeFigure((long)Math.pow(2, 22));
		allcatetoryVal.add(attr);
		
		attr = new id3Attribute();		
		attr.setAttributeContent("下雨");
		attr.setAttributeParentFigure((long)Math.pow(2, 20));
		attr.setAttributeFigure((long)Math.pow(2, 23));
		allcatetoryVal.add(attr);		
				
		attr = new id3Attribute();		
		attr.setAttributeContent("通畅");
		attr.setAttributeParentFigure((long)Math.pow(2, 30));
		attr.setAttributeFigure((long)Math.pow(2, 31));
		allcatetoryVal.add(attr);
		
		attr = new id3Attribute();		
		attr.setAttributeContent("拥堵");
		attr.setAttributeParentFigure((long)Math.pow(2, 30));
		attr.setAttributeFigure((long)Math.pow(2, 32));
		allcatetoryVal.add(attr);	
				
	}

	
	//样本集合的信息熵
	private static double calculateAllEntropy(long attrIndex)
	{			                      
        double outpercent = calculateAllProbability(0,"是");
        double nooutpercent = calculateAllProbability(0,"否");
        double entropy = -outpercent*Math.log(outpercent) - nooutpercent*Math.log(nooutpercent);  //-5/14log(5/14) - 9/14log(9/14) = 0.940
              
        System.out.println(String.format("%1$s—总样本集合出去玩的概率是:%2$f，不出去玩的概率是:%3$f,，样本集合的信息熵:%4$f",attrIndex,outpercent,nooutpercent,entropy));
        
        return entropy;        
	}	
	
	private static double calculateAllProbability(long infigure,String targetVal)
	{
        int count = allistdata.size();        
        int attributeCount = 0;
        
        for(int i = 0;i < count; i ++){

              id3data idata = allistdata.get(i);
              
              long val = idata.getValid() & infigure;
              if(val == 0){
	              String[] strArr = idata.getArrdata();
	              
	              if(strArr[4]== targetVal)
	              {
	            	  attributeCount = attributeCount+1;
	              }
              }
         	  
          }    
        
        double attributePercent = attributeCount*1.0/count;                
        return attributePercent;    		
	}	

}
