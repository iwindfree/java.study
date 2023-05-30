package io.windfree.algorithm.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AdjacencyList {

}

/* 무방향 graph
    A -- B -- C -- F
    | \      /
    |   \   /
    E --- D
 */
class Graph {
    public int numV;
    public int numE;
    public List<List<Vertex>> vList;
    public List<Boolean> visited;
    public  void init(int numV) {
        vList = new ArrayList<>();
        visited = new ArrayList<>();
        this.numV = numV;
        for(int i = 0; i < numV; i++) {
            vList.add(new ArrayList<>());
            visited.add(i,false);
        }

    }

    public void addEdge(Vertex fromV, Vertex toV) {
        vList.get(fromV.indx).add(toV);
        vList.get(toV.indx).add(fromV);
        this.numE++;
    }

    public void dfs(int start) {
        System.out.println("DFS visits:" + start);
        visited.set(start,true);
        for(int i =0; i < vList.get(start).size();i++) {
            int next = vList.get(start).get(i).indx;
            if(visited.get(next) == false) {
                dfs(next);
            }
        }

    }

    public void dfsAll() {
        for(int i = 0; i < vList.size(); i++) {
            visited.set(i,false);
        }
        for(int i = 0; i < vList.size(); i++) {
            if(visited.get(i) == false) {
                dfs(i);
            }
        }
    }

    public  void bfs(int start) {
        for(int i = 0; i < vList.size(); i++) {
            visited.set(i,false);
        }
        Queue<Integer> queue = new LinkedList<>();
        List<List<Integer>> resultList = new ArrayList<>();
        queue.offer(start);
        visited.set(start, true);
        while (!queue.isEmpty()) {
            int num = queue.poll();
            List<Integer> list = new ArrayList<>();
            for(Vertex v : vList.get(num)) {
                if(visited.get(v.indx) == false) {
                    visited.set(v.indx, true);
                    queue.offer(v.indx);
                    list.add(v.indx);
                }
            }
            if(list.size() > 0) {
                resultList.add(list);
            }
        }
        System.out.println(resultList);
    }

    /* 기존 bfs 에서 특정 노드까지 최단경로를 구하는 로직 추가 */
    List<Integer> preVertex = new ArrayList<>();
    public  void bfs2(int start) {
        for(int i = 0; i < vList.size(); i++) {
            visited.set(i,false);
        }

        List<Integer> distance = new ArrayList<>();


        for(int i = 0; i < vList.size(); i++) {
            distance.add(i,0);
            preVertex.add(i,-1);
        }

        Queue<Integer> queue = new LinkedList<>();
        List<List<Integer>> resultList = new ArrayList<>();
        queue.offer(start);
        visited.set(start, true);
        while (!queue.isEmpty()) {
            int num = queue.poll();
            List<Integer> list = new ArrayList<>();
            for(Vertex v : vList.get(num)) {
                if(visited.get(v.indx) == false) {
                    visited.set(v.indx, true);
                    queue.offer(v.indx);
                    list.add(v.indx);
                    distance.set(v.indx, distance.get(num) + 1);
                    preVertex.set(v.indx, num);
                }
            }
            if(list.size() > 0) {
                resultList.add(list);
            }
        }
        System.out.println(resultList);
    }

    public  void PrintPath(int from, int to) {

        if (from == to) {
            System.out.print(from + " ");
        } else if (preVertex.get(to) == -1) {
            System.out.println("no path");
        } else {
            PrintPath(from, preVertex.get(to));
            System.out.print(to + " ");
        }

    }



    /* vertexList, showGraph 는 단순히 출력을 위해서 사용하는 것일 뿐 */
    List<Vertex> vertexList = new ArrayList<>(); //그냥 출력을 위하여 넣어놓는 것.
    public  Vertex findVertex(int indx) {
        for(Vertex v : vertexList) {
            if (v.indx == indx) {
                return v;
            }
        }
        return null;
    }
    public void showGraph() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < vList.size(); i++) {
            sb.append(findVertex(i).value).append(" : ");
            for(Vertex v : vList.get(i)) {
                sb.append(v.value).append(" ");
            }
            System.out.println(sb.toString());
            sb.setLength(0);
        }
    }


    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.init(6);

        Vertex a = new Vertex(0,"A");
        Vertex b = new Vertex(1,"B");
        Vertex c = new Vertex(2,"C");
        Vertex d = new Vertex(3,"D");
        Vertex e = new Vertex(4,"E");
        Vertex f = new Vertex(5,"F");

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

        // 그래프에서 각 vertex 에서 연결된 vertex 정보들을 보여주는 함수
        graph.showGraph();;
        graph.dfs(1);
        System.out.println("call dfsAll");
        graph.dfsAll();


        System.out.println("------ call bfs");
        graph.bfs(0);

        System.out.println("------- call bfs2");
        graph.bfs2(0);

        System.out.println("------- call printPath");
        graph.PrintPath(0,5);
    }

}

class Vertex {
    int indx;
    String value;
    public Vertex(int indx, String value) {
        this.indx = indx;
        this.value = value;
    }
}
