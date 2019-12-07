package com.codeforces.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Winner {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out, true);

		int wheel = Integer.parseInt(br.readLine());
		Map<String, Integer> map = new Hashtable<String, Integer>();
		int max = Integer.MIN_VALUE;
		String[] names = new String[wheel];
		int[] scores = new int[wheel];
		String[] tokens;

		for (int i = 0; i < wheel; i++) {
			tokens = br.readLine().split(" ");
			String name = tokens[0];
			int score = Integer.parseInt(tokens[1]);

			Integer lookUpScore = map.get(name);
			if (lookUpScore != null) {
				score += lookUpScore;
			}
			names[i] = name;
			scores[i] = score;
			map.put(name, score);
		}

		for (String key : map.keySet()) {
			if (map.get(key) > max) {
				max = map.get(key);
			}
		}

		for (int i = 0; i < wheel; i++) {
			if (scores[i] >= max && map.get(names[i]) == max) {
				out.println(names[i]);
				break;
			}
		}
	}
}