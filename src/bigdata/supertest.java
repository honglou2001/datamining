package bigdata;

public class supertest {
    
	private static supertest super1 = new supertest(true);
	//private supertest super2 = new supertest("122");
	
	public supertest(){
		
		System.out.print("1____\n");
	}
	
	public supertest(boolean chkOK){
		
		System.out.print("super boolean chkOK\n");
	}
	
	public supertest(String str){		
		System.out.print("String str\n");
	}
}
