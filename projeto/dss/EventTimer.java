package dss;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EventTimer {

    double time;
    Event ev;

    public EventTimer(Event ev, int u, int q, int s, double inst) {
        this.ev = ev;

        double mean;
        double exp;

        switch (ev.getType()) {
            case 2:

                //calcula média da expressão fornecida
                mean = (1-Math.log(comfort))*q;

                //gera número aleatório uniformemente distribuido entre 0 e 1  
                exp = exponencialDistribution();

                //aplica distribuição exponencial com a média
                this.time = inst + Math.log(1-exp)*mean;
                
                break;

            case 3:

                //calcula média da expressão fornecida
                mean = (1-Math.log(comfort))*s;

                //gera número aleatório uniformemente distribuido entre 0 e 1  
                exp = exponencialDistribution();

                //aplica distribuição exponencial com a média
                this.time = inst + Math.log(1-exp)*mean;
                
                break;

            case 4:

                //calcula média da expressão fornecida
                mean = (1-Math.log(1-comfort))*u;
                
                //gera número aleatório uniformemente distribuido entre 0 e 1  
                exp = exponencialDistribution();

                //aplica distribuição exponencial com a média
                this.time = inst + Math.log(1-exp) * mean;

                break;
        
            default:

                //print

                break;
        }
    }

    private double exponencialDistribution(){
        return ThreadLocalRandom.current().nextDouble();
    }
    
}
