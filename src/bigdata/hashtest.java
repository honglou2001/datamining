package bigdata;

public class hashtest {

	private static boolean ChkOK = false;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String IP = "127.0.0.1";
//		
//		int haship = IP.hashCode();
//		
//		String IP1 = "127.0.0.1";
//		
//		int haship1 = IP1.hashCode();
//		
//		String IP2 = new String("127.0.0.1");
//		
//		int haship2 = IP2.hashCode();
//		
//		System.out.println(String.format("haship:%1$s,%2$s,%3$s",haship%1024,haship1%1024,haship2%1024));
//		
//		System.out.println(String.format("haship:%1$s,%2$s",IP1==IP,IP1==IP2));
		
//		PrintStar(0);
		
//        new childtest();
		
		System.out.println(String.format("haship:%1$s",2 << 3 % 2 ));
	}
	
	
	public static void PrintStar(int count)
	{
		if((count<5) && (ChkOK == false))
		{
			count ++;
			
			for(int i=0;i<count;i++)
			{
				System.out.print("*");
				
			}
		}
		else			
		{
			
			ChkOK = true;
			
			count --;
			
			for(int i=0;i<count;i++)
			{
				System.out.print("*");
				
			}	
			
		}
		
		if(count<0)
		{
			return;
		}
		
		System.out.print("\n");
		PrintStar(count);
	
		
	}

}
