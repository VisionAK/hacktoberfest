
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class ReceiverSide {
   
    static String arraytostring(int ar[]) 
    { 
        String s="";
        for (int i = 1; i < ar.length; i++) 
        { 
            s+=ar[i]; 
        } 
        return(s); 
    }
    
   
  
    static int[] generateCode(String str, int M, int r) 
    { 
        int[] ar = new int[r + M + 1]; 
        int j = 0; 
        for (int i = 1; i < ar.length; i++) 
        { 
            if ((Math.ceil(Math.log(i) / Math.log(2)) 
                 - Math.floor(Math.log(i) / Math.log(2))) 
                == 0) 
            { 
  
              
                ar[i] = 0; 
            } 
            else 
            {
                
                ar[i] = (int)(str.charAt(j) - '0'); 
                j++; 
            } 
        } 
        
        for (int i = 0; i < r; i++) 
        { 
            int x = (int)Math.pow(2, i); 
            for (int k = 1; k < ar.length; k++) 
            { 
                if (((k >> i) & 1) == 1) 
                { 
                    if (x != k) 
                        ar[x] = ar[x] ^ ar[k]; 
                } 
            } 
            System.out.println("r" + x + " = "+ ar[x]); 
        } 
        return ar; 
    } 
  
  
    public static void main(String[] args) 
    { 
  
       
        String str = "1000101"; 
        int N = str.length();
        String m="";
        int p=0;
       
        for(int i=0; i<N ; i++)
        {
            if(Math.pow(2, p)-1==i)
            {
                p++;
            }
            else
            {
                m+=str.charAt(i);
                
            }
           
        }
        int r=1;
        int M=m.length();
        while (Math.pow(2, r) < (M + r + 1)) 
        { 
            r++; 
        } 
        System.out.println("CODE RECIEVED:"+str);
        System.out.println("NO CHECK BITS:"+r);
        System.out.println("DATA: "+m);
        
         int[] ar = generateCode(m, m.length(), r); 
         System.out.print("Generated hamming code: "); 
         String cal=arraytostring(ar)+"";
         System.out.print(cal);
         String error="";
          ArrayList<Integer> er  = new ArrayList<Integer>(); 
         int e=0;
         int sum=0;
         p=0;
         for(int i=0;i<str.length();i++)
         {
             
                 if(str.charAt(i)!= cal.charAt(i))
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
         }
          System.out.println(" DEPENDANT BIT IS: "+(sum+1));
          
         cal=cal.substring(0,sum)+( Integer.parseInt(cal.charAt(sum+1)+"") ^1)+cal.substring(sum+2,cal.length());
         
         System.out.println("THE CORECTED CODE IS: "+cal);
   
    } 

}
/*
output:
run:
CODE RECIEVED:1000101
NO CHECK BITS:3
DATA: 0101
r1 = 0
r2 = 1
r4 = 0
Generated hamming code: 0100101
NUMBER OF ERRORS: 2
BITS HAVING ERROR: 1,2,
 DEPENDANT BIT IS: 3
THE CORECTED CODE IS: 011101
*/