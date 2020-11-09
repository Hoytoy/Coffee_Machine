package machine;

import java.util.Scanner;

public class CoffeeMachine {

    public static void main(String[] args) {

        if (!CoffeeMachineDemo.on) {
            CoffeeMachineDemo.turnOn();
        }

        CoffeeMachineDemo.setWater(400);
        CoffeeMachineDemo.setMilk(540);
        CoffeeMachineDemo.setCoffeeBeans(120);
        CoffeeMachineDemo.setDisposableCups(9);
        CoffeeMachineDemo.setMoney(550);

        Scanner scanner = new Scanner(System.in);
        while (CoffeeMachineDemo.on) {
            CoffeeMachineDemo.askForChoice();
            CoffeeMachineDemo.command(scanner.next());
        }
    }

}

class CoffeeMachineDemo {
    enum CoffeeSort {
        ESPRESSO(250, 0, 16, 4),
        LATTE(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6);

        int neededWater;
        int neededMilk;
        int neededCoffeeBeans;
        int neededDisposableCups;
        int cost;

        CoffeeSort(int neededWater, int neededMilk, int neededCoffeeBeans, int cost) {
            this.neededWater = neededWater;
            this.neededMilk = neededMilk;
            this.neededCoffeeBeans = neededCoffeeBeans;
            this.cost = cost;
        }
    }

    enum State {
        MAIN, BUY, FILL, TAKE, REMAINING
    }

    enum FillState {
        WATER, MILK, COFFEE_BEANS, DISPOSABLE_CUPS
    }

    public static int water = 0;
    public static int milk = 0;
    public static int coffeeBeans = 0;
    public static int disposableCups = 0;
    public static int money = 0;
    public static boolean on = false;
    public static State state = State.MAIN;
    public static FillState fillState = FillState.WATER;

    public static void turnOn() {
        on = true;
    }

    public static void turnOff() {
        on = false;
    }

    public static void setWater(int amount) {
        water = amount;
    }

    public static void setMilk(int amount) {
        milk = amount;
    }

    public static void setCoffeeBeans(int amount) {
        coffeeBeans = amount;
    }

    public static void setDisposableCups(int amount) {
        disposableCups = amount;
    }

    public static void setMoney(int amount) {
        money = amount;
    }

    public static void askForChoice() {
        switch (state) {
            case MAIN:
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                break;
            case BUY:
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                break;
            case FILL:
                switch (fillState) {
                    case WATER:
                        System.out.println("Write how many ml of water do you want to add:");
                        break;
                    case MILK:
                        System.out.println("Write how many ml of milk do you want to add:");
                        break;
                    case COFFEE_BEANS:
                        System.out.println("Write how many grams of coffee beans do you want to add:");
                        break;
                    case DISPOSABLE_CUPS:
                        System.out.println("Write how many disposable cups of coffee do you want to add:");
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    public static void command(String command) {
        switch (state) {
            case MAIN:
                mainMenu(command);
                break;
            case BUY:
                buyProcess(command);
                break;
            case FILL:
                fillProcess(Integer.parseInt(command));
                break;
            default:
                break;
        }
    }

    public static void mainMenu(String command) {
        switch (command) {
            case "buy":
                state = State.BUY;
                break;
            case "fill":
                state = State.FILL;
                break;
            case "take":
                state = State.TAKE;
                takeProcess();
                break;
            case "remaining":
                state = State.REMAINING;
                displayRemaining();
                break;
            case "exit":
                turnOff();
                break;
            default:
                break;
        }
    }

    public static void buyProcess(String command) {
        CoffeeSort coffeeSort;
        switch (command) {
            case "back":
                state = State.MAIN;
                return;
            case "1":
                coffeeSort = CoffeeSort.ESPRESSO;
                break;
            case "2":
                coffeeSort = CoffeeSort.LATTE;
                break;
            case "3":
                coffeeSort = CoffeeSort.CAPPUCCINO;
                break;
            default:
                return;
        }

        if (water < coffeeSort.neededWater) {
            System.out.println("Sorry, not enough water!");
        } else if (milk < coffeeSort.neededMilk) {
            System.out.println("Sorry, not enough milk!");
        } else if (coffeeBeans < coffeeSort.neededCoffeeBeans) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (disposableCups < coffeeSort.neededDisposableCups) {
            System.out.println("Sorry, not enough disposable cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            water -= coffeeSort.neededWater;
            milk -= coffeeSort.neededMilk;
            coffeeBeans -= coffeeSort.neededCoffeeBeans;
            disposableCups--;
            money += coffeeSort.cost;
        }
        state = State.MAIN;
    }

    public static void fillProcess(int amount) {
        switch (fillState) {
            case WATER:
                water += amount;
                fillState = FillState.MILK;
                break;
            case MILK:
                milk += amount;
                fillState = FillState.COFFEE_BEANS;
                break;
            case COFFEE_BEANS:
                coffeeBeans += amount;
                fillState = FillState.DISPOSABLE_CUPS;
                break;
            case DISPOSABLE_CUPS:
                disposableCups += amount;
                fillState = FillState.WATER;
                state = State.MAIN;
                break;
            default:
                break;
        }
    }

    public static void takeProcess() {
        System.out.printf("I gave you $%d\n", money);
        money = 0;
        state = State.MAIN;
    }

    public static void displayRemaining() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d of water\n", water);
        System.out.printf("%d of milk\n", milk);
        System.out.printf("%d of coffee beans\n", coffeeBeans);
        System.out.printf("%d of disposable cups\n", disposableCups);
        System.out.printf("%d of money\n", money);
        state = State.MAIN;
    }
}
