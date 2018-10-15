package produto;

import java.util.ArrayList;


public class RepositorioProdutoArrayList implements RepositorioInterface {

	private ArrayList<Produto> produtos;
	int index = -1;

	public RepositorioProdutoArrayList(int size) {
		super();
		this.produtos = new ArrayList<Produto>();
	}

	private int procurarIndice(int codigo) {
		for (int i=0; i < produtos.size(); i++) {
			if (produtos.get(i).getCodigo() == codigo) {
				return i;	
			}
		}
		return -1;
	}

	public boolean existe(int codigo) {

		return false;
	}


	public void inserir(Produto produto) {

	}


	public void atualizar(Produto produto) {

	}

	public void remover(int codigo) {

	}


	public Produto procurar(int codigo) {
return null;
	}
}
