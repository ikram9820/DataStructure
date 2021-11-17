package ik.tech.datastructure;

import java.util.ArrayList;

class Vert{
    int data;
    boolean isVisited;

    public Vert(int data){
        this.data=data;
    } //end Vert contructor
}//end Vert class

public class DiGraph {
    ArrayList<Vert> vertices;
    private final int maxnVert=20;
    int adjacencyMat[][];
    int nVert;

    public DiGraph(){
        vertices=new ArrayList < Vert > ();
        adjacencyMat=new int[maxnVert][maxnVert];
        nVert=0;
        for(int i=0;i<maxnVert;i++){
            for (int j=0;j<maxnVert;j++)
                adjacencyMat[i][j]=0;
        }//end for loop
    }//end DiGraph constructor

    public String addVert(int v){
        Vert vert= new Vert(v);
        for(int i=0;i<vertices.size();i++)
            if(vertices.get(i).data==vert.data)
                return "same vertex is not allowed";

        vertices.add(vert);
        return v+" is added in graph";
    }//end addVert()

    public String delVert(int v){
        int index = getVertIndex(v);

      if( index ==-1)
          return v+" is not found in graph";
      for(int i=0;i<vertices.size();i++) {
          adjacencyMat[index][i] = 0;
          adjacencyMat[i][index] = 0;
      }
        vertices.remove(index);
        return v +" is deleted from graph";
    }//end delVert()



    public String addEdge(int u,int v){
        int uIndex=0,vIndex=0;
        uIndex=getVertIndex(u);
        vIndex=getVertIndex(v);

        if(vIndex!=-1 && uIndex!=-1) {
            adjacencyMat[uIndex][vIndex]=1;
            return u +" is connected to "+v;
        }
        if(uIndex==-1)
            return "vertex "+ u+ " is not available in graph";
        else
            return "vertex "+ v+ " is not available in graph";

    }//end addEdge()

    public String delEdge(int u,int v){
        int uIndex=0,vIndex=0;
        uIndex=getVertIndex(u);
        vIndex=getVertIndex(v);

        if(vIndex!=-1 && uIndex!=-1) {
            adjacencyMat[uIndex][vIndex]=0;
            return u +" is unconnected from "+v;
        }
        if(uIndex==-1)
            return "vertex "+ u+ " is not available in graph";
        if(vIndex==-1)
            return "vertex "+ v+ " is not available in graph";
        return"there is no edge between "+u+" to "+ v;

    }//end delEdge()

    public int getVertIndex(int v){
        for(int i=0;i<vertices.size();i++){
            if(vertices.get(i).data==v)
                return i;
        }
        return  -1;
    }//end getVertIndex()
}//end DiGraph class
