import java.io.*;
import java.util.*;

public class Prog1 
{
	public ArrayList<String []> data;
	
	public Prog1(String filename)
	{
		data = new ArrayList<>();
		parseCSV(filename);
	}

	public void parseCSV(String filename)
	{
		try 
		{
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			String line;
			while((line=reader.readLine())!=null)
			{
				String row[]=line.split(",");
				data.add(row);
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Enter File Name : ");
		String filename =in.nextLine();
		Prog1 p = new Prog1(filename);
		p.aggregation(4,2);
		p.discretization(5,"output.csv");
		ArrayList<String[]>sample=p.stratiedsampling(6);
		Collections.shuffle(sample);
		for(String row[]:sample)
		{
			System.out.println(p.printrow(row));
		}
	}

	private ArrayList<String[]> stratiedsampling(int size) 
	{
		ArrayList<String[]> sample=new ArrayList<>();
		ArrayList<String[]> item1= new ArrayList<>();
		ArrayList<String[]> item2= new ArrayList<>();
		for(String row[]:data)
		{
			if(row[4].equals("male"))
				item1.add(row);
			else if(row[4].equals("female"))
				item2.add(row);
		}
		int i1=item1.size();
		int i2=item2.size();
		System.out.println("Gender : Male   Class size : " + i1);
		System.out.println("Gender : Female Class size : " + i2);
		int samp1=(int)((i1*size)/(i1+i2));
		int samp2=size-samp1;
		System.out.println("Gender : Male     Class sample size : " + samp1);
		System.out.println("Gender : Female   Class sample size : " + samp2);
		Random r =new Random();
		for(int i=1;i<=samp1;i++)
		{
			int t=r.nextInt();
			if(t<0)
				t=-t;
			sample.add(item1.get(t%i1));
		}
		for(int i=1;i<=samp2;i++)
		{
			int t=r.nextInt();
			if(t<0)
				t=-t;
			sample.add(item2.get(t%i1));
		}
		return sample;
	}

	private void discretization(int v, String output) 
	{
		try 
		{
			FileWriter writer = new FileWriter(output);
			for(String row[]:data)
			{
				writer.write(printrow(row)+",");
				Float value=Float.parseFloat(row[v]);
				String dis="";
				if(value < 200)
                    dis = "A";
                else if(value >= 200 && value < 400)
                    dis = "B";
                else if(value >= 400 && value < 600)
                    dis = "C";
                else if(value >= 600 && value < 800)
                    dis = "D";
                else if(value >= 800 && value < 1000)
                    dis = "E";
                else
                    dis = "F";
				writer.write(dis+"\n");
			}
			writer.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private  String printrow(String[] row) 
	{
		String new_row=Arrays.toString(row);
		return new_row.substring(1, new_row.length()-1);
	}

	private void aggregation(int g, int v) 
	{
		HashMap<String, Float[]> agg = new HashMap<>();
		for(String row[]:data)
		{
			if(agg.containsKey(row[g]))
			{
				Float minmax[] = agg.get(row[g]);
				Float value = Float.parseFloat(row[v]);
				if(value<minmax[0])
					minmax[0]=value;
				if(value>minmax[1])
					minmax[1]=value;
				agg.put(row[g], minmax);
			}
			else
			{
				Float value= Float.parseFloat(row[v]);
				agg.put(row[g],new Float[] {value,value});
			}
		}
		for(String key:agg.keySet())
		{
			Float value[]=agg.get(key);
			System.out.println("Gender :	"+key+"	Min :	"+value[0]+"	Max :	"+value[1] );
		}
	}

	
}