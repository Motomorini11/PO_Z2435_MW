import java.util.prefs.Preferences;

public class Game {
    private int turn;
    private int energy;
    private int maxEnergy;
    private int wood;
    private int stone;
    private int food;
    private int gold;
    private int iron;
    private int sword;
    private int tool;
    private int workers;
    private int maxWorkers;
    private int FreeWorkers;

    private Preferences prefs;
    private final String RECORD_KEY = "highestTurnRecord";

    public Game() {
        prefs = Preferences.userNodeForPackage(Game.class);
        this.turn = 1;
        this.energy = 10;
        this.maxEnergy = energy;
        this.wood = 100;
        this.stone = 100;
        this.food = 400;
        this.gold = 50;
        this.iron = 0;
        this.sword = 2100;
        this.tool = 0;
        this.workers = 5;
        this.maxWorkers = 5;
        this.FreeWorkers = workers;
    }


    public void nextTurn() {
        turn++;

        energy = Math.min(energy + 10, maxEnergy);


        if (food >= workers) {
            food -= workers;
        } else {
            int starvingWorkers = workers - food;
            food = 0;
            workers -= starvingWorkers;
        }


        wood = Math.max(wood, 0);
        stone = Math.max(stone, 0);
        food = Math.max(food, 0);
        workers = Math.max(workers, 0);

        FreeWorkers = workers;

        energy = Math.min(energy + 10, maxEnergy);
        checkForGameOver();
    }

    public boolean checkForGameOver() {
        return workers <= 0;
    }

    public int getTurn() {
        return turn;
    }

    public int getEnergy() {
        return energy;
    }

    public int getWood() {
        return wood;
    }
    public int getIron() {
        return iron;
    }
    public int getSword() {
        return sword;
    }
    public int getTool() {
        return tool;
    }

    public int getStone() {
        return stone;
    }

    public int getFood() {
        return food;
    }

    public int getGold() {
        return gold;
    }

    public int getWorkers() {
        return workers;
    }

    public int getMaxWorkers() {
        return maxWorkers;
    }

    public int getFreeWorkers() {
        return FreeWorkers;
    }

    public void storeHighestTurn() {
        int currentRecord = prefs.getInt(RECORD_KEY, 0);
        if (turn > currentRecord) {
            prefs.putInt(RECORD_KEY, turn);
        }
    }

    public int getHighestTurn() {
        return prefs.getInt(RECORD_KEY, 0);
    }

    public void useSword(int amount) {
        sword -= amount;
    }

    public boolean deductEnergy(int amount) {
        if (energy >= amount) {
            energy -= amount;
            return true;
        } else {
            return false;
        }
    }

    public boolean deductWorkers(int amount) {
        if (FreeWorkers >= amount) {
            FreeWorkers -= amount;
            return true;
        } else {
            return false;
        }
    }

    public boolean deductGold(int amount) {
        if (gold >= amount) {
            gold -= amount;
            return true;
        }else {
            return false;
        }

    }
    public boolean deductWood(int amount) {
        if (wood >= amount) {
            wood -= amount;
            return true;
        }else {
            return false;
        }
    }
    public boolean deductStone(int amount) {
        if (stone >= amount) {
            stone -= amount;
            return true;
        }else {
            return false;
        }
    }

    public void addEnergy(int amount) {
        energy = Math.min(energy + amount, maxEnergy);
    }
    public void addWorkers(int amount) {
        FreeWorkers = Math.min(FreeWorkers + amount, maxWorkers);
    }
    public void addwood(int amount) {
        wood = wood + amount;
    }
    public void addstone(int amount) {
        stone = stone + amount;
    }
    public void addFood(int amount) {
        food = food + amount;
    }
    public void addGold(int amount) {
        gold = gold + amount;
    }
    public void addTool(int amount) {
        tool = tool + amount;
    }
    public void addsword(int amount) {
        sword = sword + amount;
    }
    public void addiron(int amount) {
        iron = iron + amount;
    }

}