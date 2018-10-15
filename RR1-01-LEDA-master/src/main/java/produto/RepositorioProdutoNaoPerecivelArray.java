package produto;


public class RepositorioProdutoNaoPerecivelArray extends RepositorioProdutoArray {

	public RepositorioProdutoNaoPerecivelArray(int size) {
		super(size);
		this.produtos = new Produto[size];
	}
	
	public void inserir(Produto produto) {
		if (produto instanceof ProdutoNaoPerecivel) {
			super.inserir(produto);
		}
	}

	public void atualizar(Produto produto) {
		if (produto instanceof ProdutoNaoPerecivel) {
			super.atualizar(produto);
		}
	}

}
