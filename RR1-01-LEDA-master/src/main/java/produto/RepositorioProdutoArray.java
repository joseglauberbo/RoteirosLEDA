package produto;

import java.util.ArrayList;

public class RepositorioProdutoArray implements RepositorioInterface {

	protected Produto[] produtos;
	private int index = -1;

	public RepositorioProdutoArray(int size) {
		super();
		this.produtos = new Produto[size];
	}

	private int procurarIndice(int codigo) {
		for (int i=0; i < produtos.length; i++) {
			if(produtos[i].getCodigo() == codigo) {
				return i;
			}
		}
		return -1;
	}

	public boolean existe(int codigo) {
		for (int i=0; i < produtos.length; i++) {
			if (produtos[i].getCodigo() == codigo) {
				return true;
			}
		}
		return false;
	}

	public void inserir(Produto produto) {
		produtos[index] = produto;
	}

	public void atualizar(Produto produto) {
		int codigo = produto.getCodigo();
		for (int i=0; i<produtos.length; i++) {
			if (produtos[i] instanceof ProdutoNaoPerecivel) {
				if (produtos[i].getCodigo() == codigo) {
					produtos[i] = produto;
				}else{
					throw new UnsupportedOperationException("Produto nao localizado no array");
				}
			}else{
				throw new UnsupportedOperationException("Objeto nao eh do tipo ProdutoNaoPerecivel");
			}
		}
	}

	public void remover(int codigo) {
		for (int i=produtos.length; i<produtos.length; i--) {
			if (produtos[i] instanceof ProdutoNaoPerecivel) {
				if (produtos[i].getCodigo() == codigo) {
					produtos[i] = null;
				}else{
					throw new UnsupportedOperationException("Produto nao localizado no array");
				}
			}
			throw new UnsupportedOperationException("Objeto nao eh do tipo ProdutoNaoPerecivel");
		}
	}

	public Produto procurar(int codigo) {
		for (int i=0; i < produtos.length; i++) {
			if(produtos[i].getCodigo() == codigo) {
				return produtos[i];
			}
		}
		return null;
	}


}
