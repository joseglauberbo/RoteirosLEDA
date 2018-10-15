package forma;

public class Circulo implements FormasGeometricas {

	private double raio;
	
	public Circulo (double raio) throws DoubleInvalidoException {
		
		if (raio <= 0) {
			throw new DoubleInvalidoException("Valor do raio não pode ser negativo ou abaixo de xero");
		}
		
		
		this.raio = raio;
	}

	public double getRaio() {
		return raio;
	}

	public void setRaio(double raio) {
		this.raio = raio;
	}

	public double CalculaAreaDaForma() {
		double areaCirculo = Math.PI * Math.pow(raio, 2);
		return areaCirculo;
	}
	
}
