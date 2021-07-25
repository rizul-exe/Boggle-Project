package boggle;
import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
public class Board 
{
	HashSet<String> words = new HashSet<>(); 
	private Trie trie;
	public Board() 
	{
		trie_maker("mydictionary.txt");
	}
	void trie_maker(String name)
	
    {    
		trie = new Trie();
		Scanner scan;
        try {
            scan = new Scanner(new FileInputStream(name));
            while (scan.hasNext()) 
            {
                trie.insert(scan.next());
            }
            scan.close();
        } 
        catch (FileNotFoundException e) 
        {
            throw new RuntimeException(e);
        } 
    }
	void word_finder(char[][] boggle)
    {
        int m=boggle.length;
        int n=boggle[0].length;
        boolean[][] visited =new boolean[m][n];
        String str = "";

        // traverse all matrix elements
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                validator(boggle,visited,i,j,str,m,n);
            }
        }
    }
	void validator(char[][] boggle, boolean[][] visited, int i, int j, String str, int m, int n)
    {
        visited[i][j]=true;
        str+=boggle[i][j];
        if(trie.search(str))
        {
            words.add(str);
        }
        for(int row=i-1; row<=i+1 && row<m ; row++)
        {
            for (int col = j - 1; col <= j + 1 && col < n; col++)
            {
                if (row >= 0 && col >= 0 && !visited[row][col])
                    validator(boggle, visited, row, col, str, m, n);
            }
        }
        visited[i][j]=false;
    }
	public static void main(String[] args) 
	{
		Board board=new Board();
		char boggle[][] = 
			 { 
				 { 's', 'h', 'p', 'r' },
                 { 'o', 'o', 'e', 'e' },
                 { 'a', 't', 'e', 'd' },
                 { 'p', 'a', 'l', 'e' }
             };
		System.out.println("The boggle is as follows");
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				System.out.print(boggle[i][j]);
			}
			System.out.println();
		}
		System.out.println("The words found are as follows");
		 board.word_finder(boggle);
	     System.out.println(board.words);
	}
}
