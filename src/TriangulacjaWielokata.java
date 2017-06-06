import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;




public class TriangulacjaWielokata {

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
		Stack<Punkt> S = new Stack<Punkt>();
		S.push(listaP.get(0));
		S.push(listaP.get(1));
		double yB = listaP.get(0).getY();
		int lancuch = listaP.get(1).getY() > yB ? 1:-1;
		System.out.println("("+listaP.get(0).getX() + ", " + listaP.get(0).getY() + ") - (" + listaP.get(1).getX()+ ", " + listaP.get(1).getY() + ")");
		
		for (int i=2; i<listaP.size()-1; i++){
			System.out.println("iter" + i);
			for(Punkt xx : S){
				System.out.print(xx.getX() +" " + xx.getY() + "|| ");
			}
			
			Punkt currentPunkt = listaP.get(i);
			int lancuchStatus = currentPunkt.getY() > yB ? 1:-1;
			System.out.println("Iteracja : " + i + " status " + lancuchStatus + " " + lancuch);
			if(lancuchStatus != lancuch){
				
				Punkt p = S.pop();
				System.out.println("("+p.getX() + ", " + p.getY() + ") - (" + currentPunkt.getX()+ ", " + currentPunkt.getY() + ")");
				while(!S.isEmpty()){

					Punkt p2 = S.pop();
					System.out.println("("+p2.getX() + ", " + p2.getY() + ") - (" + currentPunkt.getX()+ ", " + currentPunkt.getY() + ")");
				}
				yB = p.getY();

				lancuch = currentPunkt.getY() > yB ? 1:-1;
				
				S.push(p);
				S.push(currentPunkt);
			} else {
				if(currentPunkt.getY() == 0.5){
					System.out.println("dla 4");
				}
				List<Punkt> sprawdzP = new ArrayList<>();
				sprawdzP.add(S.pop());//0
				
				if(lancuchStatus * currentPunkt.getY() > lancuchStatus * sprawdzP.get(0).getY()) { // na œcie¿ce 
					System.out.println("("+sprawdzP.get(0).getX() + ", " + sprawdzP.get(0).getY() + ") - (" + currentPunkt.getX()+ ", " + currentPunkt.getY() + ")");
					
					Punkt p = S.pop();
					sprawdzP.add(p);//1
					double a = p.getA(currentPunkt.getX(), currentPunkt.getY());
					double b = p.getB(a);
					int iTy=1;
					
					
					while(lancuchStatus * sprawdzP.get(iTy-1).getY() > lancuchStatus * ((a*sprawdzP.get(iTy-1).getX() + b))){
						System.out.println("tam "+"("+p.getX() + ", " + p.getY() + 
								") - (" + currentPunkt.getX()+ ", " + currentPunkt.getY() + ")");
						p = S.pop();
						sprawdzP.add(p);
						a = p.getA(currentPunkt.getX(), currentPunkt.getY());
						b = p.getB(a);
						iTy++;
					}
					
					
					
				}else {
					System.out.println("("+sprawdzP.get(0).getX() + ", " + sprawdzP.get(0).getY() + 
							") - (" + currentPunkt.getX()+ ", " + currentPunkt.getY() + ")");
					
					Punkt p = S.pop();
					sprawdzP.add(p);//1
					double a = p.getA(currentPunkt.getX(), currentPunkt.getY());
					double b = p.getB(a);
					int iTy=1;
					
					
					while(!(lancuchStatus * sprawdzP.get(iTy-1).getY() > lancuchStatus * ((a*sprawdzP.get(iTy-1).getX() + b)))){
						
						if(!S.empty()){
							p = S.pop();
							sprawdzP.add(p);
							a = p.getA(currentPunkt.getX(), currentPunkt.getY());
							b = p.getB(a);
							iTy++;
						} else {
							System.out.println("tu"+"("+p.getX() + ", " + p.getY() + 
									") - (" + currentPunkt.getX()+ ", " + currentPunkt.getY() + ")");
							break;
						}
						
					}
				}
//				for(int j=0; j<sprawdzP.size(); j++){
//					S.push(sprawdzP.get(j));
//				}
				S.push(sprawdzP.get(sprawdzP.size()-1));
				S.push(sprawdzP.get(sprawdzP.size()-2));
				S.push(currentPunkt);
				
				if(S.size() == 2){
					yB = sprawdzP.get(0).getY();
					lancuchStatus = currentPunkt.getY() > yB ? 1:-1;
				}
			}
		}
		

	}
static class Punkt {
		private double x,y;
		public Punkt(double x, double y) {
			this.setX(x);
			this.setY(y);
		}
		
		public double getB(double a) {
			
			return y-(a*x);
		}

		public double getA(double x2, double y2) {
			return (y2-y)/(x2-x);
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





/*
11
8 2
4 0,5
3 0
2 3
0 1
1 0
6 1
5 0,6
7 4
9 3
10 0,9
*/