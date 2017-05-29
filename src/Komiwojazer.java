import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;




public class Komiwojazer {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		List<Punkt> listaP = new ArrayList<>();
		int P = in.nextInt();
		double B[] = new double[P];
		
		double x,y;
		for(int i=0; i<P; i++){
			x = in.nextDouble();
			y = in.nextDouble();			
			listaP.add(new Punkt(x, y));
		}
		
		Collections.sort(listaP, new CustomCompare());
	
		
		B[1] = listaP.get(0).getDistance(listaP.get(1));
		
		for(int j=2; j<P; j++){
			double min = Double.MAX_VALUE;
			double suma = 0;
			int xx = -1;
				for(int i=j-2; i>=0; i--){
					double d = B[i+1] + listaP.get(i).getDistance(listaP.get(j)) + suma;
					//System.out.println(i + " " + j +" "+ B[i+1] + " " + listaP.get(i).getDistance(listaP.get(j)) + " " + suma);
					if(d<min) {
						min=d;
						xx = i;
					}
					suma += listaP.get(i).getDistance(listaP.get(i+1));
				}
			B[j] = min;

		}
		
		System.out.println("d³ugoœæ najkrótszej œcie¿ki  :" + (B[P-1] + listaP.get(P-1).getDistance(listaP.get(P-2))));
		
		

	}
	
static class Punkt {
		private double x,y;
		public Punkt(double x, double y) {
			this.setX(x);
			this.setY(y);
		}
		
		public double getDistance(Punkt m){
			double xdif = this.getX() - m.getX();
			double ydif = this.getY() - m.getY();
			return Math.sqrt((xdif*xdif) + (ydif*ydif));
		}

		public double getY() {
			return y;
		}

		public void setY(double y) {
			this.y = y;
		}

		public double getX() {
			
			return x;
		}

		public void setX(double x) {
			this.x = x;
		}
	}

public static class CustomCompare implements Comparator<Punkt>{

	@Override
	public int compare(Punkt o1, Punkt o2) {
		double x1= o1.getX(), x2=o2.getX();
		if(x1 > x2) return 1;
		else if(x1 < x2) return -1;
		else return 0;
	}
	
}
}
