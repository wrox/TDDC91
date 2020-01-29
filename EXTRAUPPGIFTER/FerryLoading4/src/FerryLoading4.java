import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class FerryLoading4 {
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
		
		int intCarLength;
		int intCases = -1;
		int intFerryMaxLength = -1;
		int intFerryMaxCarLoad = -1;
		final String LEFT = "left";
		final String RIGHT = "right";
		String stringCarDirection;
		Kattio io = new Kattio(System.in);
		Queue<Integer> queueRight = new LinkedList<Integer>();
		Queue<Integer> queueLeft = new LinkedList<Integer>();
		Queue<Integer> queueResult = new LinkedList<Integer>();

		
//		System.out.print("Enter case count: ");
		intCases = io.getInt();
		
		for(int a = 1;a <= intCases;a++) {
			intFerryMaxLength = io.getInt() * 100; //convert from m to cm
			intFerryMaxCarLoad = io.getInt();
			
			for(int b = 1; b <= intFerryMaxCarLoad;b++) {
				intCarLength = io.getInt();
				stringCarDirection = io.getWord();
				
                if (stringCarDirection.equals(RIGHT)) {
                	queueRight.add(intCarLength);
                	continue;
                } 
                queueLeft.add(intCarLength);
			}
			
//			System.out.println("Array assignment for-loop finished");
			int intFerryCrossings = 0;
			int intFerryCurLoad = 0;
			String direction = LEFT;	
	
			while(!(queueRight.isEmpty() && queueLeft.isEmpty())) {
				if(direction.equals(LEFT)) {
					if(queueLeft.isEmpty() && !queueRight.isEmpty()) {
						direction = RIGHT;
						intFerryCrossings++;
					}
					while(!queueLeft.isEmpty()) {
						if((intFerryCurLoad + queueLeft.peek()) <= intFerryMaxLength) {
							intFerryCurLoad += queueLeft.poll();
							if(queueLeft.isEmpty()) {
								intFerryCrossings++;
								intFerryCurLoad = 0;
								direction = RIGHT;
//								System.out.println("(1) Left is null: " + intFerryCrossings);
								break;
							}
							continue;
						}
						intFerryCrossings++;
						intFerryCurLoad = 0;
						direction = RIGHT;
//						System.out.println("(2) Left curload is bigger than ferry max: " + intFerryCrossings);
						break;
					}
				} else if(direction.equals(RIGHT)) {
					if(queueRight.isEmpty() && !queueLeft.isEmpty()) {
						direction = LEFT;
						intFerryCrossings++;
					}
					while(!queueRight.isEmpty()) {
						if((intFerryCurLoad + queueRight.peek()) <= intFerryMaxLength) {
							intFerryCurLoad += queueRight.poll();
							if(queueRight.isEmpty()) {
								intFerryCrossings++;
								intFerryCurLoad = 0;
								direction = LEFT;
//								System.out.println("(3) Right is null: " + intFerryCrossings);
								break;
							}
							continue;
						}
						intFerryCrossings++;
						intFerryCurLoad = 0;
						direction = LEFT;
//						System.out.println("(4) Right curload is bigger than ferry max: " + intFerryCrossings);
						break;
					}
				}
			}	
			queueResult.add(intFerryCrossings);
		}
		while(!queueResult.isEmpty()) {
			System.out.println(queueResult.poll());
		}
		// We're done here John, close the shop
		io.close();
	}
}
