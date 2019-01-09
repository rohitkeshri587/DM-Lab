import java.io.*;
import java.util.*;
public class Prog6 {
	static ArrayList<String[]> data ;
	static ArrayList<Comp> al;
	public Prog6(String filename)
	{
		data= new ArrayList<>();
		al= new ArrayList<>();
		parseCSV(filename);
	}
	private void parseCSV(String filename) 
	{
		try 
		{
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			String line;
			while((line=reader.readLine())!=null)
			{
				String row[] = line.split(",");
				data.add(row);
			}
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter filename:");
		String filename= sc.nextLine();
		Prog6 p = new Prog6(filename);
		System.out.println("Enter x,y,z\n");
		int x=sc.nextInt();
		int y=sc.nextInt();
		int z=sc.nextInt();
		for(String row[]:data)
		{
			int a= Integer.parseInt(row[0]);
			int b= Integer.parseInt(row[1]);
			int c= Integer.parseInt(row[2]);
			String cls = row[3];
			Double dis = p.distance(x,y,z,a,b,c);
			Comp comp = new Comp(a,b,c,cls,dis);
			al.add(comp);
		}
		Collections.sort(al, new Comparator<Comp>() {

			@Override
			public int compare(Comp o1, Comp o2) 
			{
				if(o1.dis == o2.dis)
					return 0;
				else if(o1.dis>o2.dis)
					return 1;
				else
					return -1;
			}
			
		});
		System.out.println("enter no of k");
		int k= sc.nextInt();
		int c1=0,c2=0,c3=0;
		for(Comp c:al)
		{
			if(k==0)
				break;
			if(c.cls.equals("1"))
				c1++;
			else if(c.cls.equals("2"))
				c2++;
			else
				c3++;
			k--;
		}
		if(c1>c2 && c1>c3)
			System.out.println("1");
		else if(c2>c1 && c2>c3)
			System.out.println("2");
		else
			System.out.println("3");
	}
	private Double distance(int x, int y, int z, int a, int b, int c) 
	{
		Double dis = Math.sqrt(((x-a)*(x-a))+((y-b)*(y-b))+((z-c)*(z-c)));
		return dis;
	}
}
