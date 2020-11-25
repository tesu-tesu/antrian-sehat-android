package com.antriansehat.application.model;

import java.util.List;

public class Pagination<T>{
    private int current_page, from, last_page, per_page, to, total;
    private List<T> data;

    public Pagination(int current_page, int from, int last_page, int per_page, int to, int total, List<T> data) {
        this.current_page = current_page;
        this.from = from;
        this.last_page = last_page;
        this.per_page = per_page;
        this.to = to;
        this.total = total;
        this.data = data;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
