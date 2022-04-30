package _220428.ChungLee;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class BOJ_20056_G4_마법사상어와파이어볼_short {
	static class Reader {
		int bfs = 1 << 16;
		byte[] buffer = new byte[bfs];
		int bufferPos = 0, bufferState = 0;
		DataInputStream dis = new DataInputStream(System.in);

		byte read() {
			if (bufferPos == bufferState) {
				try {
					bufferState = dis.read(buffer, bufferPos = 0, bfs);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (bufferState == -1)
					buffer[0] = -1;
			}
			return buffer[bufferPos++];
		}

		int nextInt() {
			int rtn = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do
				rtn = rtn * 10 + c - '0';
			while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -rtn;
			return rtn;
		}
	}

	static int K = 0, N;

	// 파이어볼을 저장
	static Queue<int[]> q = new LinkedList<>();
	// 방문 확정인 곳은 위치를 저장
	static Queue<int[]> confirmVisit = new LinkedList<>();
	
	static int[][] dydx = new int[][] { //
			{ -1, 0 }, { -1, 1 }, { 0, 1 }, //
			{ 1, 1 }, { 1, 0 }, { 1, -1 }, //
			{ 0, -1 }, { -1, -1 } };
	static int[][][] tmp;
	static int[] posYX;
	static int crntY, choose, crntX, mass, speed, direct, row, col, frCnt;

	static void MkNwFire() {
		while (!confirmVisit.isEmpty()) {
			posYX = confirmVisit.poll();

			crntY = posYX[0];
			crntX = posYX[1];

			mass = tmp[crntY][crntX][0];
			speed = tmp[crntY][crntX][1];
			frCnt = tmp[crntY][crntX][2];
			direct = tmp[crntY][crntX][3];
			choose = tmp[crntY][crntX][4];

			tmp[crntY][crntX] = new int[5];
			if (frCnt == 1) {
				q.add(new int[] { crntY, crntX, mass, speed, direct });
				continue;
			}

			// 합쳐진 질량이 5보다 작다면 증발
			if (mass < 5)
				continue;
			// 모두 방향이 같은 홀이나 짝일 때
			mass = mass/ 5;
			speed = speed / frCnt;

			// 항상 홀, 짝으로 나뉘어서 들어올 때
			if (choose == 1) {
				for (int i = 0; i < 8; i += 2)
					q.add(new int[] { crntY, crntX, mass, speed, i });
			}
			// 방향이 홀짝으로 통일되지 않을 때
			else {
				for (int i = 1; i < 8; i += 2)
					q.add(new int[] { crntY, crntX, mass, speed, i });
			}
		}
	}

	static int nextY, nextX;

	static void moveFire(int[] fire) {
		nextY = fire[0] + fire[3] * dydx[fire[4]][0] - 1;
		nextX = fire[1] + fire[3] * dydx[fire[4]][1] - 1;

		nextY = nextY % N;
		if (nextY < 0)
			nextY += N;
		nextX = nextX % N;
		if (nextX < 0)
			nextX += N;
		nextY += 1;
		nextX += 1;
		// 한 번도 방문 된 적이 없다면 q에 입력
		if (tmp[nextY][nextX][4] == 0)
			confirmVisit.add(new int[] { nextY, nextX });
		// 합쳐진 fb의 질량
		tmp[nextY][nextX][0] += fire[2];
		// 합쳐진 fb의 속도
		tmp[nextY][nextX][1] += fire[3];
		// fb의 갯수
		tmp[nextY][nextX][2]++;
		// 첫 fb의 방향을 가짐
		if (tmp[nextY][nextX][4] == 0) {
			tmp[nextY][nextX][3] = fire[4];
			tmp[nextY][nextX][4] = 1;
		}
		// 이미 fb가 저장되어있을 때 같은 홀짝을 구분
		else if (tmp[nextY][nextX][4] == 1) {
			if (tmp[nextY][nextX][3] % 2 != fire[4] % 2) {
				tmp[nextY][nextX][4] = 2;
			}
		}
	}

	static void simul() {
		int[] crnt;
		for (int i = 0; i < K; i++) {
			// 모든 파이어볼을 순회
			while (!q.isEmpty()) {
				crnt = q.poll();
				moveFire(crnt);
			}
			MkNwFire();
		}
	}

	public static void main(String[] args) {
		Reader r = new Reader();
		N = r.nextInt();
		int fb = r.nextInt();
		K = r.nextInt();
		// 총 질량
		// 처음 들어온 파이어볼의 방향
		// 모두 짝수거나 홀수 인지 판단 0 : 아무도 안들어왔을 때, 1 : 미정일 때, 2 : 서로 다른 홀짝으로 구성된 것이 확정일 때

		tmp = new int[N + 2][N + 2][5];
		for (int i = 0; i < fb; i++) {
			row = r.nextInt();
			col = r.nextInt();
			mass = r.nextInt();
			speed = r.nextInt();
			direct = r.nextInt();
			q.add(new int[] { row, col, mass, speed, direct });
		}
		simul();
		int sum = 0;
		while (!q.isEmpty()) {
			sum += q.poll()[2];
		}
		System.out.println(sum);
	}
}
