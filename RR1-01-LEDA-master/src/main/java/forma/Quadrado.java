package forma;

public class Quadrado implements FormasGeometricas {

	private double lado;

	public Quadrado(double lado) throws DoubleInvalidoException {

		if (lado <= 0) {
			throw new DoubleInvalidoException("Valor do lado nao pode ser negativo ou igual a zero");
		}
		this.lado = lado;
	}

	public double getLado() {
		return lado;
	}

	public void setBase(double lado) {
		this.lado = lado;
	}

	public double CalculaAreaDaForma() {
		double areaQuadrado = Math.pow(lado, 2);
		return areaQuadrado;
	}
	
}

