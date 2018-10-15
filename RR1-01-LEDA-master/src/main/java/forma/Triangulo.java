package forma;

public class Triangulo implements FormasGeometricas {
	
	private double base;
	private double altura;
	
	public Triangulo(double base, double altura) throws DoubleInvalidoException {
		
		if (base <= 0) {
			throw new DoubleInvalidoException("Valor da base nao pode ser negativo ou igual a zero");
		}
		
		if (altura <= 0) {
			throw new DoubleInvalidoException("Valor da altura nao pode ser negativo ou igual a zero");
		}
		this.base = base;
		this.altura = altura;
	}


	public double getBase() {
		return base;
	}


	public void setBase(double base) {
		this.base = base;
	}


	public double getAltura() {
		return altura;
	}


	public void setAltura(double altura) {
		this.altura = altura;
	}


	public double CalculaAreaDaForma() {
		double areaTriangulo = (this.base * this.altura)/2;
		return areaTriangulo;
	}
	
	

}
