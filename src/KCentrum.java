
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class KCentrum {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		List<Miasto> listaM = new ArrayList<>();
		Random r = new Random(System.currentTimeMillis());
		
		int M = in.nextInt();
		int S = in.nextInt();
		double x,y;
		String capM = " ";
		
		for(int i=0; i<M; i++){
			x = in.nextDouble();
			y = in.nextDouble();			
			listaM.add(new Miasto(x,y));
			capM += i+" ";
			
		}
		double[] distFromM = new double[M];
		
		int p = r.nextInt(M);
		System.out.println("Pierwszy(losowo)" + p);
		capM = capM.replaceAll(" " + p + " ", " ");
		String w = "";
		w += p + " ";
		int iter = 0;
		for(int i=0; i<M; i++){
			distFromM[i] = listaM.get(i).getDistance(listaM.get(p));
		}
		for(int n=0; n<S-1; n++){
			double dist = 0;
			int nrMiasta = -1;
			for (String t : capM.split(" ")){//miast bez w
				
				if(!t.equals("")){
				int i = Integer.valueOf(t);
				
				iter++;				
				if(distFromM[i]>dist){
					dist = distFromM[i];
					nrMiasta = i;
				}
				}
				
				
			}
			if(nrMiasta != -1){
				w += "" + nrMiasta + " ";
				capM = capM.replaceAll(" " + nrMiasta + " ", " ");
				for(int i=0; i<M; i++){
					distFromM[i] += listaM.get(i).getDistance(listaM.get(nrMiasta));
				}
			}
			//System.out.println(listaM.get(i).getY());
		}
		
		System.out.println(w + " iter : " + iter);
		

	}

static class Miasto {
	private double x,y;
	public Miasto(double x, double y) {
		this.setX(x);
		this.setY(y);
	}
	
	public double getDistance(Miasto m){
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

}

//82 0 20 30 50 60 80 90 110 1 21 31 51 61 81 91 111 2 3 22 32 52 62 112 92 23 5 53 33 83  iter : 3045




