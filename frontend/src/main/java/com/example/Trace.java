package com.example;

import java.util.List;
import java.util.ArrayList;

class Trace {
    public List<String> list;

    public Trace() {
        this.list = new ArrayList<>();
    }

    public Trace(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    public void add(String span) {
        list.add(span);
    }

    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("[trace: {");
        for (String s : list) {
            b.append(s);
            b.append(",\n");
        }
        b.append("}]");
        return b.toString();
    }
}
