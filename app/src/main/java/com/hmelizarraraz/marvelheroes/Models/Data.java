package com.hmelizarraraz.marvelheroes.Models;

/**
 * Created by heriberto on 15/02/17.
 */

public class Data<T> {

    private int total;
    private T results;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
