package utils;

/**
 *
 * @author Matheus Max
 */
public class IMC {
    
    private double height = 0.0, weight = 0.0;
    
    public IMC(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }
    
    public double number() {
        return this.weight/Math.pow(this.height, 2);
    }
    
    public String result() {
        if(this.number() < 18.5) {
            return "Abaixo do Peso";
        } else if(this.number() < 25) {
            return "Peso Normal";
        } else if(this.number() < 30){
            return "Sobrepeso";
        } else if(this.number() < 35) {
            return "Obesidade de Grau 1";
        } else if(this.number() < 40) {
            return "Obesidade de Grau 2";
        } else {
            return "Obesidade de Grau 3";
        }
    }
    
}
