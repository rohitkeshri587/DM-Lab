import java.io.*;
import java.util.*;

public class Prog2 
{	
	static ArrayList<String[]> data;
	
	public Prog2(String filename)
	{
		data = new ArrayList<>();
		parseCSV(filename);
	}
	
	private void parseCSV(String filename)
	{
		try 
		{
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			String line;
			while((line = reader.readLine())!= null)
			{
				String row[] = line.split(",",-1);
				data.add(row);  
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) 
	{
		Prog2 p = new Prog2("dataset.csv");
		double sum=0.0,avg=0.0;		
		HashMap<String, Integer> c = new HashMap<>(); 
		int temp=0,max=0;
		String rep="";
		for(String[] row : data)
		{
			if(!(row[0].isEmpty()))
			{
				sum+=Double.parseDouble(row[0]);
			}
		}
		avg=sum/data.size();
		replace(0,Double.toString(avg));
		for(String[] row : data)
		{
			if(!(row[1].isEmpty()))
			{
				if(c.containsKey(row[1]))
				{
					temp= c.get(row[1]);
					c.put(row[1], temp+1);
				}
				else
					c.put(row[1], 1);
			}
		}
		for(String key : c.keySet())
		{
			if(c.get(key)>max)
			{
				max=c.get(key);
				rep = key;				
			}
		}
		replace(1,rep);
		printdata();
	}

	private static void printdata() 
	{
		for(String row[]:data)
		{
			String new_row=Arrays.toString(row);
			System.out.println(new_row.substring(1, new_row.length()-1));
		}
	}

	private static void replace(int i, String value) 
	{
		for(String[] row : data )
		{
			if(row[i].isEmpty())
				row[i]= value;
		}
	}

}
