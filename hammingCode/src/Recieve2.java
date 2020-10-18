
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class Recieve2 {
    public static void main(String[] args) {
		
		 int r = 0 ,
		     j=0 ;
		  
		System.out.println("Enter the recieved data code: "); 
                String recieve=new Scanner(System.in).next();
                String text=""  ;
                int N=recieve.length();
                int p=0;
                for(int i=0; i<N ; i++)
                {
                    if((Math.pow(2, p)-1==i))
                    {
                        p++;
                    }
                    else
                    {
                        text+=recieve.charAt(i);
                    }

                }
                
                System.out.println("DATA BIT: "+text);
		
		int len = text.length() ; 
		ArrayList codearray = new ArrayList() ;
			

		
		while(true)
		{
			if(len+r+1<=Math.pow(2,r))
			{
				break;
			}
			r++;
		}
		System.out.println("Number of parity bits : "+r);
		
		for (int i=0 ; i<len + r  ; i++) {
			 
			if (IsPowerTwo(i+1) ){
				codearray.add("?") ;
			}else {
				codearray.add(text.charAt(j));
				j++ ;
			}
		}
		
		
		System.out.println(codearray.toString()); 
                
                codearray=generateCode(codearray);
		System.out.println("Code word: ");
                String op=Arr_to_Str(codearray);
		System.out.println(op);
                
                
                
                
                ArrayList<Integer> er  = new ArrayList<Integer>(); 
                 String error="";
                int e=0;
                int sum=0;
                p=0;
                for(int i=0;i<recieve.length();i++)
                {

                        if(recieve.charAt(i)!= op.charAt(i))
                        {
                           if(Math.pow(2, p)-1==i)
                           {
                            error+=(i+1)+",";
                            er.add(i);
                            e+=1;

                            sum+=i;

                            if(i==0)
                            {
                               sum+=1;
                            }
                            p++;
                           }
                        }     
                }
                System.out.println("\nNUMBER OF ERRORS: "+e);
                if(e!=0)
                {
                     System.out.println("BITS HAVING ERROR: "+error);
                     System.out.println(" DEPENDANT BIT IS: "+(sum+1));
                }
                 

                op=op.substring(0,sum)+( Integer.parseInt(op.charAt(sum+1)+"") ^1)+op.substring(sum+1,op.length());
         
                System.out.println("THE CORECTED CODE IS: "+op);
                
                
                
                
                
                
                
		
	}
	public static ArrayList generateCode(ArrayList codearray)
        {
            ArrayList pos_arr = new ArrayList() ;
            
           
		for (int i=0 ; i< codearray.size() ; i++) {
			if (codearray.get(i) == "?") {
				int k ,s=1;
			
				for (k = i ; k < codearray.size() ; k++){
					
					if (i==0) {
						k++ ;
						pos_arr.add(k) ;
					}else {
					
					pos_arr.add(k+1) ;
				
					
					if(s % (i+1) == 0) {
						k += i+1 ;
						}
					}
					s++ ;
					
				}
			
				checkOnes(pos_arr,codearray,i) ;
				pos_arr.clear(); 
			}
			
			
		}
                return(codearray);
	
        }   
	public static boolean IsPowerTwo(int num){
		int checked = num & num -1 ; 
		if (checked == 0 ){
			return true ; 
		}else {
			return false ; 
		}
		
	}
	public static void checkOnes(ArrayList array,ArrayList codearray, int position ){
		
		int count =0;
		 
		for (int i=0 ; i < array.size(); i++) {
			int index = (int) array.get(i) -1 ;
			if (codearray.get(index) == "?"  ) {
				codearray.set(index,0) ; 
			}
			
			int num = Integer.parseInt(codearray.get(index).toString()) ;
			if (num == 1  ) {
				count++ ; 
			}
			if(count % 2 ==0 ){
				codearray.set(position, 0) ;
			}else {
				codearray.set(position, 1) ;
			}
			
		}
		count = 0 ;
		
	}
	public static String Arr_to_Str(ArrayList array){
            String s="";
		for (int i=0;i<array.size();i++){
			s+=array.get(i); 
		}
                return(s);
	}
}
/*ouput:
Enter the recieved data code: 
0100011
DATA BIT: 0011
Number of parity bits : 3
[?, ?, 0, ?, 0, 1, 1]
Code word: 
1000011

NUMBER OF ERRORS: 2
BITS HAVING ERROR: 1,2,
 DEPENDANT BIT IS: 3
THE CORECTED CODE IS: 1010011
BUILD SUCCESSFUL (total time: 17 seconds)

*/
