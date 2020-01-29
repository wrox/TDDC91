import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args){
        int numberOfTests = 0;
        int numberOfRides = 0;
        int ferryLength = 0;
        int numberOfCars = 0;
        int occupiedSpace = 0;
        String position = "left";
        int carLength = 0;
        String carPos = "null";

        class Kattio extends PrintWriter {
            public Kattio(InputStream i) {
                super(new BufferedOutputStream(System.out));
                r = new BufferedReader(new InputStreamReader(i));
            }
            public Kattio(InputStream i, OutputStream o) {
                super(new BufferedOutputStream(o));
                r = new BufferedReader(new InputStreamReader(i));
            }

            public int getInt() {
                return Integer.parseInt(nextToken());
            }

            public String getWord() {
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

        Kattio katt = new Kattio(System.in, System.out);

        Queue<Integer> carsLeft = new LinkedList<Integer>();
        Queue<Integer> carsRight = new LinkedList<Integer>();

        numberOfTests = katt.getInt();
        int[] testResults = new int[numberOfTests];

        for (int i = 0; i < numberOfTests; i++) {
            ferryLength = katt.getInt() * 100;
            numberOfCars = katt.getInt();
            numberOfRides = 0;
            occupiedSpace = 0;
            position = "left";
            for (int j = 0; j < numberOfCars; j++) {
                //currentCar = new car(scan.nextInt(), scan.nextLine());
                carLength = katt.getInt();
                carPos = katt.getWord();
                //              currentCar = new car(katt.getInt(), katt.getWord());
                if (carPos.equals("left")) {
                    carsLeft.add(carLength);
                } else {
                    carsRight.add(carLength);
                }
            }

            while (true) {
                if (position.equals("left")) {
                    if (carsLeft.peek() == null && carsRight.peek() != null) {
                        position = "right";
                        numberOfRides++;
                    }
                    while (carsLeft.peek() != null) {
//                        carLength = carsLeft.peek();
                        if (occupiedSpace + carsLeft.peek() <= ferryLength) {
                            occupiedSpace += carsLeft.poll();
//                            carsLeft.remove();
                            if (carsLeft.peek() == null) {
                                numberOfRides++;
                                position = "right";
                                occupiedSpace = 0;
                                break;
                            }
                        } else {
                            position = "right";
                            numberOfRides++;
                            occupiedSpace = 0;
                            break;
                        }
                    }
                } else if (position.equals("right")) {
                    if (carsLeft.peek() != null && carsRight.peek() == null) {
                        position = "left";
                        numberOfRides++;
                    }
                    while (carsRight.peek() != null) {
//                        carLength = carsRight.peek();
                        if (occupiedSpace + carsRight.peek() <= ferryLength) {
                            occupiedSpace += carsRight.poll();;
//                            carsRight.remove();
                            if (carsRight.peek() == null) {
                                numberOfRides++;
                                position = "left";
                                occupiedSpace = 0;
                                break;
                            }
                        } else {
                            position = "left";
                            numberOfRides++;
                            occupiedSpace = 0;
                            break;
                        }
                    }
                }
                if (carsLeft.peek() == null && carsRight.peek() == null) {
                    break;
                }
            }

            testResults[i] = numberOfRides;
        }
        for (int i = 0; i < numberOfTests; i++) {
            System.out.println(testResults[i]);
        }

        katt.close();
    }
}