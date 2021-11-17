package ik.tech.datastructure;

import java.util.ArrayList;
import java.util.LinkedList;

class Vertex{
    int data;
    boolean isVisited;
    
    public Vertex(int data){
        this.data=data;
    }//end Vertex constructor
}//end Vertex class

public class Graph {
    ArrayList<LinkedList<Vertex>> vertices;
    LinkedList<Vertex> adjacencyList;
    public Graph(){
        vertices=new ArrayList<LinkedList<Vertex>>();
    }//end Graph constructor
    
    public String addVertex(int v){
        Vertex vert = new Vertex(v);
        for(int i=0;i<vertices.size();i++)
            if(vertices.get(i).get(0).data==vert.data)
                return "same vertex is not allowed";


        adjacencyList=new LinkedList<Vertex>();
        adjacencyList.add(vert);
        vertices.add(adjacencyList);
        return vert.data +" is added to graph";
    }//end addVertex()

public String delVert(Integer vert){
        
    for(int i=0;i<vertices.size();i++) {
        Vertex temp = vertices.get(i).get(0);
        if (temp.data == vert) {

            for (int j = 0; j < vertices.size(); j++)
                vertices.get(j).remove(temp);
            vertices.remove(i);
            return  vert+ " is deleted from graph";
        }
    }
    return vert+" is not found in graph";
}//end delVert()

    public String addEdge(int u,int v){
        int i=0,j=0;
        Vertex vertU=null,vertV=null;

        for( i=0;i< vertices.size();i++) {
            if (vertices.get(i).get(0).data == u) {
                vertU = vertices.get(i).get(0);
                break;
            }
        }

        for(j=0;j< vertices.size();j++) {
            if (vertices.get(j).get(0).data == v) {
                vertV = vertices.get(j).get(0);
                break;
            }
        }

        if(vertU!=null && vertV!=null) {
            vertices.get(j).add(vertU);
            vertices.get(i).add(vertV);
            return u+" and "+v +" is connected through edge";
        }
        else if(vertU==null)
            return "vertex "+ u+ " is not available in graph";
        else if(vertV==null)
            return "vertex "+ v+ " is not available in graph";
        return"there is no edge between "+u+" and "+ v;

    }//end addEdge()


public String delEdge(int u,int v){
    int i=0,j=0;
    Vertex vertU=null,vertV=null;
    for( i=0;i< vertices.size();i++) {
        if (vertices.get(i).get(0).data == u) {
            vertU = vertices.get(i).get(0);
            break;
        }
    }

    for(j=0;j< vertices.size();j++) {
        if (vertices.get(j).get(0).data == v) {
            vertV = vertices.get(j).get(0);
            break;
        }
    }

    if(vertU!=null && vertV!=null) {
        vertices.get(i).remove(vertV);
        vertices.get(j).remove(vertU);
        return u+" and "+v +" is unconnected through edge";
    }
     if(vertU==null)
        return "vertex "+ u+ " is not available in graph";
    if(vertV==null)
        return "vertex "+ v+ " is not available in graph";
    return"there is no edge between "+u+" and "+ v;

}//end delEdge()

}//end Graph
