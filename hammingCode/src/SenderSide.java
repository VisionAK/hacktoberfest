/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
class SenderSide { 
  
  
    static String arrayToString(int ar[]) 
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
  
       
        String str = "1011"; 
        int M = str.length();
        
        int r = 1; 
  
        while (Math.pow(2, r) < (M + r + 1)) 
        { 

            r++; 
        } 
        int[] ar = generateCode(str, M, r); 
  
        System.out.println("Generated hamming code "); 
        
        System.out.println(arrayToString(ar));
    } 
} 
/*
output:
r1 = 0
r2 = 1
r4 = 0
Generated hamming code 
0110011
*/