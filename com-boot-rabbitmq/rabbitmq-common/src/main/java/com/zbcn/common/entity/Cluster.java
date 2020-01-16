package com.zbcn.common.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 集群
 */
@Data
public abstract class Cluster {

    public List<Node> nodes;

    public Cluster() {
        this.nodes = new ArrayList<>();
    }

    public abstract void addNode(Node node);

    public abstract void removeNode(Node node);

    public abstract Node get(String key);
}