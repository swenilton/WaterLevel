package br.com.coffeebeans.repositorio;

public class RepositorioJaExistenteException extends Exception {

public RepositorioJaExistenteException () {
 super("O reposit�rio j� existe!");	
}
}
