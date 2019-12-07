package com.codeforces.problemset;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class AncientBerlandCircus {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		String[] inputs = input.split(" ");
		double x1 = Double.parseDouble(inputs[0]);
		double y1 = Double.parseDouble(inputs[1]);
		
		input = br.readLine();
		String[] inputs2 = input.split(" ");
		double x2 = Double.parseDouble(inputs2[0]);
		double y2 = Double.parseDouble(inputs2[1]);
		
		input = br.readLine();
		String[] inputs3 = input.split(" ");
		double x3 = Double.parseDouble(inputs3[0]);
		double y3 = Double.parseDouble(inputs3[1]);
		
		double a = Point2D.distance(x1, y1, x2, y2);
	    double b = Point2D.distance(x1, y1, x3, y3);
	    double c = Point2D.distance(x2, y2, x3, y3);
	    
	    double A = Math.acos((b * b + c * c - a * a) / (2 * b * c));
	    double B = Math.acos((a * a + c * c - b * b) / (2 * a * c)); 
	    double C = Math.acos((b * b + a * a - c * c) / (2 * a * b));
	    
	    double R = a / Math.sin(A) / 2;
	    double n = Math.PI / gcd(A, gcd(B, C));
	    double S = n / 2 * R * R * Math.sin(2 * Math.PI / n);
	 
	    System.out.print(S);
	}
 
	private static double gcd(double x, double y) {
		if (y < Math.PI / 100.0) {
			return x;
		} else {
			return gcd(y, x % y);
		}
	}
}