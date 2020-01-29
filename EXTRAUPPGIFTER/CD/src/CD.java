import java.util.HashSet;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class CD {
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

			boolean hasMoreTokens() {
				return peekToken() != null;
			}

			int getInt() {
				return Integer.parseInt(nextToken());
			}

			double getDouble() {
				return Double.parseDouble(nextToken());
			}

			long getLong() {
				return Long.parseLong(nextToken());
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
		int N = -1;
		int M = -1;
		
		while(true) {
			N = io.getInt();
			M = io.getInt();
			if(N == 0 && M == 0) {
				break;
			}
			
			HashSet<Integer> intArray = new HashSet<Integer>();
			int duplicateCount = 0;
			int input;
			for(int i = 0;i < N;i++) {
				input = io.getInt();
				intArray.add(input);
			}
			for(int i = 0;i < M;i++) {
				input = io.getInt();
				
				if(intArray.contains(input)) {
					duplicateCount++;
				} else {
					intArray.add(input);
				}
			}
			
			// kolla om N har en större jmf med M på samma index, om större, byt till nästa index. Input stegrar alltid.
			
			System.out.println(duplicateCount);
		}
		io.close();
	}
	
}
