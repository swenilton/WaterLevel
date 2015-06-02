package br.com.coffeebeans.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
 
public final class Sucesso
        implements Serializable {
 
    private final List<String> sucessos;
 
    public Sucesso() {
    	sucessos = new ArrayList<>();
    }
 
    public Sucesso(String mensagem) {
    	sucessos = new ArrayList<>();
        sucessos.add(mensagem);
    }
 
    public void add(String mensagem) {
        sucessos.add(mensagem);
    }
    
    public boolean isExisteSucessos() {
        return !sucessos.isEmpty();
    }
    
    public List<String> getSucessos() {
        return sucessos;
    }
}

