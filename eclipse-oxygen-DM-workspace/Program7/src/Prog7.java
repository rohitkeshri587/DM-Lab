import java.util.*;
import java.io.*;

public class Prog7 {
	ArrayList<Double[]> x;
	ArrayList<Double> y;
	int dimensions;
	Double weights[];
	public Prog7(String filename)
	{
		x = new ArrayList<>();
		y= new ArrayList<>();
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
				String[] row=line.split(",");
				dimensions=row.length-1;
				Double temp[] = new Double[dimensions];
				for(int i=0;i<dimensions;i++)
				{
					temp[i]=Double.parseDouble(row[i]);
				}
				x.add(temp);
				y.add(Double.parseDouble(row[dimensions]));
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
		System.out.println("Enter filename : ");
		String filename = in.nextLine();
		Prog7 p = new Prog7(filename);
		p.train(0.2,1000);
		Double[] test = {5.0,3.2,1.4,0.2};
		Double[] test2 = {3.4,4.9,2.7,0.1};
		System.out.println("Class : "+p.predict(test2));
		System.out.println("Class : "+p.predict(test));
	}

	private void train(double a, int e) 
	{
		dimensions=x.get(0).length;
		weights= new Double[dimensions+1];
		Random r= new Random();
		for(int i=0;i<dimensions+1;i++)
			weights[i]=r.nextDouble();
		System.out.println("Initial Weights :");
		printweights();
		while((e--)>0) {
		for(int i=0;i<x.size();i++)
		{
			int output = predict(x.get(i));
			double d = a*(y.get(i)-output);
			weights[0]+=d;
			for(int j=1;j<=dimensions;j++)
			{
				weights[j]+=d*x.get(i)[j-1];
			}
		}
		}
		System.out.println("Final Weights : ");
		printweights();
	}

	private int predict(Double[] data) 
	{
		int output=0;
		output+=weights[0];
		for(int i=1;i<=dimensions;i++)
		{
			output+=weights[i]*data[i-1];
		}
		if(output>0)
			return 1;
		else 
			return 0;
	}

	private void printweights() 
	{
		String w = Arrays.toString(weights);
		System.out.println(w.substring(1, w.length()-1));
	}
	
}
