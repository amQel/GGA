import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;






public class ProblemPlecakowy {
	static int NumberOfRandomized = 500;
	
	static double[][] Aresources;
	static double[][] Wresources;
	
	
	public static void main(String[] args) {
		
		
		Random r = new Random(System.currentTimeMillis());
		
		List<Rzecz> listaP = new ArrayList<>();
//		int P = in.nextInt();
//		
//		double waga,wartosc;
//		for(int i=0; i<P; i++){
//			waga = in.nextDouble();
//			wartosc = in.nextDouble();			
//			listaP.add(new Rzecz(waga, wartosc));
//		}
		int V = 0;
		for(int i=0; i<NumberOfRandomized; i++){
			listaP.add(new Rzecz(r.nextInt(100) + 1, r.nextInt(100) + 1));
			V+=listaP.get(i).getWaga();
		}
		
		int P = listaP.size();
		double S = 0;
		for(int i=0; i<listaP.size()/10; i++){
			S+= listaP.get(r.nextInt(P-2)).getRozmiar();
		}
		System.out.println("S : " + (int)S);
		
		Wresources = new double[listaP.size()][1 + (int) (S)];
		Aresources = new double[listaP.size()][V+2];
		
		for(int ni=0; ni<listaP.size(); ni++){
			for(int wi=0; wi<Wresources[ni].length; wi++){
				Wresources[ni][wi] = -1;
			}
			for(int ai=0; ai<V+2; ai++){
				Aresources[ni][ai] = -1;
			}
		}
		
		double[][] sss = Wresources;
		double[][] ss2 = Aresources;
		double t1 = System.currentTimeMillis();
		
		double p = W(P-1,S, listaP, sss);
		
		System.out.println("W : " + (System.currentTimeMillis() - t1));
		
		t1 = System.currentTimeMillis();
		double wynik = -1;
		for(int j=0; j<=V; j++){
			if(A(P-1, j, listaP, ss2)<=S) wynik = j;
		}
		System.out.println("A : " + (System.currentTimeMillis() - t1));
		System.out.println("max val : " + p
				+ " " + wynik);

		
		
	}
	
private static double W(int p, double s, List<Rzecz> listaP, double[][] sss) {
	//if(Wresources[p][(int)s] != -1 ) 
	if(sss[p][(int)s] != -1) return sss[p][(int)s];
	
	double waga = listaP.get(p).getWaga();
	double rozmiar = listaP.get(p).getRozmiar();
		if(p==0){
			if(s<rozmiar) return 0;
			else return waga;
		} else {
			if(s<rozmiar) return W(p-1, s, listaP, sss);
			else {
				double w1=W(p-1, s, listaP, sss), w2=W(p-1, s-rozmiar, listaP, sss) + waga;
				sss[p][(int)s] = Math.max(w1, w2);
				return sss[p][(int)s];
			}
		}
	}

private static double A(int i, double v, List<Rzecz> listaP, double[][] ss2){
	if(ss2[i][(int)v] != -1) return ss2[i][(int)v];
	
	double waga = listaP.get(i).getWaga();
	double rozmiar = listaP.get(i).getRozmiar();
	
	if(v == 0) return 0;
	else if(i == 0) {
		if(v == waga) return rozmiar;
		else return Integer.MAX_VALUE; 
	} else{
		if(waga > v ) return A(i-1, v, listaP, ss2);
		else {
			double r1 = A(i-1, v, listaP, ss2),
			r2 = A(i-1, v-waga, listaP, ss2) + rozmiar;
			ss2[i][(int)v] = Math.min(r1, r2);
			return ss2[i][(int)v];
		}
	}
	
}
static class Rzecz {
		private double rozmiar,waga;
		
		
		public double getWaga() {
			return waga;
		}
		public void setWaga(double waga) {
			this.waga = waga;
		}
		public Rzecz(double rozmiar, double waga) {
			super();
			this.rozmiar = rozmiar;
			this.waga = waga;
		}
		public double getRozmiar() {
			return rozmiar;
		}
		public void setRozmiar(double rozmiar) {
			this.rozmiar = rozmiar;
		}
		
		
	}


}
