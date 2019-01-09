import java.io.*;
import java.util.*;

public class prog5 
{
	static ArrayList<Temp> y;
	static ArrayList<Temp> n;
	
	public prog5(String filename)
	{
		y = new ArrayList<>();
		n = new ArrayList<>();
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
				Temp t = new Temp(Double.parseDouble(row[0]),row[1],row[2]);
				if(row[2].equals("Y"))
					y.add(t);
				else
					n.add(t);
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
		Scanner in = new Scanner(System.in);
		prog5 p = new prog5("data.csv");
		double py,py1,py2,py3,meany,sdy,expy;
		double pn,pn1,pn2,pn3,meann,sdn,expn;
		double t = y.size()+n.size();
		System.out.println("Enter weight between 1 to 60 and size (S/M/L)");
		double wgt = in.nextDouble();
		String size = in.next();
		
		py1=y.size()/t;
		py2=p.probability(y,size);
		meany = mean(y);
		sdy = sd(y,meany);
		expy = Math.pow(wgt-meany, 2)/(2*sdy*sdy);
		py3= Math.exp(-1*expy)/(Math.sqrt(2*3.14)*sdy);
		py = py1*py2*py3;
		
		pn1=n.size()/t;
		pn2=p.probability(n, size);
		meann= mean(n);
		sdn= sd(n,meann);
		expn= Math.pow(wgt-meann, 2)/(2*sdn*sdn);
		pn3= Math.exp(-1*expn)/(Math.sqrt(2*3.14)*sdn);
		pn = pn1*pn2*pn3;
		
		if(py>pn)
			System.out.println("Class : Y");
		else
			System.out.println("Class : N");
	}

	private static double sd(ArrayList<Temp> sample, double mean) 
	{
		double n =0.0;
		for(Temp t : sample)
		{
			n+= Math.pow(t.w-mean, 2);
		}
		int x = sample.size();
		return Math.sqrt((n/(x*(x-1))));
	}

	private static double mean(ArrayList<Temp> sample) 
	{
		double n=0;
		for(Temp t : sample)
		{
			n+=t.w;
		}
		return (n/sample.size());
	}

	private double probability(ArrayList<Temp> sample, String size) 
	{
		double sum=0.0;
		for(Temp t : sample)
		{
			if(t.s.equals(size))
				sum++;
		}
		return (sum/sample.size());
	}
}
