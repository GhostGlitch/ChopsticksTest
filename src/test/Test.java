package test;

import java.util.Arrays;

public class Test
{
	public static void main(String[] args)
	{
		int[] a = { 1, 1, 1, 1 };
		System.out.println(Arrays.deepToString(dostuff(a)));
	}

	private static int add(int startVal, int addVal)
	{
		if (startVal > 5 | addVal > 5 | startVal < 0 | addVal < 0)
			throw new IndexOutOfBoundsException();
		else
			return ((startVal + addVal) % 5);
	}

	private static int[][] dostuff(int[] thing)
	{
		/*
		 * Hand possibilities [index0][possibility#] // index0 2 = left hand
		 * value / index0 3 = right hand value / index0 4 = move // move 0 =
		 * cL-pL / move 1 = cL-pR / move 2 = cR-pL / move 3 = cR-pR
		 */
		int[][] pos = new int[5][16];
		for (int i = 0; i < 16; i++)
		{
			for (int hand = 0; hand < 4; hand++)
			{
				pos[hand][i] = thing[hand];
			}
		}
		for (int i = 0; i < 4; i++)
		{
			for (int i1 = 0; i1 < 4; i1++)
			{
				pos[4][(4 * i) + i1] = i;
			}
		}
		for (int i1 = 0; i1 < 4; i1++)
		{
			pos[0][(0) + i1] = add(pos[0][(0) + i1], thing[2]);
			pos[1][(4) + i1] = add(pos[0][(4) + i1], thing[2]);
			pos[0][(8) + i1] = add(pos[1][(8) + i1], thing[3]);
			pos[1][(12) + i1] = add(pos[1][(12) + i1], thing[3]);
		}
		int i = 0;
		for (int i1 = 0; i1 < 4; i1++)
		{
			for (int i2 = 2; i2 < 4; i2++)
			{
				for (int i3 = 0; i3 < 2; i3++, i++)
				{
					pos[i2][i] = add(pos[i2][i], pos[i3][i1 * 4]);
				}
			}
		}
		return pos;
	}
}
