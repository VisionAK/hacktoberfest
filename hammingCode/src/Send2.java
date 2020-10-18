import java.util.ArrayList;
import java.util.Scanner;


public class Send2 {
		
		
		
	public static void main(String[] args) {
		
		 int r = 0 ,
		     j=0 ;
		  
		System.out.println("Enter the data code: "); 
                
                
		String text = new Scanner(System.in).next() ;
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
		Arr_to_Str(codearray) ;
		
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
	public static void Arr_to_Str(ArrayList array){
		for (int i=0;i<array.size();i++){
			System.out.print(array.get(i)); 
		}
	}
	
	

}

/*
ouput:
Enter the data code: 
1011
Number of parity bits : 3
[?, ?, 1, ?, 0, 1, 1]
Code word: 
0110011
*/