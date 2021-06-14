/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro.Domini;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;

/**
 *
 * @author berni
 */
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class IOKakuro {
    
    private FileWriter WrKakuroGuardat;
    BufferedReader RdFitchers;
    
    public IOKakuro(){
        
        WrKakuroGuardat = null;
        RdFitchers = null;
    }
    
     //Crea un fitxer amb el nom KakuroGeneratAutomaticament amb el contingut 
    //del kakuro passat com a parametre utilitzant el format especificat
    public void GuardarTaulerEnFitxerSenseMostrarCellesBlanques(Cella[][] Tauler, String NomFitxer) throws IOException{
        
        WrKakuroGuardat = new FileWriter("../src/kakuro/Dades/"+NomFitxer);
        
        int numFiles = Tauler.length;
        int numColumnes = Tauler[0].length;
        
        WrKakuroGuardat.write(numFiles + "," + numColumnes); 

        WrKakuroGuardat.write("\n");
        
        
        for(int i= 0; i<numFiles; ++i){
            
            for(int j = 0; j<numColumnes; ++j){
                
                if(Tauler[i][j] instanceof CellaNegra){
                    
                    if(Tauler[i][j].getValorFila()==-1 && Tauler[i][j].getValorColumna()==-1){
                        
                        WrKakuroGuardat.write("*");
                        
                    }else if(Tauler[i][j].getValorColumna()!=-1 && Tauler[i][j].getValorFila()==-1){
                        
                        WrKakuroGuardat.write("C" + Tauler[i][j].getValorColumna());
                        
                    }else if(Tauler[i][j].getValorColumna()==-1 && Tauler[i][j].getValorFila()!=-1){
                        
                        WrKakuroGuardat.write("F" + Tauler[i][j].getValorFila());
                    }else{
                        
                        WrKakuroGuardat.write("C" + Tauler[i][j].getValorColumna() + "F" + Tauler[i][j].getValorFila());
                        
                    }
                    
                    
                
                }else{
                    
                    WrKakuroGuardat.write("?");        
                    
                }
                
                
                if(j<numColumnes-1){
                        
                    WrKakuroGuardat.write(",");
                }
                
                if(j==(numColumnes-1)){
                    
                    WrKakuroGuardat.write("\n");
                }
                
            }
        }
        
        WrKakuroGuardat.close();
        
    }
    
    public void GuardarTaulerEnFitxer(Cella[][] Tauler, String NomFitxer) throws IOException{
        
        WrKakuroGuardat = new FileWriter("../src/kakuro/Dades/"+NomFitxer);
        
        int numFiles = Tauler.length;
        int numColumnes = Tauler[0].length;
        
        WrKakuroGuardat.write(numFiles + "," + numColumnes); 

        WrKakuroGuardat.write("\n");
        
        
        for(int i= 0; i<numFiles; ++i){
            
            for(int j = 0; j<numColumnes; ++j){
                
                if(Tauler[i][j] instanceof CellaNegra){
                    
                    if(Tauler[i][j].getValorFila()==-1 && Tauler[i][j].getValorColumna()==-1){
                        
                        WrKakuroGuardat.write("*");
                        
                    }else if(Tauler[i][j].getValorColumna()!=-1 && Tauler[i][j].getValorFila()==-1){
                        
                        WrKakuroGuardat.write("C" + Tauler[i][j].getValorColumna());
                        
                    }else if(Tauler[i][j].getValorColumna()==-1 && Tauler[i][j].getValorFila()!=-1){
                        
                        WrKakuroGuardat.write("F" + Tauler[i][j].getValorFila());
                    }else{
                        
                        WrKakuroGuardat.write("C" + Tauler[i][j].getValorColumna() + "F" + Tauler[i][j].getValorFila());
                        
                    }
                    
                    
                
                }else if(Tauler[i][j] instanceof CellaBlanca){
                    
                    if(Tauler[i][j].getValor()!= -1){
                        
                        WrKakuroGuardat.write(String.valueOf(Tauler[i][j].getValor())); 
                        
                    }else{
                        
                       WrKakuroGuardat.write("?");  
                    }
                           
                    
                }
                
                
                if(j<numColumnes-1){
                        
                    WrKakuroGuardat.write(",");
                }
                
                if(j==(numColumnes-1)){
                    
                    WrKakuroGuardat.write("\n");
                }
                
            }
        }
        
        WrKakuroGuardat.close();
        
    }
    
        //Imprimeix en la consola el tauler generat automaticament present en el fitxer de la capa de dades
    public void ImprimirTaulerDeFitxerEnConsola(String NomFitxer) throws FileNotFoundException, IOException{
        
        RdFitchers = new BufferedReader(new FileReader("../src/kakuro/Dades/"+NomFitxer)); 
        
        String NumFilesINumColumnes = RdFitchers.readLine();
        
        System.out.println(NumFilesINumColumnes); 
        
        String[] FilesIColumnesSeparat = NumFilesINumColumnes.split(",");
       
        int numFiles = Integer.parseInt(FilesIColumnesSeparat[0]);
        
        for(int i = 0; i<numFiles; ++i){
            
            System.out.println(RdFitchers.readLine()); 
            
        }
        
        RdFitchers.close();
        
    }
    
    
    public Cella[][] ObtenirTaulerDeFitxer(String NomFitxer, Cella[][] matriu) throws FileNotFoundException, IOException{
        
        File file =  new File("../src/kakuro/Dades/"+NomFitxer);
        Scanner in = new Scanner(file);

        //System.out.print("files: "); 
        String  str= in.next();
        int files;
        char ch[] = str.toCharArray();
        int j=0;
         if (j<(ch.length-1) && (ch[j+1] >= '0') && (ch[j+1] <= '9')){
             ++j;
             files=((ch[0]-'0')*10)+(ch[j]-'0');
         }
        else
            files=ch[j]-'0';
         
        j= j+2;
        
      int columnes; 
        if (j<(ch.length-1) && (ch[j+1] >= '0') && (ch[j+1] <= '9')){
             ++j;
             columnes=((ch[0]-'0')*10)+(ch[j]-'0');
         }
        else
            columnes=ch[j]-'0';   
        
        if (columnes<2 || files<2){
            System.out.printf("Kakruro massa petit!\n");
            return null;
        }
        matriu =new Cella[files][columnes];
         
     
        int c=0;
       
        int aux;//llegir valors de columnes i files.
        int aux2;//llegir si hi hagues un cas amb fila i columna
        
        for(int f=0;f<files && in.hasNext();f++){  
            c=0;
            str=in.next();
            char ch2[]  = str.toCharArray();
           for(int i=0;i<ch2.length ;i++){
              
                switch (ch2[i]) {
                    case '*':
                        matriu[f][c]= new CellaNegra();
                      
                        break;
                    case ',':   
                        c++;
                       
                        break;
                    case 'C':
                        i++;
                        int digit = ch2[i]-'0';
                       
                        if (i<(ch2.length-1) && (ch2[i+1] >= '0') && (ch2[i+1] <= '9')){
                              i++;
                              aux =(digit*10)+(ch2[i]-'0');
                             
                              
                        }
                        else
                            aux=digit;

                            if(i<(ch2.length-1) && ch2[i+1]=='F'){
                                i=i+2;
                                digit = ch2[i]-'0';
                                 
                               if ((ch2[i+1] >= '0') && (ch2[i+1] <= '9')){
                                        i++;
                                      aux2 =(digit* 10)+(ch2[i]-'0');
                                    
                                   
                                }
                                else
                                    aux2=digit;
                            }
                            else
                                aux2=0;
                        
                        matriu[f][c]= new CellaNegra(aux,aux2);
                       
                        break;
                    case 'F':
                         i++;
                        digit = ch2[i]-'0';
                        if ((ch2[i+1] >= '0') && (ch2[i+1] <= '9')){
                              i++;
                              aux =(digit*10)+(ch2[i]-'0');
                          
                              
                        }
                            else
                                aux=digit;
                        matriu[f][c]= new CellaNegra(0,aux);
                         
                        break;
                    case '?':
                        matriu[f][c]= new CellaBlanca();
                        
                        break;
                        
                    case '1':
                        matriu[f][c]= new CellaBlanca(1);
                        break;
                    case '2':
                        matriu[f][c]= new CellaBlanca(2);
                        break;
                    case '3':
                        matriu[f][c]= new CellaBlanca(3);
                        break;
                    case '4':
                        matriu[f][c]= new CellaBlanca(4);
                        break;
                        
                    case '5':
                        matriu[f][c]= new CellaBlanca(5);
                        break;
                        
                    case '6':
                        matriu[f][c]= new CellaBlanca(6);
                        break;
                    case '7':
                        matriu[f][c]= new CellaBlanca(7);
                        break;
                        
                    case '8':
                        matriu[f][c]= new CellaBlanca(8);
                        break;
                        
                        
                    case '9':
                        matriu[f][c]= new CellaBlanca(9);
                        break;
    
                    default: 
                       
                    
                        break;
                }
           }
        }
        
        return matriu;
        
    }
    
}
