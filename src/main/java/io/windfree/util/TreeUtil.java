package io.windfree.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class TreeUtil {
	Map<String,Node> map = new HashMap<>();
	Gson gson = new Gson();
	Node node1 = new Node("1","0","node1");
	Node node2 = new Node("2","1","node2");
	Node node3 = new Node("3","2","node3");
	Node node4 = new Node("4","1","node4");
	Node node5 = new Node("5","3","node5");
	Node node6 = new Node("6","2","node6");
	public void makeTree() {
		map.put(node1.getId(), node1);
		map.put(node2.getId(), node2);
		map.put(node3.getId(), node3);
		map.put(node4.getId(), node4);
		map.put(node5.getId(), node5);
		map.put(node6.getId(), node6);
		
		Node rootNode = null;
		for(String key : map.keySet()) {
			Node node = findNode(key);
			if(node.getPid().equals("0")) {
				rootNode = node;
			}
			Node parentNode = findNode(node.getPid());
			if(parentNode != null) {
				parentNode.children.add(node);
			}
		}
		String jsonString = gson.toJson(rootNode);
		System.out.println(jsonString);
	}
	
	private Node findNode(String id) {
		for(String key : map.keySet()) {
			Node node = map.get(id);
			if(node != null && node.getId().equals(id)) {
				return node;
			}
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		new TreeUtil().makeTree();
		
	}
	
	
}

class Node {
	private String id;
	private String pid;
	private String name;
	public List<Node> children = new ArrayList<>();
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Node(String id, String pid, String name) {
		this.id = id;
		this.pid = pid;
		this.name = name;
	}
	
	public void add(Node node) {
		children.add(node);
	}
	
}
