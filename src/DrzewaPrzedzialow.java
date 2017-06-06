import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;






public class DrzewaPrzedzialow {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		List<Punkt> listaP = new ArrayList<>();
		int P = in.nextInt();
		double B[] = new double[P];

		double x1,x2,y1,y2;
		for(int i=0; i<P; i++){
			x1 = in.nextDouble();
			y1 = in.nextDouble();	
			x2 = in.nextDouble();
			y2 = in.nextDouble();	
			listaP.add(new Punkt(x1,y1,x2,y2));
		}
		
		
		Tree cos = new Tree().constructIntervalTree(listaP);
		double x = in.nextDouble();
		cos.queryIntervalTree(x);
		

	}

static class Punkt {
		private double x1,y1;
		private double x2,y2;
		
		public Punkt(double x1, double y1, double x2, double y2) {
			super();
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

		public double getX2() {
			return x2;
		}

		public void setX2(double x2) {
			this.x2 = x2;
		}

		public double getY2() {
			return y2;
		}

		public void setY2(double y2) {
			this.y2 = y2;
		}

		public double getY() {
			return y1;
		}

		public void setY(double y) {
			this.y1 = y;
		}

		public double getX() {
			
			return x1;
		}

		public void setX(double x) {
			this.x1 = x;
		}

		public boolean contains(double x) {
			if(this.x1<this.x2 && this.x1<=x && x<=this.x2){
				System.out.println("punkt : " + this.x1 + " " + this.x2);
				return true;
			} else if(this.x1>this.x2 && this.x1>=x && x>=this.x2){
				System.out.println("punkt : " + this.x2 + " " + this.x1);
				return true;
			} else 

			return false;
		}
	}



static class Tree{
	public List<Punkt> Smed, Slewy, Sprawy, Llewy, Lprawy;
	public double Xmed;
	public Tree lewysyn,prawysyn;
	public boolean pusty = false;
	
	public Tree(){
		
	}
	
	public void queryIntervalTree(double x) {

		if(x<=this.Xmed){
			for(int i=0; i<Llewy.size(); i++){
				if(!Llewy.get(i).contains(x)) break;
			}
			if(!this.lewysyn.pusty) this.lewysyn.queryIntervalTree(x);
		} else {
			for(int i=Lprawy.size()-1; i>=0; i--){
				if(!Lprawy.get(i).contains(x)) break;
			}
			if(!this.prawysyn.pusty) this.prawysyn.queryIntervalTree(x);
		}
		
		
	}

	public Tree constructIntervalTree(List<Punkt> S){
		List<Double> iksy = new ArrayList<Double>();
		Tree newConstructTree = new Tree();
		if(S == null || S.size() == 0) {
			newConstructTree.pusty = true;
			return newConstructTree;
		}else {
			for(Punkt p : S){
				iksy.add(p.x1);
				iksy.add(p.x2);
			}
			
			Collections.sort(iksy, new CustomCompare());
			
			if(iksy.size()%2==1){ 
				newConstructTree.Xmed = iksy.get(iksy.size()/2);
			} else {
				newConstructTree.Xmed=(iksy.get((iksy.size()/2)-1) + iksy.get(iksy.size()/2))/2;
			}
			newConstructTree.Smed = new ArrayList<Punkt>();
			newConstructTree.Slewy = new ArrayList<Punkt>();
			newConstructTree.Sprawy = new ArrayList<Punkt>();
			
			for(Punkt p : S){
				if(p.getX()<newConstructTree.Xmed && p.getX2()<newConstructTree.Xmed){
					newConstructTree.Slewy.add(p);
				} else if(p.getX()>newConstructTree.Xmed && p.getX2()>newConstructTree.Xmed){
					newConstructTree.Sprawy.add(p);
				} else {
					newConstructTree.Smed.add(p);
				}
			}
			
			newConstructTree.Llewy = new ArrayList<Punkt>();
			newConstructTree.Lprawy = new ArrayList<Punkt>();
			
			for(Punkt p : newConstructTree.Smed){
				newConstructTree.Llewy.add(p);
				newConstructTree.Lprawy.add(p);
			}
			if(newConstructTree.Llewy!=null)Collections.sort(newConstructTree.Llewy, new LewyComparator());
			if(newConstructTree.Lprawy!=null)Collections.sort(newConstructTree.Lprawy, new PrawyComparator());
			
			newConstructTree.lewysyn = constructIntervalTree(newConstructTree.Slewy);
			newConstructTree.prawysyn = constructIntervalTree(newConstructTree.Sprawy);
			
			return newConstructTree;
		}
		
	}
}
public static class CustomCompare implements Comparator<Double>{
	@Override
	public int compare(Double o1, Double o2) {
		if(o1 > o2) return 1;
		else if(o1 < o2) return -1;
		else return 0;
	}
}
public static class LewyComparator implements Comparator<Punkt>{

	@Override
	public int compare(Punkt o1, Punkt o2) {
		double x,x2;
		if(o1.x1 < o1.x2) x = o1.x1;
		else x=o1.x2;
		if(o2.x1 < o2.x2) x2 = o2.x1;
		else x2=o2.x2;
		
		if(x > x2) return 1;
		else if(x < x2) return -1;
		else return 0;
	}
}

public static class PrawyComparator implements Comparator<Punkt>{

	@Override
	public int compare(Punkt o1, Punkt o2) {
		double x,x2;
		if(o1.x1 > o1.x2) x = o1.x1;
		else x=o1.x2;
		if(o2.x1 > o2.x2) x2 = o2.x1;
		else x2=o2.x2;
		
		if(x > x2) return 1;
		else if(x < x2) return -1;
		else return 0;
	}
}
}
