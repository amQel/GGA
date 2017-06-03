import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;






public class ProblemPlecakowy {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		List<Rzecz> listaP = new ArrayList<>();
		int P = in.nextInt();
		double B[] = new double[P];
		
		double waga,wartosc;
		for(int i=0; i<P; i++){
			waga = in.nextDouble();
			wartosc = in.nextDouble();			
			listaP.add(new Rzecz(waga, wartosc));
		}
		double S = in.nextDouble();
		in.close();
		double p = W(P-1,S, listaP);
		System.out.println("max val : " + p);

		
		
	}
	
private static double W(int p, double s, List<Rzecz> listaP) {
	double waga = listaP.get(p).getWaga();
	double rozmiar = listaP.get(p).getRozmiar();
		if(p==0){
			if(s<rozmiar) return 0;
			else return waga;
		} else {
			if(s<rozmiar) return W(p-1, s, listaP);
			else {
				double w1=W(p-1, s, listaP), w2=W(p-1, s-rozmiar, listaP) + waga;
				return Math.max(w1, w2);
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
