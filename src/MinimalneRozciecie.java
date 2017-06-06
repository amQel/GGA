import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/*
8 15
1 2
2 3
1 4
1 5
2 4
2 5
4 5
5 6
5 8
3 6
3 7
3 8
6 7
6 8
7 8
 */


public class MinimalneRozciecie {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		List<Krawedz> listaP = new ArrayList<Krawedz>();
		int V = in.nextInt();
		int P = in.nextInt();


		int k,m;
		for(int i=0; i<P; i++){	
			k=in.nextInt();
			m=in.nextInt();
			
			listaP.add(new Krawedz(k, m));
		}
		int N = (int) ((V/2)*Math.floor(Math.log(V) + 1));
		
		double x = (1-(2/new Double(V*V)));
		double xx = Math.log(1/(new Double(V))) / Math.log(x);
		
		
		List<Krawedz> wynik = randomMinCut(listaP, V);
		for (int i=0; i<Math.floor(xx+1); i++){
			List<Krawedz> nowyWynik = randomMinCut(listaP, V);
			if(nowyWynik.size() <= wynik.size()){
				if((nowyWynik.size() < wynik.size()) || wynik.get(0).unq < nowyWynik.get(0).unq)
				wynik = nowyWynik;
			}
		}
		
		for(Krawedz k1 : wynik){
			System.out.println(k1.getK() + " " + k1.getM());
		}

	}
	
	private static List<Krawedz> randomMinCut(List<Krawedz> listaP, int V) {
		List<List<Krawedz>> G = new ArrayList<List<Krawedz>>();
		int[] unique = new int[V+1];
		int unq = 0;
		G.add(new ArrayList<Krawedz>());
		int i=0;
		for (int j=0; j<listaP.size(); j++){
			G.get(i).add(new Krawedz(listaP.get(j).getCurrentK(), listaP.get(j).getCurrentM()));
		}
		
		while (V>2){
			

			Random r = new Random(System.currentTimeMillis());
			int e = r.nextInt(G.get(i).size());
			
			Krawedz blade = G.get(i).get(e);

			i++;
			
			G.add(new ArrayList<Krawedz>());
			
			for (int j=0; j<G.get(i-1).size(); j++){
				
				if (j!=e) {
					G.get(i).add(new Krawedz(G.get(i-1).get(j).getK(), G.get(i-1).get(j).getM(),
							G.get(i-1).get(j).getCurrentK(), 
							G.get(i-1).get(j).getCurrentM()));
				}
				
			}
			
			for (int l=0; l<G.get(i).size(); l++){
					

				if(G.get(i).get(l).getCurrentK() == blade.getCurrentM()){
					G.get(i).get(l).setCurrentK(blade.getCurrentK());
				}
				
				if(G.get(i).get(l).getCurrentM() == blade.getCurrentM()){
					G.get(i).get(l).setCurrentM(blade.getCurrentK());
				}
				
				if(G.get(i).get(l).getCurrentM() == G.get(i).get(l).getCurrentK()){
					G.get(i).remove(l);
					l--;
				} else if(V==3 && l<G.get(i).size()){
					unique[G.get(i).get(l).getK()]++;
					unique[G.get(i).get(l).getM()]++;
				}
				
				

			}
			if(V==3){
				for(int p=0; p<unique.length; p++){
					if(unique[p]!=0) unq++;
				}
			}
			V--;
		}
		G.get(i).get(0).unq = unq;
		return G.get(i);
	
}

	static class Krawedz {
		public int unq;
		private int k,m;
		private int currentK,currentM;
		public int getK() {
			return k;
		}
	
		public void setK(int k) {
			this.k = k;
		}
	
		public int getM() {
			return m;
		}
	
		public void setM(int m) {
			this.m = m;
		}
	
		public int getCurrentK() {
			return currentK;
		}
	
		public void setCurrentK(int currentK) {
			this.currentK = currentK;
		}
	
		public int getCurrentM() {
			return currentM;
		}
	
		public void setCurrentM(int currentM) {
			this.currentM = currentM;
		}
	
		
		public Krawedz(int k, int m) {
			super();
			this.k = k;
			this.m = m;
			this.currentK = k;
			this.currentM = m;
		}

		public Krawedz(int k2, int m2, int currentK2, int currentM2) {
			this.k = k2;
			this.m = m2;
			this.currentK = currentK2;
			this.currentM = currentM2;
		}
	}
}
