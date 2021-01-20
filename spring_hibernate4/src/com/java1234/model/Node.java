package com.java1234.model;

import java.util.HashSet;
import java.util.Set;

public class Node {

	private long id;
	private String name;
	
	private Node parentNode;
	
	private Set<Node> childNodes=new HashSet<Node>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Node getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	public Set<Node> getChildNodes() {
		return childNodes;
	}

	public void setChildNodes(Set<Node> childNodes) {
		this.childNodes = childNodes;
	}
	
	
	
}
