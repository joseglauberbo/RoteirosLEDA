package produto;


public class RepositorioProdutoPerecivelArray extends RepositorioProdutoArray {

	public RepositorioProdutoPerecivelArray(int size) {
		super(size);
		this.produtos = new ProdutoPerecivel[size];
	}

	public void inserir(Produto produto) {
		if (produto instanceof ProdutoPerecivel) {
			super.inserir(produto);
		}
	}


	public void atualizar(Produto produto) {
		if (produto instanceof ProdutoNaoPerecivel) {
			super.atualizar(produto);
		}
	}

}
