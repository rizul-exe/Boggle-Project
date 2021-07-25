package boggle;
import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
public class Trie 
{
	HashSet<String> words = new HashSet<>();
	private node root;
	public Trie() 
	{
		root = new node('\0');
	}
	class node
	{
	    char c;
	    node []child;
	    boolean end;

	    node(char c)
	    {
	        this.c=c;
	        end=false;
	        child=new node[26];
	    }
	}
	public void insert(String word)
    {
        node current=root;
        for(int i=0; i<word.length(); i++)
        {
            char c=word.charAt(i);
            if(current.child[c-'a']==null)
               current.child[c-'a']=new node(c);
               current=current.child[c-'a'];
        }
        current.end=true;
    }
	public boolean search(String word)
    {
        node node=getnode(word);
        return node != null && node.end;
    }
	private node getnode(String word)
    {
        node current=root;
        for(int i=0; i<word.length(); i++)
        {
            char c=word.charAt(i);
            if(current.child[c-'a']==null)return null;
            current=current.child[c-'a'];
        }
        return current;
    }
	public static void main(String[] args) throws Exception 
	{
		Trie root1 = new Trie();
        Scanner scan = new Scanner(new FileInputStream("mydictionary.txt"));
        while (scan.hasNext()) 
        {
            root1.insert(scan.next());
        }	
	}
}

