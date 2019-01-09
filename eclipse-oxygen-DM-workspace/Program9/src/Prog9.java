import java.io.*;
import java.util.*;

public class Prog9 
{
	static ArrayList<Record> data;
	static ArrayList<Record> centroid;
	static ArrayList<Record> c1;
	static ArrayList<Record> c2;
	static ArrayList<Record> c3;
	public Prog9(String filename)
	{
		data = new ArrayList<>();
		centroid = new ArrayList<>();
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
				String row[]=line.split(",");
				Record r = new Record(Double.parseDouble(row[0]),Double.parseDouble(row[1]));
				data.add(r);
			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		Prog9 p = new Prog9("data.csv");
		centroid.add(data.get(0));
		centroid.add(data.get(1));
		centroid.add(data.get(2));
		c1 = new ArrayList<>();
		c2 = new ArrayList<>();
		c3 = new ArrayList<>();
		for(int i=0;i<100;i++)
		{
			c1.clear();
			c2.clear();
			c3.clear();
			for(Record r : data)
			{
				double a = dist(r.x,r.y,centroid.get(0).x,centroid.get(0).y);
				double b = dist(r.x,r.y,centroid.get(1).x,centroid.get(1).y);
				double c = dist(r.x,r.y,centroid.get(2).x,centroid.get(2).y);
				if(a<b && a<c)
					c1.add(r);
				else if(b<a && b<c)
					c2.add(r);
				else
					c3.add(r);
			}
			centroid.clear();
			centroid.add(calc(c1));
			centroid.add(calc(c2));
			centroid.add(calc(c3));
		}
		System.out.println("------0th cluster--------");
		for(Record r:c1)
			System.out.println(r.x +" "+r.y);
		System.out.println("------1th cluster--------");
		for(Record r:c2)
			System.out.println(r.x +" "+r.y);
		System.out.println("------2th cluster--------");
		for(Record r:c3)
			System.out.println(r.x +" "+r.y);
		System.out.println("-----centroid------");
		for(Record r: centroid)
			System.out.println(r.x+","+r.y);
	}

	private static Record calc(ArrayList<Record> c) 
	{
		double x=0.0;
		double y=0.0;
		for(Record r : c)
		{
			x+=r.x;
			y+=r.y;
		}
		x=x/c.size();
		y=y/c.size();
		Record test = new Record(x, y);
		return test;
	}

	private static double dist(double x1, double y1, double x2, double y2) 
	{
		
		return Math.sqrt(((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2)));
	}
}
