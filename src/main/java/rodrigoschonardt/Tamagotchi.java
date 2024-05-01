package rodrigoschonardt;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Rodrigo Augusto Schonardt
 *
 * implementação da classe Tamagotchi
 *
 **/
public class Tamagotchi {
    private String name = "";
    private int age = 0;
    private int weight = 1;

    // control inside the class
    private int sleepyCount = 0;

    private final Scanner scanner = new Scanner( System.in );

    // getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return Math.max( weight, 0 );
    }

    // método para verificação se continua vivo
    private boolean isAlive() {
        boolean alive = true;

        if ( age == 15 ) {
            alive = false;
            System.out.println( getName() + " chegou a 15 dias de vida e faleceu!" );
        }

        else if ( weight > 20 ) {
            alive = false;
            System.out.println( getName() + " chegou a mais de 20 quilos e explodiu!" );
        }

        else if ( weight <= 0 ) {
            alive = false;
            System.out.println( getName() + " chegou a 0 quilos ficou desnutrido e morreu!" );
        }

        return alive;
    }

    // método para ligar o tamagotchi
    public void turnOn() {
        System.out.println( "Tamagotchi ligado!\n" );
        System.out.println( "Informe o nome desejado: " );
        this.name = scanner.nextLine();

        System.out.println( "Diga olá para " + this.getName() + "!" );
        System.out.println( """
                 /^ ^\\
                / 0 0 \\
                V\\ Y /V
                 / - \\
                 |    \\
                 || (__V
                """ );

        while( isAlive() ) {
            getFeeling();
        }
    }

    // método para sortear o comportamento
    private void getFeeling() {
        int random = new Random().nextInt( 3 );

        switch ( random ) {
            case 0:
                sleepyAction( false );
                break;

            case 1:
                hungerAction( false );
                break;

            case 2:
                boredAction();
                break;
        }

        System.out.println( this );
    }

    // método de controle do sono
    private void sleepyAction( boolean force ) {
        System.out.println( "\n" + getName() + " está com sono!" );
        System.out.println( """
                        SONO!
                       /
                 /^ ^\\
                / 0 0 \\
                V\\ Y /V
                 / - \\
                 |    \\
                 || (__V
                """ );

        if ( force || sleepyCount == 5 )
        {
            System.out.println( getName() + " está muito cansado e dormiu!" );
            sleepyCount = 0;
            age++;
            return;
        }

        System.out.println( "Selecione uma das opções abaixo:\n1 - Dormir\n2 - Permanecer acordado" );
        int action = scanner.nextInt();

        switch (action ) {
            case 1:
                age++;
                sleepyCount = 0;
                break;

            case 2:
                sleepyCount++;
                break;
        }
    }

    // método de controle da fome
    private void hungerAction( boolean force ) {
        System.out.println( "\n" + getName() + " está com fome!" );
        System.out.println( """
                        FOME!
                       /
                 /^ ^\\
                / 0 0 \\
                V\\ Y /V
                 / - \\
                 |    \\
                 || (__V
                """ );

        int action;

        if ( force ) {
            action = 1;
            System.out.println( getName() + " estava com muita fome e comeu muito!" );
        }

        else {
            System.out.println( "Selecione uma das opções abaixo:\n1 - Comer muito\n2 - Comer pouco \n3 - Não comer" );
            action = scanner.nextInt();
        }

        switch ( action ) {
            case 1:
                weight += 5;
                sleepyAction( true );
                break;

            case 2:
                weight++;
                break;

            case 3:
                weight -= 2;
                break;

        }
    }

    // método de controle do tédio
    private void boredAction() {
        System.out.println( "\n" + getName() + " está entediado!" );
        System.out.println( """
                        TÉDIO!
                       /
                 /^ ^\\
                / 0 0 \\
                V\\ Y /V
                 / - \\
                 |    \\
                 || (__V
                """ );

        System.out.println( "Selecione uma das opções abaixo:\n1 - Correr 10 minutos \n2 - Caminhar 10 minutos" );
        int action = scanner.nextInt();

        switch ( action ) {
            case 1:
                weight -= 4;
                hungerAction( true );
                break;

            case 2:
                weight -= 1;
                hungerAction( false );
                break;
        }
    }

    @Override
    public String toString() {
        return "-----------------------------------\nNome: " + getName()
                + "\nIdade: " + getAge() + "\nPeso: " + getWeight()
                + "\n-----------------------------------";
    }
}
