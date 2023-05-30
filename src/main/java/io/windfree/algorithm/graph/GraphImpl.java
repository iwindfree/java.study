package io.windfree.algorithm.graph;

import java.util.ArrayList;
import java.util.List;
/* 무방향 graph
    A -- B -- C -- F
    | \      /
    |   \   /
    E --- D
 */
public class GraphImpl
{
    private  int numVertex;
    private  List<List<VertexImpl>> grpahList;
    List<VertexImpl> vertexList = new ArrayList<>(); //그냥 출력을 위하여 넣어놓는 것.
    public void init(int numV) {
        grpahList = new ArrayList<List<VertexImpl>>();
        vertexList = new ArrayList<>();
        for(int i = 0; i < numV; i++) {
            grpahList.add(new ArrayList<>());
        }
        this.numVertex = numV;

    }


    public void addEdge(VertexImpl fromV, VertexImpl toV) {
        grpahList.get(fromV.indx).add(toV);
        grpahList.get(toV.indx).add(fromV);

    }

    public  void showGraph() {
        for(int i = 0; i < this.numVertex; i++) {
            List<VertexImpl> vlist = grpahList.get(i);
            System.out.print(findVertex(i).value + " : ");
            for(int j = 0; j < vlist.size(); j++) {
                VertexImpl vertex = vlist.get(j);
                System.out.print(vertex.value + " ");
            }
            System.out.println();
        }
    }

    public VertexImpl findVertex(int indx) {
        return this.vertexList.get(indx);
    }

    public static void main(String[] args) {
        GraphImpl graph = new GraphImpl();
        graph.init(6);
        VertexImpl a = new VertexImpl(0,"A");
        VertexImpl b = new VertexImpl(1,"B");
        VertexImpl c = new VertexImpl(2,"C");
        VertexImpl d = new VertexImpl(3,"D");
        VertexImpl e = new VertexImpl(4,"E");
        VertexImpl f = new VertexImpl(5,"F");

        // 출력을 위해서 넣어놓는 것일
        graph.vertexList.add(a);
        graph.vertexList.add(b);
        graph.vertexList.add(c);
        graph.vertexList.add(d);
        graph.vertexList.add(e);
        graph.vertexList.add(f);

        graph.addEdge(a,b);
        graph.addEdge(a,d);
        graph.addEdge(a,e);
        graph.addEdge(b,c);
        graph.addEdge(c,d);
        graph.addEdge(d,e);
        graph.addEdge(c,f);
        graph.showGraph();;
    }
}

class VertexImpl {
    int indx;
    String value;
    public  VertexImpl(int i, String v) {
        this.indx = i;
        this.value = v;
    }
}
