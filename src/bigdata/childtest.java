package bigdata;

public class childtest extends supertest {

	private static childtest child1 = new childtest(true);
	
	//private childtest child2 = new childtest("11");
	
	public childtest()
	{
		System.out.print("childtest\n");
	}
	public childtest(boolean chkok)
	{
		System.out.print("child boolean chkok\n");
	}
	public childtest(String str)
	{
		System.out.print("String str\n");
	}
}
