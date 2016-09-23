/**Flávio Prado;
 * Guilherme Wenger;
 * Renato Otto.
 * */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexandre
 */


public class ep03 {
	/**
     * @param args the command line arguments
     */
	
    public static void main(String[] args) {
 	
    	String pathToFile = args[0];
        File fl = new File(pathToFile);        
        String line;
        
    	File pathToOutputFile = new File("Saída.txt");
    	FileWriter output;
    	PrintWriter printer;
    	String result="";
    	
        try {
            BufferedReader br = new BufferedReader(new FileReader(fl));
            output = new FileWriter(pathToOutputFile);
    		printer = new PrintWriter(output);
            
            while (br.ready()) {
                
                line = br.readLine();
                line = line.replace("{", "");
                line = line.replace("}", "");
                
                if (line.contains("u")) {
                	String numbers = line;
                 	numbers = numbers.replace("u", "");
                 	numbers = numbers.replace(",","");
                 	numbers = numbers.replace("  "," ");
                 	numbers = numbers.replace(" ",",");
                 	
                 	String[] values = new String[numbers.length()];
                 	values = numbers.split(",");
                 	int larger  = 0;
                 	int[] array = new int[values.length];
                 	
                 	for(int i=0; i<array.length;i++){
                 		array[i]=Integer.parseInt(values[i]);
                 		if(array[i]>larger){
                  			larger=array[i];
                  		}
                 	}
                 	result = union(array,(larger+1)); 
                 	result = result.substring (0, result.length() - 2);
        			printer.printf("{"+result+"} %n");
        		
                 }
                
                 if (line.contains("i")) {
                    //insira aqui o método ou objeto que trata das intersecções
                	String numbers = line;
                	String[] sequences = numbers.split("i"); 
                 	numbers = numbers.replace("i", "");
                 	numbers = numbers.replace(",","");
                 	numbers = numbers.replace("  "," ");
                 	numbers = numbers.replace(" ",",");
                 	
                 	String[] values = new String[numbers.length()];
                 	values = numbers.split(",");
                 	
                 	int[] array = new int[values.length];
                 	int larger  = 0;
                 	for(int i=0; i<array.length;i++){
                 		array[i]=Integer.parseInt(values[i]);
                 		if(array[i]>larger){
                  			larger=array[i];
                  		}
                 	}
                  	
                 	               	
                  	String firstSequence = sequences[0];
                  	String secondSequence = sequences[1];
                  	result = intersection(firstSequence,secondSequence, array, larger+1); 
                 	result = result.substring (0, result.length() - 1);
        			printer.printf("{"+result+"} %n");                 
                }
                 
                 if (line.contains("s")) {
                    //insira aqui o método ou objeto que trata das intersecções
                	String numbers = line;
                 	String[] sequences = numbers.split("s"); 
                  	numbers = numbers.replace("s", "");
                  	numbers = numbers.replace(",","");
                  	numbers = numbers.replace("  "," ");
                  	numbers = numbers.replace(" ",",");
                  	
                  	String[] values = new String[numbers.length()];
                  	values = numbers.split(",");
                  	
                  	int[] array = new int[values.length];
                  	int larger  = 0;
                  	for(int i=0; i<array.length;i++){
                  		array[i]=Integer.parseInt(values[i]);
                  		if(array[i]>larger){
                  			larger=array[i];
                  		}
                  		
                  	}
                  	String firsSequence = sequences[0];
                  	result = subtraction(firsSequence,array, larger+1); 
                 	result = result.substring (0, result.length() - 2);
        			printer.printf("{"+result+"} %n");
                }
                 
                 if (!line.contains("u") && !line.contains("i") && !line.contains("s")) {
                    //insira aqui o método ou objeto que trata dacardinalidade
                	printer.printf(cardinality(line)+"%n");
                }                 
            }                       
            
            br.close();
            printer.close();
    		output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ep03.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ep03.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static String union(int[] array, int m ){
    	int n = array.length;
        String result="";
        int auxiliaryArray[] = new int[m];
         
        for(int i = 0; i < m; i++){
        	auxiliaryArray[i] = 0;
        }
         
        for(int i = 0; i < n; i++){
        	auxiliaryArray[array[i]]++;
        
        }
        
        for (int i = 0; i < auxiliaryArray.length; i++) {
			if(auxiliaryArray[i]>0){
				result += i+", ";
			}
		}
       
        return result;

     }
     
    public static String intersection(String fs, String ss, int[] array,int m){
    	if(fs.equals(ss)){
    		return fs;
    	}else{
    		int n = array.length;
            int auxiliaryArray[] = new int[m];
            String result ="";
            for(int i = 0; i < m; i++){
            	auxiliaryArray[i] = 0;
            }
            
        	for(int i = 0; i < n; i++){
        		auxiliaryArray[array[i]]++;
            }
        	
        	for (int i = 0; i < auxiliaryArray.length; i++) {
				if(auxiliaryArray[i]>1){
					result += i+",";
				}
			}
            
          return result;
    	}
    	   	
    }
    
    public static String subtraction(String fs, int[] array,int m){
    	int n = array.length;
        int auxiliaryArray[] = new int[m];
        String result ="";
        for(int i = 0; i < m; i++){
        	auxiliaryArray[i] = 0;
        }
        
    	for(int i = 0; i < n; i++){
    		auxiliaryArray[array[i]]++;
        }
    	
    	for (int i = 0; i < auxiliaryArray.length; i++) {
			if(auxiliaryArray[i]==1 && fs.contains(String.valueOf(i))){
				result += i+", ";
			}
		}
      
        return result;
	}
    
    public static String cardinality(String line){
    	line = line.replace(",","");
     	line = line.replace("  "," ");
     	line = line.replace(" ",",");
     	String [] values = line.split(",");
     	String result = String.valueOf(values.length);
     	return result;
    }
    
}
