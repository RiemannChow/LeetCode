package com.codeforces.contests;

import java.io.PrintWriter;
import java.util.Scanner;
 
public class SecretPasswords {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int wheel = in.nextInt();
		String temp;
		boolean[] total = new boolean[26], flag;
		DSU obj = new DSU();
		
		while(wheel-- > 0) {
			temp = in.next();
			flag = new boolean[26];
			
			for (char item : temp.toCharArray()) {
				flag[item - 'a'] = true; 
			}
			for (int i = 0; i < 26; i++) {
				if (flag[i]) {
					total[i] = true;
					obj.union(temp.charAt(0) - 'a', i);
				}
			}
		}
		
		int count = 0;
		for (int i = 0; i < 26; i++) {
			if (total[i] && obj.getParent(i) == i) {
				count += 1;
			}
		}
		out.println(count);
		out.flush();
	}
}
 
class DSU {
	int[] p;
	DSU() {
		p = new int[26];
		for (int i = 0; i < 26; i++) {
			p[i] = i;
		}
	}
	
	int getParent(int i) {
		if (p[i] == i) {
			return i;
		}
		return p[i] = getParent(p[i]);
	}
	
	void union(int i, int j) {
		i = getParent(i);
		j = getParent(j);
		if (i != j) {
			p[j] = getParent(i);
		}
	}
}