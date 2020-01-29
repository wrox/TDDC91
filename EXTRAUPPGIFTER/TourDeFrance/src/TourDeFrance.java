import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class TourDeFrance {
	public static void main(String[] args) throws Exception {
		class Kattio extends PrintWriter {
			Kattio(InputStream i) {
				super(new BufferedOutputStream(System.out));
				r = new BufferedReader(new InputStreamReader(i));
			}
			Kattio(InputStream i, OutputStream o) {
				super(new BufferedOutputStream(o));
				r = new BufferedReader(new InputStreamReader(i));
			}

			int getInt() {
				return Integer.parseInt(nextToken());
			}

			String getWord() {
				return nextToken();
			}

			private BufferedReader r;
			private String line;
			private StringTokenizer st;
			private String token;

			private String peekToken() {
				if (token == null)
					try {
						while (st == null || !st.hasMoreTokens()) {
							line = r.readLine();
							if (line == null) return null;
							st = new StringTokenizer(line);
						}
						token = st.nextToken();
					} catch (IOException e) { }
				return token;
			}

			private String nextToken() {
				String ans = peekToken();
				token = null;
				return ans;
			}
		}

		Kattio io = new Kattio(System.in);

		double result;

		while(true) {
			int intSprocketsFrontCount = io.getInt();
			if (intSprocketsFrontCount == 0) {
				break;
			}
			int intSprocketsRearCount = io.getInt();

			Double[] arrSprocketsFront = new Double[intSprocketsFrontCount];
			Double[] arrSprocketsRear = new Double[intSprocketsRearCount];
			ArrayList<Double> ratios = new ArrayList<Double>();
			result = 0;

			for(int i = 0; i < intSprocketsFrontCount; i++) {
				arrSprocketsFront[i] = new Double(io.getInt());
			}
			for(int i = 0; i < intSprocketsRearCount; i++) {
				arrSprocketsRear[i] = new Double(io.getInt());
			}
			for(int i = 0; i < intSprocketsFrontCount; i++) {
				for (int j = 0; j < intSprocketsRearCount; j++) {
					double a = new Double(arrSprocketsRear[j]);
					double b = arrSprocketsFront[i] / a;
					ratios.add(b);
				}
			}
			Collections.sort(ratios);
			for(int i = 1; i < ratios.size(); i++) {
				double a = ratios.get(i);
				double b = ratios.get(i-1);
				double c = a / b;
				result = result < c ? c : result;
			}
			String output = String.valueOf(Math.round(result * 100.0) / 100.0);
			//System.out.println(output.toString()); 
			if(output.length() == 1) {
				System.out.println(output + ".00");
			} else if(output.length() == 3) {
				System.out.println(output + "0");
			} else {
				System.out.println(output);
			}
			//System.out.printf("\n%.02f\n", result); 
		}
		io.close();
	}
}
